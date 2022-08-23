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
import com.claro.gestionrecursosweb.perfil.dto.PerfiltipoDto;

@Controller
@RequestMapping("/Perfiltipo")
public class PerfilTipoController extends BaseController {
	
	@Autowired
	private ApiService<PerfiltipoDto, Integer> service;
		
	public void configurar(Model modelo, HttpServletRequest request) {
		service.setapiservicename(dominio_perfiltipo);
		super.setFormularioEstandar(modelo, request, dominio_perfiltipo);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model, HttpServletRequest request) {
		configurar(model, request);
		
		Iterable<PerfiltipoDto> dto = service.findAll(PerfiltipoDto.class);
		
		model.addAttribute("modelo", dto);
		mostrarTitulosYActiveNav(model, "Perfil Tipo", "Filtro", "Perfiltipo");
		return dominio_perfiltipo + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		modelo.addAttribute("modelo", new PerfiltipoDto());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Perfil Tipo", "Crear", "Perfiltipo");
		return dominio_perfiltipo + "/PerfilTipo";
	}
	
	@PostMapping("/Crear")
	public String crear(PerfiltipoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		PerfiltipoDto dtoResultado = service.insert(dto, PerfiltipoDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);
		
		Optional<PerfiltipoDto> dtoResultado = service.findById(id, PerfiltipoDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new PerfiltipoDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Perfil Tipo", "Editar", "Perfiltipo");
		return dominio_perfiltipo + "/PerfilTipo";
	}
	
	@PostMapping("/Editar/{id}")
	public String editar(PerfiltipoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		PerfiltipoDto dtoResultado = service.update(dto.getId(), dto, PerfiltipoDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio_perfiltipo + "/PerfilTipo";
	}
	
	private void cargarListas(Model modelo) {
		
	}

}
