package com.claro.gestionrecursosapi.estructura.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the estructuraorganizacional database table.
 * 
 */
@Entity
@Table(name="DF_ESTRUCTURAORGANIZACIONAL")
public class EstructuraorganizacionalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "estructuraorganizacional_Sequence")
	@SequenceGenerator(name = "estructuraorganizacional_Sequence", sequenceName = "SEQ_ESTRUCTURAORGANIZACIONAL", allocationSize = 1)
	private Integer id;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp fechaCreacion;

	@UpdateTimestamp
	private Timestamp fechaModificacion;

	@NotBlank(message = "Campo requerido")
	private String nombre;

	@Min(value = 1, message = "Campo requerido")
	private Integer codempleado_responsable;

	//@Min(value = 1, message = "Campo requerido")
	private Integer codpadre;

	@Min(value = 1, message = "Campo requerido")
	private Integer codestructuratipo;

	private String jerarquia;

	@Formula("(select estructura_jerarca.JERARQUIAJERARCA from DF_ESTRUCTURAORG_JERARCA estructura_jerarca where estructura_jerarca.id = id)")
	private String jerarquiaJerarca;

	public EstructuraorganizacionalEntity() {

	}

	public String getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechacreacion) {
		this.fechaCreacion = fechacreacion;
	}

	public Timestamp getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechamodificacion) {
		this.fechaModificacion = fechamodificacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCodempleado_responsable() {
		return codempleado_responsable;
	}

	public void setCodempleado_responsable(Integer codempleado_responsable) {
		this.codempleado_responsable = codempleado_responsable;
	}

	public Integer getCodpadre() {
		return codpadre;
	}

	public void setCodpadre(Integer codpadre) {
		this.codpadre = codpadre;
	}

	public Integer getCodestructuratipo() {
		return codestructuratipo;
	}

	public void setCodestructuratipo(Integer codestructuratipo) {
		this.codestructuratipo = codestructuratipo;
	}

	public String getJerarquiaJerarca() {
		return jerarquiaJerarca;
	}

	public void setJerarquiaJerarca(String jerarquiaJerarca) {
		this.jerarquiaJerarca = jerarquiaJerarca;
	}
}