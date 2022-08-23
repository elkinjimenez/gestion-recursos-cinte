package com.claro.gestionrecursosapi.reporte.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DF_EMPLEADO_VU")
public class ReporteEmpleados {

	@Id
	private Long id;
	@Column(name = "CODEMPLEADO")
	private Long codEmpleado;
	@Column(name = "CODPERSONA")
	private Long codPersona;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "CODTIPODOCUMENTO")
	private int codTipoDocumento;
	@Column(name = "TIPODOCUMENTO")
	private String tipoDocumento;
	@Column(name = "NUMERODOCUMENTO")
	private Long numeroDocumento;
	@Column(name = "NOMBRE1")
	private String primerNombre;
	@Column(name = "NOMBRE2")
	private String segundoNombre;
	@Column(name = "APELLIDO1")
	private String primerApellido;
	@Column(name = "APELLIDO2")
	private String segungoApellido;
	@Column(name = "TELEFONO")
	private String telefono;
	@Column(name = "CORREO")
	private String correo;
	@Column(name = "DIRECCIONRESIDENCIA")
	private String direccionResidencia;
	@Column(name = "GENERO")
	private String tipoGenero;
	@Column(name = "GENERONOMBRE")
	private String genero;
	@Column(name = "FECHANACIMIENTO")
	private Date fechaNacimiento;
	@Column(name = "CODPERFIL")
	private Long codPerfil;
	@Column(name = "PERFIL")
	private String perfil;
	@Column(name = "CODPERFILTIPO")
	private Long codPerfilTipo;
	@Column(name = "PERFILTIPO")
	private String perfilTipo;
	@Column(name = "CODLINEAPRODUCTO")
	private Long codLineaProducto;
	@Column(name = "LINEAPRODUCTO")
	private String lineaProducto;
	@Column(name = "CODPROVEEDOR")
	private Long codProveedor;
	@Column(name = "PROVEEDOR")
	private String proveedor;
	@Column(name = "CODPERFILNIVEL")
	private Long codPerfilNivel;
	@Column(name = "PERFILNIVEL")
	private String perfilNivel;
	@Column(name = "USUARIOCLARO")
	private String usuarioClaro;
	@Column(name = "FECHAINICIO")
	private Date fechaInicio;
	@Column(name = "FECHAFIN")
	private Date fechaFin;
	@Column(name = "CODESTRUCTURAORGANIZACIONAL")
	private Long codEstructuraOrganizacional;
	@Column(name = "ESTRUCTURAORGANIZACIONAL")
	private String estructuraOrganizacional;
	@Column(name = "ROL")
	private Long rol;
	@Column(name = "CODMODALIDAD")
	private Long codModalidad;
	@Column(name = "CELULA")
	private String celula;
	@Column(name = "VALOR")
	private Long valor;

	public ReporteEmpleados() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmpleado(Long codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

	public Long getCodPersona() {
		return codPersona;
	}

	public void setCodPersona(Long codPersona) {
		this.codPersona = codPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodTipoDocumento() {
		return codTipoDocumento;
	}

	public void setCodTipoDocumento(int codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegungoApellido() {
		return segungoApellido;
	}

	public void setSegungoApellido(String segungoApellido) {
		this.segungoApellido = segungoApellido;
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

	public String getDireccionResidencia() {
		return direccionResidencia;
	}

	public void setDireccionResidencia(String direccionResidencia) {
		this.direccionResidencia = direccionResidencia;
	}

	public String getTipoGenero() {
		return tipoGenero;
	}

	public void setTipoGenero(String tipoGenero) {
		this.tipoGenero = tipoGenero;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(Long codPerfil) {
		this.codPerfil = codPerfil;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Long getCodPerfilTipo() {
		return codPerfilTipo;
	}

	public void setCodPerfilTipo(Long codPerfilTipo) {
		this.codPerfilTipo = codPerfilTipo;
	}

	public String getPerfilTipo() {
		return perfilTipo;
	}

	public void setPerfilTipo(String perfilTipo) {
		this.perfilTipo = perfilTipo;
	}

	public Long getCodLineaProducto() {
		return codLineaProducto;
	}

	public void setCodLineaProducto(Long codLineaProducto) {
		this.codLineaProducto = codLineaProducto;
	}

	public String getLineaProducto() {
		return lineaProducto;
	}

	public void setLineaProducto(String lineaProducto) {
		this.lineaProducto = lineaProducto;
	}

	public Long getCodProveedor() {
		return codProveedor;
	}

	public void setCodProveedor(Long codProveedor) {
		this.codProveedor = codProveedor;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public Long getCodPerfilNivel() {
		return codPerfilNivel;
	}

	public void setCodPerfilNivel(Long codPerfilNivel) {
		this.codPerfilNivel = codPerfilNivel;
	}

	public String getPerfilNivel() {
		return perfilNivel;
	}

	public void setPerfilNivel(String perfilNivel) {
		this.perfilNivel = perfilNivel;
	}

	public String getUsuarioClaro() {
		return usuarioClaro;
	}

	public void setUsuarioClaro(String usuarioClaro) {
		this.usuarioClaro = usuarioClaro;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Long getCodEstructuraOrganizacional() {
		return codEstructuraOrganizacional;
	}

	public void setCodEstructuraOrganizacional(Long codEstructuraOrganizacional) {
		this.codEstructuraOrganizacional = codEstructuraOrganizacional;
	}

	public String getEstructuraOrganizacional() {
		return estructuraOrganizacional;
	}

	public void setEstructuraOrganizacional(String estructuraOrganizacional) {
		this.estructuraOrganizacional = estructuraOrganizacional;
	}

	public Long getRol() {
		return rol;
	}

	public void setRol(Long rol) {
		this.rol = rol;
	}

	public Long getCodModalidad() {
		return codModalidad;
	}

	public void setCodModalidad(Long codModalidad) {
		this.codModalidad = codModalidad;
	}

	public String getCelula() {
		return celula;
	}

	public void setCelula(String celula) {
		this.celula = celula;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

}
