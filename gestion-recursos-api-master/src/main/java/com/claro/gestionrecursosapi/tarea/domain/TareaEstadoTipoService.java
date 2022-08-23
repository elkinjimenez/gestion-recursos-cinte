package com.claro.gestionrecursosapi.tarea.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.tarea.entity.TareaEstadoTipoEntity;
import com.claro.gestionrecursosapi.tarea.repository.ITareaEstadoTipoRepository;

@Service
@Validated
public class TareaEstadoTipoService implements ICrudService<TareaEstadoTipoEntity, Integer> {

	@Autowired
	private ITareaEstadoTipoRepository repository;
	
	@Override
	public TareaEstadoTipoEntity save(TareaEstadoTipoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<TareaEstadoTipoEntity> saveAll(Iterable<TareaEstadoTipoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<TareaEstadoTipoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<TareaEstadoTipoEntity> findAll() {
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
	public void delete(TareaEstadoTipoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<TareaEstadoTipoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}	
	
}