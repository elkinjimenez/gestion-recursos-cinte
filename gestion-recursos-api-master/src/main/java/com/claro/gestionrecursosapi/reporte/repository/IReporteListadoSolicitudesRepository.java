package com.claro.gestionrecursosapi.reporte.repository;

import com.claro.gestionrecursosapi.reporte.entity.ReporteListadoSolicitudes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface IReporteListadoSolicitudesRepository extends CrudRepository<ReporteListadoSolicitudes, Long> {

    @Query(nativeQuery = true,
            value = "WITH INFO AS ( \n" +
                    "    SELECT  EXTRACT(YEAR FROM EC.FECHAHORAINICIO) AÑO, \n" +
                    "            EXTRACT(MONTH FROM EC.FECHAHORAINICIO) MES, \n" +
                    "            PY.ID CODPROYECTO, \n" +
                    "            PY.NOMBRE PROYECTO, \n" +
                    "            T.DESCRIPCION TAREA, \n" +
                    "            E.PROVEEDOR, \n" +
                    "            PF.NOMBRE PERFIL, \n" +
                    "            E.NUMERODOCUMENTO IDRECURSO, \n" +
                    "            TRIM(REPLACE(E.NOMBRE,'  ',' ')) RECURSO, \n" +
                    "            SUM(EC.HORAS) HORAS, \n" +
                    "            SUM(EC.HORAS * PF.COSTOPROMEDIOHORA) COSTOTOTAL, \n" +
                    "            E.FECHAINICIO FECHAINGRESO, \n" +
                    "            EVU.NOMBRE DOLIENTE, \n" +
                    "            EO.CODEMPLEADO_RESPONSABLE GERENTE \n" +
                    "    FROM DF_PROYECTO PY \n" +
                    "    INNER JOIN DF_TAREA T ON T.CODPROYECTO = PY.ID AND \n" +
                    "                             T.ESTADO = 'A' \n" +
                    "    INNER JOIN DF_EMPLEADOCONTROL EC ON EC.CODTAREA = T.ID \n" +
                    "    INNER JOIN DF_EMPLEADO_VU E ON E.CODEMPLEADO = EC.CODEMPLEADO \n" +
                    "    INNER JOIN DF_EMPLEADO_VU EVU ON T.CODEMPLEADOCREO = EVU.CODEMPLEADO \n" +
                    "    INNER JOIN DF_PERFIL PF ON PF.ID = E.CODPERFIL \n" +
                    "    LEFT JOIN DF_ESTRUCTURAORGANIZACIONAL EO ON EO.CODEMPLEADO_RESPONSABLE = E.CODEMPLEADO AND EO.CODESTRUCTURATIPO = 1 \n" +
                    "	 INNER JOIN DF_EMPLEADO EM ON EM.ID = E.CODEMPLEADO \r\n" +
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
                    "    GROUP BY    EXTRACT(YEAR FROM EC.FECHAHORAINICIO), \n" +
                    "                EXTRACT(MONTH FROM EC.FECHAHORAINICIO), \n" +
                    "                PY.ID, \n" +
                    "                PY.NOMBRE, \n" +
                    "                E.PROVEEDOR, \n" +
                    "                T.DESCRIPCION, \n" +
                    "                PF.NOMBRE, \n" +
                    "                E.NUMERODOCUMENTO, \n" +
                    "                E.NOMBRE, \n" +
                    "                E.FECHAINICIO, \n" +
                    "                EVU.NOMBRE, \n" +
                    "                EO.CODEMPLEADO_RESPONSABLE \n" +
                    ") \n" +
                    "SELECT  ROWNUM id, \n" +
                    "        I.PROYECTO PROYECTO, \n" +
                    "        (select nombre from df_empleado_vu where codempleado = i.gerente) GERENTE, \n" +
                    "        I.TAREA DESCRIPCION_TAREA, I.PROVEEDOR, I.PERFIL, I.IDRECURSO, UPPER(I.RECURSO) RECURSO, I.HORAS, \n" +
                    "        (I.HORAS * 100) / (SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.CODPROYECTO <> 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.IDRECURSO = I.IDRECURSO) PORCENTAJE_EN_PROYECTO, \n" +
                    "        I.HORAS + ((SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.CODPROYECTO = 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.IDRECURSO = I.IDRECURSO) * ((I.HORAS * 100) / (SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.CODPROYECTO <> 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.IDRECURSO = I.IDRECURSO)/100)) HORAS_CON_ESTRUCTURA, \n" +
                    "        I.DOLIENTE \n" +
                    "FROM INFO I \n" +
                    "WHERE I.CODPROYECTO <> 22")
    public Iterable<ReporteListadoSolicitudes> infoReporte(
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
