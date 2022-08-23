package com.claro.gestionrecursosweb.reporte.dto;

import java.math.BigDecimal;

public class ReporteOpexDto {

    private Long id;
    private String proyecto;
    private String proveedor;
    private String perfil;
    private Integer modalidad;
    private String recurso;
    private BigDecimal prorrateoPorRecurso;
    private BigDecimal horasDirectas;
    private BigDecimal horasEnEstructura;
    private BigDecimal horasOpex;
    private BigDecimal cobroProyecto;

    public ReporteOpexDto() {

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
