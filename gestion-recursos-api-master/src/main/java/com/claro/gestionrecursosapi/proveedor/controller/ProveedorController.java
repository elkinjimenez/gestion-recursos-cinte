package com.claro.gestionrecursosapi.proveedor.controller;

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
import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.proveedor.domain.IProveedorService;
import com.claro.gestionrecursosapi.proveedor.entity.ProveedorEntity;

@RestController
@RequestMapping("/api/v1/proveedor")
public class ProveedorController {

    @Autowired
    private IProveedorService service;

    @GetMapping
    public ResponseEntity<RespuestaBase> buscarTodos() {
        try {
            Iterable<ProveedorEntity> resultadoConsulta = service.buscarTodos();
            RespuestaCustomizada<Iterable<ProveedorEntity>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultadoConsulta);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (NoExisteExcepcion e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBase> buscarPorId(@PathVariable int id) throws NoExisteExcepcion {
        try {
            ProveedorEntity proveedorEntity = service.buscarPorId(id);
            RespuestaCustomizada<ProveedorEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(proveedorEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (NoExisteExcepcion e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nit/{nit}")
    public ResponseEntity<RespuestaBase> buscarPorNit(@PathVariable String nit) throws NoExisteExcepcion {
        try {
            ProveedorEntity proveedorEntity = service.buscarPorNit(nit);
            RespuestaCustomizada<ProveedorEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(proveedorEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (NoExisteExcepcion e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/linea/{linea}")
    public ResponseEntity<RespuestaBase> buscarPorLinea(@PathVariable String linea) throws NoExisteExcepcion {
        try {
            Iterable<ProveedorEntity> resultadoConsulta = service.buscarPorLinea(linea);
            RespuestaCustomizada<Iterable<ProveedorEntity>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultadoConsulta);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (NoExisteExcepcion e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<RespuestaBase> buscarPorEspecialidad(@PathVariable String especialidad) throws NoExisteExcepcion {
        try {
            Iterable<ProveedorEntity> resultadoConsulta = service.buscarPorEspecialidad(especialidad);
            RespuestaCustomizada<Iterable<ProveedorEntity>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultadoConsulta);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (NoExisteExcepcion e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<RespuestaBase> buscarPorEstado(@PathVariable String estado) throws NoExisteExcepcion {
        try {
            Iterable<ProveedorEntity> resultadoConsulta = service.buscarPorEstado(estado);
            RespuestaCustomizada<Iterable<ProveedorEntity>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultadoConsulta);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (NoExisteExcepcion e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    public ResponseEntity<RespuestaBase> crear(@RequestBody ProveedorEntity entity) {

        try {
            ProveedorEntity proveedorEntity = service.crear(entity);
            RespuestaCustomizada<ProveedorEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
            respuesta.setMensaje("Proveedor creado");
            respuesta.setData(proveedorEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
        } catch (YaExisteExcepcion | DataIncorrectaExcepcion e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaBase> actualizar(@PathVariable int id, @RequestBody ProveedorEntity entity) {
        try {
            ProveedorEntity proveedorEntity = service.actualizar(id, entity);
            RespuestaCustomizada<ProveedorEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Se actualizo proveedor");
            respuesta.setData(proveedorEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (NoExisteExcepcion e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);

        } catch (DataIncorrectaExcepcion e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RespuestaBase> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            RespuestaBase respuesta = new RespuestaBase();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Se elimino registro ");
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (NoExisteExcepcion e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }

    }
}
