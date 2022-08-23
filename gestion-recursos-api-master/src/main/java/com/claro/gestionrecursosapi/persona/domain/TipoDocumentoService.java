package com.claro.gestionrecursosapi.persona.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.persona.entity.TipoDocumentoEntity;
import com.claro.gestionrecursosapi.persona.repository.ITipoDocumentoRepository;

@Service
@Validated
public class TipoDocumentoService implements ICrudService<TipoDocumentoEntity, Integer> {

	@Autowired
	private ITipoDocumentoRepository repository;
	
	@Override
	public TipoDocumentoEntity save(TipoDocumentoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<TipoDocumentoEntity> saveAll(Iterable<TipoDocumentoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<TipoDocumentoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<TipoDocumentoEntity> findAll() {
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
	public void delete(TipoDocumentoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<TipoDocumentoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}
	
}
