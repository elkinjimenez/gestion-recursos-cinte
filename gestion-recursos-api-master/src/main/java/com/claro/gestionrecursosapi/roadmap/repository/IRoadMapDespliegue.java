package com.claro.gestionrecursosapi.roadmap.repository;

import com.claro.gestionrecursosapi.roadmap.entity.DespliegueRoadMap;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoadMapDespliegue extends CrudRepository<DespliegueRoadMap, Integer> {

}
