package com.claro.gestionrecursosapi.novedad.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

//@Entity
//@Immutable
//@Subselect(value = "select novedad, tipoNovedad.nombre as tipoNovedadNombre from NovedadEntity novedad " +
//		"inner join TipoNovedadEntity tipoNovedad on tipoNovedad.id = novedad.codTipoNovedad " +
//		"where novedad.estadoRegistro = 1 order by novedad.id desc")
//public class NovedadDtoEntity extends NovedadEntity {
//	private String tipoNovedadNombre;
////	private String proyectoNombre;
////	private String empleadoNombre;
//
//	public String getTipoNovedadNombre() {
//		return tipoNovedadNombre;
//	}
//	public void setTipoNovedadNombre(String tipoNovedadNombre) {
//		this.tipoNovedadNombre = tipoNovedadNombre;
//	}
////	public String getProyectoNombre() {
////		return proyectoNombre;
////	}
////	public void setProyectoNombre(String proyectoNombre) {
////		this.proyectoNombre = proyectoNombre;
////	}
////	public String getEmpleadoNombre() {
////		return empleadoNombre;
////	}
////	public void setEmpleadoNombre(String empleadoNombre) {
////		this.empleadoNombre = empleadoNombre;
////	}	
//}
