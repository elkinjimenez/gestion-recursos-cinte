package com.claro.gestionrecursosapi.tarea.entity;

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

@Entity
@Table(name = "DF_TAREAESTADO")
public class TareaEstadoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "tareaestado_Sequence")
	@SequenceGenerator(name = "tareaestado_Sequence", sequenceName = "SEQ_TAREAESTADO", allocationSize = 1)
	private Integer id;
	private Integer codtareaestadotipo;
	@NotBlank(message = "Campo requerido")
	private String nombre;
	private String descripcion;
	@CreationTimestamp
	@Column(updatable = false)
	private Date fechacreacion;
	@UpdateTimestamp
	private Date fechamodificacion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodtareaestadotipo() {
		return codtareaestadotipo;
	}
	public void setCodtareaestadotipo(Integer codtareaestadotipo) {
		this.codtareaestadotipo = codtareaestadotipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
