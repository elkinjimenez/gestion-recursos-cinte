package com.claro.gestionrecursosapi.hallazgo.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoPruebaVU;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoVU;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoVUPruebaRepository;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoVURepository;
import com.claro.gestionrecursosapi.hallazgo.domain.HallazgoService;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoEntity;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoProyectosEntity;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgosReportadosEntity;
import com.claro.gestionrecursosapi.hallazgo.repository.IHallazgoProyectoRepository;
import com.claro.gestionrecursosapi.hallazgo.repository.IHallazgoRepository;
import com.claro.gestionrecursosapi.hallazgo.repository.IHallazgosReportadosRepository;
import com.claro.gestionrecursosapi.perfil.domain.LineaProductoService;
import com.claro.gestionrecursosapi.perfil.entity.LineasproductoEntity;
import com.claro.gestionrecursosapi.reportegenerico.domain.ReporteGenericoService;
import com.claro.gestionrecursosapi.utils.ExceptionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hallazgo")
public class HallazgoController {

	//EmpleadoPrueba******
	@Autowired
	ReporteGenericoService servicePrueba;

	@Autowired
	private EmpleadoService serviceEmpleado;

	@Autowired
	IEmpleadoVUPruebaRepository empleadoVURepository;
	//-------------------------------------
	@Autowired
	IHallazgoRepository hallazgoRepository;

	@Autowired
	IHallazgoProyectoRepository hallazgoProyectoRepository;

	@Autowired
	IHallazgosReportadosRepository hallazgosReportadosRepository;

	@Autowired
	private HallazgoService hallazgoService;

	@Autowired
	private LineaProductoService serviceLineasProducto;

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaBase> buscarPorId(@PathVariable Integer id) {
		try {
			Optional<HallazgoEntity> hallazgoEntity = hallazgoRepository.findById(id);
			RespuestaCustomizada<HallazgoEntity> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(hallazgoEntity.get());
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<RespuestaBase> crearHallazgo(@RequestBody HallazgoEntity hallazgo) {
		try {
			hallazgo.setFechaHallazgo(new Date());
			HallazgoEntity hallazgoNuevo = hallazgoRepository.save(hallazgo);
			RespuestaCustomizada<HallazgoEntity> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Operación exitosa");
			respuesta.setData(hallazgoNuevo);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/proyectos")
	public ResponseEntity<RespuestaBase> buscarTodoProyectos() {
		try {
			//casteo
			List<HallazgoProyectosEntity> listasProyectos = (List<HallazgoProyectosEntity>) hallazgoProyectoRepository.findAll();
			RespuestaCustomizada<Iterable<HallazgoProyectosEntity>> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(hallazgoService.filterProjects(listasProyectos));
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionUtils.getStackTraceString(e));
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/lineasProducto")
	public ResponseEntity<RespuestaBase> buscarTodoLineasProd() {
		try {
			Iterable<LineasproductoEntity> listaPerfiles = serviceLineasProducto.findAll();
			RespuestaCustomizada<Iterable<LineasproductoEntity>> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaPerfiles);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/hallazgosReportados")
	public ResponseEntity<RespuestaBase> traerHallazgosReportados() {
		try {
			Iterable<HallazgosReportadosEntity> lista = hallazgosReportadosRepository.findAll();
			RespuestaCustomizada<Iterable<HallazgosReportadosEntity>> respuesta = new RespuestaCustomizada<>();

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

	@GetMapping("/empleadosPrueba")
	public ResponseEntity<RespuestaBase> traerEmpleadosVU() {

			Iterable<EmpleadoPruebaVU> lista = empleadoVURepository.findAll();
			RespuestaCustomizada<Iterable<EmpleadoPruebaVU>> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Operación exitosa");
			respuesta.setData(lista);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);

	}
}
