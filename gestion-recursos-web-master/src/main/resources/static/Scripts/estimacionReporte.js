

var proyectoSelect = document.getElementById('proyecto')
var panelGraficas = document.getElementById('panelgraficas')
var coordinacionSelect = document.getElementById('coordinacionSelect')
panelGraficas.classList.add('d-none')
var idProyecto;
proyectoSelect.addEventListener('change', function (evt) {
    evt.preventDefault()
    panelGraficas.classList.remove('d-block')
    let eventoActual = evt.target
    idProyecto =  eventoActual.options[this.selectedIndex].value
    mostrarCoordinaciones()
    
})

coordinacionSelect.addEventListener('change', function(evt){
    evt.preventDefault()
    panelGraficas.classList.remove('d-block')
    let eventoActual = evt.target
    let jerarquia = eventoActual.options[this.selectedIndex].dataset.jerarquia
    reporteXProyecto(idProyecto, jerarquia)
    panelGraficas.classList.add('d-block')
})

function reporteXProyecto(codProyecto, jerarquia) {
    if (idProyecto.length > 0) {
        $.ajax({
            url: '/EstimacionesAdmin/reporteChart/' + codProyecto + '/' + jerarquia,
            type: 'GET',
            data: null,
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
            for (var i = 0; data.data.length > i; i++) {
                morrisDonutData(data.data[i].element, data.data[i].data, data.data[i].colors);
            }
        }).fail(function (jqXHR, textStatus, errorThrown) {
            $.LoadingOverlay("hide");
            Swal.fire(
                'Oops!',
                jqXHR.responseJSON.mensaje,
                'error'
            );
        });
    }
}


function mostrarCoordinaciones() {
    let coordinacionBox = document.getElementById('coordinacionBox');
    $.ajax({
        url: '/Estructuraorganizacional/getEstructuraFiltroDonuts/' + idProyecto ,
        type: 'GET',
        processData: false,
        contentType: "application/json",
        beforeSend: function () {
            $.LoadingOverlay("show");
        }
    }).done(function (data, textStatus, jqXHR) {
        $("#coordinacionSelect").find('option').remove().end();
        let opcSeleccionar = document.createElement('option')
        opcSeleccionar.text = 'Seleccione una opcion'
        opcSeleccionar.value = 0
        coordinacionSelect.appendChild(opcSeleccionar)
        Array.prototype.forEach.call(data, function (elemento, key) {
            let opcion = document.createElement('option');
            opcion.value = elemento.id;
            opcion.text = elemento.nombre;
            opcion.dataset.codpadre = elemento.codpadre;
            opcion.dataset.jerarquia = elemento.jerarquia
            coordinacionSelect.appendChild(opcion);
            $("#coordinacionSelect").selectpicker("refresh");
        });
        $.LoadingOverlay("hide");
    }).fail(function (jqXHR, textStatus, errorThrown) {
        $.LoadingOverlay("hide");
        Swal.fire(
            'Oops!',
            jqXHR.responseJSON.mensaje,
            'error'
        );
    });
    coordinacionBox.classList.add('d-block')

}






