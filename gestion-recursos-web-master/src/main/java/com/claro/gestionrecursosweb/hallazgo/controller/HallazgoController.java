package com.claro.gestionrecursosweb.hallazgo.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.compromisosfabrica.dto.CompromisosFabricaDto;
import com.claro.gestionrecursosweb.compromisosfabrica.dto.FiltrosDto;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import com.claro.gestionrecursosweb.estructura.dto.EstructuraOrganizacionalDto;
import com.claro.gestionrecursosweb.hallazgo.dto.HallazgoDto;
import com.claro.gestionrecursosweb.hallazgo.dto.HallazgoProyectoDto;
import com.claro.gestionrecursosweb.hallazgo.dto.TipoHallazgoDto;
import com.claro.gestionrecursosweb.opcionfront.dto.OpcionFrontDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import com.claro.gestionrecursosweb.reportegenerico.dto.ColumnInformationDbDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/Hallazgo")
public class HallazgoController extends BaseController {
    private Iterable<ProyectoDto> proyectos;
    private Iterable<EmpleadoVUDto> empleados;
    private Iterable<TipoHallazgoDto> hallazgoTipos;
    private final String allPath = "/Hallazgos";
    private final String editPath = "/Hallazgo";
    private final String editPathResp = "/HallazgosResp";

    @Autowired
    private ApiService<CompromisosFabricaDto, Integer> service;

    @Autowired
    private ApiService<HallazgoDto, Integer> HallazgoService;

    @Autowired
    private ApiService<HallazgoProyectoDto, Integer> hallazgoProyectoService;

    @Autowired
    private ApiService<ProyectoDto, Integer> proyectoService;

    @Autowired
    private ApiService<EmpleadoVUDto, Integer> serviceEmpleadoVU;
    //lineaProducto
    @Autowired
    private ApiService<OpcionFrontDto, Integer> opcionFrontService;

    @Autowired
    private ApiService<ColumnInformationDbDto, String> columnInformationService;

    @Autowired
    private ApiService<EstructuraOrganizacionalDto, Integer> serviceEstructuraOrganizacional;

    @Autowired
    private ApiService<ProyectoDto, Integer> serviceProyecto;

    private void configurar(Model modelo, HttpServletRequest request) {
        HallazgoService.setapiservicename(dominio_hallazgo);

        super.setFormularioEstandar(modelo, request, dominio_hallazgo);
    }

    @GetMapping("/Filtro")
    public String filtro(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        cargarListas(modelo);

        modelo.addAttribute("filtrosDto", new FiltrosDto());
        mostrarTitulosYActiveNav(modelo, "Hallazgo", "Filtro", "Hallazgo");
        return dominio_hallazgo + allPath;
    }

    @PostMapping("/Filtro")
    public String filtrar(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request, FiltrosDto filtrosDto) {
        cargarListas(modelo);

        modelo.addAttribute("filtrosDto", filtrosDto);

        mostrarMensajes(modelo, cla);

        filtrosDto.setDiasFiltro(60);

        service.setapiservicename(dominio_compromisos_fabrica + "/filtrar");

        Iterable<CompromisosFabricaDto> dto = service.findAllWithPayloadNoUser(CompromisosFabricaDto.class, filtrosDto);

        modelo.addAttribute("modelo", dto);

        return dominio_hallazgo + allPath;
    }

    @GetMapping("/Crear")
    public String crearHallazgo(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request, @RequestParam(name = "id", required = false) Integer idCompromiso, @RequestParam(name = "idHallazgo", required = false) Integer idHallazgo) {
    	cargarListasEdicion(modelo);
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);

        HallazgoDto hallazgoDto = new HallazgoDto();

        if (Objects.nonNull(idCompromiso)) {
            service.setapiservicename(dominio_compromisos_fabrica);

            Optional<CompromisosFabricaDto> compromisoFabricaDto = service.findByIdNoUser(idCompromiso, CompromisosFabricaDto.class);

            modelo.addAttribute("compromisoFabrica", compromisoFabricaDto.get());

            hallazgoDto.setIdCompromiso(idCompromiso);
            hallazgoDto.setFechaHallazgo(new java.util.Date());
		}

        if (Objects.nonNull(idHallazgo)) {
        	Optional<HallazgoDto> hallazgoGuardado = HallazgoService.findByIdNoUser(idHallazgo, HallazgoDto.class);

        	if (hallazgoGuardado.isPresent()) {
                modelo.addAttribute("hallazgoGuardado", hallazgoGuardado.get());
        	} else {
        		modelo.addAttribute("hallazgoNoGuardado", true);
        	}
		}

        //HallazgoService.setapiservicename(dominio_hallazgo);

        modelo.addAttribute("hallazgo", hallazgoDto);
        mostrarTitulosYActiveNav(modelo, "Hallazgo", "Crear", "Hallazgo");
        return dominio_hallazgo + editPath;
    }

    @PostMapping("/Crear")
    public String postCrearHallazgo(Model modelo, HallazgoDto hallazgo, HttpServletRequest request) {

        cargarListas(modelo);

        HallazgoService.setapiservicename(dominio_hallazgo);

        HallazgoDto hallazgoGuardado = HallazgoService.insertNoUser(hallazgo, HallazgoDto.class);

        return redireccion("Crear", "S", "C" + "&idHallazgo=" + hallazgoGuardado.getId(), request);
    }

    private void cargarListas(Model modelo) {
        columnInformationService.setapiservicename(dominio_compromisos_fabrica + "/columnas-filtros");
        List<ColumnInformationDbDto> columnasFiltro =  (List<ColumnInformationDbDto>) columnInformationService.findAllNoUser(ColumnInformationDbDto.class);

        modelo.addAttribute("columnasFiltro", columnasFiltro);

        serviceProyecto.setapiservicename(dominio_hallazgo_proyectos);

        List<HallazgoProyectoDto> listaProyectos = (List<HallazgoProyectoDto>) hallazgoProyectoService.findAllNoUser(HallazgoProyectoDto.class);

        modelo.addAttribute("proyectos", listaProyectos);
        proyectoService.setapiservicename(dominio_proyecto);

        serviceEstructuraOrganizacional.setapiservicename(dominio_estructuraorganizacional);

        modelo.addAttribute("estructurasorganizacionales", serviceEstructuraOrganizacional.findAllNoUser(EstructuraOrganizacionalDto.class));
        proyectoService.setapiservicename(dominio_proyecto);

        serviceEmpleadoVU.setapiservicename(dominio_empleado + "/vu");
    }

    private void cargarListasEdicion(Model modelo) {
        opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
        modelo.addAttribute("hallazgoCriticidades",(List<OpcionFrontDto>) opcionFrontService.findAllByPathVariableNoUser(OpcionFrontDto.class, "HallazgoCriticidad"));

        opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
        modelo.addAttribute("hallazgoTipos",(List<OpcionFrontDto>) opcionFrontService.findAllByPathVariableNoUser(OpcionFrontDto.class, "HallazgoTipo"));
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
}