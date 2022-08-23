package com.claro.gestionrecursosweb.reporte.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReporteTiempoDto {

	private Integer id;
	private String proyecto;
	private String tarea;
	private String proveedor;	
	private String recurso;
	private String descripcion;
	private BigDecimal horas;
	private Date fechainicio;	
	private Date fechafin;
	private BigDecimal horasreportadas;	
	private BigDecimal horasausencias;	
	private BigDecimal horaslaborales;	
	private BigDecimal horaspendientes;
	
	public String getProyecto() {
		return proyecto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getRecurso() {
		return recurso;
	}
	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getHoras() {
		return horas;
	}
	public void setHoras(BigDecimal horas) {
		this.horas = horas;
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
	public BigDecimal getHorasreportadas() {
		return horasreportadas;
	}
	public void setHorasreportadas(BigDecimal horasreportadas) {
		this.horasreportadas = horasreportadas;
	}
	public BigDecimal getHorasausencias() {
		return horasausencias;
	}
	public void setHorasausencias(BigDecimal horasausencias) {
		this.horasausencias = horasausencias;
	}
	public BigDecimal getHoraslaborales() {
		return horaslaborales;
	}
	public void setHoraslaborales(BigDecimal horaslaborales) {
		this.horaslaborales = horaslaborales;
	}
	public BigDecimal getHoraspendientes() {
		return horaspendientes;
	}
	public void setHoraspendientes(BigDecimal horaspendientes) {
		this.horaspendientes = horaspendientes;
	}
	
}
