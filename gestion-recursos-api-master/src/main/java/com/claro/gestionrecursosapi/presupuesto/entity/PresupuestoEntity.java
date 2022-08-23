package com.claro.gestionrecursosapi.presupuesto.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="DF_PRESUPUESTO")
public class PresupuestoEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "presupuesto_Sequence")
	@SequenceGenerator(name = "presupuesto_Sequence", sequenceName = "SEQ_PRESUPUESTO", allocationSize = 1)
	private Integer id;
	private Integer codempleadogerente;
	private String elemento_pep;
	private String pocisionpresupuestal;
	private BigDecimal presupuesto_cop;
	private BigDecimal presupuesto_ussd;
	private BigDecimal saldo_cop;
	private BigDecimal saldo_ussd;
	private Integer vigencia;
	@CreationTimestamp
	@Column(updatable = false)
	private Date fechacreacion;
	@UpdateTimestamp
	private Date fechamodificacion;
	@Column(name = "IDPROYECTO")
	private String idProyecto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodempleadogerente() {
		return codempleadogerente;
	}
	public void setCodempleadogerente(Integer codempleadogerente) {
		this.codempleadogerente = codempleadogerente;
	}
	public String getElemento_pep() {
		return elemento_pep;
	}
	public void setElemento_pep(String elemento_pep) {
		this.elemento_pep = elemento_pep;
	}
	public String getPocisionpresupuestal() {
		return pocisionpresupuestal;
	}
	public void setPocisionpresupuestal(String pocisionpresupuestal) {
		this.pocisionpresupuestal = pocisionpresupuestal;
	}
	public BigDecimal getPresupuesto_cop() {
		return presupuesto_cop;
	}
	public void setPresupuesto_cop(BigDecimal presupuesto_cop) {
		this.presupuesto_cop = presupuesto_cop;
	}
	public BigDecimal getPresupuesto_ussd() {
		return presupuesto_ussd;
	}
	public void setPresupuesto_ussd(BigDecimal presupuesto_ussd) {
		this.presupuesto_ussd = presupuesto_ussd;
	}
	public BigDecimal getSaldo_cop() {
		return saldo_cop;
	}
	public void setSaldo_cop(BigDecimal saldo_cop) {
		this.saldo_cop = saldo_cop;
	}
	public BigDecimal getSaldo_ussd() {
		return saldo_ussd;
	}
	public void setSaldo_ussd(BigDecimal saldo_ussd) {
		this.saldo_ussd = saldo_ussd;
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
	public String getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Integer getVigencia() {
		return vigencia;
	}

	public void setVigencia(Integer vigencia) {
		this.vigencia = vigencia;
	}
}