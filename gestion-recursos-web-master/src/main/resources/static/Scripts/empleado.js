let path = location.pathname;
let patternPath = new RegExp('\/Crear');
let estructuraOrganizacional = document.querySelector("select[id='codestructuraorganizacional']")
let gerenciaSelect = document.getElementById('gerencias');
const patternEstructura = /(gerent|coordinac)/g
$(function () {

    let diaactual = new Date();
    diaactual = dayjs(diaactual);

    $("form .ddl-persona").change(function () {
        var elemento = $(this);
        cargarDatosBasicos();

        if (location.pathname.match('/Editar/') != null) {
            $.ajax('/api/empleado/activo/codpersona/' + elemento.val(), {
                method: 'POST',
                beforeSend: function () {
                    $.LoadingOverlay("show");
                }

            }).done(function (data) {
                let codempleado = data.id
                cargarDatosCostos(codempleado);
                cargarDatosEstructura(data.codestructuraorganizacional);
                $.LoadingOverlay("hide");
                
            }).fail(function (jqXHR, textStatus) {
                $.LoadingOverlay("hide");
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'No es un Empleado Activo'
                });
            });
        }

        function cargarDatosBasicos() {
            solicitudAjax("/api/persona/" + elemento.val(), "POST", null, function (data) {
                $("form [name='codtipodocumento']").val(data.codtipodocumento);
                $("form [name='numerodocumento']").val(data.numerodocumento);
                $("form [name='nombre']").val(data.nombre1 + ' ' + data.nombre2 + ' ' + data.apellido1 + ' ' + data.apellido2);
                $("form [name='telefono']").val(data.telefono);
                $("form [name='correo']").val(data.correo);
                $("form [name='genero']").val(data.genero);
                $("form [name='fechanacimiento']").val(data.fechanacimiento && data.fechanacimiento.length >= 10 ? data.fechanacimiento.substring(0, 10) : '');

                $.LoadingOverlay("hide");

            }, function (err, er, e) {
                $.LoadingOverlay("hide");
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Error no Hay datos de Esta Persona'
                });
            });
        }



        function cargarDatosCostos(codempleado) {

            solicitudAjax("/api/costoempleado/codEmpleado/" + codempleado, "POST", null, function (data) {

                if (data != null && data.length > 0) {
                    let firstResult = data[0]
                    $("form [name='costoPunto']").val(firstResult.costoPunto);
                    $("form [name='costoMes']").val(firstResult.costoMes);
                    $("form [name='puntoMes']").val(firstResult.puntosMes);
                    $("form [name='factorPunto']").val(firstResult.factorPunto);
                    $("form [name='modalidad']").val(firstResult.modalidad);
                    $("form [id='desde']").val(dayjs(firstResult.desde).format('DD/MM/YYYY'));
                    $.LoadingOverlay("hide");
                } else {
                    $("form [name='costoPunto']").val(0);
                    $("form [name='costoMes']").val(0);
                    $("form [name='puntoMes']").val(180);
                    $("form [name='factorPunto']").val(1.0);
                    $("form [name='modalidad']").val('SERVICIO');
                    $("form [id='desde']").val(diaactual.format('DD/MM/YYYY'));
                    $.LoadingOverlay("hide");
                }

            }, function (err, er, e) {
                $.LoadingOverlay("hide");
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'No Hay Datos Costos para este Empleado'
                });
            })
        }

    });
    var hasta = $("#hasta").val();
    if (hasta.length > 0) {

        splitHasta = hasta.split("/");
        var fecha = new Date(splitHasta[2] + "/" + splitHasta[1] + "/" + splitHasta[0]);
        fecha.setDate(fecha.getDate() + 1);

        $('#desde').datepicker('destroy');
        $('#desde').datepicker({
            locale: 'es-es',
            uiLibrary: 'bootstrap4',
            format: 'dd/mm/yyyy',
            startDate: fecha,
            setDate: fecha,
            minDate: fecha
        })
    }
});


if (patternPath.test(path)) {
    document.getElementById('div-costo-empleado').classList.add('d-none')
    $("#crearCostoEmpleado").prop('checked', true);
    costoEmpleado();
} else {
    $("#crearCostoEmpleado").prop('checked', false);
    costoEmpleado();
}

