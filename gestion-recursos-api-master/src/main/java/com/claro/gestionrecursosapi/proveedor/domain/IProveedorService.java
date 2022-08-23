package com.claro.gestionrecursosapi.proveedor.domain;

import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.proveedor.entity.ProveedorEntity;

public interface IProveedorService {

	ProveedorEntity crear(ProveedorEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion;

	ProveedorEntity actualizar(int id, ProveedorEntity entity) throws NoExisteExcepcion, DataIncorrectaExcepcion;
	
	ProveedorEntity buscarPorId(int id) throws NoExisteExcepcion;
	
	ProveedorEntity buscarPorNit(String nit) throws NoExisteExcepcion;
	
	ProveedorEntity buscarPorContratoMarcho(String contratoMarco) throws NoExisteExcepcion;
	
	Iterable<ProveedorEntity> buscarTodos() throws NoExisteExcepcion;
	
	Iterable<ProveedorEntity> buscarPorLinea(String linea) throws NoExisteExcepcion;
	
	Iterable<ProveedorEntity> buscarPorEspecialidad(String especialidad) throws NoExisteExcepcion;
	
	Iterable<ProveedorEntity> buscarPorEstado(String estado) throws NoExisteExcepcion;
	
	boolean eliminar(int id) throws NoExisteExcepcion;
	

}
