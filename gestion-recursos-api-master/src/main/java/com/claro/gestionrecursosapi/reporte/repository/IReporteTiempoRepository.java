package com.claro.gestionrecursosapi.reporte.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.reporte.entity.ReporteTiempo;

@Repository
public interface IReporteTiempoRepository extends JpaRepository<ReporteTiempo, Integer> {

	@Query(nativeQuery = true, value = ""
			+ "WITH DATOS AS (\r\n" + 
			"    SELECT  PY.NOMBRE PROYECTO, \r\n" + 
			"            FN_DF_TAREAMIGA(T.ID) TAREA,\r\n" + 
			"            E.PROVEEDOR,\r\n" + 
			"            TRIM(REPLACE(E.NOMBRE,'  ',' ')) RECURSO,\r\n" + 
			"            E.CODEMPLEADO,\r\n" + 
			"            E.CODPERSONA,\r\n" + 
			"            E.FECHAINICIO FECHAINGRESO,\r\n" + 
			"            SUM(EC.HORAS) HORAS,\r\n" + 
			"            MIN(EC.FECHAHORAINICIO) FECHAINICIO,\r\n" + 
			"            MAX(EC.FECHAHORAFIN) FECHAFIN,\r\n" + 
			"            (SELECT SUM(PA2.HORAS)\r\n" + 
			"             FROM DF_PERSONAAUSENCIA PA2\r\n" + 
			"             WHERE PA2.CODPERSONA = E.CODPERSONA AND\r\n" + 
			"                   PA2.FECHAINICIO BETWEEN :fechainicio AND :fechafin) TOTAL_HORAS_AUSENCIAS\r\n" + 
			"    FROM DF_PROYECTO PY\r\n" + 
			"    INNER JOIN DF_TAREA T ON T.CODPROYECTO = PY.ID\r\n" + 
			"    INNER JOIN DF_EMPLEADOCONTROL EC ON EC.CODTAREA = T.ID\r\n" + 
			"    INNER JOIN DF_EMPLEADO_VU E ON E.CODEMPLEADO = EC.CODEMPLEADO \r\n" +
			"	 INNER JOIN DF_EMPLEADO EM ON EM.ID = E.CODEMPLEADO \r\n" +
			"	 INNER JOIN DF_ESTRUCTURAORGANIZACIONAL emplEstOrganizacional ON emplEstOrganizacional.ID = EM.CODESTRUCTURAORGANIZACIONAL \r\n" +
			"    WHERE   T.ESTADO = 'A' AND \r\n" + 
			"            EC.FECHAHORAINICIO BETWEEN :fechainicio AND :fechafin AND \r\n" +
			"			 (:codproyecto IS NULL OR T.CODPROYECTO = TO_NUMBER(:codproyecto)) AND \r\n" +
			"			 (:codtareatipo IS NULL OR T.CODTAREATIPO = TO_NUMBER(:codtareatipo)) AND \r\n" +
			"			 (:codproveedor IS NULL OR E.CODPROVEEDOR = TO_NUMBER(:codproveedor)) AND \r\n" +
			"			 (:codempleadoresponsable IS NULL OR T.CODEMPLEADOASIGNADO = TO_NUMBER(:codempleadoresponsable)) AND \r\n" +
			"			 (:codempleadocreo IS NULL OR T.CODEMPLEADOCREO = TO_NUMBER(:codempleadocreo)) AND \r\n" +
			"			 (:codtareaestado IS NULL OR T.CODTAREAESTADO = TO_NUMBER(:codtareaestado)) AND \r\n" +
			"			 (:eslogro IS NULL OR T.ESLOGRO = TO_NUMBER(:eslogro)) AND \r\n" +
			"			 (:tarea IS NULL OR UPPER(T.NOMBRE) LIKE '%'||UPPER(:tarea)||'%') AND \r\n" +
			"			 emplEstOrganizacional.JERARQUIA LIKE :jerarquia || '%' \r\n" +
			"    GROUP BY    PY.NOMBRE, \r\n" + 
			"                T.ID,\r\n" + 
			"                E.PROVEEDOR,\r\n" + 
			"                E.CODEMPLEADO,\r\n" + 
			"                E.NOMBRE,\r\n" + 
			"                E.CODPERSONA,\r\n" + 
			"                E.FECHAINICIO\r\n" + 
			")\r\n" + 
			"SELECT  ROWNUM id, \r\n" + 
			"		 proyecto, \r\n" + 
			"        tarea,\r\n" + 
			"        proveedor,\r\n" + 
			"        recurso,\r\n" + 
			"		 NULL descripcion, \r\n" +
			"        horas,\r\n" + 
			"        fechainicio,\r\n" + 
			"        fechafin,\r\n" + 
			"        TOTAL_HORAS_REPORTADAS horasreportadas,\r\n" + 
			"        TOTAL_HORAS_AUSENCIAS horasausencias,\r\n" + 
			"        HORAS_LABORALES horaslaborales,\r\n" + 
			"        HORAS_LABORALES - NVL(TOTAL_HORAS_AUSENCIAS, 0) - TOTAL_HORAS_REPORTADAS horaspendientes \r\n" + 
			"FROM (\r\n" + 
			"    SELECT  D.PROYECTO, \r\n" + 
			"            D.TAREA,\r\n" + 
			"            D.PROVEEDOR,\r\n" + 
			"            D.RECURSO, \r\n" + 
			"            D.HORAS,\r\n" + 
			"            D.FECHAINICIO,\r\n" + 
			"            D.FECHAFIN,\r\n" + 
			"            (SELECT SUM(D2.HORAS) \r\n" + 
			"             FROM DATOS D2 \r\n" + 
			"             WHERE D2.CODEMPLEADO = D.CODEMPLEADO) TOTAL_HORAS_REPORTADAS,\r\n" + 
			"            D.TOTAL_HORAS_AUSENCIAS,\r\n" + 
			"            (SELECT \r\n" + 
			"              ((TRUNC(:fechafin) - TRUNC(CASE WHEN D.FECHAINGRESO < :fechainicio THEN :fechainicio ELSE D.FECHAINGRESO END) ) +1 - \r\n" + 
			"              ((((TRUNC(:fechafin,'D'))-(TRUNC(CASE WHEN D.FECHAINGRESO < :fechainicio THEN :fechainicio ELSE D.FECHAINGRESO END,'D')))/7)*2) -\r\n" + 
			"              (CASE WHEN TO_CHAR(CASE WHEN D.FECHAINGRESO < :fechainicio THEN :fechainicio ELSE D.FECHAINGRESO END,'DY','nls_date_language=english')='SUN' THEN 1 ELSE 0 END) -\r\n" + 
			"              (CASE WHEN TO_CHAR(:fechafin,'DY','nls_date_language=english')='SAT' THEN 1 ELSE 0 END) -\r\n" + 
			"              (SELECT COUNT(1) FROM DF_CALENDARIOFECHAS C WHERE C.FECHA BETWEEN (CASE WHEN D.FECHAINGRESO < :fechainicio THEN :fechainicio ELSE D.FECHAINGRESO END) AND :fechafin)) * 9\r\n" + 
			"            FROM DUAL) HORAS_LABORALES\r\n" + 
			"    FROM DATOS D\r\n" + 
			"    ORDER BY D.PROYECTO, \r\n" + 
			"             D.TAREA, \r\n" + 
			"             D.RECURSO\r\n" + 
			")\r\n")
	public Iterable<ReporteTiempo> findResumen(
												@Param("fechainicio") Date fechainicio, 
												@Param("fechafin") Date fechafin,
												@Param("codproyecto") Integer codproyecto,
												@Param("codtareatipo") Integer codtareatipo,
												@Param("codproveedor") Integer codproveedor,
												@Param("codempleadoresponsable") Integer codempleadoresponsable,
												@Param("codempleadocreo") Integer codempleadocreo,
												@Param("codtareaestado") Integer codtareaestado,
												@Param("eslogro") Boolean eslogro,
												@Param("tarea") String tarea,
												@Param("jerarquia") String jerarquia
												);
	
