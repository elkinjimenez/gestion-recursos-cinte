package com.claro.gestionrecursosweb.estimaciones.controller;

import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.compromisosfabrica.dto.FiltrosDto;
import com.claro.gestionrecursosweb.estimaciones.domain.IEstimacionService;
import com.claro.gestionrecursosweb.estimaciones.dto.CostoEstimacionDTO;
import com.claro.gestionrecursosweb.estimaciones.dto.EstimacionAdminDTO;
import com.claro.gestionrecursosweb.estimaciones.dto.EstimacionDto;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Estimaciones")
public class EstimacionesController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstimacionesController.class);

    private final String allPath = "/Estimaciones";
    private final String editPath = "/Estimacion";

    @Autowired
    private IEstimacionService iEstimacionService;


    private void configurar(Model modelo, HttpServletRequest request) {
        super.setFormularioEstandar(modelo, request, dominio_hallazgo);
    }

    @GetMapping("/Filtro")
    public String filtro(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request) {
        List<EstimacionDto> lista = iEstimacionService.traerTodos();
        modelo.addAttribute("estimaciones", lista);
        mostrarTitulosYActiveNav(modelo, "Estimaciones", "Filtro", "Estimaciones");
        return dominio_estimaciones + allPath;
    }

    @PostMapping("/Filtro")
    public String filtrar(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request, FiltrosDto filtrosDto) {

        List<EstimacionDto> lista = iEstimacionService.traerTodos();
        modelo.addAttribute("estimaciones", lista);

        return dominio_estimaciones + allPath;
    }

    @GetMapping("/Crear")
    public String crearEstimacion(Model modelo, @RequestParam(required = false) String cla, HttpServletRequest request, @RequestParam(name = "id", required = false) Integer idCompromiso, @RequestParam(name = "idHallazgo", required = false) Integer idHallazgo) {
        configurar(modelo, request);
        mostrarMensajes(modelo, cla);

        modelo.addAttribute("estimaciones", new EstimacionDto());

        mostrarTitulosYActiveNav(modelo, "Estimaciones", "Crear", "Estimaciones");
        return dominio_estimaciones + editPath;
    }

    @PostMapping("/Crear")
    public String postCrearEstimacion(Model modelo, EstimacionDto estimacion, HttpServletRequest request, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()){

            //con este codigo mostramos el error proporcionado en nuesra consola
            for (ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrio un error = " + error.getDefaultMessage());
            }
            return dominio_estimaciones + editPath;
        }

        iEstimacionService.guardar(estimacion);
        attributes.addFlashAttribute("msg", "Registro Exitoso");
        return "redirect:/Estimaciones/Filtro";
    }

}