package com.claro.gestionrecursosapi.empleado.repository;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEntity;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoForm;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoFormJefes;
import com.claro.gestionrecursosapi.persona.entity.PersonaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmpleadosFormularios extends CrudRepository<EmpleadoForm, Integer> {

    @Query(value = "SELECT   a.id, b.nombre as nombreEmpleado, a.codpadre,  a.codempleado_responsable, a.codestructuratipo, a.jerarquia, a.nombre \n" +
            "FROM df_estructuraorganizacional a LEFT JOIN df_empleado_vu b \n" +
            "ON  a.codempleado_responsable = b.codempleado " +
            "WHERE a.id = :Id AND b.fechafin is null ", nativeQuery = true)
    List<EmpleadoForm> getEmpleadoFormById(@Param("Id") int Id);

}
