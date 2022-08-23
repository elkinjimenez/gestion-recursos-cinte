package com.claro.gestionrecursosapi.empleado.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.empleado.domain.EmpleadoEstructuraOrganizacionalService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEstructuraOrganizacionalVU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/EmpleadoEstructura")
public class EmpleadoEstructuraController {

    
    @Autowired
    private EmpleadoEstructuraOrganizacionalService service;
    
    @GetMapping("/getEmpleadoByActivosByEstructura/{codEstructura}")
    public ResponseEntity<RespuestaBase> getEmpleadoByActivosByEstructura (@PathVariable Integer codEstructura) {
        try {
            EmpleadoEstructuraOrganizacionalVU empleado = service.getEmpleadoByActivosByEstructura(codEstructura);
            RespuestaCustomizada<EmpleadoEstructuraOrganizacionalVU> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(empleado);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }
}
