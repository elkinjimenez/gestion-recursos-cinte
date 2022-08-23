package com.claro.gestionrecursosapi.costoempleado.controller;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.costoempleado.entity.CostoEmpleadoEntity;
import com.claro.gestionrecursosapi.costoempleado.entity.CostoEmpleadoVU;
import com.claro.gestionrecursosapi.costoempleado.repository.ICostoEmpleadoRepository;
import com.claro.gestionrecursosapi.costoempleado.repository.ICostoEmpleadoVURepository;
import com.claro.gestionrecursosapi.hallazgo.domain.HallazgoService;
import com.claro.gestionrecursosapi.hallazgo.repository.IHallazgoRepository;
import com.claro.gestionrecursosapi.utils.ExceptionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/costoempleado")
public class CostoEmpleadoController {
	@Autowired
	EntityManager entityManager;
	@Autowired
	ICostoEmpleadoVURepository costoEmpleadoVURepository;

	@Autowired
	ICostoEmpleadoRepository costoEmpleadoRepository;

	@Autowired
	IHallazgoRepository hallazgoRepository;
	@Autowired
	private HallazgoService hallazgoService;

	/*
	 * @GetMapping("/{id}")
	 * public ResponseEntity<RespuestaBase> buscarPorId(@PathVariable Integer id) {
	 * try {
	 * Optional<HallazgoEntity> hallazgoEntity = hallazgoRepository.findById(id);
	 * RespuestaCustomizada<HallazgoEntity> respuesta = new
	 * RespuestaCustomizada<>();
	 * respuesta.setCodigoEstatus(HttpStatus.OK.value());
	 * respuesta.setMensaje("Consulta exitosa");
	 * respuesta.setData(hallazgoEntity.get());
	 * return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
	 * } catch (Exception e) {
	 * RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(),
	 * e.getMessage());
	 * return new ResponseEntity<RespuestaBase>(respuestaBase,
	 * HttpStatus.NOT_FOUND);
	 * }
	 * }
	 * 
	 * @PostMapping
	 * public ResponseEntity<RespuestaBase> crearHallazgo(@RequestBody
	 * HallazgoEntity hallazgo) {
	 * try {
	 * hallazgo.setFechaHallazgo(new Date());
	 * HallazgoEntity hallazgoNuevo = hallazgoRepository.save(hallazgo);
	 * RespuestaCustomizada<HallazgoEntity> respuesta = new
	 * RespuestaCustomizada<>();
	 * 
	 * respuesta.setCodigoEstatus(HttpStatus.OK.value());
	 * respuesta.setMensaje("Operación exitosa");
	 * respuesta.setData(hallazgoNuevo);
	 * 
	 * return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
	 * } catch (Exception e) {
	 * System.out.println("println: " + ExceptionUtils.getStackTraceString(e));
	 * 
	 * RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(),
	 * e.getMessage());
	 * return new ResponseEntity<RespuestaBase>(respuestaBase,
	 * HttpStatus.NOT_FOUND);
	 * }
	 * }
	 */

	@PostMapping
	public ResponseEntity<RespuestaBase> crear(@RequestBody CostoEmpleadoEntity costoEmpleadoData) {
		try {
			CostoEmpleadoEntity costoEmpleado = costoEmpleadoRepository.save(costoEmpleadoData);
			RespuestaCustomizada<CostoEmpleadoEntity> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
			respuesta.setMensaje("Empleado creado");
			respuesta.setData(costoEmpleado);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/codEmpleado/{id}")
	public ResponseEntity<RespuestaBase> buscarPorCodEmpleado(@PathVariable Integer id) {
		try {
			List<CostoEmpleadoEntity> listCostoEmpleado = costoEmpleadoRepository.findByCodEmpleado(id);
			RespuestaCustomizada<List<CostoEmpleadoEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listCostoEmpleado);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<RespuestaBase> actualizar(@PathVariable Integer id,
			@RequestBody CostoEmpleadoEntity costoEmpleadoForm) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(costoEmpleadoForm.getDesde());
			calendar.add(calendar.DAY_OF_YEAR, -1);
			CostoEmpleadoEntity costoEmpleado = costoEmpleadoRepository.findById(id).get();
			costoEmpleado.setHasta(calendar.getTime());
			costoEmpleado = costoEmpleadoRepository.save(costoEmpleado);
			RespuestaCustomizada<CostoEmpleadoEntity> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Costo empleado actualizado");
			respuesta.setData(costoEmpleado);
			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/costosCreados")
	public ResponseEntity<RespuestaBase> traerCostosCreados() {
		try {
			setSessionDatesFormat();

			Iterable<CostoEmpleadoVU> lista = costoEmpleadoVURepository.findAll();
			RespuestaCustomizada<Iterable<CostoEmpleadoVU>> respuesta = new RespuestaCustomizada<>();

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

	/**
	 * Asigna el formato de fechas de trabajo, para consultar las vistas de DB
	 * solución a error ORA-01843: not a valid month, el formato de fechas usado por
	 * las vistas actualmente es DD/MM/YYYY
	 */
	@Scheduled(fixedRate = 180000)
	private void setSessionDatesFormat() {
		try {
			Query query = entityManager.createNativeQuery("ALTER SESSION SET nls_date_format = 'DD/MM/YYYY'");

			query.getResultList();
		} catch (Exception e) {
			// System.out.println("session alter: " +
			// ExceptionUtils.getStackTraceString(e));
		}
	}

}
