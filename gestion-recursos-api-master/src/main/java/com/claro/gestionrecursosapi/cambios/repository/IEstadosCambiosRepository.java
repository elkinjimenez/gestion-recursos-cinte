package com.claro.gestionrecursosapi.cambios.repository;

import com.claro.gestionrecursosapi.cambios.entity.EstadoCambiosEntity;
import org.springframework.data.repository.CrudRepository;

public interface IEstadosCambiosRepository extends CrudRepository<EstadoCambiosEntity, Integer> {

}
