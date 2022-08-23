package com.claro.gestionrecursosweb.horasextras.dto;

import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
public class HoraExtraDto {

    private Integer id;
    private String tipo_Extra;
    private String condicion;
    private Integer nivel_Aprobador;
    private String multiplicador;
    private String Hini;
    private String Hfin;
    private String dia;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoExtra() {
        return tipo_Extra;
    }

    public void setTipoExtra(String tipoExtra) {
        this.tipo_Extra = tipoExtra;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Integer getNivelAprobador() {
        return nivel_Aprobador;
    }

    public void setNivelAprobador(Integer nivelAprobador) {
        this.nivel_Aprobador = nivelAprobador;
    }

    public String getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(String multiplicador) {
        this.multiplicador = multiplicador;
    }

    public String getHini() {
        return Hini;
    }

    public void setHini(String hini) {
        Hini = hini;
    }

    public String getHfin() {
        return Hfin;
    }

    public void setHfin(String hfin) {
        Hfin = hfin;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
