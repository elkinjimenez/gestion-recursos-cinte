package com.claro.gestionrecursosapi.horasextra.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.horasextra.domain.HoraExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import com.claro.gestionrecursosapi.horasextra.entity.HorasExtraEntity;

@RestController
@RequestMapping("/api/v1/HorasExtras")
public class HoraExtraController {

    @Autowired
    private HoraExtraService service;

    @GetMapping
    public ResponseEntity<RespuestaBase> TodasHorasExtras() {
        try {
            Iterable<HorasExtraEntity> listasPersonas = service.findAll();
            RespuestaCustomizada<Iterable<HorasExtraEntity>> respuesta = new RespuestaCustomizada<>();

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
            Optional<HorasExtraEntity> HorasExtraEntity = service.findById(id);
            RespuestaCustomizada<HorasExtraEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(HorasExtraEntity.get());
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RespuestaBase> crear(@RequestBody HorasExtraEntity entity) {
        try {

        	HorasExtraEntity HorasExtraEntity = service.save(entity);
            RespuestaCustomizada<HorasExtraEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
            respuesta.setMensaje("Hora Extra creada");
            respuesta.setData(HorasExtraEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaBase> actualizar(@PathVariable Integer id, @RequestBody HorasExtraEntity entity) {
        try {
            HorasExtraEntity HorasExtraEntity = service.save(entity);
            RespuestaCustomizada<HorasExtraEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Hora extra actualizada");
            respuesta.setData(HorasExtraEntity);
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
