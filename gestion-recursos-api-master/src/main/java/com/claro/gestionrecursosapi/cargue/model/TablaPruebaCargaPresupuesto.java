package com.claro.gestionrecursosapi.cargue.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

//@Entity
//@Table(name = "prueba_presupuesto_carga")
public class TablaPruebaCargaPresupuesto {

    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPruebaPresupuestoCarga")
    //@SequenceGenerator(name = "seqPruebaPresupuestoCarga", sequenceName = "SEQ_PRUEBA_PRESUPUESTO_CARGA", initialValue = 1, allocationSize = 1)
    private Long id;

    //@Column(name = "nombre_gerente")
    private String nombreGerente;
    private String agrcapex;
    private String idProyecto;
    //@Column(name = "nombre_amx")
    private String nombreAMX;
    //@Column(name = "nombre_local")
    private String nombreLocal;
    //@Column(name = "elemento_pep")
    private String elementoPEP;
    //@Column(name = "tipo_proyecto")
    private String tipoProyecto;
    //@Column(name = "presupuesto_usd")
    private String presupuestoUSD;
    //@Column(name = "presupuesto_cop")
    private String presupuestoCOP;
    private String usd;
    private String cop;
    private String observacion;
    //@Column(name = "fecha_creacion")
    private Date fechaCreacion;

    public TablaPruebaCargaPresupuesto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreGerente() {
        return nombreGerente;
    }

    public void setNombreGerente(String nombreGerente) {
        this.nombreGerente = nombreGerente;
    }

    public String getAgrcapex() {
        return agrcapex;
    }

    public void setAgrcapex(String agrcapex) {
        this.agrcapex = agrcapex;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreAMX() {
        return nombreAMX;
    }

    public void setNombreAMX(String nombreAMX) {
        this.nombreAMX = nombreAMX;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getElementoPEP() {
        return elementoPEP;
    }

    public void setElementoPEP(String elementoPEP) {
        this.elementoPEP = elementoPEP;
    }

    public String getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(String tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    public String getPresupuestoUSD() {
        return presupuestoUSD;
    }

    public void setPresupuestoUSD(String presupuestoUSD) {
        this.presupuestoUSD = presupuestoUSD;
    }

    public String getPresupuestoCOP() {
        return presupuestoCOP;
    }

    public void setPresupuestoCOP(String presupuestoCOP) {
        this.presupuestoCOP = presupuestoCOP;
    }

    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public String getCop() {
        return cop;
    }

    public void setCop(String cop) {
        this.cop = cop;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
