package com.claro.gestionrecursosapi.reportegenerico.dto;

import java.util.List;

import com.claro.gestionrecursosapi.reportegenerico.entity.ColumnInformationDb;

public class ViewInformationDto {
	private Iterable<ColumnInformationDb> columns;
	private List<Object[]> dataRows;

	public Iterable<ColumnInformationDb> getColumns() {
		return columns;
	}
	public void setColumns(Iterable<ColumnInformationDb> columns) {
		this.columns = columns;
	}
	public List<Object[]> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<Object[]> dataRows) {
		this.dataRows = dataRows;
	}
}
