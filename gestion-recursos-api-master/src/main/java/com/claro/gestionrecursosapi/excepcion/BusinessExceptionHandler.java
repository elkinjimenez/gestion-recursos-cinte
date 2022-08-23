package com.claro.gestionrecursosapi.excepcion;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> exceptionHandler(BusinessException ex) {
        RespuestaBase error = new RespuestaBase();
        error.setMensaje(ex.getMessage());
        error.setCodigoEstatus(HttpStatus.LOCKED.value());
        return ResponseEntity.status(HttpStatus.LOCKED).body(error);
    }
}
