package com.claro.gestionrecursosweb.hallazgo.dto;

import com.claro.gestionrecursosweb.compromisosfabrica.dto.CompromisosFabricaDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class HallazgoDto {
	private Integer id;
	private String cedula;
	private String nombre;
	private String celular;
	private String gerencia;
	private String correo;
	private Integer idCompromiso;
	private String criticidad;
	private String tipo;
	private Integer sla;
	private String detalle;
	private String evidencia;
	private Date fechaHallazgo;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaHallazgoLocal;
	private boolean esHallazgo;
	private String problemaIdentificado;
	private String solucionEntregada;
	private String detalleRespuesta;
	private String evidenciaRespuesta;
	private Date fechaRespuesta;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaRespuestaLocal;


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

	public String getCriticidad() {
		return criticidad;
	}

	public void setCriticidad(String criticidad) {
		this.criticidad = criticidad;
	}
/*
	public String getEntregable() {
		return entregable;
	}

	public void setEntregable(String entregable) {
		this.entregable = entregable;
	}*/

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public LocalDateTime getFechaHallazgoLocal() {
		return fechaHallazgoLocal;
	}

	public void setFechaHallazgoLocal(LocalDateTime fechaHallazgoLocal) {
		this.fechaHallazgoLocal = fechaHallazgoLocal;
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

	public LocalDateTime getFechaRespuestaLocal() {
		return fechaRespuestaLocal;
	}

	public void setFechaRespuestaLocal(LocalDateTime fechaRespuestaLocal) {
		this.fechaRespuestaLocal = fechaRespuestaLocal;
	}
}
