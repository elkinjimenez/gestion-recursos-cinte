package com.claro.gestionrecursosweb.perfil.dto;

import java.util.Date;

public class PerfilDto {

	private Integer id;
	private String nombre;
	private String estado;
	private Date fechacreacion;
	private Date fechamodificacion;
	private String constoPromedioHora;

	public PerfilDto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getConstoPromedioHora() {
		return constoPromedioHora;
	}

	public void setConstoPromedioHora(String constoPromedioHora) {
		this.constoPromedioHora = constoPromedioHora;
	}
}