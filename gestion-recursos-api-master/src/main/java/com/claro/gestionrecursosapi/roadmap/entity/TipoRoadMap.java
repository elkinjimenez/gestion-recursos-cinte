package com.claro.gestionrecursosapi.roadmap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DF_ROAD_MAP_TIPO")
public class TipoRoadMap {

    @Id
    private Integer id;
    @Column(name = "TIPOROADMAP")
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
