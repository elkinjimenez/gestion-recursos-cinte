package com.claro.gestionrecursosweb.empleado.dto;

import java.util.List;

import com.claro.gestionrecursosweb.persona.dto.PersonaDto;
import org.springframework.lang.Nullable;


public class GerenteDto {

    private Long id;
    private Integer codEmpleado;
    private String nombreGerencia;
    private String nombreGerente;
    private Integer codTipoEstructura;
    private Integer codPadre;
    private List<EmpleadoFormDto> listajefes;
    private EmpleadoFormDto jefeActual;


    public GerenteDto() {

    }

    public String getNombreGerente() {
        return nombreGerente;
    }

    public void setNombreGerente(String nombreGerente) {
        this.nombreGerente = nombreGerente;
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

    public Integer getCodTipoEstructura() {
        return codTipoEstructura;
    }

    public void setCodTipoEstructura(Integer codTipoEstructura) {
        this.codTipoEstructura = codTipoEstructura;
    }

    public Integer getCodPadre() {
        return codPadre;
    }

    public void setCodPadre(Integer codPadre) {
        this.codPadre = codPadre;
    }

    public List<EmpleadoFormDto> getListajefes() {
        return listajefes;
    }

    public void setListajefes(List<EmpleadoFormDto> listajefes) {
        this.listajefes = listajefes;
    }

    public EmpleadoFormDto getJefeActual() {
        return jefeActual;
    }

    public void setJefeActual(EmpleadoFormDto jefeActual) {
        this.jefeActual = jefeActual;
    }
}