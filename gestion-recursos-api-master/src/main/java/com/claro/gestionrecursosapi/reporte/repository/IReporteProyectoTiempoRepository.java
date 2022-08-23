package com.claro.gestionrecursosapi.reporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.reporte.entity.ReporteProyectoTiempo;

@Repository
public interface IReporteProyectoTiempoRepository extends JpaRepository<ReporteProyectoTiempo, Integer>{

	//@PersistenceContext
	//private EntityManager entityManager;
	@Query(nativeQuery = true, value = ""
			+ "SELECT  ROWNUM id, idproyecto, proyecto, horas, proveedor, idrecurso, REPLACE(recurso,'  ',' '), perfil, costohora, HORAS * COSTOHORA costo, descripcion, horaslaborales," + 
			"          horasausencias, TRUNC(fechaingreso) fechaingreso" + 
			"  FROM (" + 
			"    SELECT  PY.ID," + 
			"            CASE WHEN PY.CODIGOPROYECTO IS NULL OR LENGTH(PY.CODIGOPROYECTO) <= 3 THEN PY.NOMBRE ELSE PY.CODIGOPROYECTO END  IDPROYECTO," + 
			"            PY.NOMBRE PROYECTO," + 
			"            SUM(EC.HORAS) HORAS," + 
			"            PR.NOMBRE PROVEEDOR," + 
			"            E.NUMERODOCUMENTO IDRECURSO," + 
			"            TRIM(REPLACE(E.NOMBRE,'  ',' ')) RECURSO, " + 
			"            E.PERFIL," + 
			"            PE.COSTOPROMEDIOHORA COSTOHORA," + 
			"            CASE WHEN PY.CODIGOPROYECTO IS NULL OR LENGTH(PY.CODIGOPROYECTO) <= 3 THEN '' ELSE  '(' || PY.CODIGOPROYECTO || ') ' END || PY.NOMBRE || ': ' || CHR(10) || NVL(ACTIVIDADES.TAREAS, TAREAS.TAREAS) DESCRIPCION," + 
			"            (SELECT SUM(HORAS) " + 
			"                 FROM DF_EMPLEADOCONTROL EC2 " + 
			"                 INNER JOIN DF_TAREA T2 ON T2.ID = EC2.CODTAREA" + 
			"                 WHERE T2.ESTADO = 'A' AND EC2.CODEMPLEADO = E.CODEMPLEADO AND EC2.FECHAHORAINICIO >= TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND EC2.FECHAHORAINICIO <= TO_DATE(:FechaFin, 'DD/MM/YYYY')) TOTALHORASTRABAJADAS," + 
			"                (SELECT " + 
			"                  ((TRUNC(TO_DATE(:FechaFin, 'DD/MM/YYYY')) - TRUNC(CASE WHEN E.FECHAINICIO < TO_DATE(:FechaInicio, 'DD/MM/YYYY') THEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') ELSE E.FECHAINICIO END) ) +1 - " + 
			"                  ((((TRUNC(TO_DATE(:FechaFin, 'DD/MM/YYYY'),'D'))-(TRUNC(CASE WHEN E.FECHAINICIO < TO_DATE(:FechaInicio, 'DD/MM/YYYY') THEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') ELSE E.FECHAINICIO END,'D')))/7)*2) -" + 
			"                  (CASE WHEN TO_CHAR(CASE WHEN E.FECHAINICIO < TO_DATE(:FechaInicio, 'DD/MM/YYYY') THEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') ELSE E.FECHAINICIO END,'DY','nls_date_language=english')='SUN' THEN 1 ELSE 0 END) -" + 
			"                  (CASE WHEN TO_CHAR(TO_DATE(:FechaFin, 'DD/MM/YYYY'),'DY','nls_date_language=english')='SAT' THEN 1 ELSE 0 END) -" + 
			"                  (SELECT COUNT(1) FROM DF_CALENDARIOFECHAS C WHERE C.FECHA BETWEEN (CASE WHEN E.FECHAINICIO < TO_DATE(:FechaInicio, 'DD/MM/YYYY') THEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') ELSE E.FECHAINICIO END) AND TO_DATE(:FechaFin, 'DD/MM/YYYY'))) * 9" + 
			"                FROM DUAL) HORASLABORALES," + 
			"                (SELECT NVL(SUM(PA.HORAS), 0) " + 
			"                 FROM DF_PERSONAAUSENCIA PA " + 
			"                 WHERE  PA.CODPERSONA = E.CODPERSONA AND" + 
			"                        (PA.FECHAINICIO BETWEEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND TO_DATE(:FechaFin, 'DD/MM/YYYY') OR" + 
			"                        PA.FECHAFIN BETWEEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND TO_DATE(:FechaFin, 'DD/MM/YYYY'))) HORASAUSENCIAS," + 
			"                 E.FECHAINICIO FECHAINGRESO" + 
			"    FROM DF_PROYECTO PY" + 
			"    INNER JOIN DF_TAREA T ON T.CODPROYECTO = PY.ID" + 
			"    INNER JOIN DF_EMPLEADOCONTROL EC ON EC.CODTAREA = T.ID" + 
			"    INNER JOIN DF_EMPLEADO_VU E ON E.CODEMPLEADO = EC.CODEMPLEADO" + 
			"    INNER JOIN DF_PROVEEDOR PR ON PR.ID = E.CODPROVEEDOR" + 
			"    INNER JOIN DF_PERFIL PE ON PE.ID = E.CODPERFIL" + 
			"    LEFT JOIN (SELECT INFOTAREAS.CODPROYECTO, INFOTAREAS.CODEMPLEADO, LISTAGG(INFOTAREAS.TAREA, CHR(10)) WITHIN GROUP (ORDER BY INFOTAREAS.TAREA) TAREAS" + 
			"                FROM" + 
			"                    (SELECT T2.CODPROYECTO, EC2.CODEMPLEADO, T2.NOMBRE TAREA" + 
			"                     FROM DF_TAREA T2" + 
			"                     INNER JOIN DF_EMPLEADOCONTROL EC2 ON EC2.CODTAREA = T2.ID" + 
			"                     INNER JOIN DF_EMPLEADO E2 ON E2.ID = EC2.CODEMPLEADO" + 
			"                     WHERE  T2.ESTADO = 'A' AND" + 
			"                            EC2.FECHAHORAINICIO >= TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND" + 
			"                            EC2.FECHAHORAINICIO <= TO_DATE(:FechaFin, 'DD/MM/YYYY') AND " + 
			"                            UPPER(T2.NOMBRE) NOT LIKE '%TAREA POR DEFECTO%'" + 
			"                     GROUP BY T2.CODPROYECTO, EC2.CODEMPLEADO, T2.NOMBRE) INFOTAREAS " + 
			"                 GROUP BY INFOTAREAS.CODPROYECTO, INFOTAREAS.CODEMPLEADO) TAREAS ON TAREAS.CODPROYECTO = PY.ID AND TAREAS.CODEMPLEADO = E.CODEMPLEADO " + 
			"    LEFT JOIN (SELECT INFOACTIVIDADES.CODPROYECTO, INFOACTIVIDADES.CODEMPLEADO, LISTAGG(INFOACTIVIDADES.TAREA, CHR(10)) WITHIN GROUP (ORDER BY INFOACTIVIDADES.TAREA) TAREAS" + 
			"                FROM" + 
			"                    (SELECT T2.CODPROYECTO, EC2.CODEMPLEADO, EC2.DESCRIPCION TAREA" + 
			"                     FROM DF_TAREA T2" + 
			"                     INNER JOIN DF_EMPLEADOCONTROL EC2 ON EC2.CODTAREA = T2.ID" + 
			"                     INNER JOIN DF_EMPLEADO E2 ON E2.ID = EC2.CODEMPLEADO" + 
			"                     WHERE  T2.ESTADO = 'A' AND" + 
			"                            EC2.FECHAHORAINICIO >= TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND" + 
			"                            EC2.FECHAHORAINICIO <= TO_DATE(:FechaFin, 'DD/MM/YYYY') AND" + 
			"                            T2.CODPROYECTO IN (4,17,18, 54, 55, 87,88,89,38,30,83)" + 
			"                     GROUP BY T2.CODPROYECTO, EC2.CODEMPLEADO, EC2.DESCRIPCION) INFOACTIVIDADES" + 
			"                 GROUP BY INFOACTIVIDADES.CODPROYECTO, INFOACTIVIDADES.CODEMPLEADO) ACTIVIDADES ON ACTIVIDADES.CODPROYECTO = PY.ID AND ACTIVIDADES.CODEMPLEADO = E.CODEMPLEADO " + 
			"    WHERE   T.ESTADO = 'A' AND" + 
			"            EC.FECHAHORAINICIO >= TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND" + 
			"            EC.FECHAHORAINICIO <= TO_DATE(:FechaFin, 'DD/MM/YYYY')" + 
			"    GROUP BY    PY.ID," + 
			"                PY.CODIGOPROYECTO," + 
			"                PY.NOMBRE," + 
			"                PR.NOMBRE," + 
			"                E.CODPERSONA," + 
			"                E.CODEMPLEADO," + 
			"                E.NUMERODOCUMENTO," + 
			"                E.NOMBRE," + 
			"                TAREAS.TAREAS," + 
			"                ACTIVIDADES.TAREAS," + 
			"                E.FECHAINICIO," + 
			"                E.PERFIL," + 
			"                E.PERFIL," + 
			"                PE.COSTOPROMEDIOHORA" + 
			"    ORDER BY PY.NOMBRE," + 
			"             E.NOMBRE    " + 
			")")
	public Iterable<ReporteProyectoTiempo> findAll(@Param("FechaInicio") String fechaInicio, @Param("FechaFin") String fechaFin);
	
