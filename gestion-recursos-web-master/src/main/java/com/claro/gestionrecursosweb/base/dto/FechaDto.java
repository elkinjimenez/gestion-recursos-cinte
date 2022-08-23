package com.claro.gestionrecursosweb.base.dto;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class FechaDto {
	@JsonIgnore
	public String fechaCreacionString;
	@JsonIgnore
	public String fechaModificacionString;
	
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	Logger log = Logger.getLogger(FechaDto.class.getSimpleName());

	
	protected void getFormatoFechas(Date fechacreacion, Date fechamodificacion) {
		if (fechacreacion != null)
			this.fechaCreacionString = formatoFecha.format(fechacreacion);
		if (fechamodificacion != null)
			this.fechaModificacionString = formatoFecha.format(fechamodificacion);
	}
	
	protected void getFormatoFechas(String fechacreacion, String fechamodificacion) {
		SimpleDateFormat formatoFechaString = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaCreacion,fechaModificacion = null;
	
		try {
			if (fechacreacion != null) {
				fechaCreacion = formatoFechaString.parse(fechacreacion);
				this.fechaCreacionString=formatoFecha.format(fechaCreacion);
			}
			if (fechamodificacion != null) {
				fechaModificacion=formatoFechaString.parse(fechamodificacion);
				this.fechaModificacionString = formatoFecha.format(fechaModificacion);
			}	
		} catch (Exception e) {
		  log.log(Level.SEVERE, e.getMessage());
		}
		
	}
	
	
}
