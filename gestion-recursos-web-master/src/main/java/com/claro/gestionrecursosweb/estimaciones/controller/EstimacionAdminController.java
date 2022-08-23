package com.claro.gestionrecursosweb.estimaciones.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosweb.estimaciones.dto.CostoEstimacionDTO;
import com.claro.gestionrecursosweb.estimaciones.dto.DonutChartDTO;
import com.claro.gestionrecursosweb.estimaciones.dto.EstimacionAdminDTO;
import com.claro.gestionrecursosweb.estimaciones.dto.EstimacionXProyectoDTO;
import com.claro.gestionrecursosweb.estructura.dto.EstructuraOrganizacionalDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import com.claro.gestionrecursosweb.reportegenerico.dto.ReporteFiltroDto;
import com.google.common.collect.Iterables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/EstimacionesAdmin")
public class EstimacionAdminController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstimacionAdminController.class);

    private final String crear = "/Crear";
    private final String filtro = "/Filtro";
    private final String reporte = "/Reporte";
    private final String monitoreo = "/Monitoreo";

    @Autowired
    private ApiService<ProyectoDto, Integer> proyectoService;

    @Autowired
    private ApiService<CostoEstimacionDTO, Integer> costoEstimacionService;

    @Autowired
    private ApiService<EstimacionAdminDTO, Object> estimacionService;

    @Autowired
    private ApiService<EstimacionXProyectoDTO, Object> estimacionXProyectoService;

    @Autowired
    private ApiService<DonutChartDTO, Object> reporteEstimacion;

    @Autowired
    private ApiService<EstructuraOrganizacionalDto, Integer> estructuraOrganizacionalService;

    private void configurar(Model modelo, HttpServletRequest request) {
        super.setFormularioEstandar(modelo, request, dominio_estimacionesAdmin);
    }

    @GetMapping("/Filtro")
    public String filtroEstimacion(Model modelo, HttpServletRequest request,
            @RequestParam(required = false) String cla) {
        this.configurar(modelo, request);
        mostrarMensajes(modelo, cla);
        estimacionXProyectoService.setapiservicename(dominio_estimaciones);
        Iterable<EstimacionXProyectoDTO> estimacionXProyecto = estimacionXProyectoService
                .findAllByPathVariableNoUser(EstimacionXProyectoDTO.class, "estimacionesXProyecto");
        modelo.addAttribute("estimaciones", estimacionXProyecto);
        modelo.addAttribute("api", apiurl);
        mostrarTitulosYActiveNav(modelo, "Estimaciones", "Filtro", "Estimaciones");
        return dominio_estimaciones + filtro;
    }

    @GetMapping("/Crear")
    public String crearEstimacionAdmmin(Model modelo, HttpServletRequest request) {
        this.configurar(modelo, request);
        this.contenidoModel(modelo);
        modelo.addAttribute("estimacion", null);
        mostrarTitulosYActiveNav(modelo, "Estimaciones", "Crear", "Estimaciones");
        return dominio_estimaciones + crear;
    }

    private void contenidoModel(Model modelo) {

        try {
            proyectoService.setapiservicename(dominio_proyecto);
            Iterable<ProyectoDto> proyectos = proyectoService.findAll(ProyectoDto.class);
            modelo.addAttribute("proyectos", proyectos);

            costoEstimacionService.setapiservicename(dominio_estimaciones);
            List<CostoEstimacionDTO> listCosto = (List<CostoEstimacionDTO>) costoEstimacionService
                    .findAllByPathVariable(CostoEstimacionDTO.class, "costosEstimaciones");
            modelo.addAttribute("costos", listCosto);

            estructuraOrganizacionalService.setapiservicename(dominio_estructuraorganizacional);
            List<EstructuraOrganizacionalDto> estructuras = (List<EstructuraOrganizacionalDto>) estructuraOrganizacionalService
                    .findAll(EstructuraOrganizacionalDto.class);
            modelo.addAttribute("estructuras", estructuras);

            boolean datos_cargados = ((Collection<?>) proyectos).size() > 0 && ((Collection<?>) listCosto).size() > 0
                    && ((Collection<?>) estructuras).size() > 0;
            modelo.addAttribute("datos_cargados", datos_cargados);
        } catch (NullPointerException ex) {
            modelo.addAttribute("datos_cargados", false);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/CrearEstimacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postCrearEstimacionAdmin(
            @RequestBody(required = false) List<EstimacionAdminDTO> estimacion, HttpServletRequest request)
            throws IOException {

        Map<String, Object> response = new HashMap<>();
        try {
            estimacionService.setapiservicename(dominio_estimaciones);
            List<EstimacionAdminDTO> estimacionAdmin = estimacionService.insertAll(estimacion,
                    EstimacionAdminDTO.class);
            HttpStatus httpStatus = HttpStatus.CREATED;
            if (estimacionAdmin.size() == 0) {
                response.put("mensaje", "Error al crear la estimación");
                httpStatus = HttpStatus.CONFLICT;
            } else {
                response.put("mensaje", "Estamacion creada con Éxito");
            }
            response.put("estimacion", estimacionAdmin);
            return new ResponseEntity<Map<String, Object>>(response, httpStatus);
        } catch (Exception e) {
            response.put("mensaje", "Error al guardar la información");
            response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Editar/{id}")
    public String Editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla)
            throws IOException {
        List<EstimacionAdminDTO> lista = new ArrayList<>();
        mostrarMensajes(modelo, cla);
        estimacionService.setapiservicename(dominio_estimaciones);
        Iterable<EstimacionAdminDTO> estimacionAdminDTO = estimacionService.findAllByParamApi("estimacioneXCodproyecto",
                id + "", EstimacionAdminDTO.class);
        modelo.addAttribute("estimacion", estimacionAdminDTO);
        estimacionAdminDTO.forEach(lista::add);
        String urlshare = lista.get(0).getUrlsharepoint() == null ? "" : lista.get(0).getUrlsharepoint();

        modelo.addAttribute("urlsharepoint", urlshare);
        modelo.addAttribute("codProyecto", Iterables.get(estimacionAdminDTO, 0).getCodProyecto());
        modelo.addAttribute("codPerfil", Iterables.get(estimacionAdminDTO, 0).getPerfil());
        this.contenidoModel(modelo);
        mostrarTitulosYActiveNav(modelo, "Estimaciones", "Editar", "Estimaciones");
        return dominio_estimaciones + crear;
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id, Model modelo, HttpServletRequest request) {
        try {
            estimacionService.setapiservicename(dominio_estimaciones);
            Integer codProyecto = estimacionService.findById(id, EstimacionAdminDTO.class).get().getCodProyecto();
            estimacionService.deleteById(id);
            return redireccion("/EstimacionesAdmin/Editar/" + codProyecto, null, "S", "D", request);
        } catch (Exception e) {
            return redireccion("/EstimacionesAdmin/Filtro", null, "E", "D", request);
        }
    }

    @GetMapping("/Reporte")
    public String Reporte(Model modelo, HttpServletRequest request, @RequestParam(required = false) String cla) {
        try {
            this.configurar(modelo, request);
            mostrarMensajes(modelo, cla);
            estimacionXProyectoService.setapiservicename(dominio_estimaciones);
            Iterable<EstimacionXProyectoDTO> estimacionXProyecto = estimacionXProyectoService
                    .findAllByPathVariableNoUser(EstimacionXProyectoDTO.class, "estimacionesXProyecto");
            if ((((Collection<?>) estimacionXProyecto).size() > 0)) {
                modelo.addAttribute("proyectos", estimacionXProyecto);
                modelo.addAttribute("datos_cargados", true);
            } else {
                modelo.addAttribute("datos_cargados", false);
            }
            mostrarTitulosYActiveNav(modelo, "Estimaciones", "Reporte", "EstimacionesReporte");
            return dominio_estimaciones + reporte;
        } catch (NullPointerException ex) {
            modelo.addAttribute("datos_cargados", false);
            return dominio_estimaciones + reporte;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/reporteChart/{codProyecto}/{jerarquia}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reporteChart(@PathVariable String codProyecto, @PathVariable String jerarquia) {
        Map<String, Object> response = new HashMap<>();
        try {
            String urlMapping = "/reporteChart/" + codProyecto + "/" + jerarquia;
            reporteEstimacion.setapiservicename(dominio_estimaciones);
            ResponseEntity<RespuestaCustomizada<EstimacionAdminDTO>> responseEntity = null;
            responseEntity = (ResponseEntity<RespuestaCustomizada<EstimacionAdminDTO>>) reporteEstimacion.findAllByParamApiDeserializacion(urlMapping);
            return responseEntity;
        } catch (Exception e) {
            response.put("mensaje", "Error al consultar información del reporte");
            response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Monitoreo")
    public String Monitoreo(Model modelo, HttpServletRequest request, @RequestParam(required = false) String cla) {

        this.configurar(modelo, request);
        contenidoModel(modelo);
        mostrarMensajes(modelo, cla);
        estimacionXProyectoService.setapiservicename(dominio_estimaciones);
        Iterable<EstimacionXProyectoDTO> estimacionXProyecto = estimacionXProyectoService
                .findAllByPathVariableNoUser(EstimacionXProyectoDTO.class, "estimacionesXProyecto");
        modelo.addAttribute("proyectos", estimacionXProyecto);
        mostrarTitulosYActiveNav(modelo, "Estimaciones", "Monitoreo", "");
        return dominio_estimaciones + monitoreo;
    }

    @ResponseBody
    @RequestMapping(value = "/reporteChartMonitoreo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reporteChartMonitoreo(@RequestBody(required = false) ReporteFiltroDto reporteFiltro,
            HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String urlMapping = "/Monitoreo";
            reporteEstimacion.setapiservicename(dominio_estimaciones);
            ResponseEntity<RespuestaCustomizada<EstimacionAdminDTO>> responseEntity = null;
            responseEntity = (ResponseEntity<RespuestaCustomizada<EstimacionAdminDTO>>) reporteEstimacion.findAllByParamApiDeserializacionPost(reporteFiltro, urlMapping);
            return responseEntity;
        } catch (Exception e) {
            response.put("mensaje", "Error al consultar información del reporte");
            response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}