package com.claro.gestionrecursosapi.utils;


import java.math.BigDecimal;


public class FormatoUtil {
	
	
	public static Double porcentaje(String numero) {
		Double numeroDecimal = new Double(numero);
		numeroDecimal=numeroDecimal/100;
		return numeroDecimal;
	}
	
	public static void main (String [] args) {
		System.out.println("-----> "+porcentaje("81.66666666666666666666666666666666666667"));
	}

}
