package com.claro.gestionrecursosapi.cambios.repository;

import com.claro.gestionrecursosapi.cambios.entity.CambiosEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICambiosRepository extends CrudRepository<CambiosEntity, Integer> {

//    @Query("SELECT c.id, c.codCambio, c.fechaTcba, c.fechaPap,  " +
//            "c.gestorCodEmpleado,  c.resumen, c.idRlp, t.nomTipoLinea, c.estado, c.idCompromiso " +
//            " FROM CambiosEntity c JOIN TiposLineaEntity t ON c.tipoLinea  =  t.id")
//    public Iterable<CambiosEntity> findAll();


}
