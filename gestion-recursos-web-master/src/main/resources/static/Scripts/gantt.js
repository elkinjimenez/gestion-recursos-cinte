var formTarea = null;
var ddlNuevaTareaProyecto = null;
var modalTarea = null;
var modalBtnGuardar = null;
var modalBtnEliminar = null;
var modalTareaAnterior = null;

document.addEventListener("DOMContentLoaded", function (event) {

    var zoomConfig = {
        levels: [
            {
                name: "day",
                scale_height: 27,
                min_column_width: 80,
                scales: [
                    {unit: "day", step: 1, format: "%d %M"}
                ]
            },
            {
                name: "week",
                scale_height: 50,
                min_column_width: 50,
                scales: [
                    {unit: "week", step: 1, format: function (date) {
                            var dateToStr = gantt.date.date_to_str("%d %M");
                            var endDate = gantt.date.add(date, -6, "day");
                            var weekNum = gantt.date.date_to_str("%W")(date);
                            return "#" + weekNum + ", " + dateToStr(date) + " - " + dateToStr(endDate);
                        }},
                    {unit: "day", step: 1, format: "%j %D"}
                ]
            },
            {
                name: "month",
                scale_height: 50,
                min_column_width: 120,
                scales: [
                    {unit: "month", format: "%F, %Y"},
                    {unit: "week", format: "Week #%W"}
                ]
            },
            {
                name: "quarter",
                height: 50,
                min_column_width: 90,
                scales: [
                    {unit: "month", step: 1, format: "%M"},
                    {
                        unit: "quarter", step: 1, format: function (date) {
                            var dateToStr = gantt.date.date_to_str("%M");
                            var endDate = gantt.date.add(gantt.date.add(date, 3, "month"), -1, "day");
                            return dateToStr(date) + " - " + dateToStr(endDate);
                        }
                    }
                ]},
            {
                name: "year",
                scale_height: 50,
                min_column_width: 30,
                scales: [
                    {unit: "year", step: 1, format: "%Y"}
                ]}
        ]
    };

    gantt.ext.zoom.init(zoomConfig);


    // specifying the date format
    gantt.config.api_date = "%d/%m/%Y %H:%i";
    gantt.config.date_format = "%d/%m/%Y %H:%i";
    gantt.config.load_date = "%d/%m/%Y";
    gantt.config.columns = [
        {name: "text", label: "Tarea", tree: true, width: "200"},
        {name: "duration", label: "Duración", align: "center", width: "60"},
        {name: "start_date", label: "Inicio", align: "center", width: "80"},
        {name: "end_date", label: "Fin", align: "center", width: "80"}//,
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
    gantt.templates.timeline_cell_class = function (task, date) {
        if (date.getDay() == 0 || date.getDay() == 6) {
            return "weekend";
        }
    };
    // Se cargan los días festivos
    var holidays = [
        new Date(2019, 0, 1),
        new Date(2019, 0, 21),
        new Date(2019, 3, 16),
        new Date(2019, 4, 12),
        new Date(2019, 4, 27),
        new Date(2019, 5, 16),
        new Date(2019, 6, 4),
        new Date(2019, 8, 2),
        new Date(2019, 9, 14),
        new Date(2019, 10, 28),
        new Date(2019, 11, 25)
    ];

    for (var i = 0; i < holidays.length; i++) {
        gantt.setWorkTime({
            date: holidays[i],
            hours: false
        });
    }

    gantt.attachEvent("onLoadStart", function () {
        $.LoadingOverlay("show");
    });

    gantt.attachEvent("onLoadEnd", function () {
        $.LoadingOverlay("hide");
    });

    // initializing gantt
    gantt.init("cl-gantt");

    // initiating data loading
    gantt.load("/api/gantt");

    // initializing dataProcessor
    var dp = new gantt.dataProcessor("/api/gantt");
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
});

function setScaleConfig(level) {
    switch (level) {
        case "day":
            gantt.config.scales = [
                {unit: "day", step: 1, format: "%d %M"}
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
                {unit: "week", step: 1, format: weekScaleTemplate},
                {unit: "day", step: 1, format: "%D"}
            ];
            gantt.config.scale_height = 50;
            break;
        case "month":
            gantt.config.scales = [
                {unit: "month", step: 1, format: "%F, %Y"},
                {unit: "day", step: 1, format: "%j, %D"}
            ];
            gantt.config.scale_height = 50;
            break;
        case "year":
            gantt.config.scales = [
                {unit: "year", step: 1, format: "%Y"},
                {unit: "month", step: 1, format: "%M"}
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
    modalBtnEliminar = $("#btn-tarea-eliminar");
    modalTarea.on('hidden.bs.modal', function (e) {
        var btnCrearTarea = $("#btn-tarea-crear-tab");
        modalBtnGuardar.removeAttr("disabled");
        if (btnCrearTarea.attr("data-modo") === "crear")
            btnCrearTarea.click();
    });

    // Para no generar confusión, cuando es un empleado no administrador se bloquean los campos de la tarea que no puede modificar
    modalTarea.find("#tarea-tiempo-panel").find("input,select,textarea").prop("disabled", true);

    // Filtros del GANTT
    $("#ddl-seguimiento-tipo").change(function () {
        cargarListaSecundariaSeguimiento($(this).val());
    });
    $("#ddl-seguimiento-subtipo").change(function () {
        cargarDatosGantt();
    });
    $(".cl-seccion-gantt").find("#txt-fechainicio, #txt-fechafin").change(function () {
        var el = $(this);
        if ((el.attr("id") === "txt-fechainicio" && el.val() !== $("#hd-fechainicio").val()) ||
                (el.attr("id") === "txt-fechafin" && el.val() !== $("#hd-fechafin").val())) {
            $("#hd-fechainicio").val($("#txt-fechainicio").val());
            $("#hd-fechafin").val($("#txt-fechafin").val());
            cargarDatosGantt();
        }
    });

    // Evento de botón para crear nueva tarea en el Modal
    $("#btn-tarea-crear-tab").click(function () {
        var btnCrearTarea = $(this);
        if (btnCrearTarea.attr("data-modo") === "crear") {
            btnCrearTarea.removeAttr("data-modo");
            btnCrearTarea.find("span").text("Crear tarea");
            btnCrearTarea.find(".ico-crear").show().end().find(".ico-regresar").hide();

            // Si la tarea anterior es una tarea se bloquea el botón de guardar
            if (modalTareaAnterior.id < 0)
                modalBtnGuardar.prop("disabled", true);

            cargarDatosEnModal(modalTareaAnterior);
        } else {
            btnCrearTarea.attr("data-modo", "crear");
            btnCrearTarea.find("span").text("Cancelar");
            btnCrearTarea.find(".ico-crear").hide().end().find(".ico-regresar").show();
            modalBtnGuardar.removeAttr("disabled");

            // Se guarda la información de la tarea abierta en el modal
            modalTareaAnterior = $(this).parents("#cl-form-tarea").serializeObject();
            // Se borra la información del modal
            cargarDatosEnModal({
                codtareapadre: (modalTareaAnterior.id < 0 ? null : modalTareaAnterior.id),
                codproyecto: modalTareaAnterior.codproyecto,
                codtareaestado: 1 // pendiente
            });
        }
    });

    // Evento de botón de eliminar tarea
    modalBtnEliminar.click(function () {
        var codtareaeliminar = $("#cl-form-tarea").serializeObject().id;
        if (codtareaeliminar && codtareaeliminar != "") {
            if (codtareaeliminar < 0) {
                alert("La tarea no se puede eliminar porque es un proyecto.");
            } else if (codtareaeliminar > 0) {
                if (confirm("¿Está seguro de eliminar esta tarea? (Sí tiene tareas hijas también se borraran)")) {
                    solicitudAjax("/Tareas/Eliminar", "POST", {id: codtareaeliminar}, function (data) {
                        if (data > 0) {
                            alert("La tarea se eliminó correctamente.");
                            $("#ddl-seguimiento-subtipo").change();
                            modalTarea.modal("hide");
                        } else {
                            alert("No fue posible eliminar la tarea.");
                        }
                        $.LoadingOverlay("hide");
                    });
                }
            }
        }
    });

    cargarEventosTareas();
});

function cargarEventosGantt() {
    gantt.showLightbox = function (id) {
        // Sí se abre un proyecto se carga como si fuera una tarea para habilitar la funcionalidad de agregar tareas hijas
        if (id < 0) {
            solicitudAjax("/api/proyecto/" + (id * -1), "POST", null, function (data) {
                $.LoadingOverlay("hide");
                var idproyecto = id;
                let tarea = {
                    id: idproyecto,
                    codtareaestado: 1, // Pendiente
                    nombre: data.nombre,
                    descripcion: data.descripcion,
                    codproyecto: data.id,
                    fechainiestimada: data.fechainicio,
                    fechafinestimada: data.fechafin
                };
                modalBtnGuardar.prop("disabled", true);
                cargarDatosEnModal(tarea);
                modalTarea.find("#tarea-info-tab").tab("show");
                modalTarea.modal("show");
            });
        } else {
            solicitudAjax("/api/tarea/" + id, "POST", null, function (data) {
                $("#fechahorainicio").val(fechaActual());
                cargarDatosEnModal(data);
                modalTarea.modal("show");
                modalTarea.find("#tarea-info-tab").tab("show");
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

    formTarea.submit(function (e) {
        e.preventDefault();


//AJUSTE PROVISIONAL, VALIDAR PORQUE DEVUELVE EL codempleadoasignado como array		
        var formObject = formTarea.serializeObject();
        var codempleadoasignado = formObject.codempleadoasignado[0];
        var regex = "\"codempleadoasignado\":[\"" + codempleadoasignado + "\",\"" + codempleadoasignado + "\"]";
        var formReplace = JSON.stringify(formObject).replace(regex, "\"codempleadoasignado\":" + codempleadoasignado + "");

        if (formTarea.valid()) {
            solicitudAjax("/Tareas", "POST", JSON.parse(formReplace), function (data) {
                modalTarea.modal("hide");
                // Se vacían los campos
                cargarDatosEnModal({});
                // Se agrega la tarea al gantt
                solicitudAjax("/api/tareaactividad/" + data.id, "POST", {vu: "vu"}, function (data2) {
                    $.LoadingOverlay("hide");
                    gantt.parse({data: [data2]}, "json");
                });
                $.LoadingOverlay("hide");
            });
        }

        return false;
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
    modalTarea.find("[name='eslogro']").prop("checked", datos.eslogro);

    // Se actualizan los selectpicker
    $('.cl-select-picker').selectpicker('refresh');
}

function cargarDatosGantt() {
    var _vista = $(".cl-seccion-gantt #ddl-seguimiento-tipo").val();
    var _vistafiltro = $(".cl-seccion-gantt #ddl-seguimiento-subtipo").val();
    var _fechainicio = $(".cl-seccion-gantt #txt-fechainicio").val();
    var _fechafin = $(".cl-seccion-gantt #txt-fechafin").val();
    solicitudAjax("/api/gantt", "GET", {vista: _vista, vistafiltro: _vistafiltro, fechainicio: _fechainicio, fechafin: _fechafin}, function (data) {
        $.LoadingOverlay("hide");
        gantt.clearAll();
        var markerId = gantt.addMarker({start_date: new Date(), css: "today", text: "Hoy", title: "Hoy"});
        gantt.getMarker(markerId);
        gantt.parse(data);
    });
}

function eliminarEmpleadoControl(codempleadocontrol) {
    alert("Acción no permitida. La eliminación de registro de tiempo sólo puede ser realizada desde el menú de 'Actividades'.");
}