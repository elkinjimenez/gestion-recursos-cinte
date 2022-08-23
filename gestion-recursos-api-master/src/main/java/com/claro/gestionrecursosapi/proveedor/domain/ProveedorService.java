package com.claro.gestionrecursosapi.proveedor.domain;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.proveedor.entity.ProveedorEntity;
import com.claro.gestionrecursosapi.proveedor.repository.IProveedorRepository;

@Service
@Validated
public class ProveedorService implements IProveedorService {

	@Autowired
	private IProveedorRepository repository;

	@Override
	public ProveedorEntity crear(ProveedorEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion {
		if (entity.getEstado() == null || entity.getEstado().isEmpty())
			entity.setEstado("A");
		return repository.save(entity);
	}

	@Override
	public ProveedorEntity actualizar(int id, ProveedorEntity entity)
			throws NoExisteExcepcion, DataIncorrectaExcepcion {
		
		ProveedorEntity proveedorEntityBuscado = buscarPorId(id);
		proveedorEntityBuscado.setNombre(entity.getNombre());
		proveedorEntityBuscado.setContratomarco(entity.getContratomarco());
		proveedorEntityBuscado.setNit(entity.getNit());
		proveedorEntityBuscado.setLinea(entity.getLinea());
		proveedorEntityBuscado.setEspecialidad(entity.getEspecialidad());
		proveedorEntityBuscado.setEstado(entity.getEstado());
		proveedorEntityBuscado.setLogo(entity.getLogo());
		proveedorEntityBuscado.setCiudad(entity.getCiudad());
		proveedorEntityBuscado.setDepartamento(entity.getDepartamento());
		proveedorEntityBuscado.setPais(entity.getPais());
		
		if (proveedorEntityBuscado.getEstado() == null || proveedorEntityBuscado.getEstado().isEmpty())
			proveedorEntityBuscado.setEstado("A");
		
		return repository.save(proveedorEntityBuscado);
	}

	@Override
	public ProveedorEntity buscarPorId(int id) throws NoExisteExcepcion {
		Optional<ProveedorEntity> proveedorEntity = repository.findById(id);

		if (proveedorEntity.isPresent()) {
			return proveedorEntity.get();
		} else {
			throw new NoExisteExcepcion("No se encontro ningún proveedor con el Id: " + id);
		}
	}

	@Override
	public ProveedorEntity buscarPorNit(String nit) throws NoExisteExcepcion {
		ProveedorEntity proveedorEntity = repository.findByNit(nit);
		if (proveedorEntity != null) {
			return proveedorEntity;
		} else {
			throw new NoExisteExcepcion("No se encontro ningún proveedor con el Nit: " + nit);
		}
	}

	@Override
	public ProveedorEntity buscarPorContratoMarcho(String contratomarco) throws NoExisteExcepcion {
		ProveedorEntity proveedorEntity = repository.findByContratomarco(contratomarco);
		if (proveedorEntity != null) {
			return proveedorEntity;
		} else {
			throw new NoExisteExcepcion("No se encontro ningún proveedor con el ContratoMarco: " + contratomarco);
		}
	}

	@Override
	public Iterable<ProveedorEntity> buscarTodos() throws NoExisteExcepcion {
		Iterable<ProveedorEntity> listaProveedor = repository.findAll();
		if (((Collection<?>) listaProveedor).size() > 0) {
			return listaProveedor;
		} else {
			throw new NoExisteExcepcion("No se encontraron resultados");
		} 
	}

	@Override
	public Iterable<ProveedorEntity> buscarPorLinea(String linea) throws NoExisteExcepcion {
		Iterable<ProveedorEntity> listaProveedor = repository.findByLinea(linea);
		if (((Collection<?>) listaProveedor).size() > 0) {
			return listaProveedor;
		} else {
			throw new NoExisteExcepcion("No se encontraron resultados");
		}
	}

	@Override
	public Iterable<ProveedorEntity> buscarPorEspecialidad(String especialidad) throws NoExisteExcepcion {
		Iterable<ProveedorEntity> listaProveedor = repository.findByEspecialidad(especialidad);
		if (((Collection<?>) listaProveedor).size() > 0) {
			return listaProveedor;
		} else {
			throw new NoExisteExcepcion("No se encontraron resultados");
		}
	}

	@Override
	public Iterable<ProveedorEntity> buscarPorEstado(String estado) throws NoExisteExcepcion {
		Iterable<ProveedorEntity> listaProveedor = repository.findByEstado(estado);
		if (((Collection<?>) listaProveedor).size() > 0) {
			return listaProveedor;
		} else {
			throw new NoExisteExcepcion("No se encontraron resultados");
		}
	}

	@Override
	public boolean eliminar(int id) throws NoExisteExcepcion {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
			return true;
		}else {
			throw new NoExisteExcepcion("No se encontro ningún proveedor con el Id: " + id);
		}
	}

}
