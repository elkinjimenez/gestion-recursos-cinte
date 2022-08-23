package com.claro.gestionrecursosapi.tarea.domain;

import java.util.Date;
import java.util.Optional;

import com.claro.gestionrecursosapi.opcionfront.entity.OpcionFrontEntity;
import com.claro.gestionrecursosapi.opcionfront.repository.OpcionFrontRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.tarea.entity.TareaActividadVU;
import com.claro.gestionrecursosapi.tarea.repository.ITareaActividadVURepository;

@Service
public class TareaActividadVUService {

    @Autowired
    private ITareaActividadVURepository repository;

    @Autowired
    private OpcionFrontRepository opcionFrontRepository;

    public Iterable<TareaActividadVU> findAll() {
        return repository.findAll();
    }

    public Iterable<TareaActividadVU> findAllByFiltro(String CLUSUARIO, Integer codproyecto, Integer codproveedor, Integer codpersona, Date fechainicio, Date fechafin) {
        if (codpersona != null || codproveedor != null) {
            codproyecto = codproyecto == null ? 0 : codproyecto;
            codproveedor = codproveedor == null ? 0 : codproveedor;
            codpersona = codpersona == null ? 0 : codpersona;
            OpcionFrontEntity opcionFront = opcionFrontRepository.findByNombre(CLUSUARIO);
            if (opcionFront != null) {
                return repository.findByPersonaAdmin(codpersona, fechainicio, fechafin, codproveedor, codproyecto);
            }
            return repository.findByPersona(CLUSUARIO, codproyecto, codproveedor, codpersona, fechainicio, fechafin);
        } else {
            return repository.findAllByFiltro(CLUSUARIO, codproyecto, codproveedor, fechainicio, fechafin);
        }
    }

    public Optional<TareaActividadVU> findById(Integer id) {
        return repository.findById(id);
    }

}
