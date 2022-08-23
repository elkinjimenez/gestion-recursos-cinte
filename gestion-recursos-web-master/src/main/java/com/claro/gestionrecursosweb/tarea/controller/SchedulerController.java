package com.claro.gestionrecursosweb.tarea.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.tarea.dto.TareaDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaEventoDto;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController extends BaseController {

	@Autowired
	private ApiService<TareaDto, Integer> serviceTarea;
	
	@GetMapping
	public ArrayList<TareaEventoDto> GET(@RequestParam(required = false) String timeshift, 
			@RequestParam(required = false) Date from, @RequestParam(required = false) Date to,
			@RequestParam(required = false) String vista, @RequestParam(required = false) String vistafiltro) {
		
		serviceTarea.setapiservicename(dominio_tarea);
		Iterable<TareaDto> tareas = serviceTarea.findAll(TareaDto.class);
		ArrayList<TareaEventoDto> eventos = new ArrayList<TareaEventoDto>();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (TareaDto item : tareas) {
			TareaEventoDto evento = new TareaEventoDto();
			evento.setId(item.getId());
			evento.setText(item.getNombre());
			
			if (item.getFechainireal() == null)
				item.setFechainireal(new Date());
			if (item.getFechafinreal() == null)
				item.setFechafinreal(new Date());
			
			evento.setStart_date(dateFormat.format(item.getFechainireal()) + " 08:00");
			evento.setEnd_date(dateFormat.format(item.getFechafinreal()) + " 18:00");
			eventos.add(evento);
		}
		
		return eventos;
	}
	
}
