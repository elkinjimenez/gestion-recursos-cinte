package com.claro.gestionrecursosapi.novedad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DF_NOVEDAD_TIPO")
public class TipoNovedadEntity {
	@Id
	private Integer id;

	@Column(name = "TIPO_NOVEDAD")
	private String nombre;

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
}
