package com.claro.gestionrecursosapi.perfil.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the perfiltipo database table.
 * 
 */
@Entity
@Table(name="DF_PERFILTIPO")
public class PerfiltipoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "perfiltipo_Sequence")
	@SequenceGenerator(name = "perfiltipo_Sequence", sequenceName = "SEQ_PERFILTIPO", allocationSize = 1)
	private Integer id;
	@CreationTimestamp
	@Column(updatable = false)
	private Date fechacreacion;

	@UpdateTimestamp
	private Date fechamodificacion;

	@NotBlank(message = "Campo requerido")
	private String nombre;

	public PerfiltipoEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}