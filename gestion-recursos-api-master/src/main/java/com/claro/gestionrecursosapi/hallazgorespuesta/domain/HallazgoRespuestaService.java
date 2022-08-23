package com.claro.gestionrecursosapi.hallazgorespuesta.domain;

import com.claro.gestionrecursosapi.compromisosfabrica.entity.CompromisosFabricaEntity;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoEntity;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoProyectosEntity;
import com.claro.gestionrecursosapi.hallazgorespuesta.repository.IHallazgoRespuestaRepository;
import com.claro.gestionrecursosapi.novedad.entity.NovedadEntity;
import com.claro.gestionrecursosapi.novedad.repository.INovedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HallazgoRespuestaService {

    @Autowired
    private IHallazgoRespuestaRepository hallazgoRespuestaRepository;

    public Iterable<HallazgoEntity> getAllHallazgos(){

        return hallazgoRespuestaRepository.findAll();

    }

    public HallazgoEntity getHallazgoById(Integer id) {
        HallazgoEntity hallazgo = hallazgoRespuestaRepository.findById(id).get();

        if(hallazgo.getId() == id) {
            return hallazgo;
        } else {
            return null;
        }
    }

    public HallazgoEntity modificarHallazgo(HallazgoEntity hallazgo) {

        Optional<HallazgoEntity> hallazgoDb = hallazgoRespuestaRepository.findById(hallazgo.getId());

        hallazgoDb.get().setEsHallazgo(hallazgo.getEsHallazgo());
        hallazgoDb.get().setProblemaIdentificado(hallazgo.getProblemaIdentificado());
        hallazgoDb.get().setSolucionEntregada(hallazgo.getSolucionEntregada());
        hallazgoDb.get().setDetalleRespuesta(hallazgo.getDetalleRespuesta());
        hallazgoDb.get().setEvidenciaRespuesta(hallazgo.getEvidenciaRespuesta());
        hallazgoDb.get().setFechaRespuesta(new Date());

        return hallazgoRespuestaRepository.save(hallazgoDb.get());
    }

}
