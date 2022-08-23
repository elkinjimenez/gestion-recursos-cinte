package com.claro.gestionrecursosweb.tarea.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.seguridad.domain.SeguridadService;
import com.claro.gestionrecursosweb.seguridad.domain.UsuarioRolEnum;
import com.claro.gestionrecursosweb.seguridad.dto.UsuarioDto;
import com.claro.gestionrecursosweb.tarea.dto.ActividadDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaActividadDto;

@RestController
@RequestMapping("/api/gantt")
public class GanttController extends BaseController {

	@Autowired
	private ApiService<TareaActividadDto, Integer> serviceTareaActividad;
	@Autowired
	private SeguridadService serviceSeguridad;
	
	@GetMapping
	public ActividadDto get(HttpServletRequest request, @RequestParam(required = false) String vista, @RequestParam(required = false) String vistafiltro, @RequestParam(required = false) String modo, @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechainicio, @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechafin) {
		String filtro = "?codproyecto=%s&codproveedor=%s&codpersona=%s&fechainicio=%s&fechafin=%s";
		
		if (vista != null && vista.trim().equals(""))
			vista = null;
		
		// Por defecto el rango de fechas es de 2 meses, el mes actual y el mes anterior
		Calendar calendar = Calendar.getInstance();
		if (fechainicio == null) {			
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			fechainicio = calendar.getTime();
		}
		
		if (fechafin == null) {
			calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
			fechafin = calendar.getTime();
		}		

		SimpleDateFormat dateFormat  = new SimpleDateFormat("dd/MM/yyyy");		
		
		if (hasRole(UsuarioRolEnum.ADMIN) && modo == null) {
			if (vista == null || vista.equals("PROYECTO"))
				filtro = String.format(filtro, vistafiltro == null ? "0" : vistafiltro, "0", "0", dateFormat.format(fechainicio), dateFormat.format(fechafin));
			else if (vista != null && vista.equals("PROVEEDOR"))
				filtro = String.format(filtro, "0", vistafiltro, "0", dateFormat.format(fechainicio), dateFormat.format(fechafin));
			else if (vista != null && vista.equals("RECURSO"))
				filtro = String.format(filtro, "0", "0", vistafiltro, dateFormat.format(fechainicio), dateFormat.format(fechafin));
		} else if (hasRole(UsuarioRolEnum.ADMIN) || hasRole(UsuarioRolEnum.RECURSO)) {
			UsuarioDto usuario = serviceSeguridad.findByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
			filtro = String.format(filtro, "0", "0", usuario.getCodpersona(), dateFormat.format(fechainicio), dateFormat.format(fechafin));
		}
		
		serviceTareaActividad.setapiservicename(dominio_tareaactividad + "/vu/filtro" + filtro);
		//System.out.println("*************************************** q= " + filtro);
		Iterable<TareaActividadDto> actividades = serviceTareaActividad.findAll(TareaActividadDto.class);
			
		ActividadDto gantt = new ActividadDto();
		gantt.setData(actividades);	
		
		return gantt;
	}
	
}
