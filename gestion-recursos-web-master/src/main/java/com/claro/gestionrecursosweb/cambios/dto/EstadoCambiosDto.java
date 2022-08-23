package com.claro.gestionrecursosweb.cambios.dto;

import java.util.Objects;

public class EstadoCambiosDto {

    private int id;
   
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
        EstadoCambiosDto that = (EstadoCambiosDto) o;
        return id == that.id && Objects.equals(nomEstado, that.nomEstado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomEstado);
    }
}
