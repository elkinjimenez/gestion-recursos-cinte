package com.claro.gestionrecursosapi.empleado.entity;

import javax.persistence.*;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.util.Date;

/**
 *
 */
@Entity
@Immutable
@Table(name = "DF_EMPLE_EST_ORGANIZACIONAL_VU")
@Subselect("select * from DF_EMPLE_EST_ORGANIZACIONAL_VU")
public class EmpleadoEstructuraOrganizacionalVU {
    @Id
    private Integer idEmpleado;
    private Integer idUsuario;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String estructuraNombre;
    private Integer estructuraId;
    private Integer estructuraIdPadre;
    private String estructuraJerarquia;
    private Integer codestructuraTipo;
    private Integer codempleadoresponsable;
    private Date fechafin;
    private Integer idEstructuraEmpleado;
    



    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstructuraNombre() {
        return estructuraNombre;
    }

    public void setEstructuraNombre(String estructuraNombre) {
        this.estructuraNombre = estructuraNombre;
    }

    public String getEstructuraJerarquia() {
        return estructuraJerarquia;
    }

    public void setEstructuraJerarquia(String estructuraJerarquia) {
        this.estructuraJerarquia = estructuraJerarquia;
    }

    public Integer getEstructuraId() {
        return estructuraId;
    }

    public void setEstructuraId(Integer estructuraId) {
        this.estructuraId = estructuraId;
    }

    public Integer getEstructuraIdPadre() {
        return estructuraIdPadre;
    }

    public void setEstructuraIdPadre(Integer estructuraIdPadre) {
        this.estructuraIdPadre = estructuraIdPadre;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getCodestructuraTipo() {
        return codestructuraTipo;
    }

    public void setCodestructuraTipo(Integer codestructuraTipo) {
        this.codestructuraTipo = codestructuraTipo;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }
    public Integer getIdEstructuraEmpleado() {
        return idEstructuraEmpleado;
    }

    public void setIdEstructuraEmpleado(Integer idEstructuraEmpleado) {
        this.idEstructuraEmpleado = idEstructuraEmpleado;
    }

	public Integer getCodempleadoresponsable() {
		return codempleadoresponsable;
	}

	public void setCodempleadoresponsable(Integer codempleadoresponsable) {
		this.codempleadoresponsable = codempleadoresponsable;
	}
    
    
}