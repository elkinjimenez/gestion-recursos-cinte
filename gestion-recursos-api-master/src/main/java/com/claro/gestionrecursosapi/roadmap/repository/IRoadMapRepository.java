package com.claro.gestionrecursosapi.roadmap.repository;

import com.claro.gestionrecursosapi.roadmap.entity.RoadMap;
import org.springframework.data.repository.CrudRepository;

public interface IRoadMapRepository extends CrudRepository<RoadMap, Integer> {

    RoadMap findByCodPryBrief (Integer codPryBrief);
}
