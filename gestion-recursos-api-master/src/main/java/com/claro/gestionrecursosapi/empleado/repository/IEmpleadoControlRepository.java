package com.claro.gestionrecursosapi.empleado.repository;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadocontrolEntity;

@Repository
public interface IEmpleadoControlRepository extends CrudRepository<EmpleadocontrolEntity, Integer> {

	@Query(value = "SELECT EC FROM EmpleadocontrolEntity EC WHERE EC.codtarea = :codtarea ORDER BY EC.fechahorainicio DESC")
	public Iterable<EmpleadocontrolEntity> findAllByCodTarea(@Param("codtarea") Integer codtarea);
	
	@Query(value = "SELECT SUM(ec.horas) FROM EmpleadocontrolEntity ec WHERE ec.codtarea = :codtarea")
	public BigDecimal sumHorasByCodtarea(@Param("codtarea") Integer codtarea);
	
	@Query(value = "SELECT SUM(ec.horas) "
				+ "	FROM EmpleadocontrolEntity ec "
				+ " INNER JOIN TareaEntity t ON t.id = ec.codtarea"
				+ " WHERE t.estado = 'A' AND ec.codempleado = :codempleado AND ec.fechahorainicio BETWEEN :FechaIni AND :FechaFin")
	public BigDecimal sumHorasByFecha(@Param("codempleado") Integer codempleado, @Param("FechaIni") Date fechaIni, @Param("FechaFin") Date fechaFin);
	
	@Query(value = "SELECT MIN(ec.fechahorainicio) FROM EmpleadocontrolEntity ec WHERE ec.codtarea = :codtarea")
	public Date minFechaIniByCodtarea(@Param("codtarea") Integer codtarea);
	
	@Query(value = "SELECT MAX(ec.fechahorafin) FROM EmpleadocontrolEntity ec WHERE ec.codtarea = :codtarea")
	public Date maxFechaFinByCodtarea(@Param("codtarea") Integer codtarea);
	

	@Query(value = "SELECT SUM(ec.horas)  FROM EmpleadocontrolEntity ec "
			+ " WHERE ec.codempleado=:codempleado and ec.codtarea = :codtarea"
			+ "   and fechahorainicio = :fechahorainicio ")
	public Integer horasTareaFechaPorRecurso(@Param("codempleado") Integer codempleado,
			                                 @Param("codtarea") Integer codtarea,
			                                 @Param("fechahorainicio") Date fechahorainicio);
	
	@Query(value = "SELECT SUM(ec.horas)  FROM EmpleadocontrolEntity ec "
			+ " WHERE ec.codempleado=:codempleado "
			+ "   and fechahorainicio = :fechahorainicio ")
	public Integer horasFechaPorRecurso(@Param("codempleado") Integer codempleado,
			                                 @Param("fechahorainicio") Date fechahorainicio);
	
}
