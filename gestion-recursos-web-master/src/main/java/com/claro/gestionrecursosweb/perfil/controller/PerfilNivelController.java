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
import com.claro.gestionrecursosweb.perfil.dto.PerfilnivelDto;

@Controller
@RequestMapping("/Perfilnivel")
public class PerfilNivelController extends BaseController {
	
	@Autowired
	private ApiService<PerfilnivelDto, Integer> service;
		
	public void configurar(Model modelo, HttpServletRequest request) {
		service.setapiservicename(dominio_perfilnivel);
		super.setFormularioEstandar(modelo, request, dominio_perfilnivel);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		Iterable<PerfilnivelDto> dto = service.findAll(PerfilnivelDto.class);
		
		modelo.addAttribute("modelo", dto);
		mostrarTitulosYActiveNav(modelo, "Perfil Nivel", "Filtro", "Perfilnivel");
		return dominio_perfilnivel + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		modelo.addAttribute("modelo", new PerfilnivelDto());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Perfil Nivel", "Crear", "Perfilnivel");
		return dominio_perfilnivel + "/PerfilNivel";
	}
	
	@PostMapping("/Crear")
	public String crear(PerfilnivelDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		PerfilnivelDto dtoResultado = service.insert(dto, PerfilnivelDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);
		
		Optional<PerfilnivelDto> dtoResultado = service.findById(id, PerfilnivelDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new PerfilnivelDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Perfil Nivel", "Editar", "Perfilnivel");
		return dominio_perfilnivel + "/PerfilNivel";
	}
	
	@PostMapping("/Editar/{id}")
	public String editar(PerfilnivelDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		PerfilnivelDto dtoResultado = service.update(dto.getId(), dto, PerfilnivelDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio_perfilnivel + "/PerfilNivel";
	}
	
	private void cargarListas(Model modelo) {
		
	}

}
