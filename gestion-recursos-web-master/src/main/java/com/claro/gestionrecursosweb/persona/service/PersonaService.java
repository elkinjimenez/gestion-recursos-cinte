/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.claro.gestionrecursosweb.persona.service;

import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosweb.persona.dto.PersonaDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Avila
 */
@Service
public class PersonaService extends ApiService<PersonaDto, Integer> {

    public List<PersonaDto> getPersonasSinVinculacion() {
        try {
            ResponseEntity<RespuestaCustomizada<Iterable<PersonaDto>>> responseEntity = restTemplate.exchange(apiurl + "/persona/getPersonasSinVinculacion", HttpMethod.GET, null, new ParameterizedTypeReference<RespuestaCustomizada<Iterable<PersonaDto>>>() {
            });
            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            List<PersonaDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, PersonaDto.class));
            return dtoRespuesta;
        } catch (Exception e) {
            System.out.println("com.claro.gestionrecursosweb.persona.service.PersonaService.getPersonasSinVinculacion()" + e.getMessage());
            return null;
        }
    }
}
