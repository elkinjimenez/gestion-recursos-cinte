package com.claro.gestionrecursosweb.costoempleado.dto;

import java.util.Date;

public class CostoEmpleadoDto {

    private Integer id;
    private Integer costoPunto;
    private Integer costoMes;
    private Float puntosMes;
    private Float factorPunto;
    private String modalidad;
    private Integer codEmpleado;
    private Date desde;
    private Date hasta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Float getPuntosMes() {
        return puntosMes;
    }

    public void setPuntosMes(Float puntosMes) {
        this.puntosMes = puntosMes;
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

    public Integer getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(Integer codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
}
