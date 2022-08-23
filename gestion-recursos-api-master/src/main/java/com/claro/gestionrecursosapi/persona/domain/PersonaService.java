package com.claro.gestionrecursosapi.persona.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.persona.entity.PersonaEntity;
import com.claro.gestionrecursosapi.persona.repository.IPersonaRepository;

@Service
@Validated
public class PersonaService implements ICrudService<PersonaEntity, Integer> {

    @Autowired
    private IPersonaRepository repository;

    @Override
    public PersonaEntity save(PersonaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Iterable<PersonaEntity> saveAll(Iterable<PersonaEntity> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public Optional<PersonaEntity> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Iterable<PersonaEntity> findAll() {
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
    public void delete(PersonaEntity entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<PersonaEntity> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
    }

    public PersonaEntity findByCodtipodocumentoAndNumerodocumento(int tipoDocumento, Long documento) {
        return repository.findByCodtipodocumentoAndNumerodocumento(tipoDocumento, documento);
    }

    public PersonaEntity getPersonaXCodEmpleado(int codEmpleado) {
        return repository.getPersonaXCodEmpleado(codEmpleado);
    }

    public List<PersonaEntity> getPersonasSinVinculacion(){
        return repository.getPersonasSinVinculacion();
    }

}
