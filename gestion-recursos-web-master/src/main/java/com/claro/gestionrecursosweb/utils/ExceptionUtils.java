/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claro.gestionrecursosweb.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.stereotype.Service;

/**
 * Clase que define funcionalidades para el manejo de excepciones
 * @author Germán Alejandro Casallas
 */
@Service
public class ExceptionUtils {
    final StringWriter stringWriter = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(stringWriter);

    private void clearWriters(){
        printWriter.flush();
        stringWriter.getBuffer().setLength(0);
    }

    /**
     * Transforma la traza de la excepción a su equivalente en String
     * @param exception
     * @return
     */
    public String getStackTraceString(Exception exception) {
        clearWriters();
        exception.printStackTrace(printWriter);

        return stringWriter.toString();
    }

}