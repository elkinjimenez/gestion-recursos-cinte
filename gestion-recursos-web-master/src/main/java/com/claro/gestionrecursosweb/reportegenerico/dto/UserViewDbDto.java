package com.claro.gestionrecursosweb.reportegenerico.dto;

import java.util.Date;

public class UserViewDbDto {

    private int id;
    private String nombreVista;
    private Date fechacreacion;
    private Date fechamodificacion;
    private int codusuariorol;
    private String estado;
	private String vista;


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreVista() {
        return nombreVista;
    }

    public void setNombreVista(String nombreVista) {
        this.nombreVista = nombreVista;
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

    public int getCodusuariorol() {
        return codusuariorol;
    }

    public void setCodusuariorol(int codusuariorol) {
        this.codusuariorol = codusuariorol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	public String getVista() {
		return vista;
	}

	public void setVista(String vista) {
		this.vista = vista;
	}

}
