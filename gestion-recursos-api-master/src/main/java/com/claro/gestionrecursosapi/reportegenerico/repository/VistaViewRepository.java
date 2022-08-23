package com.claro.gestionrecursosapi.reportegenerico.repository;

import com.claro.gestionrecursosapi.reportegenerico.entity.VistaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface VistaViewRepository extends CrudRepository<VistaEntity, String> {
	@Query(value = "select vistaviews from VistaEntity vistaviews where vistaviews.estado = 'A' ")
	public Iterable<VistaEntity> findVistasHerramienta();
}