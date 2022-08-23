package com.claro.gestionrecursosapi.reporte.repository;

import com.claro.gestionrecursosapi.reporte.entity.ReporteProrrateoPresupuesto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface IReporteProrrateoPresupuestoRepository extends CrudRepository<ReporteProrrateoPresupuesto, Long> {

    @Query(nativeQuery = true,
    value = "WITH INFO AS ( \n" +
            "    SELECT  EXTRACT(YEAR FROM EC.FECHAHORAINICIO) AÑO, \n" +
            "            EXTRACT(MONTH FROM EC.FECHAHORAINICIO) MES, \n" +
            "            PY.ID CODPROYECTO, \n" +
            "            PY.NOMBRE PROYECTO, \n" +
            "            PR.PRESUPUESTO_COP PRESUPUESTO, \n" +
            "            E.PROVEEDOR, \n" +
            "            PF.NOMBRE PERFIL, \n" +
            "            E.NUMERODOCUMENTO IDRECURSO, \n" +
            "            TRIM(REPLACE(E.NOMBRE,'  ',' ')) RECURSO, \n" +
            "            SUM(EC.HORAS) HORAS, \n" +
            "            SUM(EC.HORAS * PF.COSTOPROMEDIOHORA) COSTOTOTAL \n" +
            "    FROM DF_PROYECTO PY \n" +
            "    INNER JOIN DF_TAREA T ON T.CODPROYECTO = PY.ID AND \n" +
            "                             T.ESTADO = 'A' \n" +
            "    INNER JOIN DF_EMPLEADOCONTROL EC ON EC.CODTAREA = T.ID \n" +
            "    INNER JOIN DF_EMPLEADO_VU E ON E.CODEMPLEADO = EC.CODEMPLEADO \n" +
            "    INNER JOIN DF_PERFIL PF ON PF.ID = E.CODPERFIL \n" +
            "    INNER JOIN DF_PRESUPUESTO PR ON PY.CODPRESUPUESTO = PR.ID \n" +
            "	 INNER JOIN DF_EMPLEADO EM ON EM.ID = E.CODEMPLEADO \r\n" +
            "	 INNER JOIN DF_USUARIO usuarioConsulta ON usuarioConsulta.USUARIO = :CLUSUARIO \r\n" +
            "	 INNER JOIN DF_EMPLEADO empleadoConsulta ON empleadoConsulta.CODPERSONA = usuarioConsulta.CODPERSONA AND \r\n" +
            "	 										(usuarioConsulta.codusuariorol=3 or	empleadoConsulta.CODESTRUCTURAORGANIZACIONAL = EM.CODESTRUCTURAORGANIZACIONAL) \r\n" +
            "    WHERE   EC.FECHAHORAINICIO BETWEEN :fechainicio AND :fechafin AND \r\n" +
            "			 (:codproyecto IS NULL OR T.CODPROYECTO = TO_NUMBER(:codproyecto)) AND \r\n" +
            "			 (:codtareatipo IS NULL OR T.CODTAREATIPO = TO_NUMBER(:codtareatipo)) AND \r\n" +
            "			 (:codproveedor IS NULL OR E.CODPROVEEDOR = TO_NUMBER(:codproveedor)) AND \r\n" +
            "			 (:codempleadoresponsable IS NULL OR T.CODEMPLEADOASIGNADO = TO_NUMBER(:codempleadoresponsable)) AND \r\n" +
            "			 (:codempleadocreo IS NULL OR T.CODEMPLEADOCREO = TO_NUMBER(:codempleadocreo)) AND \r\n" +
            "			 (:codtareaestado IS NULL OR T.CODTAREAESTADO = TO_NUMBER(:codtareaestado)) AND \r\n" +
            "			 (:eslogro IS NULL OR T.ESLOGRO = TO_NUMBER(:eslogro)) AND \r\n" +
            "			 (:tarea IS NULL OR UPPER(T.NOMBRE) LIKE '%'||UPPER(:tarea)||'%') \r\n" +
            "    GROUP BY    EXTRACT(YEAR FROM EC.FECHAHORAINICIO), \n" +
            "                EXTRACT(MONTH FROM EC.FECHAHORAINICIO), \n" +
            "                PY.ID, \n" +
            "                PY.NOMBRE, \n" +
            "                PR.PRESUPUESTO_COP, \n" +
            "                E.PROVEEDOR, \n" +
            "                PF.NOMBRE, \n" +
            "                E.NUMERODOCUMENTO, \n" +
            "                E.NOMBRE \n" +
            ") \n" +
            "SELECT  ROWNUM ID, TO_DATE(AÑO || '/' || LPAD(MES, 2, '0') || '/01', 'YYYY/MM/DD') FECHA, \n" +
            "        PROYECTO, \n" +
            "        PRESUPUESTO, \n" +
            "        PROVEEDOR, \n" +
            "        PERFIL, \n" +
            "        IDRECURSO, \n" +
            "        RECURSO, \n" +
            "        HORAS_PERFIL HORASSINESTRUCTURA, \n" +
            "        COSTO_PROYECTO COSTOSINESTRUCTURA, \n" +
            "        CASE WHEN PROYECTO = 'Estructura' THEN 0 ELSE HORAS_PERFIL + ((HORAS_ESTRUCTURA * PORCENTAJE_HORAS) / 100) END HORASCONESTRUCTURA, \n" +
            "        CASE WHEN PROYECTO = 'Estructura' THEN 0 ELSE COSTO_PROYECTO + ((COSTO_ESTRUCTURA * PORCENTAJE_COSTO) / 100) END COSTOCONESTRUCTURA, \n" +
            "        (COSTO_PROYECTO + ((COSTO_ESTRUCTURA * PORCENTAJE_COSTO) / 100)) / PRESUPUESTO PRORRATEO, \n" +
            "        /* Presupuesto disponible */ \n" +
            "        (PRESUPUESTO * (COSTO_PROYECTO + ((COSTO_ESTRUCTURA * PORCENTAJE_COSTO) / 100))) AS PRESUPUESTODISPONIBLE, \n" +
            "        /* Presupuesto consumido */ \n" +
            "        CASE WHEN PROYECTO = 'Estructura' THEN 0 ELSE (COSTO_PROYECTO + ((COSTO_ESTRUCTURA * PORCENTAJE_COSTO) / 100) * (COSTO_PROYECTO + ((COSTO_ESTRUCTURA * PORCENTAJE_COSTO) / 100)) / PRESUPUESTO) END PRESUPUESTOCONSUMIDO, \n" +
            "        CASE WHEN PROYECTO = 'Estructura' THEN 0 ELSE PORCENTAJE_HORAS END PORCENTAJEHORAS, \n" +
            "        CASE WHEN PROYECTO = 'Estructura' THEN 0 ELSE PORCENTAJE_COSTO END PORCENTAJECOSTO \n" +
            "FROM ( \n" +
            "        SELECT  I.AÑO, \n" +
            "                I.MES, \n" +
            "                I.PROYECTO, \n" +
            "                I.PRESUPUESTO, \n" +
            "                I.PROVEEDOR, \n" +
            "                I.PERFIL, \n" +
            "                I.IDRECURSO, \n" +
            "                I.RECURSO, \n" +
            "                I.HORAS HORAS_PERFIL, \n" +
            "                ((I.HORAS * 100) / (SELECT SUM(HORAS) FROM INFO I2 WHERE I2.CODPROYECTO <> 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES)) PORCENTAJE_HORAS, \n" +
            "                I.COSTOTOTAL COSTO_PROYECTO, \n" +
            "                ((I.COSTOTOTAL * 100) / (SELECT SUM(COSTOTOTAL) FROM INFO I2 WHERE I2.CODPROYECTO <> 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES)) PORCENTAJE_COSTO, \n" +
            "                (SELECT SUM(I2.HORAS) \n" +
            "                 FROM INFO I2 \n" +
            "                 WHERE  I2.CODPROYECTO = 22 AND \n" +
            "                        I2.AÑO = I.AÑO AND \n" +
            "                        I2.MES = I.MES) HORAS_ESTRUCTURA, \n" +
            "                (SELECT SUM(I2.COSTOTOTAL) \n" +
            "                 FROM INFO I2 \n" +
            "                 WHERE  I2.CODPROYECTO = 22 AND \n" +
            "                        I2.AÑO = I.AÑO AND \n" +
            "                        I2.MES = I.MES) COSTO_ESTRUCTURA \n" +
            "        FROM INFO I \n" +
            "        --WHERE I.CODPROYECTO <> 22 \n" +
            ") DATOS \n" +
            "ORDER BY AÑO, MES, PROYECTO, PROVEEDOR, PERFIL")
    public Iterable<ReporteProrrateoPresupuesto> infoReporte(@Param("CLUSUARIO") String CLUSUARIO,
                                                             @Param("fechainicio") Date fechainicio,
                                                             @Param("fechafin") Date fechafin,
                                                             @Param("codproyecto") Integer codproyecto,
                                                             @Param("codtareatipo") Integer codtareatipo,
                                                             @Param("codproveedor") Integer codproveedor,
                                                             @Param("codempleadoresponsable") Integer codempleadoresponsable,
                                                             @Param("codempleadocreo") Integer codempleadocreo,
                                                             @Param("codtareaestado") Integer codtareaestado,
                                                             @Param("eslogro") Boolean eslogro,
                                                             @Param("tarea") String tarea);

}
