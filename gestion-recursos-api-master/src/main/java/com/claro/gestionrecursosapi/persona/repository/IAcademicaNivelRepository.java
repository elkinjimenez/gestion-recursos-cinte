package com.claro.gestionrecursosapi.persona.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.persona.entity.AcademicanivelEntity;

public interface IAcademicaNivelRepository extends CrudRepository<AcademicanivelEntity, Integer> {

	Iterable<AcademicanivelEntity> findByNombre(String nombre);

	Iterable<AcademicanivelEntity> findByJerarquia(int jerarquia);
}
