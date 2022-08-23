package com.claro.gestionrecursosweb.base.controller;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.claro.gestionrecursosweb.estructura.dto.EstructuraOrganizacionalDto;
import com.google.common.hash.Hashing;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.claro.gestionrecursosweb.seguridad.domain.UsuarioRolEnum;

@Controller
public class BaseController {

    @Value("${claro.api.urlbase}")
    protected String apiurl;

    @Value("${claro.dominio.persona.nombre}")
    protected String dominio_persona;

    @Value("${claro.dominio.horaextras.nombre}")
    protected String dominio_hora_extras;

    @Value("${claro.dominio.tipodocumento.nombre}")
    protected String dominio_tipodocumento;
    @Value("${claro.dominio.empleado.nombre}")
    protected String dominio_empleado;
    @Value("${claro.dominio.costoempleado.nombre}")
    protected String dominio_costoempleado;
    @Value("${claro.dominio.gerente.nombre}")
    protected String dominio_empleado_gerente;
    @Value("${claro.dominio.estructuraorganizacional.nombre}")
    protected String dominio_estructuraorganizacional;
    @Value("${claro.dominio.estructuraorganizacional.filtrada.nombre}")
    protected String dominio_estructuraorganizacionalFiltrada;
    @Value("${claro.dominio.perfil.nombre}")
    protected String dominio_perfil;
    @Value("${claro.dominio.perfil-costo.nombre}")
    protected String dominio_perfil_costo;
    @Value("${claro.dominio.perfiltipo.nombre}")
    protected String dominio_perfiltipo;
    @Value("${claro.dominio.perfilnivel.nombre}")
    protected String dominio_perfilnivel;
    @Value("${claro.dominio.lineasproducto.nombre}")
    protected String dominio_lineasproducto;
    @Value("${claro.dominio.proveedor.nombre}")
    protected String dominio_proveedor;
    @Value("${claro.dominio.empleadocontrol.nombre}")
    protected String dominio_empleadocontrol;
    @Value("${claro.dominio.proyecto.nombre}")
    protected String dominio_proyecto;
    @Value("${claro.dominio.proyectotipo.nombre}")
    protected String dominio_proyectotipo;
    @Value("${claro.dominio.presupuesto.nombre}")
    protected String dominio_presupuesto;
    @Value("${claro.dominio.tarea.nombre}")
    protected String dominio_tarea;
    @Value("${claro.dominio.tareaactividad.nombre}")
    protected String dominio_tareaactividad;
    @Value("${claro.dominio.tareatipo.nombre}")
    protected String dominio_tareatipo;
    @Value("${claro.dominio.tareaestado.nombre}")
    protected String dominio_tareaestado;
    @Value("${claro.dominio.tareaestadotipo.nombre}")
    protected String dominio_tareaestadotipo;
    @Value("${claro.dominio.vistausuario.nombre}")
    protected String dominio_vistausuario;
    @Value("${claro.dominio.tercero.nombre}")
    protected String dominio_tercero;
    @Value("${claro.dominio.reporte.nombre}")
    protected String dominio_reporte;
    @Value("${claro.dominio.reportetarea.nombre}")
    protected String dominio_reportetarea;
    @Value("${claro.dominio.reportetiempo.nombre}")
    protected String dominio_reportetiempo;
    @Value("${claro.dominio.reporteproyectotiempos.nombre}")
    protected String dominio_reporteproyectotiempos;
    @Value("${claro.dominio.compromisosfabrica.nombre}")
    protected String dominio_compromisos_fabrica;
    @Value("${claro.dominio.reportegenerico.nombre}")
    protected String dominio_reportegenerico;
    @Value("${claro.dominio.reportegenericovistas.nombre}")
    protected String dominio_reportegenericovistas;
    @Value("${claro.dominio.reportegenericoinformacionvista.nombre}")
    protected String dominio_reportegenericoinformacionvista;
    @Value("${claro.dominio.opcionesfrontconsultargrupo.nombre}")
    protected String dominio_opcionesfrontconsultargrupo;

