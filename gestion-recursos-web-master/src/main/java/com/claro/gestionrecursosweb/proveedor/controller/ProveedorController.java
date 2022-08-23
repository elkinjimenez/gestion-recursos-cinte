package com.claro.gestionrecursosweb.proveedor.controller;

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
import com.claro.gestionrecursosweb.proveedor.dto.ProveedorDto;

@Controller
@RequestMapping("/Proveedor")
public class ProveedorController extends BaseController {
		
	@Autowired
	private ApiService<ProveedorDto, Integer> service;
		
	public void configurar(Model modelo, HttpServletRequest request) {
		service.setapiservicename(dominio_proveedor);
		super.setFormularioEstandar(modelo, request, dominio_proveedor);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		Iterable<ProveedorDto> dto = service.findAll(ProveedorDto.class);
		
		modelo.addAttribute("modelo", dto);
		mostrarTitulosYActiveNav(modelo, "Proveedor", "Filtro", "Proveedor");
		return dominio_proveedor + "/Filtro";
	}
	
	@GetMapping("/Crear")
	public String crear(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		modelo.addAttribute("modelo", new ProveedorDto());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Proveedor", "Crear", "Proveedor");
		return dominio_proveedor + "/Proveedor";
	}
	
	@PostMapping("/Crear")
	public String crear(ProveedorDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		ProveedorDto dtoResultado = service.insert(dto, ProveedorDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);
		
		Optional<ProveedorDto> dtoResultado = service.findById(id, ProveedorDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new ProveedorDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Proveedor", "Crear", "Proveedor");
		return dominio_proveedor + "/Proveedor";
	}
	
	@PostMapping("/Editar/{id}")
	public String editar(ProveedorDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		ProveedorDto dtoResultado = service.update(dto.getId(), dto, ProveedorDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio_proveedor + "/Proveedor";
	}
	
	private void cargarListas(Model modelo) {
		
	}

}
