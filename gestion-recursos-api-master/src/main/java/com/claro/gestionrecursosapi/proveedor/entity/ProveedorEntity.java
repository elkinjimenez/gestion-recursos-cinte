package com.claro.gestionrecursosapi.proveedor.entity;

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
@Table(name = "DF_PROVEEDOR")
public class ProveedorEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "proveedor_Sequence")
	@SequenceGenerator(name = "proveedor_Sequence", sequenceName = "SEQ_PROVEEDOR", allocationSize = 1)
	private Integer id;

	private String nit;

	@NotBlank(message = "Campo requerido")
	private String nombre;

	private String contratomarco;
	private String especialidad;
	private String linea;
	private byte[] logo;
	private String pais;
	private String departamento;
	private String ciudad;

	@NotBlank(message = "Campo requerido")
	private String estado;

	@CreationTimestamp
	@Column(updatable = false)
	private Date fechaCreacion;
	@UpdateTimestamp
	private Date fechaModificacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContratomarco() {
		return contratomarco;
	}

	public void setContratomarco(String contratomarco) {
		this.contratomarco = contratomarco;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechacreacion) {
		this.fechaCreacion = fechacreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechamodificacion) {
		this.fechaModificacion = fechamodificacion;
	}

}
