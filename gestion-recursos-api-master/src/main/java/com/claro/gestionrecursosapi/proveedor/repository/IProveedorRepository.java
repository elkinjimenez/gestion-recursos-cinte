package com.claro.gestionrecursosapi.proveedor.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.claro.gestionrecursosapi.proveedor.entity.ProveedorEntity;

@Repository
public interface IProveedorRepository extends CrudRepository<ProveedorEntity, Integer> {

	ProveedorEntity findByNit(String Nit);

	ProveedorEntity findByContratomarco(String contratomarco);

	Iterable<ProveedorEntity> findByLinea(String linea);

	Iterable<ProveedorEntity> findByEspecialidad(String especialidad);

	Iterable<ProveedorEntity> findByEstado(String estado);

}
