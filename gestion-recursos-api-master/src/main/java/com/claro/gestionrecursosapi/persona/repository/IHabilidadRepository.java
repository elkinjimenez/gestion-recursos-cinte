package com.claro.gestionrecursosapi.persona.repository;

import com.claro.gestionrecursosapi.persona.entity.HabilidadEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHabilidadRepository extends CrudRepository<HabilidadEntity, Integer> {

    @Query("SELECT H FROM PersonahabilidadEntity PH INNER JOIN HabilidadEntity H ON PH.codhabilidad = H.id  WHERE PH.codpersona = :codPersona")
    List<HabilidadEntity> findByIdcodpersona(@Param("codPersona") Integer codPersona);

    boolean existsByNombre(String nombre);
}
