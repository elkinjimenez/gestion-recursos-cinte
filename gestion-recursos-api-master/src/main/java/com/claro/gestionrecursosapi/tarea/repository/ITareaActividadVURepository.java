package com.claro.gestionrecursosapi.tarea.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.tarea.entity.TareaActividadVU;



@Repository
public interface ITareaActividadVURepository extends CrudRepository<TareaActividadVU, Integer> {

	@Query(	  " SELECT  t "
			+ " FROM TareaActividadVU t "
			+ " LEFT JOIN EmpleadoEntity e ON e.codpersona = NVL(t.codpersonaasignado, t.codpersonacreo)"
			+ " LEFT JOIN UsuarioEntity usuarioConsulta ON usuarioConsulta.usuario = :CLUSUARIO"
			+ " LEFT JOIN EmpleadoEntity empleadoConsulta ON empleadoConsulta.codpersona = usuarioConsulta.codpersona"
			+ " LEFT JOIN EstructuraorganizacionalEntity emplEstOrganizacional ON emplEstOrganizacional.id = e.codestructuraorganizacional "
			+ " INNER JOIN EstructuraorganizacionalEntity emplConsulEstOrganizacional ON emplConsulEstOrganizacional.id = empleadoConsulta.codestructuraorganizacional "
			+ " WHERE (empleadoConsulta.id IS NOT NULL OR (empleadoConsulta.id IS NULL AND t.id <= 0)) "
			+ " AND (emplEstOrganizacional.jerarquiaJerarca IS NULL OR emplConsulEstOrganizacional.jerarquiaJerarca = emplEstOrganizacional.jerarquiaJerarca)"
			+ " AND (:codproyecto IS NULL OR t.codproyecto = :codproyecto)"
			+ " AND (:codproveedor IS NULL OR t.codproveedor = :codproveedor) "
			+ " AND (:fechafin IS NULL OR NVL(NVL(t.fechainireal, t.fechainiestimada), t.fechainicio) <= :fechafin) "
			+ " AND (:fechainicio IS NULL OR NVL(NVL(t.fechafinreal, t.fechafinestimada), t.fechafin) >= :fechainicio) ORDER BY t.text ")
	public Iterable<TareaActividadVU> findAllByFiltro(@Param("CLUSUARIO") String CLUSUARIO, @Param("codproyecto") Integer codproyecto, 
									 	@Param("codproveedor") Integer codproveedor,
									 	@Param("fechainicio") Date fechainicio,
									 	@Param("fechafin") Date fechafin);
	
	@Query( nativeQuery=true, 
			value=" select * "+
			" from df_tareaactividad_vu q\r\n" + 
			" start with exists (\r\n" + 
			"              select 1\r\n" + 
			"              from df_tareaactividad_vu t\r\n" + 
			"              left join df_empleado e on e.codpersona = nvl(t.codpersonaasignado, t.codpersonacreo)\r\n" + 
			"              left join df_usuario usuarioConsulta on usuarioConsulta.usuario = :CLUSUARIO\r\n" + 
			"              left join df_empleado empleadoConsulta on empleadoConsulta.codpersona = usuarioConsulta.codpersona\r\n" + 
			"                            and empleadoConsulta.codestructuraorganizacional = e.codestructuraorganizacional   \r\n" + 
			"              where q.id = t.id\r\n" + 
			"                            and (empleadoConsulta.id is not null or (empleadoConsulta.id is null and t.id <= 0))\r\n" + 
			"                            and (:codproyecto=0 or t.codproyecto = :codproyecto)\r\n" + 
			"                            and (:codproveedor=0 or t.codproveedor = :codproveedor)\r\n" +
			"                            and (:codpersona=0 or t.codpersonaasignado = :codpersona)\r\n" + 
			"                            and (:fechafin IS NULL or nvl(nvl(t.fechainireal, t.fechainiestimada), t.fechainicio) <= :fechafin)\r\n" + 
			"                            and (:fechainicio is null or nvl(nvl(t.fechafinreal, t.fechafinestimada), t.fechafin) >= :fechainicio))\r\n" + 
			" connect by q.id = prior q.parent  ")
	public  Iterable<TareaActividadVU> findByPersona(@Param("CLUSUARIO") String CLUSUARIO, 
			@Param("codproyecto") Integer codproyecto, 
		 	@Param("codproveedor") Integer codproveedor, 
		 	@Param("codpersona") Integer codpersona,
		 	@Param("fechainicio") Date fechainicio,
		 	@Param("fechafin") Date fechafin);

	@Query(nativeQuery=true,
			value=" select * "+
					" from df_tareaactividad_vu q\r\n" +
					" start with exists (\r\n" +
					"              select 1\r\n" +
					"              from df_tareaactividad_vu t\r\n" +
					"              left join df_empleado e on e.codpersona = nvl(t.codpersonaasignado, t.codpersonacreo)\r\n" +
					"              where q.id = t.id\r\n" +
					"                            and (:codproyecto=0 or t.codproyecto = :codproyecto)\r\n" +
					"                            and (:codproveedor=0 or t.codproveedor = :codproveedor)\r\n" +
					"                            and (:codpersona=0 or t.codpersonaasignado = :codpersona)\r\n" +
					"                            and (:fechafin IS NULL or nvl(nvl(t.fechainireal, t.fechainiestimada), t.fechainicio) <= :fechafin)\r\n" +
					"                            and (:fechainicio is null or nvl(nvl(t.fechafinreal, t.fechafinestimada), t.fechafin) >= :fechainicio))\r\n" +
					" connect by q.id = prior q.parent  ")
	Iterable<TareaActividadVU> findByPersonaAdmin(@Param("codpersona") Integer codpersona,
												  @Param("fechainicio") Date fechainicio,
												  @Param("fechafin") Date fechafin,
												  @Param("codproveedor") Integer codproveedor,
												  @Param("codproyecto") Integer codproyecto);
}
