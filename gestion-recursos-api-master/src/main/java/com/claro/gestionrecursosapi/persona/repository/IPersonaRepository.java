package com.claro.gestionrecursosapi.persona.repository;

import java.util.List;
import java.util.Optional;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.persona.entity.PersonaEntity;

@Repository
public interface IPersonaRepository extends CrudRepository<PersonaEntity, Integer> {

    PersonaEntity findByCorreo(String correo);

    PersonaEntity findByCodtipodocumentoAndNumerodocumento(int codTipoDocumento, long numeroDocumento);

    Optional<PersonaEntity> findByIdAndCodtipodocumento(int id, int codTipoDocumento);

    @Query("SELECT P FROM PersonaEntity P INNER JOIN PersonahabilidadEntity PH ON P.id = PH.codpersona WHERE PH.codhabilidad = :idHabilidad")
    Iterable<PersonaEntity> findByIdHabilidad(@Param("idHabilidad") Integer idHabilidad);

    @Query("SELECT per FROM PersonaEntity per INNER JOIN EmpleadoEntity em ON per.id = em.codpersona WHERE em.id = :codEmpleado")
    PersonaEntity getPersonaXCodEmpleado(@Param("codEmpleado") Integer codEmpleado);

    @Query("SELECT p FROM PersonaEntity p LEFT JOIN EmpleadoEntity e  ON e.codpersona = p.id WHERE e.codpersona IS NULL ")
    List<PersonaEntity> getPersonasSinVinculacion();

}
