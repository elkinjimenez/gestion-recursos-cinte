package com.claro.gestionrecursosapi.reporte.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReporteRecursoConProveedor {

    @Id
    private Long id;
    private String proyecto;
    private String proveedor;
    private String perfil;
    private String idRecurso;
    private String recurso;

    public ReporteRecursoConProveedor() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }
}
