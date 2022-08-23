let password = document.getElementById('passwordvalue')
let passwordConfirm = document.getElementById('passwordconfirm');
let botonGuardar = document.getElementById('btnGuardar');
let mensaje = document.getElementById('mensaje')

$('#modalChancePassword').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) 
    $('input:password').attr('placeholder','');
    password.value = ''
    passwordConfirm.value = ''
    mensaje.innerText = ''
    mensaje.classList.add('d-none')
    mensaje.classList.add('alert-danger')
    let usuario = button.data('usuario')
    botonGuardar.addEventListener('click', Guardar);
    const confirmacionPassword = (password, passwordConfirm) => {
        return password.value === passwordConfirm.value;
    }

    function Guardar(evt) {
        mensaje.classList.add('d-none')
        if (password.value != '' && passwordConfirm.value) {
            if (!confirmacionPassword(password, passwordConfirm)) {
                mensaje.innerText = 'la contraseña no coincide '
                mensaje.classList.remove('d-none')
                mensaje.classList.add('alert-danger')
            } else {
                solicitudAjax("/Seguridad/cambiarPassword/" + usuario + "/" + password.value, "POST", null, function (data) {
                    $.LoadingOverlay("hide");
                    mensaje.classList.remove('d-none')
                    if (data.id != null) {
                        botonGuardar.removeEventListener('click', Guardar)
                        $('#modalChancePassword').modal('hide');
                        Swal.fire(
                            'Ok',
                            'Contraseña Actualizada ',
                            'success'
                        );
                    } else {
                        mensaje.innerText = 'usuario no encontrado '
                    }
                }, function (err, er, e) {
                    mensaje.innerText = 'Error en cambio contraseña' + err
                    botonGuardar.removeEventListener('click', Guardar)
                    Swal.fire(
                        'Ok',
                        'Error ' + err,
                        'error'
                    );
                })
            }
        } else {
            mensaje.innerText = 'debe digitar el password y la confirmacion '
            mensaje.classList.remove('d-none')
            mensaje.classList.add('alert-danger')
        }
    }

    $('#modalChancePassword').on('hidden.bs.modal', function (e) {
        $('div').find('.passtrengthMeter').removeClass('weak')
        $('div').find('.passtrengthMeter').removeClass('medium')
        $('div').find('.passtrengthMeter').removeClass('strong')
        $('div').find('.passtrengthMeter').removeClass('very-strong')
        botonGuardar.removeEventListener('click', Guardar)
    })

    $("#passwordvalue").passtrength({
        minChars: 5,
        passwordToggle: true,
        tooltip: true,
        textWeak: "Debil",
        textMedium: "Medio",
        textStrong: "Fuerte",
        textVeryStrong: "Muy Fuerte",
        eyeImg: "/Imagenes/eye.svg"
    });

    $("#passwordconfirm").passtrength({
        minChars: 5,
        passwordToggle: true,
        tooltip: true,
        textWeak: "Debil",
        textMedium: "Medio",
        textStrong: "Fuerte",
        textVeryStrong: "Muy Fuerte",
        eyeImg: "/Imagenes/eye.svg"
    });

})










