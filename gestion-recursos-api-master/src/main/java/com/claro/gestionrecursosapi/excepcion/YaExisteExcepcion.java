package com.claro.gestionrecursosapi.excepcion;

public class YaExisteExcepcion extends Exception {

	public YaExisteExcepcion(String mensaje) {
		super(mensaje);
	}
}
