package com.claro.gestionrecursosweb.seguridad.dto;

import java.util.Date;
import java.util.List;

public class UsuarioDto {

	private Integer id;
	private Integer codusuariorol;
	private Integer codpersona;
	private String usuario;
	private String nombre;
	private String clave;
	private String estado;
	private Date fechacreacion;
	private Date fechamodificacion;
	private List<UsuarioRolesDto> lUsuarioRoles;
	
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
	public String getEstado() { return estado; }
	public void setEstado(String estado) { this.estado = estado; }
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

	public List<UsuarioRolesDto> getlUsuarioRoles() {
		return lUsuarioRoles;
	}

	public void setlUsuarioRoles(List<UsuarioRolesDto> lUsuarioRoles) {
		this.lUsuarioRoles = lUsuarioRoles;
	}
}
