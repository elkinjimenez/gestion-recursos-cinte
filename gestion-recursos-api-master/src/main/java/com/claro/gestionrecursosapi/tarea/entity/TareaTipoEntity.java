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
@Table(name = "DF_TAREATIPO")
public class TareaTipoEntity {
		
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "tareatipo_Sequence")
	@SequenceGenerator(name = "tareatipo_Sequence", sequenceName = "SEQ_TAREATIPO", allocationSize = 1)
	private Integer id;
	@NotBlank(message = "Campo requerido")
	private String nombre;
	private String descripcion;
	private Integer jerarquia;
	private String color;
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
	public Integer getJerarquia() {
		return jerarquia;
	}
	public void setJerarquia(Integer jerarquia) {
		this.jerarquia = jerarquia;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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