package com.claro.gestionrecursosapi.perfil.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class PerfilCostoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer proveedorId;
    private String proveedorNombre;
    private Integer perfilId;
    private String perfilNombre;
    private Integer perfilNivelId;
    private String perfilNivelNombre;
    private Integer perfilTipoId;
    private String perfilTipoNombre;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String estado;
    private BigDecimal valor;

    public PerfilCostoDto() {

    }

    /*public PerfilCostoDto(Object id, Object lineaProductoId, Object lineaProductoNombre, Object perfilId, Object perfilNombre, Object perfilNivelId, Object perfilNivelNombre, Object perfilTipoId, Object perfilTipoNombre, Date fechaCreacion, Date fechaModificacion, Object estado, Object valor) {
        this.id = Integer.valueOf(String.valueOf(id));
        this.lineaProductoId = Integer.valueOf(String.valueOf(lineaProductoId));
        this.lineaProductoNombre = String.valueOf(lineaProductoNombre);
        this.perfilId = Integer.valueOf(String.valueOf(perfilId));
        this.perfilNombre = String.valueOf(perfilNombre);
        this.perfilNivelId = Integer.valueOf(String.valueOf(perfilNivelId));
        this.perfilNivelNombre = String.valueOf(perfilNivelNombre);
        this.perfilTipoId = Integer.valueOf(String.valueOf(perfilTipoId));
        this.perfilTipoNombre = String.valueOf(perfilTipoNombre);
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.estado = String.valueOf(estado);
        this.valor = new BigDecimal(String.valueOf(valor));
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
    }

    public Integer getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }

    public String getPerfilNombre() {
        return perfilNombre;
    }

    public void setPerfilNombre(String perfilNombre) {
        this.perfilNombre = perfilNombre;
    }

    public Integer getPerfilNivelId() {
        return perfilNivelId;
    }

    public void setPerfilNivelId(Integer perfilNivelId) {
        this.perfilNivelId = perfilNivelId;
    }

    public String getPerfilNivelNombre() {
        return perfilNivelNombre;
    }

    public void setPerfilNivelNombre(String perfilNivelNombre) {
        this.perfilNivelNombre = perfilNivelNombre;
    }

    public Integer getPerfilTipoId() {
        return perfilTipoId;
    }

    public void setPerfilTipoId(Integer perfilTipoId) {
        this.perfilTipoId = perfilTipoId;
    }

    public String getPerfilTipoNombre() {
        return perfilTipoNombre;
    }

    public void setPerfilTipoNombre(String perfilTipoNombre) {
        this.perfilTipoNombre = perfilTipoNombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
