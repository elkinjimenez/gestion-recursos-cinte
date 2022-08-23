package com.claro.gestionrecursosapi.novedad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.novedad.domain.NovedadService;
import com.claro.gestionrecursosapi.novedad.entity.NovedadEntity;
import com.claro.gestionrecursosapi.novedad.entity.TipoNovedadEntity;
import com.claro.gestionrecursosapi.utils.ExceptionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RestController
@RequestMapping("/api/v1/novedad")
public class NovedadController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private NovedadService novedadService;

    @GetMapping
    public ResponseEntity<RespuestaBase> getAllNovedades(@RequestHeader("CL_USUARIO") String CLUSUARIO) {
        try {
            setSessionDatesFormat();
            Iterable<NovedadEntity> lista = novedadService.getAllNovedades(CLUSUARIO);
            RespuestaCustomizada<Iterable<NovedadEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Operación exitosa");
            respuesta.setData(lista);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RespuestaBase> crearNovedad(@RequestBody NovedadEntity novedad) {
        try {
            setSessionDatesFormat();

            novedad = novedadService.crearNovedad(novedad);
            RespuestaCustomizada<NovedadEntity> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Operación exitosa");
            respuesta.setData(novedad);

            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaBase> modificarNovedad(@PathVariable Integer id, @RequestBody NovedadEntity novedad) {
        try {
            novedad = novedadService.modificarNovedad(novedad);
            RespuestaCustomizada<NovedadEntity> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Operación exitosa");
            respuesta.setData(novedad);

            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaBase> eliminarNovedad(@PathVariable(name = "id") Integer id) {
        try {
            novedadService.eliminarNovedad(id);
            RespuestaCustomizada<NovedadEntity> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Operación exitosa");

            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBase> getNovedadById(@PathVariable(name = "id") Integer id) {
        try {
            NovedadEntity novedad = novedadService.getNovedadById(id);
            RespuestaCustomizada<NovedadEntity> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Operación exitosa");
            respuesta.setData(novedad);

            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tipos")
    public ResponseEntity<RespuestaBase> getTiposNovedad() {
        try {
            Iterable<TipoNovedadEntity> lista = novedadService.getTiposNovedad();
            RespuestaCustomizada<Iterable<TipoNovedadEntity>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Operación exitosa");
            respuesta.setData(lista);

            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));

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

}
