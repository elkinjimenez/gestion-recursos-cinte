package com.claro.gestionrecursosapi.tarea.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.tarea.entity.TareaTipoEntity;
import com.claro.gestionrecursosapi.tarea.repository.ITareaTipoRepository;

@Service
@Validated
public class TareaTipoService implements ICrudService<TareaTipoEntity, Integer> {

	@Autowired
	private ITareaTipoRepository repository;
	
	@Override
	public TareaTipoEntity save(TareaTipoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<TareaTipoEntity> saveAll(Iterable<TareaTipoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<TareaTipoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<TareaTipoEntity> findAll() {
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
	public void delete(TareaTipoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<TareaTipoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}	
}
