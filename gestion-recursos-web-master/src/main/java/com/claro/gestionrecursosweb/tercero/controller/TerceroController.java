package com.claro.gestionrecursosweb.tercero.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import com.claro.gestionrecursosweb.tercero.dto.TerceroDto;
import com.claro.gestionrecursosweb.tercero.dto.TerceroTipoDto;

@Controller
@RequestMapping("/Tercero")
public class TerceroController extends BaseController {
		
	@Autowired
	private ApiService<TerceroDto, Integer> service;
		
	public void configurar(Model modelo, HttpServletRequest request) {
		service.setapiservicename(dominio_tercero);
		super.setFormularioEstandar(modelo, request, dominio_tercero);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		Iterable<TerceroDto> dto = service.findAll(TerceroDto.class);
		
		modelo.addAttribute("modelo", dto);
		mostrarTitulosYActiveNav(modelo, "Terceros", "Filtro", "Tercero");
		return dominio_tercero + "/Filtro";
	}
	
	@GetMapping("/Crear")
	public String crear(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		modelo.addAttribute("modelo", new TerceroDto());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Terceros", "Crear", "Tercero");
		return dominio_tercero + "/Tercero";
	}
	
	@PostMapping("/Crear")
	public String crear(TerceroDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		TerceroDto dtoResultado = service.insert(dto, TerceroDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);
		
		Optional<TerceroDto> dtoResultado = service.findById(id, TerceroDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new TerceroDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Terceros", "Editar", "Tercero");
		return dominio_tercero + "/Tercero";
	}
	
	@PostMapping("/Editar/{id}")
	public String editar(TerceroDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		TerceroDto dtoResultado = service.update(dto.getId(), dto, TerceroDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio_tercero + "/Tercero";
	}
	
	private void cargarListas(Model modelo) {
		List<TerceroTipoDto> terceroTipos = new ArrayList<TerceroTipoDto>();
		TerceroTipoDto terceroTipo = new TerceroTipoDto();
		terceroTipo.setId(1);
		terceroTipo.setNombre("EPS");		
		terceroTipos.add(terceroTipo);
		boolean datos_cargados = ((Collection<?>) terceroTipos).size() > 0;
		modelo.addAttribute("terceroTipos", terceroTipos);
		modelo.addAttribute("datos_cargados", datos_cargados);
	}

}
