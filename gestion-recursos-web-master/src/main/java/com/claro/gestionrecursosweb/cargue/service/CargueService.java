package com.claro.gestionrecursosweb.cargue.service;

import com.claro.gestionrecursosweb.cargue.model.CarguePresupuesto;
import com.claro.gestionrecursosweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CargueService implements ICargueService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Map<String, List<CarguePresupuesto>> carguePresupuesto(MultipartFile file, String usuarioSesion) throws IOException, HttpServerErrorException {
        //File fileC = new File("C:\\Users\\User\\Documents\\ACTIVIDADES\\12MAY2020\\HERRAMIENTA_GESTION_ACTIVIDADES\\CARGUES\\cargues.xlsx");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("CL_USUARIO", usuarioSesion);
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
        parameters.add("archivo", new FileSystemResource(StringUtils.convert(file)));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(parameters, headers);
        ResponseEntity<Map<String, List<CarguePresupuesto>>> result = restTemplate.exchange(
                "http://localhost:8080/api/v1/cargue/presupuesto", HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<Map<String, List<CarguePresupuesto>>>() {});
        return result.getBody();
    }

}
