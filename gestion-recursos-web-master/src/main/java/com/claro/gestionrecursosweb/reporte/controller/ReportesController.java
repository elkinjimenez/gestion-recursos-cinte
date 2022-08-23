package com.claro.gestionrecursosweb.reporte.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import com.claro.gestionrecursosweb.reporte.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.dto.FiltroFechas;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import com.claro.gestionrecursosweb.proveedor.dto.ProveedorDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaEstadoDto;
import com.claro.gestionrecursosweb.tarea.dto.TareaTipoDto;

@Controller
@RequestMapping("/Reportes")
public class ReportesController extends BaseController {

    @Autowired
    private ApiService<TareaTipoDto, Integer> serviceTareaTipo;
    @Autowired
    private ApiService<TareaEstadoDto, Integer> serviceTareaEstado;
    @Autowired
    private ApiService<EmpleadoVUDto, Integer> serviceEmpleado;
    @Autowired
    private ApiService<ProyectoDto, Integer> serviceProyecto;
    @Autowired
    private ApiService<ProveedorDto, Integer> serviceProveedor;
    @Autowired
    private ApiService<ReporteTareaVUDto, Integer> service;
    @Autowired
    private ApiService<ReporteProyectoTiempo, Integer> serviceReporteProTmp;
    @Autowired
    private ApiService<ReporteTiempoDto, Integer> serviceReporteTiempo;
    @Autowired
    private ApiService<ReporteProrrateoPersonaDto, Integer> prorrateoReportePorPersona;
    @Autowired
    private ApiService<ReporteProrrateoGeneralDto, Integer> prorrateoReporteGeneral;
    @Autowired
    private ApiService<ReporteRecursoConProveedorDto, Integer> recursoConProveedor;
    @Autowired
    private ApiService<ReporteProrrateoPresupuestoDto, Integer> prorrateoVSPresupuesto;
    @Autowired
    private ApiService<ReporteListadoSolicitudesDto, Integer> listaSolicitudes;
    @Autowired
    private ApiService<ReporteOpexDto, Integer> opex;
    @Autowired
    private ApiService<ReporteRhPagosDto, Integer> pagos;

    @Autowired
    private ApiService<ReporteEmpleadosDto, Integer> empleados;
    @Autowired
    private ApiService<ReporteInfoCompromisosDto, Integer> compromisos;

    @GetMapping
    public String reportes(Model modelo) {
        cargarListas(modelo);

        ReporteFiltroDto dto = new ReporteFiltroDto();
        dto.setCodreporte(1);

        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(Calendar.DAY_OF_MONTH, 1);
        fechaFin.set(Calendar.DAY_OF_MONTH, fechaFin.getActualMaximum(Calendar.DATE));

        dto.setFechainicio(fechaInicio.getTime());
        dto.setFechafin(fechaFin.getTime());
        dto.setCodproyecto(0);
        dto.setCodtareatipo(0);

        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Reportes", "Reporte", "Reportes");
        return dominio_reporte + "/Reportes";
    }

