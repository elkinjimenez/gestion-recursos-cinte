package com.claro.gestionrecursosapi.horasextra.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "DF_REPORTE_HEXTRAS_VU")
public class RepHExtrasVU {
    @Basic
    @Column(name = "ID")
    @Id
    private Integer id;
    @Basic
    @Column(name = "CODEMPLEADO")
    private Integer codempleado;
    @Basic
    @Column(name = "RECURSO")
    private String recurso;
    @Basic
    @Column(name = "PROVEEDOR")
    private String proveedor;
    @Basic
    @Column(name = "IDPROYECTO")
    private Integer idproyecto;
    @Basic
    @Column(name = "COSTO")
    private Long costo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodempleado() {
        return codempleado;
    }

    public void setCodempleado(Integer codempleado) {
        this.codempleado = codempleado;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Integer idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }
}
