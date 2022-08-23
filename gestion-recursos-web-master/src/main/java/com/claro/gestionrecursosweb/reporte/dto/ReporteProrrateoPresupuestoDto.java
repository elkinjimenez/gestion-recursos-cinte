package com.claro.gestionrecursosweb.reporte.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReporteProrrateoPresupuestoDto {

    private Long id;
    private Date fecha;
    private String proyecto;
    private BigDecimal presupuesto;
    private String proveedor;
    private String perfil;
    private String idRecurso;
    private String recurso;
    private BigDecimal horasSinEstructura;
    private BigDecimal costoSinEstructura;
    private BigDecimal horasConEstructura;
    private BigDecimal costoConEstructura;
    private BigDecimal prorrateo;
    private BigDecimal presupuestoDisponible;
    private BigDecimal presupuestoConsumido;
    private BigDecimal porcentajeHoras;
    private BigDecimal porcentajeCosto;

    public ReporteProrrateoPresupuestoDto() {

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

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
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

    public BigDecimal getHorasSinEstructura() {
        return horasSinEstructura;
    }

    public void setHorasSinEstructura(BigDecimal horasSinEstructura) {
        this.horasSinEstructura = horasSinEstructura;
    }

    public BigDecimal getCostoSinEstructura() {
        return costoSinEstructura;
    }

    public void setCostoSinEstructura(BigDecimal costoSinEstructura) {
        this.costoSinEstructura = costoSinEstructura;
    }

    public BigDecimal getHorasConEstructura() {
        return horasConEstructura;
    }

    public void setHorasConEstructura(BigDecimal horasConEstructura) {
        this.horasConEstructura = horasConEstructura;
    }

    public BigDecimal getCostoConEstructura() {
        return costoConEstructura;
    }

    public void setCostoConEstructura(BigDecimal costoConEstructura) {
        this.costoConEstructura = costoConEstructura;
    }

    public BigDecimal getProrrateo() {
        return prorrateo;
    }

    public void setProrrateo(BigDecimal prorrateo) {
        this.prorrateo = prorrateo;
    }

    public BigDecimal getPresupuestoDisponible() {
        return presupuestoDisponible;
    }

    public void setPresupuestoDisponible(BigDecimal presupuestoDisponible) {
        this.presupuestoDisponible = presupuestoDisponible;
    }

    public BigDecimal getPresupuestoConsumido() {
        return presupuestoConsumido;
    }

    public void setPresupuestoConsumido(BigDecimal presupuestoConsumido) {
        this.presupuestoConsumido = presupuestoConsumido;
    }

    public BigDecimal getPorcentajeHoras() {
        return porcentajeHoras;
    }

    public void setPorcentajeHoras(BigDecimal porcentajeHoras) {
        this.porcentajeHoras = porcentajeHoras;
    }

    public BigDecimal getPorcentajeCosto() {
        return porcentajeCosto;
    }

    public void setPorcentajeCosto(BigDecimal porcentajeCosto) {
        this.porcentajeCosto = porcentajeCosto;
    }
}
