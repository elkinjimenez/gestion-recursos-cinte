package com.claro.gestionrecursosapi.horasextra.domain;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.horasextra.repository.IHorasExtraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import   com.claro.gestionrecursosapi.horasextra.entity.HorasExtraEntity;
import java.util.Optional;

@Service
public class HoraExtraService implements ICrudService<HorasExtraEntity, Integer> {

    @Autowired
    private IHorasExtraRepositorio repoHextra;


    @Override
    public HorasExtraEntity save(HorasExtraEntity horasExtraEntity) {
          return  repoHextra.save(horasExtraEntity);
    }

    @Override
    public Iterable<HorasExtraEntity> saveAll(Iterable<HorasExtraEntity> horasExtraEntities) {
        return repoHextra.saveAll(horasExtraEntities);
    }

    @Override
    public Optional<HorasExtraEntity> findById(Integer id) {
        return repoHextra.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repoHextra.existsById(id);
    }

    @Override
    public Iterable<HorasExtraEntity> findAll() {
         return  repoHextra.findAll();
    }

    @Override
    public long count() {
        return  repoHextra.count();
    }

    @Override
    public void deleteById(Integer id) {
        repoHextra.deleteById(id);
    }

    @Override
    public void delete(HorasExtraEntity horasExtraEntity) {
        repoHextra.delete(horasExtraEntity);
    }

    @Override
    public void deleteAll(Iterable<HorasExtraEntity> horasExtraEntities) {
        repoHextra.deleteAll(horasExtraEntities);
    }

    @Override
    public void deleteAll() {
        repoHextra.deleteAll();
    }
}
