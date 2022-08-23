package com.claro.gestionrecursosapi.tarea.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DF_TAREAACTIVIDAD_VU")
public class TareaActividadVU {

	@Id
	private Integer id;
    private String text;
    private String start_date;
    private Integer duration;
    private BigDecimal progress;
    private Integer parent;
    private String type;
    private Boolean open;    
    private Integer codproyecto;
    private Integer codproveedor;
    private Integer codpersona;
    private Integer codpersonaasignado;
    private String empleadoasignado;
    private Integer codpersonacreo;
    private Date fechainiestimada;
    private Date fechafinestimada;
    private Integer tiempoestimado;
    private Date fechainireal;
    private Date fechafinreal;
    private Integer tiemporeal;
    private String color;
    private String progressColor;
    private Date fechainicio;
    private Date fechafin; 
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public BigDecimal getProgress() {
		return progress;
	}
	public void setProgress(BigDecimal progress) {
		this.progress = progress;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public Integer getCodproyecto() {
		return codproyecto;
	}
	public void setCodproyecto(Integer codproyecto) {
		this.codproyecto = codproyecto;
	}
	public Integer getCodproveedor() {
		return codproveedor;
	}
	public void setCodproveedor(Integer codproveedor) {
		this.codproveedor = codproveedor;
	}
	public Integer getCodpersona() {
		return codpersona;
	}
	public void setCodpersona(Integer codpersona) {
		this.codpersona = codpersona;
	}
	public Integer getCodpersonaasignado() {
		return codpersonaasignado;
	}
	public void setCodpersonaasignado(Integer codpersonaasignado) {
		this.codpersonaasignado = codpersonaasignado;
	}
	public String getEmpleadoasignado() {
		return empleadoasignado;
	}
	public void setEmpleadoasignado(String empleadoasignado) {
		this.empleadoasignado = empleadoasignado;
	}
	public Integer getCodpersonacreo() {
		return codpersonacreo;
	}
	public void setCodpersonacreo(Integer codpersonacreo) {
		this.codpersonacreo = codpersonacreo;
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
	public Integer getTiempoestimado() {
		return tiempoestimado;
	}
	public void setTiempoestimado(Integer tiempoestimado) {
		this.tiempoestimado = tiempoestimado;
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
	public Integer getTiemporeal() {
		return tiemporeal;
	}
	public void setTiemporeal(Integer tiemporeal) {
		this.tiemporeal = tiemporeal;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	public String getProgressColor() {
		return progressColor;
	}
	public void setProgressColor(String progressColor) {
		this.progressColor = progressColor;
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
	
}
