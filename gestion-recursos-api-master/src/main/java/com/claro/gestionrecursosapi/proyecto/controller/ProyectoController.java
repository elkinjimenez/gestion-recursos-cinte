package com.claro.gestionrecursosapi.proyecto.controller;

import java.util.Optional;

import com.claro.gestionrecursosapi.excepcion.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.proyecto.domain.ProyectoService;
import com.claro.gestionrecursosapi.proyecto.entity.ProyectoEntity;


import javax.persistence.EntityManager;
import javax.persistence.Query;

@RestController
@RequestMapping("/api/v1/proyecto")
public class ProyectoController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private ProyectoService service;

    @GetMapping
    public ResponseEntity<RespuestaBase> buscarTodo(@RequestHeader("CL_USUARIO") String CLUSUARIO) {
        try {
            setSessionDatesFormat();

            Iterable<ProyectoEntity> proyectos = service.findAll();
            RespuestaCustomizada<Iterable<ProyectoEntity>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(proyectos);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBase> buscarPorId(@RequestHeader("CL_USUARIO") String CLUSUARIO, @PathVariable Integer id) {
        try {
            Optional<ProyectoEntity> personaEntity = service.findById(id);
            RespuestaCustomizada<ProyectoEntity> respuesta = new RespuestaCustomizada<>();
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
    public ResponseEntity<RespuestaBase> crear(@RequestHeader("CL_USUARIO") String CLUSUARIO, @RequestBody ProyectoEntity entity) throws BusinessException {
        try {
            ProyectoEntity personaEntity = service.save(entity);

            RespuestaCustomizada<ProyectoEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
            respuesta.setMensaje("Proyecto creado");
            respuesta.setData(personaEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                throw new BusinessException(e.getMessage());
            }
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaBase> actualizar(@RequestHeader("CL_USUARIO") String CLUSUARIO, @PathVariable Integer id, @RequestBody ProyectoEntity entity) {
        try {
            ProyectoEntity personaEntity = service.update(entity);
            RespuestaCustomizada<ProyectoEntity> respuesta = new RespuestaCustomizada<>();
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
    public ResponseEntity<RespuestaBase> eliminar(@RequestHeader("CL_USUARIO") String CLUSUARIO, @PathVariable Integer id) {
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

    /**
     * Asigna el formato de fechas de trabajo, para consultar las vistas de DB
     * solución a error ORA-01843: not a valid month, el formato de fechas usado
     * por las vistas actualmente es DD/MM/YYYY
     */
    @Scheduled(fixedRate = 180000)
    private void setSessionDatesFormat() {
        try {
            Query query = entityManager.createNativeQuery("ALTER SESSION SET nls_date_format = 'DD/MM/YYYY'");

            query.getResultList();
        } catch (Exception e) {
            //System.out.println("session alter: " + ExceptionUtils.getStackTraceString(e));
        }
    }

    @GetMapping("/getProyectosWithCompromiso")
    public ResponseEntity<RespuestaBase> getProyectosWithCompromiso() {
        try {
            Iterable<ProyectoEntity> proyectos = service.getProyectosWithCompromiso();
            RespuestaCustomizada<Iterable<ProyectoEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(proyectos);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/proyectos-gantt")
    public ResponseEntity<RespuestaBase> TodosProyectosGantt() {
        try {

            Iterable<ProyectoEntity> proyectos = service.getProyectosGantt();
            RespuestaCustomizada<Iterable<ProyectoEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(proyectos);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }

    }


}
