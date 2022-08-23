package com.claro.gestionrecursosapi.estructura.repository;

import com.claro.gestionrecursosapi.estimaciones.dto.DonutChartDataDTO;
import com.claro.gestionrecursosapi.gerente.entity.GerenteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.claro.gestionrecursosapi.estructura.entity.EstructuraorganizacionalEntity;
import org.springframework.data.repository.query.Param;


public interface IEstructuraOrganizacionalRepository extends CrudRepository<EstructuraorganizacionalEntity, Integer> {

    @Query("SELECT p FROM EstructuraorganizacionalEntity p WHERE p.codestructuratipo = 3")
    Iterable<EstructuraorganizacionalEntity> findEstructuraorganizacionalEntityByCodestructuratipo();

    @Query("SELECT new com.claro.gestionrecursosapi.gerente.entity.GerenteEntity(EO.id, EVU.codempleado, EO.nombre, EVU.nombre, EO.codestructuratipo,EO.codpadre) \n" +
            "FROM EstructuraorganizacionalEntity EO \n" +
            "LEFT JOIN EmpleadoVU EVU ON EO.codempleado_responsable = EVU.codempleado \n" +
            "WHERE EO.codestructuratipo = :tipoEstructura AND EO.codpadre = :padre AND EVU.fechafin IS NULL ")
    Iterable<GerenteEntity> findJerarquiaAndTipoEstructura(Integer padre, Integer tipoEstructura);

    @Query("SELECT EO \n" +
            "FROM EstructuraorganizacionalEntity EO \n" +
            "LEFT JOIN EmpleadoVU EVU ON EO.codempleado_responsable = EVU.codempleado \n" +
            "WHERE EVU.fechafin IS NULL AND (EO.codestructuratipo = :tipoEstructura OR  EO.codestructuratipo = 4 )   AND EO.jerarquia LIKE :jerarquia || '%' ")
    Iterable<EstructuraorganizacionalEntity> findIdXJerarquia(String jerarquia, Integer tipoEstructura);


    @Query("SELECT DISTINCT eo \n " +
            "FROM ProyectoEntity p JOIN TareaEntity tar ON p.id = tar.codproyecto \n " +
            "JOIN EmpleadocontrolEntity econ ON econ.codtarea = tar.id \n " +
            "JOIN EmpleadoEntity emp ON emp.id = econ.codempleado \n" +
            "JOIN EstructuraorganizacionalEntity eo ON eo.id = emp.codestructuraorganizacional \n" +
            "WHERE p.id = :codProyecto AND emp.fechafin IS NULL " +
            "ORDER BY eo.nombre ASC"
    )
    Iterable<EstructuraorganizacionalEntity> getEstructuraXCodProyecto(@Param("codProyecto") Integer codProyecto);

    @Query("SELECT E FROM EstructuraorganizacionalEntity E WHERE E.codpadre = :codPadre ")
    Iterable<EstructuraorganizacionalEntity> getEstructuraxCodPadre(@Param("codPadre") int codPadre);

    @Query("SELECT E FROM EstructuraorganizacionalEntity E WHERE E.jerarquia  LIKE :jerarquia || '%' ")
    List<EstructuraorganizacionalEntity> getEstructuraXGerencia(@Param("jerarquia") String jerarquia);

    @Query("SELECT E FROM EstructuraorganizacionalEntity E WHERE E.codestructuratipo  = :codTipo  ")
    List<EstructuraorganizacionalEntity> getEstructuraXTipo(@Param("codTipo") Integer codTipo);

    @Query("SELECT E FROM EstructuraorganizacionalEntity E WHERE E.codestructuratipo = 3 AND E.jerarquia LIKE :jerarquia || '%' ")
    List<EstructuraorganizacionalEntity> getEstructurasXJerarquia(@Param("jerarquia") String jerarquia);

    @Query(nativeQuery = true,  value = "SELECT DISTINCT  SUBSTR( EO.JERARQUIA, 0 , 20)\n" +
            "FROM DF_PROYECTO P \n" +
            "INNER JOIN DF_TAREA T ON P.ID = T.CODPROYECTO\n" +
            "INNER JOIN DF_EMPLEADOCONTROL EC ON EC.CODTAREA = T.ID\n" +
            "INNER JOIN DF_EMPLEADO_VU EM ON EM.CODEMPLEADO = EC.CODEMPLEADO\n" +
            "INNER JOIN DF_COSTO_EMPLEADO CO ON CO.CODEMPLEADO = EM.CODEMPLEADO\n" +
            "INNER JOIN DF_ESTRUCTURAORGANIZACIONAL EO ON  EO.ID = EM.CODESTRUCTURAORGANIZACIONAL \n" +
            "WHERE P.ID = :codProyecto AND  EM.FECHAFIN IS NULL ")
    List<String> jerarquiasporProyecto(int codProyecto);

    @Query(value = "SELECT EO " +
            "FROM EstructuraorganizacionalEntity EO " +
            "WHERE  EO.jerarquia IN  (:jerarquias) ")
    List<EstructuraorganizacionalEntity> getEstructuraFiltroDonuts(@Param("jerarquias") List<String> jerarquias);


}