package com.claro.gestionrecursosweb.perfil.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.perfil.dto.*;
import com.claro.gestionrecursosweb.perfil.service.PerfilCostoService;
import com.claro.gestionrecursosweb.proveedor.dto.ProveedorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Perfilcosto")
public class PerfilCostoController extends BaseController {

    @Autowired
    private ApiService<PerfilCostoDto, Integer> service;

    @Autowired
    private ApiService<PerfilDto, Integer> perfilService;

    @Autowired
    private ApiService<PerfiltipoDto, Integer> perfilTipoService;

    @Autowired
    private ApiService<ProveedorDto, Integer> proveedorService;

    @Autowired
    private ApiService<PerfilnivelDto, Integer> perfilNivelService;

    @Autowired
    private PerfilCostoService perfilCostoService;

    public void configurar(Model modelo, HttpServletRequest request) {
        service.setapiservicename(dominio_perfil_costo);
        super.setFormularioEstandar(modelo, request, dominio_perfil_costo);
    }

    @GetMapping("/Filtro")
    public String filtro(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);

        Iterable<PerfilCostoDto> dto = perfilCostoService.buscarTodo();

        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Perfil Costo", "Filtro", "Perfilcosto");
        return dominio_perfil_costo + "/Filtro";
    }

    @GetMapping("/Crear")
    public String crear(Model modelo, HttpServletRequest request) {
        configurar(modelo, request);

        modelo.addAttribute("modelo", new PerfilCostoDto());
        cargarListas(modelo);
        mostrarTitulosYActiveNav(modelo, "Perfil Costo", "Crear", "Perfilcosto");
        return dominio_perfil_costo + "/Crear";
    }

    @PostMapping("/Crear")
    public String crear(PerfilCostoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
        configurar(modelo, request);

        PerfilCostoDto dtoResultado = service.insert(dto, PerfilCostoDto.class);

        return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
    }

    @GetMapping("/Editar/{id}")
    public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);

        Optional<PerfilCostoDto> dtoResultado = service.findById(id, PerfilCostoDto.class);
        if (dtoResultado == null)
            dtoResultado = Optional.of(new PerfilCostoDto());

        modelo.addAttribute("modelo", dtoResultado.get());
        cargarListas(modelo);
        mostrarTitulosYActiveNav(modelo, "Perfil Costo", "Editar", "Perfilcosto");
        return dominio_perfil_costo + "/Crear";
    }

    @PostMapping("/Editar/{id}")
    public String editar(PerfilCostoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
        configurar(modelo, request);

        PerfilCostoDto dtoResultado = service.update(dto.getId(), dto, PerfilCostoDto.class);
        modelo.addAttribute("modelo", dtoResultado);

        mostrarMensajes(modelo, "S", "U");
        cargarListas(modelo);
        return dominio_perfil_costo + "/Crear";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        configurar(modelo, request);
        try {
            service.deleteById(id);
            return redireccion("/PerfilCosto/Filtro", null, "S", "D", request);
        } catch (Exception e) {
            return redireccion("/PerfilCosto/Filtro", null, "E", "D", request);
        }
    }

    private void cargarListas(Model modelo) {
        /*service.setapiservicename(dominio_presupuesto);
        Iterable<PerfilCostoDto> presupuestos = service.findAll(PerfilCostoDto.class);
        service.setapiservicename(dominio_proyectotipo);
        Iterable<PerfilCostoDto> proyectoTipos = service.findAll(PerfilCostoDto.class);

        modelo.addAttribute("presupuestos", presupuestos);
        modelo.addAttribute("proyectoTipos", proyectoTipos);*/
    }

    @ModelAttribute("perfiles")
    private List<PerfilDto> getAllPerfil() {
        perfilService.setapiservicename(dominio_perfil);
        return (List<PerfilDto>) perfilService.findAll(PerfilDto.class);
    }

    @ModelAttribute("perfilTipos")
    private List<PerfiltipoDto> getAllPerfilTipo() {
        perfilTipoService.setapiservicename(dominio_perfiltipo);
        List<PerfiltipoDto> lPerfilTipo =  (List<PerfiltipoDto>) perfilTipoService.findAll(PerfiltipoDto.class);
        return lPerfilTipo;
    }

    @ModelAttribute("proveedores")
    private List<ProveedorDto> getAllproveedores() {
        proveedorService.setapiservicename(dominio_proveedor);
        return (List<ProveedorDto>) proveedorService.findAll(ProveedorDto.class);
    }

    @ModelAttribute("nivelPerfiles")
    private List<PerfilnivelDto> getAllNivelPerfiles() {
        perfilNivelService.setapiservicename(dominio_perfilnivel);
        return (List<PerfilnivelDto>) perfilNivelService.findAll(PerfilnivelDto.class);
    }


}
