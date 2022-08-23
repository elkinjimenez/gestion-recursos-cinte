package com.claro.gestionrecursosapi.reporte.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.claro.gestionrecursosapi.reporte.entity.ReporteEmpleados;


public interface IReporteEmpleadosRepository extends CrudRepository<ReporteEmpleados, Long> {

@Query(nativeQuery = true, value =" SELECT  \n"
		+ " de.ID,de.CODEMPLEADO,de.CODPERSONA,de.NOMBRE,de.CODTIPODOCUMENTO, \n"
		+ " de.TIPODOCUMENTO,de.NUMERODOCUMENTO,de.NOMBRE1,de.NOMBRE2,de.APELLIDO1,de.APELLIDO2, \n"
		+ " de.TELEFONO,de.CORREO,de.DIRECCIONRESIDENCIA,de.GENERO,de.GENERONOMBRE,de.FECHANACIMIENTO, \n"
		+ " de.CODPERFIL,de.PERFIL,de.CODPERFILTIPO,de.PERFILTIPO,de.CODLINEAPRODUCTO,de.LINEAPRODUCTO, \n"
		+ " de.CODPROVEEDOR,de.PROVEEDOR,de.CODPERFILNIVEL,de.PERFILNIVEL,de.USUARIOCLARO,de.FECHAINICIO, \n"
		+ " de.FECHAFIN,de.CODESTRUCTURAORGANIZACIONAL,de.ESTRUCTURAORGANIZACIONAL,de.ROL,de.CODMODALIDAD, \n"
		+ " de.CELULA,de.VALOR \n"
		+ " FROM DF_EMPLEADO_VU de \n"
		+ " INNER JOIN DF_EMPLEADO e ON e.id =  de.id  \n"
		+ " INNER JOIN DF_USUARIO usuarioConsulta ON usuarioConsulta.usuario = :CLUSUARIO \n"
		+ " INNER JOIN DF_EMPLEADO empleadoConsulta ON empleadoConsulta.codpersona = usuarioConsulta.codpersona AND (usuarioConsulta.codusuariorol=3 or empleadoConsulta.codestructuraorganizacional = e.codestructuraorganizacional) \n"
		+ " WHERE 1 = 1  \n"
		+ " AND (:fechafin IS NULL OR (de.fechafin) <= :fechafin) \n"
		+ "	AND (:fechainicio IS NULL OR (de.fechainicio) >= :fechainicio)\n" 
		+ "	AND (:codempleado IS NULL OR de.codempleado =TO_NUMBER(:codempleado))"
		+ " AND (:codproveedor IS NULL OR de.codproveedor = TO_NUMBER(:codproveedor))")

public Iterable<ReporteEmpleados> infoReporte(
	@Param("CLUSUARIO") String CLUSUARIO,
	@Param("fechainicio") Date fechainicio,
	@Param("fechafin") Date fechafin,
	@Param("codempleado") Integer codempleado,
	@Param("codproveedor") Integer codproveedor);

	

}

