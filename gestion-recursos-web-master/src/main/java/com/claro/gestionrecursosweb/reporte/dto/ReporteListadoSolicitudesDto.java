package com.claro.gestionrecursosweb.reporte.dto;

import java.math.BigDecimal;

public class ReporteListadoSolicitudesDto {

    private Long id;
    private String proyecto;
    private String gerente;
    private String descripcionTarea;
    private String proveedor;
    private String perfil;
    private String idRecurso;
    private String recurso;
    private BigDecimal horas;
    private BigDecimal porcentajeEnProyecto;
    private BigDecimal horasConEstructura;
    private String doliente;

    public ReporteListadoSolicitudesDto() {

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

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
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

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public BigDecimal getHoras() {
        return horas;
    }

    public void setHoras(BigDecimal horas) {
        this.horas = horas;
    }

    public BigDecimal getPorcentajeEnProyecto() {
        return porcentajeEnProyecto;
    }

    public void setPorcentajeEnProyecto(BigDecimal porcentajeEnProyecto) {
        this.porcentajeEnProyecto = porcentajeEnProyecto;
    }

    public BigDecimal getHorasConEstructura() {
        return horasConEstructura;
    }

    public void setHorasConEstructura(BigDecimal horasConEstructura) {
        this.horasConEstructura = horasConEstructura;
    }

    public String getDoliente() {
        return doliente;
    }

    public void setDoliente(String doliente) {
        this.doliente = doliente;
    }
}
