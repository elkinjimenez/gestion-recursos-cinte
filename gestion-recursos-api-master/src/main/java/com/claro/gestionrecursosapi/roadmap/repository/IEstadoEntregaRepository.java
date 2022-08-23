package com.claro.gestionrecursosapi.roadmap.repository;

import com.claro.gestionrecursosapi.roadmap.entity.EstadoEntrega;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoEntregaRepository extends CrudRepository<EstadoEntrega, Integer> {

}
