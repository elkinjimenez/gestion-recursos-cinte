package com.claro.gestionrecursosapi.reporte.enums;

public enum ModalidadEmpleadoEnum {

    CAPEX(0, "CAPEX"),
    OPEX(1, "OPEX");

    private Integer id;
    private String descripcion;

    ModalidadEmpleadoEnum(Integer id, String descripcion) {
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
