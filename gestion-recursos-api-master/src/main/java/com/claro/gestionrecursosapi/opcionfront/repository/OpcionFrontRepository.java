package com.claro.gestionrecursosapi.opcionfront.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.opcionfront.entity.OpcionFrontEntity;
import org.springframework.data.repository.query.Param;

public interface OpcionFrontRepository extends CrudRepository<OpcionFrontEntity, Integer> {


	@Query("SELECT o from OpcionFrontEntity o where o.nombre = :userName")
	public Optional<Iterable<OpcionFrontEntity>> findGrupoByUsuario(@Param("userName") String userName);

	@Query(value = "select opcionFront from OpcionFrontEntity opcionFront where opcionFront.grupo = :grupo")
	public Optional<Iterable<OpcionFrontEntity>> findAllByGrupo(String grupo);

	OpcionFrontEntity findByNombre(String usuarioClaro);

	@Query(value = "select COUNT(o.id) from OpcionFrontEntity o where o.grupo like '%UsuariosVinculacion%' AND o.valor = :usuario")
	Integer ValidarsiUsurioExiste(@Param("usuario") String usuario);

}
