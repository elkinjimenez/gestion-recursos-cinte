package com.claro.gestionrecursosapi.excepcion;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataNotFoundExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> exceptionHandler(DataNotFoundException ex) {
        RespuestaBase error = new RespuestaBase();
        error.setMensaje(ex.getMessage());
        error.setCodigoEstatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

}
