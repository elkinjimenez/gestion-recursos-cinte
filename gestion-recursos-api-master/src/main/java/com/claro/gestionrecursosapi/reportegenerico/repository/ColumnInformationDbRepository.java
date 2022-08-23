package com.claro.gestionrecursosapi.reportegenerico.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.reportegenerico.entity.ColumnInformationDb;

public interface ColumnInformationDbRepository extends CrudRepository<ColumnInformationDb, String> {

	@Query(value = "Select columnInformation from ColumnInformationDb columnInformation where columnInformation.tableName = :nombreTabla")
	public Iterable<ColumnInformationDb> getColumnasTabla(String nombreTabla);

	@Query(value = "Select columnInformation from ColumnInformationDb columnInformation where columnInformation.tableName = :nombreTabla and columnInformation.name != 'ID' and columnInformation.name not like 'COD%'")
	public Iterable<ColumnInformationDb> getColumnasTablaParaFiltros(String nombreTabla);
}
