package com.claro.gestionrecursosapi.hallazgorespuesta.repository;

import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoEntity;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoProyectosEntity;
import com.claro.gestionrecursosapi.novedad.entity.NovedadEntity;
import org.springframework.data.repository.CrudRepository;

public interface IHallazgoRespuestaRepository extends CrudRepository<HallazgoEntity, Integer> {
    @Override
    public Iterable<HallazgoEntity> findAll();

}
