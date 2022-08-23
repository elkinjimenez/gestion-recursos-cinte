package com.claro.gestionrecursosapi.persona.domain;

import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.persona.entity.AcademicanivelEntity;

public interface IAcademicaNivelService {

	AcademicanivelEntity create(AcademicanivelEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion;

	AcademicanivelEntity update(int id, AcademicanivelEntity entity) throws NoExisteExcepcion, DataIncorrectaExcepcion;

	boolean delete(int id) throws NoExisteExcepcion;

	AcademicanivelEntity findById(int id) throws NoExisteExcepcion;

	Iterable<AcademicanivelEntity> findByNombre(String nombre) throws NoExisteExcepcion;

	Iterable<AcademicanivelEntity> findByJerarquia(int jerarquia) throws NoExisteExcepcion;

	Iterable<AcademicanivelEntity> findAll() throws NoExisteExcepcion;

}
