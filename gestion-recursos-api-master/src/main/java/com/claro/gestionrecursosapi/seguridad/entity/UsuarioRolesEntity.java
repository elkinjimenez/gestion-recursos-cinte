package com.claro.gestionrecursosapi.seguridad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DF_USUARIO_ROLES")
public class UsuarioRolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuarioRoles")
    @SequenceGenerator(name = "seqUsuarioRoles", sequenceName = "SEQ_USUARIO_ROLES", initialValue = 1, allocationSize = 1)
    private Long id;
    @JoinColumn(name = "codusuario", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnore
    private UsuarioEntity usuarioEntity;
    @JoinColumn(name = "codusuariorol", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private UsuarioRolEntity usuarioRolEntity;

    public UsuarioRolesEntity() {

    }

    public UsuarioRolesEntity(UsuarioEntity usuarioEntity, UsuarioRolEntity usuarioRolEntity) {
        this.usuarioEntity = usuarioEntity;
        this.usuarioRolEntity = usuarioRolEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public UsuarioRolEntity getUsuarioRolEntity() {
        return usuarioRolEntity;
    }

    public void setUsuarioRolEntity(UsuarioRolEntity usuarioRolEntity) {
        this.usuarioRolEntity = usuarioRolEntity;
    }
}