	@Query(nativeQuery = true, value = "" +
			"SELECT  ROWNUM ID, \r\n" +
			"		 PY.NOMBRE PROYECTO, \r\n" + 
			"        FN_DF_TAREAMIGA(T.ID) TAREA, \r\n" + 
			"        E.PROVEEDOR, \r\n" + 
			"        TRIM(REPLACE(E.NOMBRE,'  ',' ')) RECURSO , \r\n" + 
			"        EC.DESCRIPCION, \r\n" + 
			"        EC.HORAS HORAS, \r\n" + 
			"        EC.FECHAHORAINICIO FECHAINICIO, \r\n" + 
			"        EC.FECHAHORAFIN FECHAFIN, \r\n" +
			"		 0 horasreportadas, \r\n" + 
			"		 0 horasausencias, \r\n" + 
			"		 0 horaslaborales, \r\n" + 
			"		 0 horaspendientes \r\n" + 
			"FROM DF_PROYECTO PY \r\n" + 
			"INNER JOIN DF_TAREA T ON T.CODPROYECTO = PY.ID \r\n" + 
			"INNER JOIN DF_EMPLEADOCONTROL EC ON EC.CODTAREA = T.ID \r\n" + 
			"INNER JOIN DF_EMPLEADO_VU E ON E.CODEMPLEADO = EC.CODEMPLEADO \r\n" +
			"INNER JOIN DF_EMPLEADO EM ON EM.ID = E.CODEMPLEADO \r\n" +
			"INNER JOIN DF_ESTRUCTURAORGANIZACIONAL emplEstOrganizacional ON emplEstOrganizacional.ID = EM.CODESTRUCTURAORGANIZACIONAL \r\n" +
			"WHERE   T.ESTADO = 'A' AND \r\n" + 
			"        EC.FECHAHORAINICIO BETWEEN :fechainicio AND :fechafin AND \r\n" +
			"		 (:codproyecto IS NULL OR T.CODPROYECTO = TO_NUMBER(:codproyecto)) AND \r\n" +
			"		 (:codtareatipo IS NULL OR T.CODTAREATIPO = TO_NUMBER(:codtareatipo)) AND \r\n" +
			"		 (:codproveedor IS NULL OR E.CODPROVEEDOR = TO_NUMBER(:codproveedor)) AND \r\n" +
			"		 (:codempleadoresponsable IS NULL OR T.CODEMPLEADOASIGNADO = TO_NUMBER(:codempleadoresponsable)) AND \r\n" +
			"		 (:codempleadocreo IS NULL OR T.CODEMPLEADOCREO = TO_NUMBER(:codempleadocreo)) AND \r\n" +
			"		 (:codtareaestado IS NULL OR T.CODTAREAESTADO = TO_NUMBER(:codtareaestado)) AND \r\n" +
			"		 (:eslogro IS NULL OR T.ESLOGRO = TO_NUMBER(:eslogro)) AND \r\n" +
			"		 (:tarea IS NULL OR UPPER(T.NOMBRE) LIKE '%'||UPPER(:tarea)||'%') AND \r\n" +
			"		 emplEstOrganizacional.JERARQUIA LIKE :jerarquia || '%' \r\n" +
			"ORDER BY PY.NOMBRE, E.NOMBRE, EC.FECHAHORAINICIO")
	public Iterable<ReporteTiempo> findDetalle(
												@Param("fechainicio") Date fechainicio, 
												@Param("fechafin") Date fechafin,
												@Param("codproyecto") Integer codproyecto,
												@Param("codtareatipo") Integer codtareatipo,
												@Param("codproveedor") Integer codproveedor,
												@Param("codempleadoresponsable") Integer codempleadoresponsable,
												@Param("codempleadocreo") Integer codempleadocreo,
												@Param("codtareaestado") Integer codtareaestado,
												@Param("eslogro") Boolean eslogro,
												@Param("tarea") String tarea,
												@Param("jerarquia") String jerarquia
												);
	
}
