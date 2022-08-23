package com.claro.gestionrecursosapi.compromisosfabrica.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The persistent class for the DF_PRY_BRIEF_IDRLP database table.
 * 
 */

@Entity
@Table(name = "DF_PRY_BRIEF_IDRLP")
public class CompromisosFabricaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer Id;
	@Min(value = 1, message = "Campo requerido")
	Integer codProyecto;
	@Min(value = 1, message = "Campo requerido")
	private Integer codperfil;
	@Min(value = 1, message = "Campo requerido")
	private Integer codproveedor;
	@Min(value = 1, message = "Campo requerido")
	private Integer codperfiltipo;
	@Min(value = 1, message = "Campo requerido")
	private Integer codperfilnivel;
	@Column
	private String lineaProducto;
	@Column(name = "HORAS_HL")
	private Integer horasHL;
	@Column
	private Date fechaInicio;
	@Column
	private Date fechaFin;
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp fechacreacion;
	@UpdateTimestamp
	private Timestamp fechamodificacion;
	@Column(name = "HORAS_LL")
	private Integer horarLL;
	@Column
	private String brief;
	@Column
	private Integer idRlp;
	@Column(name = "FECHAQAINI")
	private Date fechaQaInicio;
	@Column
	private Date fechaQaFin;
	@Column(name = "FECHA_DESPLIEGUE")
	private Date fechaDespliegue;
	@Column(name = "FECHA_DESP_REAL")
	private Date fechaDespliegueReal;
	@Column(name = "ID_CAMBIO")
	private String idCambio;
	@Column
	private String resultado;
	@Column
	private String justificacion;
	@Column
	private String descripcion;
	@Column(name = "FECHA_PRESUPUESTO")
	private Date fechaPresupuesto;
	@Column(name = "HORAS_REAL")
	private Integer horasReal;
	@Column
	private String lt;
	@Column
	private String pm;
	@NotBlank(message = "Campo requerido")
	private String estado;
	@Column
	private Integer fase;
	@Column
	private Integer codEstructura;
	@Column(name = "FECHA_SOLICITUD")
	private Date fechaSolicitud;
	private String stopper;
	private String evidencia;

	public CompromisosFabricaEntity() {
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodificacion() {
		return this.fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public Integer getCodperfilnivel() {
		return codperfilnivel;
	}

	public void setCodperfilnivel(Integer codperfilnivel) {
		this.codperfilnivel = codperfilnivel;
	}

	public Integer getCodperfiltipo() {
		return codperfiltipo;
	}

	public void setCodperfiltipo(Integer codperfiltipo) {
		this.codperfiltipo = codperfiltipo;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getCodProyecto() {
		return codProyecto;
	}

	public void setCodProyecto(Integer codProyecto) {
		this.codProyecto = codProyecto;
	}

	public Integer getCodperfil() {
		return codperfil;
	}

	public void setCodperfil(Integer codperfil) {
		this.codperfil = codperfil;
	}

	public String getLineaProducto() {
		return lineaProducto;
	}

	public void setLineaProducto(String lineaProducto) {
		this.lineaProducto = lineaProducto;
	}

	public Integer getCodproveedor() {
		return codproveedor;
	}

	public void setCodproveedor(Integer codproveedor) {
		this.codproveedor = codproveedor;
	}

	public Integer getHorasHL() {
		return horasHL;
	}

	public void setHorasHL(Integer horasHL) {
		this.horasHL = horasHL;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getHorarLL() {
		return horarLL;
	}

	public void setHorarLL(Integer horarLL) {
		this.horarLL = horarLL;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Integer getIdRlp() {
		return idRlp;
	}

	public void setIdRlp(Integer idRlp) {
		this.idRlp = idRlp;
	}

	public Date getFechaQaInicio() {
		return fechaQaInicio;
	}

	public void setFechaQaInicio(Date fechaQaInicio) {
		this.fechaQaInicio = fechaQaInicio;
	}

	public Date getFechaQaFin() {
		return fechaQaFin;
	}

	public void setFechaQaFin(Date fechaQaFin) {
		this.fechaQaFin = fechaQaFin;
	}

	public Date getFechaDespliegue() {
		return fechaDespliegue;
	}

	public void setFechaDespliegue(Date fechaDespliegue) {
		this.fechaDespliegue = fechaDespliegue;
	}

	public Date getFechaDespliegueReal() {
		return fechaDespliegueReal;
	}

	public void setFechaDespliegueReal(Date fechaDespliegueReal) {
		this.fechaDespliegueReal = fechaDespliegueReal;
	}

	public String getIdCambio() {
		return idCambio;
	}

	public void setIdCambio(String idCambio) {
		this.idCambio = idCambio;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaPresupuesto() {
		return fechaPresupuesto;
	}

	public void setFechaPresupuesto(Date fechaPresupuesto) {
		this.fechaPresupuesto = fechaPresupuesto;
	}

	public Integer getHorasReal() {
		return horasReal;
	}

	public void setHorasReal(Integer horasReal) {
		this.horasReal = horasReal;
	}

	public String getLt() {
		return lt;
	}

	public void setLt(String lt) {
		this.lt = lt;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public Integer getFase() {
		return fase;
	}

	public void setFase(Integer fase) {
		this.fase = fase;
	}

	public Integer getCodEstructura() {
		return codEstructura;
	}

	public void setCodEstructura(Integer value) {
		codEstructura = value;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getStopper() {
		return stopper;
	}

	public void setStopper(String stopper) {
		this.stopper = stopper;
	}

	public String getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(String evidencia) {
		this.evidencia = evidencia;
	}
}