var entregables = ['Estimación', 'Diseño', 'Servicio', 'Despliegue QA', 'Documentación Arquitectura',
    'Pruebas QA', 'Pruebas no funcionales', 'Validación de estandares y documentación', 'Sesión con Proyecto', "PaP's Producción", 'Otros'
];
var tr = '';
var contador = 0;
var codProyecto = 0;
var costo = 0;
var totalCosto = 0;
var href;
$("#Agregar").hide();
inicio();
proyecto();
total();
validarAccionInicial();

function inicio() {
    var url = $(location).attr('href');
    const myArr = url.split("Editar");
    if (myArr.length == 1) {
        href = "Filtro";
    } else {
        $("#btnCrear").text("Actualizar");
        href = "../Filtro";
    }
}

function validarAccionInicial() {
    if ($(" #contenido tr").length > 0) {
        $(" #Estimar ").val("Agregar Estimación");
        $(" #FormEstimar ").attr("onsubmit", "agregarContenido(); return false");
        codProyecto = $(" #proyecto").val();
        costo = ($(" #perfil option:selected ")[0].attributes.costo.value);
    }
}

function contenido() {
    codProyecto = $(" #proyecto").val();
    if (codProyecto == 0) {
        Swal.fire({
            title: 'Proyecto',
            text: `Debe seleccionar un proyecto`,
            icon: 'warning',
            confirmButtonColor: '#d33',
            confirmButtonText: 'Ok',
        });
        return false;
    }
    pintarTabla(10, null);
    colocarConsecutivoFechasInicioyFin();
    $(" #contenTable ").removeClass("d-none");
    tr = '';
    $(" #Estimar ").val("Agregar Estimación");
    $(" #FormEstimar ").attr("onsubmit", "agregarContenido(); return false");
    return false;
}

function eliminarTr(id) {
    $(".tr-" + id).remove();
    if ($(" #contenido tr").length == 0) {
        $(" #FormEstimar ").attr("onsubmit", "contenido(); return false");
        $(" #Estimar ").val("Estimar");
    }
    total();
}

function agregarContenido() {
    pintarTabla(1, "Otro");
}
/*  funcion para colocar en la estimacion el consecutivo de la fecha de inicio y fin de cada uno de los items de la estimacion */
function colocarConsecutivoFechasInicioyFin() {
    let diaactual = new Date();
    diaactual = dayjs(diaactual);
    let diaposterior;
    Array.prototype.forEach.call(document.getElementsByName('fechaInicio'), function (item, clave) {
        if (clave == 0) {
            item.value = diaactual.format('YYYY-MM-DD')
            diaposterior = diaactual.add(1, 'day');
            document.getElementsByName('fechaFin')[clave].value = diaposterior.format('YYYY-MM-DD')
            diaactual = diaposterior
        } else {
            diaactual = diaactual.add(1, 'day')
            diaposterior = diaactual.add(1, 'day')
            item.value = diaactual.format('YYYY-MM-DD')
            document.getElementsByName('fechaFin')[clave].value = diaposterior.format('YYYY-MM-DD')
            diaactual = diaposterior;
        }
    });
}

