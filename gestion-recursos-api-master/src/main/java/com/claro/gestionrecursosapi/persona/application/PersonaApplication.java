package com.claro.gestionrecursosapi.persona.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.persona.domain.PersonaService;
import com.claro.gestionrecursosapi.persona.entity.PersonaEntity;
@Service
public class PersonaApplication {
 
	@Autowired
	private PersonaService personaService;    
	
	public PersonaEntity save(PersonaEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion {
		
		// Se crea la persona
		entity = personaService.save(entity);
		
		return entity;
	}
	
}
