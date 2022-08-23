package com.claro.gestionrecursosweb.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoDto;
import com.claro.gestionrecursosweb.persona.dto.PersonaDto;
import com.claro.gestionrecursosweb.seguridad.domain.SeguridadService;
import com.claro.gestionrecursosweb.seguridad.dto.UsuarioDto;

@Service
public class EmpleadoApplication {
	
	@Value("${claro.dominio.tarea.nombre}")
	private String dominio_tarea;
	@Value("${claro.dominio.seguridad.nombre}")
	private String dominio_seguridad;
	@Value("${claro.dominio.persona.nombre}")
	private String dominio_persona;
	@Value("${claro.dominio.empleado.nombre}")
	private String dominio_empleado;
	
	
	@Autowired
	private SeguridadService serviceUsuario;
	@Autowired
	private ApiService<PersonaDto, Integer> servicePersona;
	@Autowired
	private EmpleadoService serviceEmpleado;
	
	public Optional<EmpleadoDto> findActivoByUsuario(String nombreusuario) {
		serviceUsuario.setapiservicename(dominio_seguridad);
		UsuarioDto usuario = serviceUsuario.findByUsuario(nombreusuario);
		
		servicePersona.setapiservicename(dominio_persona);
		Optional<PersonaDto> persona = servicePersona.findById(usuario.getCodpersona(), PersonaDto.class);
		
		serviceEmpleado.setapiservicename(dominio_empleado);
		Optional<EmpleadoDto> empleado = serviceEmpleado.findActivoByCodPersona(persona.get().getId());
		return empleado;
	}
	
}
