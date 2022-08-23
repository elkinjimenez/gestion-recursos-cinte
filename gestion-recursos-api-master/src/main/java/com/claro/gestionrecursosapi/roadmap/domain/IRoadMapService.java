package com.claro.gestionrecursosapi.roadmap.domain;

import com.claro.gestionrecursosapi.roadmap.entity.*;

import java.util.List;

public interface IRoadMapService {

    public List<RoadMap> findAll();

    public RoadMap findById(Integer id);

    public RoadMap save(RoadMap roadMap);

    public List<TipoRoadMap> tipoRoadMapFindAll();

    public List<EstadoEntrega> estadoEntregaFindAll();

    public List<EstadoRoadMap> estadoRoadMapFindAll();

    List<DespliegueRoadMap> despliegueRoadMapFindAll();
}
