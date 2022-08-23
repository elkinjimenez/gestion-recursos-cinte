package com.claro.gestionrecursosweb.cambios.dto;

import java.util.Date;
import java.util.Objects;

public class CambiosDto {

    private Integer id;

    private String codCambio;

    private Date fechaTcba;

    private Date fechaPap;

    private String gestorCodEmpleado;

    private String resumen;

    private String idRlp;

    private String tipoLinea;

    private String estado;

    private Integer idCompromiso;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodCambio() {
        return this.codCambio;
    }

    public void setCodCambio(String codCambio) {
        this.codCambio = codCambio;
    }

    public Date getFechaTcba() {
        return this.fechaTcba;
    }

    public void setFechaTcba(Date fechaTcba) {
        this.fechaTcba = fechaTcba;
    }

    public Date getFechaPap() {
        return this.fechaPap;
    }

    public void setFechaPap(Date fechaPap) {
        this.fechaPap = fechaPap;
    }

    public String getGestorCodEmpleado() {
        return this.gestorCodEmpleado;
    }

    public void setGestorCodEmpleado(String gestorCodEmpleado) {
        this.gestorCodEmpleado = gestorCodEmpleado;
    }

    public String getResumen() {
        return this.resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getIdRlp() {
        return this.idRlp;
    }

    public void setIdRlp(String idRlp) {
        this.idRlp = idRlp;
    }

    public String getTipoLinea() {
        return this.tipoLinea;
    }

    public void setTipoLinea(String tipoLinea) {
        this.tipoLinea = tipoLinea;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdCompromiso() {
        return this.idCompromiso;
    }

    public void setIdCompromiso(Integer idCompromiso) {
        this.idCompromiso = idCompromiso;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CambiosDto)) {
            return false;
        }
        CambiosDto cambios = (CambiosDto) o;
        return Objects.equals(id, cambios.id) && Objects.equals(codCambio, cambios.codCambio) && Objects.equals(fechaTcba, cambios.fechaTcba) && Objects.equals(fechaPap, cambios.fechaPap) && Objects.equals(gestorCodEmpleado, cambios.gestorCodEmpleado) && Objects.equals(resumen, cambios.resumen) && Objects.equals(idRlp, cambios.idRlp) && Objects.equals(tipoLinea, cambios.tipoLinea) && Objects.equals(estado, cambios.estado) && Objects.equals(idCompromiso, cambios.idCompromiso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codCambio, fechaTcba, fechaPap, gestorCodEmpleado, resumen, idRlp, tipoLinea, estado, idCompromiso);
    }

}
