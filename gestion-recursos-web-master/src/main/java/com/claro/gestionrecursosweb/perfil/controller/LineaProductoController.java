
package com.claro.gestionrecursosweb.perfil.controller;

import java.util.Date;
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
//import com.claro.gestionrecursosweb.dto.PruebaDTO;
import com.claro.gestionrecursosweb.perfil.dto.LineaProductoDto;

@Controller
@RequestMapping("/Lineaproducto")
public class LineaProductoController extends BaseController {
		
	
	@Autowired
	private ApiService<LineaProductoDto, Integer> service;

	public void configurar(Model modelo, HttpServletRequest request) {
		service.setapiservicename(dominio_lineasproducto);
		super.setFormularioEstandar(modelo, request, dominio_lineasproducto);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);

		Iterable<LineaProductoDto> dto = service.findAll(LineaProductoDto.class);
		modelo.addAttribute("modelo", dto);		
		System.out.println(modelo.toString());

		mostrarTitulosYActiveNav(modelo, "Linea Producto", "Filtro", "LineaProducto");
		return  dominio_lineasproducto + "/LineaProducto";

	}
	
	@GetMapping("Crear")
	public String crear(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		modelo.addAttribute("modelo", new LineaProductoDto());
		mostrarTitulosYActiveNav(modelo, "Linea Producto", "Crear", "LineaProducto");
		return dominio_lineasproducto + "/CrearLineaProducto";
	}
	
	@PostMapping("/Crear")
	public String crear(LineaProductoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		dto.setFechacreacion(new Date());
		dto.setFechamodificacion(new Date());
		LineaProductoDto dtoResultado = service.insert(dto, LineaProductoDto.class);
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);
		
		Optional<LineaProductoDto> dtoResultado = service.findById(id, LineaProductoDto.class);
				
		modelo.addAttribute("modelo", dtoResultado.get());
		mostrarTitulosYActiveNav(modelo, "Linea Producto", "Editar", "LineaProducto");
		return dominio_lineasproducto + "/CrearLineaProducto";
	}
	
	@PostMapping("/Editar/{id}")
	public String editar(LineaProductoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);		
		dto.setFechamodificacion(new Date());
		LineaProductoDto dtoResultado = service.update(dto.getId(), dto, LineaProductoDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		return dominio_lineasproducto + "/CrearLineaProducto";
	}
	
		
}


