package com.claro.gestionrecursosweb.cambios.dto;

import java.util.Objects;

public class TiposLineaDto {

    private int id;

    private String nomTipoLinea;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        TiposLineaDto that = (TiposLineaDto) o;
        return id == that.id && Objects.equals(nomTipoLinea, that.nomTipoLinea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomTipoLinea);
    }
}
