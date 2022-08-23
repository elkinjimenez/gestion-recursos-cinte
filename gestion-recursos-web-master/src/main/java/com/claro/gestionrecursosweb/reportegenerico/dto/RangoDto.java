package com.claro.gestionrecursosweb.reportegenerico.dto;

import java.util.Date;

public class RangoDto {

    private String guid;
    private Date fechainicio;
    private Date fechafin;
    private Integer codEstructura;
    private Integer ger;


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public Integer getCodEstructura() {
        return codEstructura;
    }

    public void setCodEstructura(Integer codEstructura) {
        this.codEstructura = codEstructura;
    }

    public Integer getGer() {
        return ger;
    }

    public void setGer(Integer ger) {
        this.ger = ger;
    }
}