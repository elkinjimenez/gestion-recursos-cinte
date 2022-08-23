package com.claro.gestionrecursosapi.estimaciones.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="DF_ESTIMACION")
public class EstimacionAdmin {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "estimacion_Sequence")
    @SequenceGenerator(name = "estimacion_Sequence", sequenceName = "SEQ_ESTIMACION", allocationSize = 1)
    private Integer id;
    @Column(name = "ENTREGABLE")
    private String entregable;
    @Column(name = "PERFIL")
    private Integer perfil;
    @Column(name = "PREREQUISITO")
    private String preRequisito;
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Column(name = "FECHAINICIO")
    private Date fechaInicio;
    @Column(name = "FECHAFIN")
    private Date fechaFin;
    @Column(name = "COSTO")
    private int costo;
    @Column(name = "PORCENTAJE")
    private int porcentaje;
    @Column(name = "CODPROYECTO")
    private int codProyecto;

    @Column(name = "URLSHAREPOINT")
    private String urlsharepoint;


    public EstimacionAdmin() {

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

    public String geturlsharepoint() {
        return urlsharepoint;
    }

    public void seturlsharepoint(String urlsharepoint) {
        this.urlsharepoint = urlsharepoint;
    }
}