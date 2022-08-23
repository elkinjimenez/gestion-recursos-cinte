package com.claro.gestionrecursosweb.estimaciones.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EstimacionAdminDTO {
    private Integer id;
    private String entregable;
    private Integer perfil;
    private String preRequisito;
    private int cantidad;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date fechaInicio;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date fechaFin;
    private int costo;
    private int porcentaje;
    private int codProyecto;
    private String urlsharepoint;
    

    public EstimacionAdminDTO() {
        this.fechaInicio = new Date();
        this.fechaInicio = new Date();
    }



    public EstimacionAdminDTO(Integer id, String entregable, Integer perfil, String preRequisito, int cantidad, Date fechaInicio, Date fechaFin, int costo, int porcentaje, int codProyecto, String urlsharepoint) {
        this.id = id;
        this.entregable = entregable;
        this.perfil = perfil;
        this.preRequisito = preRequisito;
        this.cantidad = cantidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
        this.porcentaje = porcentaje;
        this.codProyecto = codProyecto;
        this.urlsharepoint = urlsharepoint;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntregable() {
        return entregable;
    }

    public void setEntregable(String entregable) {
        this.entregable = entregable;
    }

    public Integer getPerfil() {
        return perfil;
    }

    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }

    public String getPreRequisito() {
        return preRequisito;
    }

    public void setPreRequisito(String preRequisito) {
        this.preRequisito = preRequisito;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
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

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(int codProyecto) {
        this.codProyecto = codProyecto;
    }


    public String getUrlsharepoint() {
        return this.urlsharepoint;
    }

    public void setUrlsharepoint(String urlsharepoint) {
        this.urlsharepoint = urlsharepoint;
    }

}
