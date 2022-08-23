package com.claro.gestionrecursosapi.proyecto.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.proyecto.entity.ProyectoEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProyectoRepository extends CrudRepository<ProyectoEntity, Integer> {

    @Query(value = "SELECT SUM(EC.HORAS * P.COSTOPROMEDIOHORA) " +
            "FROM DF_PROYECTO PY " +
            "INNER JOIN DF_TAREA T ON T.CODPROYECTO = T.ID " +
            "INNER JOIN DF_EMPLEADOCONTROL EC ON EC.CODTAREA = T.ID " +
            "INNER JOIN DF_EMPLEADO E ON E.ID = EC.CODEMPLEADO " +
            "INNER JOIN DF_PERFIL P ON P.ID = E.CODPERFIL " +
            "WHERE PY.ID = ?1 AND " +
            "T.ESTADO = 'A'", nativeQuery = true)
    public BigDecimal calculoDeHorasQueLlevaUnProyecto(Integer idProyecto);

    @Query("SELECT p FROM ProyectoEntity p WHERE p.codigoproyecto in (?1)")
    public List<ProyectoEntity> buscarTodosLosCodigosProyecto(List<String> lCodigoProyecto);

    public Optional<ProyectoEntity> findByCodigoproyecto(String codigoProyecto);
    
    @Query("SELECT p FROM ProyectoEntity p WHERE p.codigoproyecto = :codigoProyecto")
    public Optional<List<ProyectoEntity>> findAllByCodigoproyecto(String codigoProyecto);

    @Query("SELECT DISTINCT  p FROM ProyectoEntity p JOIN CompromisosFabricaEntity c ON p.id = c.codProyecto ")
    public Iterable<ProyectoEntity> findProyectosWithCompromisos();

    @Query("SELECT p FROM ProyectoEntity p INNER JOIN PresupuestoEntity pre ON p.codpresupuesto = pre.id where pre.vigencia BETWEEN TO_CHAR (SYSDATE, 'YYYY') - 1   AND  TO_CHAR (SYSDATE, 'YYYY')  ")
    public Iterable<ProyectoEntity> findProyectosGantt();
}
