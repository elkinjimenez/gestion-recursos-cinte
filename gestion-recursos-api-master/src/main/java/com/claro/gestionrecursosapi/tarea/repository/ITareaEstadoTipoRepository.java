package com.claro.gestionrecursosapi.tarea.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.tarea.entity.TareaEstadoTipoEntity;

@Repository
public interface ITareaEstadoTipoRepository extends CrudRepository<TareaEstadoTipoEntity, Integer> {

}
