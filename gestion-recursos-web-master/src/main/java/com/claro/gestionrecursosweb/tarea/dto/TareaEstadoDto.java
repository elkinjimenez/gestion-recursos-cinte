package com.claro.gestionrecursosweb.tarea.dto;

import java.util.Date;

import com.claro.gestionrecursosweb.base.dto.FechaDto;

public class TareaEstadoDto extends FechaDto{
	
	private Integer id;
	private Integer codtareaestadotipo;
	private String nombre;
	private String descripcion;
	private Date fechacreacion;
	private Date fechamodificacion;
	
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
		getFormatoFechas(getFechacreacion(), getFechamodificacion());
	}
	
}
