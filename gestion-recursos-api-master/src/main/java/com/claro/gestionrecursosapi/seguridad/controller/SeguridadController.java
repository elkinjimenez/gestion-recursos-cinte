package com.claro.gestionrecursosapi.seguridad.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.seguridad.domain.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.claro.gestionrecursosapi.seguridad.application.SeguridadApplication;
import com.claro.gestionrecursosapi.seguridad.entity.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/seguridad")
public class SeguridadController {

	@Autowired
	EntityManager entityManager;

	@Autowired
	private SeguridadApplication service;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public Integer POST(@RequestBody UsuarioEntity entity) {
		setSessionDatesFormat();
		return service.iniciarSesion(entity);
	}   
	
	@GetMapping
	public UsuarioEntity GET(@RequestParam String usuario) {
		return service.findByUsuario(usuario);
	}

	@GetMapping("/usuario/{codPersona}")
	public ResponseEntity<RespuestaBase> findByCodPersona(@PathVariable Integer codPersona) {
		try {
			Optional<UsuarioEntity> usuario = Optional.of(usuarioService.findByCodPersona(codPersona));
			RespuestaCustomizada<Optional<UsuarioEntity>> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(usuario);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/actualizarClave/{usuarioClaro}/{clave}")
	public ResponseEntity<RespuestaBase> actualizarClave(@PathVariable String usuarioClaro, @PathVariable String clave){
		try {
			UsuarioEntity usuario = usuarioService.findByUsuario(usuarioClaro);
			usuario.setClave(clave);

			UsuarioEntity usuarioActualizado = usuarioService.save(usuario);
			RespuestaCustomizada<UsuarioEntity> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Usuario actualizado");
			respuesta.setData(usuarioActualizado);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		}catch (Exception e){
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Asigna el formato de fechas de trabajo, para consultar las vistas de DB
	 * solución a error ORA-01843: not a valid month, el formato de fechas usado por las vistas actualmente es DD/MM/YYYY
	 */
	@Scheduled(fixedRate = 180000)
	private void setSessionDatesFormat() {
		try {
			Query query = entityManager.createNativeQuery("ALTER SESSION SET nls_date_format = 'DD/MM/YYYY'");

			query.getResultList();
		} catch (Exception e) {
			//System.out.println("session alter: " + ExceptionUtils.getStackTraceString(e));
		}
	}
	
}
