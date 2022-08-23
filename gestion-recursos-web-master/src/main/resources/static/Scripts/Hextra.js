function validarHoraInicioyfin()
{
    $('#success-message').css('display','none');
    let ab = $('#Hini').val()
    let horaInicialval = $('#Hini').val();
    let horaFinalVal =  $('#Hfin').val();

     let reversoincial = horaInicialval.split('/').reverse();
    let reversofinal  = horaFinalVal.split('/').reverse();

    let añohorainicial =  reversoincial[0].split(' ');
    let añoinicial = añohorainicial[0];
    let tiempoInicial = añohorainicial[1].split(':');
    let HoraInical = tiempoInicial[0];
    let minutoInicial = tiempoInicial[1];
    let mesinicial = reversoincial[1];
    let diainicial = reversoincial[2];
    let añohorafinal =  reversofinal[0].split(' ');
    let añofinal = añohorafinal[0];
    let tiempoFinal  =  añohorafinal[1].split(':');
    let horafinal = tiempoFinal[0];
    let minutoFinal = tiempoFinal[1];
    let mesfinal = reversofinal[1];
    let diafinal = reversofinal[2];


    let HoraInicialDate = new Date(añoinicial, mesinicial -1, diainicial, HoraInical, minutoInicial);
    let fechaFinalDate = new Date(añofinal, mesfinal-1, diafinal,horafinal, minutoFinal );

    if(HoraInicialDate.getTime() > fechaFinalDate.getTime()) {
        $("#evidencia-error").addClass('alert alert-danger d-block').text('La Hora Inicial no puede ser mayor que la Hora final ');
    }
    else
        $('#formHoraExtra').submit();

}