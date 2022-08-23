package com.claro.gestionrecursosweb.hallazgorespuesta.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.compromisosfabrica.dto.CompromisosFabricaDto;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
/*import com.claro.gestionrecursosweb.hallazgo.dto.HallazgoDto;
import com.claro.gestionrecursosweb.hallazgo.dto.HallazgosReportadosDto;
import com.claro.gestionrecursosweb.hallazgo.dto.TipoHallazgoDto;*/
import com.claro.gestionrecursosweb.hallazgo.dto.HallazgoDto;
import com.claro.gestionrecursosweb.hallazgo.dto.HallazgosReportadosDto;
import com.claro.gestionrecursosweb.hallazgo.dto.TipoHallazgoDto;
import com.claro.gestionrecursosweb.opcionfront.dto.OpcionFrontDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/HallazgoRespuesta")
public class HallazgoRespController extends BaseController {
    private Iterable<ProyectoDto> proyectos;
    private Iterable<EmpleadoVUDto> empleados;
    private Iterable<TipoHallazgoDto> hallazgoTipos;
    private final String allPath = "/HallazgosResp";
    private final String editPath = "/HallazgoResp";

    @Autowired
    private ApiService<CompromisosFabricaDto, Integer> service;

    @Autowired
    private ApiService<HallazgoDto, Integer> HallazgoService;

    @Autowired
    private ApiService<HallazgosReportadosDto, Integer> hallazgosReportadosService;

    @Autowired
    private ApiService<OpcionFrontDto, Integer> opcionFrontService;

    private void configurar(Model modelo, HttpServletRequest request) {
        HallazgoService.setapiservicename(dominio_hallazgo_respuesta);
        hallazgosReportadosService.setapiservicename(dominio_hallazgo_vista);

        super.setFormularioEstandar(modelo, request, "HallazgoRespuesta");
    }

    @GetMapping("/Filtro")
    public String filtro(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {

        /*HallazgoService.setapiservicename(dominio_hallazgo_respuesta + "/hallazgos");
        List<HallazgoDto> hallazgos =  (List<HallazgoDto>) HallazgoService.findAll(HallazgoDto.class);*/

        hallazgosReportadosService.setapiservicename(dominio_hallazgo + "/hallazgosReportados");
        List<HallazgosReportadosDto> hallazgos = (List<HallazgosReportadosDto>) hallazgosReportadosService.findAll(HallazgosReportadosDto.class);

        modelo.addAttribute("hallazgo", hallazgos);
        mostrarTitulosYActiveNav(modelo, "Hallazgo", "Filtro", "HallazgoRespuesta");
        return dominio_hallazgo_respuesta + allPath;
    }

    @PostMapping("/Filtro")
    public String filtrar(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        
        mostrarMensajes(modelo, cla);
        HallazgoService.setapiservicename(dominio_hallazgo_respuesta + "/hallazgos");
        List<HallazgoDto> dto = (List<HallazgoDto>) HallazgoService.findAll(HallazgoDto.class);
        modelo.addAttribute("hallazgo", dto);
        return dominio_hallazgo_respuesta + allPath;
    }

    @GetMapping("/Editar/{id}")
    public String editarHallazgo(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request, @PathVariable Integer id) {
       
        cargarListasEdicion(modelo);
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);
        if (Objects.nonNull(id)) {
            hallazgosReportadosService.setapiservicename(dominio_hallazgo_vista);
            Optional<HallazgosReportadosDto> hallazgoReportado = hallazgosReportadosService.findById(id, HallazgosReportadosDto.class);
            modelo.addAttribute("hallazgoReportado", hallazgoReportado.get());
        }

        HallazgoService.setapiservicename(dominio_hallazgo_respuesta);
        HallazgoDto hallazgo = HallazgoService.findById(id, HallazgoDto.class).get();
        modelo.addAttribute("hallazgo", hallazgo);
        mostrarTitulosYActiveNav(modelo, "Hallazgo", "Editar", "HallazgoRespuesta");
        return dominio_hallazgo_respuesta + editPath;
    }

    @PostMapping("/Editar/{id}")
    public String postEditarHallazgo(Model modelo, HallazgoDto hallazgo, HttpServletRequest request) {

        configurar(modelo, request);
        cargarListasEdicion(modelo);

        HallazgoService.setapiservicename(dominio_hallazgo_respuesta);
        HallazgoDto RespuestaCreada = HallazgoService.update(hallazgo.getId(), hallazgo, HallazgoDto.class);
        mostrarMensajes(modelo, "S", "U");
        modelo.addAttribute("hallazgo", RespuestaCreada);
        return dominio_hallazgo_respuesta + editPath;
    }

    private void cargarLista(Model modelo)
    {
    	opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
        List<OpcionFrontDto> esHallazgo = (List<OpcionFrontDto>) opcionFrontService.findAllByPathVariable(OpcionFrontDto.class, "EsHallazgo");
        modelo.addAttribute("esHallazgo", esHallazgo);
    }
   
    private void cargarListasEdicion(Model modelo) {
    	
        try {
            
            opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
            List<OpcionFrontDto> problemaIdentificado = (List<OpcionFrontDto>) opcionFrontService.findAllByPathVariable(OpcionFrontDto.class, "ProblemaIdentificado");
            modelo.addAttribute("problemaIdentificado", problemaIdentificado);

            opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
            List<OpcionFrontDto> solucionEntregada = (List<OpcionFrontDto>) opcionFrontService.findAllByPathVariable(OpcionFrontDto.class, "SolucionEntregada");
            modelo.addAttribute("solucionEntregada", solucionEntregada);

            boolean datos_cargados = ((Collection<?>) solucionEntregada).size() > 0 && ((Collection<?>) problemaIdentificado).size() > 0;
            modelo.addAttribute("datos_cargados", datos_cargados);

        } catch (NullPointerException ex) {
            modelo.addAttribute("datos_cargados", false);
        }

    }

    private TipoHallazgoDto gethallazgoTipoByCod(Integer cod) {
        TipoHallazgoDto hallazgoTipo = null;
        for (TipoHallazgoDto tipoHallazgoDto : hallazgoTipos) {
            if (tipoHallazgoDto.getId().equals(cod)) {
                hallazgoTipo = tipoHallazgoDto;
            }
        }
        return hallazgoTipo;
    }

    private EmpleadoVUDto getEmpleadoByCod(Integer cod) {
        EmpleadoVUDto empleadoResult = null;
        for (EmpleadoVUDto empleado : empleados) {
            if (empleado.getCodempleado().equals(cod)) {
                empleadoResult = empleado;
            }
        }
        return empleadoResult;
    }

    private ProyectoDto getProyectoByCod(Integer cod) {
        ProyectoDto proyectoResult = null;

        for (ProyectoDto proyecto : proyectos) {
            if (proyecto.getId().equals(cod)) {
                proyectoResult = proyecto;
            }
        }

        return proyectoResult;
    }

    private HallazgoDto ajustarFechashallazgo(HallazgoDto hallazgo) {
        hallazgo.setFechaHallazgo(Date.from(hallazgo.getFechaHallazgoLocal().toInstant(ZoneOffset.ofHours(-5))));

        return hallazgo;
    }

    @ModelAttribute("hallazgos")
    private Iterable<HallazgoDto> getAllHallazgos() {
        HallazgoService.setapiservicename(dominio_hallazgo_respuesta);
        Iterable<HallazgoDto> proyectos = HallazgoService.findAll(HallazgoDto.class);
        return proyectos;
    }
}