var formTarea = null;
var ddlNuevaTareaProyecto = null;
var modalTarea = null;
var modalBtnGuardar = null;
var modalTareaAnterior = null;
var idHistoriaTarea = 0;
var datosEmpleadoTarea = { codEmpleado: '', codTarea: '' }

document.addEventListener("DOMContentLoaded", function (event) {


	var zoomConfig = {
		levels: [
			{
				name: "day",
				scale_height: 27,
				min_column_width: 80,
				scales: [
					{ unit: "day", step: 1, format: "%d %M" }
				]
			},
			{
				name: "week",
				scale_height: 50,
				min_column_width: 50,
				scales: [
					{
						unit: "week", step: 1, format: function (date) {
							var dateToStr = gantt.date.date_to_str("%d %M");
							var endDate = gantt.date.add(date, -6, "day");
							var weekNum = gantt.date.date_to_str("%W")(date);
							return "#" + weekNum + ", " + dateToStr(date) + " - " + dateToStr(endDate);
						}
					},
					{ unit: "day", step: 1, format: "%j %D" }
				]
			},
			{
				name: "month",
				scale_height: 50,
				min_column_width: 120,
				scales: [
					{ unit: "month", format: "%F, %Y" },
					{ unit: "week", format: "Week #%W" }
				]
			},
			{
				name: "quarter",
				height: 50,
				min_column_width: 90,
				scales: [
					{ unit: "month", step: 1, format: "%M" },
					{
						unit: "quarter", step: 1, format: function (date) {
							var dateToStr = gantt.date.date_to_str("%M");
							var endDate = gantt.date.add(gantt.date.add(date, 3, "month"), -1, "day");
							return dateToStr(date) + " - " + dateToStr(endDate);
						}
					}
				]
			},
			{
				name: "year",
				scale_height: 50,
				min_column_width: 30,
				scales: [
					{ unit: "year", step: 1, format: "%Y" }
				]
			}
		]
	};

	gantt.ext.zoom.init(zoomConfig);


	// specifying the date format
	gantt.config.api_date = "%d/%m/%Y %H:%i";
	gantt.config.date_format = "%d/%m/%Y %H:%i";
	gantt.config.load_date = "%d/%m/%Y";
	gantt.config.columns = [
		{ name: "text", label: "Tarea", tree: true, width: "200" },
		{ name: "duration", label: "Duración", align: "center", width: "60" },
		{ name: "start_date", label: "Inicio", align: "center", width: "80" },
		{ name: "end_date", label: "Fin", align: "center", width: "80" }//,
		//{name:"progress",   label:"Progreso",   align:"center" },
	];
	cargarEventosGantt();
	// several scales at once
	setScaleConfig("year");

	gantt.config.work_time = true;
	gantt.config.duration_unit = "day";
	gantt.templates.scale_cell_class = function (date) {
		if (date.getDay() == 0 || date.getDay() == 6) {
			return "weekend";
		}
	};

	gantt.config.work_time = true;

	gantt.attachEvent("onLoadStart", function () {
		$.LoadingOverlay("show", {
			textResizeFactor: 0.3,
			text: "Se está cargando su información, esto podría tardar un poco"
		});
	});
	gantt.attachEvent("onLoadEnd", function () {
		$.LoadingOverlay("hide");
	});

	// initializing gantt
	gantt.init("cl-gantt");

	// initiating data loading
	gantt.load("/api/gantt?modo=e");

	// initializing dataProcessor
	var dp = new gantt.dataProcessor("/api/gantt?modo=e");
	// and attaching it to gantt
	dp.init(gantt);

	// setting the REST mode for dataProcessor
	dp.setTransactionMode("REST");


	var markerId = gantt.addMarker({
		start_date: new Date(),
		css: "today",
		text: "Hoy",
		title: "Hoy"
	});
	gantt.getMarker(markerId);


	var els = document.querySelectorAll("select[name='scale']");
	for (var i = 0; i < els.length; i++) {
		els[i].onchange = function (e) {
			var el = e.target;
			var value = el.value;
			setScaleConfig(value);
			gantt.render();
		};
	}

	/*   $("#tarea-tiempo-tab").on("click", function(){
		   $("div #botones").remove();
		   alert('click');
	   });
	  */


});

