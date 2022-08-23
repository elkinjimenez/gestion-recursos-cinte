package com.claro.gestionrecursosapi.perfil.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.claro.gestionrecursosapi.perfil.dto.PerfilCostoDto;
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
import com.claro.gestionrecursosapi.perfil.domain.PerfilCostoService;
import com.claro.gestionrecursosapi.perfil.entity.PerfilcostoEntity;

@RestController
@RequestMapping("/api/v1/perfilcosto")
public class PerfilCostoController {

	@Autowired
	private PerfilCostoService service;

	@GetMapping
	public ResponseEntity<?> buscarTodo() {
		try {
			Iterable<PerfilCostoDto> listaPerfilesCosto = service.findAllPerfilCosto();
			Map<String, Object> respuesta = new HashMap<>();

			respuesta.put("codigoEstadus", HttpStatus.OK.value());
			respuesta.put("mensaje", "Consulta exitosa");
			respuesta.put("data", listaPerfilesCosto);
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaBase> buscarPorId(@PathVariable Integer id) {
		try {
			PerfilCostoDto perfilCostoEntity = service.findByIdPerfilCosto(id);
			RespuestaCustomizada<PerfilCostoDto> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(perfilCostoEntity);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/estado/{estado}")
	public ResponseEntity<RespuestaBase> buscarContengaEstado(@PathVariable String estado) {
		try {
			Iterable<PerfilcostoEntity> listadoPefilCosto = service.findByEstadoContains(estado);
			RespuestaCustomizada<Iterable<PerfilcostoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listadoPefilCosto);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/perfil/{perfil}")
	public ResponseEntity<RespuestaBase> buscarPorPefil(@PathVariable int perfil) {
		try {
			Iterable<PerfilcostoEntity> listadoPefilCosto = service.findByPerfil(perfil);
			RespuestaCustomizada<Iterable<PerfilcostoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listadoPefilCosto);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/perfilnivel/{perfilnivel}")
	public ResponseEntity<RespuestaBase> buscarPorPefilNivel(@PathVariable int perfilnivel) {
		try {
			Iterable<PerfilcostoEntity> listadoPefilCosto = service.findByPerfilNivel(perfilnivel);
			RespuestaCustomizada<Iterable<PerfilcostoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listadoPefilCosto);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/proveedor/{codProveedor}")
	public ResponseEntity<RespuestaBase> buscarPorLineasProducto(@PathVariable Integer codProveedor) {
		try {
			Iterable<PerfilcostoEntity> listadoPefilCosto = service.findByCodProveedor(codProveedor);
			RespuestaCustomizada<Iterable<PerfilcostoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listadoPefilCosto);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/perfiltipo/{perfiltipo}")
	public ResponseEntity<RespuestaBase> buscarPorPerfilTipo(@PathVariable int perfiltipo) {
		try {
			Iterable<PerfilcostoEntity> listadoPefilCosto = service.findByPerfilTipo(perfiltipo);
			RespuestaCustomizada<Iterable<PerfilcostoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listadoPefilCosto);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<RespuestaBase> crear(@RequestBody PerfilCostoDto perfilCostoDto) {
		try {
			PerfilCostoDto perfilCostoEntity = service.savePerfilCosto(perfilCostoDto);
			RespuestaCustomizada<PerfilCostoDto> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
			respuesta.setMensaje("Se creo correctamente");
			respuesta.setData(perfilCostoEntity);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<RespuestaBase> actualizar(@RequestBody PerfilCostoDto entity, @PathVariable Integer id) {
		try {
			PerfilCostoDto perfilCostoEntity = service.updatePerfilCosto(entity, id);
			RespuestaCustomizada<PerfilCostoDto> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Se actualizo correctamente");
			respuesta.setData(perfilCostoEntity);
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
