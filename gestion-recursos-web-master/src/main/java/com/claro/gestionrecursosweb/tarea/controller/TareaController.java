package com.claro.gestionrecursosweb.tarea.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.claro.gestionrecursosweb.application.TareaApplication;
import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaEstadoTipoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaTipoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaVUDto;

@Controller
@RequestMapping("/Tareas")
public class TareaController extends BaseController {
	
	@Autowired
	private TareaApplication service;
	@Autowired
	private ApiService<ProyectoDto, Integer> serviceProyecto;
	@Autowired
	private ApiService<TareaTipoDto, Integer> serviceTareaTipo;
	@Autowired
	private ApiService<EmpleadoVUDto, Integer> serviceEmpleadoVU;
	@Autowired
	private ApiService<TareaEstadoTipoDto, Integer> serviceTareaEstadoTipo;
	
	@GetMapping
	public String Tareas(Model modelo) {
		
		serviceProyecto.setapiservicename(dominio_proyecto);
		Iterable<ProyectoDto> proyectos = serviceProyecto.findAll(ProyectoDto.class);
		modelo.addAttribute("proyectos", proyectos);
		modelo.addAttribute("modelotarea", new TareaDto());
		cargarListas(modelo);
		return dominio_tarea + "/Tarea";
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TareaDto> Tareas(TareaDto dto) {
		dto = service.insertar(dto);
		return ResponseEntity.ok(dto);
	}
	
	@ResponseBody
	@RequestMapping(value = "CambiarEstado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TareaDto> cambiarEstado(TareaDto dto) {
		Optional<TareaDto> tareaActual = service.findById(dto.getId());
		if (tareaActual.isPresent()) {
			tareaActual.get().setCodtareaestado(dto.getCodtareaestado());
			service.actualizar(tareaActual.get());
		}
		return ResponseEntity.ok(dto);
	}
	
	@ResponseBody
	@RequestMapping(value = "/Eliminar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> eliminar(Integer id) {
		try {
			service.deleteById(id);
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			return ResponseEntity.ok(-1);
		}		
	}
	
	@PostMapping("/lista")
	public String tareasParcial(Model modelo, @RequestParam Integer codproyecto) {
		Iterable<TareaVUDto> resultado = service.findAllByAttr("vu/codproyecto", codproyecto.toString());
		
		serviceTareaEstadoTipo.setapiservicename(dominio_tareaestadotipo);
		Iterable<TareaEstadoTipoDto> tareaEstadoTipos = serviceTareaEstadoTipo.findAll(TareaEstadoTipoDto.class);
						
		modelo.addAttribute("modelo", resultado);
		modelo.addAttribute("tareaestadotipos", tareaEstadoTipos);
		return dominio_tarea + "/_Lista";
	}
	
	
	private void cargarListas(Model modelo) {
		serviceTareaTipo.setapiservicename(dominio_tareatipo);
		Iterable<TareaTipoDto> tareatipos = serviceTareaTipo.findAll(TareaTipoDto.class);
		modelo.addAttribute("tareatipos", tareatipos);
		
		serviceEmpleadoVU.setapiservicename(dominio_empleado + "/vu");
		Iterable<EmpleadoVUDto> empleados = serviceEmpleadoVU.findAll(EmpleadoVUDto.class);
		modelo.addAttribute("empleados", empleados);
	}
}
