package com.claro.gestionrecursosapi.reporte.repository;

import com.claro.gestionrecursosapi.reporte.entity.ReporteRecursoConProveedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface IReporteRecursoConProveedorRepository extends CrudRepository<ReporteRecursoConProveedor, Long> {

    @Query(nativeQuery = true,
    value = "WITH INFO AS (\n" +
            "    SELECT  PY.ID CODPROYECTO, \n" +
            "            PY.NOMBRE PROYECTO, \n" +
            "            E.PROVEEDOR, \n" +
            "            PF.NOMBRE PERFIL, \n" +
            "            E.NUMERODOCUMENTO IDRECURSO, \n" +
            "            TRIM(REPLACE(E.NOMBRE,'  ',' ')) RECURSO \n" +
            "    FROM DF_PROYECTO PY \n" +
            "    INNER JOIN DF_TAREA T ON T.CODPROYECTO = PY.ID AND \n" +
            "                             T.ESTADO = 'A' \n" +
            "    INNER JOIN DF_EMPLEADOCONTROL EC ON EC.CODTAREA = T.ID \n" +
            "    INNER JOIN DF_EMPLEADO_VU E ON E.CODEMPLEADO = EC.CODEMPLEADO \n" +
            "    INNER JOIN DF_PERFIL PF ON PF.ID = E.CODPERFIL \n" +
            "	 INNER JOIN DF_EMPLEADO EM ON EM.ID = E.CODEMPLEADO \r\n" +
            "	 INNER JOIN DF_USUARIO usuarioConsulta ON usuarioConsulta.USUARIO = :CLUSUARIO \r\n" +
            "	 INNER JOIN DF_ESTRUCTURAORGANIZACIONAL emplEstOrganizacional ON emplEstOrganizacional.ID = EM.CODESTRUCTURAORGANIZACIONAL \r\n" +
            "    WHERE   EC.FECHAHORAINICIO BETWEEN :fechainicio AND :fechafin AND \r\n" +
            "			 (:codproyecto IS NULL OR T.CODPROYECTO = TO_NUMBER(:codproyecto)) AND \r\n" +
            "			 (:codtareatipo IS NULL OR T.CODTAREATIPO = TO_NUMBER(:codtareatipo)) AND \r\n" +
            "			 (:codproveedor IS NULL OR E.CODPROVEEDOR = TO_NUMBER(:codproveedor)) AND \r\n" +
            "			 (:codempleadoresponsable IS NULL OR T.CODEMPLEADOASIGNADO = TO_NUMBER(:codempleadoresponsable)) AND \r\n" +
            "			 (:codempleadocreo IS NULL OR T.CODEMPLEADOCREO = TO_NUMBER(:codempleadocreo)) AND \r\n" +
            "			 (:codtareaestado IS NULL OR T.CODTAREAESTADO = TO_NUMBER(:codtareaestado)) AND \r\n" +
            "			 (:eslogro IS NULL OR T.ESLOGRO = TO_NUMBER(:eslogro)) AND \r\n" +
            "			 (:tarea IS NULL OR UPPER(T.NOMBRE) LIKE '%'||UPPER(:tarea)||'%') AND \r\n" +
            "			 emplEstOrganizacional.JERARQUIA LIKE :jerarquia || '%' \r\n" +
            "    GROUP BY    PY.ID, \n" +
            "                PY.NOMBRE, \n" +
            "                E.PROVEEDOR, \n" +
            "                PF.NOMBRE, \n" +
            "                E.NUMERODOCUMENTO, \n" +
            "                E.NOMBRE \n" +
            ") \n" +
            "SELECT  ROWNUM ID, I.PROYECTO PROYECTO, I.PROVEEDOR, I.PERFIL, I.IDRECURSO, UPPER(REPLACE(I.RECURSO,'  ',' ')) RECURSO \n" +
            "FROM INFO I \n" +
            "WHERE I.CODPROYECTO <> 22")
    public Iterable<ReporteRecursoConProveedor> infoReporte(@Param("CLUSUARIO") String CLUSUARIO,
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
                                                        @Param("jerarquia") String jerarquia);
}
