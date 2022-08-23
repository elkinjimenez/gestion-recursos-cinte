$(function() {
	
	/* "Autocomplete" */
	$.fn.selectpicker.Constructor.BootstrapVersion = '4';
	
	/**
	 * Menú principal
	 */
	var clm = obtenerParametroUrl("clm"); // Parámetro estándar para menú activo
	if (!clm)
		clm = "/"; // Por defecto menú de inicio
	$(".cl-menu a[href$='" + clm + "']")
		.addClass("cl-menu-activo")
		.parents(".cl-menu-group")
			.addClass("show").each(function() {
				var element = $(this);
				$(".cl-menu").find("a[href='#" + element.attr("id") + "']").addClass("cl-menu-activo");
			});
	clTablasBotonConsultarMantenerMenu();
	
	// Botón de expandir y contraer menú principal
	$(".cl-btn-menu-abrir-cerrar").click(function() {
		if ($(".cl-menu-lateral").hasClass("cl-menu-cerrado")) {
			$(".cl-menu-lateral,.cl-contenedor-principal").removeClass("cl-menu-cerrado");
			$(this).find(".fa-angle-double-left").show();
			$(this).find(".fa-angle-double-right").hide();
		} else {
			$(".cl-menu-lateral,.cl-contenedor-principal").addClass("cl-menu-cerrado");
			$(this).find(".fa-angle-double-left").hide();
			$(this).find(".fa-angle-double-right").show();
		} 
	});
	
	
	/**
	 * Se activa validación de formularios
	 */
	$("form.validate").validate({
		errorPlacement: function ( error, element ) {	
	        if(element.parent().hasClass('input-group') || element.hasClass('cl-select-picker')){
	          error.insertAfter( element.parent() );
	        } else {
	          	error.insertAfter( element );
	        }	
		}
	});
	// Mantener el parámetro del menú a través de los formularios
	clFormMantenerMenu();
	
	jQuery.validator.addMethod("notEqual", function (value, element, param) {
	    return this.optional(element) || value != '0';
	});
	
	/**
	 * Se implementan los DatePicker
	 */
	$("input.cl-date-picker").each(function() {
		$(this).datepicker({
			locale: 'es-es',
	        uiLibrary: 'bootstrap4',
	        format: 'dd/mm/yyyy'
	    });
	});
	$("input.cl-datetime-picker").each(function() {
		$(this).datetimepicker({
			locale: 'es-es',
	        uiLibrary: 'bootstrap4',
	        format: 'dd/mm/yyyy HH:MM',
			// mode: 'ampm',
			footer: true,
			modal: true
	    });
	});
		
	/**
	 * Miga de pan
	 */
	$(".cl-menu a.cl-menu-activo").each(function() {
		$("ol.breadcrumb").append('<li class="breadcrumb-item">' + $(this).find("span").text() + '</li>');
	});
	
	/*  
	 * Autocompletar
	 * */
	$(".cl-select-picker").selectpicker();

	
	
	
	
	/*
	 * Función para serializar formularios para enviar via ajax 
	 * */
	$.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
	
});

/*
	Función encargada de preguntar si desea elimar un registro
	@param ruta recibe la url a la cual redirecciona al confirmar
 */
function confirmarEliminarSweetAlert(ruta) {
	Swal.fire({
		title: '¿Estas seguro?',
		text: `¿Está seguro de eliminar esta información?`,
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
					$(location).attr('href',ruta);
				}
			})

		}
	});
}

function confirmarEliminar() {
	/*
	 * Botones de eliminar con pregunta de confirmación
	 * */
	if (confirm("¿Está seguro de eliminar esta información?"))
		return true;
	else
		return false;
}

function obtenerParametroUrl(name){
   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
      return decodeURIComponent(name[1]);
}

function clTablasBotonConsultarMantenerMenu() {
	var clm = obtenerParametroUrl("clm"); // Parámetro estándar para menú activo
	// Se agrega el parámetro clm a los link "consultar" que se encuentran en las rejillas de resultados
	if (clm) {
		$(".cl-tabla tbody tr td:first-child a").each(function() {
			if ($(this).attr("href").indexOf("clm=") < 0) {
				$(this).attr("href", $(this).attr("href") + ($(this).attr("href").indexOf("?") >= 0 ? "&" : "?") + "clm=" + clm);
			}
		});
	}
}

function clFormMantenerMenu() {
	var clm = obtenerParametroUrl("clm"); // Parámetro estándar para menú activo
	// Se agrega el parámetro clm a los formularios
	if (clm) {
		$("form.validate").each(function() {
			if ($(this).attr("action").indexOf("clm=") < 0) {
				$(this).attr("action", $(this).attr("action") + ($(this).attr("action").indexOf("?") >= 0 ? "&" : "?") + "clm=" + clm);
			}
		});
	}
}

function solicitudAjax(_url, _method, _params, _fdone, _ferror) {
	if (!_fdone) {
		_fdone = function (data) {

		};
	}
	
	if (!_ferror) {
		_ferror = function (err, er, e) {
			
		};
	}
	
	$.ajax({
		url: _url,
		method: _method,
		data: _params,
			beforeSend: function() {
				$.LoadingOverlay("show");
			 },
	}).done( _fdone )
	.fail( function( jqXHR, textStatus, errorThrown ) {
	    if(jqXHR.status != 200 || jqXHR.status != 201) {
	        var res = jQuery.parseJSON( jqXHR.responseText);
	        $.LoadingOverlay("hide");
        	$.toast({
                heading: 'Error',
                text: res.mensaje,
                showHideTransition: 'fade',
                icon: 'error',
                hideAfter: 5000,
                position: 'top-right'
            })
	    }

	})
}


function formatDateStringToString(fecha) {
	if (fecha) {
		// Si ya tiene el formato correcto no se hace nada
		if (fecha.split("/").length == 3 && fecha.split("/")[0].length == 2 && fecha.split("/")[1].length == 2 && fecha.split("/")[2].length == 4)
			return fecha;
		return fecha.substring(8, 10) + "/" + fecha.substring(5, 7) + "/" + fecha.substring(0, 4);
	} else 
		return "";
}

function fechaActual(){
	var fecha = new Date();
	var diaBase = fecha.getDate().toString();
	var mesBase = (fecha.getMonth()+1).toString();
	var dia = diaBase.length==1?'0'+diaBase:diaBase;
	var mes = mesBase.length==1?'0'+mesBase:mesBase;
	var anio = fecha.getFullYear();
    var fechaFormateada  = dia+'/'+mes+'/'+anio;
    return fechaFormateada;
    
}
