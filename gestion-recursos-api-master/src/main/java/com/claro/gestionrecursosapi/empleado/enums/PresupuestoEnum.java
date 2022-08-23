package com.claro.gestionrecursosapi.empleado.enums;

public enum PresupuestoEnum {

    NO_EXCEDIDO(0),
    EXCEDIDO(1);

    private Integer id;

    PresupuestoEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
