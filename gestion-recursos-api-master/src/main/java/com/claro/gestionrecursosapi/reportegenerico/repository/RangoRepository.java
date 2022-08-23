package com.claro.gestionrecursosapi.reportegenerico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.reportegenerico.entity.RangoEntity;

public interface RangoRepository extends CrudRepository<RangoEntity, String> {

	@Query(value = "Select rango from RangoEntity rango")
	public Optional<RangoEntity> getRangoRegistro();
}
