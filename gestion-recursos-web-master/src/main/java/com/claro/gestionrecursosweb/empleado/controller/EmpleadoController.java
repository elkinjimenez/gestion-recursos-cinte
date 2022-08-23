package com.claro.gestionrecursosweb.empleado.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.claro.gestionrecursosweb.costoempleado.dto.CostoEmpleadoDto;
import com.claro.gestionrecursosweb.empleado.domain.GerenteService;
import com.claro.gestionrecursosweb.empleado.enums.ModalidadEmpleadoEnum;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import com.claro.gestionrecursosweb.seguridad.domain.UsuarioRolEnum;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.dto.TipoDocumentoDto;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoDto;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import com.claro.gestionrecursosweb.estructura.dto.EstructuraOrganizacionalDto;
import com.claro.gestionrecursosweb.opcionfront.dto.OpcionFrontDto;
import com.claro.gestionrecursosweb.perfil.dto.LineaProductoDto;
import com.claro.gestionrecursosweb.perfil.dto.PerfilDto;
import com.claro.gestionrecursosweb.perfil.dto.PerfilnivelDto;
import com.claro.gestionrecursosweb.perfil.dto.PerfiltipoDto;
import com.claro.gestionrecursosweb.persona.dto.PersonaDto;
import com.claro.gestionrecursosweb.persona.service.PersonaService;
import com.claro.gestionrecursosweb.proveedor.dto.ProveedorDto;

@Controller
@RequestMapping("/Empleado")
public class EmpleadoController extends BaseController {

    @Autowired
    private ApiService<CostoEmpleadoDto, Integer> serviceCosto;
    @Autowired
    private ApiService<EmpleadoDto, Integer> service;
    @Autowired
    private ApiService<EmpleadoVUDto, Integer> serviceVU;
    @Autowired
    private ApiService<PerfilDto, Integer> servicePerfil;
    @Autowired
    private ApiService<PerfiltipoDto, Integer> servicePerfilTipo;
    @Autowired
    private ApiService<PerfilnivelDto, Integer> servicePerfilNivel;
    @Autowired
    private ApiService<LineaProductoDto, Integer> serviceLineasProducto;
    @Autowired
    private ApiService<ProveedorDto, Integer> serviceProveedor;
    @Autowired
    private ApiService<PersonaDto, Integer> servicePersona;
    @Autowired
    private ApiService<TipoDocumentoDto, Integer> serviceTipoDocumento;
    @Autowired
    private ApiService<EstructuraOrganizacionalDto, Integer> serviceEstructuraOrganizacional;
    @Autowired
    private ApiService<OpcionFrontDto, Integer> opcionFrontService;
    @Autowired
    private ApiService<ProyectoDto, Integer> proyectoService;

    @Autowired
    private PersonaService personaservice;

    @Autowired
    private GerenteService gerenteService;

    public void configurar(Model modelo, HttpServletRequest request) {
        service.setapiservicename(dominio_empleado);
        super.setFormularioEstandar(modelo, request, dominio_empleado);
    }

    @GetMapping("/Filtro")
    public String filtro(Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        serviceVU.setapiservicename(dominio_empleado + "/vu");
        Iterable<EmpleadoVUDto> dto = serviceVU.findAll(EmpleadoVUDto.class);
        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Empleado Vinculación", "Filtro", "Empleado");
        return dominio_empleado + "/Filtro";
    }

    @GetMapping("/FiltroPorRol/{rol}")
    public String filtroPorRol(@PathVariable("rol") String rol, Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        Integer codproveedor = service.findByParamApi("usuarioClaro", this.getUsuarioSesion(), EmpleadoDto.class).get()
                .getCodproveedor();
        serviceVU.setapiservicename(dominio_empleado + "/vu");
        Iterable<EmpleadoVUDto> dto = serviceVU.findAll(EmpleadoVUDto.class);
        dto = this.empleadosPorProveedor((List<EmpleadoVUDto>) dto, codproveedor);
        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Empleado Vinculación", "Filtro", "Empleado");
        return dominio_empleado + "/Filtro";
    }

