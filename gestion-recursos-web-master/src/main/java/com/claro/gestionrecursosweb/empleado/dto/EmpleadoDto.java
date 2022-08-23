package com.claro.gestionrecursosweb.empleado.dto;

import java.util.Date;

public class EmpleadoDto {

	private Integer id;	
	private Integer codpersona;
	private Integer codperfil;
	private Integer codperfiltipo;
	private Integer codlineaproducto;
	private Integer codproveedor;
	private Integer codperfilnivel;
	private String usuarioclaro;
	private String usuarioclaroedicion;
	private Date fechainicio;
	private Date fechafin;
	private Integer codestructuraorganizacional;
	private Date fechacreacion;
	private Date fechamodificacion;
	private Integer rol;
	private Integer codModalidad;
	private Integer valor;
	private Integer valormes;
	private Integer costoPunto;
	private Integer costoMes;
	private Float puntoMes;
	private Float factorPunto;
	private String modalidad;
	private Integer idCostoEmpleado;
	private Date fechaCostoEmpleado;
	private String celula;
	private boolean crearCostoEmpleado;


	public EmpleadoDto() {
	}

	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public Integer getValormes() {
		return valormes;
	}
	public void setValormes(Integer valormes) {
		this.valormes = valormes;
	}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodpersona() {
		return codpersona;
	}
	public void setCodpersona(Integer codpersona) {
		this.codpersona = codpersona;
	}
	public Integer getCodperfil() {
		return codperfil;
	}
	public void setCodperfil(Integer codperfil) {
		this.codperfil = codperfil;
	}
	public Integer getCodperfiltipo() {
		return codperfiltipo;
	}
	public void setCodperfiltipo(Integer codperfiltipo) {
		this.codperfiltipo = codperfiltipo;
	}
	public Integer getCodlineaproducto() {
		return codlineaproducto;
	}
	public void setCodlineaproducto(Integer codlineaproducto) {
		this.codlineaproducto = codlineaproducto;
	}
	public Integer getCodproveedor() {
		return codproveedor;
	}
	public void setCodproveedor(Integer codproveedor) {
		this.codproveedor = codproveedor;
	}
	public Integer getCodperfilnivel() {
		return codperfilnivel;
	}
	public void setCodperfilnivel(Integer codperfilnivel) {
		this.codperfilnivel = codperfilnivel;
	}
	public String getUsuarioclaro() {
		return usuarioclaro;
	}
	public void setUsuarioclaro(String usuarioclaro) {
		this.usuarioclaro = usuarioclaro;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public Integer getCodestructuraorganizacional() {
		return codestructuraorganizacional;
	}
	public void setCodestructuraorganizacional(Integer codestructuraorganizacional) {
		this.codestructuraorganizacional = codestructuraorganizacional;
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

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public Integer getCodModalidad() {
		return codModalidad;
	}

	public void setCodModalidad(Integer codModalidad) {
		this.codModalidad = codModalidad;
	}

	public String getUsuarioclaroedicion() {
		return usuarioclaroedicion;
	}

	public void setUsuarioclaroedicion(String usuarioclaroedicion) {
		this.usuarioclaroedicion = usuarioclaroedicion;
	}

	public Integer getCostoPunto() {
		return costoPunto;
	}

	public void setCostoPunto(Integer costoPunto) {
		this.costoPunto = costoPunto;
	}

	public Integer getCostoMes() {
		return costoMes;
	}

	public void setCostoMes(Integer costoMes) {
		this.costoMes = costoMes;
	}

	public Float getPuntoMes() {
		return puntoMes;
	}

	public void setPuntoMes(Float puntoMes) {
		this.puntoMes = puntoMes;
	}

	public Float getFactorPunto() {
		return factorPunto;
	}

	public void setFactorPunto(Float factorPunto) {
		this.factorPunto = factorPunto;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Integer getIdCostoEmpleado() {
		return idCostoEmpleado;
	}

	public void setIdCostoEmpleado(Integer idCostoEmpleado) {
		this.idCostoEmpleado = idCostoEmpleado;
	}

	public Date getFechaCostoEmpleado() {
		return fechaCostoEmpleado;
	}

	public void setFechaCostoEmpleado(Date fechaCostoEmpleado) {
		this.fechaCostoEmpleado = fechaCostoEmpleado;
	}

	public String getCelula() {
		return celula;
	}

	public void setCelula(String celula) {
		this.celula = celula;
	}

	public boolean isCrearCostoEmpleado() {
		return crearCostoEmpleado;
	}

	public void setCrearCostoEmpleado(boolean crearCostoEmpleado) {
		this.crearCostoEmpleado = crearCostoEmpleado;
	}
}