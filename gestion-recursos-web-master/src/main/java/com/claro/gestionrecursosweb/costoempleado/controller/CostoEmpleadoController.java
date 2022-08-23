package com.claro.gestionrecursosweb.costoempleado.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosweb.costoempleado.dto.CostoEmpleadoDto;
import com.claro.gestionrecursosweb.costoempleado.dto.CostoEmpleadoVUDto;
import com.claro.gestionrecursosweb.novedad.dto.NovedadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/CostoEmpleado")
public class CostoEmpleadoController extends BaseController {
    private final String allPath = "/Costos";
    private final String editPath = "/Costo";

    @Autowired
    private ApiService<CostoEmpleadoVUDto, Integer> serviceCosto;
    @Autowired
    private ApiService<CostoEmpleadoDto, Integer> serviceCostoEmp;

    public void configurar(Model modelo, HttpServletRequest request) {
        serviceCosto.setapiservicename(dominio_costoempleado);
        super.setFormularioEstandar(modelo, request, dominio_costoempleado);
    }

    @GetMapping("/Filtro")
    public String traerCostosCreados(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        //cargarListas(modelo);
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);

        serviceCosto.setapiservicename(dominio_costoempleado + "/costosCreados");
        Iterable<CostoEmpleadoVUDto> dto = serviceCosto.findAll(CostoEmpleadoVUDto.class);

        modelo.addAttribute("costoEmpleado", dto);

        return dominio_costoempleado + allPath;
        ///return "hallazgorespuesta/HallazgoResp";
    }

    @GetMapping("/Crear")
    public String crearCostoEmpleado(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        /*//cargarListas(modelo);
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);

        serviceCostoEmp.setapiservicename(dominio_costo_empleado);

        modelo.addAttribute("costoEmpleado", new CostoEmpleadoDto());*/

        return "hallazgorespuesta/HallazgoResp";
    }

    @GetMapping("/traerVariableCalFactorPunto")
    public ResponseEntity<?> cargarConstante(Model modelo, @RequestParam(required = false) String cla,
            HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String url = "/traerValorProperties/calFactorPunto";
            serviceCostoEmp.setapiservicename(dominio_costoempleado);
            ResponseEntity<RespuestaCustomizada<Integer>> responseEntity = null;
            responseEntity = (ResponseEntity<RespuestaCustomizada<Integer>>) serviceCostoEmp
                    .findAllByParamApiDeserializacion(url);
            return responseEntity;
        } catch (Exception e) {
            response.put("mensaje", "Error al consultar información del reporte");
            response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping("/Filtro")
    public String filtro(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);

        serviceCosto.setapiservicename(dominio_costo_empleado + "/costosCreados");
        Iterable<CostoEmpleadoVUDto> dto = (List<CostoEmpleadoVUDto>) serviceCosto.findAll(CostoEmpleadoVUDto.class);

        modelo.addAttribute("costoEmpleado", dto);
        return dominio_costo_empleado + allPath;
    }*/

    /*@GetMapping("/Filtro")
    public String filtro(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {

        *//*HallazgoService.setapiservicename(dominio_hallazgo_respuesta + "/hallazgos");
        List<HallazgoDto> hallazgos =  (List<HallazgoDto>) HallazgoService.findAll(HallazgoDto.class);*//*

        hallazgosReportadosService.setapiservicename(dominio_hallazgo + "/hallazgosReportados");
        List<HallazgosReportadosDto> hallazgos =  (List<HallazgosReportadosDto>) hallazgosReportadosService.findAll(HallazgosReportadosDto.class);

        modelo.addAttribute("hallazgo", hallazgos);

        return dominio_hallazgo_respuesta + allPath;
    }*/

  /*  @PostMapping("/Filtro")
    public String filtrar(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        cargarListas(modelo);

        mostrarMensajes(modelo, cla);

        HallazgoService.setapiservicename(dominio_hallazgo_respuesta + "/hallazgos");
        List<HallazgoDto> dto = (List<HallazgoDto>)HallazgoService.findAll(HallazgoDto.class);

        modelo.addAttribute("hallazgo", dto);


        return dominio_hallazgo_respuesta + allPath;
    }*/
}
