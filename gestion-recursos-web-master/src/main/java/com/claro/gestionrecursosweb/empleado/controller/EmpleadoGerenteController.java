package com.claro.gestionrecursosweb.empleado.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.empleado.domain.EmpleadoControlService;
import com.claro.gestionrecursosweb.empleado.domain.GerenteService;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoFormDto;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import com.claro.gestionrecursosweb.empleado.dto.GerenteDto;
import com.claro.gestionrecursosweb.estructura.dto.EstructuraOrganizacionalDto;
import com.claro.gestionrecursosweb.opcionfront.dto.OpcionFrontDto;
import com.claro.gestionrecursosweb.seguridad.domain.SeguridadService;
import com.claro.gestionrecursosweb.seguridad.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/Estructuraorganizacional")
public class EmpleadoGerenteController extends BaseController {

    @Autowired
    private ApiService<EstructuraOrganizacionalDto, Integer> service;

    @Autowired
    private ApiService<EmpleadoVUDto, Integer> serviceListaEmpleados;

    @Autowired
    private EmpleadoControlService empleadoService;

    @Autowired
    private GerenteService gerenteService;

    @Autowired
    private SeguridadService serviceSeguridad;

    @Autowired
    private ApiService<OpcionFrontDto, Integer> opcionFrontService;

    private String templateCoordinador = "/Coordinador";
    private String templateDesarrollador = "/Desarrollador";

    public void configurar(Model modelo, HttpServletRequest request) {
        service.setapiservicename(dominio_estructuraorganizacional);
        super.setFormularioEstandar(modelo, request, dominio_estructuraorganizacional);
    }

    @GetMapping("/Filtro")
    public String Filtro(Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        Iterable<GerenteDto> dto = gerenteService.findAllGerentes();
        dto.forEach(gerente -> {
            if (gerente.getCodEmpleado() == null) {
                gerente.setNombreGerente("SIN ASIGNAR");
            }
        });
        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Estructura Organizacional", "Filtro", "Estructuraorganizacional");
        return dominio_empleado_gerente + "/Filtro";
    }

