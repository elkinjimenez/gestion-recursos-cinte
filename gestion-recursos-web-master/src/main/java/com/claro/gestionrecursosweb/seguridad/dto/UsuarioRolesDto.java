package com.claro.gestionrecursosweb.seguridad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UsuarioRolesDto {

    private Long id;

    private UsuarioDto usuarioEntity;
    private UsuarioRolDto usuarioRolEntity;

    public UsuarioRolesDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDto getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioDto usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public UsuarioRolDto getUsuarioRolEntity() {
        return usuarioRolEntity;
    }

    public void setUsuarioRolEntity(UsuarioRolDto usuarioRolEntity) {
        this.usuarioRolEntity = usuarioRolEntity;
    }
}
