package com.claro.gestionrecursosapi.roadmap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DF_ESTADO_ROAD_MAP")
public class EstadoRoadMap {

    @Id
    private Integer id;
    @Column(name = "ESTADONOMBRE")
    private String nombre;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
