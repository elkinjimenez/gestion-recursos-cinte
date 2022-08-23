package com.claro.gestionrecursosweb.empleado.controller;

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
import com.claro.gestionrecursosweb.empleado.domain.ControlTiempoService;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoControlDto;
import com.claro.gestionrecursosweb.persona.dto.PersonaDto;

@Controller
@RequestMapping("/ControlTiempo")
public class ControlTiempoController extends BaseController {

	@Autowired
	private ControlTiempoService service;
	
	public void ConfigurarService() {
		service.setapiservicename("EmpleadoControl");
	}
	
	@GetMapping("/Filtro")
	public String Filtro(Model model) {
		ConfigurarService();
		
		Iterable<EmpleadoControlDto> dto = service.findAll(EmpleadoControlDto.class);
		
		model.addAttribute("modelo", dto);
		return "persona/Filtro";
	}
	
	@GetMapping("Crear")
	public String Crear(Model modelo) {
		ConfigurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new PersonaDto());
		return "persona/Persona";
	}
	
	@PostMapping("/Crear")
	public String Crear(EmpleadoControlDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		ConfigurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		EmpleadoControlDto dtoResultado = service.insert(dto, EmpleadoControlDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String Editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		ConfigurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<EmpleadoControlDto> dtoResultado = service.findById(id, EmpleadoControlDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new EmpleadoControlDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		return "persona/Persona";
	}
	
	@PostMapping("/Editar")
	public String Editar(EmpleadoControlDto dto, BindingResult result, Model modelo) {
		ConfigurarService();
		modelo.addAttribute("cl_formaction", "Editar");
		
		EmpleadoControlDto dtoResultado = service.update(dto.getId(), dto, EmpleadoControlDto.class);
		
		mostrarMensajes(modelo, "S", "U");
		
		modelo.addAttribute("modelo", dtoResultado);
		return "persona/Persona";
	}
	
}