function setScaleConfig(level) {
	switch (level) {
		case "day":
			gantt.config.scales = [
				{ unit: "day", step: 1, format: "%d %M" }
			];
			gantt.config.scale_height = 27;
			break;
		case "week":
			var weekScaleTemplate = function (date) {
				var dateToStr = gantt.date.date_to_str("%d %M");
				var endDate = gantt.date.add(gantt.date.add(date, 1, "week"), -1, "day");
				return dateToStr(date) + " - " + dateToStr(endDate);
			};
			gantt.config.scales = [
				{ unit: "week", step: 1, format: weekScaleTemplate },
				{ unit: "day", step: 1, format: "%D" }
			];
			gantt.config.scale_height = 50;
			break;
		case "month":
			gantt.config.scales = [
				{ unit: "month", step: 1, format: "%F, %Y" },
				{ unit: "day", step: 1, format: "%j, %D" }
			];
			gantt.config.scale_height = 50;
			break;
		case "year":
			gantt.config.scales = [
				{ unit: "year", step: 1, format: "%Y" },
				{ unit: "month", step: 1, format: "%M" }
			];
			gantt.config.scale_height = 90;
			break;
	}
}


$(function () {
	formTarea = $("form#cl-form-tarea");
	ddlNuevaTareaProyecto = $(".pnl-nueva-tarea select[name='codproyecto']");
	modalTarea = $("#cl-tarea-modal");
	modalBtnGuardar = $("#btn-moda-tarea-guardar");

	var btnCrearTarea = $("#btn-tarea-crear-tab");
	btnCrearTarea.remove();

	modalTarea.on('hidden.bs.modal', function (e) {
		modalBtnGuardar.removeAttr("disabled");
	});

	// Para no generar confusión, cuando es un empleado no administrador se bloquean los campos de la tarea que no puede modificar
	modalTarea.find("#tarea-info-panel, #tarea-estimacion-panel").find("input,select,textarea,button").prop("disabled", true);
	// Sólo se deja activo el campo de estado
	modalTarea.find("#tarea-info-panel select[name='codtareaestado']").removeAttr("disabled");

	// Filtros del GANTT
	$(".cl-seccion-gantt").find("#txt-fechainicio, #txt-fechafin").change(function () {
		var el = $(this);
		if ((el.attr("id") === "txt-fechainicio" && el.val() !== $("#hd-fechainicio").val()) ||
			(el.attr("id") === "txt-fechafin" && el.val() !== $("#hd-fechafin").val())) {
			$("#hd-fechainicio").val($("#txt-fechainicio").val());
			$("#hd-fechafin").val($("#txt-fechafin").val());
			cargarDatosGantt();
		}
	});

	cargarEventosTareas();
});

function cargarEventosGantt() {

	gantt.showLightbox = function (id) {
		// Sí se abre un proyecto se carga como si fuera una tarea para habilitar la funcionalidad de agregar tareas hijas
		if (id < 0) {
			solicitudAjax("/api/proyecto/" + (id * -1), "POST", null, function (data) {
				var idproyecto = id;
				let tarea = {
					id: idproyecto,
					nombre: data.nombre,
					descripcion: data.descripcion,
					codproyecto: data.id,
					fechainiestimada: data.fechainicio,
					fechafinestimada: data.fechafin
				};
				modalBtnGuardar.prop("disabled", true);
				cargarDatosEnModal(tarea);
				modalTarea.find("#tarea-info-tab").tab("show");
				// Sí es un proyecto se inactiva el registro de tiempo
				modalTarea.find("#tarea-tiempo-tab").addClass("disabled");
				modalTarea.modal("show");
				$.LoadingOverlay("hide");
			});
		} else {
			solicitudAjax("/api/tarea/" + id, "POST", null, function (data) {
				//alert('alerta '+data.id+'---'+data.codempleadoasignado);
				$("#fechahorainicio").val(fechaActual());
				if (data.codempleadoasignado) {
					datosEmpleadoTarea.codEmpleado = data.codempleadoasignado;
					datosEmpleadoTarea.codTarea = data.id;
					$("#tareaFormRegistroTiempo").show();
				} else {
					$("#tareaFormRegistroTiempo").hide();
				}
				cargarDatosEnModal(data);
				$.LoadingOverlay("hide");
				modalTarea.modal("show");
				formTarea.find("[name='horas']").val("")
				formTarea.find("[name='descripciontiempo']").val("");
				modalTarea.find("#tarea-info-tab").tab("show");
				modalTarea.find("#tarea-tiempo-tab").removeClass("disabled");

				//Obtiene el id de la tarea para el historial
				idHistoriaTarea = data.id;

				// Se carga el historial de registro de tiempos de la tarea
				solicitudAjax("/Empleado/Tareas/historial?id=" + data.id, "POST", null, function (html) {
					$.LoadingOverlay("hide");
					formTarea.find(".cl-tarea-tiempo-historial").empty().append(html);
				});
				$.LoadingOverlay("hide");
			});
		}
	}


	gantt.templates.tooltip_text = function (start, end, task) {
		if (task.type === "project") {
			return "<b>Proyecto:</b> " + task.text;
		} else {
			let tooltip = "<b>Tarea:</b> " + task.text + "<br/>";
			tooltip += "<b>Responsable:</b> " + (task.empleadoasignado ? task.empleadoasignado : "") + "<br/><br/>";
			tooltip += "<table class='table table-sm'><tr>";
			tooltip += "<th></th><th>Fecha inicio</th><th>Feha fin</th><th>Horas</th></tr>";
			tooltip += "<tr><th>Estimación</th><td>" + (task.fechainiestimada ? task.fechainiestimada.substring(0, 10) : "") + "</td><td>" + (task.fechafinestimada ? task.fechafinestimada.substring(0, 10) : "") + "</td><td>" + (task.tiempoestimado ? task.tiempoestimado : "") + "</td></tr>";
			tooltip += "<tr><th>Real</th><td>" + (task.fechainireal ? task.fechainireal.substring(0, 10) : "") + "</td><td>" + (task.fechafinreal ? task.fechafinreal.substring(0, 10) : "") + "</td><td>" + (task.tiemporeal ? task.tiemporeal : "") + "</td></tr>";
			tooltip += "</table>";
			return tooltip;
		}
	};
}