    @Value("${claro.dominio.opcionesfrontconsultarGrupoporUsuario.nombre}")
    protected String dominio_opcionesfrontconsultarGrupoxUsuario;
    @Value("${claro.dominio.novedad.nombre}")
    protected String dominio_novedad;
    @Value("${claro.dominio.novedad.tipos.nombre}")
    protected String dominio_novedad_tipos;
    @Value("${claro.dominio.novedad.roadMap.nombre}")
    protected String dominio_roadMap;
    @Value("${claro.dominio.novedad.tipoRoadMap.nombre}")
    protected String dominio_tipoRoadMap;
    @Value("${claro.dominio.novedad.estadoEntrega.nombre}")
    protected String dominio_estadoEntrega;
    @Value("${claro.dominio.novedad.estadoRoadMap.nombre}")
    protected String dominio_estadoRoadMap;

    //hallazgos
    @Value("${claro.dominio.hallazgo.nombre}")
    protected String dominio_hallazgo;
    @Value("${claro.dominio.hallazgo.respuesta}")
    protected String dominio_hallazgo_respuesta;
    @Value("${claro.dominio.hallazgo.vista}")
    protected String dominio_hallazgo_vista;
    @Value("${claro.dominio.hallazgo.tipos.nombre}")
    protected String dominio_hallazgo_tipos;
    @Value("${claro.dominio.hallazgo.proyecto}")
    protected String dominio_hallazgo_proyectos;
    @Value("${claro.dominio.hallazgo.estructuraorganizacional}")
    protected String dominio_hallazgo_estructuraorganizacional;
    @Value("${claro.dominio.hallazgo.opcionesfrontconsultargrupo}")
    protected String dominio_hallazgo_opcionesfrontconsultargrupo;
    @Value("${claro.dominio.seguridad.nombre}")
    protected String dominio_seguridad;
    @Value("${claro.dominio.cambios.nombre}")
    protected String dominio_cambios;


    // Correo corporativo
    @Value("${spring.mail.username}")
    protected String correoCorporativo;

    //Estimaciones
    @Value("${claro.dominio.estimaciones.nombre}")
    protected String dominio_estimaciones;
    @Value("${claro.dominio.estimaciones.admin.nombre}")
    protected String dominio_estimacionesAdmin;


    /**
     * Método para presentación de mensajes estándar
     *
     * @param modelo Modelo de la vista para agregar atributos estándar de mensajes
     * @param tipo   Tipo de mensaje: E(Error),S(Exito),W(Advertencia),I(Informativo)
     * @param accion Accion de CRUD realizada: C(Crear),R(Consultar),U(Actualizar),D(Eliminar)
     */
    protected void mostrarMensajes(Model modelo, String tipo, String accion) {
        String mensajeTipo = "alert-info";
        String mensaje = "";
        switch (tipo) {
            case "E":
                mensajeTipo = "alert-danger";
                break;
            case "S":
                mensajeTipo = "alert-success";
                break;
            case "W":
                mensajeTipo = "alert-warning";
                break;
            case "I":
                mensajeTipo = "alert-info";
                break;
        }

        switch (accion) {
            case "C":
                mensaje = "La información se guardó correctamente";
                break;
            case "R":
                mensaje = "La información se consultó correctamente";
                break;
            case "U":
                mensaje = "La información se actualizó correctamente";
                break;
            case "D":
                if (tipo.equalsIgnoreCase("E"))
                    mensaje = "No fue posible eliminar la información. Verifique que no tenga información relacionada";
                else
                    mensaje = "La información se eliminó correctamente";
        }

        modelo.addAttribute("cl_validacion_mensaje_tipo", mensajeTipo);
        modelo.addAttribute("cl_validation_mensaje", mensaje);
    }

    /**
     * @param cla Parametro estandar para presentación de mensajes estandarizados
     */
    protected void mostrarMensajes(Model modelo, String cla) {
        if (cla != null && cla != "") {
            if (cla.length() == 2) {
                mostrarMensajes(modelo, Character.toString(cla.charAt(0)), Character.toString(cla.charAt(1)));
            }
        }
    }

