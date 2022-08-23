package com.claro.gestionrecursosapi.tarea.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.tarea.entity.TareaVU;

@Repository
public interface ITareaVURepository extends CrudRepository<TareaVU, Integer> {

	@Query("select t from TareaVU t where t.codproyecto = :codproyecto")
	public Iterable<TareaVU> findAllByCodproyecto(@Param("codproyecto") Integer codproyecto);
	
}
