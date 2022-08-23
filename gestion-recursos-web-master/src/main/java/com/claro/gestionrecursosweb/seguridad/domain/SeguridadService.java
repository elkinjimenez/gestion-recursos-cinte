package com.claro.gestionrecursosweb.seguridad.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.claro.gestionrecursosweb.base.model.RespuestaBase;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosweb.persona.dto.PersonaDto;
import com.claro.gestionrecursosweb.utils.StringUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.seguridad.dto.UsuarioDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class SeguridadService extends ApiService<UsuarioDto, Integer> implements UserDetailsService {

	@Value("${claro.dominio.seguridad.nombre}")
	private String dominio;

	@Autowired
	private PasswordEncoder passwordEncoder;
	/**
	 * El objeto usuario ya debe traer la contraseña crifrada
	 * @param dto
	 * @return Retorna el Id del usuario
	 */
	public Integer iniciarSesion(UsuarioDto dto) {
		ResponseEntity<Integer> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename, HttpMethod.POST, new HttpEntity<UsuarioDto>(dto), new ParameterizedTypeReference<Integer>() {});
		Integer respuesta = new ObjectMapper().convertValue(responseEntity.getBody(), Integer.class);
		return respuesta;
	}

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		apiservicename = dominio;
		ResponseEntity<UsuarioDto> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "?usuario=" + usuario, HttpMethod.GET, null, new ParameterizedTypeReference<UsuarioDto>() {});
		UsuarioDto respuesta = new ObjectMapper().convertValue(responseEntity.getBody(), UsuarioDto.class);
		List<GrantedAuthority> rolesDelUsuario = new ArrayList<GrantedAuthority>();
		if (Objects.nonNull(respuesta)) {
			responseEntity.getBody().getlUsuarioRoles().stream()
					.forEach( r -> {
								GrantedAuthority usuarioRoles = new SimpleGrantedAuthority(UsuarioRolEnum.valueOf(r.getUsuarioRolEntity().getId()).toString());
								rolesDelUsuario.add(usuarioRoles);
							});
		} else {
			throw new UsernameNotFoundException("Usuario y/o contraseña incorrectos");
		}

		UserDetails usuarioAutenticado = (UserDetails)new User(respuesta.getUsuario(), respuesta.getClave(), rolesDelUsuario);
		return usuarioAutenticado;
	}
	
	public UsuarioDto findByUsuario(String usuario) {
		apiservicename = dominio;
		ResponseEntity<UsuarioDto> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "?usuario=" + usuario, HttpMethod.GET, null, new ParameterizedTypeReference<UsuarioDto>() {});
		UsuarioDto respuesta = new ObjectMapper().convertValue(responseEntity.getBody(), UsuarioDto.class);
		return respuesta;
	}

	public PersonaDto personaFindById(Integer id) {
		ResponseEntity<RespuestaCustomizada<PersonaDto>> responseEntity = restTemplate.exchange(apiurl + "/persona/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<RespuestaCustomizada<PersonaDto>>() {});
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(StringUtils.dataFormat);

		PersonaDto dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), PersonaDto.class);

		if (dtoRespuesta == null)
			return null;
		else
			return dtoRespuesta;
	}

	public UsuarioDto insert(String parametros) {
		Gson gson = new Gson();
		ResponseEntity<RespuestaCustomizada<UsuarioDto>> responseEntity = null;
		RespuestaCustomizada<UsuarioDto> dtoRespuesta = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			responseEntity = restTemplate.exchange(apiurl + "/seguridad/actualizarClave/" + parametros, HttpMethod.PUT,null, new ParameterizedTypeReference<RespuestaCustomizada<UsuarioDto>>() {});
			dtoRespuesta = mapper.convertValue(responseEntity.getBody(), RespuestaCustomizada.class);
			this.codigoHttp = dtoRespuesta.getCodigoEstatus();
			this.mensaje = dtoRespuesta.getMensaje();
		} catch (HttpClientErrorException ex) {
			RespuestaBase respuestaBase = gson.fromJson(ex.getResponseBodyAsString(), RespuestaBase.class);
			this.codigoHttp = respuestaBase.getCodigoEstatus();
			this.mensaje = respuestaBase.getMensaje();
			return null;
		}
		UsuarioDto dtoRespuesta2 = mapper.convertValue(dtoRespuesta.getData(), UsuarioDto.class);
		return dtoRespuesta2;
	}

}