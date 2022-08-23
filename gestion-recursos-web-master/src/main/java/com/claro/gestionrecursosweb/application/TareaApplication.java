package com.claro.gestionrecursosweb.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaVUDto;

@Service
public class TareaApplication {

	@Value("${claro.dominio.tarea.nombre}")
	private String dominio_tarea;
	
	@Autowired
	private ApiService<TareaDto, Integer> service;
	@Autowired
	private ApiService<TareaVUDto, Integer> serviceVU;
	@Autowired
	private EmpleadoApplication serviceEmpleado;
		
	private void configurarService( ) {
		service.setapiservicename(dominio_tarea);
	}
	
	public TareaDto insertar(TareaDto dto) {
		Authentication usuarioSesion = SecurityContextHolder.getContext().getAuthentication();
		Optional<EmpleadoDto> empleado = serviceEmpleado.findActivoByUsuario(usuarioSesion.getName());
		if (empleado.isPresent()) {
			dto.setCodempleadocreo(empleado.get().getId());
		}
		
		configurarService();
		dto = service.insert(dto, TareaDto.class);	
		
		return dto;
	}
	
	public TareaDto actualizar(TareaDto dto) {
		configurarService();
		TareaDto tareaActual = service.update(dto.getId(), dto, TareaDto.class);
		return tareaActual;
	}
	
	public Optional<TareaDto> findById(Integer id) {
		configurarService();
		return service.findById(id, TareaDto.class);
	}
	
	public Iterable<TareaVUDto> findAllByAttr(String attrnombre, String attrvalor) {
		serviceVU.setapiservicename(dominio_tarea);
		return serviceVU.findAllByAttr(attrnombre, attrvalor, TareaVUDto.class);
	}
	
	public void deleteById(Integer id) {
		configurarService();
		service.deleteById(id);
	}
	
}
