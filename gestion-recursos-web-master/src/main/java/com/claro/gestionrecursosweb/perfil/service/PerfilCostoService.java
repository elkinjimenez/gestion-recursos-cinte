package com.claro.gestionrecursosweb.perfil.service;

import com.claro.gestionrecursosweb.perfil.dto.PerfilCostoDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PerfilCostoService {

    public List<PerfilCostoDto> buscarTodo() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Map<String,Object>> responseEntity = template.exchange("http://localhost:8080/api/v1/perfilcosto", HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {});
        if (responseEntity.getStatusCode().equals(HttpStatus.OK) && Objects.nonNull(responseEntity.getBody()) && Objects.nonNull(responseEntity.getBody().get("data"))) {
            return (List<PerfilCostoDto>) responseEntity.getBody().get("data");
        }
        return new ArrayList<>();
    }
}
