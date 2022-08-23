var formTarea = null; 

var pnlTareaLista = null;
var ddlTareaListaProyectos = null;
var ddlNuevaTareaProyecto = null;

var btnTareaListaItem = null;

var modalTarea = null;

$(function() {
	formTarea = $("form#cl-form-tarea");
	ddlTareaListaProyectos = $(".cl-seccion-tarea select.ddl-tarea-proyecto");
	pnlTareaLista = $(".cl-seccion-tarea .cl-contenedor-tareas");
	ddlNuevaTareaProyecto = $(".pnl-nueva-tarea select[name='codproyecto']");
	
	modalTarea = $("#cl-tarea-modal");
	
	// Para no generar confusión, cuando es un empleado no administrador se bloquean los campos de la tarea que no puede modificar
	modalTarea.find("#tarea-info-panel, #tarea-estimacion-panel").find("input,select,textarea").prop("disabled", true);
	// Sólo se deja activo el campo de estado
	modalTarea.find("#tarea-info-panel select[name='codtareaestado']").removeAttr("disabled");
	
	cargarEventosTareas();
});

function cargarEventosTareas() {
	
	// Lista de proyectos
	ddlTareaListaProyectos.unbind("change").change(function() {
		var elemento = $(this);
		if (elemento.val() !== "0") {
			cargarTareasFiltro();
		} else {
			// Se limpian las tareas cargadas
			pnlTareaLista.empty();
		}
	});
	
	// Tarea tipo: Aplicar el color correspondiente al tipo de tarea
	$(".cl-seccion-tarea .cl-tarea-item select[name='codtareatipo']").unbind("change").change(function() {
		$(this).parents(".cl-tarea-item").css("background-color", $(this).find(":selected").attr("data-color"));
	});
	
	// Desde la vista del empleado cuando cambia el estado de la tarea autómaticamente se actualiza en BD
	modalTarea.find(".cl-seccion-tarea .cl-tarea-item select[name='codtareaestado']").change(function() {
		solicitudAjax("/Tareas/CambiarEstado", "POST", formTarea.serializeObject(), function(data) {
		    $.LoadingOverlay("hide");
			alert("El estado se modificó correctamente.");			
		});
	});
	
	formTarea.submit(function(e) {
		e.preventDefault();
		
		if (formTarea.valid()) {
			solicitudAjax("/Empleado/Tareas", "POST", {
				codtarea: formTarea.find("[name='id']").val(),
				codempleado: formTarea.find("[name='codproyecto']").val(),
				fechahorainicio: formTarea.find("[name='fechahorainicio']").val(),
				fechahorafin: formTarea.find("[name='fechahorafin']").val(),
				horas: formTarea.find("[name='horas']").val(),
				descripcion: formTarea.find("[name='descripciontiempo']").val()
			}, function(data) {
				// Se vacian los campos
				formTarea.find("[name='fechahorainicio']").val("");
				formTarea.find("[name='fechahorafin']").val("");
				formTarea.find("[name='horas']").val(""),
				formTarea.find("[name='descripciontiempo']").val("");
				formTarea.find(".cl-tarea-tiempo-historial").empty();
				modalTarea.modal("hide");
                $.LoadingOverlay("hide");
				cargarTareasFiltro();
			});
		}
		
		return false;
	});
}

function cargarEventosTareasLista() {
	btnTareaListaItem = $(".cl-tarea-list-item .btn-tarea-expandir");
	btnTareaListaItem.unbind("click").click(function() {
		var el = $(this).parents(".cl-tarea-list-item").find("form");	
		// Se cargan los datos de la tarea en el modal
		let datosTarea = el.serializeObject();
		cargarDatosEnModal(datosTarea);
		// Se carga el historial de registro de tiempos de la tarea
		solicitudAjax("/Empleado/Tareas/historial?id=" + datosTarea.id, "POST", null, function(html) {
		    $.LoadingOverlay("hide");
			formTarea.find(".cl-tarea-tiempo-historial").empty().append(html);
		});
		
		modalTarea.modal("show");
	});
}

function cargarTareasFiltro() {
	// Se cargan las tareas correspondientes al proyecto seleccionado
	solicitudAjax("/Empleado/Tareas/lista?codproyecto=" + ddlTareaListaProyectos.val(), "POST", null, function(html) {
	    $.LoadingOverlay("hide");
		pnlTareaLista.empty().append(html);
		cargarEventosTareasLista();
	});
}

function cargarDatosEnModal(datos) {
	modalTarea.find("[name='id']").val(datos.id);
	modalTarea.find("[name='nombre']").val(datos.nombre);
	modalTarea.find("[name='codtareatipo']").val(datos.codtareatipo).change();
	modalTarea.find("[name='codempleadoasignado']").val(datos.codempleadoasignado);
	modalTarea.find("[name='codtareaestado']").val(datos.codtareaestado);
	modalTarea.find("[name='descripcion']").val(datos.descripcion);
	modalTarea.find("[name='codproyecto']").val(datos.codproyecto);
	modalTarea.find("[name='fechainiestimada']").val(datos.fechainiestimada);
	modalTarea.find("[name='fechafinestimada']").val(datos.fechafinestimada);
	modalTarea.find("[name='tiempoestimado']").val(datos.tiempoestimado);
	modalTarea.find("[name='fechainireal']").val(datos.fechainireal);
	modalTarea.find("[name='fechafinreal']").val(datos.fechafinreal);
	modalTarea.find("[name='tiemporeal']").val(datos.tiemporeal);
	modalTarea.find("[name='eslogro']").prop("checked", datos.eslogro);
	
	// Se actualizan los selectpicker
	$('.cl-select-picker').selectpicker('refresh');
}