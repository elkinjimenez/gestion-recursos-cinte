package com.claro.gestionrecursosapi.empleado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoVU;


@Repository
public interface IEmpleadoVURepository extends CrudRepository<EmpleadoVU, Integer> {

    @Query("SELECT e " + "FROM EmpleadoVU e " + "INNER JOIN EmpleadoEntity em ON em.id = e.codempleado "
            + "INNER JOIN UsuarioEntity usuarioConsulta ON usuarioConsulta.usuario = :CLUSUARIO "
            + "INNER JOIN EstructuraorganizacionalEntity emplEstOrganizacional ON emplEstOrganizacional.id = em.codestructuraorganizacional "
            + "WHERE e.fechafin is null AND " + "emplEstOrganizacional.jerarquia LIKE :jerarquia || '%'")
    public Iterable<EmpleadoVU> findAll(@Param("CLUSUARIO") String CLUSUARIO, @Param("jerarquia") String jerarquia);

    @Query(" SELECT DISTINCT e " +
            " FROM EmpleadoVU e " 
            + "LEFT JOIN EstructuraorganizacionalEntity emplEstOrganizacional ON emplEstOrganizacional.id = e.codestructuraorganizacional "
            + "WHERE e.fechafin is null"
    )
    public Iterable<EmpleadoVU> findAllWithOutUser();

    @Query(value = "SELECT E FROM EmpleadoVU E WHERE E.codempleado = :numEmpleado")
    public Optional<EmpleadoVU> findByCodEmpleado(@Param("numEmpleado") Integer numEmpleado);

    /*
     * Cod perfil 75 es para gerente
     */
    @Query("SELECT e FROM EmpleadoVU e WHERE e.codperfil = 75")
    public Iterable<EmpleadoVU> findAllGerentes();

    @Query("SELECT e "
            + "FROM EmpleadoVU e "
            + "INNER JOIN EmpleadoEntity em ON em.id = e.codempleado "
            + "INNER JOIN EstructuraorganizacionalEntity emplEstOrganizacional ON emplEstOrganizacional.id = em.codestructuraorganizacional"
            + " WHERE emplEstOrganizacional.codestructuratipo = :codEstructura")
    public Iterable<EmpleadoVU> findAllBycodEstructura(@Param("codEstructura") Integer codEstructura);

}
