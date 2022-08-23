package com.claro.gestionrecursosweb.compromisosfabrica.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.compromisosfabrica.dto.CompromisosFabricaDto;
import com.claro.gestionrecursosweb.compromisosfabrica.dto.FiltrosDto;
import com.claro.gestionrecursosweb.estructura.dto.EstructuraOrganizacionalDto;
import com.claro.gestionrecursosweb.novedad.dto.EstadoEntregaDto;
import com.claro.gestionrecursosweb.novedad.dto.EstadoRoadMapDto;
import com.claro.gestionrecursosweb.novedad.dto.RoadMapDto;
import com.claro.gestionrecursosweb.novedad.dto.TipoRoadMapDto;
import com.claro.gestionrecursosweb.opcionfront.dto.OpcionFrontDto;
import com.claro.gestionrecursosweb.perfil.dto.*;
import com.claro.gestionrecursosweb.proveedor.dto.ProveedorDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import com.claro.gestionrecursosweb.reportegenerico.dto.ColumnInformationDbDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Fabrica")
public class CompromisosFabricaController extends BaseController {

	@Autowired
	private ApiService<CompromisosFabricaDto, Integer> service;

	@Autowired
	private ApiService<OpcionFrontDto, Integer> opcionFrontService;

	@Autowired
	private ApiService<ColumnInformationDbDto, String> columnInformationService;

	@Autowired
	private ApiService<ProyectoDto, Integer> serviceProyecto;

	@Autowired
	private ApiService<ProveedorDto, Integer> serviceProveedor;

	@Autowired
	private ApiService<PerfiltipoDto, Integer> servicePerfilTipo;

	@Autowired
	private ApiService<PerfilDto, Integer> servicePerfil;

	@Autowired
	private ApiService<PerfilnivelDto, Integer> servicePerfilNivel;

	@Autowired
	private ApiService<EstructuraOrganizacionalDto, Integer> serviceEstructuraOrganizacional;

	@Autowired
	private ApiService<TipoRoadMapDto, Integer> tipoRoadMapService;

	@Autowired
	private ApiService<EstadoEntregaDto, Integer> estadoEntregaService;

	@Autowired
	private ApiService<EstadoRoadMapDto, Integer> estadoRoadMapService;

	@Autowired
	private ApiService<RoadMapDto, Integer> roadMapService;

	private final String editTemplateName = "CompromisoFabrica";
	private final String roadMapTemplate = "RoadMap";
	private Iterable<TipoRoadMapDto> tipoRoadMap;
	private Iterable<TipoRoadMapDto> despliegue;
	private Iterable<EstadoEntregaDto> estadoEntrega;
	private Iterable<EstadoRoadMapDto> estadoRoadMap;

	public void configurar(Model modelo, HttpServletRequest request) {
		service.setapiservicename(dominio_compromisos_fabrica);
		super.setFormularioEstandar(modelo, request, dominio_compromisos_fabrica);
	}

