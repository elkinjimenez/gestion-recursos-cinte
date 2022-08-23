package com.claro.gestionrecursosweb.compromisosfabrica.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class CompromisosFabricaDto implements Serializable {
	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private Integer idCodProyecto;
	private Integer idCodPerfil;
	private Integer idCodPerfiLNivel;
	private Integer idCodPerfilTipo;
	private Integer idCodProveedor;
	private String codProyecto;
	private String codPerfil;
	private String codPerfiLNivel;
	private String codPerfilTipo;
	private String lineaProducto;
	private String codProveedor;
	private Integer horasHL;
	private Date fechaInicio;
	private Date fechaFin;
	private Timestamp fechaCreacion;
	private Timestamp fechaModificacion;
	private Integer horasLL;
	private String brief;
	private Integer idRlp;
	private Date fechaQaInicio;
	private Date fechaQaFin;
	private Date fechaDespliegue;
	private Date fechaDespliegueReal;
	private String idCambio;
	private String resultado;
	private String justificacion;
	private String descripcion;
	private Date fechaPresupuesto;
	private Integer horasReal;
	private String lt;
	private String pm;
	private String estado;
	private Integer fase;
	private Integer codEstructura;
	private Date fechaSolicitud;
	private String stopper;
	private String nombreEstructura;
	private String evidencia;

	public Integer getCodEstructura() {
		return codEstructura;
	}

	public void setCodEstructura(Integer codEstructura) {
		this.codEstructura = codEstructura;
	}

	public CompromisosFabricaDto() {

	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getIdCodProyecto() {
		return idCodProyecto;
	}

	public void setIdCodProyecto(Integer idCodProyecto) {
		this.idCodProyecto = idCodProyecto;
	}

	public Integer getIdCodPerfil() {
		return idCodPerfil;
	}

	public void setIdCodPerfil(Integer idCodPerfil) {
		this.idCodPerfil = idCodPerfil;
	}

	public Integer getIdCodPerfiLNivel() {
		return idCodPerfiLNivel;
	}

	public void setIdCodPerfiLNivel(Integer idCodPerfiLNivel) {
		this.idCodPerfiLNivel = idCodPerfiLNivel;
	}

	public Integer getIdCodPerfilTipo() {
		return idCodPerfilTipo;
	}

	public void setIdCodPerfilTipo(Integer idCodPerfilTipo) {
		this.idCodPerfilTipo = idCodPerfilTipo;
	}

	public Integer getIdCodProveedor() {
		return idCodProveedor;
	}

	public void setIdCodProveedor(Integer idCodProveedor) {
		this.idCodProveedor = idCodProveedor;
	}

	public String getCodProyecto() {
		return codProyecto;
	}

	public void setCodProyecto(String codProyecto) {
		this.codProyecto = codProyecto;
	}

	public String getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(String codPerfil) {
		this.codPerfil = codPerfil;
	}

	public String getCodPerfiLNivel() {
		return codPerfiLNivel;
	}

	public void setCodPerfiLNivel(String codPerfiLNivel) {
		this.codPerfiLNivel = codPerfiLNivel;
	}

	public String getCodPerfilTipo() {
		return codPerfilTipo;
	}

	public void setCodPerfilTipo(String codPerfilTipo) {
		this.codPerfilTipo = codPerfilTipo;
	}

	public String getLineaProducto() {
		return lineaProducto;
	}

	public void setLineaProducto(String lineaProducto) {
		this.lineaProducto = lineaProducto;
	}

	public String getCodProveedor() {
		return codProveedor;
	}

	public void setCodProveedor(String codProveedor) {
		this.codProveedor = codProveedor;
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

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getHorasLL() {
		return horasLL;
	}

	public void setHorasLL(Integer horasLL) {
		this.horasLL = horasLL;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getFase() {
		return fase;
	}

	public void setFase(Integer fase) {
		this.fase = fase;
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

	public String getNombreEstructura() {
		return nombreEstructura;
	}

	public void setNombreEstructura(String nombreEstructura) {
		this.nombreEstructura = nombreEstructura;
	}

	public String getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(String evidencia) {
		this.evidencia = evidencia;
	}
}
