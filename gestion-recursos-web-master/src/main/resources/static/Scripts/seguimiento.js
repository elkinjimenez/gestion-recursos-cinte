function cargarListaSecundariaSeguimiento(tipoPresentacion) {
	if (tipoPresentacion === "")
		return false;
	
	var ddlSubTipo = $("#ddl-seguimiento-subtipo");
	
	if (tipoPresentacion === "PROYECTO") {
		solicitudAjax("/api/proyecto", "POST", null, function(data) {
		    $.LoadingOverlay("hide");
			ddlSubTipo.empty();
			$(data).each(function(i) {
				if (i === 0)
					ddlSubTipo.append('<option selected value="0">Seleccione un proyecto...</option>');
				
				ddlSubTipo.append('<option value="' + this.id + '">' + this.codigoproyecto + " - " + this.nombre + '</option>');
			}).promise().done(function() {
				ddlSubTipo.selectpicker('refresh');
			});
		});
	} else if (tipoPresentacion === "PROVEEDOR") {
		solicitudAjax("/api/proveedor", "POST", null, function(data) {
		    $.LoadingOverlay("hide");
			ddlSubTipo.empty();
			$(data).each(function(i) {
				if (i === 0)
					ddlSubTipo.append('<option selected value="0">Seleccione un proveedor...</option>');
				
				ddlSubTipo.append('<option value="' + this.id + '">' + this.nombre + '</option>');
			}).promise().done(function() {
				ddlSubTipo.selectpicker('refresh');
			});
		});
	} else if (tipoPresentacion === "RECURSO") {
		solicitudAjax("/api/empleado", "POST", { vu: "vu" }, function(data) {
		    $.LoadingOverlay("hide");
			ddlSubTipo.empty();
			$(data).each(function(i) {
				if (i === 0)
					ddlSubTipo.append('<option selected value="0">Seleccione un recurso...</option>');
				
				ddlSubTipo.append('<option value="' + this.codpersona + '">' + this.proveedor + ' - ' + this.nombre + '</option>');
			}).promise().done(function() {
				ddlSubTipo.selectpicker('refresh');
			});
		});
	}
	
}