package com.claro.gestionrecursosapi.hallazgo.repository;

import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoEntity;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoProyectosEntity;
import org.springframework.data.repository.CrudRepository;

public interface IHallazgoProyectoRepository extends CrudRepository<HallazgoProyectosEntity, Integer> {

}
