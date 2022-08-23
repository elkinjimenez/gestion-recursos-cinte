package com.claro.gestionrecursosapi.estimaciones.dto;

import java.util.Date;

public class EstimacionDownloadDTO {
    private String entregable;
    private String perfil;
    private String prerequisito;
    private Integer cantidad;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer costo;
    private Integer porcentaje;


    public EstimacionDownloadDTO(String entregable, String perfil, String prerequisito, Integer cantidad, Date fechaInicio, Date fechaFin, Integer costo, Integer porcentaje) {
        this.entregable = entregable;
        this.perfil = perfil;
        this.prerequisito = prerequisito;
        this.cantidad = cantidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
        this.porcentaje = porcentaje;
    }


    public String getEntregable() {
        return entregable;
    }

    public void setEntregable(String entregable) {
        this.entregable = entregable;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPrerequisito() {
        return prerequisito;
    }

    public void setPrerequisito(String prerequisito) {
        this.prerequisito = prerequisito;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }
}