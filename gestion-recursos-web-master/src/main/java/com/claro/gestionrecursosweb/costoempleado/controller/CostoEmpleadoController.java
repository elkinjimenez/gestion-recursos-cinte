package com.claro.gestionrecursosweb.costoempleado.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.costoempleado.dto.CostoEmpleadoDto;
import com.claro.gestionrecursosweb.costoempleado.dto.CostoEmpleadoVUDto;
import com.claro.gestionrecursosweb.novedad.dto.NovedadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
