$("#btnvalidar").click(function(){
  if ($("#inputArchivo").val()) {
    $("#exampleModal").modal("show");
    $("#formEnvio").append($("#inputArchivo").attr("hidden",true));
  } else {
    $("#error").show(0).delay(4000).hide(0);
  }
});

$("#btncerrar").click(function() {
    $("#exampleModal").modal("hide");
    $("#divArchivo").append($("#inputArchivo").attr("hidden",false));
});