package com.claro.gestionrecursosweb.horasextras.dto;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HoraExtraDtoParseada {

    private Integer id;
    private String tipoExtra;
    private String condicion;
    private Integer nivelAprobador;
    private String multiplicador;
    private Date Hini;
    private Date Hfin;
    private String dia;

    public HoraExtraDtoParseada() {

    }

    public HoraExtraDtoParseada(HoraExtraDto hextradto) throws Exception {
        this.setId(hextradto.getId());
        this.setTipoExtra(hextradto.getTipoExtra());
        this.setCondicion(hextradto.getCondicion());
        this.setNivelAprobador(hextradto.getNivelAprobador());
        this.setMultiplicador(cambiarComaporPunto(hextradto.getMultiplicador()));
        this.setHini(FormatearFecha(hextradto.getHini()));
        this.setHfin(FormatearFecha(hextradto.getHfin()));
    }

    public Date FormatearFecha(String hextra) throws Exception {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm", new Locale("es", "CO"));
            return formatter.parse(hextra);

        } catch (Exception e) {
            throw new Exception("Error");
        }
    }

    public String cambiarComaporPunto(String valor) {
        if (!valor.equalsIgnoreCase("")) {
            return valor.replace(",", ".");
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoExtra() {
        return tipoExtra;
    }

    public void setTipoExtra(String tipoExtra) {
        this.tipoExtra = tipoExtra;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Integer getNivelAprobador() {
        return nivelAprobador;
    }

    public void setNivelAprobador(Integer nivelAprobador) {
        this.nivelAprobador = nivelAprobador;
    }

    public String getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(String multiplicador) {
        this.multiplicador = multiplicador;
    }

    public Date getHini() {
        return Hini;
    }

    public void setHini(Date hini) {
        Hini = hini;
    }

    public Date getHfin() {
        return Hfin;
    }

    public void setHfin(Date hfin) {
        Hfin = hfin;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
