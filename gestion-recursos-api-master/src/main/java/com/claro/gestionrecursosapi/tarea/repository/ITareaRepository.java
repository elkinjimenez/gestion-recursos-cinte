package com.claro.gestionrecursosapi.tarea.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.tarea.entity.TareaEntity;

@Repository
public interface ITareaRepository extends CrudRepository<TareaEntity, Integer> {

	@Query("select t from TareaEntity t where t.codproyecto = :codproyecto")
	public Iterable<TareaEntity> findAllByCodproyecto(@Param("codproyecto") Integer codproyecto);

}
