package com.claro.gestionrecursosapi.persona.domain;

import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.persona.entity.AcademicaEntity;

public interface IAcademicaService {

	AcademicaEntity create(AcademicaEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion;

	AcademicaEntity update(int id, AcademicaEntity entity) throws NoExisteExcepcion, DataIncorrectaExcepcion;

	boolean delete(int id) throws NoExisteExcepcion;

	Iterable<AcademicaEntity> findByCodpersona(Integer codPersona) throws NoExisteExcepcion;

	Iterable<AcademicaEntity> findByTitulo(String titulo) throws NoExisteExcepcion;
	
	Iterable<AcademicaEntity> findByInstitucion(String institucion) throws NoExisteExcepcion;
	
	AcademicaEntity findById(int id) throws NoExisteExcepcion;

	Iterable<AcademicaEntity> findAll() throws NoExisteExcepcion;

}
