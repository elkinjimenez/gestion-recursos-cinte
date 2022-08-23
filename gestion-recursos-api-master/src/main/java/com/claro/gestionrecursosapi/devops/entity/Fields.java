package com.claro.gestionrecursosapi.devops.entity;

public enum Fields {
    TITULO("Title", "/fields/System.Title"),
    ESTADO("State", "/fields/System.State"),
    GERENCIA("GerenciaDirEvolucionDigital", "/fields/Custom.GerenciaDirEvolucionDigital"),
    TIPO_ASIGNACION("TipoAsignacion", "/fields/Custom.TipoAsignacion"),
    EMPRESA("Empresa", "/fields/Custom.Empresa"),
    FECHA_INICIO("Fechainicio", "/fields/Custom.Fechainicio"),
    FECHA_FIN("Fechafin", "/fields/Custom.Fechafin"),
    PORCENTAJE_OCUPACION("Porcentajedeocupacion", "/fields/Custom.Porcentajedeocupacion"),
    ASIGNADO_A("AssignedTo", "/fields/System.AssignedTo"),
    DESCRIPCION("Description", "/fields/System.Description"),
	PROYECTO("Proyecto", "/fields/Custom.Proyecto");

    private final String nombre; 
    private final String valor; 

    Fields(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String nombre() {
        return nombre;
    }

    public  String valor() {
        return valor;
    }


}