    /**
     * Método para redireccionar entre acciones crud de un controlador
     *
     * @param tipo   Tipo de mensaje: E(Error),S(Exito),W(Advertencia),I(Informativo)
     * @param accion Accion de CRUD realizada: C(Crear),R(Consultar),U(Actualizar),D(Eliminar)
     */
    protected String redireccion(String url, String id, String tipo, String accion, HttpServletRequest request) {
        String parametrosEstandar = "";
        if (request.getParameter("clm") != null)
            parametrosEstandar += "&clm=" + request.getParameter("clm");

        return "redirect:" + url + (id != null ? "/" + id : "") + "?cla=" + tipo + accion + parametrosEstandar;
    }

    /**
     * Método para redireccionar entre acciones crud de un controlador
     *
     * @param tipo   Tipo de mensaje: E(Error),S(Exito),W(Advertencia),I(Informativo)
     * @param accion Accion de CRUD realizada: C(Crear),R(Consultar),U(Actualizar),D(Eliminar)
     */
    protected String redireccion(String url, String tipo, String accion, HttpServletRequest request) {
        String parametrosEstandar = "";
        if (request.getParameter("clm") != null)
            parametrosEstandar += "&clm=" + request.getParameter("clm");

        return "redirect:" + url + "?cla=" + tipo + accion + parametrosEstandar;
    }

    protected void setFormularioEstandar(Model modelo, HttpServletRequest request, String dominio) {
        String parametrosEstandar = "?";
        if (request.getParameter("clm") != null)
            parametrosEstandar += "clm=" + request.getParameter("clm");

        String[] seccionesUrl = request.getRequestURI().split("/");
        String accionFormulario = seccionesUrl[seccionesUrl.length - 1];
        String idEditar = "";
        dominio = StringUtils.capitalize(dominio);
        if (!accionFormulario.equals("Filtro") && !accionFormulario.equals("Crear") && !accionFormulario.equals("Editar")) {
            if (seccionesUrl.length > 2) {
                idEditar = accionFormulario;
                accionFormulario = seccionesUrl[seccionesUrl.length - 2];
            }
        }

        modelo.addAttribute("cl_formulario_dominio", dominio);
        modelo.addAttribute("cl_formulario_parametros", parametrosEstandar);
        modelo.addAttribute("cl_formulario_accion", accionFormulario);
        modelo.addAttribute("cl_formulario_ideditar", idEditar);
        modelo.addAttribute("cl_formulario_url", "/" + dominio + "/" + accionFormulario + (accionFormulario.equals("Editar") ? "/" + idEditar : "") + parametrosEstandar);
    }

    /**
     * Para dar el formato de fecha cuando se envía una fecha en un modelo desde el controlador hacia la interfaz gráfica
     *
     * @param binder
     */
    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    protected boolean hasRole(UsuarioRolEnum role) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return false;

        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.toString().equals(auth.getAuthority()))
                return true;
        }

        return false;
    }

    protected String getUsuarioSesion() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * funcion encargada de pintar los titulos en la plantilla y activar el item en el nav
     *
     * @param modelo
     * @param titulo
     * @param Subtitulo
     * @param itemNav
     */
    protected void mostrarTitulosYActiveNav(Model modelo, String titulo, String Subtitulo, String itemNav) {
        modelo.addAttribute("titulo", titulo);
        modelo.addAttribute("subtitulo", Subtitulo);
        modelo.addAttribute("itemNav", itemNav);
    }

    /**
     * Metodo encargado de crear cadena de texto de 10 caracteres ramdom
     *
     * @return 10 caracteres random
     */
    protected String passwordRandom() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    /**
     * Metodo encargado de encriptar un texto
     *
     * @param rawPassword texto a encriptar
     * @return string de sha256
     */
    protected String encode(String rawPassword) {
        return Hashing.sha256().hashString(rawPassword, StandardCharsets.UTF_8).toString();
    }

    /*
     * Función encargada de filtrar estructura organizacional por Codestructuratipo == 2
     * @param list hace referencia al listado de EstructuraOrganizacionalDto
     * return retorna listado de EstructuraOrganizacionalDto filtrados
     */
    protected List<EstructuraOrganizacionalDto> estructuraOrga(List<EstructuraOrganizacionalDto> list) {
        return list.stream().filter(x -> x.getCodestructuratipo() == 2).collect(Collectors.toList());
    }
}