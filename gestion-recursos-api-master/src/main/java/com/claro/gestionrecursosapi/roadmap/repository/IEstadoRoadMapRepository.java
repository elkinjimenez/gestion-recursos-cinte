package com.claro.gestionrecursosapi.roadmap.repository;

import com.claro.gestionrecursosapi.roadmap.entity.EstadoRoadMap;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoRoadMapRepository extends CrudRepository<EstadoRoadMap, Integer> {

}
