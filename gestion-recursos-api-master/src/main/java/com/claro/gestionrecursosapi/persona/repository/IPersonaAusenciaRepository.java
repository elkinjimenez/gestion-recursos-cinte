package com.claro.gestionrecursosapi.persona.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.persona.entity.PersonaausenciaEntity;

public interface IPersonaAusenciaRepository extends CrudRepository<PersonaausenciaEntity, Integer> {

	PersonaausenciaEntity findByCodpersona(Integer codPersona);

	PersonaausenciaEntity findByCodausenciatipo(Integer codAusenciaTipo);

	Iterable<PersonaausenciaEntity> findAllByFechafinLessThanEqualAndFechainicioGreaterThanEqual(Date fechaInicio,
			Date fechaFin);
}