function cargarEventosTareas() {

	// Tarea tipo: Aplicar el color correspondiente al tipo de tarea
	$("select[name='codtareatipo']").unbind("change").change(function () {
		$(this).parents(".cl-tarea-item").css("background-color", $(this).find(":selected").attr("data-color"));
	});

	// Desde la vista del empleado cuando cambia el estado de la tarea autómaticamente se actualiza en BD
	modalTarea.find(".cl-tarea-item select[name='codtareaestado']").change(function () {
		solicitudAjax("/Tareas/CambiarEstado", "POST", formTarea.serializeObject(), function (data) {
			alert("El estado se modificó correctamente.");
		});
		$.LoadingOverlay("hide");
	});

	formTarea.submit(function (e) {
		e.preventDefault();

		if (formTarea.valid()) {
			console.log('Inicio guardado tarea')

			solicitudAjax("/Empleadocontrol/horasRecursoFecha?codEmpleado=" + datosEmpleadoTarea.codEmpleado +
				"&fecha=" + formTarea.find("[name='fechahorainicio']").val(), "GET", null, function (resultado) {

					solicitudAjax("/Empleadocontrol/horasTareaRecursoFecha?codEmpleado=" + datosEmpleadoTarea.codEmpleado +
						"&codTarea=" + datosEmpleadoTarea.codTarea + "&fecha=" + formTarea.find("[name='fechahorainicio']").val(), "GET", null, function (data) {
							if (resultado != null && (resultado == 9 || resultado >= 9)) {
								if (!confirm("Usted ya tiene registrado en total " + resultado + " horas para la fecha seleccionada y para esta tarea " + data +
									" horas ¿Desea realizar el registro ?")) {
									$.LoadingOverlay("hide");
									modalBtnGuardar.removeAttr("disabled");
								}
								else {
									registroTiempoTareas();
									$.LoadingOverlay("hide");
									modalBtnGuardar.removeAttr("disabled");
								}
							}
							else {
								registroTiempoTareas();
								$.LoadingOverlay("hide");
								modalBtnGuardar.removeAttr("disabled");
							}
							$.LoadingOverlay("hide");
							modalBtnGuardar.removeAttr("disabled");
						});
				});

		}

		return false;
	});
}

function registroTiempoTareas() {
	// se bloquea botón para evitar regitros duplicados
	modalBtnGuardar.prop("disabled", true);
	solicitudAjax("/Empleado/Tareas", "POST", {
		codtarea: formTarea.find("[name='id']").val(),
		codempleado: formTarea.find("[name='codproyecto']").val(),
		fechahorainicio: formTarea.find("[name='fechahorainicio']").val(),
		fechahorafin: formTarea.find("[name='fechahorafin']").val(),
		horas: formTarea.find("[name='horas']").val(),
		descripcion: formTarea.find("[name='descripciontiempo']").val()
	}, function (data) {
		//var res = jQuery.parseJSON(JSON.stringify(data));
		//if (res.excedido === 0) {
		$.LoadingOverlay("hide");
		$.toast({
			heading: 'Información',
			text: 'Su registro de horas se ejecutó de manera satisfactoria',
			showHideTransition: 'slide',
			icon: 'info',
			hideAfter: 5000,
			position: 'top-right'
		});
		/*} else {
			$.toast({
				heading: 'Precaución',
				text: 'Cantidad de horas excedidas para el proyecto',
				showHideTransition: 'plain',
				icon: 'warning',
				hideAfter: 5000,
				position: 'top-right'
			});
		}*/
		// Se vacian los campos
		//formTarea.find("[name='fechahorainicio']").val("");
		formTarea.find("[name='fechahorafin']").val("");
		formTarea.find("[name='horas']").val("")
		formTarea.find("[name='descripciontiempo']").val("");


		if (idHistoriaTarea != 0) {
			solicitudAjax("/Empleado/Tareas/historial?id=" + idHistoriaTarea, "POST", null, function (html) {
				formTarea.find(".cl-tarea-tiempo-historial").empty().append(html);
			});
		}


		$.LoadingOverlay("hide");
		modalBtnGuardar.removeAttr("disabled");
	});
}

