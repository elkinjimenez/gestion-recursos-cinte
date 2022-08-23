package com.claro.gestionrecursosapi.reporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosapi.base.model.FiltroFechas;
import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.reporte.domain.ReporteProyectoTiempoService;
import com.claro.gestionrecursosapi.reporte.entity.ReporteProyectoTiempo;

@RestController
@RequestMapping("/api/v1/reporte/proyectotiempos")
public class ReporteProyectoTiempoController {

	@Autowired
	private ReporteProyectoTiempoService service;
	
	@PostMapping
	public ResponseEntity<RespuestaBase> findAll(@RequestBody FiltroFechas filtro) {
		try {
			Iterable<ReporteProyectoTiempo> resultado = service.findAll(filtro);
			RespuestaCustomizada<Iterable<ReporteProyectoTiempo>> respuesta = new RespuestaCustomizada<>();
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
