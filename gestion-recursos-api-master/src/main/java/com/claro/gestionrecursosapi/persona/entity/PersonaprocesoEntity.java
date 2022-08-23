package com.claro.gestionrecursosapi.persona.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the personaproceso database table.
 * 
 */
@Entity
@Table(name="DF_PERSONAPROCESO")
public class PersonaprocesoEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "personaproceso_Sequence")
	@SequenceGenerator(name = "personaproceso_Sequence", sequenceName = "SEQ_PERSONAPROCESO", allocationSize = 1)
	private int id;
	
	@Min(value = 1, message = "Campo requerido")
	private Integer codpersona;
	
	@Min(value = 1, message = "Campo requerido")
	private Integer codprocesoestado;

	private String observacion;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp fechacreacion;

	@UpdateTimestamp
	private Timestamp fechamodificacion;
	
	public PersonaprocesoEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getCodpersona() {
		return codpersona;
	}

	public void setCodpersona(Integer codpersona) {
		this.codpersona = codpersona;
	}

	public Integer getCodprocesoestado() {
		return codprocesoestado;
	}

	public void setCodprocesoestado(Integer codprocesoestado) {
		this.codprocesoestado = codprocesoestado;
	}
}