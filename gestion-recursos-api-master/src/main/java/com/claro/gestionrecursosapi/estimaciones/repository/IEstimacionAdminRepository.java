package com.claro.gestionrecursosapi.estimaciones.repository;

import com.claro.gestionrecursosapi.estimaciones.dto.DonutChartDataDTO;
import com.claro.gestionrecursosapi.estimaciones.dto.EstimacionDownloadDTO;
import com.claro.gestionrecursosapi.estimaciones.dto.EstimacionXProyectoDTO;
import com.claro.gestionrecursosapi.estimaciones.entity.EstimacionAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEstimacionAdminRepository extends JpaRepository<EstimacionAdmin, Integer> {


    @Query(value = "SELECT new com.claro.gestionrecursosapi.estimaciones.dto.EstimacionXProyectoDTO( p.id, p.nombre ) FROM  ProyectoEntity p WHERE p.id IN (SELECT et.codProyecto FROM  EstimacionAdmin et GROUP BY et.codProyecto) ")
    List<EstimacionXProyectoDTO> estimacionXProyecto();

    List<EstimacionAdmin> findByCodProyecto(int codProyecto);

    @Query(value = "SELECT new com.claro.gestionrecursosapi.estimaciones.dto.DonutChartDataDTO( p.nombre, SUM(e.costo) )  " +
            "FROM EstimacionAdmin e " +
            "JOIN ProyectoEntity p ON p.id = e.codProyecto " +
            "where e.codProyecto = :codProyecto " +
            "GROUP BY p.nombre")
    DonutChartDataDTO costoEstimacionXProyecto(int codProyecto);

    @Query(value = "SELECT new com.claro.gestionrecursosapi.estimaciones.dto.DonutChartDataDTO( p.nombre, SUM(e.cantidad)  )  " +
            "FROM EstimacionAdmin e " +
            "JOIN ProyectoEntity p ON p.id = e.codProyecto " +
            "where e.codProyecto = :codProyecto " +
            "GROUP BY p.nombre")
    DonutChartDataDTO horaEstimacionXProyecto(int codProyecto);

    @Query(value = "SELECT new com.claro.gestionrecursosapi.estimaciones.dto.DonutChartDataDTO( EM.nombre , ((SUM(EC.horas)*CO.factorPunto) * CO.costoPunto)) " +
            "FROM ProyectoEntity P " +
            "INNER JOIN TareaEntity T ON P.id = T.codproyecto " +
            "INNER JOIN EmpleadocontrolEntity EC ON EC.codtarea = T.id " +
            "INNER JOIN EmpleadoVU EM ON EM.codempleado  = EC.codempleado " +
            "INNER JOIN CostoEmpleadoEntity CO ON CO.codEmpleado = EM.codempleado " +
            "INNER JOIN EstructuraorganizacionalEntity EO ON  EO.id = EM.codestructuraorganizacional " +
            "WHERE P.id = :codProyecto " +
            "AND EO.jerarquia LIKE :jerarquia || '%' " +
            "AND CO.hasta IS NULL " +
            "GROUP BY EM.nombre , CO.factorPunto,  CO.costoPunto " +
            "ORDER BY EM.nombre ASC ")
    List<DonutChartDataDTO> costoxEmpleado(Integer codProyecto, String jerarquia);

    @Query(value = "SELECT new com.claro.gestionrecursosapi.estimaciones.dto.EstimacionDownloadDTO(e.entregable, ce.perfil, e.preRequisito, e.cantidad, e.fechaInicio, e.fechaFin, e.costo, e.porcentaje) " +
            "FROM EstimacionAdmin e " +
            "JOIN CostoEstimacion ce on ce.id = e.perfil " +
            "WHERE e.codProyecto = :codProyecto")
    List<EstimacionDownloadDTO> downloadExcel(Integer codProyecto);


    @Query(value = "SELECT SUM(SUM (((EC.horas) * CO.factorPunto) * CO.costoPunto))  " +
            "FROM ProyectoEntity P " +
            "INNER JOIN TareaEntity T ON P.id = T.codproyecto " +
            "INNER JOIN EmpleadocontrolEntity EC ON EC.codtarea = T.id " +
            "INNER JOIN EmpleadoVU EM ON EM.codempleado = EC.codempleado " +
            "INNER JOIN CostoEmpleadoEntity CO ON CO.codEmpleado = EM.codempleado " +
            "INNER JOIN EstructuraorganizacionalEntity EO ON  EO.id = EM.codestructuraorganizacional " +
            "WHERE P.id = :codproyecto AND EO.jerarquia  LIKE :jerarquia || '%' " +
            "AND CO.hasta IS NULL " +
            "GROUP BY EM.codempleado,  EM.nombre  "
    )
    Long valorTotalxCoordinacion(Integer codproyecto, @Param("jerarquia") String jerarquia);


    @Query(value = "SELECT SUM(SUM (((EC.horas) * CO.factorPunto) * CO.costoPunto))  " +
            "FROM ProyectoEntity P " +
            "INNER JOIN TareaEntity T ON P.id = T.codproyecto " +
            "INNER JOIN EmpleadocontrolEntity EC ON EC.codtarea = T.id " +
            "INNER JOIN EmpleadoVU EM ON EM.codempleado = EC.codempleado " +
            "INNER JOIN CostoEmpleadoEntity CO ON CO.codEmpleado = EM.codempleado " +
            "INNER JOIN EstructuraorganizacionalEntity EO ON  EO.id = EM.codestructuraorganizacional " +
            "WHERE P.id = :codproyecto " +
            "AND CO.hasta IS NULL " +
            "GROUP BY EM.codempleado,  EM.nombre  "
    )
    Long valorTotal(Integer codproyecto);

}