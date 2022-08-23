package com.claro.gestionrecursosapi.reportegenerico.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect(value = "SELECT CONCAT(CONCAT(cols.owner, cols.table_name), cols.column_name) as id, cols.* FROM  all_tab_columns cols")
public class ColumnInformationDb {
	@Id
	private String id;

	@Column(name = "TABLE_NAME")
	private String tableName;

	@Column(name = "COLUMN_NAME")
	private String name;

	@Column(name = "DATA_TYPE")
	private String dataType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
