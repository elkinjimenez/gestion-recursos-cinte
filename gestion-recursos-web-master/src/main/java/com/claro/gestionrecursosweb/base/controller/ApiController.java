package com.claro.gestionrecursosweb.base.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.proyecto.domain.ProyectoService;
import com.google.common.base.Charsets;

/**
 * Controlador para solicitar información directamente desde Ajax al API
 *
 * @author German Jesus Rojas
 */
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

    @Autowired
    private ApiService<Object, Integer> service;

    @Autowired
    private ProyectoService serviceproject;

    @PostMapping("/{dominio}")
    public ResponseEntity<?> POST(@PathVariable String dominio, @RequestParam(required = false) String vu) {
        Object respuesta = null;
        if (vu != null && !vu.isEmpty()) {
            dominio += "/" + vu;
        }
        if(dominio.equals("proyecto")){
            respuesta =   serviceproject.getProyectosGantt();
            return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
        }
        service.setapiservicename(dominio);
        respuesta = service.findAll(Object.class);
        return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/{dominio}/{id}")
    public ResponseEntity<?> POST(@PathVariable String dominio, @PathVariable Integer id, @RequestParam(required = false) String vu) {
        service.setapiservicename(dominio + (vu != null ? "/" + vu : ""));
        Object respuesta = service.findById(id, Object.class);
        return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/{dominio}/{apiresource}/{id}")
    public ResponseEntity<?> POST(@PathVariable String dominio, @PathVariable String apiresource, @PathVariable Integer id, @RequestParam(required = false) String vu) {
        service.setapiservicename(dominio + "/" + apiresource + (vu != null ? "/" + vu : ""));
        Object respuesta = service.findById(id, Object.class);
        return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/{dominio}/{apiresource1}/{apiresource2}/{id}")
    public ResponseEntity<?> POST(@PathVariable String dominio, @PathVariable String apiresource1, @PathVariable String apiresource2, @PathVariable Integer id, @RequestParam(required = false) String vu) {
        service.setapiservicename(dominio + "/" + apiresource1 + "/" + apiresource2 + (vu != null ? "/" + vu : ""));
        Object respuesta = service.findById(id, Object.class);
        return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/descargar")
    public void descargar(HttpServletResponse response, @RequestParam String dominio, @RequestParam String q) throws IOException {
        try {
            q = URLEncoder.encode(q, Charsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
        }
        response.sendRedirect(apiurl + "/" + dominio + "/descargar?q=" + q + "&CLUSUARIO=" + getUsuarioSesion());
    }

}
