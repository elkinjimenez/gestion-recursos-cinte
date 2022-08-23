package com.claro.gestionrecursosapi.reportegenerico.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.claro.gestionrecursosapi.reportegenerico.entity.RangoEntity;
import com.claro.gestionrecursosapi.reportegenerico.entity.VistaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.reportegenerico.domain.ReporteGenericoService;
import com.claro.gestionrecursosapi.reportegenerico.dto.ReporteFiltroDto;
import com.claro.gestionrecursosapi.reportegenerico.dto.ViewInformationDto;
import com.claro.gestionrecursosapi.utils.ExceptionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/reportegenerico")
public class ReporteGenericoController {
	@Autowired
	ReporteGenericoService reporteGenericoService;

	@GetMapping("/vistas")
	public ResponseEntity<RespuestaBase> getVistasUsuarioDb() {
		try {
			Iterable<VistaEntity> listaVistas = reporteGenericoService.getVistasUsuarioDb();
			RespuestaCustomizada<Iterable<VistaEntity>> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(listaVistas);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());

			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/rango")
	public ResponseEntity<RespuestaBase> getRango() {
		try {
			RangoEntity rango = reporteGenericoService.getRangoRegistro();
			RespuestaCustomizada<RangoEntity> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(rango);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/informacion-vista")
	public ResponseEntity<RespuestaBase> getDatosVistaDb(@RequestBody ReporteFiltroDto filtros) {
		try {
			ViewInformationDto viewInformationDto = reporteGenericoService.getInformacionVista(filtros);
			RespuestaCustomizada<ViewInformationDto> respuesta = new RespuestaCustomizada<>();
			respuesta.setCodigoEstatus(HttpStatus.OK.value());
			respuesta.setMensaje("Consulta exitosa");
			respuesta.setData(viewInformationDto);

			return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

			RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());

			return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/descargar")
    public HttpEntity<ByteArrayResource> download(String CLUSUARIO, String q) {
		try {
			q = URLDecoder.decode(q, StandardCharsets.UTF_8.toString());
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
			ReporteFiltroDto filtros = mapper.readValue(q, ReporteFiltroDto.class);

			byte[] resultado = reporteGenericoService.download(filtros);

	        HttpHeaders header = new HttpHeaders();
	        header.setContentType(new MediaType("application", "force-download"));

	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date fecha = Calendar.getInstance().getTime();
	        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ filtros.getViewName() +"_" + dateFormat.format(fecha) + ".xlsx");

	        return new HttpEntity<>(new ByteArrayResource(resultado), header);
		} catch (Exception e) {
			System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

			return null;
		}
    }
}
