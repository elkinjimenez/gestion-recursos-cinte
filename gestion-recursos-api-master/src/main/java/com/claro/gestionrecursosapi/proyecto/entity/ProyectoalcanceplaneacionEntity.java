package com.claro.gestionrecursosapi.proyecto.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the proyectoalcanceplaneacion database table.
 * 
 */
@Entity
@Table(name="DF_PROYECTOALCANCEPLANEACION")
public class ProyectoalcanceplaneacionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "proyectoalcanceplaneacion_Sequence")
	@SequenceGenerator(name = "proyectoalcanceplaneacion_Sequence", sequenceName = "SEQ_PROYECTOALCANCEPLANEACION", allocationSize = 1)
	private int id;

	private String descripcion;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp fechacreacion;

	@UpdateTimestamp
	private Timestamp fechamodificacion;

	@Min(value = 1, message = "Campo requerido")
	private int orden;

	@NotBlank(message = "Campo requerido")
	private String tipo;

	@Min(value = 1, message = "Campo requerido")
	private Integer codproyecto;

	public ProyectoalcanceplaneacionEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCodproyecto() {
		return codproyecto;
	}

	public void setCodproyecto(Integer codproyecto) {
		this.codproyecto = codproyecto;
	}

}