package com.claro.gestionrecursosapi.novedad.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.novedad.entity.NovedadEntity;
import org.springframework.data.repository.query.Param;

public interface INovedadRepository extends CrudRepository<NovedadEntity, Integer> {
	@Query(value = "SELECT N.* from DF_NOVEDAD N JOIN DF_EMPLEADO E ON N.IDEMPLEADO = E.ID \n" +
			"INNER JOIN DF_ESTRUCTURAORGANIZACIONAL eo ON eo.ID = E.CODESTRUCTURAORGANIZACIONAL " +
			"WHERE N.ESTADO = 1 \n" +
			"AND E.FECHAFIN IS NULL \n" +
			"AND eo.JERARQUIA LIKE :jerarquia || '%' \n" +
			"ORDER BY N.id DESC", nativeQuery = true)
	public Iterable<NovedadEntity> findAll(@Param("jerarquia") String jerarquia);

	@Query(value = "Select novedad from NovedadEntity novedad where novedad.id = (select max(novedadMax.id) from NovedadEntity novedadMax)")
	Optional<NovedadEntity> findLastRecord();
}
