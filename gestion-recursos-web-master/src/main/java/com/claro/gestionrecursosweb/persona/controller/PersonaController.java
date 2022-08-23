package com.claro.gestionrecursosweb.persona.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.dto.TipoDocumentoDto;
import com.claro.gestionrecursosweb.persona.dto.PersonaDto;
import com.claro.gestionrecursosweb.tercero.dto.TerceroDto;

@Controller
@RequestMapping("/Persona")
public class PersonaController extends BaseController {

    @Autowired
    private ApiService<PersonaDto, Integer> service;
    @Autowired
    private ApiService<TipoDocumentoDto, Integer> serviceTipoDocumento;
    @Autowired
    private ApiService<TerceroDto, Integer> serviceTercero;

    public void configurar(Model modelo, HttpServletRequest request) {
        service.setapiservicename(dominio_persona);
        super.setFormularioEstandar(modelo, request, dominio_persona);
    }

    @GetMapping("/Filtro")
    public String Filtro(Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        Iterable<PersonaDto> dto = service.findAll(PersonaDto.class);

        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Datos Básicos", "Filtro", "Persona");
        return dominio_persona + "/Filtro";
    }

    @GetMapping("/Crear")
    public String Crear(Model modelo, HttpServletRequest request) {
        configurar(modelo, request);

        modelo.addAttribute("modelo", new PersonaDto());
        cargarListas(modelo);
        mostrarTitulosYActiveNav(modelo, "Datos Básicos", "Crear", "Persona");
        return dominio_persona + "/Persona";
    }

    @PostMapping("/Crear")
    public String Crear(@Valid PersonaDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        Optional<PersonaDto> validarPersona = service.findByUrl(dto.getCodtipodocumento() + "/" + dto.getNumerodocumento(), PersonaDto.class);
        if (validarPersona != null) {
            modelo.addAttribute("cl_validacion_mensaje_tipo", "alert-danger");
            modelo.addAttribute("cl_validation_mensaje", "La persona ya existe");
            modelo.addAttribute("modelo", new PersonaDto());
            cargarListas(modelo);
            return dominio_persona + "/Persona";
        }
        PersonaDto dtoResultado = service.insert(dto, PersonaDto.class);
        return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
    }

    @GetMapping("/Editar/{id}")
    public String Editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);

        Optional<PersonaDto> dtoResultado = service.findById(id, PersonaDto.class);

        if (dtoResultado == null) {
            dtoResultado = Optional.of(new PersonaDto());
        }

        modelo.addAttribute("modelo", dtoResultado.get());
        cargarListas(modelo);
        mostrarTitulosYActiveNav(modelo, "Datos Básicos", "Editar", "Persona");
        return dominio_persona + "/Persona";
    }

    @PostMapping("/Editar/{id}")
    public String Editar(PersonaDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
        configurar(modelo, request);

        PersonaDto dtoResultado = service.update(dto.getId(), dto, PersonaDto.class);

        mostrarMensajes(modelo, "S", "U");

        modelo.addAttribute("modelo", dtoResultado);
        cargarListas(modelo);
        return dominio_persona + "/Persona";
    }

    private void cargarListas(Model modelo) {
        serviceTipoDocumento.setapiservicename(dominio_tipodocumento);
        Iterable<TipoDocumentoDto> tiposDocumento = serviceTipoDocumento.findAll(TipoDocumentoDto.class);

        serviceTercero.setapiservicename(dominio_tercero);
        Iterable<TerceroDto> terceros = serviceTercero.findAll(TerceroDto.class);

        modelo.addAttribute("tipodocumentos", tiposDocumento);
        modelo.addAttribute("terceros", terceros);
    }

}
