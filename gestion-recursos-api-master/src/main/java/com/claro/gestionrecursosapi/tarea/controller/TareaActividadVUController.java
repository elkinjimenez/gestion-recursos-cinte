package com.claro.gestionrecursosapi.tarea.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.websocket.server.PathParam;

import javassist.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.tarea.domain.TareaActividadVUService;
import com.claro.gestionrecursosapi.tarea.entity.TareaActividadVU;

@RestController
@RequestMapping("/api/v1/tareaactividad/vu")
public class TareaActividadVUController {

    @Autowired
    private TareaActividadVUService service;

    @GetMapping
    public ResponseEntity<RespuestaBase> findAll(@RequestHeader("CL_USUARIO") String CLUSUARIO) {
        try {
            Iterable<TareaActividadVU> resultado = service.findAll();
            RespuestaCustomizada<Iterable<TareaActividadVU>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filtro")
    public ResponseEntity<RespuestaBase> findAllByFiltro(
            @RequestHeader("CL_USUARIO") String CLUSUARIO,
            @PathParam(value = "codproyecto") Integer codproyecto,
            @PathParam(value = "codproveedor") Integer codproveedor,
            @PathParam(value = "codpersona") Integer codpersona,
            @PathParam(value = "fechainicio") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechainicio,
            @PathParam(value = "fechafin") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechafin) {
        try {
            Calendar fechaactual = Calendar.getInstance();
            if (codproyecto != null && codproyecto == 0) {
                codproyecto = null;
            }
            if (codproveedor != null && codproveedor == 0) {
                codproveedor = null;
            }
            if (codpersona != null && codpersona == 0) {
                codpersona = null;
            }

            Iterable<TareaActividadVU> resultado = service.findAllByFiltro(CLUSUARIO, codproyecto, codproveedor, codpersona, fechainicio, fechafin);
            RespuestaCustomizada<Iterable<TareaActividadVU>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            //System.out.println("********* " + e.getMessage());
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBase> findById(@PathVariable Integer id) {
        //try { 
        Optional<TareaActividadVU> resultado = service.findById(id);
        RespuestaCustomizada<Optional<TareaActividadVU>> respuesta = new RespuestaCustomizada<>();

        respuesta.setCodigoEstatus(HttpStatus.OK.value());
        respuesta.setMensaje("Consulta exitosa");
        respuesta.setData(resultado);
        return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        //} catch (Exception e) {
        //	RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
        //	return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        //}
    }

}
