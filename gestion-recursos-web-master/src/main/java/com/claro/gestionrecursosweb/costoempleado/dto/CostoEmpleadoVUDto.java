package com.claro.gestionrecursosweb.costoempleado.dto;

import java.util.Date;

public class CostoEmpleadoVUDto {

    private Integer id;
    private Integer costoPunto;
    private Integer costoMes;
    private Float puntosMes;
    private Float factorPunto;
    private String modalidad;
    private Integer codEmpleado;
    private Date desde;
    private Date hasta;
    private String nombre1;
    private String apellido1;
    private String usuarioclaro;

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

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getUsuarioclaro() {
        return usuarioclaro;
    }

    public void setUsuarioclaro(String usuarioclaro) {
        this.usuarioclaro = usuarioclaro;
    }
}
