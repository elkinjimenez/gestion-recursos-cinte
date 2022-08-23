package com.claro.gestionrecursosapi.estructura.enums;

public enum EstructuraTipoEnum {

    GERENTE(1,"GERENTE");

    private Integer id;
    private String descripcion;

    EstructuraTipoEnum(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
