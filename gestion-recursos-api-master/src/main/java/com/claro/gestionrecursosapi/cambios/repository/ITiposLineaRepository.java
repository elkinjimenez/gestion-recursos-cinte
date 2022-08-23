package com.claro.gestionrecursosapi.cambios.repository;

import com.claro.gestionrecursosapi.cambios.entity.TiposLineaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITiposLineaRepository extends CrudRepository<TiposLineaEntity, Integer> {
}
