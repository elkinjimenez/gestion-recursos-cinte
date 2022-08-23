package com.claro.gestionrecursosapi.reporte.domain;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.base.model.FiltroFechas;
import com.claro.gestionrecursosapi.reporte.entity.ReporteProyectoTiempo;
import com.claro.gestionrecursosapi.reporte.repository.IReporteProyectoTiempoRepository;

@Service
public class ReporteProyectoTiempoService {

	@Autowired
	private IReporteProyectoTiempoRepository repository;
	
	public Iterable<ReporteProyectoTiempo> findAll(FiltroFechas filtro){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return repository.findAll(sdf.format(filtro.getFechaInicio()), sdf.format(filtro.getFechaFin()));
	}
	
}
