package com.claro.gestionrecursosweb.tarea.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TareaDto {

	private Integer id;
	private Integer codtareapadre;
	private Integer codtareatipo;
	private Integer codtareaestado;
	private Integer codempleadocreo;
	private Integer codempleadoasignado;
	private Integer codproyecto;
	private String jerarquia;
	private String nombre;
	private Integer nivel;
	private String descripcion;
	private Date fechainiestimada;
	private Date fechafinestimada;
	private Date fechainireal;
	private Date fechafinreal;
	private BigDecimal tiempoestimado;
	private BigDecimal tiemporeal;
	private Boolean eslogro;
	private String estado;
	private Date fechacreacion;
	private Date fechamodificacion;
	private Date fechacompromiso;
	private Date fechasolicitud;
	private String descripcionsolicitud;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodtareapadre() {
		return codtareapadre;
	}
	public void setCodtareapadre(Integer codtareapadre) {
		this.codtareapadre = codtareapadre;
	}
	public Integer getCodtareatipo() {
		return codtareatipo;
	}
	public void setCodtareatipo(Integer codtareatipo) {
		this.codtareatipo = codtareatipo;
	}
	public Integer getCodtareaestado() {
		return codtareaestado;
	}
	public void setCodtareaestado(Integer codtareaestado) {
		this.codtareaestado = codtareaestado;
	}
	public Integer getCodempleadocreo() {
		return codempleadocreo;
	}
	public void setCodempleadocreo(Integer codempleadocreo) {
		this.codempleadocreo = codempleadocreo;
	}
	public Integer getCodempleadoasignado() {
		return codempleadoasignado;
	}
	public void setCodempleadoasignado(Integer codempleadoasignado) {
		this.codempleadoasignado = codempleadoasignado;
	}
	public Integer getCodproyecto() {
		return codproyecto;
	}
	public void setCodproyecto(Integer codproyecto) {
		this.codproyecto = codproyecto;
	}
	public String getJerarquia() {
		return jerarquia;
	}
	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechainiestimada() {
		return fechainiestimada;
	}
	public void setFechainiestimada(Date fechainiestimada) {
		this.fechainiestimada = fechainiestimada;
	}
	public Date getFechafinestimada() {
		return fechafinestimada;
	}
	public void setFechafinestimada(Date fechafinestimada) {
		this.fechafinestimada = fechafinestimada;
	}
	public Date getFechainireal() {
		return fechainireal;
	}
	public void setFechainireal(Date fechainireal) {
		this.fechainireal = fechainireal;
	}
	public Date getFechafinreal() {
		return fechafinreal;
	}
	public void setFechafinreal(Date fechafinreal) {
		this.fechafinreal = fechafinreal;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public Date getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}
	public BigDecimal getTiempoestimado() {
		return tiempoestimado;
	}
	public void setTiempoestimado(BigDecimal tiempoestimado) {
		this.tiempoestimado = tiempoestimado;
	}
	public BigDecimal getTiemporeal() {
		return tiemporeal;
	}
	public void setTiemporeal(BigDecimal tiemporeal) {
		this.tiemporeal = tiemporeal;
	}
	public Boolean getEslogro() {
		return eslogro;
	}
	public void setEslogro(Boolean eslogro) {
		this.eslogro = eslogro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechacompromiso() {
		return fechacompromiso;
	}

	public void setFechacompromiso(Date fechacompromiso) {
		this.fechacompromiso = fechacompromiso;
	}

	public Date getFechasolicitud() {
		return fechasolicitud;
	}

	public void setFechasolicitud(Date fechasolicitud) {
		this.fechasolicitud = fechasolicitud;
	}

	public String getDescripcionsolicitud() {
		return descripcionsolicitud;
	}

	public void setDescripcionsolicitud(String descripcionsolicitud) {
		this.descripcionsolicitud = descripcionsolicitud;
	}
}
