package com.claro.gestionrecursosweb.tarea.dto;

import com.claro.gestionrecursosweb.base.dto.FechaDto;

public class TareaEstadoVistaDTO extends FechaDto  {

	/**
	 * 
	 */
	private Integer id;
	private Integer codtareaestadotipo;
	private String codigoestado;
	private String nombre;
	private String descripcion;
	private String fechacreacion;
	private String fechamodificacion;
	
	public TareaEstadoVistaDTO(Integer id,Integer codtareaestadotipo,String codigoestado, String nombre,String descripcion,String fechacreacion,String fechamodificacion) {
		this.id=id;
		this.codtareaestadotipo=codtareaestadotipo;
		this.codigoestado=codigoestado;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.fechacreacion=fechacreacion;
	    this.fechamodificacion=fechamodificacion;
	    getFormatoFechas(fechacreacion, fechamodificacion);		
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodtareaestadotipo() {
		return codtareaestadotipo;
	}
	public void setCodtareaestadotipo(Integer codtareaestadotipo) {
		this.codtareaestadotipo = codtareaestadotipo;
	}
	public String getCodigoestado() {
		return codigoestado;
	}
	public void setCodigoestado(String codigoestado) {
		this.codigoestado = codigoestado;
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
	public String getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(String fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public String getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(String fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}
	
	
	
	
}
