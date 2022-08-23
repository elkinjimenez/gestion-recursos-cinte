package com.claro.gestionrecursosweb.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.claro.gestionrecursosweb.dto.PruebaDTO;


@RestController
@RequestMapping("/vistausuario")
public class VistaController extends BaseController {

	/*@Autowired
	private ApiService<PruebaDTO, Integer> service;
	
	@GetMapping("/filtro")
	public String Tareas(Model modelo) {
		
		service.setapiservicename(dominio_vistausuario);
		Iterable<PruebaDTO> proyectos = service.findAll(PruebaDTO.class);
		System.out.println("emtro biennnnnnn===============SSS");
		
		return dominio_tarea + "/Tarea";
	}*/
}
