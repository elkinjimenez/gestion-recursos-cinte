package com.claro.gestionrecursosapi.tarea.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.tarea.domain.TareaVUService;
import com.claro.gestionrecursosapi.tarea.entity.TareaVU;

@RestController
@RequestMapping("/api/v1/tarea/vu")
public class TareaVUController {

	@Autowired
	private TareaVUService service;
	
	@GetMapping
	public ResponseEntity<RespuestaBase> findAll() {
		try {
			Iterable<TareaVU> resultado = service.findAll();
			RespuestaCustomizada<Iterable<TareaVU>> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(resultado);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RespuestaBase> findById(@PathVariable Integer id) {
		//try { 
			Optional<TareaVU> resultado = service.findById(id);
			RespuestaCustomizada<Optional<TareaVU>> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(resultado);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		//} catch (Exception e) {
		//	RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
		//	return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		//}
	}
	
	@GetMapping("/codproyecto")
	public ResponseEntity<RespuestaBase> findAllByCodproyecto(@RequestParam Integer v) {
		try {
			Iterable<TareaVU> resultado = service.findAllByCodproyecto(v);
			RespuestaCustomizada<Iterable<TareaVU>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(resultado);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}
	
}
