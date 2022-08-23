var formTarea = null; 

var btnTareaAgregar = null;
var pnlTareaLista = null;
var ddlTareaListaProyectos = null;
var ddlNuevaTareaProyecto = null;

var btnTareaListaItem = null;

var modalTarea = null;

$(function() {
	formTarea = $("form#cl-form-tarea");
	ddlTareaListaProyectos = $(".cl-seccion-tarea select.ddl-tarea-proyecto");
	btnTareaAgregar = $(".cl-seccion-tarea button.btn-tarea-agregar");
	pnlTareaLista = $(".cl-seccion-tarea .cl-contenedor-tareas");
	ddlNuevaTareaProyecto = $(".pnl-nueva-tarea select[name='codproyecto']");
	
	modalTarea = $("#cl-tarea-modal");
	
	// Para no generar confusión, cuando es un empleado no administrador se bloquean los campos de la tarea que no puede modificar
	modalTarea.find("#tarea-tiempo-panel").find("input,select,textarea").prop("disabled", true);
	
	cargarEventosTareas();
});

function cargarEventosTareas() {
	
	// Botón crear tarea
	btnTareaAgregar.unbind("click").click(function() {	
		debugger
		cargarDatosEnModal({ codproyecto: ddlTareaListaProyectos.val() });
		modalTarea.modal("show");
	});
	
	// Lista de proyectos
	ddlTareaListaProyectos.unbind("change").change(function() {
		var elemento = $(this);
		if (elemento.val() !== "0") {
			// Se activa botón para agregar nuevas tareas
			btnTareaAgregar.removeClass("btn-secondary").removeAttr("disabled").addClass("btn-success");
			cargarTareasPorProyecto();
		} else {
			// Se inactiva botón para agregar nuevas tareas
			btnTareaAgregar.removeClass("btn-success").prop("disabled", true).addClass("btn-secondary");
			// Se limpian las tareas cargadas
			pnlTareaLista.empty();
		}
	});
	
	// Tarea tipo: Aplicar el color correspondiente al tipo de tarea
	$(".cl-seccion-tarea .cl-tarea-item select[name='codtareatipo']").unbind("change").change(function() {
		$(this).parents(".cl-tarea-item").css("background-color", $(this).find(":selected").attr("data-color"));
	});
	
	formTarea.submit(function(e) {
		e.preventDefault();
		
		
		if (formTarea.valid()) {
			solicitudAjax("/Tareas", "POST", formTarea.serializeObject(), function(data) {
			    $.LoadingOverlay("hide");
				modalTarea.modal("hide");

				// Se vacían los campos
				cargarDatosEnModal({});
				// Se vuelve a cagar la lista de tareas
				cargarTareasPorProyecto();
			});
		}
		
		return false;
	});
}

function cargarEventosTareasLista() {
	btnTareaListaItem = $(".cl-tarea-list-item .btn-tarea-expandir");
	btnTareaListaItem.unbind("click").click(function() {
		var el = $(this).parents(".cl-tarea-list-item").find("form");	
		
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

function cargarTareasPorProyecto() {
	// Se cargan las tareas correspondientes al proyecto seleccionado
	solicitudAjax("/Tareas/lista?codproyecto=" + ddlTareaListaProyectos.val(), "POST", null, function(html) {
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
	modalTarea.find("[name='fechacompromiso']").val(datos.fechacompromiso);
    modalTarea.find("[name='fechasolicitud']").val(datos.fechasolicitud);
    modalTarea.find("[name='descripcionsolicitud']").val(datos.descripcionsolicitud);
	modalTarea.find("[name='eslogro']").prop("checked", datos.eslogro);
	
	// Se actualizan los selectpicker
	$('.cl-select-picker').selectpicker('refresh');
}