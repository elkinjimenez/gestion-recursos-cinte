package com.claro.gestionrecursosweb.cargue.controller;

import com.claro.gestionrecursosweb.cargue.service.ICargueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/cargue")
public class CargueController {


    @Autowired
    private ICargueService iCargueService;

    @PostMapping(path = "presupuesto")
    public ResponseEntity<?> cargueArchivoPresupuesto(@RequestHeader("usuario") String usuarioSesion, @RequestParam("archivo") MultipartFile archivo) throws IOException {
        return ResponseEntity.ok(iCargueService.carguePresupuesto(archivo, usuarioSesion));
    }
}
