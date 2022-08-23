package com.claro.gestionrecursosapi.perfil.repository;

import com.claro.gestionrecursosapi.perfil.dto.PerfilCostoDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.perfil.entity.PerfilcostoEntity;

import java.util.List;

public interface IPerfilCostoRepository extends CrudRepository<PerfilcostoEntity, Integer> {
	
	Iterable<PerfilcostoEntity> findByEstado(String estado);
	
	Iterable<PerfilcostoEntity> findByCodproveedor(Integer codProveedor);
	
	Iterable<PerfilcostoEntity> findByCodperfil(Integer perfil);
	
	Iterable<PerfilcostoEntity> findByCodperfiltipo(Integer perfilTipo);
	
	Iterable<PerfilcostoEntity> findByCodperfilnivel(Integer perfilNivel);

}
