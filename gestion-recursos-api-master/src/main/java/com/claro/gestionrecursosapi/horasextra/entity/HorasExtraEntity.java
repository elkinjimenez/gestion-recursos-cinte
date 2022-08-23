package com.claro.gestionrecursosapi.horasextra.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Table(name = "DF_EXTRA_TIPO",  schema = "BITACORA2")
@Entity
public class HorasExtraEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "hora_extra_secuencia")
    @SequenceGenerator(name = "hora_extra_secuencia", sequenceName = "SEQ_HEXTRA", allocationSize = 1)
    private int id;
	
    private String tipo_Extra;
    private String condicion;
    private int nivel_Aprobador;
    private double  multiplicador;
    private Date hini;
    private Date hfin;
    private String dia;

    
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TIPO_EXTRA")
    public String getTipoExtra() {
        return tipo_Extra;
    }

    public void setTipoExtra(String tipoExtra) {
        this.tipo_Extra = tipoExtra;
    }

    @Basic
    @Column(name = "CONDICION")
    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    @Basic
    @Column(name = "NIVEL_APROBADOR")
    public int getNivelAprobador() {
        return nivel_Aprobador;
    }

    public void setNivelAprobador(int nivelAprobador) {
        this.nivel_Aprobador = nivelAprobador;
    }

    @Basic
    @Column(name = "MULTIPLICADOR")
    public double getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    @Basic
    @Column(name = "HINI")
    public Date getHini() {
        return hini;
    }

    public void setHini(java.sql.Date hini) {
        this.hini = hini;
    }

    @Basic
    @Column(name = "HFIN")
    public Date getHfin() {
        return hfin;
    }

    public void setHfin(Date hfin) {
        this.hfin = hfin;
    }

    @Basic
    @Column(name = "DIA")
    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorasExtraEntity that = (HorasExtraEntity) o;
        return id == that.id && nivel_Aprobador == that.nivel_Aprobador && Double.compare(that.multiplicador, multiplicador) == 0 && Objects.equals(tipo_Extra, that.tipo_Extra) && Objects.equals(condicion, that.condicion) && Objects.equals(hini, that.hini) && Objects.equals(hfin, that.hfin) && Objects.equals(dia, that.dia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo_Extra, condicion, nivel_Aprobador, multiplicador, hini, hfin, dia);
    }
}