function cargarDatosEnModal(datos) {
	modalTarea.find("[name='id']").val(datos.id);
	modalTarea.find("[name='codtareapadre']").val(datos.codtareapadre);
	modalTarea.find("[name='nombre']").val(datos.nombre);
	modalTarea.find("[name='codtareatipo']").val(datos.codtareatipo).change();
	modalTarea.find("[name='codempleadoasignado']").val(datos.codempleadoasignado);
	modalTarea.find("[name='codtareaestado']").val(datos.codtareaestado);
	modalTarea.find("[name='descripcion']").val(datos.descripcion);
	modalTarea.find("[name='codproyecto']").val(datos.codproyecto);
	modalTarea.find("[name='fechainiestimada']").val(formatDateStringToString(datos.fechainiestimada));
	modalTarea.find("[name='fechafinestimada']").val(formatDateStringToString(datos.fechafinestimada));
	modalTarea.find("[name='tiempoestimado']").val(datos.tiempoestimado);
	modalTarea.find("[name='fechainireal']").val(formatDateStringToString(datos.fechainireal));
	modalTarea.find("[name='fechafinreal']").val(formatDateStringToString(datos.fechafinreal));
	modalTarea.find("[name='tiemporeal']").val(datos.tiemporeal);
	modalTarea.find("[name='fechacompromiso']").val(datos.fechacompromiso);
	modalTarea.find("[name='fechasolicitud']").val(datos.fechasolicitud);
	modalTarea.find("[name='descripcionsolicitud']").val(datos.descripcionsolicitud);
	modalTarea.find("[name='eslogro']").prop("checked", datos.eslogro);

	// Se actualizan los selectpicker
	$('.cl-select-picker').selectpicker('refresh');
}

function cargarDatosGantt() {
	var _fechainicio = $(".cl-seccion-gantt #txt-fechainicio").val();
	var _fechafin = $(".cl-seccion-gantt #txt-fechafin").val();
	solicitudAjax("/api/gantt?modo=e", "GET", { vista: "", vistafiltro: "", fechainicio: _fechainicio, fechafin: _fechafin }, function (data) {
		gantt.clearAll();
		var markerId = gantt.addMarker({ start_date: new Date(), css: "today", text: "Hoy", title: "Hoy" });
		gantt.getMarker(markerId);
		gantt.parse(data, "json");
	});
	$.LoadingOverlay("hide");
}

function eliminarEmpleadoControl(codempleadocontrol) {
	$("#cl-tarea-modal").modal('hide');
	Swal.fire({
		title: '¿Estas seguro?',
		text: `¿Está seguro de eliminar el registro?`,
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#d33',
		cancelButtonColor: '#17a2b8',
		confirmButtonText: 'Si, Eliminar!',
		cancelButtonText: 'No, cancelar'
	}).then((result) => {
		if (result.isConfirmed) {
			Swal.fire({
				title: 'Petición enviada!',
				html: 'Esperando respuesta <b></b>.',
				timer: 2000,
				timerProgressBar: true,
				didOpen: () => {
					Swal.showLoading()
					timerInterval = setInterval(() => {
						const content = Swal.getHtmlContainer()
						if (content) {
							const b = content.querySelector('b')
							if (b) {

								b.textContent = Swal.getTimerLeft()
							}
						}
					}, 100)
				},
				willClose: () => {
					solicitudAjax("/Empleadocontrol/Eliminar", "POST", { id: codempleadocontrol }, function (data) {
						if (data > 0) {
							$(".cl-tarea-tiempo-historial #tarea-historial-" + codempleadocontrol).slideUp("slow");
							Swal.fire({
								title: "Eliminado!",
								text: "Registro eliminado con éxito.",
								type: "success",
								timer: 2000
							});
						} else {
							Swal.fire({
								icon: 'error',
								title: 'Oops...',
								text: 'No fue posible eliminar el registro',
								timer: 2000
							});
						}
						$.LoadingOverlay("hide");
						setTimeout(function () { $("#cl-tarea-modal").modal('show'); }, 2000);
					});
				}
			})
		} else if (result.isDismissed) {
			$("#cl-tarea-modal").modal('show');
		}
	});
}


