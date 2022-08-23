package com.claro.gestionrecursosweb.estimaciones.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.compromisosfabrica.dto.CompromisosFabricaDto;
import com.claro.gestionrecursosweb.compromisosfabrica.dto.FiltrosDto;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import com.claro.gestionrecursosweb.estimaciones.domain.IEstimacionService;
import com.claro.gestionrecursosweb.estimaciones.dto.EstimacionDto;
import com.claro.gestionrecursosweb.estructura.dto.EstructuraOrganizacionalDto;
import com.claro.gestionrecursosweb.hallazgo.dto.HallazgoDto;
import com.claro.gestionrecursosweb.hallazgo.dto.HallazgoProyectoDto;
import com.claro.gestionrecursosweb.opcionfront.dto.OpcionFrontDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import com.claro.gestionrecursosweb.reportegenerico.dto.ColumnInformationDbDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Estimacion")
public class EstimacionGuardarController extends BaseController {
    private final String allPath = "/EstimacionesGuardar";
    private final String editPath = "/EstimacionGuardar";

    @Autowired
    private IEstimacionService iEstimacionService;


    private void configurar(Model modelo, HttpServletRequest request) {
        //HallazgoService.setapiservicename(dominio_hallazgo);

        super.setFormularioEstandar(modelo, request, dominio_hallazgo);
    }

    @GetMapping("/Filtro")
    public String filtro(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
       /* cargarListas(modelo);
        modelo.addAttribute("filtrosDto", new FiltrosDto());*/

        List<EstimacionDto> lista = iEstimacionService.traerTodos();
        modelo.addAttribute("estimaciones", lista);

        return dominio_estimaciones + allPath;
    }

    @PostMapping("/Filtro")
    public String filtrar(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request, FiltrosDto filtrosDto) {
       /* cargarListas(modelo);

        modelo.addAttribute("filtrosDto", filtrosDto);   */

        mostrarMensajes(modelo, cla);

        /*filtrosDto.setDiasFiltro(60);

        service.setapiservicename(dominio_compromisos_fabrica + "/filtrar");

        Iterable<CompromisosFabricaDto> dto = service.findAllWithPayloadNoUser(CompromisosFabricaDto.class, filtrosDto);

        modelo.addAttribute("modelo", dto);*/

        List<EstimacionDto> lista = iEstimacionService.traerTodos();
        modelo.addAttribute("estimaciones", lista);

        return dominio_estimaciones + allPath;
    }

    @GetMapping("/Crear")
    public String crearEstimacion(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request, @RequestParam(name = "id", required = false) Integer idCompromiso, @RequestParam(name = "idHallazgo", required = false) Integer idHallazgo) {
        //cargarListasEdicion(modelo);
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);

        modelo.addAttribute("estimaciones", new EstimacionDto());

        //HallazgoDto hallazgoDto = new HallazgoDto();

      /*  if (Objects.nonNull(idCompromiso)) {
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
        }*/

        //HallazgoService.setapiservicename(dominio_hallazgo);

        //modelo.addAttribute("hallazgo", hallazgoDto);

        return dominio_estimaciones + editPath;
    }
//
//    @PostMapping("/Crear")
//    public String postCrearEstimacion(Model modelo, NovedadDto novedad,  HttpServletRequest request) {
//        novedad = ajustarFechasNovedad(novedad);
//        cargarListas(modelo);
//
//        novedadService.setapiservicename(dominio_novedad);
//
//        NovedadDto novedadCreada = novedadService.insert(novedad, NovedadDto.class);
//
//        modelo.addAttribute("novedad", novedadCreada);
//
//        return redireccion("Editar", novedadCreada.getId().toString(), "S", "C", request);
//    }

    @PostMapping("/Crear")
    public String postCrearEstimacion(Model modelo, EstimacionDto estimacion, HttpServletRequest request, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()){

            //con este codigo mostramos el error proporcionado en nuesra consola
            for (ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrio un error = " + error.getDefaultMessage());
            }
            return dominio_estimaciones + editPath;
        }

        iEstimacionService.guardar(estimacion);
        //agregamos el mesnaje de registro exitoso al flash attributes
        attributes.addFlashAttribute("msg", "Registro Exitoso");

        //cargarListas(modelo);

        //HallazgoService.setapiservicename(dominio_hallazgo);

        //HallazgoDto hallazgoGuardado = HallazgoService.insertNoUser(hallazgo, HallazgoDto.class);

        //return redireccion("Crear", "S", "C" + "&idHallazgo=" + hallazgoGuardado.getId(), request);
        //return dominio_estimaciones + allPath;
        return "redirect:/Estimaciones/Filtro";
    }

    private void cargarListas(Model modelo) {
        /*columnInformationService.setapiservicename(dominio_compromisos_fabrica + "/columnas-filtros");
        List<ColumnInformationDbDto> columnasFiltro =  (List<ColumnInformationDbDto>) columnInformationService.findAllNoUser(ColumnInformationDbDto.class);

        modelo.addAttribute("columnasFiltro", columnasFiltro);

        serviceProyecto.setapiservicename(dominio_hallazgo_proyectos);

        List<HallazgoProyectoDto> listaProyectos = (List<HallazgoProyectoDto>) hallazgoProyectoService.findAllNoUser(HallazgoProyectoDto.class);

        modelo.addAttribute("proyectos", listaProyectos);
        proyectoService.setapiservicename(dominio_proyecto);

        serviceEstructuraOrganizacional.setapiservicename(dominio_estructuraorganizacional);

        modelo.addAttribute("estructurasorganizacionales", serviceEstructuraOrganizacional.findAllNoUser(EstructuraOrganizacionalDto.class));
        proyectoService.setapiservicename(dominio_proyecto);

        serviceEmpleadoVU.setapiservicename(dominio_empleado + "/vu");*/
    }

    /*private void cargarListasEdicion(Model modelo) {
        opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
        modelo.addAttribute("entregable",(List<OpcionFrontDto>) opcionFrontService.findAllByPathVariableNoUser(OpcionFrontDto.class, "EntregableEstimacion"));

        //opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
        //modelo.addAttribute("hallazgoTipos",(List<OpcionFrontDto>) opcionFrontService.findAllByPathVariableNoUser(OpcionFrontDto.class, "HallazgoTipo"));
    }*/
/*
    private TipoHallazgoDto gethallazgoTipoByCod(Integer cod) {
        TipoHallazgoDto hallazgoTipo = null;

       /* for (TipoHallazgoDto tipoHallazgoDto : hallazgoTipos) {
            if (tipoHallazgoDto.getId().equals(cod)) {
                hallazgoTipo = tipoHallazgoDto;
            }
        }*/
/*
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
*/

}