package com.claro.gestionrecursosapi.gerente.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GerenteEntity {

    @Id
    private Long id;
    private Integer codEmpleado;
    private String nombreGerencia;
    private String nombreGerente;
    private Integer codPadre;

    public GerenteEntity() {

    }


    public GerenteEntity(Integer id, Integer codEmpleado, String nombreGerencia, String nombreGerente, Integer codTipoEstructura, Integer codPadre) {
        this.id = Long.valueOf(id);
        this.codEmpleado = codEmpleado;
        this.nombreGerencia = nombreGerencia;
        this.nombreGerente = nombreGerente;
        this.codPadre = codPadre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(Integer codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getNombreGerencia() {
        return nombreGerencia;
    }

    public void setNombreGerencia(String nombreGerencia) {
        this.nombreGerencia = nombreGerencia;
    }

    public String getNombreGerente() {
        return nombreGerente;
    }

    public void setNombreGerente(String nombreGerente) {
        this.nombreGerente = nombreGerente;
    }

    public Integer getCodPadre() {
        return codPadre;
    }

    public void setCodPadre(Integer codPadre) {
        this.codPadre = codPadre;
    }
}
