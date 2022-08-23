package com.claro.gestionrecursosapi.empleado.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEntity;

@Repository
public interface IEmpleadoRepository extends CrudRepository<EmpleadoEntity, Integer>{

	@Query(value = "SELECT E FROM EmpleadoEntity E WHERE E.usuarioclaro = :usuarioclaro and E.fechafin is null")
	public EmpleadoEntity findByusuarioclaro (@Param("usuarioclaro") String usuarioclaro);

	@Query(value = "SELECT E FROM EmpleadoEntity E WHERE E.codpersona = :codpersona")
	public EmpleadoEntity findByCodPersona(@Param("codpersona") Integer codpersona);
	
	@Query(value = "SELECT E FROM EmpleadoEntity E WHERE E.codpersona = :codpersona AND E.fechainicio <= :fecha AND (E.fechafin IS NULL OR E.fechafin > :fecha)"
			+ " AND E.id = (SELECT MAX(EM.id) FROM EmpleadoEntity EM WHERE EM.codpersona = E.codpersona AND EM.fechainicio <= :fecha AND (EM.fechafin IS NULL OR EM.fechafin > :fecha))")
	public EmpleadoEntity findActivoByCodPersona(@Param("codpersona") Integer codpersona, @Param("fecha") Date fecha);
	
	@Query(value = "SELECT E FROM EmpleadoEntity E WHERE E.usuarioclaro = :usuarioclaro AND (E.fechafin IS NULL OR E.fechafin > :fecha)")
	public EmpleadoEntity findActivoByUsuarioclaro (String usuarioclaro, @Param("fecha") Date fecha);
	
	@Query(value = "SELECT E FROM EmpleadoEntity E WHERE E.codestructuraorganizacional = :codestructuraorganizacional AND E.fechafin IS NULL " )
	public List<EmpleadoEntity> findByCodestructuraorganizacional (@Param("codestructuraorganizacional") Integer codestructuraorganizacional);
        
	
}
