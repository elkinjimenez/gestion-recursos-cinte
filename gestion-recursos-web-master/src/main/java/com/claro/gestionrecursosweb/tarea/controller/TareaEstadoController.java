package com.claro.gestionrecursosweb.tarea.controller;


import java.util.Date;
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
import com.claro.gestionrecursosweb.tarea.dto.TareaEstadoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaEstadoTipoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaEstadoVistaDTO;

@Controller
@RequestMapping("/Tareaestado")
public class TareaEstadoController extends BaseController {
	

@Autowired
private ApiService<TareaEstadoDto, Integer> service;

@Autowired
private ApiService<TareaEstadoTipoDto, Integer> serviceTareaEstadoTipo;

@Autowired
private ApiService<TareaEstadoVistaDTO, Integer> serviceView;


public void configurar(Model modelo, HttpServletRequest request) {

	service.setapiservicename(dominio_tareaestado);
	super.setFormularioEstandar(modelo, request, dominio_tareaestado);
}


@GetMapping("/Filtro")
public String consultaTareaTipo(Model modelo, HttpServletRequest request) {	
	configurar(modelo, request);
	Iterable<TareaEstadoDto> listaTipoTarea = service.findAll(TareaEstadoDto.class);
	//serviceView.setapiservicename(dominio_vistausuario);
	//Iterable<TareaEstadoVistaDTO> listaTipoTarea = serviceView.findAllView("VIEW_TAREA_ESTADO",TareaEstadoVistaDTO.class);
	modelo.addAttribute("modelo", listaTipoTarea);
	cargarLista(modelo);
	mostrarTitulosYActiveNav(modelo, "Tarea Estado", "Filtro", "Tareaestado");
	return dominio_tareaestado + "/TareaEstado";
}

@GetMapping("/Crear")
public String crear(Model modelo, HttpServletRequest request) {
	configurar(modelo, request);
	modelo.addAttribute("modelo", new TareaEstadoDto());
	cargarLista(modelo);
	mostrarTitulosYActiveNav(modelo, "Tarea Estado", "Crear", "Tareaestado");
	return dominio_tareaestado + "/CrearTareaEstado";
}

@PostMapping("/Crear")
public String crear(TareaEstadoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
	configurar(modelo, request);
	dto.setFechacreacion(new Date());
	dto.setFechamodificacion(new Date());
	TareaEstadoDto dtoResultado = service.insert(dto, TareaEstadoDto.class);
	cargarLista(modelo);
	return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
}

@GetMapping("/Editar/{id}")
public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
	configurar(modelo, request);
	mostrarMensajes(modelo, cla);
	
	Optional<TareaEstadoDto> dtoResultado = service.findById(id, TareaEstadoDto.class);
	if (dtoResultado == null)
		dtoResultado = Optional.of(new TareaEstadoDto());
	cargarLista(modelo);
	modelo.addAttribute("modelo", dtoResultado.get());
	mostrarTitulosYActiveNav(modelo, "Tarea Estado", "Editar", "Tareaestado");
	return dominio_tareaestado + "/CrearTareaEstado";
}

@PostMapping("/Editar/{id}")
public String editar(TareaEstadoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
	configurar(modelo, request);
	dto.setFechamodificacion(new Date());
	TareaEstadoDto dtoResultado = service.update(dto.getId(), dto, TareaEstadoDto.class);				
	modelo.addAttribute("modelo", dtoResultado);
	cargarLista(modelo);
	mostrarMensajes(modelo, "S", "U");
	return dominio_tareaestado + "/CrearTareaEstado";
}

	private void cargarLista(Model model) {
		serviceTareaEstadoTipo.setapiservicename(dominio_tareaestadotipo);
		Iterable<TareaEstadoTipoDto> listaTareaEstadoTipo = serviceTareaEstadoTipo.findAll(TareaEstadoTipoDto.class);
		model.addAttribute("listaTareaEstadoTipo",listaTareaEstadoTipo);
	}

}
