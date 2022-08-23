package com.claro.gestionrecursosweb.reportegenerico.dto;

import java.util.List;

public class ViewInformationDto {
	private Iterable<ColumnInformationDbDto> columns;
	private List<Object[]> dataRows;

	public Iterable<ColumnInformationDbDto> getColumns() {
		return columns;
	}
	public void setColumns(Iterable<ColumnInformationDbDto> columns) {
		this.columns = columns;
	}
	public List<Object[]> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<Object[]> dataRows) {
		this.dataRows = dataRows;
	}
}
