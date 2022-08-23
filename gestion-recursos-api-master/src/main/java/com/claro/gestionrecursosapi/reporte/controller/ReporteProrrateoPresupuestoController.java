package com.claro.gestionrecursosapi.reporte.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.reporte.domain.ReporteProrrateoPresupuestoService;
import com.claro.gestionrecursosapi.reporte.dto.ReporteFiltroDto;
import com.claro.gestionrecursosapi.reporte.entity.ReporteProrrateoPresupuesto;
import com.claro.gestionrecursosapi.reporte.entity.ReporteRecursoConProveedor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/reporte/prorrateovspresupuesto")
public class ReporteProrrateoPresupuestoController {

    @Autowired
    private ReporteProrrateoPresupuestoService service;

    @GetMapping
    public ResponseEntity<?> informe(@RequestHeader("CL_USUARIO") String CLUSUARIO, String q) {
        try {
            q = URLDecoder.decode(q, StandardCharsets.UTF_8.toString());
            ObjectMapper mapper = new ObjectMapper();
            ReporteFiltroDto modelo = mapper.readValue(q, ReporteFiltroDto.class);

            Iterable<ReporteProrrateoPresupuesto> resultado = service.informe(CLUSUARIO, modelo);

            RespuestaCustomizada<Iterable<ReporteProrrateoPresupuesto>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            StringWriter outError = new StringWriter();
            e.printStackTrace(new PrintWriter(outError));
            String errorString = outError.toString();

            System.out.println("************************ error: " + errorString);
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
            ReporteFiltroDto modelo = mapper.readValue(q, ReporteFiltroDto.class);

            byte[] resultado = service.download(CLUSUARIO, modelo);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = Calendar.getInstance().getTime();
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prorrateo_vs_presupuesto_" + dateFormat.format(fecha) + ".xlsx");

            return new HttpEntity<>(new ByteArrayResource(resultado), header);

        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (JsonMappingException e) {
            return null;
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
