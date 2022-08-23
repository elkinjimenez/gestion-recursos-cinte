package com.claro.gestionrecursosapi.proyecto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.proyecto.domain.ProyectoSolicitudEmpleadoService;
import com.claro.gestionrecursosapi.proyecto.entity.ProyectosolicitudempleadoEntity;

@RestController
@RequestMapping("/api/v1/proyectosolicitudempleado")
public class ProyectoSolicitudEmpleadoController {

	@Autowired
	private ProyectoSolicitudEmpleadoService service;

	@GetMapping
	public ResponseEntity<RespuestaBase> buscarTodo() {
		try {
			Iterable<ProyectosolicitudempleadoEntity> listaProyectoSolicitud = service.findAll();
			RespuestaCustomizada<Iterable<ProyectosolicitudempleadoEntity>> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaProyectoSolicitud);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaBase> buscarPorId(@PathVariable Integer id) {
		try {
			Optional<ProyectosolicitudempleadoEntity> proyectoSolicitud = service.findById(id);
			RespuestaCustomizada<ProyectosolicitudempleadoEntity> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(proyectoSolicitud.get());
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/perfil/{perfil}")
	public ResponseEntity<RespuestaBase> buscarPorPefil(@PathVariable int perfil) {
		try {
			Iterable<ProyectosolicitudempleadoEntity> listaProyectoSolicitud = service.findByPerfil(perfil);
			RespuestaCustomizada<Iterable<ProyectosolicitudempleadoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaProyectoSolicitud);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/perfilnivel/{perfilnivel}")
	public ResponseEntity<RespuestaBase> buscarPorPefilNivel(@PathVariable int perfilnivel) {
		try {
			Iterable<ProyectosolicitudempleadoEntity> listaProyectoSolicitud = service.findByPerfilNivel(perfilnivel);
			RespuestaCustomizada<Iterable<ProyectosolicitudempleadoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaProyectoSolicitud);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/lineasproducto/{lineasproducto}")
	public ResponseEntity<RespuestaBase> buscarPorLineasProducto(@PathVariable int lineasproducto) {
		try {
			Iterable<ProyectosolicitudempleadoEntity> listaProyectoSolicitud = service
					.findByLineasProducto(lineasproducto);
			RespuestaCustomizada<Iterable<ProyectosolicitudempleadoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaProyectoSolicitud);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/perfiltipo/{perfiltipo}")
	public ResponseEntity<RespuestaBase> buscarPorPerfilTipo(@PathVariable int perfiltipo) {
		try {
			Iterable<ProyectosolicitudempleadoEntity> listaProyectoSolicitud = service.findByPerfilTipo(perfiltipo);
			RespuestaCustomizada<Iterable<ProyectosolicitudempleadoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaProyectoSolicitud);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/proyecto/{proyecto}")
	public ResponseEntity<RespuestaBase> buscarPorProyecto(@PathVariable int proyecto) {
		try {
			Iterable<ProyectosolicitudempleadoEntity> listaProyectoSolicitud = service.findByProyecto(proyecto);
			RespuestaCustomizada<Iterable<ProyectosolicitudempleadoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaProyectoSolicitud);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<RespuestaBase> crear(@RequestBody ProyectosolicitudempleadoEntity entity) {
		try {
			ProyectosolicitudempleadoEntity proyectoSolicitud = service.save(entity);
			RespuestaCustomizada<ProyectosolicitudempleadoEntity> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
			respuesta.setMensaje("Solicitud creada");
			respuesta.setData(proyectoSolicitud);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
		}
	}

	@PutMapping
	public ResponseEntity<RespuestaBase> actualizar(@RequestBody ProyectosolicitudempleadoEntity entity) {
		try {
			ProyectosolicitudempleadoEntity proyectoSolicitud = service.save(entity);
			RespuestaCustomizada<ProyectosolicitudempleadoEntity> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Soliciutd actualizada");
			respuesta.setData(proyectoSolicitud);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RespuestaBase> eliminar(@PathVariable Integer id) {
		try {
			service.delete(service.findById(id).get());
			RespuestaBase respuesta = new RespuestaBase();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Se elimino registro");
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}
}
