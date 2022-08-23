package com.claro.gestionrecursosapi.perfil.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.perfil.entity.LineasproductoEntity;
import com.claro.gestionrecursosapi.perfil.repository.ILineaProductoRepository;

@Service
public class LineaProductoService implements ICrudService<LineasproductoEntity, Integer> {

	@Autowired
	private ILineaProductoRepository repository;

	@Override
	public LineasproductoEntity save(LineasproductoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<LineasproductoEntity> saveAll(Iterable<LineasproductoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<LineasproductoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<LineasproductoEntity> findAll() {
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
	public void delete(LineasproductoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<LineasproductoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}
	
}
