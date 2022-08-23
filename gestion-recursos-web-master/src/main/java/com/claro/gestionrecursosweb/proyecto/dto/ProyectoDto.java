package com.claro.gestionrecursosweb.proyecto.dto;

import java.sql.Timestamp;
import java.util.Date;

public class ProyectoDto {

	private Integer id;	
	private Integer codproyectotipo;
	private PresupuestoDto codpresupuesto;
	private String codigoproyecto;
	private String nombre;
	private String descripcion;
	private Boolean prioritario;
	private Date fechafin;
	private Date fechainicio;
	private Timestamp fechacreacion;
	private Timestamp fechamodificacion;
	private String estado;
	private String nombreAMX;
	private String nombreLocal;
	private String agrcapex;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodproyectotipo() {
		return codproyectotipo;
	}
	public void setCodproyectotipo(Integer codproyectotipo) {
		this.codproyectotipo = codproyectotipo;
	}
	public PresupuestoDto getCodpresupuesto() {
		return codpresupuesto;
	}
	public void setCodpresupuesto(PresupuestoDto codpresupuesto) {
		this.codpresupuesto = codpresupuesto;
	}
	public String getCodigoproyecto() {
		return codigoproyecto;
	}
	public void setCodigoproyecto(String codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getPrioritario() {
		return prioritario;
	}
	public void setPrioritario(Boolean prioritario) {
		this.prioritario = prioritario;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Timestamp getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public Timestamp getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombreAMX() {
		return nombreAMX;
	}

	public void setNombreAMX(String nombreAMX) {
		this.nombreAMX = nombreAMX;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public String getAgrcapex() {
		return agrcapex;
	}

	public void setAgrcapex(String agrcapex) {
		this.agrcapex = agrcapex;
	}
}
