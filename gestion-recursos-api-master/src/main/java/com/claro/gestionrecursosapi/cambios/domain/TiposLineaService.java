package com.claro.gestionrecursosapi.cambios.domain;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.cambios.entity.TiposLineaEntity;
import com.claro.gestionrecursosapi.cambios.repository.ICambiosRepository;
import com.claro.gestionrecursosapi.cambios.repository.ITiposLineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TiposLineaService implements ICrudService<TiposLineaEntity, Integer> {

    @Autowired
    private ITiposLineaRepository service;

    @Override
    public TiposLineaEntity save(TiposLineaEntity tiposLineaEntity) {
        return null;
    }

    @Override
    public Iterable<TiposLineaEntity> saveAll(Iterable<TiposLineaEntity> tiposLineaEntities) {
        return null;
    }

    @Override
    public Optional<TiposLineaEntity> findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public Iterable<TiposLineaEntity> findAll() {
        return service.findAll();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(TiposLineaEntity tiposLineaEntity) {

    }

    @Override
    public void deleteAll(Iterable<TiposLineaEntity> tiposLineaEntities) {

    }

    @Override
    public void deleteAll() {

    }
}
