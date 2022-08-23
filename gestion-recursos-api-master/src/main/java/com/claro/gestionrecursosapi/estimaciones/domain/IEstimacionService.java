package com.claro.gestionrecursosapi.estimaciones.domain;

import com.claro.gestionrecursosapi.estimaciones.dto.EstimacionXProyectoDTO;
import com.claro.gestionrecursosapi.estimaciones.entity.CostoEstimacion;
import com.claro.gestionrecursosapi.estimaciones.entity.EstimacionAdmin;
import java.util.List;

public interface IEstimacionService {

    List<EstimacionAdmin> findAll();

    EstimacionAdmin finById(Integer id);

    List<EstimacionAdmin> saveAll(List<EstimacionAdmin> estimaciones);

    List<CostoEstimacion> findAllByFechaFinIsNull();

    List<EstimacionXProyectoDTO> estimacionXProyecto();

    List<EstimacionAdmin> estimacionXCodProyecto(int codProyecto);

    void deleteById(Integer id);
}