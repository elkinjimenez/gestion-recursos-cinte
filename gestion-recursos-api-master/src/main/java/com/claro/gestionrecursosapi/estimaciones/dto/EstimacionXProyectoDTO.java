package com.claro.gestionrecursosapi.estimaciones.dto;

public class EstimacionXProyectoDTO {

    private Integer codProyecto;
    private String nombreProyecto;

    public EstimacionXProyectoDTO(Integer codProyecto, String nombreProyecto) {
        this.codProyecto = codProyecto;
        this.nombreProyecto = nombreProyecto;
    }

    public Integer getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(Integer codProyecto) {
        this.codProyecto = codProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }
}
