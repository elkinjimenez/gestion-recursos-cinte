package com.claro.gestionrecursosweb.proyecto.domain;
import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.proyecto.dto.ProyectoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;

import java.util.ArrayList;

@Service
public class ProyectoService extends ApiService<ProyectoDto, Integer> {
    
    public Iterable<ProyectoDto> getProyectosWithCompromiso() {
        String url = apiurl + "/" + apiservicename;
        ResponseEntity<RespuestaCustomizada<Iterable<ProyectoDto>>> responseEntity = restTemplate.exchange(url + "/getProyectosWithCompromiso" , HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<ProyectoDto>>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        Iterable<ProyectoDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, ProyectoDto.class));
        return dtoRespuesta;
    }

    public Iterable<ProyectoDto> getProyectosGantt() {
        String url = apiurl + "/" + apiservicename;
        ResponseEntity<RespuestaCustomizada<Iterable<ProyectoDto>>> responseEntity = restTemplate.exchange(url + "/proyectos-gantt" , HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<ProyectoDto>>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        Iterable<ProyectoDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, ProyectoDto.class));
        return dtoRespuesta;
    }
      

}
