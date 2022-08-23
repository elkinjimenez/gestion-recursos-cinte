package com.claro.gestionrecursosapi.opcionfront.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DF_OPCIONES_FRONT_END")
public class OpcionFrontEntity {
	@Id
	private Integer id;
	private String grupo;
	private String nombre;
	private String valor;
	@Column(name = "RUTA_PADRE")
	private String rutaPadre;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getRutaPadre() {
		return rutaPadre;
	}
	public void setRutaPadre(String rutaPadre) {
		this.rutaPadre = rutaPadre;
	}
}
