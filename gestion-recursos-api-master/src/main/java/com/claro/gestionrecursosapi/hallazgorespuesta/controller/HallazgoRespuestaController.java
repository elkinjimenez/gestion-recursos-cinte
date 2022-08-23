package com.claro.gestionrecursosapi.hallazgorespuesta.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.hallazgo.domain.HallazgoService;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoEntity;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoProyectosEntity;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgosReportadosEntity;
import com.claro.gestionrecursosapi.hallazgo.repository.IHallazgoRepository;
import com.claro.gestionrecursosapi.hallazgo.repository.IHallazgosReportadosRepository;
import com.claro.gestionrecursosapi.hallazgorespuesta.domain.HallazgoRespuestaService;
import com.claro.gestionrecursosapi.novedad.domain.NovedadService;
import com.claro.gestionrecursosapi.novedad.entity.NovedadEntity;
import com.claro.gestionrecursosapi.perfil.domain.LineaProductoService;
import com.claro.gestionrecursosapi.perfil.entity.LineasproductoEntity;
import com.claro.gestionrecursosapi.proyecto.domain.ProyectoService;
import com.claro.gestionrecursosapi.proyecto.entity.ProyectoEntity;
import com.claro.gestionrecursosapi.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hallazgorespuesta")
public class HallazgoRespuestaController {
	
	@Autowired
	private HallazgoService hallazgoService;

	@Autowired
	private HallazgoRespuestaService hallazgoRespuestaService;

	@Autowired
	IHallazgoRepository hallazgoRepository;


	@GetMapping("/{id}")
	public ResponseEntity<RespuestaBase> getHallazgoId(@PathVariable(name = "id") Integer id) {
		try {
			HallazgoEntity hallazgo = hallazgoRespuestaService.getHallazgoById(id);
			RespuestaCustomizada<HallazgoEntity> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Operación exitosa");
			respuesta.setData(hallazgo);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/hallazgos")
	public ResponseEntity<RespuestaBase> getAllHallazgos() {
		try {
			Iterable<HallazgoEntity> lista = hallazgoRespuestaService.getAllHallazgos();
			RespuestaCustomizada<Iterable<HallazgoEntity>> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Operación exitosa");
			respuesta.setData(lista);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<RespuestaBase> modificarHallazgo(@PathVariable Integer id, @RequestBody HallazgoEntity hallazgo) {
		try {
			HallazgoEntity hallazgoModificado = hallazgoRespuestaService.modificarHallazgo(hallazgo);


			RespuestaCustomizada<HallazgoEntity> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Operación exitosa");
			respuesta.setData(hallazgoModificado);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<RespuestaBase> getHallazgoReportadoById(@PathVariable(name = "id") Integer id) {
//		try {
//			HallazgosReportadosEntity hallazgoReportados = hallazgoService.getHallazgosById(id);
//			RespuestaCustomizada<HallazgosReportadosEntity> respuesta = new RespuestaCustomizada<>();
//
//			respuesta.setCodigoEstatus(HttpStatus.OK.value());
//			respuesta.setMensaje("Operación exitosa");
//			respuesta.setData(hallazgoReportados);
//
//			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
//		} catch (Exception e) {
//			System.out.println("println: " + ExceptionUtils.getStackTraceString(e));
//
//			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
//			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
//		}
//	}
}
