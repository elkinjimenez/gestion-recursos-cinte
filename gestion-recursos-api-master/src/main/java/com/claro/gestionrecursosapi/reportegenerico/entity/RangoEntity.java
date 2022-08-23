package com.claro.gestionrecursosapi.reportegenerico.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DF_RANGO")
public class RangoEntity {
	@Id
	@Column(name = "GUID")
	private String guid;

	@Column(name = "FINI")
	private Date fechainicio;

	@Column(name = "FFIN")
	private Date fechafin;

	@Column(name = "COORD")
	private Integer codEstructura;
	
	@Column(name = "GER")
	private Integer ger;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public Integer getGer() {
		return ger;
	}
	public void setGer(Integer ger) {
		this.ger = ger;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public Integer getCodEstructura() {
		return codEstructura;
	}
	public void setCodEstructura(Integer codEstructura) {
		this.codEstructura = codEstructura;
	}
}
