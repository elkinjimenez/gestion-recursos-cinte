package com.claro.gestionrecursosweb.cambios.domain;

import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosweb.cambios.dto.EstadoCambiosDto;
import com.claro.gestionrecursosweb.cambios.dto.TiposLineaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EstadoCambiosService extends ApiService<EstadoCambiosDto, Integer> {

    public Iterable<EstadoCambiosDto> getTiposLineaCambios() {
        String url = apiurl + "/" + apiservicename;
        ResponseEntity<RespuestaCustomizada<Iterable<EstadoCambiosDto>>> responseEntity = restTemplate.exchange(url + "/estados" , HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<EstadoCambiosDto>>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        Iterable<EstadoCambiosDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, EstadoCambiosDto.class));
        return dtoRespuesta;
    }

    public EstadoCambiosDto buscarporId(int id) {
        String url = apiurl + "/" + apiservicename;
        ResponseEntity<RespuestaCustomizada<EstadoCambiosDto>> responseEntity = restTemplate.exchange(url + "/estados/" + id , HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<EstadoCambiosDto>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        EstadoCambiosDto dtoRespuesta =  mapper.convertValue(responseEntity.getBody().getData(), EstadoCambiosDto.class);
        return dtoRespuesta;
    }
}
