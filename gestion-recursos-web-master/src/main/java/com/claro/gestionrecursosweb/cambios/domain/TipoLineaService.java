package com.claro.gestionrecursosweb.cambios.domain;

import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosweb.cambios.dto.TiposLineaDto;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TipoLineaService extends ApiService<TiposLineaDto, Integer> {

    public Iterable<TiposLineaDto> getTiposLineaCambios() {
        String url = apiurl + "/" + apiservicename;
        ResponseEntity<RespuestaCustomizada<Iterable<TiposLineaDto>>> responseEntity = restTemplate.exchange(url + "/tiposLinea" , HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<TiposLineaDto>>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        Iterable<TiposLineaDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, TiposLineaDto.class));
        return dtoRespuesta;
    }

    public TiposLineaDto buscarporId(int id) {
        String url = apiurl + "/" + apiservicename;
        ResponseEntity<RespuestaCustomizada<TiposLineaDto>> responseEntity = restTemplate.exchange(url + "/tiposLinea/" + id , HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<TiposLineaDto>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        TiposLineaDto dtoRespuesta =  mapper.convertValue(responseEntity.getBody().getData(), TiposLineaDto.class);
        return dtoRespuesta;
    }
}
