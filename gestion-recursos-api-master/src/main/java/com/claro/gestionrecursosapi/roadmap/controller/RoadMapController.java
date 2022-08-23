package com.claro.gestionrecursosapi.roadmap.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.novedad.entity.NovedadEntity;
import com.claro.gestionrecursosapi.roadmap.domain.RoadMapService;
import com.claro.gestionrecursosapi.roadmap.entity.*;
import com.claro.gestionrecursosapi.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roadMap")
public class RoadMapController {

    @Autowired
    private RoadMapService roadMapService;

    @GetMapping
    public ResponseEntity<RespuestaBase> buscarTodo() {
        try {
            Iterable<RoadMap> listRoadMap = roadMapService.findAll();
            RespuestaCustomizada<Iterable<RoadMap>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listRoadMap);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBase> finById(@PathVariable Integer id){
        try {
            RoadMap roadMap = roadMapService.findById(id);
            RespuestaCustomizada<RoadMap> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Operación exitosa");
            if (roadMap == null)
                respuesta.setMensaje("No existe roadMap con id: "+id);
            respuesta.setData(roadMap);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RespuestaBase> crearRoadMap(@RequestBody RoadMap roadMap) {
        try {
            roadMap = roadMapService.save(roadMap);
            RespuestaCustomizada<RoadMap> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Operación exitosa");
            respuesta.setData(roadMap);

            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaBase> modificarRoadMap(@PathVariable Integer id, @RequestBody RoadMap roadMap) {
        try {
            roadMap = roadMapService.modificarRoadMap(roadMap);
            RespuestaCustomizada<RoadMap> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Operación exitosa");
            respuesta.setData(roadMap);

            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/codPryBrief/{codPryBrief}")
    public ResponseEntity<RespuestaBase> findByCodPryBrief(@PathVariable Integer codPryBrief) {
        try {
            RoadMap roadMap = roadMapService.findByCodNovedad(codPryBrief);
            RespuestaCustomizada<RoadMap> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(roadMap);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tiposRoadMap")
    public ResponseEntity<RespuestaBase> tipoRoadMapFindAll() {
        try {
            List<TipoRoadMap> tipoRoadMap = roadMapService.tipoRoadMapFindAll();
            RespuestaCustomizada<List<TipoRoadMap>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(tipoRoadMap);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/estadoEntrega")
    public ResponseEntity<RespuestaBase> estadoEntregaFindAll() {
        try {
            List<EstadoEntrega> estadoEntregas = roadMapService.estadoEntregaFindAll();
            RespuestaCustomizada<List<EstadoEntrega>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estadoEntregas);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/estadoRoadMap")
    public ResponseEntity<RespuestaBase> estadoRoadMapFindAll() {
        try {
            List<EstadoRoadMap> estadosRoadMap = roadMapService.estadoRoadMapFindAll();
            RespuestaCustomizada<List<EstadoRoadMap>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estadosRoadMap);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/despliegueRoadMap")
    public ResponseEntity<RespuestaBase> despliegueRoadMapFindAll() {
        try {
            List<DespliegueRoadMap> despliegueRoadMaps = roadMapService.despliegueRoadMapFindAll();
            RespuestaCustomizada<List<DespliegueRoadMap>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(despliegueRoadMaps);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }
}
