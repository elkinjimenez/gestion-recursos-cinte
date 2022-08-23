package com.claro.gestionrecursosapi.persona.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.persona.domain.HabilidadService;
import com.claro.gestionrecursosapi.persona.entity.HabilidadEntity;
import com.claro.gestionrecursosapi.persona.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habilidades")
public class HabilidadController {

    @Autowired
    private HabilidadService habilidadService;

    @GetMapping
    public ResponseEntity buscarTodo() {
        try{
            Iterable<HabilidadEntity> listaHabilidades = habilidadService.buscarTodo();
            RespuestaCustomizada<Iterable<HabilidadEntity>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listaHabilidades);

            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<RespuestaBase> buscarPorIdPersona(@PathVariable Integer id) {
        try {
            Iterable<HabilidadEntity> listHabPorPersona = habilidadService.buscarPorCodPersona(id);
            RespuestaCustomizada<Iterable<HabilidadEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listHabPorPersona);

            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<RespuestaBase> crearHabilidad(@RequestBody HabilidadEntity habilidadEntity) {
       try{
           HabilidadEntity habilidad = habilidadService.save(habilidadEntity);

           RespuestaCustomizada<HabilidadEntity> respuesta = new RespuestaCustomizada<>();
           respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
           respuesta.setMensaje("Habilidad creada");
           respuesta.setData(habilidad);
           return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
       }catch (YaExisteExcepcion | DataIncorrectaExcepcion e) {
           RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
           return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
       }
    }
}
