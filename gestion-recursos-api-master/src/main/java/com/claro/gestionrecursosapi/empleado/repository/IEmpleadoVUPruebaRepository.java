package com.claro.gestionrecursosapi.empleado.repository;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoPruebaVU;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoVU;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmpleadoVUPruebaRepository extends CrudRepository<EmpleadoPruebaVU, Integer> {

	@Query(	  "SELECT e "
			+ "FROM EmpleadoVU e "
			+ "INNER JOIN EmpleadoEntity em ON em.id = e.codempleado "
			+ "INNER JOIN UsuarioEntity usuarioConsulta ON usuarioConsulta.usuario = :CLUSUARIO "
			+ "INNER JOIN EmpleadoEntity empleadoConsulta ON empleadoConsulta.codpersona = usuarioConsulta.codpersona "
			+ "INNER JOIN EstructuraorganizacionalEntity emplEstOrganizacional ON emplEstOrganizacional.id = em.codestructuraorganizacional "
			+ "WHERE e.fechafin is null AND "
			+ "emplEstOrganizacional.jerarquia LIKE :jerarquia || '%'")
	public Iterable<EmpleadoVU> findAll(@Param("CLUSUARIO") String CLUSUARIO, @Param("jerarquia") String jerarquia);
	
	
	@Query(value = "SELECT E FROM EmpleadoVU E WHERE codempleado = :codempleado and E.fechafin is null")
	public Optional<EmpleadoVU> findByCodEmpleado(@Param("codempleado") Integer codempleado);

	/*
	* Cod perfil 75 es para gerente
	* */
	@Query("SELECT e FROM EmpleadoVU e WHERE e.codperfil = 75")
	public Iterable<EmpleadoVU> findAllGerentes();
	
}
