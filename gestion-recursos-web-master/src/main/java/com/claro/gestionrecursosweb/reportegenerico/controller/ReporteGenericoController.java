package com.claro.gestionrecursosweb.reportegenerico.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoDto;
import com.claro.gestionrecursosweb.proveedor.dto.ProveedorDto;
import com.claro.gestionrecursosweb.reportegenerico.dto.*;
import com.claro.gestionrecursosweb.seguridad.domain.UsuarioRolEnum;
import com.claro.gestionrecursosweb.seguridad.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.estructura.dto.EstructuraOrganizacionalDto;

@Controller
@RequestMapping("/ReportesGenericos")
public class ReporteGenericoController extends BaseController {
    private final String viewEndPoint = "/ReportesGenericos";

    @Autowired
    private ApiService<EstructuraOrganizacionalDto, Integer> estructuraOrganizacionalService;

    @Autowired
    private ApiService<UserViewDbDto, String> serviceUserViewDb;

    @Autowired
    private ApiService<ViewInformationDto, String> serviceViewInformation;

    @Autowired
    private ApiService<EmpleadoDto, Integer> serviceEmpleado;

    @Autowired
    private ApiService<ProveedorDto, Integer> serviceProveedor;

    @Autowired
    private ApiService<UsuarioDto, Integer> serviceUsuario;

    @Autowired
    private ApiService<RangoDto, Integer> serviceRango;

    @Value("${claro.dominio.seguridad.nombre}")
    private String dominio_seguridad;

    @GetMapping
    public String reportesGenericos(Model modelo) {
        cargarListas(modelo);

        ReporteFiltroDto dto = new ReporteFiltroDto();
        serviceRango.setapiservicename("reportegenerico");
        RangoDto rangoDto = serviceRango.findByUrl("rango", RangoDto.class).get();
        if (rangoDto != null) {
            dto.setFechainicio(rangoDto.getFechainicio());
            dto.setFechafin(rangoDto.getFechafin());
            dto.setCodEstructura(rangoDto.getCodEstructura());
        }


        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Reportes Genericos", "Reporte Generico", "ReportesGenericos");
        return dominio_reportegenerico + viewEndPoint;
    }

    @PostMapping
    public String reportesGenericos(Model modelo, ReporteFiltroDto dto) {
        try {
            cargarListas(modelo);
            modelo.addAttribute("modelo", dto);
            ViewInformationDto informacionVista = getInformacionVista(dto);
            modelo.addAttribute("columnasVista", informacionVista.getColumns());
            serviceEmpleado.setapiservicename(dominio_empleado);
            Optional<EmpleadoDto> empleadoDto = serviceEmpleado.findByParamApi("usuarioClaro", this.getUsuarioSesion(), EmpleadoDto.class);
            serviceUsuario.setapiservicename(dominio_seguridad);
            Optional<UsuarioDto> usuarioRol = serviceUsuario.findByParamApi("usuario", empleadoDto.get().getCodpersona().toString(), UsuarioDto.class);
            serviceProveedor.setapiservicename(dominio_proveedor);
            String proveedorFiltro = serviceProveedor.findById(empleadoDto.get().getCodproveedor(), ProveedorDto.class).get().getNombre();

            if (usuarioRol.get().getCodusuariorol() == UsuarioRolEnum.ALIADO.getValue())
                informacionVista.setDataRows(this.reporteGenericoPorProveedor(informacionVista, proveedorFiltro));

            modelo.addAttribute("filasVista", informacionVista.getDataRows());
        } catch (IllegalArgumentException i) {
            System.out.println("error: " + i.getMessage());
        }

        return dominio_reportegenerico + viewEndPoint;
    }

    private List<Object[]> reporteGenericoPorProveedor(ViewInformationDto informacionVista, String proveedorFiltro) {
        int indexProveedor = -1;
        List<Object[]> newDataRows = new ArrayList<>();
        String filtro = "PROVEEDOR";

        Iterable<ColumnInformationDbDto> infoColumnas = informacionVista.getColumns();
        for (int index = 0; index < ((ArrayList) infoColumnas).size(); index++) {
            String name = ((ArrayList<ColumnInformationDbDto>) informacionVista.getColumns()).get(index).getName();
            if (name.equals(filtro))
                indexProveedor = index;
        }

        if (indexProveedor > -1) {
            List<Object[]> dataRows = informacionVista.getDataRows();
            for (int filas = 0; filas < ((ArrayList) dataRows).size(); filas++) {
                String proveedor = dataRows.get(filas)[indexProveedor].toString();
                if (proveedor.equals(proveedorFiltro))
                    newDataRows.add(dataRows.get(filas));
            }
        }
        return newDataRows;
    }

    public ViewInformationDto getInformacionVista(ReporteFiltroDto filtros) {
        serviceViewInformation.setapiservicename(dominio_reportegenericoinformacionvista);
        return serviceViewInformation.findByPayload(filtros, ViewInformationDto.class);
    }

    public void cargarListas(Model modelo) {
        serviceUserViewDb.setapiservicename(dominio_reportegenericovistas);
        List<UserViewDbDto> vistas = (List<UserViewDbDto>) serviceUserViewDb.findAll(UserViewDbDto.class);
        modelo.addAttribute("vistas", serviceUserViewDb.findAll(UserViewDbDto.class));

        estructuraOrganizacionalService.setapiservicename(dominio_estructuraorganizacional);
        List<EstructuraOrganizacionalDto> estructuras = (List<EstructuraOrganizacionalDto>) estructuraOrganizacionalService.findAll(EstructuraOrganizacionalDto.class);
        modelo.addAttribute("estructuras", estructuras);

        boolean datos_cargados = ((Collection<?>) vistas).size() > 0;
        modelo.addAttribute("datos_cargados", datos_cargados);
    }

    @ModelAttribute("viewEndPoint")
    public String getViewEndPoint() {
        return viewEndPoint;
    }

}
