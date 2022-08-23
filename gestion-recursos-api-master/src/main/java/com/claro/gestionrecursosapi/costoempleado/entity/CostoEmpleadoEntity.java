package com.claro.gestionrecursosapi.costoempleado.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the costo empleado database table.
 **/

@Entity
@Table(name="DF_COSTO_EMPLEADO")
public class CostoEmpleadoEntity {

    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "costo_Sequence")
    @SequenceGenerator(name = "costo_Sequence", sequenceName = "DF_COSTO_EMPLEADO_SEQ", allocationSize = 1)
    @Column(name = "ID_COSTO_EMPLEADO")
    private Integer id;
    @Column(name = "COSTO_PUNTO")
    private Integer costoPunto;
    @Column(name = "COSTO_MES")
    private Integer costoMes;
    @Column(name = "PUNTOS_MES")
    private Float puntosMes;
    @Column(name = "FACTOR_PUNTO")
    private Float factorPunto;
    private String modalidad;
    @Column(name = "CODEMPLEADO")
    private Integer codEmpleado;
    private Date desde;
    private Date hasta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCostoPunto() {
        return costoPunto;
    }

    public void setCostoPunto(Integer costoPunto) {
        this.costoPunto = costoPunto;
    }

    public Integer getCostoMes() {
        return costoMes;
    }

    public void setCostoMes(Integer costoMes) {
        this.costoMes = costoMes;
    }

    public Float getPuntosMes() {
        return puntosMes;
    }

    public void setPuntosMes(Float puntosMes) {
        this.puntosMes = puntosMes;
    }

    public Float getFactorPunto() {
        return factorPunto;
    }

    public void setFactorPunto(Float factorPunto) {
        this.factorPunto = factorPunto;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Integer getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(Integer codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
}
