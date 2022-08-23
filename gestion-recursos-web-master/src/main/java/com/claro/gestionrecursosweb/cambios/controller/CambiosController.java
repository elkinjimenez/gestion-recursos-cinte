package com.claro.gestionrecursosweb.cambios.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;

import java.util.*;

import com.claro.gestionrecursosweb.cambios.domain.EstadoCambiosService;
import com.claro.gestionrecursosweb.cambios.domain.TipoLineaService;
import com.claro.gestionrecursosweb.cambios.dto.EstadoCambiosDto;
import com.claro.gestionrecursosweb.cambios.dto.TiposLineaDto;

import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.claro.gestionrecursosweb.cambios.dto.CambiosDto;
import com.claro.gestionrecursosweb.proyecto.domain.ProyectoService;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Cambios")
public class CambiosController extends BaseController {

    @Autowired
    private ApiService<CambiosDto, Integer> service;

    @Autowired
    private EstadoCambiosService estadoService;

    @Autowired
    private ApiService<Object[], Integer> servicecambiosall;

    @Autowired
    private ProyectoService proyectoservice;

    @Autowired
    private TipoLineaService tipolineaservice;

    @Autowired
    private ApiService<EmpleadoVUDto, Integer> serviceEmpleadoVU;


    public void configurar(Model modelo, HttpServletRequest request) {
        service.setapiservicename(dominio_cambios);
        super.setFormularioEstandar(modelo, request, dominio_cambios);
    }

    @GetMapping("/Filtro")
    public String filtro(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {

        configurar(modelo, request);
        mostrarMensajes(modelo, cla);
        servicecambiosall.setapiservicename(dominio_cambios);
        Iterable<CambiosDto> dto = service.findAll(CambiosDto.class);
        dto = buscarNombresTipoyEstados(dto);
        modelo.addAttribute("modelo", dto);
        return dominio_cambios + "/Filtro";

    }

    @GetMapping("/Crear")
    public String crear(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        configurar(modelo, request);
        mostrarTitulosYActiveNav(modelo, "Cambios", "Crear", "cambios");
        modelo.addAttribute("modelo", new CambiosDto());
        return dominio_cambios + "/Cambio";

    }

    @GetMapping("/Editar/{id}")
    public String Editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla,
                         HttpServletRequest request) {
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);
        mostrarTitulosYActiveNav(modelo, "Cambios", "Editar", "Cambio");
        CambiosDto cambio = service.findById(id, CambiosDto.class).get();
        modelo.addAttribute("modelo", cambio);
        return dominio_cambios + "/Cambio";
    }

    @PostMapping("/Editar/{id}")
    public String Editar(@PathVariable Integer id, CambiosDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        service.setapiservicename(dominio_cambios);
        CambiosDto dtoResultado = service.update(id, dto, CambiosDto.class);
        if (dtoResultado != null) {
            modelo.addAttribute("modelo", dtoResultado);
        }
        mostrarMensajes(modelo, "S", "U");
        return dominio_cambios + "/Cambio";
    }

    @PostMapping("/Crear")
    public String crear(CambiosDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        service.setapiservicename(dominio_cambios);
        CambiosDto dtoResultado = service.insert(dto, CambiosDto.class);
        mostrarMensajes(modelo, "S", "C");
        return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
    }


    @ModelAttribute(name = "Proyectos")
    public List<ProyectoDto> cargarProjectos() {
        proyectoservice.setapiservicename(dominio_proyecto);
        List<ProyectoDto> allprojects = new ArrayList<>();
        proyectoservice.getProyectosWithCompromiso().forEach(allprojects::add);

        if (allprojects.isEmpty()) {
            ProyectoDto p = new ProyectoDto();
            p.setId(0);
            p.setNombre("No hay proyectos con compromisos");
            allprojects.add(p);
        }
        return allprojects;
    }

    @ModelAttribute(name = "tiposLinea")
    public List<TiposLineaDto> cargarTiposLinea() {
        tipolineaservice.setapiservicename(dominio_cambios);
        List<TiposLineaDto> alltiposLinea = new ArrayList<>();
        tipolineaservice.getTiposLineaCambios().forEach(alltiposLinea::add);
        if (alltiposLinea.isEmpty()) {
            TiposLineaDto tipo = new TiposLineaDto();
            tipo.setId(0);
            tipo.setNomTipoLinea("No hay Tipos Linea ");
            alltiposLinea.add(tipo);
        }
        return alltiposLinea;
    }

    @ModelAttribute(name = "Estados")
    public List<EstadoCambiosDto> cargarEstados() {
        estadoService.setapiservicename(dominio_cambios);
        List<EstadoCambiosDto> estados = new ArrayList<>();
        estadoService.getTiposLineaCambios().forEach(estados::add);
        if (estados.isEmpty()) {
            EstadoCambiosDto estado = new EstadoCambiosDto();
            estado.setId(0);
            estado.setNomEstado("No hay Estados");
            estados.add(estado);
        }
        return estados;
    }

    @ModelAttribute("Empleados")
    public Iterable<EmpleadoVUDto> Empleados() {
        serviceEmpleadoVU.setapiservicename(dominio_empleado + "/vu");
        Iterable<EmpleadoVUDto> empleados = serviceEmpleadoVU.findAll(EmpleadoVUDto.class);
        EmpleadoVUDto e = new EmpleadoVUDto();
        if (empleados == null)
            e.setId(0);
        return empleados;
    }

    public Iterable<CambiosDto> buscarNombresTipoyEstados(Iterable<CambiosDto> cambios) {
        cambios.forEach(item -> {
            String tipolineanombre = tipolineaservice.buscarporId(Integer.parseInt(item.getTipoLinea())).getNomTipoLinea();
            String estado = estadoService.buscarporId(Integer.parseInt(item.getEstado())).getNomEstado();
            item.setEstado(estado);
            item.setTipoLinea(tipolineanombre);
        });
        return cambios;
    }

//    private CambiosDto ParsearObjects(Object[] objeto) throws ParseException {
//        CambiosDto cambio = new CambiosDto();
//        Date fechaformat = new Date();
//        for (int p = 0; p < objeto.length; p++) {
//            if (p == 2 || p == 3) {
//                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//                fechaformat = formato.parse(objeto[p].toString().substring(0, 10));
//            }
//            switch (p) {
//                case 0:
//                    cambio.setId(Integer.parseInt(objeto[p].toString()));
//                    break;
//                case 1:
//                    cambio.setCodCambio(objeto[p].toString());
//                    break;
//                case 2:
//                    cambio.setFechaTcba(fechaformat);
//                    break;
//                case 3:
//                    cambio.setFechaPap(fechaformat);
//                    break;
//                case 4:
//                    cambio.setGestorCodEmpleado(Integer.parseInt(objeto[p].toString()));
//                    break;
//                case 5:
//                    cambio.setResumen(objeto[p].toString());
//                    break;
//                case 6:
//                    cambio.setIdRlp(objeto[p].toString());
//                    break;
//                case 7:
//                    cambio.setTipoLinea(objeto[p].toString());
//                    break;
//                case 8:
//                    cambio.setEstado(objeto[p].toString());
//                    break;
//                case 9:
//                    cambio.setIdCompromiso(Integer.parseInt(objeto[p].toString()));
//                    break;
//                default:
//                    cambio = null;
//            }
//        }
//        return cambio;
//    }

}
