package com.claro.gestionrecursosweb.persona.dto;

import java.util.Date;

public class PersonaDto {

	private Integer id;	
	private Integer codtipodocumento;	
	private Long numerodocumento;	
	private String nombre1;	
	private String nombre2;	
	private String apellido1;	
	private String apellido2;
	private String telefono;
	private String correo;
	private String direccionresidencia;	
	private String genero;	
	private Date fechanacimiento;	
	private Date fechacreacion;
	private Date fechamodificacion;
	
	private Integer codterceroeps;
	private String ubicacion;
	private String celular;
	private String gruposanguineo;
	private String contactoemergencianombre;
	private String contactoemergenciatelefono;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCodtipodocumento() {
		return codtipodocumento;
	}
	
	public void setCodtipodocumento(int codtipodocumento) {
		this.codtipodocumento = codtipodocumento;
	}
	
	public Long getNumerodocumento() {
		return numerodocumento;
	}
	
	public void setNumerodocumento(Long numerodocumento) {
		this.numerodocumento = numerodocumento;
	}
	
	public String getNombre1() {
		return nombre1;
	}
	
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	
	public String getNombre2() {
		return nombre2;
	}
	
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	
	public String getApellido1() {
		return apellido1;
	}
	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return apellido2;
	}
	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getDireccionresidencia() {
		return direccionresidencia;
	}
	
	public void setDireccionresidencia(String direccionresidencia) {
		this.direccionresidencia = direccionresidencia;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
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

	public Integer getCodterceroeps() {
		return codterceroeps;
	}

	public void setCodterceroeps(Integer codterceroeps) {
		this.codterceroeps = codterceroeps;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getGruposanguineo() {
		return gruposanguineo;
	}

	public void setGruposanguineo(String gruposanguineo) {
		this.gruposanguineo = gruposanguineo;
	}

	public String getContactoemergencianombre() {
		return contactoemergencianombre;
	}

	public void setContactoemergencianombre(String contactoemergencianombre) {
		this.contactoemergencianombre = contactoemergencianombre;
	}

	public String getContactoemergenciatelefono() {
		return contactoemergenciatelefono;
	}

	public void setContactoemergenciatelefono(String contactoemergenciatelefono) {
		this.contactoemergenciatelefono = contactoemergenciatelefono;
	}

	public void setCodtipodocumento(Integer codtipodocumento) {
		this.codtipodocumento = codtipodocumento;
	}
	
}
