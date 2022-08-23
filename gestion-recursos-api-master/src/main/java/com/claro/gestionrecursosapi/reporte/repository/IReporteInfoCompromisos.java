package com.claro.gestionrecursosapi.reporte.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.claro.gestionrecursosapi.reporte.entity.ReporteInfoCompromisos;

public interface IReporteInfoCompromisos extends CrudRepository<ReporteInfoCompromisos, Long>{

	@Query(nativeQuery = true, value = "SELECT ROWNUM AS ID,  C.*"+
			"FROM\r\n" + 
			" DF_INF_COMPROMISOS_FD_V C\r\n" + 
			"    INNER JOIN DF_EMPLEADO e ON e.id = c.codempleado\r\n" + 
			"			 INNER JOIN DF_USUARIO usuarioConsulta ON usuarioConsulta.usuario = :CLUSUARIO\r\n" + 
			"			 INNER JOIN DF_EMPLEADO empleadoConsulta ON empleadoConsulta.codpersona = usuarioConsulta.codpersona AND \r\n" + 
			"												(usuarioConsulta.codusuariorol=3 or empleadoConsulta.codestructuraorganizacional = e.codestructuraorganizacional) \r\n" + 
			"			WHERE 1 = 1  \r\n" + 
			"			AND (:fechafin IS NULL OR NVL(c.fechasolicitud, c.fechainicioproceso) <= :fechafin) \r\n" + 
			"			AND (:fechainicio IS NULL OR NVL(c.fechasolicitud, c.fechainicioproceso) >= :fechainicio) \r\n" + 
			"			AND (:codproyecto IS NULL OR c.codproyecto = TO_NUMBER(:codproyecto))\r\n" + 
			"			AND (:codproveedor IS NULL OR c.codproveedor = TO_NUMBER(:codproveedor))\r\n" + 
			"			AND (:tarea IS NULL OR UPPER(c.lineaproducto) LIKE  '%' || UPPER(:tarea) || '%') \r\n" + 
			"			AND (:codtareaestado IS NULL OR C.estado = :codtareaestado) ")
	public Iterable<ReporteInfoCompromisos> infoReporte(@Param("CLUSUARIO") String CLUSUARIO,
            @Param("fechainicio") Date fechainicio,
            @Param("fechafin") Date fechafin,
            @Param("codproyecto") Integer codproyecto,
            @Param("codproveedor") Integer codproveedor,
            @Param("codtareaestado") Integer codtareaestado,
            @Param("tarea") String tarea);
}