    @PostMapping
    public String reportes(Model modelo, ReporteFiltroDto dto) {
        cargarListas(modelo);
        modelo.addAttribute("modelo", dto);

        if (dto.getCodreporte() == 1) { // Tareas
            service.setapiservicename(dominio_reportetarea);
            Iterable<ReporteTareaVUDto> resultado = service.findAllWithParams(ReporteTareaVUDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 3) { // Tiempos Reportados (Resumen)
            System.out.println("************************ Resumen");
            serviceReporteTiempo.setapiservicename(dominio_reportetiempo + "/resumen");
            Iterable<ReporteTiempoDto> resultado = serviceReporteTiempo.findAllWithParams(ReporteTiempoDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 4) { // Tiempos Reportados (Detalle)
            serviceReporteTiempo.setapiservicename(dominio_reportetiempo + "/detalle");
            Iterable<ReporteTiempoDto> resultado = serviceReporteTiempo.findAllWithParams(ReporteTiempoDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 5) {
            serviceReporteTiempo.setapiservicename(dominio_reporte + "/prorrateo");
            Iterable<ReporteProrrateoPersonaDto> resultado = prorrateoReportePorPersona.findAllWithParams(ReporteProrrateoPersonaDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 6) {
            serviceReporteTiempo.setapiservicename(dominio_reporte + "/prorrateo/general");
            Iterable<ReporteProrrateoGeneralDto> resultado = prorrateoReporteGeneral.findAllWithParams(ReporteProrrateoGeneralDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 7) {
            serviceReporteTiempo.setapiservicename(dominio_reporte + "/proveedor");
            Iterable<ReporteRecursoConProveedorDto> resultado = recursoConProveedor.findAllWithParams(ReporteRecursoConProveedorDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 8) {
            serviceReporteTiempo.setapiservicename(dominio_reporte + "/prorrateovspresupuesto");
            Iterable<ReporteProrrateoPresupuestoDto> resultado = prorrateoVSPresupuesto.findAllWithParams(ReporteProrrateoPresupuestoDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 9) {
            serviceReporteTiempo.setapiservicename(dominio_reporte + "/solicitudes");
            Iterable<ReporteListadoSolicitudesDto> resultado = listaSolicitudes.findAllWithParams(ReporteListadoSolicitudesDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 10) {
            serviceReporteTiempo.setapiservicename(dominio_reporte + "/opex");
            Iterable<ReporteOpexDto> resultado = opex.findAllWithParams(ReporteOpexDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 2) { // Facturación
            serviceReporteProTmp.setapiservicename(dominio_reporteproyectotiempos);

            FiltroFechas filtro = new FiltroFechas();
            filtro.setFechaInicio(dto.getFechainicio());
            filtro.setFechaFin(dto.getFechafin());
            Iterable<ReporteProyectoTiempo> resultado = serviceReporteProTmp.findAllPost(ReporteProyectoTiempo.class, filtro);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 12) {
            serviceReporteTiempo.setapiservicename(dominio_reporte + "/pagos");
            Iterable<ReporteRhPagosDto> resultado = pagos.findAllWithParams(ReporteRhPagosDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 13) {
            serviceReporteTiempo.setapiservicename(dominio_reporte + "/empleados");
            Iterable<ReporteEmpleadosDto> resultado = empleados.findAllWithParams(ReporteEmpleadosDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        } else if (dto.getCodreporte() == 14) {
            serviceReporteTiempo.setapiservicename(dominio_reporte + "/compromisos");
            Iterable<ReporteInfoCompromisosDto> resultado = compromisos.findAllWithParams(ReporteInfoCompromisosDto.class, dto);
            modelo.addAttribute("resultado", resultado);
        }

        return dominio_reporte + "/Reportes";
    }

    public void cargarListas(Model modelo) {
        try {
            serviceTareaTipo.setapiservicename(dominio_tareatipo);
            List<TareaTipoDto> tareaTipos = (List<TareaTipoDto>) serviceTareaTipo.findAll(TareaTipoDto.class);
            modelo.addAttribute("tareaTipos", serviceTareaTipo.findAll(TareaTipoDto.class));

            serviceTareaEstado.setapiservicename(dominio_tareaestado);
            List<TareaEstadoDto> tareaEstados = (List<TareaEstadoDto>) serviceTareaEstado.findAll(TareaEstadoDto.class);
            modelo.addAttribute("tareaEstados", serviceTareaEstado.findAll(TareaEstadoDto.class));

            serviceEmpleado.setapiservicename(dominio_empleado + "/vu");
            List<EmpleadoVUDto> empleados = (List<EmpleadoVUDto>) serviceEmpleado.findAll(EmpleadoVUDto.class);
            modelo.addAttribute("empleados", serviceEmpleado.findAll(EmpleadoVUDto.class));

            serviceProyecto.setapiservicename(dominio_proyecto);
            List<ProyectoDto> proyectos = (List<ProyectoDto>) serviceProyecto.findAll(ProyectoDto.class);
            modelo.addAttribute("proyectos", serviceProyecto.findAll(ProyectoDto.class));

            serviceProveedor.setapiservicename(dominio_proveedor);
            List<ProveedorDto> proveedores = (List<ProveedorDto>) serviceProveedor.findAll(ProveedorDto.class);
            modelo.addAttribute("proveedores", serviceProveedor.findAll(ProveedorDto.class));

            boolean datos_cargados = !((Collection<?>) tareaTipos).isEmpty() && !((Collection<?>) tareaEstados).isEmpty()
                    && !((Collection<?>) empleados).isEmpty() && !((Collection<?>) proyectos).isEmpty()
                    && !((Collection<?>) proveedores).isEmpty() && !((Collection<?>) proyectos).isEmpty();
            modelo.addAttribute("datos_cargados", datos_cargados);
        } catch (NullPointerException ex) {
            modelo.addAttribute("datos_cargados", false);
        }
    }
}