function cargarDatoGerencia(codGerencia){
    $.ajax('/api/estructuraorganizacional/getGerenteporCodigoEstructura/' + codGerencia, {
        method: 'POST',
        beforeSend: function () {
            $.LoadingOverlay("show");
        }

    }).done(function (data) {
        if (data != "" ) {
            $("form [name='codestructuraorganizacional']").val(data.estructuraNombre);
        } else {
            
        }
        $.LoadingOverlay("hide");
    }).fail(function (jqXHR, textStatus) {
        $.LoadingOverlay("hide");
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Error ' + textStatus
        });
    });
}
function cargarDatosEstructura(codEstructura){
    $.ajax('/api/EmpleadoEstructura/getEmpleadoByActivosByEstructura/' + codEstructura, {
        method: 'POST',
        beforeSend: function () {
            $.LoadingOverlay("show");
        }

    }).done(function (data) {
        if (data != "" ) {
            $("form [name='codestructuraorganizacional']").val(data.estructuraNombre);
        } else {
            
        }
        $.LoadingOverlay("hide");
    }).fail(function (jqXHR, textStatus) {
        $.LoadingOverlay("hide");
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Error ' + textStatus
        });
    });
}


function validarEstructuraOrganizacion() {

    let item = estructuraOrganizacional.options[estructuraOrganizacional.selectedIndex]
    let tipoestructura = item.dataset.codestructura
    let codestructuraorganizacional = item.value
    let form = document.getElementById('formEmpleado').getElementsByTagName('input')
    let codEstructuraOriginal = form['codestructuraorganizacional'].value

    $('#success-message').css('display', 'none');
    if ((tipoestructura == 1 || tipoestructura == 2)) {
        $.ajax('/api/EmpleadoEstructura/getEmpleadoByActivosByEstructura/' + codestructuraorganizacional, {
            method: 'POST',
            beforeSend: function () {
                $.LoadingOverlay("show");
            }

        }).done(function (data) {
            if (data != "" && (codEstructuraOriginal != codestructuraorganizacional)) {
                Swal.fire({
                    icon: 'info',
                    title: 'Validacion Formulario',
                    text: `La estructura organizacional  ${data.estructuraNombre}  no se puede asignar, ya que ${data.nombre}  ${data.apellido} se encuentra encargado de esta estructura  `
                });
            } else {
                $('#formEmpleado').submit();
            }
            $.LoadingOverlay("hide");
        }).fail(function (jqXHR, textStatus) {
            $.LoadingOverlay("hide");
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Error ' + textStatus
            });
        });
    } else {
        $('#formEmpleado').submit();
    }
}



function costoEmpleado() {
    var crearCostoEmpleado = $("#crearCostoEmpleado").is(':checked');
    $(" .costoEmpleado ").hide();
    if (crearCostoEmpleado) {
        let fechacosto = document.getElementById('desde')
        fechacosto.setAttribute('required', true)
        $(".costoEmpleado ").show();
    }
}
gerenciaSelect.addEventListener('change', (e) => {
    let opcionseleccionada = gerenciaSelect.options[gerenciaSelect.selectedIndex]
    let codGerente = opcionseleccionada.value;
    cargarEstructuraxGerencia(codGerente)
    console.log('se consultaron las estructuras de acuerdo con el gerente ')

})

function cargarEstructuraxGerencia(codGerencia) {
    url = '/api/estructuraorganizacional/getEstructuraXGerencia/' + codGerencia
    $("#codestructuraorganizacional").find('option').remove().end()
    let opcSeleccionar = document.createElement('option')
    opcSeleccionar.text = 'Seleccione una opcion'
    opcSeleccionar.value = 0
    estructuraOrganizacional.appendChild(opcSeleccionar)
    $("#codestructuraorganizacional").selectpicker("refresh");
    solicitudAjax(url, 'POST', null, function (data) {
        if (data != null) {
            Array.prototype.forEach.call(data, function (elemento, indice) {
                let opcion = document.createElement('option')
                opcion.value = elemento.id
                opcion.text = elemento.nombre
                opcion.dataset.codempleado = elemento.codempleado_responsable
                opcion.dataset.codestructura=elemento.codestructuratipo
                estructuraOrganizacional.appendChild(opcion)
                $("#codestructuraorganizacional").selectpicker("refresh");
            })
            $.LoadingOverlay("hide");
        }
    }, function (err, er, error) {
        console.error(err)
        $.LoadingOverlay("hide");
    })
}


