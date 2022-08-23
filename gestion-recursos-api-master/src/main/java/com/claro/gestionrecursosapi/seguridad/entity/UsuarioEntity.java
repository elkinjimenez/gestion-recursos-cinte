package com.claro.gestionrecursosapi.seguridad.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="DF_USUARIO")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "usuario_Sequence")
	@SequenceGenerator(name = "usuario_Sequence", sequenceName = "SEQ_USUARIO", allocationSize = 1)
	private Integer id;
	@Min(value = 1, message = "Campo requerido")
	private Integer codusuariorol;
	@Min(value = 1, message = "Campo requerido")
	@Column(updatable = false)
	private Integer codpersona;
	@NotBlank(message = "Campo requerido")
	@Column(name="usuario")
	private String usuario;
	@NotBlank(message = "Campo requerido")
	private String nombre;
	@NotBlank(message = "Campo requerido")
	private String clave;
	@NotBlank(message = "Campo requerido")
	private String estado;	
	@CreationTimestamp
	@Column(updatable = false)
	private Date fechacreacion;
	@UpdateTimestamp
	private Date fechamodificacion;
	@OneToMany(mappedBy = "usuarioEntity")
	private List<UsuarioRolesEntity> lUsuarioRoles;

	public UsuarioEntity() {

	}

	public UsuarioEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodusuariorol() {
		return this.codusuariorol;
	}
	public void setCodusuariorol(Integer codusuariorol) {
		this.codusuariorol = codusuariorol;
	}
	public Integer getCodpersona() {
		return this.codpersona;
	}
	public void setCodpersona(Integer codpersona) {
		this.codpersona = codpersona;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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

	public List<UsuarioRolesEntity> getlUsuarioRoles() {
		return lUsuarioRoles;
	}

	public void setlUsuarioRoles(List<UsuarioRolesEntity> lUsuarioRoles) {
		this.lUsuarioRoles = lUsuarioRoles;
	}
}
