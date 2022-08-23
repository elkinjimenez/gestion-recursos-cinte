package com.claro.gestionrecursosapi.gerente.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.gerente.domain.GerenteService;
import com.claro.gestionrecursosapi.gerente.entity.GerenteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gerente")
public class GerenteController {

    @Autowired
    private GerenteService service;

    @GetMapping
    public ResponseEntity<?> getAllGerentes() {
        try {
            Iterable<GerenteEntity> listaPerfiles = service.findAllGerentes();
            RespuestaCustomizada<Iterable<GerenteEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listaPerfiles);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarGerente(@RequestBody GerenteEntity gerenteEntity, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.actualizarGerente(gerenteEntity, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findGerenteByCodEmpleado(@PathVariable Integer id) {
        GerenteEntity gerenteEntity = service.findGerenteByCodEmpleado(id);
        return ResponseEntity.ok().body(gerenteEntity);
    }

}
