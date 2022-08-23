package com.claro.gestionrecursosweb.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.claro.gestionrecursosweb.seguridad.domain.SeguridadService;
import com.claro.gestionrecursosweb.seguridad.dto.UsuarioDto;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
	private final String dominio = "seguridad";
	
	@Autowired
	private SeguridadService seguridadService;
	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
    	seguridadService.setapiservicename(dominio);
  
        String usuarionombre = authentication.getName();
        String password = authentication.getCredentials().toString();
         
        UsuarioDto usuario = new UsuarioDto();
        usuario.setUsuario(usuarionombre);
        usuario.setClave(password);        
        
        if (seguridadService.iniciarSesion(usuario) > 0) {
        	UserDetails usuarioDetalles = null;
        	try {
        		usuarioDetalles = seguridadService.loadUserByUsername(usuarionombre);
        	} catch (UsernameNotFoundException e) {
        		
        	}
        	return new UsernamePasswordAuthenticationToken(usuarioDetalles.getUsername(), usuarioDetalles.getPassword(), usuarioDetalles.getAuthorities());
        } else {
            return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
