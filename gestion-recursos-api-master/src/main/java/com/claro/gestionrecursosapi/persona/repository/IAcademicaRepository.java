package com.claro.gestionrecursosapi.persona.repository;

import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.persona.entity.AcademicaEntity;

public interface IAcademicaRepository extends CrudRepository<AcademicaEntity, Integer> {
	
	Iterable<AcademicaEntity> findByCodpersona(Integer codPersona);
	
	Iterable<AcademicaEntity> findByTitulo(String titulo);
	
	Iterable<AcademicaEntity> findByInstitucion(String institucion);

}
