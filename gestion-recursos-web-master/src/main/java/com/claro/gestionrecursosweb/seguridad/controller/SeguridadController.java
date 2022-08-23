package com.claro.gestionrecursosweb.seguridad.controller;

import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoDto;
import com.claro.gestionrecursosweb.persona.dto.PersonaDto;
import com.claro.gestionrecursosweb.seguridad.domain.MailService;
import com.claro.gestionrecursosweb.seguridad.domain.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.claro.gestionrecursosweb.base.controller.BaseController;
import com.claro.gestionrecursosweb.seguridad.dto.UsuarioDto;
import java.util.Objects;


@Controller
@RequestMapping("/Seguridad")
public class SeguridadController extends BaseController {

    private final String dominio = "seguridad";

    @Autowired
    private MailService mailService;


    @Autowired
    private ApiService<PersonaDto, Integer> servicePersona;

    @Autowired
    private SeguridadService serviceUsuario;

    
    public void ConfigurarService() {

    }

    @GetMapping("/Ingresar")
    public String ingresar(@RequestParam(value = "error", required = false) String error, Model model) {
        ConfigurarService();
        model.addAttribute("modelo", new UsuarioDto());
        if (Objects.nonNull(error)) {
            model.addAttribute("error", "Usuario y/o contraseña incorrectos");
        }
        return dominio + "/Ingresar";
    }


    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("usuario") String nombreUsuario, Model model) {

        model.addAttribute("modelo", new UsuarioDto());
        servicePersona.setapiservicename(dominio_seguridad);
        UsuarioDto usuario = serviceUsuario.findByUsuario(nombreUsuario);

        if (usuario != null) {
            String clave = this.passwordRandom();
            String claveSha256 = this.encode(clave);
            usuario.setClave(claveSha256);

            String message = "Sus credenciales son: " +
                    "Usuario: " + nombreUsuario +
                    " Clave: " + clave;
            serviceUsuario.insert(nombreUsuario + "/" + claveSha256);

            String correo = serviceUsuario.personaFindById(usuario.getCodpersona()).getCorreo();

            if (correo != null) {
                String envio = mailService.sendMail(correoCorporativo, correo, "Restablecer Contraseña", message);
                if (envio == "ok") {
                    model.addAttribute("exito", "Correo enviado, favor revisar el correo: " + correo);
                } else {
                    model.addAttribute("error", "Contactarse con soporte para actualizar el correo " + correo);
                }
            } else {
                model.addAttribute("error", "No se encontro correo para el usuario: " + nombreUsuario + " contactarse con soporte");
            }
        } else {
            model.addAttribute("error", "El usuario: " + nombreUsuario + " no encontrado, intente nuevamente (minúsculas o mayúsculas)");
        }
        return dominio + "/Ingresar";
    }

    @PostMapping("/cambiarPassword/{nombreUsuario}/{password}")
    public ResponseEntity<?> cambiarContraseña(@PathVariable("nombreUsuario") String nombreUsuario, @PathVariable("password") String password) {
        servicePersona.setapiservicename(dominio_seguridad);
        UsuarioDto usuario = serviceUsuario.findByUsuario(nombreUsuario);
        UsuarioDto respuesta = null;
        if (usuario != null) {
            String clave = this.passwordRandom();
            String claveSha256 = this.encode(password);
            usuario.setClave(claveSha256);
            respuesta = serviceUsuario.insert(nombreUsuario + "/" + claveSha256);
        }
        return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
    }
}