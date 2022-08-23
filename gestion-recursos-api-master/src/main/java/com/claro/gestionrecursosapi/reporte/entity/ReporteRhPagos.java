package com.claro.gestionrecursosapi.reporte.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DF_INF_RRHHPAGOS_V")
public class ReporteRhPagos {

	@Id
	private Long id;
	@Column(name = "RECURSO")
	private String recurso;
	@Column(name = "CODEMPLEADO")
	private Long codEmmpleado;
	@Column(name = "CODPROYECTO")
	private Long codProyecto;
	@Column(name = "IDRECURSO")
	private Long idRecurso;
	@Column(name = "PROVEEDOR")
	private String proveedor;
	@Column(name = "TIPOPRESUPUESTO")
	private String tipoPresupuesto;
	@Column(name = "IDPROYECTO")
	private String IdProyecto;
	@Column(name = "PEP")
	private String pep;
	@Column(name = "NOMBRE_AMX")
	private String nombreAmx;
	@Column(name = "CONPRESUPUESTO")
	private String conPresupuesto;
	@Column(name = "TIPOPROYECTO")
	private String tipoProyecto;
	@Column(name = "PROYECTO")
	private String proyecto;
	@Column(name = "FECHA")
	private Date fecha;
	@Column(name = "SEMANA")
	private Long semana;
	@Column(name = "PORCENTAJEPROYECTO")
	private BigDecimal porcentajeProyecto;
	@Column(name = "COSTORECURSOMES")
	private BigDecimal costoRecursos;
	@Column(name = "COSTOXPAGAR")
	private BigDecimal costoxPagar;
	@Column(name = "GERENTE")
	private String gerente;
	@Column(name = "APROBADOR")
	private String aprobador;
	@Column(name = "CELULA")
	private String celula;

	public ReporteRhPagos() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public Long getCodEmmpleado() {
		return codEmmpleado;
	}

	public void setCodEmmpleado(Long codEmmpleado) {
		this.codEmmpleado = codEmmpleado;
	}

	public Long getCodProyecto() {
		return codProyecto;
	}

	public void setCodProyecto(Long codProyecto) {
		this.codProyecto = codProyecto;
	}

	public Long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Long idRecurso) {
		this.idRecurso = idRecurso;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getTipoPresupuesto() {
		return tipoPresupuesto;
	}

	public void setTipoPresupuesto(String tipoPresupuesto) {
		this.tipoPresupuesto = tipoPresupuesto;
	}

	public String getIdProyecto() {
		return IdProyecto;
	}

	public void setIdProyecto(String idProyecto) {
		IdProyecto = idProyecto;
	}

	public String getPep() {
		return pep;
	}

	public void setPep(String pep) {
		this.pep = pep;
	}

	public String getNombreAmx() {
		return nombreAmx;
	}

	public void setNombreAmx(String nombreAmx) {
		this.nombreAmx = nombreAmx;
	}

	public String getConPresupuesto() {
		return conPresupuesto;
	}

	public void setConPresupuesto(String conPresupuesto) {
		this.conPresupuesto = conPresupuesto;
	}

	public String getTipoProyecto() {
		return tipoProyecto;
	}

	public void setTipoProyecto(String tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getSemana() {
		return semana;
	}

	public void setSemana(Long semana) {
		this.semana = semana;
	}

	public BigDecimal getPorcentajeProyecto() {
		return porcentajeProyecto;
	}

	public void setPorcentajeProyecto(BigDecimal porcentajeProyecto) {
		this.porcentajeProyecto = porcentajeProyecto;
	}

	public BigDecimal getCostoRecursos() {
		return costoRecursos;
	}

	public void setCostoRecursos(BigDecimal costoRecursos) {
		this.costoRecursos = costoRecursos;
	}

	public BigDecimal getCostoxPagar() {
		return costoxPagar;
	}

	public void setCostoxPagar(BigDecimal costoxPagar) {
		this.costoxPagar = costoxPagar;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	public String getAprobador() {
		return aprobador;
	}

	public void setAprobador(String aprobador) {
		this.aprobador = aprobador;
	}

	public String getCelula() {
		return celula;
	}

	public void setCelula(String celula) {
		this.celula = celula;
	}

}
