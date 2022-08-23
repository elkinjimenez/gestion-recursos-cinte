function formatoFecha(fecha) {
    fechaSplit = fecha.split("/");
    return fechaSplit[2] + '/' + fechaSplit[1] + '/' + fechaSplit[0];
}

function monitore() {
    var fechaInicio = $("[name='fechaInicio']").val();
    var fechaFin = $("[name='fechaFin']").val();
    var codEstructura = $("[name='codEstructura']").val();

    if ( fechaInicio.length > 3 && fechaFin.length > 3 && codEstructura != "" ){
        var objet = {
            viewName: "DF_INDICADOR_CALIDAD_V",
            fechainicio: new Date( this.formatoFecha(fechaInicio) ),
            fechafin: new Date( this.formatoFecha(fechaFin) ),
            codEstructura: codEstructura
        };

        $.ajax({
            url: '/EstimacionesAdmin/reporteChartMonitoreo/',
            type: 'POST',
            data: JSON.stringify(objet),
            processData: false,
            contentType: "application/json",
            beforeSend: function() {
                $.LoadingOverlay("show");
            }
        }).done(function( data, textStatus, jqXHR ) {
            $.LoadingOverlay("hide");
            Swal.fire(
                'Ok!',
                data.mensaje,
                'success'
            );
            for (var i = 0; data.data.length > i; i++){
                morrisDonutData(data.data[i].element, data.data[i].data, data.data[i].colors);
            }
        }).fail(function( jqXHR, textStatus, errorThrown ) {
            $.LoadingOverlay("hide");
            Swal.fire(
                'Oops!',
                jqXHR.responseJSON.mensaje,
                'error'
            );
        });
    }
}