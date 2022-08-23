package com.claro.gestionrecursosapi.reportegenerico.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "DF_VISTAS", schema = "BITACORA2")
public class VistaEntity {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "vista_Sequence")
    @SequenceGenerator(name = "SEQ_VISTA", sequenceName = "SEQ_VISTA", allocationSize = 1)
    private Integer id;
    @Basic
    @Column(name = "NOMBRE_VISTA")
    private String nombreVista;
    @Basic
    @Column(name = "FECHACREACION")
    private Date fechacreacion;
    @Basic
    @Column(name = "FECHAMODIFICACION")
    private Date fechamodificacion;
    @Basic
    @Column(name = "CODUSUARIOROL")
    private Integer codusuariorol;
    @Basic
    @Column(name = "ESTADO")
    private String estado;
    @Basic
    @Column(name = "VISTA")
    private String vista;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreVista() {
        return nombreVista;
    }

    public void setNombreVista(String nombreVista) {
        this.nombreVista = nombreVista;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public Integer getCodusuariorol() {
        return codusuariorol;
    }

    public void setCodusuariorol(Integer codusuariorol) {
        this.codusuariorol = codusuariorol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VistaEntity dfVistas = (VistaEntity) o;
        return Objects.equals(id, dfVistas.id) && Objects.equals(nombreVista, dfVistas.nombreVista) && Objects.equals(fechacreacion, dfVistas.fechacreacion) && Objects.equals(fechamodificacion, dfVistas.fechamodificacion) && Objects.equals(codusuariorol, dfVistas.codusuariorol) && Objects.equals(estado, dfVistas.estado) && Objects.equals(vista, dfVistas.vista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreVista, fechacreacion, fechamodificacion, codusuariorol, estado, vista);
    }
}
