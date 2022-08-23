package com.claro.gestionrecursosapi.novedad.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "DF_NOVEDAD")
public class NovedadEntity {
	@Id
	private Integer id;
	@Column(name = "IDEMPLEADO")
	private Integer codEmpleado;
	@Column(name = "TIPO_NOVEDAD")
	private Integer codTipoNovedad;
	@Column(name = "IDPROYECTO")
	private Integer codProyecto;
	@JsonFormat(timezone="GMT-5")
	@Column(name = "INI_NOVEDAD")
	private Date fechaInicio;
	@JsonFormat(timezone="GMT-5")
	@Column(name = "FIN_NOVEDAD")
	private Date fechaFin;
	private String observacion;
	private boolean aprobado;
	private String evidencia;
	@Column(name = "ESTADO")
	private Integer estadoRegistro;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodEmpleado() {
		return codEmpleado;
	}
	public void setCodEmpleado(Integer codEmpleado) {
		this.codEmpleado = codEmpleado;
	}
	public Integer getCodTipoNovedad() {
		return codTipoNovedad;
	}
	public void setCodTipoNovedad(Integer codTipoNovedad) {
		this.codTipoNovedad = codTipoNovedad;
	}
	public Integer getCodProyecto() {
		return codProyecto;
	}
	public void setCodProyecto(Integer codProyecto) {
		this.codProyecto = codProyecto;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public boolean isAprobado() {
		return aprobado;
	}
	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}
	public String getEvidencia() {
		return evidencia;
	}
	public void setEvidencia(String evidencia) {
		this.evidencia = evidencia;
	}
	public Integer getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(Integer estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
