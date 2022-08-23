package com.claro.gestionrecursosapi.empleado.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoForm;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoFormJefes;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadosFormularios;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadosFormulariosJefes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadocontrolEntity;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoControlRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoControlService implements ICrudService<EmpleadocontrolEntity, Integer> {

	@Autowired
	private IEmpleadoControlRepository repository;

	@Autowired
	IEmpleadosFormularios serviceform;
	
	@Autowired
	IEmpleadosFormulariosJefes serviceformjefe;

	@Override
	@Transactional
	public EmpleadocontrolEntity save(EmpleadocontrolEntity entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public Iterable<EmpleadocontrolEntity> saveAll(Iterable<EmpleadocontrolEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<EmpleadocontrolEntity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<EmpleadocontrolEntity> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public long count() {
		return repository.count();
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(EmpleadocontrolEntity entity) {
		repository.delete(entity);
	}

	@Override
	@Transactional
	public void deleteAll(Iterable<EmpleadocontrolEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
	}

	@Transactional(readOnly = true)
	public Iterable<EmpleadocontrolEntity> findAllByCodTarea(Integer codtarea) {
		return repository.findAllByCodTarea(codtarea);
	}

	@Transactional(readOnly = true)
	public BigDecimal sumHorasByCodtarea(Integer codtarea) {
		return repository.sumHorasByCodtarea(codtarea);
	}
	
	public BigDecimal sumHorasByFecha(Integer codEmpleado, Date fechaIni, Date fechaFin) {
		return repository.sumHorasByFecha(codEmpleado, fechaIni, fechaFin);
	}
	
	public Date minFechaIniByCodtarea(Integer codtarea) {
		return repository.minFechaIniByCodtarea(codtarea);
	}
	
	public Date maxFechaFinByCodtarea(Integer codtarea) {
		return repository.maxFechaFinByCodtarea(codtarea);
	}
	
	public Integer horasTareaFechaPorRecurso(Integer codEmpleado,Integer codTarea, Date fechaHoraInicio) {
		Integer horas=repository.horasTareaFechaPorRecurso(codEmpleado, codTarea, fechaHoraInicio);
		return horas; 
	}
	
	public Integer horasFechaPorRecurso(Integer codEmpleado, Date fechaHoraInicio) {
		Integer horas=repository.horasFechaPorRecurso(codEmpleado,fechaHoraInicio);
		return horas; 
	}

	public List<EmpleadoFormJefes> getEmpleadoByEstructuraTipo(int cod){
		return serviceformjefe.getEmpleadoByEstructuraTipo(cod);
	}

	public List<EmpleadoForm> getEmpleadoFormById(int Id){
		return serviceform.getEmpleadoFormById(Id);
	}

}
