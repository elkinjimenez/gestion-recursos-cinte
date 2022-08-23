package com.claro.gestionrecursosweb.novedad.dto;

public class RoadMapDto {

    private Integer id;
    private String nombreMicroservicio;
    private Integer tipo;
    private Integer despliegue;
    private String mongoVersion;
    private Integer estado;
    private String jdkVersion;
    private String woEstandares;
    private String woDocumentacion;
    private String descripcionServicio;
    private String tryscape;
    private String urlDev;
    private String urlQa;
    private String urlProduccion;
    private Integer estadoMicroservicio;
    private boolean introduccionServicio;
    private boolean estadoEntrega;
    private Integer codPryBrief;
    private String actualizadoXUsuarioClaro;
    private String informacion3scala;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreMicroservicio() {
        return nombreMicroservicio;
    }

    public void setNombreMicroservicio(String nombreMicroservicio) {
        this.nombreMicroservicio = nombreMicroservicio;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getDespliegue() {
        return despliegue;
    }

    public void setDespliegue(Integer despliegue) {
        this.despliegue = despliegue;
    }

    public String getMongoVersion() {
        return mongoVersion;
    }

    public void setMongoVersion(String mongoVersion) {
        this.mongoVersion = mongoVersion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public String getWoEstandares() {
        return woEstandares;
    }

    public void setWoEstandares(String woEstandares) {
        this.woEstandares = woEstandares;
    }

    public String getWoDocumentacion() {
        return woDocumentacion;
    }

    public void setWoDocumentacion(String woDocumentacion) {
        this.woDocumentacion = woDocumentacion;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public String getTryscape() {
        return tryscape;
    }

    public void setTryscape(String tryscape) {
        this.tryscape = tryscape;
    }

    public String getUrlDev() {
        return urlDev;
    }

    public void setUrlDev(String urlDev) {
        this.urlDev = urlDev;
    }

    public String getUrlQa() {
        return urlQa;
    }

    public void setUrlQa(String urlQa) {
        this.urlQa = urlQa;
    }

    public String getUrlProduccion() {
        return urlProduccion;
    }

    public void setUrlProduccion(String urlProduccion) {
        this.urlProduccion = urlProduccion;
    }

    public void setEstadoMicroservicio(Integer estadoMicroservicio) {
        this.estadoMicroservicio = estadoMicroservicio;
    }

    public void setEstadoEntrega(boolean estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public boolean isIntroduccionServicio() {
        return introduccionServicio;
    }

    public void setIntroduccionServicio(boolean introduccionServicio) {
        this.introduccionServicio = introduccionServicio;
    }

    public Integer getEstadoMicroservicio() {
        return estadoMicroservicio;
    }

    public boolean isEstadoEntrega() {
        return estadoEntrega;
    }

    public Integer getCodPryBrief() {
        return codPryBrief;
    }

    public void setCodPryBrief(Integer codPryBrief) {
        this.codPryBrief = codPryBrief;
    }

    public String getActualizadoXUsuarioClaro() {
        return actualizadoXUsuarioClaro;
    }

    public void setActualizadoXUsuarioClaro(String actualizadoXUsuarioClaro) {
        this.actualizadoXUsuarioClaro = actualizadoXUsuarioClaro;
    }

    public String getInformacion3scala() {
        return informacion3scala;
    }

    public void setInformacion3scala(String informacion3scala) {
        this.informacion3scala = informacion3scala;
    }
}