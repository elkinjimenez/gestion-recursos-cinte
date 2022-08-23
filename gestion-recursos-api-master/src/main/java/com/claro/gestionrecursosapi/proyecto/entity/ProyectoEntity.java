package com.claro.gestionrecursosapi.proyecto.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the proyecto database table.
 * 
 */
@Entity
@Table(name="DF_PROYECTO")
public class ProyectoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "proyecto_Sequence")
	@SequenceGenerator(name = "proyecto_Sequence", sequenceName = "SEQ_PROYECTO", allocationSize = 1)
	private Integer id;	
	private Integer codproyectotipo;
	@JoinColumn(name = "codpresupuesto")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private PresupuestoEntity codpresupuesto;
	//private Integer codpresupuesto;
	private String codigoproyecto;
	private String nombre;
	private String descripcion;
	private Boolean prioritario;
	private Date fechainicio;
	private Date fechafin;
	@CreationTimestamp
	@Column(updatable = false)
	private Date fechacreacion;
	@UpdateTimestamp
	private Date fechamodificacion;
	private String estado;
	@Column(name = "NOMBRE_AMX")
	private String nombreAMX;
	@Column(name = "NOMBRE_LOCAL")
	private String nombreLocal;
	private String agrcapex;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodproyectotipo() {
		return codproyectotipo;
	}
	public void setCodproyectotipo(Integer codproyectotipo) {
		this.codproyectotipo = codproyectotipo;
	}
	/*public Integer getCodpresupuesto() {
		return codpresupuesto;
	}
	public void setCodpresupuesto(Integer codpresupuesto) {
		this.codpresupuesto = codpresupuesto;
	}*/

	public PresupuestoEntity getCodpresupuesto() {
		return codpresupuesto;
	}

	public void setCodpresupuesto(PresupuestoEntity codpresupuesto) {
		this.codpresupuesto = codpresupuesto;
	}

	public String getCodigoproyecto() {
		return codigoproyecto;
	}
	public void setCodigoproyecto(String codigoproyecto) {
		this.codigoproyecto = codigoproyecto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getPrioritario() {
		return prioritario;
	}
	public void setPrioritario(Boolean prioritario) {
		this.prioritario = prioritario;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombreAMX() {
		return nombreAMX;
	}

	public void setNombreAMX(String nombreAMX) {
		this.nombreAMX = nombreAMX;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public String getAgrcapex() {
		return agrcapex;
	}

	public void setAgrcapex(String agrcapex) {
		this.agrcapex = agrcapex;
	}
}