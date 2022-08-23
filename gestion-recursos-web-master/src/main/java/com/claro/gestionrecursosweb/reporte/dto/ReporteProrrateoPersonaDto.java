package com.claro.gestionrecursosweb.reporte.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReporteProrrateoPersonaDto {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date fecha;
    private String codproyecto;
    private String idProyecto;
    private String pep;
    private String proyecto;
    private String proveedor;
    private String perfil;
    private String idrecurso;
    private String recurso;
    private BigDecimal costo;
    private Date fechaingreso;
    private Integer horas;
    private BigDecimal horaEnEstructura;
    private BigDecimal porcentajeProyecto;
    private BigDecimal horaConEstructura;

    public ReporteProrrateoPersonaDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCodproyecto() {
        return codproyecto;
    }

    public void setCodproyecto(String codproyecto) {
        this.codproyecto = codproyecto;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getPep() {
        return pep;
    }

    public void setPep(String pep) {
        this.pep = pep;
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

    public String getIdrecurso() {
        return idrecurso;
    }

    public void setIdrecurso(String idrecurso) {
        this.idrecurso = idrecurso;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public BigDecimal getHoraEnEstructura() {
        return horaEnEstructura;
    }

    public void setHoraEnEstructura(BigDecimal horaEnEstructura) {
        this.horaEnEstructura = horaEnEstructura;
    }

    public BigDecimal getPorcentajeProyecto() {
        return porcentajeProyecto;
    }

    public void setPorcentajeProyecto(BigDecimal porcentajeProyecto) {
        this.porcentajeProyecto = porcentajeProyecto;
    }

    public BigDecimal getHoraConEstructura() {
        return horaConEstructura;
    }

    public void setHoraConEstructura(BigDecimal horaConEstructura) {
        this.horaConEstructura = horaConEstructura;
    }
}
