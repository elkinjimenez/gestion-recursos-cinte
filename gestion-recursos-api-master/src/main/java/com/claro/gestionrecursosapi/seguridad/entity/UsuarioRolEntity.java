package com.claro.gestionrecursosapi.seguridad.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "DF_USUARIOROL")
public class UsuarioRolEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "usuariorol_Sequence")
	@SequenceGenerator(name = "usuariorol_Sequence", sequenceName = "SEQ_USUARIOROL", allocationSize = 1)
	private Integer id;
	
	private Integer codusuariorol;
	
	private String nombre;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Date fechacreacion;
	@UpdateTimestamp
	private Date fechamodificacion;

	public UsuarioRolEntity() {

	}

	public UsuarioRolEntity(Integer id) {
		this.id = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setCodusuariorol(Integer codusuariorol) {
		this.codusuariorol = codusuariorol;
	}
	
	public Integer getCodusuariorol() {
		return codusuariorol;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Date getFechacreacion() {
		return fechacreacion;
	}
	
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	
	public Date getFechamodificacion() {
		return fechamodificacion;
	}
	
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}
}