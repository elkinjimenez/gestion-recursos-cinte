package com.claro.gestionrecursosapi.perfil.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.perfil.entity.LineasproductoEntity;

@Repository
public interface ILineaProductoRepository extends CrudRepository<LineasproductoEntity, Integer> {

}
