package com.claro.gestionrecursosweb.tarea.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.claro.gestionrecursosweb.tarea.dto.TareaTipoDto;

@Controller
@RequestMapping("/Tareatipo")
public class TareaTipoController extends BaseController {

@Value("${claro.dominio.tareatipo.nombre}")
private String dominio_tareatipo;

@Autowired
private ApiService<TareaTipoDto, Integer> service;

public void configurar(Model modelo, HttpServletRequest request) {
	service.setapiservicename(dominio_tareatipo);
	super.setFormularioEstandar(modelo, request, dominio_tareatipo);
}


@GetMapping("/Filtro")
public String consultaTareaTipo(Model modelo, HttpServletRequest request) {	
	configurar(modelo, request);
	Iterable<TareaTipoDto> listaTipoTarea = service.findAll(TareaTipoDto.class);
	modelo.addAttribute("modelo", listaTipoTarea);
	cargarListas(modelo);
	mostrarTitulosYActiveNav(modelo, "Tarea Tipo", "Filtro", "Tareatipo");
	return dominio_tareatipo + "/TareaTipo";
}

@GetMapping("/Crear")
public String crear(Model modelo, HttpServletRequest request) {
	configurar(modelo, request);
	modelo.addAttribute("modelo", new TareaTipoDto());
	mostrarTitulosYActiveNav(modelo, "Tarea Tipo", "Crear", "Tareatipo");
	return dominio_tareatipo + "/CrearTareaTipo";
}

@PostMapping("/Crear")
public String crear(TareaTipoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
	configurar(modelo, request);
	dto.setFechacreacion(new Date());
	dto.setFechamodificacion(new Date());
	TareaTipoDto dtoResultado = service.insert(dto, TareaTipoDto.class);
	return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
}

@GetMapping("/Editar/{id}")
public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
	configurar(modelo, request);
	mostrarMensajes(modelo, cla);
	
	Optional<TareaTipoDto> dtoResultado = service.findById(id, TareaTipoDto.class);
	if (dtoResultado == null)
		dtoResultado = Optional.of(new TareaTipoDto());
			
	modelo.addAttribute("modelo", dtoResultado.get());
	mostrarTitulosYActiveNav(modelo, "Tarea Tipo", "Editar", "Tareatipo");
	return dominio_tareatipo + "/CrearTareaTipo";
}

@PostMapping("/Editar/{id}")
public String editar(TareaTipoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
	configurar(modelo, request);
	dto.setFechamodificacion(new Date());
	TareaTipoDto dtoResultado = service.update(dto.getId(), dto, TareaTipoDto.class);				
	modelo.addAttribute("modelo", dtoResultado);
	
	mostrarMensajes(modelo, "S", "U");
	return dominio_tareatipo + "/CrearTareaTipo";
}


private void cargarListas(Model modelo) {
	service.setapiservicename(dominio_tareatipo);
	Iterable<TareaTipoDto> tareatipos = service.findAll(TareaTipoDto.class);
	modelo.addAttribute("tareatipos", tareatipos);
	
}
}
