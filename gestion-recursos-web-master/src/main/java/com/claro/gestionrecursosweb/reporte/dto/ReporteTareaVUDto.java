package com.claro.gestionrecursosweb.reporte.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReporteTareaVUDto {

	private Integer id;
	private Integer codproyecto;
	private String proyecto;
	private String tarearuta;
	private String tarea;
	private Integer codtareatipo;
	private String tareatipo;
	private Integer codtareaestado;
	private String tareaestado;
	private Integer codempleadocreo;
	private String empleadocreo;
	private Integer codempleadoasignado;
	private String empleadoasignado;
	private Boolean eslogro;
    private Integer codproveedor;
    private String proveedor;
    private Date fechainireal;
    private Date fechafinreal;
    private BigDecimal tiemporeal;
    private BigDecimal tiemporealacumulado;
    private Date fechainiestimada;
    private Date fechafinestimada;
    private BigDecimal tiempoestimado;
    private Date fechacreacion;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodproyecto() {
		return codproyecto;
	}
	public void setCodproyecto(Integer codproyecto) {
		this.codproyecto = codproyecto;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public String getTarearuta() {
		return tarearuta;
	}
	public void setTarearuta(String tarearuta) {
		this.tarearuta = tarearuta;
	}
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	public Integer getCodtareatipo() {
		return codtareatipo;
	}
	public void setCodtareatipo(Integer codtareatipo) {
		this.codtareatipo = codtareatipo;
	}
	public String getTareatipo() {
		return tareatipo;
	}
	public void setTareatipo(String tareatipo) {
		this.tareatipo = tareatipo;
	}
	public Integer getCodtareaestado() {
		return codtareaestado;
	}
	public void setCodtareaestado(Integer codtareaestado) {
		this.codtareaestado = codtareaestado;
	}
	public String getTareaestado() {
		return tareaestado;
	}
	public void setTareaestado(String tareaestado) {
		this.tareaestado = tareaestado;
	}
	public Integer getCodempleadocreo() {
		return codempleadocreo;
	}
	public void setCodempleadocreo(Integer codempleadocreo) {
		this.codempleadocreo = codempleadocreo;
	}
	public String getEmpleadocreo() {
		return empleadocreo;
	}
	public void setEmpleadocreo(String empleadocreo) {
		this.empleadocreo = empleadocreo;
	}
	public Integer getCodempleadoasignado() {
		return codempleadoasignado;
	}
	public void setCodempleadoasignado(Integer codempleadoasignado) {
		this.codempleadoasignado = codempleadoasignado;
	}
	public String getEmpleadoasignado() {
		return empleadoasignado;
	}
	public void setEmpleadoasignado(String empleadoasignado) {
		this.empleadoasignado = empleadoasignado;
	}
	public Boolean getEslogro() {
		return eslogro;
	}
	public void setEslogro(Boolean eslogro) {
		this.eslogro = eslogro;
	}
	public Integer getCodproveedor() {
		return codproveedor;
	}
	public void setCodproveedor(Integer codproveedor) {
		this.codproveedor = codproveedor;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public Date getFechainireal() {
		return fechainireal;
	}
	public void setFechainireal(Date fechainireal) {
		this.fechainireal = fechainireal;
	}
	public Date getFechafinreal() {
		return fechafinreal;
	}
	public void setFechafinreal(Date fechafinreal) {
		this.fechafinreal = fechafinreal;
	}
	public BigDecimal getTiemporeal() {
		return tiemporeal;
	}
	public void setTiemporeal(BigDecimal tiemporeal) {
		this.tiemporeal = tiemporeal;
	}
	public BigDecimal getTiemporealacumulado() {
		return tiemporealacumulado;
	}
	public void setTiemporealacumulado(BigDecimal tiemporealacumulado) {
		this.tiemporealacumulado = tiemporealacumulado;
	}
	public Date getFechainiestimada() {
		return fechainiestimada;
	}
	public void setFechainiestimada(Date fechainiestimada) {
		this.fechainiestimada = fechainiestimada;
	}
	public Date getFechafinestimada() {
		return fechafinestimada;
	}
	public void setFechafinestimada(Date fechafinestimada) {
		this.fechafinestimada = fechafinestimada;
	}
	public BigDecimal getTiempoestimado() {
		return tiempoestimado;
	}
	public void setTiempoestimado(BigDecimal tiempoestimado) {
		this.tiempoestimado = tiempoestimado;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
}
