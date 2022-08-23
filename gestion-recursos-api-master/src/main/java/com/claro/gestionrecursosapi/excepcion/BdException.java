package com.claro.gestionrecursosapi.excepcion;

import java.sql.SQLException;

public class BdException extends SQLException {
    
    public BdException(){

    }
    public BdException(String mensaje){
        super(mensaje);
    }
    public BdException(String mensaje, String status ){
         super(mensaje, status);
    }
}
