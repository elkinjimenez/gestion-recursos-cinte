document.addEventListener("DOMContentLoaded", function(event) {
            scheduler.setLoadMode("month");            
            scheduler.config.api_date="%d/%m/%Y %H:%i";
            scheduler.config.date_format = "%d/%m/%Y %H:%i";
            scheduler.config.load_date = "%d/%m/%Y"
            // initializing scheduler
            scheduler.init("cl-calendario", new Date());
            // initiating data loading
            scheduler.load("/api/scheduler");
            // initializing dataProcessor
            var dp = new dataProcessor("/api/scheduler");
            // and attaching it to scheduler
            dp.init(scheduler);
            // setting the REST mode for dataProcessor
            dp.setTransactionMode("REST");            
        });


$(function() {
	$("#ddl-seguimiento-tipo").change(function() {
		cargarListaSecundariaSeguimiento($(this).val());
		solicitudAjax("/api/scheduler", "GET", { vista: $("#ddl-seguimiento-tipo").val(), vistafiltro: $("#ddl-seguimiento-subtipo").val() }, function(data) {
			scheduler.parse(data, "json");
			$.LoadingOverlay("hide");
		})
	});
});