package com.claro.gestionrecursosapi.compromisosfabrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.claro.gestionrecursosapi.compromisosfabrica.entity.CompromisosFabricaEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface ICompromisosFabricaRepository extends CrudRepository<CompromisosFabricaEntity, Integer> {

	@Query(value = "Select compromisoFabrica from CompromisosFabricaEntity compromisoFabrica where compromisoFabrica.Id = (select max(Id) from CompromisosFabricaEntity)")
	Optional<CompromisosFabricaEntity> findLastRecord();

	@Query(value = "SELECT CONCAT(per.nombre1, ' ', per.nombre2, ' ', per.apellido1, ' ', per.apellido2) FROM CompromisosFabricaEntity cf "
			+ "JOIN ProyectoEntity p ON p.id = cf.codProyecto "
			+ "JOIN PresupuestoEntity pr ON pr.id = p.codpresupuesto.id "
			+ "JOIN EmpleadoEntity e ON e.id = pr.codempleadogerente "
			+ "JOIN PersonaEntity per ON per.id = e.codpersona " + "where cf.Id = :id ")
	String gerenteProyectoXCompromisoFabrica(@PathVariable Integer id);

	@Query("SELECT c FROM CompromisosFabricaEntity c JOIN ProyectoEntity p " + "ON p.id = c.codProyecto "
			+ "WHERE c.codProyecto = :idProyecto " + "ORDER BY p.nombre ASC")
	List<CompromisosFabricaEntity> findCompromisosByProyecto(@Param("idProyecto") Integer idProyecto);

}
