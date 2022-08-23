package com.claro.gestionrecursosapi.perfil.entity;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the perfil_rol_homologacion database table.
 * 
 */
@Entity
@Table(name="DF_PERFIL_ROL_HOMOLOGACION")
public class PerfilRolHomologacionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "perfil_rol_homologacion_Sequence")
	@SequenceGenerator(name = "perfil_rol_homologacion_Sequence", sequenceName = "SEQ_PERFIL_ROL_HOMOLOGACION", allocationSize = 1)
	private int id;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp fechacreacion;

	@UpdateTimestamp
	private Timestamp fechamodificacion;

	@Min(value = 1, message = "Campo requerido")
	private Integer codperfil;

	@Min(value = 1, message = "Campo requerido")
	private Integer codrol;

	public PerfilRolHomologacionEntity() {
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

	public Integer getCodperfil() {
		return codperfil;
	}

	public void setCodperfil(Integer codperfil) {
		this.codperfil = codperfil;
	}

	public Integer getCodrol() {
		return codrol;
	}

	public void setCodrol(Integer codrol) {
		this.codrol = codrol;
	}
}