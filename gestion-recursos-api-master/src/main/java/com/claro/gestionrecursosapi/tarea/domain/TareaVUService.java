package com.claro.gestionrecursosapi.tarea.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.tarea.entity.TareaVU;
import com.claro.gestionrecursosapi.tarea.repository.ITareaVURepository;

@Service
public class TareaVUService {

	@Autowired
	private ITareaVURepository repository;
	
	public Iterable<TareaVU> findAll() {
		return repository.findAll();
	}
	
	public Optional<TareaVU> findById(Integer id) {
		return repository.findById(id);
	}
	
	public Iterable<TareaVU> findAllByCodproyecto(Integer codproyecto) {
		return repository.findAllByCodproyecto(codproyecto);
	}
	
}