function pintarTabla(cantidadFor, entregable) {

    var cantidad = $('[ name="cantidad" ]').val();
    costo = ($(" #perfil option:selected ")[0].attributes.costo.value);
    var costoXCantidad = costo * cantidad;
    var tr = '';
    cantidadFor <= 0 ? cantidadFor = 1 : cantidadFor = cantidadFor;
    for (var i = 0; i < cantidadFor; i++) {
        contador = contador + i + 1;
        if (i != 2) {
            if (entregable == null) {
                tr += '<tr class="tr-' + contador + '">';
                tr += '<td> <input type="text" class="form-control" name="entregable" value="' + entregables[i] + '"> </td>';
            } else {
                tr += '<tr class="tr-' + contador + ' bg-warning">';
                tr += '<td> <input type="text" class="form-control" name="entregable" value="' + entregable + '"> </td>';
            }
            tr += '<td> <select class="form-control cl-select-picker selectdPerfil" name="perfil" data-live-search="true"></select> </td>';
            tr += '<td> <input type="text" onchange="objeto()" name="preRequisito" class="form-control"></td>';
            tr += '<td style="display: none;"> <input type="hidden" class="form-control" name="codProyecto" value="' + codProyecto + '"></td>';
            tr += '<td> <input type="number" class="form-control" name="cantidad" onchange="cantidadXcostoPerfil(' + contador + ', this)" value="' + cantidad + '"></td>';
            tr += '<td> <input type="date" onchange="objeto()" name="fechaInicio"  class="form-control cl-date-picker"></td>';
            tr += '<td> <input type="date" onchange="objeto()" name="fechaFin" class="form-control cl-date-picker"></td>';
            tr += '<td> <input type="hidden" name="costo" class="form-control" value="' + costoXCantidad + '">$ <spam>' + new Intl.NumberFormat('es-MX').format(costoXCantidad) + '</spam></td>';
            tr += '<td class="porcentaje"> <input type="hidden" name="porcentaje" class="form-control" value="0"><spam>0%</spam>' +
                '<input type="hidden" name="id" class="form-control"></td>';
            tr += '<td><a class="btn btn-danger" onclick="eliminarTr(' + contador + '), objeto()">Eliminar</a></td>';
            tr += '</tr>';
        } else {
            for (var x = 0; x < cantidad; x++) {
                tr += '<tr class="tr-' + contador + '' + x + '">';
                tr += '<td> <input type="text" class="form-control" name="entregable" value="' + entregables[2] + ' ' + (x + 1) + ' "> </td>';
                tr += '<td> <select class="form-control cl-select-picker selectdPerfil" name="perfil" data-live-search="true"></select> </td>';
                tr += '<td> <input type="text" onchange="objeto()" name="preRequisito" class="form-control"></td>';
                tr += '<td style="display: none;"> <input type="hidden" class="form-control" name="codProyecto" value="' + codProyecto + '"></td>';
                tr += '<td> <input type="number" class="form-control" name="cantidad" onchange="cantidadXcostoPerfil(' + contador + '' + x + ', this)" value="' + cantidad + '"></td>';
                tr += '<td> <input type="date" onchange="objeto()" name="fechaInicio"  class="form-control cl-date-picker"></td>';
                tr += '<td> <input type="date" onchange="objeto()" name="fechaFin" class="form-control cl-date-picker"></td>';
                tr += '<td> <input type="hidden" name="costo" class="form-control" value="' + costoXCantidad + '">$ <spam>' + new Intl.NumberFormat('es-MX').format(costoXCantidad) + '</spam></td>';
                tr += '<td class="porcentaje"> <input type="hidden" name="porcentaje" class="form-control" value="0"><spam>0%</spam>' +
                    '<input type="hidden" name="id" class="form-control"></td>';
                tr += '<td><a class="btn btn-danger" onclick="eliminarTr(' + contador + '' + x + '), objeto()">Eliminar</a></td>';
                tr += '</tr>';
            }
        }
    }

    $(" #contenido ").append(tr);
    tr = '';
    var options = '';
    $(" #perfil option").each(function () {
        selected = this.selected == true ? "selected" : "";
        options += '<option value="' + this.value + '" ' + selected + ' >' + this.text + '</option>';
    })
    $(' .selectdPerfil ').append(options);
    options = '';
    $(".selectdPerfil").removeClass("selectdPerfil");
    total();
}

