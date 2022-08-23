package com.claro.gestionrecursosapi.perfil.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;


/**
 * The persistent class for the perfiltipo_perfil database table.
 * 
 */
@Entity
@Table(name="DF_PERFILTIPO_PERFIL")
public class PerfiltipoPerfilEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "perfiltipo_perfil_Sequence")
	@SequenceGenerator(name = "perfiltipo_perfil_Sequence", sequenceName = "SEQ_PERFILTIPO_PERFIL", allocationSize = 1)
	private int id;

	@Min(value = 1, message = "Campo requerido")
	private Integer codperfil;

	@Min(value = 1, message = "Campo requerido")
	private Integer codperfiltipo;

	public PerfiltipoPerfilEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCodperfil() {
		return codperfil;
	}

	public void setCodperfil(Integer codperfil) {
		this.codperfil = codperfil;
	}

	public Integer getCodperfiltipo() {
		return codperfiltipo;
	}

	public void setCodperfiltipo(Integer codperfiltipo) {
		this.codperfiltipo = codperfiltipo;
	}
}