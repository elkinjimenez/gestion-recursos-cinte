package com.claro.gestionrecursosapi.perfil.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.perfil.entity.PerfiltipoEntity;
import com.claro.gestionrecursosapi.perfil.repository.IPerfilTipoRepository;

@Service
@Transactional
public class PerfilTipoService implements ICrudService<PerfiltipoEntity, Integer>{

	@Autowired
	private IPerfilTipoRepository repository;
	
	@Override
	public PerfiltipoEntity save(PerfiltipoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<PerfiltipoEntity> saveAll(Iterable<PerfiltipoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<PerfiltipoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}
	
	public Iterable<PerfiltipoEntity> findByNombreContains(String nombre) {
		return repository.findByNombreContains(nombre);
	}
	
	@Override
	public Iterable<PerfiltipoEntity> findAll() {
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
	public void delete(PerfiltipoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<PerfiltipoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

}
