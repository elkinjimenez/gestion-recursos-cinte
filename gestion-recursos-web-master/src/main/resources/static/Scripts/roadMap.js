$(' [name="_introduccionServicio"] ').remove();
$(' [name="_estadoEntrega"] ').remove();
tipoRoadMap();

function iniciar(){
    $(".informacion3scala").hide();
    $(".mongoVersion").hide();
    $(".jdkVersion").hide();
}

function tipoRoadMap() {
    var tipo = $('[name="tipo"]').val();
    iniciar();
    if (tipo == 1){
        $(".informacion3scala").show();

        $('[name="jdkVersion"]').val("");
        $('[name="mongoVersion"]').val("");
    }else if ( tipo == 6){
        $(".mongoVersion").show();

        $('[name="jdkVersion"]').val("");
        $('[name="informacion3scala"]').val("");
    }else if ( tipo == 9){
        $(".jdkVersion").show();

        $('[name="mongoVersion"]').val("");
        $('[name="informacion3scala"]').val("");
    }
}