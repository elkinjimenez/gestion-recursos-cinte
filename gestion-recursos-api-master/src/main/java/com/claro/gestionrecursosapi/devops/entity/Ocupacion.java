package com.claro.gestionrecursosapi.devops.entity;

public class Ocupacion {
    private String AreaId;
    private String Title;
    private String State;
    private String GerenciaDirEvolucionDigital;
    private String TipoAsignacion;
    private String Empresa;
    private String Fechainicio;
    private String Fechafin;
    private String Porcentajedeocupacion;
    private String AssignedTo;
    private String Description;
    private String Proyecto;

    public String getAreaId() {
        return AreaId;
    }

    public void setAreaId(String areaId
    ) {
        AreaId = areaId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getGerenciaDirEvolucionDigital() {
        return GerenciaDirEvolucionDigital;
    }

    public void setGerenciaDirEvolucionDigital(String gerenciaDirEvolucionDigital) {
        GerenciaDirEvolucionDigital = gerenciaDirEvolucionDigital;
    }

    public String getTipoAsignacion() {
        return TipoAsignacion;
    }

    public void setTipoAsignacion(String tipoAsignacion) {
        TipoAsignacion = tipoAsignacion;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public String getFechainicio() {
        return Fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        Fechainicio = fechainicio;
    }

    public String getFechafin() {
        return Fechafin;
    }

    public void setFechafin(String fechafin) {
        Fechafin = fechafin;
    }

    public String getPorcentajedeocupacion() {
        return Porcentajedeocupacion;
    }

    public void setPorcentajedeocupacion(String porcentajedeocupacion) {
        Porcentajedeocupacion = porcentajedeocupacion;
    }

    public String getAssignedTo() {
        return AssignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        AssignedTo = assignedTo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getProyecto() {
        return Proyecto;
    }

    public void setProyecto(String proyecto) {
        Proyecto = proyecto;
    }
}
