package com.claro.gestionrecursosapi.persona.domain;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.persona.entity.PersonaausenciaEntity;
import com.claro.gestionrecursosapi.persona.repository.IPersonaAusenciaRepository;

@Service
@Transactional
public class PersonaAusenciaService implements ICrudService<PersonaausenciaEntity, Integer> {

	@Autowired
	private IPersonaAusenciaRepository repository;

	@Override
	public PersonaausenciaEntity save(PersonaausenciaEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<PersonaausenciaEntity> saveAll(Iterable<PersonaausenciaEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<PersonaausenciaEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<PersonaausenciaEntity> findAll() {
		return repository.findAll();
	}

	public PersonaausenciaEntity findByCodpersona(Integer codPersona) {
		return repository.findByCodpersona(codPersona);
	}

	public PersonaausenciaEntity findByAusenciatipo(Integer codAusenciaTipo) {
		return repository.findByCodausenciatipo(codAusenciaTipo);
	}

	public Iterable<PersonaausenciaEntity> findAllBetweenFechaInicioAndFechaFin(Date fechainicio, Date fechafin) {
		return repository.findAllByFechafinLessThanEqualAndFechainicioGreaterThanEqual(fechafin, fechainicio);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(PersonaausenciaEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<PersonaausenciaEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

}