	@GetMapping("/Filtro")
	public String filtro(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
		cargarListas(modelo);
		modelo.addAttribute("filtrosDto", new FiltrosDto());
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);
		Iterable<CompromisosFabricaDto> dto = service.findAll(CompromisosFabricaDto.class);
		modelo.addAttribute("modelo", dto);
		mostrarTitulosYActiveNav(modelo, "Gestionar Compromisos Fábrica", "Filtro", "Fabrica");
		return dominio_compromisos_fabrica + "/Filtro";
	}

	@PostMapping("/Filtro")
	public String filtrar(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request,
			FiltrosDto filtrosDto) {
		cargarListas(modelo);
		modelo.addAttribute("filtrosDto", filtrosDto);
		mostrarMensajes(modelo, cla);
		service.setapiservicename(dominio_compromisos_fabrica + "/filtrar");
		Iterable<CompromisosFabricaDto> dto = service.findAllWithPayload(CompromisosFabricaDto.class, filtrosDto);
		modelo.addAttribute("modelo", dto);
		return dominio_compromisos_fabrica + "/Filtro";
	}

	@GetMapping("/Crear")
	public String crear(Model modelo, HttpServletRequest request) {
		cargarListas(modelo);
		configurar(modelo, request);
		modelo.addAttribute("modelo", new CompromisosFabricaDto());
		mostrarTitulosYActiveNav(modelo, "Gestionar Compromisos Fábrica", "Crear", "Fabrica");
		return dominio_compromisos_fabrica + "/" + editTemplateName;
	}

	@PostMapping("/Crear")
	public String crear(CompromisosFabricaDto dto, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);
		CompromisosFabricaDto dtoResultado = service.insert(dto, CompromisosFabricaDto.class);
		modelo.addAttribute("cl_validacion_mensaje_tipo", "alert-success");
		modelo.addAttribute("cl_validation_mensaje",
				"La información se guardó correctamente, si pertenece a fábrica digital no olvidé diligenciar el RoadMap");
		return dominio_compromisos_fabrica + "/" + editTemplateName;
	}

	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla,
			HttpServletRequest request) {
		cargarListas(modelo);
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);
		Optional<CompromisosFabricaDto> dtoResultado = service.findById(id, CompromisosFabricaDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new CompromisosFabricaDto());

		modelo.addAttribute("modelo", dtoResultado.get());
		mostrarTitulosYActiveNav(modelo, "Gestionar Compromisos Fábrica", "Editar", "Fabrica");
		return dominio_compromisos_fabrica + "/" + editTemplateName;
	}

	@GetMapping("/RoadMap/{id}")
	public String roadMap(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla,
			HttpServletRequest request) {
		configurar(modelo, request);
		mostrarMensajes(modelo, cla);
		roadMapService.setapiservicename(dominio_compromisos_fabrica);
		String gerente = service.findByParamApi("gerenteProyecto", id + "");

		roadMapService.setapiservicename(dominio_roadMap);
		Optional<RoadMapDto> roadMapOptional = roadMapService.findByParamApi("codPryBrief", id.toString(),
				RoadMapDto.class);

		RoadMapDto roadMap = new RoadMapDto();
		if (roadMapOptional == null) {
			roadMap.setCodPryBrief(id);
		} else {
			roadMap = roadMapOptional.get();
		}
		this.cargarListasRoadMap(modelo);

		modelo.addAttribute("roadMap", roadMap);
		modelo.addAttribute("gerente", gerente);
		mostrarTitulosYActiveNav(modelo, "Gestionar Compromisos Fábrica", "RoadMap", "Fabrica");
		return dominio_compromisos_fabrica + "/" + roadMapTemplate;
	}

	private void cargarListasRoadMap(Model modelo) {
		try {
			estadoEntregaService.setapiservicename(dominio_estadoEntrega);
			estadoEntrega = estadoEntregaService.findAll(EstadoEntregaDto.class);

			estadoRoadMapService.setapiservicename(dominio_estadoRoadMap);
			estadoRoadMap = estadoRoadMapService.findAll(EstadoRoadMapDto.class);

			estadoRoadMapService.setapiservicename(dominio_roadMap + "/despliegueRoadMap");
			despliegue = tipoRoadMapService.findAll(TipoRoadMapDto.class);

			tipoRoadMapService.setapiservicename(dominio_tipoRoadMap);
			tipoRoadMap = tipoRoadMapService.findAll(TipoRoadMapDto.class);

			boolean datos_cargados = ((Collection<?>) estadoEntrega).size() > 0
					&& ((Collection<?>) estadoRoadMap).size() > 0 && ((Collection<?>) despliegue).size() > 0
					&& ((Collection<?>) tipoRoadMap).size() > 0;

			modelo.addAttribute("datos_cargados", datos_cargados);
			modelo.addAttribute("estadosEntrega", estadoEntrega);
			modelo.addAttribute("estadosRoadMap", estadoRoadMap);
			modelo.addAttribute("listDespliegue", despliegue);
			modelo.addAttribute("tiposRoadMap", tipoRoadMap);
		} catch (NullPointerException ex) {
			modelo.addAttribute("datos_cargados", false);
		}

	}

	@PostMapping("/RoadMap")
	public String crearRoadMap(RoadMapDto roadMap, BindingResult result, Model modelo, HttpServletRequest request) {
		configurar(modelo, request);

		roadMap.setActualizadoXUsuarioClaro(this.getUsuarioSesion());
		roadMapService.setapiservicename(dominio_roadMap);
		roadMapService.insert(roadMap, RoadMapDto.class);
		return redireccion("RoadMap", "" + roadMap.getCodPryBrief(), "S", "C", request);
	}

	@PostMapping("/Editar/{id}")
	public String editar(CompromisosFabricaDto dto, BindingResult result, Model modelo, HttpServletRequest request,
			@PathVariable Integer id) {
		configurar(modelo, request);
		CompromisosFabricaDto dtoResultado = service.update(dto.getId(), dto, CompromisosFabricaDto.class);
		modelo.addAttribute("modelo", dtoResultado);
		modelo.addAttribute("cl_validacion_mensaje_tipo", "alert-success");
		modelo.addAttribute("cl_validation_mensaje",
				"La información se guardó correctamente, si pertenece a fábrica digital no olvidé diligenciar el RoadMap");
		return dominio_compromisos_fabrica + "/" + editTemplateName;
	}

	@GetMapping("/Eliminar/{id}")
	public String eliminar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla,
			HttpServletRequest request) {
		configurar(modelo, request);

		try {
			service.deleteById(id);
			return redireccion("/Fabrica/Filtro", null, "S", "D", request);
		} catch (Exception e) {
			return redireccion("/Fabrica/Filtro", null, "E", "D", request);
		}
	}

	private void cargarListas(Model model) {
		try {
			columnInformationService.setapiservicename(dominio_compromisos_fabrica + "/columnas-filtros");
			List<ColumnInformationDbDto> columnasFiltro = (List<ColumnInformationDbDto>) columnInformationService
					.findAll(ColumnInformationDbDto.class);
			model.addAttribute("columnasFiltro", columnasFiltro);

			serviceProyecto.setapiservicename(dominio_proyecto);
			List<ProyectoDto> proyectos = (List<ProyectoDto>) serviceProyecto.findAll(ProyectoDto.class);
			model.addAttribute("proyectos", proyectos);

			serviceProveedor.setapiservicename(dominio_proveedor);
			List<ProveedorDto> proveedores = (List<ProveedorDto>) serviceProveedor.findAll(ProveedorDto.class);
			model.addAttribute("proveedores", proveedores);

			servicePerfilTipo.setapiservicename(dominio_perfiltipo);
			List<PerfiltipoDto> perfiltipos = (List<PerfiltipoDto>) servicePerfilTipo.findAll(PerfiltipoDto.class);
			model.addAttribute("perfiltipos", perfiltipos);

			servicePerfil.setapiservicename(dominio_perfil);
			List<PerfilDto> perfiles = (List<PerfilDto>) servicePerfil.findAll(PerfilDto.class);
			model.addAttribute("perfiles", perfiles);

			serviceEstructuraOrganizacional.setapiservicename(dominio_estructuraorganizacional);
			List<EstructuraOrganizacionalDto> estructurasorganizacionales = (List<EstructuraOrganizacionalDto>) serviceEstructuraOrganizacional
					.findAll(EstructuraOrganizacionalDto.class);
			model.addAttribute("estructurasorganizacionales", estructurasorganizacionales);

			servicePerfilNivel.setapiservicename(dominio_perfilnivel);
			List<PerfilnivelDto> perfilniveles = (List<PerfilnivelDto>) servicePerfilNivel
					.findAll(PerfilnivelDto.class);
			model.addAttribute("perfilniveles", perfilniveles);

			opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
			List<OpcionFrontDto> lineasProducto = (List<OpcionFrontDto>) opcionFrontService
					.findAllByPathVariable(OpcionFrontDto.class, "LineaProductoCF");
			model.addAttribute("lineasProducto", lineasProducto);

			opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
			List<OpcionFrontDto> resultados = (List<OpcionFrontDto>) opcionFrontService
					.findAllByPathVariable(OpcionFrontDto.class, "ResultadoCF");
			model.addAttribute("resultados", resultados);

			opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
			List<OpcionFrontDto> estados = (List<OpcionFrontDto>) opcionFrontService
					.findAllByPathVariable(OpcionFrontDto.class, "EstadoCF");
			model.addAttribute("estados", estados);

			boolean datos_cargados = ((Collection<?>) perfiles).size() > 0 && ((Collection<?>) perfiltipos).size() > 0
					&& ((Collection<?>) perfilniveles).size() > 0 && ((Collection<?>) proveedores).size() > 0
					&& ((Collection<?>) columnasFiltro).size() > 0 && ((Collection<?>) proyectos).size() > 0
					&& ((Collection<?>) estructurasorganizacionales).size() > 0
					&& ((Collection<?>) lineasProducto).size() > 0 && ((Collection<?>) resultados).size() > 0
					&& ((Collection<?>) estados).size() > 0;

			model.addAttribute("datos_cargados", datos_cargados);
		} catch (NullPointerException ex) {
			model.addAttribute("datos_cargados", false);
		}
	}

	@ModelAttribute("numeros")
	private List<Integer> listNumero() {
		List<Integer> listNumero = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			listNumero.add(i);
		}
		return listNumero;
	}
}
