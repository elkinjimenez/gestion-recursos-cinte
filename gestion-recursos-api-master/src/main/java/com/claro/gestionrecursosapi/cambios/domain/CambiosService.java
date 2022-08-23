package com.claro.gestionrecursosapi.cambios.domain;

import java.util.Optional;
import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.cambios.entity.CambiosEntity;
import com.claro.gestionrecursosapi.cambios.repository.ICambiosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CambiosService implements ICrudService<CambiosEntity, Integer> {

    @Autowired
    private ICambiosRepository repository;

    @Override
    public CambiosEntity save(CambiosEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Iterable<CambiosEntity> saveAll(Iterable<CambiosEntity> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public Optional<CambiosEntity> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<CambiosEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(CambiosEntity entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<CambiosEntity> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {

    }

}
