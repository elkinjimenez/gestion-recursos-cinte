package com.claro.gestionrecursosapi.cambios.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DF_TIPOS_LINEA", schema = "BITACORA2")
public class TiposLineaEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "NOM_TIPO_LINEA")
    private String nomTipoLinea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomTipoLinea() {
        return nomTipoLinea;
    }

    public void setNomTipoLinea(String nomTipoLinea) {
        this.nomTipoLinea = nomTipoLinea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TiposLineaEntity that = (TiposLineaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nomTipoLinea, that.nomTipoLinea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomTipoLinea);
    }
}
