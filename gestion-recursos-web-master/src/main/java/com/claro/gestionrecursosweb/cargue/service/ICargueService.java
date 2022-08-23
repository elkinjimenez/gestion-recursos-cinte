package com.claro.gestionrecursosweb.cargue.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.claro.gestionrecursosweb.cargue.model.CarguePresupuesto;
import org.springframework.web.multipart.MultipartFile;

public interface ICargueService {

    public Map<String, List<CarguePresupuesto>> carguePresupuesto(MultipartFile file, String usuarioSesion) throws IOException;
}
