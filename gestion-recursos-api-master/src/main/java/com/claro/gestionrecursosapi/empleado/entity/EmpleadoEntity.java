package com.claro.gestionrecursosapi.empleado.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@Table(name="DF_EMPLEADO")
public class EmpleadoEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "empleado_Sequence")
    @SequenceGenerator(name = "empleado_Sequence", sequenceName = "SEQ_EMPLEADO", allocationSize = 1)
	private Integer id;	
	private Integer codpersona;
	private Integer codperfil;
	private Integer codperfiltipo;
	private Integer codlineaproducto;
	private Integer codproveedor;
	private Integer codperfilnivel;
	private String usuarioclaro;
	@Transient
	private String usuarioclaroedicion;
	private Integer codestructuraorganizacional;
	private Date fechainicio;
	private Date fechafin;
	@CreationTimestamp
	@Column(updatable = false)
	private Date fechacreacion;	
	@UpdateTimestamp
	private Date fechamodificacion;
	@Transient
	private Integer rol;
	private Integer codModalidad;
	
	@Column(name = "CHORA")
	private Integer valor;
	@Column(name = "CMES")
	private Integer valormes;

	@Column(name = "CELULA")
	private String celula;

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

	public String getCelula() {
		return celula;
	}

	public void setCelula(String celula) {
		this.celula = celula;
	}

}