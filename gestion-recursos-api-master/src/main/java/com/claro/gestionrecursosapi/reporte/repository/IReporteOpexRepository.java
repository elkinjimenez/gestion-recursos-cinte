package com.claro.gestionrecursosapi.reporte.repository;

import com.claro.gestionrecursosapi.reporte.entity.ReporteOpex;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface IReporteOpexRepository extends CrudRepository<ReporteOpex, Long> {

    @Query(nativeQuery = true,
            value = "WITH INFO AS ( \n" +
                    "    SELECT  EXTRACT(YEAR FROM EC.FECHAHORAINICIO) AÑO, \n" +
                    "            EXTRACT(MONTH FROM EC.FECHAHORAINICIO) MES, \n" +
                    "            EXTRACT(DAY FROM EC.FECHAHORAINICIO) DIA, \n" +
                    "            PY.ID CODPROYECTO, \n" +
                    "            PY.NOMBRE PROYECTO, \n" +
                    "            E.PROVEEDOR, \n" +
                    "            PF.NOMBRE PERFIL, \n" +
                    "            E.NUMERODOCUMENTO IDRECURSO, \n" +
                    "            TRIM(REPLACE(E.NOMBRE,'  ',' '))  RECURSO, \n" +
                    "            SUM(EC.HORAS) HORAS, \n" +
                    "            E.CODMODALIDAD MODALIDAD, \n" +
                    "            SUM(EC.HORAS * PF.COSTOPROMEDIOHORA) COSTOTOTAL \n" +
                    "    FROM DF_PROYECTO PY \n" +
                    "    INNER JOIN DF_TAREA T ON T.CODPROYECTO = PY.ID AND \n" +
                    "                             T.ESTADO = 'A' \n" +
                    "    INNER JOIN DF_EMPLEADOCONTROL EC ON EC.CODTAREA = T.ID \n" +
                    "    INNER JOIN DF_EMPLEADO_VU E ON E.CODEMPLEADO = EC.CODEMPLEADO \n" +
                    "    INNER JOIN DF_PERFIL PF ON PF.ID = E.CODPERFIL \n" +
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
                    "                EXTRACT(DAY FROM EC.FECHAHORAINICIO), \n" +
                    "                PY.ID, \n" +
                    "                PY.NOMBRE, \n" +
                    "                E.PROVEEDOR, \n" +
                    "                PF.NOMBRE, \n" +
                    "                E.NUMERODOCUMENTO, \n" +
                    "                E.NOMBRE, \n" +
                    "                E.FECHAINICIO, \n" +
                    "                E.CODMODALIDAD \n" +
                    ") \n" +
                    "SELECT  ROWNUM ID, I.PROYECTO PROYECTO, I.PROVEEDOR, I.PERFIL, I.MODALIDAD, UPPER(REPLACE(I.RECURSO,'  ',' ')) RECURSO, \n" +
                    "        (I.HORAS * 100) / (SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.CODPROYECTO <> 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.DIA = I.DIA AND I2.IDRECURSO = I.IDRECURSO) PRORRATEO_POR_RECURSO, \n" +
                    "        I.HORAS HORAS_DIRECTAS, \n" +
                    "        /*(SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.CODPROYECTO = 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.DIA = I.DIA AND I2.IDRECURSO = I.IDRECURSO) HORAS_EN_ESTRUCTURA,*/ \n" +
                    "        ((SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.CODPROYECTO = 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.DIA = I.DIA AND I2.IDRECURSO = I.IDRECURSO) * ((I.HORAS * 100) / (SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.CODPROYECTO <> 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.DIA = I.DIA AND I2.IDRECURSO = I.IDRECURSO)/100)) HORAS_EN_ESTRUCTURA, \n" +
                    "        (SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.MODALIDAD = 1 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.DIA = I.DIA) * (HORAS / (SELECT SUM(HORAS) FROM INFO I2 WHERE I2.CODPROYECTO <> 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.DIA = I.DIA)) HORAS_OPEX, \n" +
                    "        I.HORAS + ((SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.CODPROYECTO = 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.DIA = I.DIA AND I2.IDRECURSO = I.IDRECURSO) * ((I.HORAS * 100) / (SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.CODPROYECTO <> 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.DIA = I.DIA AND I2.IDRECURSO = I.IDRECURSO)/100)) + (SELECT NVL(SUM(HORAS), 0) FROM INFO I2 WHERE I2.MODALIDAD = 1 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.DIA = I.DIA) * (HORAS / (SELECT SUM(HORAS) FROM INFO I2 WHERE I2.CODPROYECTO <> 22 AND I2.AÑO = I.AÑO AND I2.MES = I.MES AND I2.DIA = I.DIA)) COBRO_PROYECTO \n" +
                    "FROM INFO I \n" +
                    "WHERE I.CODPROYECTO <> 22")
    public Iterable<ReporteOpex> infoReporte(
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