	/*public Iterable<ReporteProyectoTiempo> findAll(FiltroFechas filtro) {
		List<ReporteProyectoTiempo> tipo = new ArrayList<ReporteProyectoTiempo>();
		Query query = entityManager.createNativeQuery(""
				+ "SELECT  idproyecto, proyecto, horas, proveedor, idrecurso, recurso, perfil, costohora, HORAS * COSTOHORA costo, descripcion, horaslaborales," + 
				"          horasausencias, TRUNC(fechaingreso) fechaingreso" + 
				"  FROM (" + 
				"    SELECT  PY.ID," + 
				"            CASE WHEN PY.CODIGOPROYECTO IS NULL OR LENGTH(PY.CODIGOPROYECTO) <= 3 THEN PY.NOMBRE ELSE PY.CODIGOPROYECTO END  IDPROYECTO," + 
				"            PY.NOMBRE PROYECTO," + 
				"            SUM(EC.HORAS) HORAS," + 
				"            PR.NOMBRE PROVEEDOR," + 
				"            E.NUMERODOCUMENTO IDRECURSO," + 
				"            E.NOMBRE RECURSO,   " + 
				"            E.PERFIL," + 
				"            PE.COSTOPROMEDIOHORA COSTOHORA," + 
				"            CASE WHEN PY.CODIGOPROYECTO IS NULL OR LENGTH(PY.CODIGOPROYECTO) <= 3 THEN '' ELSE  '(' || PY.CODIGOPROYECTO || ') ' END || PY.NOMBRE || ': ' || CHR(10) || NVL(ACTIVIDADES.TAREAS, TAREAS.TAREAS) DESCRIPCION," + 
				"            (SELECT SUM(HORAS) " + 
				"                 FROM DF_EMPLEADOCONTROL EC2 " + 
				"                 INNER JOIN DF_TAREA T2 ON T2.ID = EC2.CODTAREA" + 
				"                 WHERE T2.ESTADO = 'A' AND EC2.CODEMPLEADO = E.CODEMPLEADO AND EC2.FECHAHORAINICIO >= TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND EC2.FECHAHORAINICIO <= TO_DATE(:FechaFin, 'DD/MM/YYYY')) TOTALHORASTRABAJADAS," + 
				"                (SELECT " + 
				"                  ((TRUNC(TO_DATE(:FechaFin, 'DD/MM/YYYY')) - TRUNC(CASE WHEN E.FECHAINICIO < TO_DATE(:FechaInicio, 'DD/MM/YYYY') THEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') ELSE E.FECHAINICIO END) ) +1 - " + 
				"                  ((((TRUNC(TO_DATE(:FechaFin, 'DD/MM/YYYY'),'D'))-(TRUNC(CASE WHEN E.FECHAINICIO < TO_DATE(:FechaInicio, 'DD/MM/YYYY') THEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') ELSE E.FECHAINICIO END,'D')))/7)*2) -" + 
				"                  (CASE WHEN TO_CHAR(CASE WHEN E.FECHAINICIO < TO_DATE(:FechaInicio, 'DD/MM/YYYY') THEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') ELSE E.FECHAINICIO END,'DY','nls_date_language=english')='SUN' THEN 1 ELSE 0 END) -" + 
				"                  (CASE WHEN TO_CHAR(TO_DATE(:FechaFin, 'DD/MM/YYYY'),'DY','nls_date_language=english')='SAT' THEN 1 ELSE 0 END) -" + 
				"                  (SELECT COUNT(1) FROM DF_CALENDARIOFECHAS C WHERE C.FECHA BETWEEN (CASE WHEN E.FECHAINICIO < TO_DATE(:FechaInicio, 'DD/MM/YYYY') THEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') ELSE E.FECHAINICIO END) AND TO_DATE(:FechaFin, 'DD/MM/YYYY'))) * 9" + 
				"                FROM DUAL) HORASLABORALES," + 
				"                (SELECT NVL(SUM(PA.HORAS), 0) " + 
				"                 FROM DF_PERSONAAUSENCIA PA " + 
				"                 WHERE  PA.CODPERSONA = E.CODPERSONA AND" + 
				"                        (PA.FECHAINICIO BETWEEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND TO_DATE(:FechaFin, 'DD/MM/YYYY') OR" + 
				"                        PA.FECHAFIN BETWEEN TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND TO_DATE(:FechaFin, 'DD/MM/YYYY'))) HORASAUSENCIAS," + 
				"                 TO_CHAR(E.FECHAINICIO, 'DD/MM/YYYY') FECHAINGRESO" + 
				"    FROM DF_PROYECTO PY" + 
				"    INNER JOIN DF_TAREA T ON T.CODPROYECTO = PY.ID" + 
				"    INNER JOIN DF_EMPLEADOCONTROL EC ON EC.CODTAREA = T.ID" + 
				"    INNER JOIN DF_EMPLEADO_VU E ON E.CODEMPLEADO = EC.CODEMPLEADO" + 
				"    INNER JOIN DF_PROVEEDOR PR ON PR.ID = E.CODPROVEEDOR" + 
				"    INNER JOIN DF_PERFIL PE ON PE.ID = E.CODPERFIL" + 
				"    LEFT JOIN (SELECT INFOTAREAS.CODPROYECTO, INFOTAREAS.CODEMPLEADO, LISTAGG(INFOTAREAS.TAREA, CHR(10)) WITHIN GROUP (ORDER BY INFOTAREAS.TAREA) TAREAS" + 
				"                FROM" + 
				"                    (SELECT T2.CODPROYECTO, EC2.CODEMPLEADO, T2.NOMBRE TAREA" + 
				"                     FROM DF_TAREA T2" + 
				"                     INNER JOIN DF_EMPLEADOCONTROL EC2 ON EC2.CODTAREA = T2.ID" + 
				"                     INNER JOIN DF_EMPLEADO E2 ON E2.ID = EC2.CODEMPLEADO" + 
				"                     WHERE  T2.ESTADO = 'A' AND" + 
				"                            EC2.FECHAHORAINICIO >= TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND" + 
				"                            EC2.FECHAHORAINICIO <= TO_DATE(:FechaFin, 'DD/MM/YYYY') AND " + 
				"                            UPPER(T2.NOMBRE) NOT LIKE '%TAREA POR DEFECTO%'" + 
				"                     GROUP BY T2.CODPROYECTO, EC2.CODEMPLEADO, T2.NOMBRE) INFOTAREAS " + 
				"                 GROUP BY INFOTAREAS.CODPROYECTO, INFOTAREAS.CODEMPLEADO) TAREAS ON TAREAS.CODPROYECTO = PY.ID AND TAREAS.CODEMPLEADO = E.CODEMPLEADO " + 
				"    LEFT JOIN (SELECT INFOACTIVIDADES.CODPROYECTO, INFOACTIVIDADES.CODEMPLEADO, LISTAGG(INFOACTIVIDADES.TAREA, CHR(10)) WITHIN GROUP (ORDER BY INFOACTIVIDADES.TAREA) TAREAS" + 
				"                FROM" + 
				"                    (SELECT T2.CODPROYECTO, EC2.CODEMPLEADO, EC2.DESCRIPCION TAREA" + 
				"                     FROM DF_TAREA T2" + 
				"                     INNER JOIN DF_EMPLEADOCONTROL EC2 ON EC2.CODTAREA = T2.ID" + 
				"                     INNER JOIN DF_EMPLEADO E2 ON E2.ID = EC2.CODEMPLEADO" + 
				"                     WHERE  T2.ESTADO = 'A' AND" + 
				"                            EC2.FECHAHORAINICIO >= TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND" + 
				"                            EC2.FECHAHORAINICIO <= TO_DATE(:FechaFin, 'DD/MM/YYYY') AND" + 
				"                            T2.CODPROYECTO IN (4,17,18, 54, 55, 87,88,89,38,30,83)" + 
				"                     GROUP BY T2.CODPROYECTO, EC2.CODEMPLEADO, EC2.DESCRIPCION) INFOACTIVIDADES" + 
				"                 GROUP BY INFOACTIVIDADES.CODPROYECTO, INFOACTIVIDADES.CODEMPLEADO) ACTIVIDADES ON ACTIVIDADES.CODPROYECTO = PY.ID AND ACTIVIDADES.CODEMPLEADO = E.CODEMPLEADO " + 
				"    WHERE   T.ESTADO = 'A' AND" + 
				"            EC.FECHAHORAINICIO >= TO_DATE(:FechaInicio, 'DD/MM/YYYY') AND" + 
				"            EC.FECHAHORAINICIO <= TO_DATE(:FechaFin, 'DD/MM/YYYY')" + 
				"    GROUP BY    PY.ID," + 
				"                PY.CODIGOPROYECTO," + 
				"                PY.NOMBRE," + 
				"                PR.NOMBRE," + 
				"                E.CODPERSONA," + 
				"                E.CODEMPLEADO," + 
				"                E.NUMERODOCUMENTO," + 
				"                E.NOMBRE," + 
				"                TAREAS.TAREAS," + 
				"                ACTIVIDADES.TAREAS," + 
				"                E.FECHAINICIO," + 
				"                E.PERFIL," + 
				"                E.PERFIL," + 
				"                PE.COSTOPROMEDIOHORA" + 
				"    ORDER BY PY.NOMBRE," + 
				"             E.NOMBRE    " + 
				")", tipo.getClass());
		
		query.setParameter("FechaInicio", filtro.getFechaInicio());
		query.setParameter("FechaFin", filtro.getFechaFin());
		
		@SuppressWarnings("unchecked")
		List<ReporteProyectoTiempo> datos = query.getResultList();
		
		@SqlResultSetMapping(
			    name="groupDetailsMapping",
			    classes={
			        @ConstructorResult(
			            targetClass=GroupDetails.class,
			            columns={
			                @ColumnResult(name="GROUP_ID"),
			                @ColumnResult(name="USER_ID")
			            }
			        )
			    }
			)

			@NamedNativeQuery(name="getGroupDetails", query="SELECT g.*, gm.* FROM group g LEFT JOIN group_members gm ON g.group_id = gm.group_id and gm.user_id = :userId WHERE g.group_id = :groupId", resultSetMapping="groupDetailsMapping")
		
		return datos;
	}*/
	
}
