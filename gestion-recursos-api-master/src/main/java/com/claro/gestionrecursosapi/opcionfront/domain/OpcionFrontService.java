package com.claro.gestionrecursosapi.opcionfront.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.opcionfront.entity.OpcionFrontEntity;
import com.claro.gestionrecursosapi.opcionfront.repository.OpcionFrontRepository;

@Service
public class OpcionFrontService {
    @Autowired
    private OpcionFrontRepository opcionFrontRepository;

    public Iterable<OpcionFrontEntity> findAllByGrupo(String grupo) {
        return opcionFrontRepository.findAllByGrupo(grupo).get();
    }

    public Iterable<OpcionFrontEntity> findGrupoByUsuario(String userName){
         Optional<Iterable<OpcionFrontEntity>> resultado =  opcionFrontRepository.findGrupoByUsuario(userName);
         return resultado.isPresent() ? resultado.get() : null;
    }

    public Integer ValidarsiUsurioExiste(String usuario){
        return opcionFrontRepository.ValidarsiUsurioExiste(usuario);
    }

}
