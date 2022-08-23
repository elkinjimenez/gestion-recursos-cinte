package com.claro.gestionrecursosapi.estimaciones.repository;

import com.claro.gestionrecursosapi.estimaciones.entity.EstimacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IEstimacionRepository extends JpaRepository <EstimacionEntity, Integer> {
}
