package com.claro.gestionrecursosapi.tarea.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.tarea.entity.TareaEstadoEntity;
import com.claro.gestionrecursosapi.tarea.repository.ITareaEstadoRepository;

@Service
@Validated
public class TareaEstadoService implements ICrudService<TareaEstadoEntity, Integer> {

	@Autowired
	private ITareaEstadoRepository repository;
	
	@Override
	public TareaEstadoEntity save(TareaEstadoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<TareaEstadoEntity> saveAll(Iterable<TareaEstadoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<TareaEstadoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<TareaEstadoEntity> findAll() {
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
	public void delete(TareaEstadoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<TareaEstadoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}	
	
}