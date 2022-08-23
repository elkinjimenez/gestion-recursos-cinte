package com.claro.gestionrecursosapi.empleado.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the empleadocontrol database table.
 * 
 */
@Entity
@Table(name="DF_EMPLEADOCONTROL")
public class EmpleadocontrolEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "empleadocontrol_Sequence")
    @SequenceGenerator(name = "empleadocontrol_Sequence", sequenceName = "SEQ_EMPLEADOCONTROL", allocationSize = 1)
	private Integer id;

	private String descripcion;

	@CreationTimestamp
	@Column(updatable = false)
	private Date fechacreacion;

	private Date fechahorafin;

	private Date fechahorainicio;
	
	@UpdateTimestamp
	private Date fechamodificacion;

	@Min(value = 1, message = "Campo requerido")
	private BigDecimal horas;

	@Min(value = 1, message = "Campo requerido")
	private Integer codempleado;

	@Min(value = 1, message = "Campo requerido")
	private Integer codtarea;

	//private Integer excedido;

	public EmpleadocontrolEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechahorafin() {
		return this.fechahorafin;
	}

	public void setFechahorafin(Date fechahorafin) {
		this.fechahorafin = fechahorafin;
	}

	public Date getFechahorainicio() {
		return this.fechahorainicio;
	}

	public void setFechahorainicio(Timestamp fechahorainicio) {
		this.fechahorainicio = fechahorainicio;
	}

	public Date getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public BigDecimal getHoras() {
		return this.horas;
	}

	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}

	public Integer getCodempleado() {
		return this.codempleado;
	}

	public void setempleado(Integer codempleado) {
		this.codempleado = codempleado;
	}

	public Integer getCodtarea() {
		return this.codtarea;
	}

	public void setCodtarea(Integer codtarea) {
		this.codtarea = codtarea;
	}

	/*public Integer getExcedido() {
		return excedido;
	}

	public void setExcedido(Integer excedido) {
		this.excedido = excedido;
	}*/
}