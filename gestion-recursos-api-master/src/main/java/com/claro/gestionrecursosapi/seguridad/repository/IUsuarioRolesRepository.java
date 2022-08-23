package com.claro.gestionrecursosapi.seguridad.repository;

import com.claro.gestionrecursosapi.seguridad.entity.UsuarioRolesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRolesRepository extends CrudRepository<UsuarioRolesEntity, Long> {

    @Query("SELECT ur FROM UsuarioRolesEntity ur WHERE ur.usuarioEntity.usuario = ?1 AND ur.usuarioRolEntity.id <> 3")
    public UsuarioRolesEntity obtenerUsuarioRoles(String usuarioClaro);

    @Query("SELECT ur FROM UsuarioRolesEntity ur WHERE ur.usuarioEntity.id = :idUsuario")
    public UsuarioRolesEntity findByIdUsuario(Integer idUsuario);
}
