package com.claro.gestionrecursosapi.persona.domain;

import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.persona.entity.HabilidadEntity;
import com.claro.gestionrecursosapi.persona.repository.IHabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class HabilidadService {

    @Autowired
    private IHabilidadRepository habilidadRepository;

    public Iterable<HabilidadEntity> buscarTodo() throws NoExisteExcepcion{
        Iterable<HabilidadEntity> resultadoConsulta = habilidadRepository.findAll();
        if (((Collection<?>) resultadoConsulta).size() > 0) {
            return resultadoConsulta;
        } else {
            throw new NoExisteExcepcion("No hay resultados");
        }

    }

    public Iterable<HabilidadEntity> buscarPorCodPersona(Integer codPersona) {
        return habilidadRepository.findByIdcodpersona(codPersona);
    }

    public HabilidadEntity save(HabilidadEntity habilidadEntity) throws YaExisteExcepcion, DataIncorrectaExcepcion {
        if (habilidadRepository.existsByNombre(habilidadEntity.getNombre())){
            throw new YaExisteExcepcion("Ya existe una habilidad con ese nombre");
        }else {
            return habilidadRepository.save(habilidadEntity);
        }
    }
}
