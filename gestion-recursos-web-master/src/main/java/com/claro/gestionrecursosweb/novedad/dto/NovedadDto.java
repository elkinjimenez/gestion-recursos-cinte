package com.claro.gestionrecursosweb.novedad.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class NovedadDto {
	private Integer id;
	private Integer codEmpleado;
	private Integer codTipoNovedad;
	private Integer codProyecto;
	private Date fechaInicio;
	private Date fechaFin;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaInicioLocal;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaFinLocal;
	private String observacion;
	private boolean aprobado;
	private String evidencia;
	private Integer estadoRegistro;
	private String tipoNovedad;
	private String empleado;
	private String proyecto;

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
	public LocalDateTime getFechaInicioLocal() {
		return fechaInicioLocal;
	}
	public void setFechaInicioLocal(LocalDateTime fechaInicioLocal) {
		this.fechaInicioLocal = fechaInicioLocal;
	}
	public LocalDateTime getFechaFinLocal() {
		return fechaFinLocal;
	}
	public void setFechaFinLocal(LocalDateTime fechaFinLocal) {
		this.fechaFinLocal = fechaFinLocal;
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
	public String getTipoNovedad() {
		return tipoNovedad;
	}
	public void setTipoNovedad(String tipoNovedad) {
		this.tipoNovedad = tipoNovedad;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
}
