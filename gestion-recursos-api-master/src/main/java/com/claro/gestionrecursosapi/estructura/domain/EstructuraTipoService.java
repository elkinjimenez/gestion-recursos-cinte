package com.claro.gestionrecursosapi.estructura.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.estructura.entity.EstructuratipoEntity;
import com.claro.gestionrecursosapi.estructura.repository.IEstructuraTipoRepository;

@Service
@Validated
public class EstructuraTipoService implements ICrudService<EstructuratipoEntity, Integer> {
	@Autowired
	private IEstructuraTipoRepository repository;

	@Override
	public EstructuratipoEntity save(EstructuratipoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<EstructuratipoEntity> saveAll(Iterable<EstructuratipoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<EstructuratipoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<EstructuratipoEntity> findAll() {
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
	public void delete(EstructuratipoEntity entity) {
		repository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<EstructuratipoEntity> entities) {
		repository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		
		
	}

	
}
