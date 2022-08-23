package com.claro.gestionrecursosapi.perfil.repository;

import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.perfil.entity.PerfilnivelEntity;

public interface IPerfilNivelRepository extends CrudRepository<PerfilnivelEntity, Integer>{

	Iterable<PerfilnivelEntity> findByNombreContains(String nombre);
}
