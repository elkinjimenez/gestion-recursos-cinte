package com.claro.gestionrecursosapi.proyecto.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.proyecto.entity.ProyectosolicitudempleadoEntity;
import com.claro.gestionrecursosapi.proyecto.repository.IProyectoSolicitudEmpleadoRepository;

@Service
@Transactional
public class ProyectoSolicitudEmpleadoService implements ICrudService<ProyectosolicitudempleadoEntity, Integer>{

	@Autowired
	private IProyectoSolicitudEmpleadoRepository repository;

	@Override
	public ProyectosolicitudempleadoEntity save(ProyectosolicitudempleadoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<ProyectosolicitudempleadoEntity> saveAll(Iterable<ProyectosolicitudempleadoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<ProyectosolicitudempleadoEntity> findById(Integer id) {
		return repository.findById(id);
	}
	
	public Iterable<ProyectosolicitudempleadoEntity> findByLineasProducto(Integer codLineaProducto){
		return repository.findByCodlineaproducto(codLineaProducto);		
	}
	
	public Iterable<ProyectosolicitudempleadoEntity> findByPerfil(Integer codPerfil){
		return repository.findByCodlineaproducto(codPerfil);		
	}
	
	public Iterable<ProyectosolicitudempleadoEntity> findByPerfilTipo(Integer codPerfilTipo){
		return repository.findByCodperfiltipo(codPerfilTipo);		
	}
	
	public Iterable<ProyectosolicitudempleadoEntity> findByPerfilNivel(Integer codPerfilNivel){
		return repository.findByCodlineaproducto(codPerfilNivel);		
	}
	
	public Iterable<ProyectosolicitudempleadoEntity> findByProyecto(Integer codProyecto){
		return repository.findByCodproyecto(codProyecto);		
	}
	
	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	@Override
	public Iterable<ProyectosolicitudempleadoEntity> findAll() {
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
	public void delete(ProyectosolicitudempleadoEntity entity) {
		repository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<ProyectosolicitudempleadoEntity> entities) {
		repository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
		
	}
		
}
