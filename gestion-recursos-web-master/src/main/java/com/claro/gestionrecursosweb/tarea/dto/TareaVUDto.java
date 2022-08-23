package com.claro.gestionrecursosweb.tarea.dto;

import java.math.BigDecimal;
import java.util.Date;
public class TareaVUDto {

	private Integer id;
	private Integer codtarea;
	private Integer codtareapadre;
	private String tareapadre;
	private Integer codtareatipo;
	private String tareatipo;
	private Integer codtareaestado;
	private String tareaestado;
	private Integer codtareaestadotipo;
	private String tareaestadotipo;
	private Integer codempleadocreo;
	private String empleadocreo;
	private Integer codempleadoasignado;
	private String empleadoasignado;
	private Integer codproyecto;
	private String proyecto;
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodtarea() {
		return codtarea;
	}
	public void setCodtarea(Integer codtarea) {
		this.codtarea = codtarea;
	}
	public Integer getCodtareapadre() {
		return codtareapadre;
	}
	public void setCodtareapadre(Integer codtareapadre) {
		this.codtareapadre = codtareapadre;
	}
	public String getTareapadre() {
		return tareapadre;
	}
	public void setTareapadre(String tareapadre) {
		this.tareapadre = tareapadre;
	}
	public Integer getCodtareatipo() {
		return codtareatipo;
	}
	public void setCodtareatipo(Integer codtareatipo) {
		this.codtareatipo = codtareatipo;
	}
	public String getTareatipo() {
		return tareatipo;
	}
	public void setTareatipo(String tareatipo) {
		this.tareatipo = tareatipo;
	}
	public Integer getCodtareaestado() {
		return codtareaestado;
	}
	public void setCodtareaestado(Integer codtareaestado) {
		this.codtareaestado = codtareaestado;
	}
	public String getTareaestado() {
		return tareaestado;
	}
	public void setTareaestado(String tareaestado) {
		this.tareaestado = tareaestado;
	}
	public Integer getCodtareaestadotipo() {
		return codtareaestadotipo;
	}
	public void setCodtareaestadotipo(Integer codtareaestadotipo) {
		this.codtareaestadotipo = codtareaestadotipo;
	}
	public String getTareaestadotipo() {
		return tareaestadotipo;
	}
	public void setTareaestadotipo(String tareaestadotipo) {
		this.tareaestadotipo = tareaestadotipo;
	}
	public Integer getCodempleadocreo() {
		return codempleadocreo;
	}
	public void setCodempleadocreo(Integer codempleadocreo) {
		this.codempleadocreo = codempleadocreo;
	}
	public String getEmpleadocreo() {
		return empleadocreo;
	}
	public void setEmpleadocreo(String empleadocreo) {
		this.empleadocreo = empleadocreo;
	}
	public Integer getCodempleadoasignado() {
		return codempleadoasignado;
	}
	public void setCodempleadoasignado(Integer codempleadoasignado) {
		this.codempleadoasignado = codempleadoasignado;
	}
	public String getEmpleadoasignado() {
		return empleadoasignado;
	}
	public void setEmpleadoasignado(String empleadoasignado) {
		this.empleadoasignado = empleadoasignado;
	}
	public Integer getCodproyecto() {
		return codproyecto;
	}
	public void setCodproyecto(Integer codproyecto) {
		this.codproyecto = codproyecto;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
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
	
}
