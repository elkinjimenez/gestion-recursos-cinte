package com.claro.gestionrecursosweb.novedad.controller;

import java.sql.Date;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.claro.gestionrecursosweb.novedad.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;

@Controller
@RequestMapping("/Novedad")
public class NovedadController extends BaseController {

    private Iterable<ProyectoDto> proyectos;
    private Iterable<EmpleadoVUDto> empleados;
    private Iterable<TipoNovedadDto> novedadTipos;
    private Iterable<TipoRoadMapDto> tipoRoadMap;
    private Iterable<EstadoEntregaDto> estadoEntrega;
    private Iterable<EstadoRoadMapDto> estadoRoadMap;
    private final String allPath = "/Novedades";
    private final String editPath = "/Novedad";

    @Autowired
    private ApiService<NovedadDto, Integer> novedadService;

    @Autowired
    private ApiService<RoadMapDto, Integer> roadMapService;

    @Autowired
    private ApiService<TipoRoadMapDto, Integer> tipoRoadMapService;

    @Autowired
    private ApiService<EstadoEntregaDto, Integer> estadoEntregaService;

    @Autowired
    private ApiService<EstadoRoadMapDto, Integer> estadoRoadMapService;

    @Autowired
    private ApiService<ProyectoDto, Integer> proyectoService;

    @Autowired
    private ApiService<EmpleadoVUDto, Integer> serviceEmpleadoVU;

    @Autowired
    private ApiService<TipoNovedadDto, Integer> serviceTipoNovedad;

    private void configurar(Model modelo, HttpServletRequest request) {
        novedadService.setapiservicename(dominio_novedad);
        super.setFormularioEstandar(modelo, request, dominio_novedad);
    }

    @GetMapping("/Filtro")
    public String allNovedades(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        cargarListas(modelo);
        if (this.empleados != null) {
            configurar(modelo, request);
            mostrarMensajes(modelo, cla);
            novedadService.setapiservicename(dominio_novedad);
            Iterable<NovedadDto> novedades = novedadService.findAll(NovedadDto.class);
            modelo.addAttribute("novedades", intercambiarDatosNovedades(novedades));
            mostrarTitulosYActiveNav(modelo, "Novedades", "Filtro", "Novedad");
        } else {
            modelo.addAttribute("datos_cargados", false);
        }
        return dominio_novedad + allPath;
    }

