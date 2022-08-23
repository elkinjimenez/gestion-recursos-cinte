package com.claro.gestionrecursosapi.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.claro.gestionrecursosapi.ui.repository.VistaUsuarioRepository;

@Service
public class VistaUsuarioService<T> {
	
	@Autowired
	private VistaUsuarioRepository repository;
	
	public Iterable<T> findAll(String nombreVista) {
		return repository.findAll(nombreVista);
		
	}
	
	
	
}
