package com.claro.gestionrecursosapi.opcionfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.opcionfront.domain.OpcionFrontService;
import com.claro.gestionrecursosapi.opcionfront.entity.OpcionFrontEntity;

@RestController
@RequestMapping("/api/v1/opciones-front")
public class OpcionFrontController {
	@Autowired
	private OpcionFrontService opcionFrontService;

	@GetMapping("/consultar-grupo/{grupo}")
	public ResponseEntity<RespuestaBase> findAllByGrupo(@PathVariable String grupo) {
		try {
			Iterable<OpcionFrontEntity> listaOpciones = opcionFrontService.findAllByGrupo(grupo);
			RespuestaCustomizada<Iterable<OpcionFrontEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaOpciones);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/consultar-by-username/{username}")
	public ResponseEntity<RespuestaBase> findGrupoByUsuario(@PathVariable String username) {
		try {
			Iterable<OpcionFrontEntity> listaOpciones = opcionFrontService.findGrupoByUsuario(username);
			RespuestaCustomizada<Iterable<OpcionFrontEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaOpciones);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

}
