package com.claro.gestionrecursosweb.empleado.dto;

public class EmpleadoFormDto {

    private Integer id;
    private String nombreEmpleado;
    private Integer codpadre;
    private Integer codempleado_responsable;
    private Integer codestructuratipo;
    private String jerarquia;
    private String nombre;

    public EmpleadoFormDto(){

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getCodpadre() {
        return codpadre;
    }

    public void setCodpadre(Integer codpadre) {
        this.codpadre = codpadre;
    }

    public Integer getCodempleado_responsable() {
        return codempleado_responsable;
    }

    public void setCodempleado_responsable(Integer codempleado_responsable) {
        this.codempleado_responsable = codempleado_responsable;
    }

    public Integer getCodestructuratipo() {
        return codestructuratipo;
    }

    public void setCodestructuratipo(Integer codestructuratipo) {
        this.codestructuratipo = codestructuratipo;
    }

    public String getJerarquia() {
        return jerarquia;
    }

    public void setJerarquia(String jerarquia) {
        this.jerarquia = jerarquia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }


}
