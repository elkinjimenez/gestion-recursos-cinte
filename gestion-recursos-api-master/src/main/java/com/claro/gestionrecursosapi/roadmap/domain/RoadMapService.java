package com.claro.gestionrecursosapi.roadmap.domain;

import com.claro.gestionrecursosapi.roadmap.entity.*;
import com.claro.gestionrecursosapi.roadmap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadMapService implements IRoadMapService{

    @Autowired
    private IRoadMapRepository roadMapRepository;

    @Autowired
    private IRoadMapTipoRepository roadMapTipoRepository;

    @Autowired
    private IEstadoEntregaRepository estadoEntregaRepository;

    @Autowired
    private IEstadoRoadMapRepository estadoRoadMapRepository;

    @Autowired
    private IRoadMapDespliegue roadMapDespliegue;


    public RoadMap findByCodNovedad(Integer codPryBrief) {
        return roadMapRepository.findByCodPryBrief(codPryBrief);
    }

    public RoadMap modificarRoadMap(RoadMap roadMap){

        RoadMap roadMapActual = this.findByCodNovedad(roadMap.getCodPryBrief());
        if (roadMapActual == null){
            roadMap.setId(null);
            return roadMapRepository.save(roadMap);
        }
        roadMapActual.setNombreMicroservicio(roadMap.getNombreMicroservicio());
        roadMapActual.setTipo(roadMap.getTipo());
        roadMapActual.setDespliegue(roadMap.getDespliegue());
        roadMapActual.setMongoVersion(roadMap.getMongoVersion());
        roadMapActual.setEstado(roadMap.getEstado());
        roadMapActual.setJdkVersion(roadMap.getJdkVersion());
        roadMapActual.setWoEstandares(roadMap.getWoEstandares());
        roadMapActual.setWoDocumentacion(roadMap.getWoDocumentacion());
        roadMapActual.setDescripcionServicio(roadMap.getDescripcionServicio());
        roadMapActual.setTryscape(roadMap.getTryscape());
        roadMapActual.setUrlDev(roadMap.getUrlDev());
        roadMapActual.setUrlQa(roadMap.getUrlQa());
        roadMapActual.setUrlProduccion(roadMap.getUrlProduccion());
        roadMapActual.setIntroduccionServicio(roadMap.isIntroduccionServicio());

        return roadMapRepository.save(roadMapActual);
    }

    @Override
    public List<RoadMap> findAll() {
        return (List<RoadMap>) roadMapRepository.findAll();
    }

    @Override
    public RoadMap findById(Integer id) {
        return roadMapRepository.findById(id).orElse(null);
    }

    @Override
    public RoadMap save(RoadMap roadMap) {
        return roadMapRepository.save(roadMap);
    }

    @Override
    public List<TipoRoadMap> tipoRoadMapFindAll() {
        return (List<TipoRoadMap>) roadMapTipoRepository.findAll();
    }

    @Override
    public List<EstadoEntrega> estadoEntregaFindAll() {
        return (List<EstadoEntrega>) estadoEntregaRepository.findAll();
    }

    @Override
    public List<EstadoRoadMap> estadoRoadMapFindAll() {
        return (List<EstadoRoadMap>) estadoRoadMapRepository.findAll();
    }

    @Override
    public List<DespliegueRoadMap> despliegueRoadMapFindAll() {
        return (List<DespliegueRoadMap>) roadMapDespliegue.findAll();
    }
}
