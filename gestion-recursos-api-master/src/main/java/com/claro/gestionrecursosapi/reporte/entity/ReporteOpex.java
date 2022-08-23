package com.claro.gestionrecursosapi.reporte.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ReporteOpex {

    @Id
    private Long id;
    private String proyecto;
    private String proveedor;
    private String perfil;
    private Integer modalidad;
    private String recurso;
    @Column(name = "PRORRATEO_POR_RECURSO")
    private BigDecimal prorrateoPorRecurso;
    @Column(name = "HORAS_DIRECTAS")
    private BigDecimal horasDirectas;
    @Column(name = "HORAS_EN_ESTRUCTURA")
    private BigDecimal horasEnEstructura;
    @Column(name = "HORAS_OPEX")
    private BigDecimal horasOpex;
    @Column(name = "COBRO_PROYECTO")
    private BigDecimal cobroProyecto;

    public ReporteOpex() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Integer getModalidad() {
        return modalidad;
    }

    public void setModalidad(Integer modalidad) {
        this.modalidad = modalidad;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public BigDecimal getProrrateoPorRecurso() {
        return prorrateoPorRecurso;
    }

    public void setProrrateoPorRecurso(BigDecimal prorrateoPorRecurso) {
        this.prorrateoPorRecurso = prorrateoPorRecurso;
    }

    public BigDecimal getHorasDirectas() {
        return horasDirectas;
    }

    public void setHorasDirectas(BigDecimal horasDirectas) {
        this.horasDirectas = horasDirectas;
    }

    public BigDecimal getHorasEnEstructura() {
        return horasEnEstructura;
    }

    public void setHorasEnEstructura(BigDecimal horasEnEstructura) {
        this.horasEnEstructura = horasEnEstructura;
    }

    public BigDecimal getHorasOpex() {
        return horasOpex;
    }

    public void setHorasOpex(BigDecimal horasOpex) {
        this.horasOpex = horasOpex;
    }

    public BigDecimal getCobroProyecto() {
        return cobroProyecto;
    }

    public void setCobroProyecto(BigDecimal cobroProyecto) {
        this.cobroProyecto = cobroProyecto;
    }
}
