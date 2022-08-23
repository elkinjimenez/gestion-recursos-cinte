package com.claro.gestionrecursosapi.perfil.repository;

import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.perfil.entity.PerfiltipoEntity;

public interface IPerfilTipoRepository extends CrudRepository<PerfiltipoEntity, Integer> {

	Iterable<PerfiltipoEntity> findByNombreContains(String nombre);
}