    @GetMapping("/Coordinador/{id}")
    public String Coordinador(@PathVariable Integer id, Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        Iterable<GerenteDto> dto = gerenteService.findJerarquiaAndTipoEstructura(id + "", 2);
        dto.forEach(gerente -> {
            if (gerente.getCodEmpleado() == null) {
                gerente.setNombreGerente("SIN ASIGNAR");
            }
        });
        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Estructura Organizacional - Coordinador", "Filtro",
                "Estructuraorganizacional");
        return dominio_empleado_gerente + templateCoordinador;
    }

    @GetMapping("/DesarrolladorCelula/{id}")
    public String DesarrolladorCelula(@PathVariable Integer id, Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        String jerarquia = String.format("%010d", id);
        Iterable<GerenteDto> dto = gerenteService.findJerarquiaAndTipoEstructura(jerarquia, 3);
        dto.forEach(gerente -> {
            if (gerente.getCodEmpleado() == null) {
                gerente.setNombreGerente("SIN ASIGNAR");
            }
        });
        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Estructura Organizacional - Desarrollo Celula", "Filtro",
                "Estructuraorganizacional");
        return dominio_empleado_gerente + templateDesarrollador;
    }

    @GetMapping("/Crear/{tipoEstructura}/{padre}")
    public String crear(@PathVariable Integer tipoEstructura, @PathVariable Integer padre, Model modelo,
            HttpServletRequest request) {
        cargarListas(modelo);
        configurar(modelo, request);
        GerenteDto gerenteDto = new GerenteDto();
        EmpleadoFormDto jefedto = new EmpleadoFormDto();
        gerenteDto.setJefeActual(jefedto);
        gerenteDto.setCodTipoEstructura(tipoEstructura);
        gerenteDto.setCodPadre(padre == 0 ? null : padre);
        modelo.addAttribute("modelo", gerenteDto);
        mostrarTitulosYActiveNav(modelo, "Estructura Organizacional", "Crear", "Estructuraorganizacional");
        return dominio_empleado_gerente + "/Gerente";
    }

    @PostMapping("/Crear")
    public String crear(GerenteDto gerenteDto, Model modelo, HttpServletRequest request, RedirectAttributes flash) {
        cargarListas(modelo);
        configurar(modelo, request);
        EstructuraOrganizacionalDto estructuraOrganizacionalDto = new EstructuraOrganizacionalDto();

        estructuraOrganizacionalDto.setNombre(gerenteDto.getNombreGerencia());
        estructuraOrganizacionalDto.setCodempleado_responsable(gerenteDto.getCodEmpleado());
        estructuraOrganizacionalDto.setCodestructuratipo(gerenteDto.getCodTipoEstructura());
        estructuraOrganizacionalDto.setCodpadre(gerenteDto.getCodPadre());

        EstructuraOrganizacionalDto dtoResultado = service.insert(estructuraOrganizacionalDto,
                EstructuraOrganizacionalDto.class);
        modelo.addAttribute("modelo", dtoResultado);
        flash.addFlashAttribute("success", "Asignación realizada correctamente!!!");
        // return redireccion("Filtro", dtoResultado.getId().toString(), "S", "C",
        // request);
        return "redirect:" + "Filtro?clm=adpestr";
    }

    @GetMapping("/Editar/{id}")
    public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla,
            HttpServletRequest request) throws Exception {
        try {
            cargarListas(modelo);
            configurar(modelo, request);
            mostrarMensajes(modelo, cla);
            EstructuraOrganizacionalDto dtoResultado = service.findById(id, EstructuraOrganizacionalDto.class).get();
            EmpleadoFormDto jefedto = new EmpleadoFormDto();
            if (dtoResultado.getCodpadre() != null) {
                jefedto = empleadoService.getEmpleadoByCodPadre(dtoResultado.getCodpadre(),
                        EmpleadoFormDto.class);
            }

            if (dtoResultado.getCodempleado_responsable() == null)
                dtoResultado.setCodempleado_responsable(0);

            GerenteDto gerenteDto = new GerenteDto();
            gerenteDto.setId(dtoResultado.getId().longValue());
            gerenteDto.setNombreGerencia(dtoResultado.getNombre());
            gerenteDto.setCodEmpleado(dtoResultado.getCodempleado_responsable());
            gerenteDto.setCodPadre(dtoResultado.getCodpadre());
            gerenteDto.setCodTipoEstructura(dtoResultado.getCodestructuratipo());
            gerenteDto.setJefeActual(jefedto);
            List<EmpleadoFormDto> jefes = empleadoService.getEmpleadoByEstructura(gerenteDto.getCodTipoEstructura(),
                    EmpleadoFormDto.class);
            gerenteDto.setListajefes(jefes);
            modelo.addAttribute("modelo", gerenteDto);
            mostrarTitulosYActiveNav(modelo, "Estructura Organizacional", "Editar", "Estructuraorganizacional");
            return dominio_empleado_gerente + "/Gerente";

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return dominio_empleado_gerente + "/Gerente";
        }
    }

    @PostMapping("/Editar/{id}")
    public String editar(GerenteDto gerenteDto, @PathVariable String id, Model modelo, HttpServletRequest request,
            RedirectAttributes flash) {
        configurar(modelo, request);
        // gerenteDto.setCodPadre(gerenteDto.getJefeActual().getCodpadre());
        GerenteDto dtoResultado = gerenteService.actualizarGerente(gerenteDto, gerenteDto.getId());
        if (dtoResultado.getCodEmpleado() == null) {
            dtoResultado.setCodEmpleado(0);
        }
        modelo.addAttribute("modelo", dtoResultado);
        flash.addFlashAttribute("success", "Registro actualizado con éxito!!!");
        mostrarMensajes(modelo, "S", "U");
        return "redirect:" + "/Estructuraorganizacional/Filtro?clm=adpestr";
    }

    public List<EmpleadoVUDto> listaEmpleados() {
        serviceListaEmpleados.setapiservicename(dominio_empleado + "/vu");
        List<EmpleadoVUDto> lEmpleadosDto = new ArrayList<>();
        lEmpleadosDto = (List<EmpleadoVUDto>) serviceListaEmpleados.findAll(EmpleadoVUDto.class);
        UsuarioDto user = serviceSeguridad
                .findByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
        opcionFrontService.setapiservicename(dominio_opcionesfrontconsultarGrupoxUsuario);
        List<OpcionFrontDto> lstusuarios = (List<OpcionFrontDto>) opcionFrontService
                .findAllByPathVariable(OpcionFrontDto.class, user.getUsuario() == null ? "" : user.getUsuario());
        if (lstusuarios != null) {
            serviceListaEmpleados.setapiservicename(dominio_empleado + "/vu");
            lEmpleadosDto = (List<EmpleadoVUDto>) serviceListaEmpleados.findAllByPathVariable(EmpleadoVUDto.class,
                    "findAll");
        }
        return lEmpleadosDto;
    }

    @ResponseBody
    @GetMapping(value = "/getEstructuraXCodProyecto/{codProyecto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEstructuraXCodProyecto(@PathVariable int codProyecto) {
        Map<String, Object> response = new HashMap<>();
        try {
            gerenteService.setapiservicename(dominio_estructuraorganizacional);
            List<EstructuraOrganizacionalDto> lst = gerenteService.getEstructuraXCodProyecto(codProyecto);
            ResponseEntity<List<EstructuraOrganizacionalDto>> respuesta = new ResponseEntity<>(lst, HttpStatus.OK);
            return respuesta;
        } catch (Exception e) {
            response.put("mensaje", "Error al consultar información del reporte");
            response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @GetMapping(value = "/getEstructuraFiltroDonuts/{codProyecto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEstructuraFiltroDonuts(@PathVariable int codProyecto) {
        Map<String, Object> response = new HashMap<>();
        try {
            gerenteService.setapiservicename(dominio_estructuraorganizacional);
            List<EstructuraOrganizacionalDto> lst = gerenteService.getEstructuraFiltroDonuts(codProyecto);
            ResponseEntity<List<EstructuraOrganizacionalDto>> respuesta = new ResponseEntity<>(lst, HttpStatus.OK);
            return respuesta;
        } catch (Exception e) {
            response.put("mensaje", "Error al consultar información del reporte");
            response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @ResponseBody
    // @GetMapping(value = "/getEstructuraXCodCoordinacion/{codTipo}", produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<?> getEstructuraXCodCoordinacion(@PathVariable int codTipo) {
    //     Map<String, Object> response = new HashMap<>();
    //     try {
    //         gerenteService.setapiservicename(dominio_estructuraorganizacional);
    //         List<EstructuraOrganizacionalDto> lst = gerenteService.getEstructuraXTipo(codTipo);
    //         ResponseEntity<List<EstructuraOrganizacionalDto>> respuesta = new ResponseEntity<>(lst, HttpStatus.OK);
    //         return respuesta;
    //     } catch (Exception e) {
    //         response.put("mensaje", "Error al consultar información del reporte");
    //         response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
    //         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }


    private void cargarListas(Model modelo) {
        try {
            List<EmpleadoVUDto> lstempleados = this.listaEmpleados();
            if (((Collection<?>) lstempleados).size() <= 0 || lstempleados == null) {

                modelo.addAttribute("datos_cargados", false);
                modelo.addAttribute("empleados", null);
            } else {

                EmpleadoVUDto empleadosinasignar = new EmpleadoVUDto();
                empleadosinasignar.setNombre("SIN ASIGNAR");
                empleadosinasignar.setCodempleado(0);
                lstempleados.add(empleadosinasignar);
                modelo.addAttribute("datos_cargados", true);
                modelo.addAttribute("empleados", lstempleados);
            }
        } catch (NullPointerException ex) {
            modelo.addAttribute("datos_cargados", false);
            modelo.addAttribute("empleados", null);
        }
    }

}
