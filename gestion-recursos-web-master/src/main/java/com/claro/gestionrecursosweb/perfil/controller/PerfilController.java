package com.claro.gestionrecursosweb.perfil.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import com.claro.gestionrecursosweb.perfil.dto.PerfilDto;

@Controller
@RequestMapping("/Perfil")
public class PerfilController extends BaseController {
	
	@Autowired
	private ApiService<PerfilDto, Integer> service;
		
	public void configurar(Model modelo, HttpServletRequest request) {
		service.setapiservicename(dominio_perfil);
		super.setFormularioEstandar(modelo, request, dominio_perfil);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		Iterable<PerfilDto> dto = service.findAll(PerfilDto.class);
		
		modelo.addAttribute("modelo", dto);
		mostrarTitulosYActiveNav(modelo, "Perfil", "Editar", "Perfil");
		return dominio_perfil + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		modelo.addAttribute("modelo", new PerfilDto());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Perfil", "Editar", "Perfil");
		return dominio_perfil + "/Perfil";
	}
	
	@PostMapping("/Crear")
	public String crear(PerfilDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		PerfilDto dtoResultado = service.insert(dto, PerfilDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);
		
		Optional<PerfilDto> dtoResultado = service.findById(id, PerfilDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new PerfilDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Perfil", "Editar", "Perfil");
		return dominio_perfil + "/Perfil";
	}
	
	@PostMapping("/Editar/{id}")
	public String editar(PerfilDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		PerfilDto dtoResultado = service.update(dto.getId(), dto, PerfilDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio_perfil + "/Perfil";
	}
	
	private void cargarListas(Model modelo) {
		
	}

}
