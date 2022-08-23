package com.claro.gestionrecursosapi.empleado.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.empleado.domain.EmpleadoVUService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoVU;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@RestController
@RequestMapping("/api/v1/empleado/vu")
public class EmpleadoVUController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private EmpleadoVUService service;

    @GetMapping
    public ResponseEntity<RespuestaBase> findAll(@RequestHeader("CL_USUARIO") String CLUSUARIO) {
        try {
            setSessionDatesFormat();
            Iterable<EmpleadoVU> resultado = service.findAll(CLUSUARIO);
            RespuestaCustomizada<Iterable<EmpleadoVU>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("findAll")
    public ResponseEntity<RespuestaBase> findAll() {
        try {
            setSessionDatesFormat();
            Iterable<EmpleadoVU> resultado = service.findTodos();
            RespuestaCustomizada<Iterable<EmpleadoVU>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAllBycodEstructura/{codTipoEstructura}")
    public ResponseEntity<RespuestaBase> findAllBycodEstructura(@PathVariable Integer codTipoEstructura) {
        try {
            setSessionDatesFormat();
            Iterable<EmpleadoVU> resultado = service.findAllBycodEstructura(codTipoEstructura);
            RespuestaCustomizada<Iterable<EmpleadoVU>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBase> findByCodpersona(@PathVariable Integer id) {

        try {
            Optional<EmpleadoVU> resultado = service.findByCodEmpleado(id);
            RespuestaCustomizada<Optional<EmpleadoVU>> respuesta = new RespuestaCustomizada<>();
            if (resultado.isPresent()) {
                resultado.get().setUsuarioclaroedicion(resultado.get().getUsuarioclaro());
            }
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("error :" + e.getMessage());
            return null;
        }

    }

    @GetMapping("/gerente")
    public ResponseEntity<?> findAllGerentes() {
        List<EmpleadoVU> lGerentes = (List<EmpleadoVU>) service.findAllGerentes();
        return ResponseEntity.ok().body(lGerentes);
    }

    /**
     * Asigna el formato de fechas de trabajo, para consultar las vistas de DB
     * solución a error ORA-01843: not a valid month, el formato de fechas usado por
     * las vistas actualmente es DD/MM/YYYY
     */
    // @Scheduled(fixedRate = 180000)
    private void setSessionDatesFormat() {
        try {
            Query query = entityManager.createNativeQuery("ALTER SESSION SET nls_date_format = 'DD/MM/YYYY'");

            query.getResultList();
        } catch (Exception e) {
            // System.out.println("session alter: " +
            // ExceptionUtils.getStackTraceString(e));
        }
    }

}
