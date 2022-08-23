package com.claro.gestionrecursosapi.tarea.domain;

import java.io.IOException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.claro.gestionrecursosapi.devops.domain.OcupacionImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.tarea.entity.TareaEntity;
import com.claro.gestionrecursosapi.tarea.repository.ITareaRepository;

@Service
@Validated
public class TareaService implements ICrudService<TareaEntity, Integer> {

	@Autowired
	private ITareaRepository repository;

	@Autowired
	private OcupacionImplementacion ocupacionservice;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public TareaEntity save(TareaEntity entity) {
		Boolean esInsert = (entity.getId() == null || entity.getId() <= 0);	
		
		entity.setEstado("A");
		
		// Por defecto el nivel es 1, más adelante se actualiza al valor correcto
		entity.setNivel(1);
		
		// Se guarda la tarea
		if (!esInsert) {
			Optional<TareaEntity> entityActual = repository.findById(entity.getId());
			
			if (entityActual.isPresent()) {
				// De momento el nivel y la jerarquia no son modificables
				entity.setJerarquia(entityActual.get().getJerarquia());
				entity.setNivel(entityActual.get().getNivel());
			}
		}
		
		entity = repository.save(entity);
		
		if (esInsert) {
			Integer cantidadDigitosPorNivel = 10;
			
			// Se le asigna a la nueva tarea el valor de "jerarquia" correspondiente
			String jerarquia = String.format("%1$" + cantidadDigitosPorNivel + "s", entity.getId().toString()).replace(' ', '0');
			if (entity.getCodtareapadre() != null) {
				// Sí la tarea tiene un padre se ante pone la "jerarquia" del padre
				Optional<TareaEntity> tareaPadre = repository.findById(entity.getCodtareapadre());
				if (tareaPadre.isPresent()) {
					jerarquia = tareaPadre.get().getJerarquia() + jerarquia;
				}
			}

			entity.setJerarquia(jerarquia);
			
			// Se asigna el nivel correspondiente
			entity.setNivel(entity.getJerarquia().length() / cantidadDigitosPorNivel);
			
			repository.save(entity);
		}
		
		return entity;
	}
	
	@Override
	public Iterable<TareaEntity> saveAll(Iterable<TareaEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<TareaEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<TareaEntity> findAll() {
		return repository.findAll();
	}
	
	
	public Iterable<TareaEntity> findAllByCodproyecto(Integer codproyecto) {
		return repository.findAllByCodproyecto(codproyecto);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void deleteById(Integer id) {
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("SP_DF_TAREAELIMINAR")
				.registerStoredProcedureParameter(
				    1,
				    Integer.class,
				    ParameterMode.IN
				)
				.setParameter(1, id);
				 
		query.execute();
	}

	@Override
	public void delete(TareaEntity entity) {
		
	}

	@Override
	public void deleteAll(Iterable<TareaEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}

	/**
	 * Registra una nueva tarea en el devops como una ocupacion para registrar el tipo de registro es 1
	 * @param tarea
	 */
	public boolean agregarTareaDevops(TareaEntity tarea) throws IOException {
		 return ocupacionservice.RegistrarOcupacion(tarea, 1);
	}


}