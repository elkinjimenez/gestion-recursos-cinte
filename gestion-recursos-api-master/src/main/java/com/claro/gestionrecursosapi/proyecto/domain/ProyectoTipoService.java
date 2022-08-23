package com.claro.gestionrecursosapi.proyecto.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.proyecto.entity.ProyectoTipoEntity;
import com.claro.gestionrecursosapi.proyecto.repository.IProyectoTipoRepository;

@Service
@Validated
public class ProyectoTipoService implements ICrudService<ProyectoTipoEntity, Integer> {

	@Autowired
	private IProyectoTipoRepository repository;
	
	@Override
	public ProyectoTipoEntity save(ProyectoTipoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<ProyectoTipoEntity> saveAll(Iterable<ProyectoTipoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<ProyectoTipoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<ProyectoTipoEntity> findAll() {
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
	public void delete(ProyectoTipoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<ProyectoTipoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}
	
}
