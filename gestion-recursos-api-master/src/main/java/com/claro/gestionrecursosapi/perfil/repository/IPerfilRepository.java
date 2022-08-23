package com.claro.gestionrecursosapi.perfil.repository;

import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.perfil.entity.PerfilEntity;

public interface IPerfilRepository extends CrudRepository<PerfilEntity, Integer>{
	
	Iterable<PerfilEntity> findByEstadoContains(String estado);
	
	Iterable<PerfilEntity> findByNombreContains(String nombre);
}
