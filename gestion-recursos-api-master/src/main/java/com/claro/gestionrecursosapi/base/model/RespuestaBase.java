package com.claro.gestionrecursosapi.base.model;

public class RespuestaBase {
	
	private int codigoEstatus;
	private String mensaje;
	
	public RespuestaBase(int codigoEstatus, String mensaje) {
		super();
		this.codigoEstatus = codigoEstatus;
		this.mensaje = mensaje;
	}
	
	public RespuestaBase() {
		super();
	}



	public int getCodigoEstatus() {
		return codigoEstatus;
	}
	public void setCodigoEstatus(int codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
