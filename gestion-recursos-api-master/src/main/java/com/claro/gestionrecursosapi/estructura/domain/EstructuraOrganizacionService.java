package com.claro.gestionrecursosapi.estructura.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.claro.gestionrecursosapi.gerente.entity.GerenteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.estructura.entity.EstructuraorganizacionalEntity;
import com.claro.gestionrecursosapi.estructura.repository.IEstructuraOrganizacionalRepository;

@Service
@Validated
public class EstructuraOrganizacionService implements ICrudService<EstructuraorganizacionalEntity, Integer> {

    @Autowired
    private IEstructuraOrganizacionalRepository repository;

    @Override
    public EstructuraorganizacionalEntity save(EstructuraorganizacionalEntity entity) {
        try {
            if (entity.getCodempleado_responsable() == 0) {
                entity.setCodempleado_responsable(null);
            }
            EstructuraorganizacionalEntity newEstructura = new EstructuraorganizacionalEntity();
            newEstructura.setCodempleado_responsable(entity.getCodempleado_responsable());
            newEstructura.setCodestructuratipo(entity.getCodestructuratipo());
            newEstructura.setNombre(entity.getNombre());
            newEstructura.setCodpadre(entity.getCodpadre());
            repository.save(newEstructura);
            String jerarquia = newEstructura.getCodpadre() != null
                    ? repository.findById(newEstructura.getCodpadre()).get().getJerarquia()
                    : "";
            jerarquia = jerarquia + this.jerarquia(newEstructura.getId());

            newEstructura.setJerarquia(jerarquia);
            repository.save(newEstructura);
            // ActualizarCodEstructuralEmpleado(newEstructura.getId(),
            // newEstructura.getCodempleado_responsable());
            return newEstructura;
        } catch (Exception e) {
            throw e;
        }

    }


    private String jerarquia(Integer id) {
        return String.format("%010d", id);
    }

    @Override
    public Iterable<EstructuraorganizacionalEntity> saveAll(Iterable<EstructuraorganizacionalEntity> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public Optional<EstructuraorganizacionalEntity> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public Iterable<EstructuraorganizacionalEntity> findAllFiltrada() {
        return repository.findEstructuraorganizacionalEntityByCodestructuratipo();
    }

    @Override
    public Iterable<EstructuraorganizacionalEntity> findAll() {
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
    public void delete(EstructuraorganizacionalEntity entity) {
        repository.delete(entity);

    }

    @Override
    public void deleteAll(Iterable<EstructuraorganizacionalEntity> entities) {
        repository.deleteAll(entities);

    }

    @Override
    public void deleteAll() {

    }

    public Iterable<GerenteEntity> findJerarquiaAndTipoEstructura(Integer padre, Integer tipoEstructura) {
        return repository.findJerarquiaAndTipoEstructura(padre, tipoEstructura);
    }

    public Iterable<EstructuraorganizacionalEntity> getEstructuraXCodProyecto(int codProyecto) {
        return repository.getEstructuraXCodProyecto(codProyecto);
    }

    public Iterable<EstructuraorganizacionalEntity> getEstructuraxCodPadre(int codPadre) {
        return repository.getEstructuraxCodPadre(codPadre);
    }

    public List<EstructuraorganizacionalEntity> getEstructuraXGerencia(int codGerente) {
        return repository.getEstructuraXGerencia(llenarDigitosJerarquia(codGerente));
    }

    public List<EstructuraorganizacionalEntity> getEstructuraXTipo(int codTipo) {
        return repository.getEstructuraXTipo(codTipo);
    }

    public List<EstructuraorganizacionalEntity> getEstructuraFiltroDonuts(int codProyecto) {
        List<String> jerarquias = repository.jerarquiasporProyecto(codProyecto).stream().distinct().collect(Collectors.toList());
        return repository.getEstructuraFiltroDonuts(jerarquias);

    }


    public EstructuraorganizacionalEntity getGerenteporCodigoEstructura(int codEstructura) {
        try {
            Optional<EstructuraorganizacionalEntity> estructura = repository.findById(codEstructura);
            if (!estructura.isPresent())
                return new EstructuraorganizacionalEntity();
            int tipoestructura = estructura.get().getCodestructuratipo();
            if (tipoestructura == 2) {
                estructura = repository.findById(estructura.get().getCodpadre());
            }
            if (tipoestructura == 3 || tipoestructura == 4) {
                String jerarquia = estructura.get().getJerarquia().substring(0, 10);
                Integer codgerente = Integer.parseInt(jerarquia);
                estructura = repository.findById(codgerente);
            }
            return estructura.get();
        } catch (NullPointerException e) {
            //TODO: handle exception
            return new EstructuraorganizacionalEntity();
        }

    }

    private String llenarDigitosJerarquia(int codPadre) {
        String cadenares = "";
        int max = 10;
        int longitudcodigo = String.valueOf(codPadre).length();
        for (int i = 0; i < max - longitudcodigo; i++) {
            cadenares += "0";
        }
        cadenares = cadenares + String.valueOf(codPadre);
        return cadenares;
    }

}
