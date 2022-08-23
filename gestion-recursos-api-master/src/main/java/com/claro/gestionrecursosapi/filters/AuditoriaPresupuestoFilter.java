package com.claro.gestionrecursosapi.filters;

import com.claro.gestionrecursosapi.excepcion.BusinessException;
import com.claro.gestionrecursosapi.presupuesto.domain.PresupuestoHistorialService;
import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoEntity;
import com.claro.gestionrecursosapi.utils.MultiReadRequest;
import com.claro.gestionrecursosapi.utils.MultiReadResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@Component
//@Order(1)
public class AuditoriaPresupuestoFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriaPresupuestoFilter.class);

    /*@Autowired
    private PresupuestoHistorialService presupuestoHistorialService;*/


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        /*Gson gson = new Gson();
        MultiReadRequest wrapperRequest = null;
        MultiReadResponse wrapperResponse = null;
        String respuesta = null;
        StringBuilder builder = new StringBuilder();
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        if (servletRequest.getRequestURI().contains("/api/v1/presupuesto/")) {
            wrapperRequest = new MultiReadRequest(servletRequest);
            wrapperResponse = new MultiReadResponse((HttpServletResponse) response);
        }

        if (servletRequest.getRequestURI().contains("/api/v1/presupuesto/") && "PUT".equals(wrapperRequest.getMethod())) {
            String usuarioSession = servletRequest.getHeader("CL_USUARIO");
            if (Objects.isNull(usuarioSession)) {
                throw new BusinessException("No se ha detectado el parametro de cabecera usuario");
            }
            wrapperRequest.getReader().lines()
                    .forEach(l -> builder.append(l.replace("\t", "")));
            LOGGER.info("Request: " + builder.toString());

            try {
                PresupuestoEntity presupuestoEntity = gson.fromJson(builder.toString(), PresupuestoEntity.class);
                presupuestoHistorialService.guardarHistorialPresupuesto(presupuestoEntity, usuarioSession);
                chain.doFilter(wrapperRequest, wrapperResponse);
                wrapperResponse.flushBuffer();
            } finally {
                byte[] copy = wrapperResponse.getCopy();
                respuesta = new String(copy, response.getCharacterEncoding());
                LOGGER.info("Respuesta: " + respuesta); // Do your logging job here. This is just a basic example.
            }

        } else {
            chain.doFilter(request, response);
        }*/

    }
}