    @GetMapping("/Crear")
    public String crear(Model modelo, HttpServletRequest request) {

        cargarListas(modelo);
        CargarListaNuevos(modelo);
        configurar(modelo, request);
        modelo.addAttribute("usuarioAut", usuariosAutorizados());
        modelo.addAttribute("modelo", new EmpleadoVUDto());
        modelo.addAttribute("costoEmpleado", new CostoEmpleadoDto());
        CostoEmpleadoDto costo = new CostoEmpleadoDto();
        costo.setCostoPunto(0);
        costo.setCostoMes(0);
        costo.setPuntosMes(Float.valueOf(180));
        costo.setFactorPunto(Float.valueOf(1));
        costo.setHasta(new Date(System.currentTimeMillis()));
        modelo.addAttribute("costoEmpleado", costo);
        mostrarTitulosYActiveNav(modelo, "Empleado Vinculación", "Crear", "Empleado");
        return dominio_empleado + "/Empleado";
    }

    @PostMapping("/Crear")
    public String crear(EmpleadoDto dto, EmpleadoVUDto dto2, BindingResult result, Model modelo,
            HttpServletRequest request) {
        configurar(modelo, request);
        EmpleadoDto dtoResultado = service.insert(dto, EmpleadoDto.class);
        if (dtoResultado != null) {
            this.costoEmpleadoGuardar(dto, dtoResultado.getId());
        } else {
            return "redirect:/Empleado/Editar/" + dtoResultado.getId();
        }
        mostrarMensajes(modelo, "S", "C");
        return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
    }

    private CostoEmpleadoDto costoEmpleadoGuardar(EmpleadoDto costo, Integer codEmpleado) {

        String puntoMes = costo.getPuntoMes().toString().replace(',', '.');
        String factorPunto = costo.getFactorPunto().toString().replace(',', '.');
        CostoEmpleadoDto costoEmpleado = new CostoEmpleadoDto();
        costoEmpleado.setCostoPunto(costo.getCostoPunto());
        costoEmpleado.setCostoMes(costo.getCostoMes());
        costoEmpleado.setPuntosMes(Float.valueOf(puntoMes));
        costoEmpleado.setFactorPunto(Float.valueOf(factorPunto));
        costoEmpleado.setModalidad(costo.getModalidad());
        costoEmpleado.setCodEmpleado(costo.getId());
        costoEmpleado.setDesde(costo.getFechaCostoEmpleado());
        costoEmpleado.setCodEmpleado(codEmpleado);
        servicePerfil.setapiservicename(dominio_costoempleado);
        serviceCosto.insert(costoEmpleado, CostoEmpleadoDto.class);
        return costoEmpleado;
    }

