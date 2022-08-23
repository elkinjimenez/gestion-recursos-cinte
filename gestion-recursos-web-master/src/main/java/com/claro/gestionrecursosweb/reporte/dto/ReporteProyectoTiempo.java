package com.claro.gestionrecursosweb.reporte.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReporteProyectoTiempo {

	private String idproyecto;
	private String proyecto;
	private BigDecimal horas;
	private String proveedor;
	private Long idrecurso;
	private String recurso;
	private String perfil;
	private BigDecimal costohora;
	private BigDecimal costo;
	private String descripcion;
	private BigDecimal horaslaborales;
    private BigDecimal horasausencias;
    private Date fechaingreso;
    
    public ReporteProyectoTiempo() {
    	
    }
    
    public ReporteProyectoTiempo(String idproyecto, String proyecto, BigDecimal horas,
    							 String proveedor, Long idrecurso, String recurso,
    							 String perfil, BigDecimal costohora, BigDecimal costo,
    							 String descripcion, BigDecimal horaslaborales, 
    							 BigDecimal horasausencias, Date fechaingreso) {
    	this.idproyecto = idproyecto;
    	this.proyecto = proyecto;
    	this.horas = horas;
    	this.proveedor = proveedor;
    	this.idrecurso = idrecurso;
    	this.recurso = recurso;
    	this.perfil = perfil;
    	this.costohora = costohora;
    	this.costo = costo;
    	this.descripcion = descripcion;
    	this.horaslaborales = horaslaborales;
    	this.horasausencias = horasausencias;
    	this.fechaingreso = fechaingreso;
    }
    
	public String getIdproyecto() {
		return idproyecto;
	}
	public void setIdproyecto(String idproyecto) {
		this.idproyecto = idproyecto;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public BigDecimal getHoras() {
		return horas;
	}
	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public Long getIdrecurso() {
		return idrecurso;
	}
	public void setIdrecurso(Long idrecurso) {
		this.idrecurso = idrecurso;
	}
	public String getRecurso() {
		return recurso;
	}
	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public BigDecimal getCostohora() {
		return costohora;
	}
	public void setCostohora(BigDecimal costohora) {
		this.costohora = costohora;
	}
	public BigDecimal getCosto() {
		return costo;
	}
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getHoraslaborales() {
		return horaslaborales;
	}
	public void setHoraslaborales(BigDecimal horaslaborales) {
		this.horaslaborales = horaslaborales;
	}
	public BigDecimal getHorasausencias() {
		return horasausencias;
	}
	public void setHorasausencias(BigDecimal horasausencias) {
		this.horasausencias = horasausencias;
	}
	public Date getFechaingreso() {
		return fechaingreso;
	}
	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}
	
}
