package com.claro.gestionrecursosapi.reportegenerico.dto;

import java.util.Date;

public class ReporteFiltroDto {
	private String viewName;
	private Date fechainicio;
	private Date fechafin;
	private Integer codEstructura;

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
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