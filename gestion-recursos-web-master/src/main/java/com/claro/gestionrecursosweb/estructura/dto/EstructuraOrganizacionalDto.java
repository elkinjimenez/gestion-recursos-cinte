package com.claro.gestionrecursosweb.estructura.dto;

import java.util.Date;

public class EstructuraOrganizacionalDto {

	private Integer id;
	private Integer codpadre;
	private Integer codestructuratipo;
	private String jerarquia;
	private Integer codempleado_responsable;
	private String nombre;
	private Date fechaCreacion;
	private Date fechaModificacion;
	public String incorrectData;
	private String jerarquiaJerarca;

	public EstructuraOrganizacionalDto() {
	}

	public String getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechacreacion) {
		this.fechaCreacion = fechacreacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechamodificacion) {
		this.fechaModificacion = fechamodificacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCodempleado_responsable() {
		return codempleado_responsable;
	}

	public void setCodempleado_responsable(Integer codempleado_responsable) {
		this.codempleado_responsable = codempleado_responsable;
	}

	public Integer getCodpadre() {
		return codpadre;
	}

	public void setCodpadre(Integer codpadre) {
		this.codpadre = codpadre;
	}

	public Integer getCodestructuratipo() {
		return codestructuratipo;
	}

	public void setCodestructuratipo(Integer codestructuratipo) {
		this.codestructuratipo = codestructuratipo;
	}

	public String getJerarquiaJerarca() {
		return jerarquiaJerarca;
	}

	public void setJerarquiaJerarca(String jerarquiaJerarca) {
		this.jerarquiaJerarca = jerarquiaJerarca;
	}
}
