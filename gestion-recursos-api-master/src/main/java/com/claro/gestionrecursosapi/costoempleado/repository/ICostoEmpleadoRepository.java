package com.claro.gestionrecursosapi.costoempleado.repository;

import com.claro.gestionrecursosapi.costoempleado.entity.CostoEmpleadoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICostoEmpleadoRepository extends CrudRepository<CostoEmpleadoEntity, Integer> {

    List<CostoEmpleadoEntity> findByCodEmpleado (Integer codEmpleado);
}
