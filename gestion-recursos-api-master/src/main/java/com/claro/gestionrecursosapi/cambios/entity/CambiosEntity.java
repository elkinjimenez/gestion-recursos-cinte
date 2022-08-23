package com.claro.gestionrecursosapi.cambios.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "DF_CAMBIOS", schema = "BITACORA2")
public class CambiosEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "cambio_Sequence")
    @SequenceGenerator(name = "cambio_Sequence", sequenceName = "DF_CAMBIOS_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "COD_CAMBIO")
    private String codCambio;
    @Basic
    @Column(name = "FECHA_TCBA")
    private Date fechaTcba;
    @Basic
    @Column(name = "FECHA_PAP")
    private Date fechaPap;
    @Basic
    @Column(name = "GESTOR_COD_EMPLEADO")
    private Integer gestorCodEmpleado;
    @Basic
    @Column(name = "RESUMEN")
    private String resumen;
    @Basic
    @Column(name = "ID_RLP")
    private String idRlp;
    @Basic
    @Column(name = "TIPO_LINEA")
    private Integer tipoLinea;
    @Basic
    @Column(name = "ESTADO")
    private Integer estado;
    @Basic
    @Column(name = "ID_COMPROMISO")
    private Integer idCompromiso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodCambio() {
        return codCambio;
    }

    public void setCodCambio(String codCambio) {
        this.codCambio = codCambio;
    }

    public Date getFechaTcba() {
        return fechaTcba;
    }

    public void setFechaTcba(Date fechaTcba) {
        this.fechaTcba = fechaTcba;
    }

    public Date getFechaPap() {
        return fechaPap;
    }

    public void setFechaPap(Date fechaPap) {
        this.fechaPap = fechaPap;
    }

    public Integer getGestorCodEmpleado() {
        return gestorCodEmpleado;
    }

    public void setGestorCodEmpleado(Integer gestorCodEmpleado) {
        this.gestorCodEmpleado = gestorCodEmpleado;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getIdRlp() {
        return idRlp;
    }

    public void setIdRlp(String idRlp) {
        this.idRlp = idRlp;
    }

    public Integer getTipoLinea() {
        return tipoLinea;
    }

    public void setTipoLinea(Integer tipoLinea) {
        this.tipoLinea = tipoLinea;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getIdCompromiso() {
        return idCompromiso;
    }

    public void setIdCompromiso(Integer idCompromiso) {
        this.idCompromiso = idCompromiso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CambiosEntity that = (CambiosEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (codCambio != null ? !codCambio.equals(that.codCambio) : that.codCambio != null) return false;
        if (fechaTcba != null ? !fechaTcba.equals(that.fechaTcba) : that.fechaTcba != null) return false;
        if (fechaPap != null ? !fechaPap.equals(that.fechaPap) : that.fechaPap != null) return false;
        if (gestorCodEmpleado != null ? !gestorCodEmpleado.equals(that.gestorCodEmpleado) : that.gestorCodEmpleado != null)
            return false;
        if (resumen != null ? !resumen.equals(that.resumen) : that.resumen != null) return false;
        if (idRlp != null ? !idRlp.equals(that.idRlp) : that.idRlp != null) return false;
        if (tipoLinea != null ? !tipoLinea.equals(that.tipoLinea) : that.tipoLinea != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;
        if (idCompromiso != null ? !idCompromiso.equals(that.idCompromiso) : that.idCompromiso != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (codCambio != null ? codCambio.hashCode() : 0);
        result = 31 * result + (fechaTcba != null ? fechaTcba.hashCode() : 0);
        result = 31 * result + (fechaPap != null ? fechaPap.hashCode() : 0);
        result = 31 * result + (gestorCodEmpleado != null ? gestorCodEmpleado.hashCode() : 0);
        result = 31 * result + (resumen != null ? resumen.hashCode() : 0);
        result = 31 * result + (idRlp != null ? idRlp.hashCode() : 0);
        result = 31 * result + (tipoLinea != null ? tipoLinea.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (idCompromiso != null ? idCompromiso.hashCode() : 0);
        return result;
    }
}
