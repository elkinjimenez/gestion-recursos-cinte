package com.claro.gestionrecursosapi.base.domain;

import java.util.Optional;

public interface ICrudService<Entity, IdDataType> {	
		
	public Entity save(Entity entity);
	
	public Iterable<Entity> saveAll(Iterable<Entity> entities);
	
	public Optional<Entity> findById(IdDataType id);

	public boolean existsById(IdDataType id);

	public Iterable<Entity> findAll();

	public long count();

	public void deleteById(IdDataType id);

	public void delete(Entity entity);

	public void deleteAll(Iterable<Entity> entities);
	
	public void deleteAll();
	
	
	
}
