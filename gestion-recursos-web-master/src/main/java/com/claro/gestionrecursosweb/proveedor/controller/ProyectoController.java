package com.claro.gestionrecursosweb.proveedor.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.proyecto.domain.ProyectoService;
import com.claro.gestionrecursosweb.proyecto.dto.PresupuestoDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoTipoDto;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Proyecto")
public class ProyectoController extends BaseController {

    @Autowired
    private ApiService<ProyectoDto, Integer> service;
    @Autowired
    private ApiService<PresupuestoDto, Integer> servicePresupuesto;
    @Autowired
    private ApiService<ProyectoTipoDto, Integer> serviceProyectoTipo;

    @Autowired
    private ProyectoService proyectService;

    public void configurar(Model modelo, HttpServletRequest request) {
        service.setapiservicename(dominio_proyecto);
        super.setFormularioEstandar(modelo, request, dominio_proyecto);
    }

    @GetMapping("/Filtro")
    public String filtro(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);
        Iterable<ProyectoDto> dto = service.findAll(ProyectoDto.class);
        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Proyecto", "Filtro", "Proyecto");
        return dominio_proyecto + "/Filtro";
    }

    @GetMapping("/Crear")
    public String crear(Model modelo, HttpServletRequest request) {
        cargarListas(modelo);
        configurar(modelo, request);
        if (modelo.getAttribute("modelo") != null) {
            modelo.addAttribute("modelo", (modelo.getAttribute("modelo")));
        } else {
            modelo.addAttribute("modelo", new ProyectoDto());
        }

        mostrarTitulosYActiveNav(modelo, "Proyecto", "Crear", "Proyecto");
        return dominio_proyecto + "/Proyecto";
    }

    @PostMapping("/Crear")
    public String crear(ProyectoDto dto, BindingResult result, Model modelo, HttpServletRequest request,
            RedirectAttributes flash) {
        configurar(modelo, request);

        ProyectoDto dtoResultado = service.insert(dto, ProyectoDto.class);

        if (Objects.nonNull(service.codigoHttp) && HttpStatus.LOCKED.value() == ApiService.codigoHttp) {
            flash.addFlashAttribute("error", service.mensaje);
            flash.addFlashAttribute("modelo", dto);
            return "redirect:/Proyecto/Crear";
        }

        return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
    }

    @GetMapping("/Editar/{id}")
    public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla,
            HttpServletRequest request) {
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);

        Optional<ProyectoDto> dtoResultado = service.findById(id, ProyectoDto.class);
        if (dtoResultado == null)
            dtoResultado = Optional.of(new ProyectoDto());

        modelo.addAttribute("modelo", dtoResultado.get());
        cargarListas(modelo);
        mostrarTitulosYActiveNav(modelo, "Proyecto", "Editar", "Proyecto");
        return dominio_proyecto + "/Proyecto";
    }

    @PostMapping("/Editar/{id}")
    public String editar(ProyectoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
        configurar(modelo, request);

        ProyectoDto dtoResultado = service.update(dto.getId(), dto, ProyectoDto.class);
        modelo.addAttribute("modelo", dtoResultado);

        mostrarMensajes(modelo, "S", "U");
        cargarListas(modelo);
        return dominio_proyecto + "/Proyecto";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla,
            HttpServletRequest request) {
        configurar(modelo, request);
        try {
            service.deleteById(id);
            return redireccion("/Proyecto/Filtro", null, "S", "D", request);
        } catch (Exception e) {
            return redireccion("/Proyecto/Filtro", null, "E", "D", request);
        }
    }

    private void cargarListas(Model modelo) {
        try {
            servicePresupuesto.setapiservicename(dominio_presupuesto);
            Iterable<PresupuestoDto> presupuestos = servicePresupuesto.findAll(PresupuestoDto.class);
            serviceProyectoTipo.setapiservicename(dominio_proyectotipo);
            Iterable<ProyectoTipoDto> proyectoTipos = serviceProyectoTipo.findAll(ProyectoTipoDto.class);
            boolean datos_cargados = ((Collection<?>) presupuestos).size() > 0
                    && ((Collection<?>) proyectoTipos).size() > 0;
            modelo.addAttribute("datos_cargados", datos_cargados);
            modelo.addAttribute("presupuestos", presupuestos);
            modelo.addAttribute("proyectoTipos", proyectoTipos);
        } catch (NullPointerException ex) {
            modelo.addAttribute("datos_cargados", false);
        }
    }

}
