package com.claro.gestionrecursosapi.cambios.controller;


import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.cambios.domain.CambiosService;
import com.claro.gestionrecursosapi.cambios.domain.TiposLineaService;
import com.claro.gestionrecursosapi.cambios.entity.CambiosEntity;
import com.claro.gestionrecursosapi.cambios.entity.EstadoCambiosEntity;
import com.claro.gestionrecursosapi.cambios.entity.TiposLineaEntity;
import com.claro.gestionrecursosapi.cambios.repository.IEstadosCambiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api/v1/cambios")
public class CambiosController {


    @Autowired
    private CambiosService servicecambios;

    @Autowired
    private TiposLineaService tipolineaservice;

    @Autowired
    private IEstadosCambiosRepository estadoscambiosservice;
    
    @GetMapping
    public ResponseEntity<RespuestaBase> buscarTodos () {
        try {
            Iterable<CambiosEntity> cambios = servicecambios.findAll();
            RespuestaCustomizada<Iterable<CambiosEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(cambios);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{Id}")
    public ResponseEntity<RespuestaBase> buscarPorId (@PathVariable Integer Id) {
        try {
            CambiosEntity cambios = servicecambios.findById(Id).get();
            RespuestaCustomizada<CambiosEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(cambios);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<RespuestaBase> Crear (@RequestBody  CambiosEntity cambio) {
        try {
            CambiosEntity cambios = servicecambios.save(cambio);
            RespuestaCustomizada<CambiosEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(cambios);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaBase> Actualizar (@PathVariable Integer id, @RequestBody CambiosEntity entity)  {
        try {
            CambiosEntity cambios = servicecambios.save(entity);
            RespuestaCustomizada<CambiosEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(cambios);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaBase> eliminar(@PathVariable Integer id) {

        try {
            servicecambios.delete(servicecambios.findById(id).get());
            RespuestaBase respuesta = new RespuestaBase();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Se elimino registro");
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tiposLinea")
    public ResponseEntity<RespuestaBase> allTiposLinea () {
        try {
            Iterable<TiposLineaEntity> tiposlinea = tipolineaservice.findAll();
            RespuestaCustomizada<Iterable<TiposLineaEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(tiposlinea);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tiposLinea/{id}")
    public ResponseEntity<RespuestaBase> buscarTipoLineaPorId (@PathVariable Integer id) {
        try {
            Optional<TiposLineaEntity> tipolinea = tipolineaservice.findById(id);
            RespuestaCustomizada<Optional<TiposLineaEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(tipolinea);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/estados")
    public ResponseEntity<RespuestaBase> allEstados () {
        try {
            Iterable<EstadoCambiosEntity> estados = estadoscambiosservice.findAll();
            RespuestaCustomizada<Iterable<EstadoCambiosEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estados);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/estados/{id}")
    public ResponseEntity<RespuestaBase> buscarEstadoporId (@PathVariable Integer id) {
        try {
            Optional<EstadoCambiosEntity> estado = estadoscambiosservice.findById(id);
            RespuestaCustomizada<Optional<EstadoCambiosEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }
  
}
