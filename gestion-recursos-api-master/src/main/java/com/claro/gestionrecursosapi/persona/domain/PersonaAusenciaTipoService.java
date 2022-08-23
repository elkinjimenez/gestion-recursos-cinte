package com.claro.gestionrecursosapi.persona.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.persona.entity.PersonaausenciatipoEntity;
import com.claro.gestionrecursosapi.persona.repository.IPersonaAusenciaTipoRepository;

@Service		
@Transactional
public class PersonaAusenciaTipoService implements ICrudService<PersonaausenciatipoEntity, Integer>{

	@Autowired
	private IPersonaAusenciaTipoRepository repository;

	@Override
	public PersonaausenciatipoEntity save(PersonaausenciatipoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<PersonaausenciatipoEntity> saveAll(Iterable<PersonaausenciatipoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<PersonaausenciatipoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<PersonaausenciatipoEntity> findAll() {
		return repository.findAll();
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
	public void delete(PersonaausenciatipoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<PersonaausenciatipoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
	
	
}
