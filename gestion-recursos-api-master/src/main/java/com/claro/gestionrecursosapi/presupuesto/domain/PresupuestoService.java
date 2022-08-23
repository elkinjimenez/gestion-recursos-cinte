package com.claro.gestionrecursosapi.presupuesto.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoEntity;
import com.claro.gestionrecursosapi.presupuesto.repository.IPresupuestoRepository;

@Service
@Validated
public class PresupuestoService implements ICrudService<PresupuestoEntity, Integer> {

	@Autowired
	private IPresupuestoRepository repository;

	@Override
	public PresupuestoEntity save(PresupuestoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<PresupuestoEntity> saveAll(Iterable<PresupuestoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<PresupuestoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<PresupuestoEntity> findAll() {
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
	public void delete(PresupuestoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<PresupuestoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}

	public Optional<PresupuestoEntity> buscarPresupuestoPorProyecto(int codProyecto) {
		try {
			List<PresupuestoEntity> presupuesto = repository.buscarPresupuestoPorProyecto(codProyecto);
			return presupuesto.stream().findFirst();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}
