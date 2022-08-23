package com.claro.gestionrecursosapi.empleado.controller;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoForm;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoFormJefes;
import com.claro.gestionrecursosapi.excepcion.BusinessException;
import com.claro.gestionrecursosapi.excepcion.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.empleado.application.EmpleadoApplication;
import com.claro.gestionrecursosapi.empleado.domain.EmpleadoControlService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadocontrolEntity;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/empleadocontrol")
public class EmpleadoControlController {

    @Autowired
    private EmpleadoControlService service;
    @Autowired
    private EmpleadoApplication serviceEmpleadoApplication;

    @GetMapping
    public ResponseEntity<RespuestaBase> buscarTodo() {
        try {
            Iterable<EmpleadocontrolEntity> listaPerfileNivel = service.findAll();
            RespuestaCustomizada<Iterable<EmpleadocontrolEntity>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listaPerfileNivel);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBase> buscarPorId(@PathVariable Integer id) {
        try {
            Optional<EmpleadocontrolEntity> EmpleadocontrolEntity = service.findById(id);
            RespuestaCustomizada<EmpleadocontrolEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(EmpleadocontrolEntity.get());
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/codtarea")
    public ResponseEntity<RespuestaBase> findAllByCodproyecto(@RequestParam Integer v) {
        try {
            Iterable<EmpleadocontrolEntity> resultado = service.findAllByCodTarea(v);
            RespuestaCustomizada<Iterable<EmpleadocontrolEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEmpleadoByEstructuraTipo/{cod}")
    public ResponseEntity<RespuestaBase> getEmpleadoByEstructuraTipo(@PathVariable int cod) {
        try {
            List<EmpleadoFormJefes> resultado = service.getEmpleadoByEstructuraTipo(cod);
            RespuestaCustomizada<List<EmpleadoFormJefes>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEmpleadoFormById/{Id}")
    public ResponseEntity<RespuestaBase> getEmpleadoFormById(@PathVariable int Id) {
        try {
            List<EmpleadoForm> resultado = service.getEmpleadoFormById(Id);
            RespuestaCustomizada<List<EmpleadoForm>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(resultado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RespuestaBase> crear(@Valid @RequestBody EmpleadocontrolEntity entity, BindingResult result) throws BusinessException, DataNotFoundException {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getFieldErrors()
                    .stream()
                    .forEach(e -> errors.add(e.getField().concat(": ".concat(e.getDefaultMessage()))));
            RespuestaBase respuestaBase = new RespuestaBase();
            respuestaBase.setCodigoEstatus(HttpStatus.BAD_REQUEST.value());
            respuestaBase.setMensaje(errors.stream().collect(Collectors.joining(", ")));
            return ResponseEntity.badRequest().body(respuestaBase);
        }

        try {
            EmpleadocontrolEntity EmpleadocontrolEntity = serviceEmpleadoApplication.saveControl(entity);
            RespuestaCustomizada<EmpleadocontrolEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
            respuesta.setMensaje("Empleado control creado");
            respuesta.setData(EmpleadocontrolEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
        } catch (BusinessException ex) {
            throw new BusinessException(ex);
        } catch (DataNotFoundException ex) {
            throw new DataNotFoundException(ex);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaBase> actualizar(@PathVariable Integer id, @RequestBody EmpleadocontrolEntity entity) {
        try {
            EmpleadocontrolEntity EmpleadocontrolEntity = service.save(entity);
            RespuestaCustomizada<EmpleadocontrolEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Empleado Control actualizado");
            respuesta.setData(EmpleadocontrolEntity);
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

    @GetMapping("/horasmesactual/{id}")
    public ResponseEntity<RespuestaBase> horasMesActual(@PathVariable Integer id) {
        try {
            Calendar fecha = Calendar.getInstance();
            Date fechaIni = new Date();
            Date fechaFin = new Date();

            fecha.set(Calendar.HOUR_OF_DAY, 0);
            fecha.set(Calendar.MINUTE, 0);
            fecha.set(Calendar.SECOND, 0);
            fecha.set(Calendar.MILLISECOND, 0);

            if (fecha.get(Calendar.DAY_OF_MONTH) <= 5) {
                // Si la fecha actual es menor al día 5 del mes actual, se muestra la información del mes anterior
                fecha.add(Calendar.MONTH, -1);
            }

            fecha.set(Calendar.DAY_OF_MONTH, 1);
            fechaIni = fecha.getTime();

            fecha.set(Calendar.DAY_OF_MONTH, fecha.getActualMaximum(Calendar.DAY_OF_MONTH));
            fechaFin = fecha.getTime();

            BigDecimal horasMesActual = service.sumHorasByFecha(id, fechaIni, fechaFin);
            RespuestaCustomizada<BigDecimal> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
            respuesta.setMensaje("Empleado control creado");
            respuesta.setData(horasMesActual);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/horastarearecursofecha")
    public ResponseEntity<RespuestaBase> horasTareaRecursoFecha(@RequestBody EmpleadocontrolEntity empleadoControl) {
        try {

            RespuestaCustomizada<EmpleadocontrolEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
            respuesta.setMensaje("Consulta exitosa");

            EmpleadocontrolEntity entityHoras = new EmpleadocontrolEntity();
            entityHoras.setHoras(new BigDecimal(service.horasTareaFechaPorRecurso(empleadoControl.getCodempleado(),
                    empleadoControl.getCodtarea(), empleadoControl.getFechahorainicio())));
            respuesta.setData(entityHoras);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/horasrecursofecha")
    public ResponseEntity<RespuestaBase> horasRecursoFecha(@RequestBody EmpleadocontrolEntity empleadoControl) {
        try {

            RespuestaCustomizada<EmpleadocontrolEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
            respuesta.setMensaje("Consulta exitosa");

            EmpleadocontrolEntity entityHoras = new EmpleadocontrolEntity();
            entityHoras.setHoras(new BigDecimal(service.horasFechaPorRecurso(empleadoControl.getCodempleado(),
                    empleadoControl.getFechahorainicio())));
            respuesta.setData(entityHoras);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }


}
