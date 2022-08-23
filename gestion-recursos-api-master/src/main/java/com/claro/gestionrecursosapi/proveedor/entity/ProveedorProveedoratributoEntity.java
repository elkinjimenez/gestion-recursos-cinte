package com.claro.gestionrecursosapi.proveedor.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the proveedor_proveedoratributo database table.
 * 
 */
@Entity
@Table(name="DF_PROVEEDOR_PROVEEDORATRIBUTO")
public class ProveedorProveedoratributoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "proveedor_proveedoratributo_Sequence")
	@SequenceGenerator(name = "proveedor_proveedoratributo_Sequence", sequenceName = "SEQ_PROVEEDOR_PROVEEDORATRIBUT", allocationSize = 1)
	private int id;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp fechacreacion;

	@UpdateTimestamp
	private Timestamp fechamodificacion;

	@Min(value = 1, message = "Campo requerido")
	private Integer codproveedor;

	@Min(value = 1, message = "Campo requerido")
	private Integer codproveedoratributo;

	public ProveedorProveedoratributoEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Integer getCodproveedor() {
		return codproveedor;
	}

	public void setCodproveedor(Integer codproveedor) {
		this.codproveedor = codproveedor;
	}

	public Integer getCodproveedoratributo() {
		return codproveedoratributo;
	}

	public void setCodproveedoratributo(Integer codproveedoratributo) {
		this.codproveedoratributo = codproveedoratributo;
	}
}