function objeto() {
    var cantInput = $(" #contenido tr td [name]").length;
    var cantObjetos = $(" #contenido tr").length;
    var estimaciones = [];
    var estimacion = {};
    var llave = '';
    var valor = '';
    cantObjetos = (cantInput / cantObjetos);

    for (var i = 1; i <= cantInput; i++) {
        llave = $(" #contenido tr td [name]")[i - 1].name;
        valor = $(" #contenido tr td [name]")[i - 1].value;
        estimacion[llave] = valor;

        if (llave == "fechaFin" || llave == "fechaInicio" && valor.length > 5) {
            estimacion[llave] = new Date(valor.split('/')[2] + '/' + valor.split('/')[1] + '/' + valor.split('/')[0]);
        }
        if (i % (cantObjetos) == 0) {
            estimacion.urlsharepoint = String(document.getElementById('urlsharepoint').value).toString()
            estimaciones.push(estimacion);
            estimacion = {};
        }
    }
    
    
    $(" #estimaciones ").val(JSON.stringify(estimaciones));
}

function guardarEstimacion() {
    //$("#btnCrear").addClass("disabled");
    if (!validarUrlSharepoint()) {
        Swal.fire(
            'Informacion',
            'Debe proporcionar una cadena valida para la url del sharepoint',
            'info'
        );
        return
    }
    var data = JSON.parse($("#estimaciones").val());

    $.ajax({
        url: '/EstimacionesAdmin/CrearEstimacion',
        type: 'POST',
        data: JSON.stringify(data),
        processData: false,
        contentType: "application/json",
        beforeSend: function () {
            $.LoadingOverlay("show");
        }
    }).done(function (data, textStatus, jqXHR) {
        $.LoadingOverlay("hide");
        Swal.fire(
            'Ok!',
            data.mensaje,
            'success'
        );
        setTimeout(function () {
            $(location).attr('href', href);
        }, 2000);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        $.LoadingOverlay("hide");
        Swal.fire(
            'Oops!',
            jqXHR.responseJSON.mensaje,
            'error'
        );
        $("#btnCrear").removeClass("disabled");
    });
}

function validarUrlSharepoint() {
    let patronUrl = /https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)/g
    let patron = new RegExp(patronUrl)
    let valido =  patron.test(String(document.getElementById('urlsharepoint').value).toString())
    return valido;

}

function proyecto() {

    $("#codPresupuesto").val($(" #proyecto option:selected ")[0].attributes.codPresupuesto.value);
    $("#codigoProyecto").val($(" #proyecto option:selected ")[0].attributes.codigoProyecto.value);
    $("#fechaFin").val($(" #proyecto option:selected ")[0].attributes.fechaFin.value);
    $("#fechaInicio").val($(" #proyecto option:selected ")[0].attributes.fechaInicio.value);

}

function cantidadXcostoPerfil(id, $this) {
    var costoXCantidad = costo * $this.value;
    $(".tr-" + id + " td input")[6].value = costoXCantidad;
    $(".tr-" + id + " td spam")[0].innerText = (new Intl.NumberFormat('es-MX').format(costoXCantidad));
    total();
}

function porcentaje() {

    var cantidadTr = $(" #contenido tr ").length;
    var costo = 0;
    var porcentaje = 0;
    for (var i = 0; i < cantidadTr; i++) {
        costo = $(' #contenido tr [name="costo"] ')[i].value;
        porcentaje = Math.round((costo / totalCosto) * 100);
        $(' #contenido tr [name="porcentaje"] ')[i].value = porcentaje;
        $(' #contenido tr .porcentaje spam ')[i].innerText = porcentaje + "%";
    }
}

function total() {
    var sum = 0;
    $('[name="costo"]').each(function () {
        sum += parseFloat($(this).val().replace(/,/g, ''), 10);
    });
    totalCosto = sum
    $('#totalCosto').html("$ " + new Intl.NumberFormat('es-MX').format(totalCosto));

    porcentaje();

    sum = 0;
    $('[name="porcentaje"]').each(function () {
        sum += parseFloat($(this).val().replace(/,/g, ''), 10);
    });
    $(" #totalPorcentaje ").text(Math.round(sum) + "%");
}