    @GetMapping("/Crear")
    public String crearNovedad(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        cargarListas(modelo);
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);
        novedadService.setapiservicename(dominio_novedad);
        modelo.addAttribute("novedad", new NovedadDto());
        mostrarTitulosYActiveNav(modelo, "Novedades", "Crear", "Novedad");
        return dominio_novedad + editPath;
    }

    @PostMapping("/Crear")
    public String postCrearNovedad(Model modelo, NovedadDto novedad, RoadMapDto roadMap, HttpServletRequest request) {
        novedad = ajustarFechasNovedad(novedad);
        cargarListas(modelo);

        novedadService.setapiservicename(dominio_novedad);
        NovedadDto novedadCreada = novedadService.insert(novedad, NovedadDto.class);
        modelo.addAttribute("novedad", novedadCreada);
        return redireccion("Editar", novedadCreada.getId().toString(), "S", "C", request);
    }

    @GetMapping("/Editar/{id}")
    public String editarNovedad(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request,
            @PathVariable Integer id) {
        cargarListas(modelo);
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);
        novedadService.setapiservicename(dominio_novedad);
        NovedadDto novedad = novedadService.findById(id, NovedadDto.class).get();
        modelo.addAttribute("novedad", novedad);
        mostrarTitulosYActiveNav(modelo, "Novedades", "Editar", "Novedad");
        return dominio_novedad + editPath;
    }

    @PostMapping("/Editar/{id}")
    public String postEditarNovedad(Model modelo, NovedadDto novedad, HttpServletRequest request) {
        novedad = ajustarFechasNovedad(novedad);
        cargarListas(modelo);
        configurar(modelo, request);

        novedadService.setapiservicename(dominio_novedad);
        NovedadDto novedadActualizada = novedadService.update(novedad.getId(), novedad, NovedadDto.class);

        mostrarMensajes(modelo, "S", "U");

        modelo.addAttribute("novedad", novedadActualizada);

        return dominio_novedad + editPath;
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla,
            HttpServletRequest request) {
        configurar(modelo, request);

        try {
            novedadService.deleteById(id);

            return redireccion("/Novedad/Filtro", null, "S", "D", request);
        } catch (Exception e) {
            return redireccion("/Novedad/Filtro", null, "E", "D", request);
        }
    }

    private void cargarListas(Model modelo) {
        try {
            proyectoService.setapiservicename(dominio_proyecto);
            proyectos = proyectoService.findAll(ProyectoDto.class);
            modelo.addAttribute("proyectos", proyectos);

            serviceEmpleadoVU.setapiservicename(dominio_empleado + "/vu");
            empleados = serviceEmpleadoVU.findAll(EmpleadoVUDto.class);
            modelo.addAttribute("empleados", empleados);

            serviceTipoNovedad.setapiservicename(dominio_novedad_tipos);
            novedadTipos = serviceTipoNovedad.findAll(TipoNovedadDto.class);
            modelo.addAttribute("novedadTipos", novedadTipos);

            tipoRoadMapService.setapiservicename(dominio_tipoRoadMap);
            tipoRoadMap = tipoRoadMapService.findAll(TipoRoadMapDto.class);
            modelo.addAttribute("tiposRoadMap", tipoRoadMap);

            estadoEntregaService.setapiservicename(dominio_estadoEntrega);
            estadoEntrega = estadoEntregaService.findAll(EstadoEntregaDto.class);
            modelo.addAttribute("estadosEntrega", estadoEntrega);

            estadoRoadMapService.setapiservicename(dominio_estadoRoadMap);
            estadoRoadMap = estadoRoadMapService.findAll(EstadoRoadMapDto.class);
            modelo.addAttribute("estadosRoadMap", estadoRoadMap);

            boolean datos_cargados = ((Collection<?>) proyectos).size() > 0 && ((Collection<?>) empleados).size() > 0
                    && ((Collection<?>) novedadTipos).size() > 0 && ((Collection<?>) tipoRoadMap).size() > 0
                    && ((Collection<?>) estadoEntrega).size() > 0 && ((Collection<?>) estadoRoadMap).size() > 0;
            modelo.addAttribute("datos_cargados", datos_cargados);

        } catch (NullPointerException ex) {
            modelo.addAttribute("datos_cargados", false);
        }
    }

    private TipoNovedadDto getNovedadTipoByCod(Integer cod) {
        TipoNovedadDto novedadTipo = null;
        for (TipoNovedadDto tipoNovedadDto : novedadTipos) {
            if (tipoNovedadDto.getId().equals(cod)) {
                novedadTipo = tipoNovedadDto;
            }
        }

        return novedadTipo;
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

    private Iterable<NovedadDto> intercambiarDatosNovedades(Iterable<NovedadDto> novedades) {
        for (NovedadDto novedad : novedades) {
            novedad.setTipoNovedad(getNovedadTipoByCod(novedad.getCodTipoNovedad()).getNombre());
            novedad.setEmpleado(getEmpleadoByCod(novedad.getCodEmpleado()).getNombre());
            novedad.setProyecto(getProyectoByCod(novedad.getCodProyecto()).getNombre());
        }

        return novedades;
    }

    private NovedadDto ajustarFechasNovedad(NovedadDto novedad) {
        novedad.setFechaInicio(Date.from(novedad.getFechaInicioLocal().toInstant(ZoneOffset.ofHours(-5))));
        novedad.setFechaFin(Date.from(novedad.getFechaFinLocal().toInstant(ZoneOffset.ofHours(-5))));
        return novedad;
    }
}
