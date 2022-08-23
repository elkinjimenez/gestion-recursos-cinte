package com.claro.gestionrecursosweb.empleado.controller;

import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.claro.gestionrecursosweb.persona.dto.PersonaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoControlDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;

@Controller
@RequestMapping("/Empleadocontrol")
public class EmpleadoControlController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoControlController.class);
	
	@Autowired
	private ApiService<EmpleadoControlDto, Integer> service;
	@Autowired
	private ApiService<ProyectoDto, Integer> serviceProyecto;

	private ApiService<PersonaDto, Integer> servicePersona;
		
	public void configurar(Model modelo, HttpServletRequest request) {
		service.setapiservicename(dominio_empleadocontrol);
		super.setFormularioEstandar(modelo, request, dominio_empleadocontrol);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model, HttpServletRequest request) {
		configurar(model, request);
		
		Iterable<EmpleadoControlDto> dto = service.findAll(EmpleadoControlDto.class);
		
		model.addAttribute("modelo", dto);
		return dominio_empleadocontrol + "/Filtro";
	}
	
	@GetMapping("Crear")
	public String crear(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		modelo.addAttribute("modelo", new EmpleadoControlDto());
		cargarListas(modelo);
		return dominio_empleadocontrol + "/EmpleadoControl";
	}
	
	@PostMapping("/Crear")
	public String crear(EmpleadoControlDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		EmpleadoControlDto dtoResultado = service.insert(dto, EmpleadoControlDto.class);
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);
		
		Optional<EmpleadoControlDto> dtoResultado = service.findById(id, EmpleadoControlDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new EmpleadoControlDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		return dominio_empleadocontrol + "/EmpleadoControl";
	}
	
	@PostMapping("/Editar/{id}")
	public String editar(EmpleadoControlDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		
		EmpleadoControlDto dtoResultado = service.update(dto.getId(), dto, EmpleadoControlDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio_empleadocontrol + "/EmpleadoControl";
	}
	
	@ResponseBody
	@RequestMapping(value = "/Eliminar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> eliminar(Integer id) {
		try {
			service.setapiservicename(dominio_empleadocontrol);
			service.deleteById(id);
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			return ResponseEntity.ok(-1);
		}		
	}
	
	private void cargarListas(Model modelo) {
		serviceProyecto.setapiservicename(dominio_proyecto);
		Iterable<ProyectoDto> proyectos = serviceProyecto.findAll(ProyectoDto.class);
		modelo.addAttribute("proyectos", proyectos);		
		
	}
	
	@GetMapping("/horasTareaRecursoFecha")
	public ResponseEntity<Double> horasTareaRecursoFecha(@RequestParam Integer codEmpleado,
			@RequestParam Integer codTarea,@RequestParam String fecha) {
		try {
			service.setapiservicename(dominio_empleadocontrol+"/horastarearecursofecha");
			EmpleadoControlDto dto=new EmpleadoControlDto();
			dto.setCodempleado(codEmpleado);
			dto.setCodtarea(codTarea);
			dto.setFechahorainicio(new SimpleDateFormat("dd/MM/yyyy").parse(fecha));
			EmpleadoControlDto horas = service.findByParams(dto,EmpleadoControlDto.class);
			return ResponseEntity.ok(horas.getHoras().doubleValue());
		} catch (Exception e) {
			return ResponseEntity.ok(0.0);
		}		
	}

	@GetMapping("/horasRecursoFecha")
	public ResponseEntity<Double> horasRecursoFecha(@RequestParam Integer codEmpleado,
			@RequestParam String fecha) {
		try {
			service.setapiservicename(dominio_empleadocontrol+"/horasrecursofecha");
			
			EmpleadoControlDto dto=new EmpleadoControlDto();
			dto.setCodempleado(codEmpleado);
			dto.setFechahorainicio(new SimpleDateFormat("dd/MM/yyyy").parse(fecha));
			EmpleadoControlDto horas = service.findByParams(dto,EmpleadoControlDto.class);
			return ResponseEntity.ok(horas.getHoras().doubleValue());
		} catch (Exception e) {
			return ResponseEntity.ok(0.0);
		}		
	}

}
