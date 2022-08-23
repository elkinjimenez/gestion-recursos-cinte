package com.claro.gestionrecursosapi.empleado.controller;

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
import com.claro.gestionrecursosapi.empleado.application.EmpleadoApplication;
import com.claro.gestionrecursosapi.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEntity;

@RestController
@RequestMapping("/api/v1/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService service;
    @Autowired
    private EmpleadoApplication serviceApplication;
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<RespuestaBase> buscarTodo() {
        try {
            Iterable<EmpleadoEntity> listasPersonas = service.findAll();
            RespuestaCustomizada<Iterable<EmpleadoEntity>> respuesta = new RespuestaCustomizada<>();
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
            Optional<EmpleadoEntity> personaEntity = service.findById(id);
            RespuestaCustomizada<EmpleadoEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(personaEntity.get());
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/codpersona/{codpersona}")
    public ResponseEntity<RespuestaBase> buscarPorCodPersona(@PathVariable Integer codpersona) {
        try {
            Optional<EmpleadoEntity> empleado = Optional.of(service.findByCodPersona(codpersona));
            RespuestaCustomizada<EmpleadoEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(empleado.get());
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuarioClaro/{usuarioClaro}")
    public ResponseEntity<RespuestaBase> findByUsuarioclaro(@PathVariable String usuarioClaro) {
        try {
            Optional<EmpleadoEntity> empleado = Optional.of(service.findByUsuarioclaro(usuarioClaro));
            RespuestaCustomizada<EmpleadoEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(empleado.get());
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/activo/codpersona/{codpersona}")
    public ResponseEntity<RespuestaBase> findActivoByCodPersona(@PathVariable Integer codpersona) {
        try {
            Optional<EmpleadoEntity> empleado = Optional.of(service.findActivoByCodPersona(codpersona));
            RespuestaCustomizada<EmpleadoEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(empleado.get());
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RespuestaBase> crear(@RequestBody EmpleadoEntity entity) {
        try {
            EmpleadoEntity empleadoActivo = empleadoService.findActivoByCodPersona(entity.getCodpersona());

            RespuestaCustomizada<EmpleadoEntity> respuesta = new RespuestaCustomizada<>();
            if (empleadoActivo == null) {
                empleadoActivo = serviceApplication.save(entity);
                respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
                respuesta.setMensaje("Empleado creado");
            } else {
                empleadoActivo.setUsuarioclaro("Ya existe");
                respuesta.setCodigoEstatus(HttpStatus.OK.value());
                respuesta.setMensaje("El empleado ya existe");
            }
            respuesta.setData(empleadoActivo);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaBase> actualizar(@PathVariable Integer id, @RequestBody EmpleadoEntity entity) {
        try {
            EmpleadoEntity personaEntity = service.save(entity);
            RespuestaCustomizada<EmpleadoEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Empleado actualizado");
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

}
