package com.claro.gestionrecursosapi.estimaciones.repository;

import com.claro.gestionrecursosapi.estimaciones.entity.CostoEstimacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICostoEstimacionRepository extends JpaRepository<CostoEstimacion, Integer> {

    List<CostoEstimacion> findAllByFechaFinIsNull();
}
