package com.claro.gestionrecursosapi.perfil.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.perfil.entity.PerfilnivelEntity;
import com.claro.gestionrecursosapi.perfil.repository.IPerfilNivelRepository;

@Service
@Transactional
public class PerfilNivelService implements ICrudService<PerfilnivelEntity, Integer> {

	@Autowired
	private IPerfilNivelRepository repository;
	
	@Override
	public PerfilnivelEntity save(PerfilnivelEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<PerfilnivelEntity> saveAll(Iterable<PerfilnivelEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<PerfilnivelEntity> findById(Integer id) {
		return repository.findById(id);
	}

	public Iterable<PerfilnivelEntity> findByNombreContains(String nombre) {
		return repository.findByNombreContains(nombre);
	}
	
	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<PerfilnivelEntity> findAll() {
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
	public void delete(PerfilnivelEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<PerfilnivelEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

}
