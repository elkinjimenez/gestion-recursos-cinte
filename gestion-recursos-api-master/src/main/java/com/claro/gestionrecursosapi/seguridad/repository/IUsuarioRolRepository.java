package com.claro.gestionrecursosapi.seguridad.repository;

import com.claro.gestionrecursosapi.seguridad.entity.UsuarioRolEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRolRepository extends CrudRepository<UsuarioRolEntity, Integer> {
}
