package com.claro.gestionrecursosapi.tarea.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.tarea.entity.TareaTipoEntity;

@Repository
public interface ITareaTipoRepository extends CrudRepository<TareaTipoEntity, Integer> {

}
