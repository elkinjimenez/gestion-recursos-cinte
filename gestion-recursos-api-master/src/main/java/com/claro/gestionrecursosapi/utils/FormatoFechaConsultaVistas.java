package com.claro.gestionrecursosapi.utils;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class FormatoFechaConsultaVistas {

    @Autowired
    EntityManager entityManager;

    /**
     * Asigna el formato de fechas de trabajo, para consultar las vistas de DB
     * solución a error ORA-01843: not a valid month, el formato de fechas usado por las vistas actualmente es DD/MM/YYYY
     */
    private void setSessionDatesFormat() {
        try {
            Query query = entityManager.createNativeQuery("ALTER SESSION SET nls_date_format = 'DD/MM/YYYY'");

            query.getResultList();
        } catch (Exception e) {
            //System.out.println("session alter: " + ExceptionUtils.getStackTraceString(e));
        }
    }

}
