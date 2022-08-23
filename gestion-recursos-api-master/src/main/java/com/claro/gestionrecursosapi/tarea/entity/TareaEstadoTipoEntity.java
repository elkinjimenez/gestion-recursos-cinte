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
@Table(name = "DF_TAREAESTADOTIPO")
public class TareaEstadoTipoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "tareaestadotipo_Sequence")
	@SequenceGenerator(name = "tareaestadotipo_Sequence", sequenceName = "SEQ_TAREAESTADOTIPO", allocationSize = 1)
	private Integer id;
	@NotBlank(message = "Campo requerido")
	private String codigotipo;
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
	public String getCodigotipo() {
		return codigotipo;
	}
	public void setCodigotipo(String codigotipo) {
		this.codigotipo = codigotipo;
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
