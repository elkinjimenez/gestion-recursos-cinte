package com.claro.gestionrecursosapi.hallazgo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DF_Reporta_Hallazgo")
public class HallazgoEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Hallazgo_Sequence")
	@SequenceGenerator(name = "Hallazgo_Sequence", sequenceName = "DF_Reporta_Hallazgo_SEQ", allocationSize = 1)
	@Column(name = "ID_Reporta_Hallazgo")
	private Integer id;
	@Column(name = "Cedula")
	private String cedula;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Celular")
	private String celular;
	@Column(name = "Gerencia")
	private String gerencia;
	@Column(name = "Correo")
	private String correo;
	@Column(name = "ID_Compromiso")
	private Integer idCompromiso;
	/*@Column(name = "Entregable")
	private String entregable;*/
	@Column(name = "Tipo_Hallazgo")
	private String tipo;
	@Column(name = "Criticidad")
	private String criticidad;
	@Column(name = "SLA")
	private Integer sla;
	@Column(name = "Detalle_Hallazgo")
	private String detalle;
	@Column(name = "Evidencia_Hallazgo")
	private String evidencia;
	@Column(name = "Fecha_Hallazgo")
	private Date fechaHallazgo;
	@Column(name = "Es_Hallazgo")
	private boolean esHallazgo;
	@Column(name = "Problema_Identificado")
	private String problemaIdentificado;
	@Column(name = "Solucion_Entregada")
	private String solucionEntregada;
	@Column(name = "Detalle_Respuesta")
	private String detalleRespuesta;
	@Column(name = "Evidencia_Respuesta")
	private String evidenciaRespuesta;
	@Column(name = "Fecha_Respuesta")
	private Date fechaRespuesta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getGerencia() {
		return gerencia;
	}

	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getIdCompromiso() {
		return idCompromiso;
	}

	public void setIdCompromiso(Integer idCompromiso) {
		this.idCompromiso = idCompromiso;
	}
/*
	public String getEntregable() {
		return entregable;
	}

	public void setEntregable(String entregable) {
		this.entregable = entregable;
	}
*/
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCriticidad() {
		return criticidad;
	}

	public void setCriticidad(String criticidad) {
		this.criticidad = criticidad;
	}

	public Integer getSla() {
		return sla;
	}

	public void setSla(Integer sla) {
		this.sla = sla;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(String evidencia) {
		this.evidencia = evidencia;
	}

	public Date getFechaHallazgo() {
		return fechaHallazgo;
	}

	public void setFechaHallazgo(Date fechaHallazgo) {
		this.fechaHallazgo = fechaHallazgo;
	}

	public boolean getEsHallazgo() {
		return esHallazgo;
	}

	public void setEsHallazgo(boolean esHallazgo) {
		this.esHallazgo = esHallazgo;
	}

	public String getProblemaIdentificado() {
		return problemaIdentificado;
	}

	public void setProblemaIdentificado(String problemaIdentificado) {
		this.problemaIdentificado = problemaIdentificado;
	}

	public String getSolucionEntregada() {
		return solucionEntregada;
	}

	public void setSolucionEntregada(String solucionEntregada) {
		this.solucionEntregada = solucionEntregada;
	}

	public String getDetalleRespuesta() {
		return detalleRespuesta;
	}

	public void setDetalleRespuesta(String detalleRespuesta) {
		this.detalleRespuesta = detalleRespuesta;
	}

	public String getEvidenciaRespuesta() {
		return evidenciaRespuesta;
	}

	public void setEvidenciaRespuesta(String evidenciaRespuesta) {
		this.evidenciaRespuesta = evidenciaRespuesta;
	}

	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}
}
