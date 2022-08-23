package com.claro.gestionrecursosweb.tarea.dto;

import java.util.Date;

import com.claro.gestionrecursosweb.base.dto.FechaDto;

public class TareaTipoDto extends FechaDto{
		
	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer jerarquia;
	private String color;
	private Date fechacreacion;
	private Date fechamodificacion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getJerarquia() {
		return jerarquia;
	}
	public void setJerarquia(Integer jerarquia) {
		this.jerarquia = jerarquia;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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