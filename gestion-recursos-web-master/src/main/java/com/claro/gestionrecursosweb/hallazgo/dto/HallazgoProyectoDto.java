package com.claro.gestionrecursosweb.hallazgo.dto;

import com.claro.gestionrecursosweb.compromisosfabrica.dto.CompromisosFabricaDto;
import com.claro.gestionrecursosweb.proyecto.dto.PresupuestoDto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class HallazgoProyectoDto {

    private Integer id;
    private Integer codproyectotipo;
    private List<HallazgoCompromisoDto> compromisos;
    //private Integer codpresupuesto;
    private String codigoproyecto;
    private String nombre;
    private String descripcion;
    private Boolean prioritario;
    private Date fechainicio;
    private Date fechafin;
    private Date fechacreacion;
    private Date fechamodificacion;
    private String estado;
    private String nombreAMX;
    private String nombreLocal;
    private String agrcapex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodproyectotipo() {
        return codproyectotipo;
    }

    public void setCodproyectotipo(Integer codproyectotipo) {
        this.codproyectotipo = codproyectotipo;
    }

    public List<HallazgoCompromisoDto> getCompromisos() {
        return compromisos;
    }

    public void setCompromisos(List<HallazgoCompromisoDto> compromisos) {
        this.compromisos = compromisos;
    }

    public String getCodigoproyecto() {
        return codigoproyecto;
    }

    public void setCodigoproyecto(String codigoproyecto) {
        this.codigoproyecto = codigoproyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPrioritario() {
        return prioritario;
    }

    public void setPrioritario(Boolean prioritario) {
        this.prioritario = prioritario;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreAMX() {
        return nombreAMX;
    }

    public void setNombreAMX(String nombreAMX) {
        this.nombreAMX = nombreAMX;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public String getAgrcapex() {
        return agrcapex;
    }

    public void setAgrcapex(String agrcapex) {
        this.agrcapex = agrcapex;
    }
}
