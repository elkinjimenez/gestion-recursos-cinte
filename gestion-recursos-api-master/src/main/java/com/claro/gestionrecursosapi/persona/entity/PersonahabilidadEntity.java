package com.claro.gestionrecursosapi.persona.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.Min;


/**
 * The persistent class for the personahabilidad database table.
 * 
 */
@Entity
@Table(name="DF_PERSONAHABILIDAD")
public class PersonahabilidadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "personahabilidad_Sequence")
	@SequenceGenerator(name = "personahabilidad_Sequence", sequenceName = "SEQ_PERSONAHABILIDAD", allocationSize = 1)
	private int id;

	@Min(value = 0, message = "Campo requerido")
	private BigDecimal experienciaanios;

	@Min(value = 1, message = "Campo requerido")
	private Integer codhabilidad;

	@Min(value = 1, message = "Campo requerido")
	private Integer codpersona;

	public PersonahabilidadEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getExperienciaanios() {
		return this.experienciaanios;
	}

	public void setExperienciaanios(BigDecimal experienciaanios) {
		this.experienciaanios = experienciaanios;
	}

	public Integer getCodhabilidad() {
		return codhabilidad;
	}

	public void setCodhabilidad(Integer codhabilidad) {
		this.codhabilidad = codhabilidad;
	}

	public Integer getCodpersona() {
		return codpersona;
	}

	public void setCodpersona(Integer codpersona) {
		this.codpersona = codpersona;
	}
}