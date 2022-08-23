package com.claro.gestionrecursosapi.empleado.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class EmpleadoFormJefes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private String nombre;
    private Integer codpadre;
    private Integer codempleado_responsable;
    private Integer codestructuratipo;
    private String jerarquia;
  
    

    public EmpleadoFormJefes() {

    }



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Integer getCodpadre() {
		return codpadre;
	}



	public void setCodpadre(Integer codpadre) {
		this.codpadre = codpadre;
	}



	public Integer getCodempleado_responsable() {
		return codempleado_responsable;
	}



	public void setCodempleado_responsable(Integer codempleado_responsable) {
		this.codempleado_responsable = codempleado_responsable;
	}



	public Integer getCodestructuratipo() {
		return codestructuratipo;
	}



	public void setCodestructuratipo(Integer codestructuratipo) {
		this.codestructuratipo = codestructuratipo;
	}



	public String getJerarquia() {
		return jerarquia;
	}



	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}




  }
