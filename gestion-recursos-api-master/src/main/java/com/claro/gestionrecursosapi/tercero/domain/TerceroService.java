package com.claro.gestionrecursosapi.tercero.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.tercero.entity.TerceroEntity;
import com.claro.gestionrecursosapi.tercero.repository.ITerceroRepository;

@Service
@Validated
public class TerceroService implements ICrudService<TerceroEntity, Integer> {

	@Autowired
	private ITerceroRepository repository;
	
	@Override
	public TerceroEntity save(TerceroEntity entity) {
		return repository.save(entity);
	}
	
	@Override
	public Iterable<TerceroEntity> saveAll(Iterable<TerceroEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<TerceroEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<TerceroEntity> findAll() {
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
	public void delete(TerceroEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<TerceroEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}	
	
}