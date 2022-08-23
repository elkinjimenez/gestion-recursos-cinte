package com.claro.gestionrecursosweb.reporte.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReporteRhPagosDto {

	private Long id;
	private String recurso;
	private Long codEmpleado;
	private Long codProyecto;
	private Long idRecurso;
	private String proveedor;
	private String tipoPresupuesto;
	private String IdProyecto;
	private String pep;
	private String nombreAmx;
	private String conPresupuesto;
	private String tipoProyecto;
	private String proyecto;
	private Date fecha;
	private Long semana;
	private BigDecimal porcentajeProyecto;
	private BigDecimal costoRecursos;
	private BigDecimal costoxPagar;
	private String gerente;
	private String aprobador;
	private String celula;

	private ReporteRhPagosDto() {

	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmmpleado(Long codEmpleado) {
		this.codEmpleado = codEmpleado;
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
