package com.claro.gestionrecursosapi.hallazgorespuesta.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.hallazgo.domain.HallazgoService;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgosReportadosEntity;
import com.claro.gestionrecursosapi.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hallazgovista")
public class HallazgoVistaController {
	
	@Autowired
	private HallazgoService hallazgoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<RespuestaBase> getHallazgoReportadoById(@PathVariable(name = "id") Integer id) {
		try {
			HallazgosReportadosEntity hallazgoReportados = hallazgoService.getHallazgosById(id);
			RespuestaCustomizada<HallazgosReportadosEntity> respuesta = new RespuestaCustomizada<>();

			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Operación exitosa");
			respuesta.setData(hallazgoReportados);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}
}
