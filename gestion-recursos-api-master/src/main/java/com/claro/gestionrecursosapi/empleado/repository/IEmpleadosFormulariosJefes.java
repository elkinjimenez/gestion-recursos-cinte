package com.claro.gestionrecursosapi.empleado.repository;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoForm;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoFormJefes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmpleadosFormulariosJefes extends CrudRepository<EmpleadoFormJefes, Integer>{


    @Query(value = "     SELECT   a.id, a.nombre, a.codpadre,  a.codempleado_responsable, a.codestructuratipo, a.jerarquia \n" +
            "            FROM df_estructuraorganizacional a  \n" +
            "            LEFT JOIN DF_EMPLEADO_VU b \n" +
            "            ON a.id = b.codempleado   \n" +
            "            WHERE a.codestructuratipo = :cod", nativeQuery = true)
    List<EmpleadoFormJefes> getEmpleadoByEstructuraTipo(@Param("cod") int cod);
}
