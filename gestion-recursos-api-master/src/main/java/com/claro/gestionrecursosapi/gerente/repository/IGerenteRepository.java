package com.claro.gestionrecursosapi.gerente.repository;

import com.claro.gestionrecursosapi.gerente.entity.GerenteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IGerenteRepository extends CrudRepository<GerenteEntity, Long> {

    @Query(nativeQuery = true,
            value = "SELECT EO.ID, EVU.CODEMPLEADO, EO.NOMBRE NOMBREGERENCIA, EVU.NOMBRE NOMBREGERENTE, EO.CODPADRE \n" +
                    "FROM DF_ESTRUCTURAORGANIZACIONAL EO \n" +
                    "LEFT JOIN DF_EMPLEADO_VU EVU ON EO.CODEMPLEADO_RESPONSABLE = EVU.CODEMPLEADO \n" +
                    "WHERE EO.CODESTRUCTURATIPO = 1 AND EVU.FECHAFIN IS NULL ")
    public Iterable<GerenteEntity> findAllGerentes();

    @Query(nativeQuery = true,
            value = "SELECT EO.ID, EVU.CODEMPLEADO, EO.NOMBRE NOMBREGERENCIA, EVU.NOMBRE NOMBREGERENTE,  EO.CODPADRE \n" +
                    "FROM DF_ESTRUCTURAORGANIZACIONAL EO \n" +
                    "LEFT JOIN DF_EMPLEADO_VU EVU ON EO.CODEMPLEADO_RESPONSABLE = EVU.CODEMPLEADO \n" +
                    "WHERE EO.CODESTRUCTURATIPO = 1 AND EO.CODEMPLEADO_RESPONSABLE =?1")
    public GerenteEntity findGerenteByCodEmpleado(Integer codEmpleado);


}
