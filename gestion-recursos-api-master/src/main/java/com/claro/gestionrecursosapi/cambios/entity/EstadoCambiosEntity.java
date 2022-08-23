package com.claro.gestionrecursosapi.cambios.entity;

import javax.persistence.*;

@Entity
@Table(name = "DF_ESTADO_CAMBIOS", schema = "BITACORA2")
public class EstadoCambiosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "NOM_ESTADO")
    private String nomEstado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoCambiosEntity that = (EstadoCambiosEntity) o;

        if (id != that.id) return false;
        if (nomEstado != null ? !nomEstado.equals(that.nomEstado) : that.nomEstado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nomEstado != null ? nomEstado.hashCode() : 0);
        return result;
    }
}
