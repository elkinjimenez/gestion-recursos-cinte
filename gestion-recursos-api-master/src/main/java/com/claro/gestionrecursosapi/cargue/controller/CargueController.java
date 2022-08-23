package com.claro.gestionrecursosapi.cargue.controller;

import com.claro.gestionrecursosapi.cargue.service.CargueService;
import com.claro.gestionrecursosapi.cargue.service.ICargueService;
import com.claro.gestionrecursosapi.excepcion.BusinessException;
import com.claro.gestionrecursosapi.excepcion.DataNotFoundException;
import com.claro.gestionrecursosapi.utils.CargueUtil;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/cargue")
public class CargueController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CargueController.class);
    private static final String FORMATO1 = "xls";
    private static final String FORMATO2 = "xlsx";

    @Autowired
    private CargueUtil cargueUtil;

    @Autowired
    private ICargueService iCargueService;

    @PostMapping("presupuesto")
    public ResponseEntity<?> cargarArchivoPresupuesto(@RequestHeader(value = "CL_USUARIO",required = false) String usuarioSesion, @RequestParam("archivo") MultipartFile archivo) throws DataNotFoundException, BusinessException, IOException {
        if(archivo.isEmpty()) {
            throw new DataNotFoundException("No se detectó archivo");
        }
        if (Objects.isNull(usuarioSesion)) {
            throw new BusinessException("parametro de cabecera usuario no detectado");
        }
        String extension = FilenameUtils.getExtension(archivo.getOriginalFilename());
        if (!(extension.toLowerCase().equals(FORMATO1) || extension.toLowerCase().equals(FORMATO2))) {
            throw new BusinessException("El archivo no contiene la extesión correcta");
        }

        return ResponseEntity.ok(iCargueService.carguePresupuesto(cargueUtil.lecturaArchivoPresupuesto(archivo, extension), usuarioSesion));
    }
}
