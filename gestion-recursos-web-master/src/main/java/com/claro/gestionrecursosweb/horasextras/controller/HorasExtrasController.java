package com.claro.gestionrecursosweb.horasextras.controller;
import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.horasextras.dto.HoraExtraDto;
import com.claro.gestionrecursosweb.novedad.dto.RoadMapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import com.claro.gestionrecursosweb.horasextras.dto.HoraExtraDtoParseada;

@Controller
@RequestMapping("/HorasExtras")
public class HorasExtrasController extends BaseController {

    private final String templateFiltro = "/Filtro";
    private final String editPath = "/HorasExtras";

    @Autowired
    private ApiService<HoraExtraDtoParseada, Integer> hextraservice;

    public void configurar(Model modelo, HttpServletRequest request) {
        hextraservice.setapiservicename(dominio_hora_extras);
        super.setFormularioEstandar(modelo, request, dominio_hora_extras);
    }


    @GetMapping("/Filtro")
    public String filtro(Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        Iterable<HoraExtraDtoParseada> dto = hextraservice.findAll(HoraExtraDtoParseada.class);
        modelo.addAttribute("modelo", dto);
        mostrarTitulosYActiveNav(modelo, "Horas Extras", "Filtro", "Horas Extras");
        return "horasextras" + templateFiltro;
    }


    @GetMapping("/Crear")
    public String Crear(Model modelo, HttpServletRequest request) {
        configurar(modelo, request);
        modelo.addAttribute("modelo", new HoraExtraDtoParseada());
        mostrarTitulosYActiveNav(modelo, "Horas Extras", "Crear", "Horas Extras");
        return "horasextras" + "/HorasExtras";
    }


    @PostMapping("/Crear")
    public String Crear(Model modelo, HoraExtraDto hextra, RoadMapDto roadMap, HttpServletRequest request) throws Exception {
        HoraExtraDtoParseada hextraParseada = new HoraExtraDtoParseada(hextra);
        hextraParseada.setDia(cargarDiaSemana(hextraParseada.getHini()));
        hextraservice.setapiservicename(dominio_hora_extras);
        HoraExtraDtoParseada hextracreada = hextraservice.insert(hextraParseada, HoraExtraDtoParseada.class);

        modelo.addAttribute("modelo", hextracreada);
        return redireccion("Editar", hextracreada.getId().toString(), "S", "C", request);
    }

    @GetMapping("/Editar/{id}")
    public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);
        Optional<HoraExtraDtoParseada> dtoResultado = hextraservice.findById(id, HoraExtraDtoParseada.class);
        if (dtoResultado == null)
            dtoResultado = Optional.of(new HoraExtraDtoParseada());

        modelo.addAttribute("modelo", dtoResultado.get());
        mostrarTitulosYActiveNav(modelo, "HorasExtras", "Editar", "HorasExtras");
        return "horasextras" + editPath;
    }

    @PostMapping("/Editar/{id}")
    public String postEditarHoraExtra(Model modelo, HoraExtraDto hextra, HttpServletRequest request) throws Exception {

        configurar(modelo, request);
        hextraservice.setapiservicename(dominio_hora_extras);
        HoraExtraDtoParseada hextraParseada = new HoraExtraDtoParseada(hextra);
        hextraParseada.setDia(cargarDiaSemana(hextraParseada.getHini()));
        HoraExtraDtoParseada horaextraactaulizada = hextraservice.update(hextra.getId(), hextraParseada, HoraExtraDtoParseada.class);
        mostrarMensajes(modelo, "S", "U");
        modelo.addAttribute("modelo", horaextraactaulizada);
        return "horasextras" + editPath;
    }

    public void cargarListas(Model model) {

    }

    private String cargarDiaSemana(Date fechainicial) {
        Calendar c = Calendar.getInstance();
        c.setTime(fechainicial);
        int dayofweek = c.get(Calendar.DAY_OF_WEEK);
        String dia = "";
        switch (dayofweek) {
            case 1:
                dia = "DOM";
                break;
            case 2:
                dia = "LUN";
                break;
            case 3:
                dia = "MAR";
                break;
            case 4:
                dia = "MIÉ";
                break;
            case 5:
                dia = "JUE";
                break;
            case 6:
                dia = "VIE";
                break;
            case 7:
                dia = "SÁB";
                break;
            default:
                dia = "no existe dia de la semana";
                break;
        }
        return dia;
    }

}
