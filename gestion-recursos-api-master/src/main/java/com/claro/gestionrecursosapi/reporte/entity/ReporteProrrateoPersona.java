package com.claro.gestionrecursosapi.reporte.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ReporteProrrateoPersona {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private Date fecha;
    @Column(name = "CODPROYECTO")
    private String idProyecto;
    @Column(name = "IDPROYECTO")
    private String codproyecto;
    private String pep;
    private String proyecto;
    private String proveedor;
    private String perfil;
    private String idrecurso;
    private String recurso;
    private BigDecimal costo;
    private Date fechaingreso;
    private BigDecimal horas;
    private BigDecimal horaEnEstructura;
    private BigDecimal porcentajeProyecto;
    private BigDecimal horaConEstructura;

    public ReporteProrrateoPersona() {

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

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getCodproyecto() {
        return codproyecto;
    }

    public void setCodproyecto(String codproyecto) {
        this.codproyecto = codproyecto;
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

    public BigDecimal getHoras() {
        return horas;
    }

    public void setHoras(BigDecimal horas) {
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
