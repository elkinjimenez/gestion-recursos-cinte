package com.claro.gestionrecursosapi.persona.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the personaausencia database table.
 * 
 */
@Entity
@Table(name="DF_PERSONAAUSENCIA")
public class PersonaausenciaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "personaausencia_Sequence")
	@SequenceGenerator(name = "personaausencia_Sequence", sequenceName = "SEQ_PERSONAAUSENCIA", allocationSize = 1)
	private int id;

	private String descripcion;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp fechacreacion;

	@Temporal(TemporalType.DATE)
	private Date fechafin;

	@Temporal(TemporalType.DATE)
	private Date fechainicio;

	@UpdateTimestamp
	private Timestamp fechamodificacion;

	@Min(value = 1, message = "Campo requerido")
	private Integer codpersona;

	@Min(value = 1, message = "Campo requerido")
	private Integer codausenciatipo;

	public PersonaausenciaEntity() {
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

	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Date getFechainicio() {
		return this.fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public Timestamp getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public Integer getCodpersona() {
		return codpersona;
	}

	public void setCodpersona(Integer codpersona) {
		this.codpersona = codpersona;
	}

	public Integer getCodausenciatipo() {
		return codausenciatipo;
	}

	public void setCodausenciatipo(Integer codausenciatipo) {
		this.codausenciatipo = codausenciatipo;
	}
	
	
}