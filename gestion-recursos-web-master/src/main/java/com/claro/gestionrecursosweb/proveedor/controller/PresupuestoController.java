package com.claro.gestionrecursosweb.proveedor.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.claro.gestionrecursosweb.cargue.model.CarguePresupuesto;
import com.claro.gestionrecursosweb.cargue.service.ICargueService;
import com.claro.gestionrecursosweb.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.proyecto.dto.PresupuestoDto;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Presupuesto")
public class PresupuestoController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PresupuestoController.class);
	private static final String FORMATO1 = "xls";
	private static final String FORMATO2 = "xlsx";

	@Autowired
	private ApiService<PresupuestoDto, Integer> service;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private ICargueService iCargueService;

	public void configurar(Model modelo, HttpServletRequest request) {
		service.setapiservicename(dominio_presupuesto);
		super.setFormularioEstandar(modelo, request, dominio_presupuesto);
	}

	@GetMapping("/Filtro")
	public String filtro(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		Iterable<PresupuestoDto> dto = service.findAll(PresupuestoDto.class);
		modelo.addAttribute("modelo", dto);
		mostrarTitulosYActiveNav(modelo, "Presupuesto", "Filtro", "Presupuesto");
		return dominio_presupuesto + "/Filtro";
	}

	@GetMapping("/Crear")
	public String crear(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);

		modelo.addAttribute("modelo", new PresupuestoDto());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Presupuesto", "Crear", "Presupuesto");
		return dominio_presupuesto + "/Presupuesto";
	}

	@GetMapping("/Cargar")
	public String cargar(Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Presupuesto", "Cargar", "Presupuesto");
		return dominio_presupuesto + "/Cargar";

	}

	@PostMapping("/Cargar")
	public String cargar(Model modelo, HttpServletRequest request, @RequestParam("archivo") MultipartFile archivo,
			RedirectAttributes flash) throws IOException {
		configurar(modelo, request);
		Map<String, List<CarguePresupuesto>> resumen = null;
		String extension = FilenameUtils.getExtension(archivo.getOriginalFilename());
		if (!(extension.toLowerCase().equals(FORMATO1) || extension.toLowerCase().equals(FORMATO2))) {
			modelo.addAttribute("error", "El archivo no contiene la extesión correcta");
			return dominio_presupuesto + "/Cargar";
		}
		if (archivo.isEmpty()) {
			cargarListas(modelo);
			modelo.addAttribute("error", "Por favor debe cargar un archivo!!!");
			return dominio_presupuesto + "/Cargar?clm=adpr";
		}
		try {
			String usuarioSesion = this.getUsuarioSesion();
			resumen = iCargueService.carguePresupuesto(archivo, usuarioSesion);
			List<CarguePresupuesto> actualizados = resumen.get("actualizados");
			List<CarguePresupuesto> errores = resumen.get("errores");
			flash.addFlashAttribute("actualizados", actualizados);
			// flash.addFlashAttribute("errores", errores);
		} catch (HttpServerErrorException ex) {
			modelo.addAttribute("error", "Error procesando el archivo!!!");
			return dominio_presupuesto + "/Cargar";
		}

		flash.addFlashAttribute("success", "El archivo ha sido cargado exitosamente!!!");

		cargarListas(modelo);
		return "redirect:/Presupuesto/CargarView" + "?clm=adpr";
	}

	@GetMapping("/CargarView")
	public String cargarView(@ModelAttribute("actualizados") List<CarguePresupuesto> actualizados, Model modelo,
			HttpServletRequest request) {
		configurar(modelo, request);

		LOGGER.info("actualizados: " + actualizados.size());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Presupuesto", "Cargar", "Presupuesto");
		return dominio_presupuesto + "/CargarView";
	}

	@PostMapping("/Crear")
	public String crear(PresupuestoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		PresupuestoDto dtoResultado = service.insert(dto, PresupuestoDto.class);
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}

	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla,
			HttpServletRequest request) {
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);

		Optional<PresupuestoDto> dtoResultado = service.findById(id, PresupuestoDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new PresupuestoDto());

		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		mostrarTitulosYActiveNav(modelo, "Presupuesto", "Editar", "Presupuesto");
		return dominio_presupuesto + "/Presupuesto";
	}

	@PostMapping("/Editar/{id}")
	public String editar(PresupuestoDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		modelo.addAttribute("cl_formaction", "Editar");
		PresupuestoDto dtoResultado = service.update(dto.getId(), dto, PresupuestoDto.class);
		modelo.addAttribute("modelo", dtoResultado);
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio_presupuesto + "/Presupuesto";
	}

	private void cargarListas(Model modelo) {
		try {
			List<EmpleadoVUDto> lEmpleadosDto = empleadoService.findAllGerentes();
			modelo.addAttribute("gerentes", lEmpleadosDto);
			boolean datos_cargados = ((Collection<?>) lEmpleadosDto).size() > 0;
			modelo.addAttribute("datos_cargados", datos_cargados);
		} catch (NullPointerException e) {
			// TODO: handle exception
			modelo.addAttribute("datos_cargados", false);
		}
	}

//    @ModelAttribute("gerentes")
//    public List<EmpleadoVUDto> listaGerentes() {
//        List<EmpleadoVUDto> lEmpleadosDto = empleadoService.findAllGerentes();
//        return lEmpleadosDto;
//        //return new ArrayList<EmpleadoVUDto>();
//    }

}
