package com.claro.gestionrecursosapi.roadmap.entity;

import javax.persistence.*;

@Entity
@Table(name = "DF_ROAD_MAP")
public class RoadMap {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "roadMap_Sequence")
    @SequenceGenerator(name = "roadMap_Sequence", sequenceName = "SEQ_ROAD_MAP", allocationSize = 1)
    private Integer id;
    @Column(name = "NOMBREMICROSERVICIO")
    private String nombreMicroservicio;
    @Column(name = "TIPO")
    private Integer tipo;
    @Column(name = "DESPLIEGUE")
    private Integer despliegue;
    @Column(name = "MONGOVERSION")
    private String mongoVersion;
    @Column(name = "ESTADO")
    private Integer estado;
    @Column(name = "JDKVERSION")
    private String jdkVersion;
    @Column(name = "WOESTANDARES")
    private String woEstandares;
    @Column(name = "WODOCUMENTACION")
    private String woDocumentacion;
    @Column(name = "DESCRIPCIONSERVICIO")
    private String descripcionServicio;
    @Column(name = "TRYSCAPE")
    private String tryscape;
    @Column(name = "URLDEV")
    private String urlDev;
    @Column(name = "URLQA")
    private String urlQa;
    @Column(name = "URLPRODUCCION")
    private String urlProduccion;
    @Column(name = "ESTADOMICROSERVICIO")
    private Integer estadoMicroservicio;
    @Column(name = "INTRODUCCIONSERVICIO")
    private boolean introduccionServicio;
    @Column(name = "ESTADOENTREGA")
    private boolean estadoEntrega;
    @Column(name = "CODPRYBRIEF")
    private Integer codPryBrief;
    @Column(name = "ACTUALIZADOXUSUARIOCLARO")
    private String actualizadoXUsuarioClaro;
    @Column(name = "INFORMACION3SCALA")
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

    public Integer getEstadoMicroservicio() {
        return estadoMicroservicio;
    }

    public void setEstadoMicroservicio(Integer estadoMicroservicio) {
        this.estadoMicroservicio = estadoMicroservicio;
    }

    public boolean isIntroduccionServicio() {
        return introduccionServicio;
    }

    public void setIntroduccionServicio(boolean introduccionServicio) {
        this.introduccionServicio = introduccionServicio;
    }

    public boolean isEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(boolean estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
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