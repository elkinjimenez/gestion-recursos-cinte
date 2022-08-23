package com.claro.gestionrecursosapi.reporte.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.claro.gestionrecursosapi.reporte.entity.ReporteOpex;
import com.claro.gestionrecursosapi.reporte.entity.ReporteRhPagos;

public interface IReporteRhPagosRepository extends
		CrudRepository<ReporteRhPagos, Long> {

	@Query(nativeQuery = true, value ="SELECT  \n"
			+ " ROWNUM id,d.RECURSO,d.CODEMPLEADO,d.CODPROYECTO,d.IDRECURSO,d.PROVEEDOR,  \n"
			+ " d.CODPROVEEDOR,d.TIPOPRESUPUESTO,d.IDPROYECTO,d.PEP,d.NOMBRE_AMX,d.CONPRESUPUESTO,d.TIPOPROYECTO,  \n"
			+ " d.PROYECTO,d.FECHA,d.SEMANA,d.PORCENTAJEPROYECTO,d.COSTORECURSOMES,d.COSTOXPAGAR,d.GERENTE, \n"
			+ " d.APROBADOR,d.CELULA \n"
			+ " FROM DF_INF_RRHHPAGOS_V d  \n"
			+ " INNER JOIN DF_EMPLEADO e ON e.id =  d.codempleado \n"
			+ " INNER JOIN DF_USUARIO usuarioConsulta ON usuarioConsulta.usuario = :CLUSUARIO \n"
			+ " INNER JOIN DF_EMPLEADO empleadoConsulta ON empleadoConsulta.codpersona = usuarioConsulta.codpersona AND (usuarioConsulta.codusuariorol=3 or empleadoConsulta.codestructuraorganizacional = e.codestructuraorganizacional)\n"
			+ "	WHERE 1 = 1  \n"
			+ "  AND  e.fechainicio BETWEEN :fechainicio AND :fechafin \n"
			+ "  AND (:codproyecto IS NULL OR d.codproyecto = TO_NUMBER(:codproyecto))   \n"
			+ " AND (:codproveedor IS NULL OR d.codproveedor = TO_NUMBER(:codproveedor)) ")
	public Iterable<ReporteRhPagos> infoReporte(
			@Param("CLUSUARIO") String CLUSUARIO,
			@Param("fechainicio") Date fechainicio,
			@Param("fechafin") Date fechafin,
			@Param("codproyecto") Integer codproyecto,
			@Param("codproveedor") Integer codproveedor);
	
}
