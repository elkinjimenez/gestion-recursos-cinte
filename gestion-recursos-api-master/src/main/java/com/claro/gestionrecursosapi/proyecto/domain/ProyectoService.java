package com.claro.gestionrecursosapi.proyecto.domain;

import java.util.List;
import java.util.Optional;
import com.claro.gestionrecursosapi.excepcion.BusinessException;
import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoEntity;
import com.claro.gestionrecursosapi.presupuesto.repository.IPresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.proyecto.entity.ProyectoEntity;
import com.claro.gestionrecursosapi.proyecto.repository.IProyectoRepository;

@Service
@Validated
public class ProyectoService implements ICrudService<ProyectoEntity, Integer> {

	@Autowired
	private IProyectoRepository repository;

	@Autowired
	private IPresupuestoRepository presupuestoRepository;
	
	@Override
	@Transactional
	public ProyectoEntity save(ProyectoEntity entity) throws BusinessException {
		Optional<PresupuestoEntity> presupuesto = presupuestoRepository.findById(entity.getCodpresupuesto().getId());

		if (!presupuesto.isPresent()) {
			throw new BusinessException("El id de presupuesto '" + entity.getCodpresupuesto().getId() +"' no existe");
		}
		
		entity.setCodpresupuesto(presupuesto.get());
		
		ProyectoEntity proyectoEntity = repository.save(entity);
		return proyectoEntity;
	}

	@Transactional
	public ProyectoEntity update(ProyectoEntity entity) throws BusinessException {
		Optional<ProyectoEntity> op = repository.findById(entity.getId());

		if (!op.isPresent()) {
			throw new BusinessException("El id de proyecto '" + entity.getId() +"' no existe");
		}

		Optional<PresupuestoEntity> presupuesto = presupuestoRepository.findById(entity.getCodpresupuesto().getId());

		if (!presupuesto.isPresent()) {
			throw new BusinessException("El id de presupuesto '" + entity.getCodpresupuesto().getId() +"' no existe");
		}

		ProyectoEntity proyecto = op.get();
		proyecto.setCodproyectotipo(entity.getCodproyectotipo());
		proyecto.setCodpresupuesto(presupuesto.get());
		proyecto.setCodigoproyecto(entity.getCodigoproyecto());
		proyecto.setNombre(entity.getNombre());
		proyecto.setFechainicio(entity.getFechainicio());
		proyecto.setFechafin(entity.getFechafin());
		proyecto.setPrioritario(entity.getPrioritario());
		proyecto.setDescripcion(entity.getDescripcion());

		return repository.save(proyecto);
	}

	@Override
	public Iterable<ProyectoEntity> saveAll(Iterable<ProyectoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<ProyectoEntity> findById(Integer id) {
		Optional<ProyectoEntity> oProyectoEntity = repository.findById(id);
		return oProyectoEntity;
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<ProyectoEntity> findAll() {
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
	public void delete(ProyectoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<ProyectoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}	

	public Iterable<ProyectoEntity> getProyectosWithCompromiso(){
		return repository.findProyectosWithCompromisos();
	}

	public Iterable<ProyectoEntity> getProyectosGantt(){
		return repository.findProyectosGantt();
	}
}
