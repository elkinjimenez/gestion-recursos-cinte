package com.claro.gestionrecursosapi.compromisosfabrica.controller;

import com.claro.gestionrecursosapi.utils.Filtros;
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

import java.util.List;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.compromisosfabrica.domain.CompromisosFabricaService;
import com.claro.gestionrecursosapi.compromisosfabrica.dto.CompromisosFabricaDto;
import com.claro.gestionrecursosapi.compromisosfabrica.dto.FiltrosDto;
import com.claro.gestionrecursosapi.compromisosfabrica.entity.CompromisosFabricaEntity;
import com.claro.gestionrecursosapi.reportegenerico.entity.ColumnInformationDb;
import com.claro.gestionrecursosapi.utils.ExceptionUtils;


@RestController
@RequestMapping("/api/v1/fabrica")
public class CompromisosFabricaController {

	@Autowired
	private CompromisosFabricaService service;

	@Autowired
	private Filtros filtro;

	@GetMapping("/columnas-filtros")
	public ResponseEntity<RespuestaBase> getColumnasFiltros() {
		try {
			Iterable<ColumnInformationDb> results = service.getColumnasFiltro();
			RespuestaCustomizada<Iterable<ColumnInformationDb>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(results);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/filtrar")
	public ResponseEntity<RespuestaBase> getFiltrar(@RequestBody FiltrosDto filtros) {
		try {
			Iterable<CompromisosFabricaDto> listaCompromisosFabrica = service.findAllByFiltro(filtros);

			if (filtros.getDiasFiltro()!=null && filtros.getDiasFiltro() >= 1){
				listaCompromisosFabrica = filtro.filterProjects(listaCompromisosFabrica , filtros.getDiasFiltro());
			}

			RespuestaCustomizada<Iterable<CompromisosFabricaDto>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaCompromisosFabrica);
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			System.out.println("println: " + ExceptionUtils.getStackTraceString(e));
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<?> buscarTodo() {
		try {
			Iterable<CompromisosFabricaDto> listaCompromisosFabrica = service.findAllCompromisosFabrica();

			RespuestaCustomizada<Iterable<CompromisosFabricaDto>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaCompromisosFabrica);

			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());

			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaBase> buscarPorId(@PathVariable Integer id) {
		try {
			CompromisosFabricaDto compromisosFabricaEntity = service.findByIdCompromisosFabrica(id);

			RespuestaCustomizada<CompromisosFabricaDto> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(compromisosFabricaEntity);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());

			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<RespuestaBase> crear(@RequestBody CompromisosFabricaDto compromisosFabricaDto) {
		try {
			CompromisosFabricaDto CompromisosFabricaEntity = service.saveCompromisosFabrica(compromisosFabricaDto);

			RespuestaCustomizada<CompromisosFabricaDto> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
			respuesta.setMensaje("Se creo correctamente");
			respuesta.setData(CompromisosFabricaEntity);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());

			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<RespuestaBase> actualizar(@RequestBody CompromisosFabricaDto entity, @PathVariable Integer id) {
		try {
			CompromisosFabricaDto CompromisosFabricaEntity = service.updateCompromisosFabrica(entity, id);

			RespuestaCustomizada<CompromisosFabricaDto> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Se actualizo correctamente");
			respuesta.setData(CompromisosFabricaEntity);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());

			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RespuestaBase> eliminar(@PathVariable Integer id) {
		try {
			service.delete(id);

			RespuestaBase respuesta = new RespuestaBase();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Se elimino registro");

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());

			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/gerenteProyecto/{id}")
	public ResponseEntity<RespuestaBase> getGerenteProyecto(@PathVariable Integer id) {
		try {
			String results = service.gerenteProyecto(id);

			RespuestaCustomizada<String> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(results);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/proyecto/{idProyecto}")
	public ResponseEntity<RespuestaBase> findCompromisosByProyecto(@PathVariable Integer idProyecto) {
		try {
			List<CompromisosFabricaEntity> compromisos =  service.findCompromisosByProyecto(idProyecto);
			RespuestaCustomizada<List<CompromisosFabricaEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(compromisos);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}
}
