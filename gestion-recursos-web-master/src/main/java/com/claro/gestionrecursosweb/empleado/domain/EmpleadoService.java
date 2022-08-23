package com.claro.gestionrecursosweb.empleado.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.claro.gestionrecursosweb.empleado.dto.EmpleadoVUDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmpleadoService extends ApiService<EmpleadoDto, Integer> {

    public Optional<EmpleadoDto> findActivoByCodPersona(Integer codpersona) {
        ResponseEntity<RespuestaCustomizada<EmpleadoDto>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/activo/codpersona/" + codpersona, HttpMethod.GET, null, new ParameterizedTypeReference<RespuestaCustomizada<EmpleadoDto>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        EmpleadoDto dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), EmpleadoDto.class);
        if (dtoRespuesta == null) {
            return null;
        } else {
            return Optional.of(dtoRespuesta);
        }
    }

    public List<EmpleadoVUDto> findAllGerentes() {
        ResponseEntity<List<EmpleadoVUDto>> responseEntity = restTemplate.exchange(apiurl + "/empleado/vu/gerente", HttpMethod.GET, null, new ParameterizedTypeReference<List<EmpleadoVUDto>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        List<EmpleadoVUDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, EmpleadoVUDto.class));
        return dtoRespuesta;
    }
    
}
