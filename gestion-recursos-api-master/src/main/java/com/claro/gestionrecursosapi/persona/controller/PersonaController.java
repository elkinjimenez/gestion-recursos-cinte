package com.claro.gestionrecursosapi.persona.controller;

import com.claro.gestionrecursosapi.persona.application.PersonaApplication;
import com.claro.gestionrecursosapi.persona.domain.PersonaService;

import java.util.List;
import java.util.Optional;

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

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.persona.entity.PersonaEntity;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    @Autowired
    private PersonaApplication serviceApplication;

    @Autowired
    private PersonaService service;

    @GetMapping
    public ResponseEntity<RespuestaBase> buscarTodo() {
        try {
            Iterable<PersonaEntity> listasPersonas = service.findAll();
            RespuestaCustomizada<Iterable<PersonaEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listasPersonas);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBase> buscarPorId(@PathVariable Integer id) {
        try {
            Optional<PersonaEntity> personaEntity = service.findById(id);
            RespuestaCustomizada<PersonaEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(personaEntity.get());
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RespuestaBase> crear(@RequestBody PersonaEntity entity) {
        try {
            PersonaEntity personaEntity = serviceApplication.save(entity);

            RespuestaCustomizada<PersonaEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
            respuesta.setMensaje("Proyecto creado");
            respuesta.setData(personaEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaBase> actualizar(@PathVariable Integer id, @RequestBody PersonaEntity entity) {
        try {
            PersonaEntity personaEntity = service.save(entity);
            RespuestaCustomizada<PersonaEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Proyecto actualizado");
            respuesta.setData(personaEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaBase> eliminar(@PathVariable Integer id) {

        try {
            service.delete(service.findById(id).get());
            RespuestaBase respuesta = new RespuestaBase();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Se elimino registro");
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{tipoDocumento}/{documento}")
    public ResponseEntity<RespuestaBase> buscarPorTipoDocYDocumento(@PathVariable Integer tipoDocumento, @PathVariable Long documento) {
        try {
            PersonaEntity personaEntity = service.findByCodtipodocumentoAndNumerodocumento(tipoDocumento, documento);
            RespuestaCustomizada<PersonaEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            if (personaEntity == null) {
                respuesta.setMensaje("La persona no existe");
            }
            respuesta.setData(personaEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getPersonaXCodEmpleado/{codEmpleado}")
    public ResponseEntity<RespuestaBase> getPersonaXCodEmpleado(@PathVariable Integer codEmpleado) {
        try {
            PersonaEntity personaEntity = service.getPersonaXCodEmpleado(codEmpleado);
            RespuestaCustomizada<PersonaEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            if (personaEntity == null) {
                respuesta.setMensaje("La persona no existe");
            }
            respuesta.setData(personaEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/getPersonasSinVinculacion")
    public ResponseEntity<RespuestaBase> getPersonasSinVinculacion() {
        try {
            List<PersonaEntity> personassinVinculacion = service.getPersonasSinVinculacion();
            RespuestaCustomizada<List<PersonaEntity>>  respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(personassinVinculacion);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

}
