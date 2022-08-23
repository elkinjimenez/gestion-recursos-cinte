package com.claro.gestionrecursosweb.empleado.domain;


import com.claro.gestionrecursosweb.base.domain.ApiService;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosweb.empleado.dto.EmpleadoFormDto;
import com.claro.gestionrecursosweb.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class EmpleadoControlService extends ApiService<EmpleadoFormDto, Integer> {

    public List<EmpleadoFormDto> getEmpleadoByEstructura(int codEstructura, Class<EmpleadoFormDto> tipo) throws Exception {
        try {
            List<EmpleadoFormDto> jefes = new ArrayList<>();
            if (codEstructura != 1) {
                codEstructura = codEstructura - 1;
                String apiservicename = "empleadocontrol/getEmpleadoByEstructuraTipo/" + codEstructura;
                String url = apiurl + "/" + apiservicename;
                ResponseEntity<RespuestaCustomizada<List<EmpleadoFormDto>>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<List<EmpleadoFormDto>>>() {
                });
                ObjectMapper mapper = new ObjectMapper();
                Iterable<EmpleadoFormDto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));
                dtoRespuesta.forEach(item -> {
                    jefes.add(item);
                });
            }

            if (jefes.isEmpty()) {
                EmpleadoFormDto p = new EmpleadoFormDto();
                p.setNombreEmpleado("No hay personas en el nivel superrior");
                jefes.add(p);
            }
            return jefes;
        } catch (Exception e) {
            throw new Exception("Error al realizar la peticion" + e.getMessage());
        }
    }

    public EmpleadoFormDto getEmpleadoByCodPadre(int codPadre, Class<EmpleadoFormDto> tipo) throws Exception {
        try {
            List<EmpleadoFormDto> dtoRespuesta = new ArrayList<>();
            String apiservicename = "empleadocontrol/getEmpleadoFormById/" + codPadre;
            String url = apiurl + "/" + apiservicename;
            ResponseEntity<RespuestaCustomizada<List<EmpleadoFormDto>>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<List<EmpleadoFormDto>>>() {
            });
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(StringUtils.dataFormat);
            dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));
            return dtoRespuesta.get(0);
        } catch (Exception e) {
            throw new Exception("Error al realizar la peticion" + e.getMessage());
        }
    }

}