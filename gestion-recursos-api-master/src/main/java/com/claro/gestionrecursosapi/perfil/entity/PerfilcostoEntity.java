package com.claro.gestionrecursosapi.perfil.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.claro.gestionrecursosapi.perfil.dto.PerfilCostoDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the perfilcosto database table.
 * 
 */

@Entity
@Table(name="DF_PERFILCOSTO")
//@IdClass(PerfilcostoPK.class)
public class PerfilcostoEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPerfilCosto")
	@SequenceGenerator(name = "seqPerfilCosto", sequenceName = "SEQ_PERFIL_COSTO", allocationSize = 1, initialValue = 1)
	private Integer id;

	@Min(value = 1, message = "Campo requerido")
	private Integer codperfil;

	@Min(value = 1, message = "Campo requerido")
	private Integer codproveedor;

	@Min(value = 1, message = "Campo requerido")
	private Integer codperfiltipo;

	@Min(value = 1, message = "Campo requerido")
	private Integer codperfilnivel;
	
	@Min(value = 1, message = "Campo requerido")
	private BigDecimal valor;
	
	@NotBlank(message = "Campo requerido")
	private String estado;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp fechacreacion;

	@UpdateTimestamp
	private Timestamp fechamodificacion;

	@Transient
	private List<PerfilCostoDto> listadoPerfilCosto;

	public PerfilcostoEntity() {
	}

	@PrePersist
	public void prePersist() {
		this.estado = "A";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getCodproveedor() {
		return codproveedor;
	}

	public void setCodproveedor(Integer codproveedor) {
		this.codproveedor = codproveedor;
	}

	public Integer getCodperfil() {
		return codperfil;
	}

	public void setCodperfil(Integer codperfil) {
		this.codperfil = codperfil;
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

	public List<PerfilCostoDto> getListadoPerfilCosto() {
		return listadoPerfilCosto;
	}

	public void setListadoPerfilCosto(List<PerfilCostoDto> listadoPerfilCosto) {
		this.listadoPerfilCosto = listadoPerfilCosto;
	}

}