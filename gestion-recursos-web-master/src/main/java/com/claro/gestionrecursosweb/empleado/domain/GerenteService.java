package com.claro.gestionrecursosweb.empleado.domain;

import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosweb.empleado.dto.GerenteDto;
import com.claro.gestionrecursosweb.estructura.dto.EstructuraOrganizacionalDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GerenteService extends ApiService<GerenteDto, Long> {

    public List<GerenteDto> findAllGerentes() {
        try {

            ResponseEntity<RespuestaCustomizada<Iterable<GerenteDto>>> responseEntity = restTemplate.exchange(
                    apiurl + "/gerente", HttpMethod.GET, null,
                    new ParameterizedTypeReference<RespuestaCustomizada<Iterable<GerenteDto>>>() {
                    });
            ObjectMapper mapper = new ObjectMapper();
            List<GerenteDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(),
                    mapper.getTypeFactory().constructCollectionType(ArrayList.class, GerenteDto.class));
            if (dtoRespuesta == null)
                return null;
            else
                return dtoRespuesta;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public GerenteDto actualizarGerente(GerenteDto gerenteDto, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GerenteDto> entity = new HttpEntity<GerenteDto>(gerenteDto, headers);
        ResponseEntity<GerenteDto> responseEntity = restTemplate.exchange(apiurl + "/gerente/" + id, HttpMethod.PUT,
                entity, GerenteDto.class);
        ObjectMapper mapper = new ObjectMapper();
        GerenteDto dtoRespuesta = mapper.convertValue(responseEntity.getBody(), GerenteDto.class);
        return dtoRespuesta;
    }

    public GerenteDto findGerenteByCodEmpleado(Integer codEmpleado) {
        ResponseEntity<GerenteDto> responseEntity = restTemplate.exchange(apiurl + "/gerente/" + codEmpleado,
                HttpMethod.GET, null, GerenteDto.class);
        ObjectMapper mapper = new ObjectMapper();
        GerenteDto dtoRespuesta = mapper.convertValue(responseEntity.getBody(), GerenteDto.class);
        return dtoRespuesta;
    }

    public List<GerenteDto> findJerarquiaAndTipoEstructura(String jerarquia, Integer tipoEstructura) {
        String url = jerarquia + "/" + tipoEstructura;
        ResponseEntity<RespuestaCustomizada<Iterable<GerenteDto>>> responseEntity = restTemplate.exchange(
                apiurl + "/estructuraorganizacional/findJerarquiaAndTipoEstructura/" + url, HttpMethod.GET, null,
                new ParameterizedTypeReference<RespuestaCustomizada<Iterable<GerenteDto>>>() {
                });
        ObjectMapper mapper = new ObjectMapper();
        List<GerenteDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(),
                mapper.getTypeFactory().constructCollectionType(ArrayList.class, GerenteDto.class));
        if (dtoRespuesta == null)
            return null;
        else
            return dtoRespuesta;
    }

    public List<EstructuraOrganizacionalDto> getEstructuraXCodProyecto(int codProyecto) {

        ResponseEntity<RespuestaCustomizada<Iterable<EstructuraOrganizacionalDto>>> responseEntity = restTemplate
                .exchange(apiurl + "/estructuraorganizacional/getEstructuraXCodProyecto/" + codProyecto, HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<RespuestaCustomizada<Iterable<EstructuraOrganizacionalDto>>>() {
                        });
        ObjectMapper mapper = new ObjectMapper();
        List<EstructuraOrganizacionalDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(),
                mapper.getTypeFactory().constructCollectionType(ArrayList.class, EstructuraOrganizacionalDto.class));
        if (dtoRespuesta == null)
            return null;
        else
            return dtoRespuesta;
    }

    public Iterable<EstructuraOrganizacionalDto> getEstructuraxCodPadre(int codPadre) {
        ResponseEntity<RespuestaCustomizada<Iterable<EstructuraOrganizacionalDto>>> responseEntity = restTemplate
                .exchange(apiurl + "/estructuraorganizacional/getEstructuraxCodPadre/" + codPadre, HttpMethod.GET, null,
                        new ParameterizedTypeReference<RespuestaCustomizada<Iterable<EstructuraOrganizacionalDto>>>() {
                        });
        ObjectMapper mapper = new ObjectMapper();
        List<EstructuraOrganizacionalDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(),
                mapper.getTypeFactory().constructCollectionType(ArrayList.class, EstructuraOrganizacionalDto.class));
        if (dtoRespuesta == null)
            return null;
        else
            return dtoRespuesta;
    }

    public EstructuraOrganizacionalDto getGerenteporCodigoEstructura(int codEstructura) {
        ResponseEntity<RespuestaCustomizada<EstructuraOrganizacionalDto>> responseEntity = restTemplate.exchange(
                apiurl + "/estructuraorganizacional/getGerenteporCodigoEstructura/" + codEstructura, HttpMethod.GET,
                null, new ParameterizedTypeReference<RespuestaCustomizada<EstructuraOrganizacionalDto>>() {
                });
        ObjectMapper mapper = new ObjectMapper();
        EstructuraOrganizacionalDto dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(),
                EstructuraOrganizacionalDto.class);
        if (dtoRespuesta == null)
            return null;
        else
            return dtoRespuesta;
    }

    public Iterable<EstructuraOrganizacionalDto> getEstructuraXGerencia(int codGerencia) {

        ResponseEntity<RespuestaCustomizada<Iterable<EstructuraOrganizacionalDto>>> responseEntity = restTemplate
                .exchange(apiurl + "/estructuraorganizacional/getEstructuraXGerencia/" + codGerencia, HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<RespuestaCustomizada<Iterable<EstructuraOrganizacionalDto>>>() {
                        });
        ObjectMapper mapper = new ObjectMapper();
        List<EstructuraOrganizacionalDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(),
                mapper.getTypeFactory().constructCollectionType(ArrayList.class, EstructuraOrganizacionalDto.class));
        if (dtoRespuesta == null)
            return null;
        else
            return dtoRespuesta;
    }

    public List<EstructuraOrganizacionalDto> getEstructuraFiltroDonuts(int codProyecto) throws Exception {

        try {
            ResponseEntity<RespuestaCustomizada<Iterable<EstructuraOrganizacionalDto>>> responseEntity = restTemplate
                    .exchange(apiurl + "/estructuraorganizacional/getEstructuraFiltroDonuts/" + codProyecto, HttpMethod.GET, null,
                            new ParameterizedTypeReference<RespuestaCustomizada<Iterable<EstructuraOrganizacionalDto>>>() {
                            });
            ObjectMapper mapper = new ObjectMapper();
            List<EstructuraOrganizacionalDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(),
                    mapper.getTypeFactory().constructCollectionType(ArrayList.class,
                            EstructuraOrganizacionalDto.class));
            if (dtoRespuesta == null)
                return null;
            else
                return dtoRespuesta;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

}