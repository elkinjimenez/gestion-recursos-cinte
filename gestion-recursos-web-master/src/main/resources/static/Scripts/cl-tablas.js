/**
 * Requiere que se incluya el complemento "DataTables"
 */

$(function() {
	$(".cl-tabla").DataTable({
		"language" : {
			"decimal" : "",
			"emptyTable" : "No se encontró información",
			"info" : "Mostrando _START_ a _END_ de _TOTAL_ resultados",
			"infoEmpty" : "Mostrando 0 a 0 de 0 resultados",
			"infoFiltered" : "(Filtrados de _MAX_ resultados)",
			"infoPostFix" : "",
			"thousands" : ",",
			"lengthMenu" : "Mostrar _MENU_ resultados",
			"loadingRecords" : "Cargando...",
			"processing" : "Procesando...",
			"search" : "Buscar:",
			"zeroRecords" : "No se encontraron resultados",
			"paginate" : {
				"first" : "Inicio",
				"last" : "Fin",
				"next" : ">>",
				"previous" : "<<"
			},
			"aria" : {
				"sortAscending" : ": orden ascendente",
				"sortDescending" : ": orden descendente"
			}
		}
	});
	
	$('.cl-tabla').on( 'page.dt', function () {
		setTimeout(clTablasBotonConsultarMantenerMenu, 100);
	}).on( 'search.dt', function () {
		setTimeout(clTablasBotonConsultarMantenerMenu, 100);
	});
});