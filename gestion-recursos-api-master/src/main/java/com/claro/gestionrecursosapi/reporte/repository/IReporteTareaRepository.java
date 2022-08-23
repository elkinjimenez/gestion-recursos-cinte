package com.claro.gestionrecursosapi.reporte.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.reporte.entity.ReporteTareaVU;

@Repository
public interface IReporteTareaRepository extends CrudRepository<ReporteTareaVU, Integer> {

	@Query(	  " SELECT t "
			+ " FROM ReporteTareaVU t "
			+ " INNER JOIN EmpleadoEntity e ON e.id = t.codempleadoasignado"
			+ " INNER JOIN UsuarioEntity usuarioConsulta ON usuarioConsulta.usuario = :CLUSUARIO"
			+ " INNER JOIN EmpleadoEntity empleadoConsulta ON empleadoConsulta.codpersona = usuarioConsulta.codpersona "
			+ " INNER JOIN EstructuraorganizacionalEntity emplEstOrganizacional ON emplEstOrganizacional.id = e.codestructuraorganizacional "
			+ "WHERE 1 = 1 "
			+ "AND (:fechafin IS NULL OR NVL(NVL(t.fechainireal, t.fechainiestimada), t.fechacreacion) <= :fechafin) "
			+ "AND (:fechainicio IS NULL OR NVL(NVL(t.fechafinreal, t.fechafinestimada), t.fechacreacion) >= :fechainicio) "
			+ "AND (:codproyecto IS NULL OR t.codproyecto = :codproyecto) "
			+ "AND (:codproveedor IS NULL OR t.codproveedor = :codproveedor) "
			+ "AND (:tarea IS NULL OR UPPER(t.tarea) LIKE CONCAT('%', UPPER(:tarea), '%')) "
			+ "AND (:codtareatipo IS NULL OR t.codtareatipo = :codtareatipo) "
			+ "AND (:codtareaestado IS NULL OR t.codtareaestado = :codtareaestado) "
			+ "AND (:eslogro IS NULL OR NVL(t.eslogro, 0) = :eslogro)"
			+ "AND (:codempleadocreo IS NULL OR t.codempleadocreo = :codempleadocreo) "
			+ "AND (:codempleadoasignado IS NULL OR t.codempleadoasignado = :codempleadoasignado) "
			+ "AND emplEstOrganizacional.jerarquia LIKE :jerarquia || '%' "
			+ "ORDER BY t.tarearuta")
	public Iterable<ReporteTareaVU> findAll(@Param("CLUSUARIO") String CLUSUARIO,
											@Param("fechainicio") Date fechainicio, @Param("fechafin") Date fechafin,
											@Param("codproyecto") Integer codproyecto, @Param("codproveedor") Integer codproveedor, @Param("tarea") String tarea, 
											@Param("codtareatipo") Integer codtareatipo, @Param("codtareaestado") Integer codtareaestado, @Param("eslogro") Boolean eslogro,
											@Param("codempleadocreo") Integer codempleadocreo, @Param("codempleadoasignado") Integer codempleadoasignado,
											@Param("jerarquia") String jerarquia);
	
}
