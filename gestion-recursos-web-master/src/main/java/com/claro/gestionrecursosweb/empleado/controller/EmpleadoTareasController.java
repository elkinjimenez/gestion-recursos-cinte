package com.claro.gestionrecursosweb.empleado.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import com.claro.gestionrecursosweb.base.model.RespuestaBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.claro.gestionrecursosweb.application.EmpleadoApplication;
import com.claro.gestionrecursosweb.application.TareaApplication;
import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoControlDto;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoDto;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaTipoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaVUDto;

@Controller
@RequestMapping("/Empleado/Tareas")
public class EmpleadoTareasController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoTareasController.class);
	
	@Autowired
	private TareaApplication service;
	@Autowired
	private ApiService<ProyectoDto, Integer> serviceProyecto;
	@Autowired
	private ApiService<TareaTipoDto, Integer> serviceTareaTipo;
	@Autowired
	private ApiService<EmpleadoVUDto, Integer> serviceEmpleadoVU;
	@Autowired
	private ApiService<EmpleadoControlDto, Integer> serviceEmpleadoControl;
	@Autowired
	private ApiService<BigDecimal, Integer> serviceApi;
	@Autowired
	private EmpleadoApplication serviceEmpleado;
	
	@GetMapping
	public String filtro(Model modelo) {
		serviceProyecto.setapiservicename(dominio_proyecto);
		Iterable<ProyectoDto> proyectos = serviceProyecto.findAll(ProyectoDto.class);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		modelo.addAttribute("fechainicio", calendar.getTime());
		
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
		modelo.addAttribute("fechafin", calendar.getTime());		
		
		modelo.addAttribute("proyectos", proyectos);
		modelo.addAttribute("modelotarea", new TareaDto());
		
		Authentication usuarioSesion = SecurityContextHolder.getContext().getAuthentication();
		Optional<EmpleadoDto> empleado = serviceEmpleado.findActivoByUsuario(usuarioSesion.getName());

		if (empleado.isPresent()) {
			serviceApi.setapiservicename(dominio_empleadocontrol + "/horasmesactual");
			Optional<BigDecimal> horasMesActual = serviceApi.findById(empleado.get().getId(), BigDecimal.class);
			
			if (horasMesActual == null)
				horasMesActual = Optional.of(new BigDecimal(0));
			
			if (horasMesActual.isPresent()) {
				Calendar fechaActual = Calendar.getInstance();
				BigDecimal horas = horasMesActual.get();
				String mensaje = "";
				
				if (fechaActual.get(Calendar.DAY_OF_MONTH) <= 5)
					mensaje = "Horas registradas en el mes anterior: " + horas.toString();
				else 
					mensaje = "Horas registradas en el mes actual: " + horas.toString();
				
				modelo.addAttribute("horasmesactual", mensaje);
			}
		}
		
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Empleado Tareas", "Filtro", "Empleado/Tareas");
		return dominio_empleado + "/Tareas";
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> Tareas(EmpleadoControlDto dto) {
		Authentication usuarioSesion = SecurityContextHolder.getContext().getAuthentication();
		Optional<EmpleadoDto> empleado = serviceEmpleado.findActivoByUsuario(usuarioSesion.getName());		
		
		dto.setCodempleado(empleado.get().getId());
		serviceEmpleadoControl.setapiservicename(dominio_empleadocontrol);
		dto.setFechahorafin(dto.getFechahorainicio());
		dto = serviceEmpleadoControl.insert(dto, EmpleadoControlDto.class);
		if (!(String.valueOf(ApiService.codigoHttp).equals(String.valueOf(HttpStatus.OK.value()))) &&
				!(String.valueOf(ApiService.codigoHttp).equals(String.valueOf(HttpStatus.CREATED.value())))) {
			RespuestaBase respuestaBase = new RespuestaBase();
			respuestaBase.setCodigoEstatus(ApiService.codigoHttp);
			respuestaBase.setMensaje( ApiService.mensaje);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaBase);
		}
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/lista")
	public String tareasParcial(Model modelo, @RequestParam Integer codproyecto) {
		Iterable<TareaVUDto> resultado = service.findAllByAttr("vu/codproyecto", codproyecto.toString());
		
		modelo.addAttribute("modelo", resultado);
		return dominio_tarea + "/_Lista";
	}
	
	@PostMapping("/historial")
	public String tareaHistorial(Model modelo, @RequestParam Integer id) {
		serviceEmpleadoControl.setapiservicename(dominio_empleadocontrol);
		Iterable<EmpleadoControlDto> empleadoControl = serviceEmpleadoControl.findAllByAttr("codtarea", id.toString(), EmpleadoControlDto.class);
		modelo.addAttribute("modelo", empleadoControl);
		
		return dominio_tarea + "/_TareaHistorial";
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