    @GetMapping("/Editar/{id}")
    public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla,
            HttpServletRequest request) {
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);
        serviceVU.setapiservicename(dominio_empleado + "/vu");

        Optional<EmpleadoVUDto> dtoResultado = serviceVU.findById(id, EmpleadoVUDto.class);
        if (dtoResultado == null) {
            dtoResultado = Optional.of(new EmpleadoVUDto());
        }
        serviceEstructuraOrganizacional.setapiservicename(dominio_estructuraorganizacional);
        Iterable<EstructuraOrganizacionalDto> estructurasorganizacionales = serviceEstructuraOrganizacional
                .findAll(EstructuraOrganizacionalDto.class);
        Integer codgerenteEmpleado = codGerenteAsignado(dtoResultado.get().getCodestructuraorganizacional());
        if (codgerenteEmpleado != null) {
            if (gerenteService.getEstructuraXGerencia(codgerenteEmpleado) != null) {
                estructurasorganizacionales = gerenteService.getEstructuraXGerencia(codgerenteEmpleado);
            }
        }

        modelo.addAttribute("usuarioAut", usuariosAutorizados());
        modelo.addAttribute("modelo", dtoResultado.get());
        modelo.addAttribute("codGerenteasignado", codgerenteEmpleado);
        modelo.addAttribute("costoEmpleado", this.costoEmpleado(id));
        modelo.addAttribute("estructurasorganizacionales", estructurasorganizacionales);

        cargarListas(modelo);
        mostrarTitulosYActiveNav(modelo, "Empleado Vinculación", "Editar", "Empleado");
        return dominio_empleado + "/Empleado";
    }

    private CostoEmpleadoDto costoEmpleado(Integer codEmpleado) {

        serviceVU.setapiservicename(dominio_costoempleado);
        Iterable<CostoEmpleadoDto> costoEmpleado = serviceCosto.findAllByPathVariable(CostoEmpleadoDto.class,
                "codEmpleado/" + codEmpleado);
        costoEmpleado = ((List<CostoEmpleadoDto>) costoEmpleado).stream().filter(x -> x.getHasta() == null)
                .collect(Collectors.toList());
        CostoEmpleadoDto costoEmpleadoDto = new CostoEmpleadoDto();

        if (((ArrayList) costoEmpleado).size() != 0)
            costoEmpleadoDto = Iterables.get(costoEmpleado, 0);

        else {
            costoEmpleadoDto.setCostoPunto(0);
            costoEmpleadoDto.setCostoMes(0);
            costoEmpleadoDto.setPuntosMes(Float.valueOf(180));
            costoEmpleadoDto.setFactorPunto(Float.valueOf(1));
            costoEmpleadoDto.setDesde(new Date(System.currentTimeMillis()));
            costoEmpleadoDto.setHasta(new Date(System.currentTimeMillis()));
        }
        return costoEmpleadoDto;
    }

    @PostMapping("/Editar/{id}")
    public String editar(@PathVariable Integer id, EmpleadoDto dto, EmpleadoVUDto dtoVU, BindingResult result,
            Model modelo, HttpServletRequest request) {
        try {
            configurar(modelo, request);

            // El id del empleado real viene en la propiedad codempleado del EmpleadoVUDto
            dto.setId(dtoVU.getCodempleado());
            dto.setRol(dtoVU.getRol());
            EmpleadoDto dtoResultado = service.update(id, dto, EmpleadoDto.class);

            if (dtoResultado != null && dto.isCrearCostoEmpleado()) {
                CostoEmpleadoDto costoEmpleado = new CostoEmpleadoDto();
                costoEmpleado.setId(dto.getIdCostoEmpleado());
                costoEmpleado.setCodEmpleado(id);
                costoEmpleado.setDesde(dto.getFechaCostoEmpleado());

                // cierra el costo que tenga el empleado
                if (costoEmpleado.getId() != null) {
                    servicePerfil.setapiservicename(dominio_costoempleado);
                    serviceCosto.update(costoEmpleado.getId(), costoEmpleado, CostoEmpleadoDto.class);
                }

                // Crea el nuevo costo empleado
                this.costoEmpleadoGuardar(dto, dtoResultado.getId());
            }

            serviceVU.setapiservicename(dominio_empleado + "/vu");
            Optional<EmpleadoVUDto> dtoResultadoVU = serviceVU.findById(dtoResultado.getId(), EmpleadoVUDto.class);

            if (dtoResultadoVU != null && dtoResultadoVU.isPresent()) {
                dtoVU = dtoResultadoVU.get();
                modelo.addAttribute("modelo", dtoVU);
            } else {
                modelo.addAttribute("modelo", new EmpleadoDto());
            }
            serviceEstructuraOrganizacional.setapiservicename(dominio_estructuraorganizacional);
            Iterable<EstructuraOrganizacionalDto> estructurasorganizacionales = serviceEstructuraOrganizacional
                    .findAll(EstructuraOrganizacionalDto.class);
            Integer codgerenteEmpleado = codGerenteAsignado(dtoResultadoVU.get().getCodestructuraorganizacional());

            if (codgerenteEmpleado != null) {

                if (gerenteService.getEstructuraXGerencia(codgerenteEmpleado) != null) {
                    estructurasorganizacionales = gerenteService.getEstructuraXGerencia(codgerenteEmpleado);
                }
            }
            mostrarMensajes(modelo, "S", "U");
            modelo.addAttribute("codGerenteasignado", codgerenteEmpleado);
            modelo.addAttribute("estructurasorganizacionales", estructurasorganizacionales);
            modelo.addAttribute("usuarioAut", usuariosAutorizados());
            cargarListas(modelo);
            modelo.addAttribute("costoEmpleado", this.costoEmpleado(id));
            return dominio_empleado + "/Empleado";
        } catch (Exception e) {
            modelo.addAttribute("cl_validation_mensaje", e.getMessage());
            return dominio_empleado + "/Empleado";
        }

    }

    @ModelAttribute("roles")
    public Map<Integer, String> rolesMap() {
        Map<Integer, String> roles = new HashMap<>();
        roles.put(1, UsuarioRolEnum.ADMIN.name());
        roles.put(2, UsuarioRolEnum.RECURSO.name());
        roles.put(4, UsuarioRolEnum.ALIADO.name());
        return roles;
    }

    @ModelAttribute("modalidades")
    public Map<Integer, String> modalidadesMap() {
        Map<Integer, String> modalidades = new HashMap<>();
        modalidades.put(ModalidadEmpleadoEnum.CAPEX.getId(), ModalidadEmpleadoEnum.CAPEX.getDescripcion());
        modalidades.put(ModalidadEmpleadoEnum.OPEX.getId(), ModalidadEmpleadoEnum.OPEX.getDescripcion());
        return modalidades;
    }

    private void CargarListaNuevos(Model modelo) {
        Iterable<PersonaDto> personas = personaservice.getPersonasSinVinculacion();
        modelo.addAttribute("personas", personas);
    }

    private void cargarListas(Model modelo) {
        servicePerfil.setapiservicename(dominio_perfil);
        Iterable<PerfilDto> perfiles = servicePerfil.findAll(PerfilDto.class);
        modelo.addAttribute("perfiles", servicePerfil.findAll(PerfilDto.class));

        servicePerfilTipo.setapiservicename(dominio_perfiltipo);
        Iterable<PerfiltipoDto> perfiltipos = servicePerfilTipo.findAll(PerfiltipoDto.class);
        modelo.addAttribute("perfiltipos", servicePerfilTipo.findAll(PerfiltipoDto.class));

        servicePerfilNivel.setapiservicename(dominio_perfilnivel);
        Iterable<PerfilnivelDto> perfilniveles = servicePerfilNivel.findAll(PerfilnivelDto.class);
        modelo.addAttribute("perfilniveles", servicePerfilNivel.findAll(PerfilnivelDto.class));

        serviceLineasProducto.setapiservicename(dominio_lineasproducto);
        Iterable<LineaProductoDto> lineasproducto = serviceLineasProducto.findAll(LineaProductoDto.class);
        modelo.addAttribute("lineasproducto", serviceLineasProducto.findAll(LineaProductoDto.class));

        serviceProveedor.setapiservicename(dominio_proveedor);
        Iterable<ProveedorDto> proveedores = serviceProveedor.findAll(ProveedorDto.class);
        modelo.addAttribute("proveedores", serviceProveedor.findAll(ProveedorDto.class));

        servicePersona.setapiservicename(dominio_persona);
        Iterable<PersonaDto> personas = servicePersona.findAll(PersonaDto.class);
        modelo.addAttribute("personas", servicePersona.findAll(PersonaDto.class));

        serviceTipoDocumento.setapiservicename(dominio_tipodocumento);
        Iterable<TipoDocumentoDto> tiposdocumento = serviceTipoDocumento.findAll(TipoDocumentoDto.class);
        modelo.addAttribute("tiposdocumento", serviceTipoDocumento.findAll(TipoDocumentoDto.class));

        serviceEstructuraOrganizacional.setapiservicename(dominio_estructuraorganizacional);
        Iterable<EstructuraOrganizacionalDto> estructurasorganizacionalesGerencia = serviceEstructuraOrganizacional
                .findAll(EstructuraOrganizacionalDto.class);

        
        List<EstructuraOrganizacionalDto> listagerencias = new ArrayList<>();
        estructurasorganizacionalesGerencia.forEach(listagerencias::add);
        listagerencias = listagerencias.stream().filter(item -> item.getCodestructuratipo() == 1)
                .collect(Collectors.toList());

        modelo.addAttribute("gerencias", listagerencias);

        proyectoService.setapiservicename(dominio_proyecto);
        Iterable<ProyectoDto> proyectos = proyectoService.findAll(ProyectoDto.class);
        modelo.addAttribute("proyectos", proyectoService.findAll(ProyectoDto.class));

        boolean datos_cargados = !((Collection<?>) perfiles).isEmpty() && !((Collection<?>) perfiltipos).isEmpty()
                && !((Collection<?>) perfilniveles).isEmpty() && !((Collection<?>) proveedores).isEmpty()
                && !((Collection<?>) lineasproducto).isEmpty() && !((Collection<?>) personas).isEmpty()
                && !((Collection<?>) tiposdocumento).isEmpty()
                && !((Collection<?>) proyectos).isEmpty();
        modelo.addAttribute("datos_cargados", datos_cargados);

    }

    // Obtengo los usuarios que pueden visualizar y hacer cambios en costos en la
    // funcion vincular
    private int usuariosAutorizados() {
        int usuariosIguales = 0;

        // BUSCAMOS EL USUARIO ACTUAL DE LA SESION
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        // obtenemos el usuario actual de la sesion
        String usuarioAct = userDetails.getUsername();
        // traemos los usuarios autorizados para poderlos comparar con el usuario actual
        opcionFrontService.setapiservicename(dominio_opcionesfrontconsultargrupo);
        List<OpcionFrontDto> usuarios = (List<OpcionFrontDto>) opcionFrontService
                .findAllByPathVariable(OpcionFrontDto.class, "UsuariosVinculacion");
        // comparamos usuarios traidos con usuario actaul para validar si son iguales
        for (OpcionFrontDto usu : usuarios) {
            if (usu.getNombre().equals(usuarioAct)) {
                usuariosIguales = 1;
                break;
            } else {
                usuariosIguales = 0;
            }
        }
        return usuariosIguales;
    }

    /*
     * Función encargada de filtrar empleados por id del proveedor
     * 
     * @param list hace referencia al listado de empleados List<EmpleadoVUDto>
     * 
     * @param proveedor hace referencia al id del proveedor
     * return retorna la lista de proveedores List<EmpleadoVUDto>
     */
    private static List<EmpleadoVUDto> empleadosPorProveedor(List<EmpleadoVUDto> list, Integer proveedor) {
        return list.stream().filter(x -> x.getCodproveedor() == proveedor).collect(Collectors.toList());
    }

    @ModelAttribute("modalidad")
    public Map<Integer, String> modalidadMap() {
        Map<Integer, String> modalidades = new HashMap<>();
        modalidades.put(1, "SERVICIO");
        modalidades.put(2, "RECURSO");
        return modalidades;
    }

    public Integer codGerenteAsignado(int codEstructura) {
        EstructuraOrganizacionalDto gerente = gerenteService.getGerenteporCodigoEstructura(codEstructura);
        if (gerente.getId() == null) {
            return null;
        }
        return gerente.getId();
    }
}
