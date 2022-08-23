package com.claro.gestionrecursosapi.reporte.dto;

import java.util.Date;

public class ReporteFiltroDto {
	
	private Integer codreporte;
	private Date fechainicio;
	private Date fechafin;
	private Integer codproyecto;
	private Integer codproveedor;
	private String tarea;
	private Integer codtareatipo;
	private Integer codtareaestado;	
	private Boolean eslogro;
	private Integer codempleadoasignado;
	private Integer codempleadocreo;
	
	public Integer getCodreporte() {
		return codreporte;
	}
	public void setCodreporte(Integer codreporte) {
		this.codreporte = codreporte;
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
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
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
	public Boolean getEslogro() {
		return eslogro;
	}
	public void setEslogro(Boolean eslogro) {
		this.eslogro = eslogro;
	}
	public Integer getCodempleadoasignado() {
		return codempleadoasignado;
	}
	public void setCodempleadoasignado(Integer codempleadoasignado) {
		this.codempleadoasignado = codempleadoasignado;
	}
	public Integer getCodempleadocreo() {
		return codempleadocreo;
	}
	public void setCodempleadocreo(Integer codempleadocreo) {
		this.codempleadocreo = codempleadocreo;
	}
	
}
