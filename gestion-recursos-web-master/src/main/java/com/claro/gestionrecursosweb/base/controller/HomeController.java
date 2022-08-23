package com.claro.gestionrecursosweb.base.controller;


import com.claro.gestionrecursosweb.seguridad.domain.SeguridadService;
import com.claro.gestionrecursosweb.seguridad.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

	@Autowired
	private SeguridadService serviceUsuario;

	public void ConfigurarService() {

	}

	@GetMapping("/")
	public String inicio(Model model) {
		ConfigurarService();
		mostrarTitulosYActiveNav(model, "Versionamiento", "versiones", "/");
		return "home/Inicio";
	}

	@GetMapping("/ajustes")
	public String Ajuste(Model model) {
		ConfigurarService();
		mostrarTitulosYActiveNav(model, "Ajustes", "configuración", "/");
		return "home/Ajuste";
	}

	@PostMapping("/cambioClave")
	public String actualizarClave(Model modelo, @RequestBody String clave, HttpServletRequest request, BindingResult result, RedirectAttributes attributes) {
		String claveSha256 = this.encode(clave.split("=")[1]);
		UsuarioDto usuario = serviceUsuario.insert(getUsuarioSesion()+"/"+claveSha256);
		if (usuario != null){
			mostrarMensajes(modelo, "S", "U");
		}else{
			modelo.addAttribute("cl_validacion_mensaje_tipo", "alert-danger");
			modelo.addAttribute("cl_validation_mensaje", "No fue posible actualizar la contraseña, contactarse con soporte");
		}

		return "home/Ajuste";
	}

	@GetMapping("error")
	public String error(Model model) {
		ConfigurarService();
		mostrarTitulosYActiveNav(model, "Error", "Error", "/");
		return "home/Error";
	}

}
