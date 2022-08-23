package com.claro.gestionrecursosweb.hallazgo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class HallazgoCompromisoDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private Integer Id;
    private Integer codProyecto;
    private Integer codperfil;
    private Integer codproveedor;
    private Integer codperfiltipo;
    private Integer codperfilnivel;
    private String lineaProducto;
    private Integer horasHL;
    private Date fechaInicio;
    private Date fechaFin;
    private Timestamp fechacreacion;
    private Timestamp fechamodificacion;
    private Integer horarLL;
    private String brief;
    private Integer idRlp;
    private Date fechaQaInicio;
    private Date fechaQaFin;
    private Date fechaDespliegue;
    private Date fechaDespliegueReal;
    private String idCambio;
    private String resultado;
    private String justificacion;
    private String descripcion;
    private Date fechaPresupuesto;
    private Integer horasReal;
    private String lt;
    private String pm;
    private String estado;
    private Integer fase;
    private Integer codEstructura;
    private Date fechaSolicitud;
    private String stopper;
    private String evidencia;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(Integer codProyecto) {
        this.codProyecto = codProyecto;
    }

    public Integer getCodperfil() {
        return codperfil;
    }

    public void setCodperfil(Integer codperfil) {
        this.codperfil = codperfil;
    }

    public Integer getCodproveedor() {
        return codproveedor;
    }

    public void setCodproveedor(Integer codproveedor) {
        this.codproveedor = codproveedor;
    }

    public Integer getCodperfiltipo() {
        return codperfiltipo;
    }

    public void setCodperfiltipo(Integer codperfiltipo) {
        this.codperfiltipo = codperfiltipo;
    }

    public Integer getCodperfilnivel() {
        return codperfilnivel;
    }

    public void setCodperfilnivel(Integer codperfilnivel) {
        this.codperfilnivel = codperfilnivel;
    }

    public String getLineaProducto() {
        return lineaProducto;
    }

    public void setLineaProducto(String lineaProducto) {
        this.lineaProducto = lineaProducto;
    }

    public Integer getHorasHL() {
        return horasHL;
    }

    public void setHorasHL(Integer horasHL) {
        this.horasHL = horasHL;
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

    public Timestamp getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Timestamp fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Timestamp getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Timestamp fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public Integer getHorarLL() {
        return horarLL;
    }

    public void setHorarLL(Integer horarLL) {
        this.horarLL = horarLL;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getIdRlp() {
        return idRlp;
    }

    public void setIdRlp(Integer idRlp) {
        this.idRlp = idRlp;
    }

    public Date getFechaQaInicio() {
        return fechaQaInicio;
    }

    public void setFechaQaInicio(Date fechaQaInicio) {
        this.fechaQaInicio = fechaQaInicio;
    }

    public Date getFechaQaFin() {
        return fechaQaFin;
    }

    public void setFechaQaFin(Date fechaQaFin) {
        this.fechaQaFin = fechaQaFin;
    }

    public Date getFechaDespliegue() {
        return fechaDespliegue;
    }

    public void setFechaDespliegue(Date fechaDespliegue) {
        this.fechaDespliegue = fechaDespliegue;
    }

    public Date getFechaDespliegueReal() {
        return fechaDespliegueReal;
    }

    public void setFechaDespliegueReal(Date fechaDespliegueReal) {
        this.fechaDespliegueReal = fechaDespliegueReal;
    }

    public String getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(String idCambio) {
        this.idCambio = idCambio;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaPresupuesto() {
        return fechaPresupuesto;
    }

    public void setFechaPresupuesto(Date fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
    }

    public Integer getHorasReal() {
        return horasReal;
    }

    public void setHorasReal(Integer horasReal) {
        this.horasReal = horasReal;
    }

    public String getLt() {
        return lt;
    }

    public void setLt(String lt) {
        this.lt = lt;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getFase() {
        return fase;
    }

    public void setFase(Integer fase) {
        this.fase = fase;
    }

    public Integer getCodEstructura() {
        return codEstructura;
    }

    public void setCodEstructura(Integer codEstructura) {
        this.codEstructura = codEstructura;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getStopper() {
        return stopper;
    }

    public void setStopper(String stopper) {
        this.stopper = stopper;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }
}
