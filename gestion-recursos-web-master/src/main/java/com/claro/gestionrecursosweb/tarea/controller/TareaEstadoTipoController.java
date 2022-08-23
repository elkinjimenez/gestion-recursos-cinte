package com.claro.gestionrecursosweb.tarea.controller;

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
import com.claro.gestionrecursosweb.tarea.dto.TareaEstadoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaEstadoTipoDto;

@Controller
@RequestMapping("/Tareaestadotipo")
public class TareaEstadoTipoController extends BaseController{
	
	@Autowired
	private ApiService<TareaEstadoTipoDto, Integer> service;
	
	public void configurarService() {
		service.setapiservicename(dominio_tareaestadotipo);
	}
	
	public void configurar(Model modelo, HttpServletRequest request) {

		service.setapiservicename(dominio_tareaestado);
		super.setFormularioEstandar(modelo, request, dominio_tareaestado);
	}



	@GetMapping("/Filtro")
	public String consultaTareaTipo(Model modelo, HttpServletRequest request) {	
		configurar(modelo, request);
		service.setapiservicename(dominio_tareaestadotipo);
		Iterable<TareaEstadoTipoDto> listaTipoTarea = service.findAll(TareaEstadoTipoDto.class);
		modelo.addAttribute("modelo", listaTipoTarea);
		mostrarTitulosYActiveNav(modelo, "Tarea Estado Tipo", "Filtro", "Tareaestadotipo");
		return dominio_tareaestadotipo + "/TareaEstadoTipo";
	}

	@GetMapping("Crear")
	public String crear(Model modelo, HttpServletRequest request) {	
		configurar(modelo, request);
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		modelo.addAttribute("modelo",new TareaEstadoTipoDto());
		mostrarTitulosYActiveNav(modelo, "Tarea Estado Tipo", "Crear", "Tareaestadotipo");
		return dominio_tareaestadotipo + "/CrearTareaEstadoTipo";
	}

	@PostMapping("/Crear")
	public String crear(TareaEstadoTipoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		modelo.addAttribute("cl_formaction", "Crear");
		dto.setFechacreacion(new Date());
		dto.setFechamodificacion(new Date());
		TareaEstadoTipoDto dtoResultado = service.insert(dto, TareaEstadoTipoDto.class);

		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}

	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<TareaEstadoTipoDto> dtoResultado = service.findById(id, TareaEstadoTipoDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new TareaEstadoTipoDto());

		modelo.addAttribute("modelo", dtoResultado.get());
		mostrarTitulosYActiveNav(modelo, "Tarea Estado Tipo", "Editar", "Tareaestadotipo");
		return dominio_tareaestadotipo + "/CrearTareaEstadoTipo";
	}

	@PostMapping("/Editar")
	public String editar(TareaEstadoTipoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);	
		modelo.addAttribute("cl_formaction", "Editar");
		dto.setFechamodificacion(new Date());
		TareaEstadoTipoDto dtoResultado = service.update(dto.getId(), dto, TareaEstadoTipoDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		mostrarMensajes(modelo, "S", "U");
		return dominio_tareaestadotipo + "/CrearTareaEstadoTipo";
	}


}
