package com.claro.gestionrecursosweb.estimaciones.domain;

import com.claro.gestionrecursosweb.estimaciones.dto.EstimacionDto;

import java.util.List;

public interface IEstimacionService {
    List<EstimacionDto> traerTodos();
    EstimacionDto buscarPorId(Integer idEstimacion);
    void guardar(EstimacionDto estimacion);
}
