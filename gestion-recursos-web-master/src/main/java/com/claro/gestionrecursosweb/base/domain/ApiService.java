package com.claro.gestionrecursosweb.base.domain;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.claro.gestionrecursosweb.base.model.RespuestaBase;
import com.claro.gestionrecursosweb.reportegenerico.dto.ReporteFiltroDto;
import com.claro.gestionrecursosweb.utils.ExceptionUtils;
import com.claro.gestionrecursosweb.utils.StringUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.claro.gestionrecursosweb.base.dto.FiltroFechas;
import com.claro.gestionrecursosweb.base.model.RespuestaCustomizada;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiService<Dto, IdDataType> implements ICrudService<Dto, IdDataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);

    public static int codigoHttp;
    public static String mensaje;

    protected String apiservicename;

    @Autowired
    protected ExceptionUtils exceptionUtils;

    @Autowired
    protected RestTemplate restTemplate;

    @Value("${claro.api.urlbase}")
    protected String apiurl;

    public void setapiservicename(String apiservicename) {
        this.apiservicename = apiservicename;
    }

    @Override
    public Dto insert(Dto entity, Class<Dto> tipo) {
        Gson gson = new Gson();
        ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = null;
        RespuestaCustomizada<Dto> dtoRespuesta = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename, HttpMethod.POST, new HttpEntity<Dto>(entity, getHeaders()), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
            });
            dtoRespuesta = mapper.convertValue(responseEntity.getBody(), RespuestaCustomizada.class);
            this.codigoHttp = dtoRespuesta.getCodigoEstatus();
            this.mensaje = dtoRespuesta.getMensaje();
        } catch (HttpClientErrorException ex) {

            LOGGER.info("ERROR:" + ex.getResponseBodyAsString());
            RespuestaBase respuestaBase = gson.fromJson(ex.getResponseBodyAsString(), RespuestaBase.class);
            this.codigoHttp = respuestaBase.getCodigoEstatus();
            this.mensaje = respuestaBase.getMensaje();
            return null;
        }

        Dto dtoRespuesta2 = mapper.convertValue(dtoRespuesta.getData(), tipo);
        return dtoRespuesta2;
    }

    @Override
    public List<Dto> insertAll(List<Dto> entity, Class<Dto> tipo) {
        Gson gson = new Gson();
        List<Dto> respuesta = new ArrayList<>();
        ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = null;
        RespuestaCustomizada<Dto> dtoRespuesta = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/saveAll", HttpMethod.POST, new HttpEntity<List<Dto>>(entity, getHeaders()), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
            });
            dtoRespuesta = mapper.convertValue(responseEntity.getBody(), RespuestaCustomizada.class);
            this.codigoHttp = dtoRespuesta.getCodigoEstatus();
            this.mensaje = dtoRespuesta.getMensaje();

            respuesta = mapper.convertValue(dtoRespuesta.getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));

        } catch (HttpClientErrorException ex) {
            LOGGER.info("ERROR:" + ex.getResponseBodyAsString());
            RespuestaBase respuestaBase = gson.fromJson(ex.getResponseBodyAsString(), RespuestaBase.class);
            this.codigoHttp = respuestaBase.getCodigoEstatus();
            this.mensaje = respuestaBase.getMensaje();
            return null;
        }

        return respuesta;
    }

	@Override
	public Dto update(IdDataType id, Dto entity, Class<Dto> tipo) {
    
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + id, HttpMethod.PUT, new HttpEntity<Dto>(entity, getHeaders()), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {});
		RespuestaCustomizada<Dto> dtoRespuesta = mapper.convertValue(responseEntity.getBody(), RespuestaCustomizada.class);
		Dto dtoRespuesta2 = mapper.convertValue(dtoRespuesta.getData(), tipo);
		return dtoRespuesta2;
	}

	@Override
	public Optional<Dto> findById(IdDataType id, Class<Dto> tipo) {
		ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + id, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {});
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(StringUtils.dataFormat);
		Dto dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), tipo);
		if (dtoRespuesta == null)
			return null;
		else
			return Optional.of(dtoRespuesta);
	}

    @Override
    public boolean existsById(IdDataType id) {
        // TODO Auto-generated method stub
        return false;
    }

    public Iterable<Dto> findAllByAttr(String attrnombre, String attrvalor, Class<Dto> tipo) {
        try {
            ResponseEntity<RespuestaCustomizada<Iterable<Dto>>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + attrnombre + "?v=" + attrvalor, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Dto>>>() {
            });
            ObjectMapper mapper = new ObjectMapper();
            Iterable<Dto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));
            return dtoRespuesta;
        } catch (Exception e) {
            // Controlar errores correctamente, obtener errores!
        }
        return null;
    }

    @Override
    public Iterable<Dto> findAll(Class<Dto> tipo) {
        String url = apiurl + "/" + apiservicename;
        try {
            ResponseEntity<RespuestaCustomizada<Iterable<Dto>>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Dto>>>() {
            });
            ObjectMapper mapper = new ObjectMapper();
            Iterable<Dto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));
            return dtoRespuesta;
        } catch (Exception e) {
            // Controlar errores correctamente, obtener errores!
            System.out.println("Error al realizar la peticion: " + url + " " + exceptionUtils.getStackTraceString(e) + e.getMessage());
        }
        return null;
    }

    //Hallazgos No User----------------------------------------------------------------------------------------------------------------------------------
    public Iterable<Dto> findAllNoUser(Class<Dto> tipo) {
        String url = apiurl + "/" + apiservicename;
        try {
            ResponseEntity<RespuestaCustomizada<Iterable<Dto>>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Dto>>>() {
            });
            ObjectMapper mapper = new ObjectMapper();
            Iterable<Dto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));
            return dtoRespuesta;
        } catch (Exception e) {
            // Controlar errores correctamente, obtener errores!
            System.out.println("Error al realizar la peticion: " + url + " " + exceptionUtils.getStackTraceString(e) + e.getMessage());
        }
        return null;
    }

    public Iterable<Dto> findAllByPathVariableNoUser(Class<Dto> tipo, String pathVariable) {
        String url = apiurl + "/" + apiservicename + "/" + pathVariable;
        try {
            ResponseEntity<RespuestaCustomizada<Iterable<Dto>>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Dto>>>() {
            });
            ObjectMapper mapper = new ObjectMapper();
            Iterable<Dto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));
            return dtoRespuesta;
        } catch (Exception e) {
            // Controlar errores correctamente, obtener errores!
            System.out.println("Error al realizar la peticion: " + url + " " + exceptionUtils.getStackTraceString(e) + e.getMessage());
        }
        return null;
    }

    public Iterable<Dto> findAllWithPayloadNoUser(Class<Dto> tipo, Object payload) {
        String url = apiurl + "/" + apiservicename;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            ResponseEntity<RespuestaCustomizada<Iterable<Dto>>> dtoRespuesta = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(payload, null), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Dto>>>() {
            });

            Iterable<Dto> dtoRespuesta2 = mapper.convertValue(dtoRespuesta.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));

            return dtoRespuesta2;
        } catch (Exception e) {
            // Controlar errores correctamente, obtener errores!
            System.out.println("Error al realizar la peticion:" + e.getMessage());
            return null;
        }
    }

    public Optional<Dto> findByIdNoUser(IdDataType id, Class<Dto> tipo) {
        String url = apiurl + "/" + apiservicename + "/" + id;
        ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(StringUtils.dataFormat);

        Dto dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), tipo);

        if (dtoRespuesta == null) {
            return null;
        } else {
            return Optional.of(dtoRespuesta);
        }
    }

    public Dto insertNoUser(Dto entity, Class<Dto> tipo) {
        Gson gson = new Gson();
        ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = null;
        RespuestaCustomizada<Dto> dtoRespuesta = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename, HttpMethod.POST, new HttpEntity<Dto>(entity, null), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
            });
            dtoRespuesta = mapper.convertValue(responseEntity.getBody(), RespuestaCustomizada.class);
            this.codigoHttp = dtoRespuesta.getCodigoEstatus();
            this.mensaje = dtoRespuesta.getMensaje();
        } catch (HttpClientErrorException ex) {

            LOGGER.info("ERROR:" + ex.getResponseBodyAsString());
            RespuestaBase respuestaBase = gson.fromJson(ex.getResponseBodyAsString(), RespuestaBase.class);
            this.codigoHttp = respuestaBase.getCodigoEstatus();
            this.mensaje = respuestaBase.getMensaje();
            return null;
        }

        Dto dtoRespuesta2 = mapper.convertValue(dtoRespuesta.getData(), tipo);
        return dtoRespuesta2;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------
    public Iterable<Dto> findAllPost(Class<Dto> tipo, FiltroFechas params) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            ResponseEntity<RespuestaCustomizada<Iterable<Dto>>> dtoRespuesta = restTemplate.exchange(apiurl + "/" + apiservicename, HttpMethod.POST, new HttpEntity<FiltroFechas>(params, getHeaders()), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Dto>>>() {
            });

            Iterable<Dto> dtoRespuesta2 = mapper.convertValue(dtoRespuesta.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));

            return dtoRespuesta2;
        } catch (Exception e) {
            // Controlar errores correctamente, obtener errores!
            System.out.println("Error al realizar la peticion:" + e.getMessage());
            return null;
        }
    }

    public Iterable<Dto> findAllWithPayload(Class<Dto> tipo, Object payload) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            ResponseEntity<RespuestaCustomizada<Iterable<Dto>>> dtoRespuesta = restTemplate.exchange(apiurl + "/" + apiservicename, HttpMethod.POST, new HttpEntity<Object>(payload, getHeaders()), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Dto>>>() {
            });

            Iterable<Dto> dtoRespuesta2 = mapper.convertValue(dtoRespuesta.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));

            return dtoRespuesta2;
        } catch (Exception e) {
            // Controlar errores correctamente, obtener errores!
            System.out.println("Error al realizar la peticion:" + e.getMessage());
            return null;
        }
    }

    public Iterable<Dto> findAllWithParams(Class<Dto> tipo, Object params) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String parametros = objectMapper.writeValueAsString(params);
            parametros = URLEncoder.encode(parametros, StandardCharsets.UTF_8.toString());
            ResponseEntity<RespuestaCustomizada<Iterable<Dto>>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "?q=" + parametros, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Dto>>>() {
            });
            ObjectMapper mapper = new ObjectMapper();
            Iterable<Dto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));
            return dtoRespuesta;
        } catch (Exception e) {
            // Controlar errores correctamente, obtener errores!
            System.out.println("Error al realizar la peticion:" + e.getMessage());
        }
        return null;
    }

    public Iterable<Dto> findAllByPathVariable(Class<Dto> tipo, String pathVariable) {
        try {
            ResponseEntity<RespuestaCustomizada<Iterable<Dto>>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + pathVariable, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Dto>>>() {
            });
            ObjectMapper mapper = new ObjectMapper();
            Iterable<Dto> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));
            return dtoRespuesta;
        } catch (Exception e) {
            // Controlar errores correctamente, obtener errores!
            System.out.println("Error al realizar la peticion:" + e.getMessage());
        }
        return null;
    }

    @Override
    public Iterable<Dto> findAllView(String nombreView, Class<Dto> tipo) {
        try {
            ResponseEntity<RespuestaCustomizada<Iterable<Object[]>>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + nombreView, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Iterable<Object[]>>>() {
            });
            ObjectMapper mapper = new ObjectMapper();
            List<Object[]> dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, Object[].class));
            return VistaServiceBase.wrapper(dtoRespuesta, tipo);
        } catch (Exception e) {
            // Controlar errores correctamente, obtener errores!
            System.out.println("Error al realizar la peticion:" + e.getMessage());
        }
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(IdDataType id) {
        restTemplate.exchange(apiurl + "/" + apiservicename + "/" + id, HttpMethod.DELETE, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Object>>() {
        });
    }

    @Override
    public void delete(Dto entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<Dto> entities) {
        // TODO Auto-generated method stub

    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("CL_USUARIO", SecurityContextHolder.getContext().getAuthentication().getName());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    public HttpEntity getRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("CL_USUARIO", SecurityContextHolder.getContext().getAuthentication().getName());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity request = new HttpEntity(headers);
        return request;
    }

    @Override
    public Dto findByParams(Dto entity, Class<Dto> tipo) {
        ResponseEntity<RespuestaCustomizada<Dto>> dtoRespuesta = restTemplate.exchange(apiurl + "/" + apiservicename,
                HttpMethod.POST, new HttpEntity<Dto>(entity, getHeaders()), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
                });
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        RespuestaCustomizada<Dto> respuesta = mapper.convertValue(dtoRespuesta.getBody(), RespuestaCustomizada.class);
        Dto dtoRespuesta2 = mapper.convertValue(respuesta.getData(), tipo);
        return dtoRespuesta2;
    }

    public Dto findByPayload(Object entity, Class<Dto> tipo) {
        ResponseEntity<RespuestaCustomizada<Dto>> dtoRespuesta = restTemplate.exchange(apiurl + "/" + apiservicename,
                HttpMethod.POST, new HttpEntity<Object>(entity, getHeaders()), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
                });
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        RespuestaCustomizada<Dto> respuesta = mapper.convertValue(dtoRespuesta.getBody(), RespuestaCustomizada.class);
        Dto dtoRespuesta2 = mapper.convertValue(respuesta.getData(), tipo);
        return dtoRespuesta2;
    }

    public Optional<Dto> findByParamApi(String path, String pathVariable, Class<Dto> tipo) {
        ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + path + "/" + pathVariable, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(StringUtils.dataFormat);

        Dto dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), tipo);

        if (dtoRespuesta == null) {
            return null;
        } else {
            return Optional.of(dtoRespuesta);
        }
    }

    public Iterable<Dto> findAllByParamApi(String path, String pathVariable, Class<Dto> tipo) {
        ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + path + "/" + pathVariable, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(StringUtils.dataFormat);
        List<Dto> respuesta = new ArrayList<>();
        respuesta = mapper.convertValue(responseEntity.getBody().getData(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, tipo));

        if (respuesta == null) {
            return null;
        } else {
            return respuesta;
        }
    }

    public Optional<Dto> findByUrl(String url, Class<Dto> tipo) {
        String urlApi = apiurl + "/" + apiservicename + "/" + url;
        ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = restTemplate.exchange(urlApi, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
        });
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(StringUtils.dataFormat);
        Dto dtoRespuesta = mapper.convertValue(responseEntity.getBody().getData(), tipo);
        if (dtoRespuesta == null) {
            return null;
        } else {
            return Optional.of(dtoRespuesta);
        }
    }

    public ResponseEntity<?> findAllByParamApiDeserializacion(String urlMapping) {

        Gson gson = new Gson();
        ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = null;
        RespuestaCustomizada<Dto> dtoRespuesta = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            String url = apiurl + "/" + apiservicename;
            responseEntity = restTemplate.exchange(url + urlMapping, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
            });
            dtoRespuesta = mapper.convertValue(responseEntity.getBody(), RespuestaCustomizada.class);
            this.codigoHttp = dtoRespuesta.getCodigoEstatus();
            this.mensaje = dtoRespuesta.getMensaje();

            //respuesta = (Iterable<DonutChartDTO>) mapper.convertValue(dtoRespuesta.getData(), DonutChartDTO.class);
        } catch (HttpClientErrorException ex) {
            LOGGER.info("ERROR:" + ex.getResponseBodyAsString());
            RespuestaBase respuestaBase = gson.fromJson(ex.getResponseBodyAsString(), RespuestaBase.class);
            this.codigoHttp = respuestaBase.getCodigoEstatus();
            this.mensaje = respuestaBase.getMensaje();
            return null;
        }

        return responseEntity;

    }

    public ResponseEntity<?> findAllByParamApiDeserializacionPost(ReporteFiltroDto entity, String urlMapping) {
        Gson gson = new Gson();
        ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = null;
        RespuestaCustomizada<Dto> dtoRespuesta = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            String url = apiurl + "/" + apiservicename + urlMapping;
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Dto>((Dto) entity, getHeaders()), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
            });
            dtoRespuesta = mapper.convertValue(responseEntity.getBody(), RespuestaCustomizada.class);
            this.codigoHttp = dtoRespuesta.getCodigoEstatus();
            this.mensaje = dtoRespuesta.getMensaje();

        } catch (HttpClientErrorException ex) {
            LOGGER.info("ERROR:" + ex.getResponseBodyAsString());
            RespuestaBase respuestaBase = gson.fromJson(ex.getResponseBodyAsString(), RespuestaBase.class);
            this.codigoHttp = respuestaBase.getCodigoEstatus();
            this.mensaje = respuestaBase.getMensaje();
            return null;
        }

        return responseEntity;
    }

    public String findByParamApi(String path, String pathVariable) {
        ResponseEntity<RespuestaCustomizada<Dto>> responseEntity = restTemplate.exchange(apiurl + "/" + apiservicename + "/" + path + "/" + pathVariable, HttpMethod.GET, getRequest(), new ParameterizedTypeReference<RespuestaCustomizada<Dto>>() {
        });
        return (String) responseEntity.getBody().getData();

    }

}
