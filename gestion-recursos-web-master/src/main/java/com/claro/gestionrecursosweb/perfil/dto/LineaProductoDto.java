package com.claro.gestionrecursosweb.perfil.dto;

import java.util.Date;

import com.claro.gestionrecursosweb.base.dto.FechaDto;

public class LineaProductoDto extends FechaDto {
	
	private Integer id;
	
	private String nombre;

	private Date fechacreacion;

	private Date fechamodificacion;
	
	public String incorrectData;

	

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

