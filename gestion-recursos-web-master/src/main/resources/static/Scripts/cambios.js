
let projectSelect = document.querySelector("select[id='projectSelect']")
let compromisoSelect = document.querySelector("select[id='compromisosSelect']")
let boxproject = document.getElementById('box-proyecto')
let boxsCompromisos = document.getElementById('box-compromiso')
let boxcambios = document.getElementById('box-info-cambio')

if (location.pathname.match('/Editar/') != null){
    boxproject.classList.remove('d-block')
    boxproject.classList.add('d-none')
    boxcambios.classList.remove('d-none')
    boxcambios.classList.add('d-block')
} 

boxsCompromisos.classList.add('d-none')
boxcambios.classList.add('d-none')
// adicionar un event listener para el select de proyectos 
projectSelect.addEventListener('change', () => {

    boxsCompromisos.classList.remove('d-block')
    boxcambios.classList.remove('d-inline')
    boxsCompromisos.classList.add('d-none')
    boxcambios.classList.add('d-none')

    let item = projectSelect.options[projectSelect.selectedIndex]
    let idProyecto = item.value
    
    if (idProyecto != 0) {
        urlcompromiso = '/api/fabrica/proyecto/' + idProyecto
        cargarCompromisosPorProyecto(urlcompromiso)
        boxsCompromisos.classList.remove('d-none')
        boxsCompromisos.classList.add('d-block')
    }
})

// adicionar un event listener para el select de compromisos 

compromisoSelect.addEventListener('change', () => {
    let item = compromisoSelect.options[compromisoSelect.selectedIndex]
    let idCompromiso = item.value
    
    if (idCompromiso != 0) {
        let urlcompromiso = '/api/fabrica/' + idCompromiso
        cargarCompromiso(urlcompromiso)
        boxcambios.classList.remove('d-none')
        boxcambios.classList.add('d-inline')
    }
})

async function cargarCompromiso(url, options = { metodo: 'POST' }) {
    $.ajax(url, {
        method: options.metodo,
        beforeSend: function () {
            $.LoadingOverlay("show");
        }

    }).done(function (data) {
        if (data != null) {
            
            cargarDatosCompromisos(data)
            $.LoadingOverlay("hide");

        } else {
            $.LoadingOverlay("hide");
            Swal.fire({
                icon: 'info',
                title: 'Aviso de Informacion',
                text: 'No hay datos'
            });
        }

    }).fail(function (jqXHR, textStatus) {
        $.LoadingOverlay("hide");
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Error ' + textStatus
        });
        return null;
    });
}

async function cargarCompromisosPorProyecto(url, options = { metodo: 'POST' }) {
    $.ajax(url, {
        method: options.metodo,
        beforeSend: function () {
            $.LoadingOverlay("show");
        }
    }).done(function (data) {
        if (data.length > 0) {
            
            $('#compromisosSelect').find('option').remove()
            let opciondefault = document.createElement('option')
            opciondefault.value = 0
            opciondefault.text = 'Seleccione una opcion'
            compromisoSelect.add(opciondefault)
            Array.prototype.forEach.call(data, function (compromiso, indice) {
                let opcion = document.createElement('option')
                opcion.value = compromiso.id
                opcion.text = compromiso.descripcion
                compromisoSelect.add(opcion)
            })
            $.LoadingOverlay("hide");

        } else {
            $.LoadingOverlay("hide");
            Swal.fire({
                icon: 'info',
                title: 'Aviso de Informacion',
                text: 'No hay d' + textStatus
            });
        }

    }).fail(function (jqXHR, textStatus) {
        $.LoadingOverlay("hide");
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Error ' + textStatus
        });
        return null;
    });

}
function cargarDatosCompromisos(data) {
    $("form [id='codCambio']").val(data.idCambio);
    $("form [id='idRlp']").val(data.idRlp);
    $("form [id='idCompromiso']").val(data.id);

}






