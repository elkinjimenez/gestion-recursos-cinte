package com.claro.gestionrecursosapi.utils;

public class ReplaceTildesUtil {
	
	 // Cadena de caracteres original a sustituir.
	 private static final String original = "áàäéèëíìïóòöúùuÁÀÄÉÈËÍÌÏÓÒÖÚÙÜçÇ";
	  // Cadena de caracteres ASCII que reemplazarán los originales.
	 private static final String ascii = "aaaeeeiiiooouuuAAAEEEIIIOOOUUUcC";
	
	public static String replace(String input) {	 
		  String output = input;
		  for (int i=0; i<original.length(); i++) {
		      // Reemplazamos los caracteres especiales.
		      output = output.replace(original.charAt(i), ascii.charAt(i));
		  }//for i
		  return output;
		}
	

}
