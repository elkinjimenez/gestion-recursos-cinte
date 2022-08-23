package com.claro.gestionrecursosapi.empleado.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEstructuraOrganizacionalVU;

@Repository
@Transactional
public interface IEmpleadoEstructuraOrganizacionalRepository extends CrudRepository<EmpleadoEstructuraOrganizacionalVU, Integer> {


      @Query("SELECT A FROM EmpleadoEstructuraOrganizacionalVU A "
      		+ "WHERE A.idEstructuraEmpleado = :codEstructura "
      		+ "AND A.fechafin is null "
      		+ "AND (A.codestructuraTipo = 1 OR A.codestructuraTipo = 2) "
      		+ "AND  A.codempleadoresponsable IS NOT NULL "
      		)
      
      public EmpleadoEstructuraOrganizacionalVU getEmpleadoByActivosByEstructura(@Param("codEstructura") Integer codEstructura);
      
     

      @Query("SELECT A FROM EmpleadoEstructuraOrganizacionalVU A "
      		+ "WHERE A.idEmpleado = :codEmpleado "
      		+ "AND A.fechafin is null "
      		)
      
      public EmpleadoEstructuraOrganizacionalVU findByCodEmpleado(@Param("codEmpleado") Integer codEmpleado);
      
      @Query("SELECT A FROM EmpleadoEstructuraOrganizacionalVU A "
        		+ "WHERE A.estructuraId = :idEstructura "
        		+ "AND A.fechafin is null "
        		)
        
        public EmpleadoEstructuraOrganizacionalVU findByEstructuraId(@Param("idEstructura") Integer idEstructura);
}