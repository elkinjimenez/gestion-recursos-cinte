function valdiarUrl() {
    var evidencia = $("[name='evidencia']").val();
    if (!evidencia.startsWith('http')){
        $("#evidencia-error").text("Esto debe estar cargado en el sharepoint por favor indicar la ruta (URL)");
    }else {
        $( "#formNovedad" ).submit();
    }
}