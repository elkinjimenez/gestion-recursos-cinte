package com.claro.gestionrecursosapi.estimaciones.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DF_COSTO_ESTIMACION")
public class CostoEstimacion {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "costoEstimacion_Sequence")
    @SequenceGenerator(name = "costoEstimacion_Sequence", sequenceName = "SEQ_COSTOESTIMACION", allocationSize = 1)
    private int id;

    @Column(name = "PERFIL")
    private String perfil;
    @Column(name = "COSTO")
    private Integer costo;
    @Column(name = "FECHAINICIO")
    private Date fechaInicio;
    @Column(name = "FECHAFIN")
    private Date fechaFin;


    public CostoEstimacion() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
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
}