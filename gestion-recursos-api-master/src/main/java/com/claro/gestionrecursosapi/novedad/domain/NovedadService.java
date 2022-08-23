package com.claro.gestionrecursosapi.novedad.domain;

import java.util.Optional;

import com.claro.gestionrecursosapi.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEstructuraOrganizacionalVU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.novedad.entity.NovedadEntity;
import com.claro.gestionrecursosapi.novedad.entity.TipoNovedadEntity;
import com.claro.gestionrecursosapi.novedad.repository.INovedadRepository;
import com.claro.gestionrecursosapi.novedad.repository.ITipoNovedadRepository;

@Service
public class NovedadService {

    @Autowired
    private INovedadRepository novedadRepository;

    @Autowired
    private ITipoNovedadRepository tipoNovedadRepository;

    @Autowired
    private EmpleadoService empleadoService;

    public Iterable<NovedadEntity> getAllNovedades(String CLUSUARIO) {
        EmpleadoEstructuraOrganizacionalVU lastEmpleadoParent = empleadoService.getLastEmpleadoParent(CLUSUARIO);

        return novedadRepository.findAll(lastEmpleadoParent.getEstructuraJerarquia());
    }

    public NovedadEntity crearNovedad(NovedadEntity novedad) {
        novedad.setId(getNextNovedadId());
        novedad.setEstadoRegistro(1);

        return novedadRepository.save(novedad);
    }

    public NovedadEntity modificarNovedad(NovedadEntity novedad) {
        Optional<NovedadEntity> novedadDb = novedadRepository.findById(novedad.getId());

        //anexado para actualizacion
        novedadDb.get().setCodTipoNovedad(novedad.getCodTipoNovedad());
        novedadDb.get().setFechaInicio(novedad.getFechaInicio());
        novedadDb.get().setFechaFin(novedad.getFechaFin());
        novedadDb.get().setAprobado(novedad.isAprobado());
        novedadDb.get().setObservacion(novedad.getObservacion());
        novedadDb.get().setEvidencia(novedad.getEvidencia());
        //---------------------------
        novedad.setEstadoRegistro(novedadDb.get().getEstadoRegistro());
        //retorno el save enviando novedadDb.get estaba novedad
        return novedadRepository.save(novedadDb.get());
    }

    public void eliminarNovedad(Integer id) {
        NovedadEntity novedad = novedadRepository.findById(id).get();
        novedad.setEstadoRegistro(9);

        novedadRepository.save(novedad);
    }

    public NovedadEntity getNovedadById(Integer id) {
        NovedadEntity novedad = novedadRepository.findById(id).get();

        if (novedad.getEstadoRegistro() == 1) {
            return novedad;
        } else {
            return null;
        }
    }

    public Iterable<TipoNovedadEntity> getTiposNovedad() {
        return tipoNovedadRepository.findAll();
    }

    private Integer getNextNovedadId() {
        Optional<NovedadEntity> result = novedadRepository.findLastRecord();

        if (result.isPresent()) {
            return result.get().getId() + 1;
        } else {
            return 1;
        }
    }
}
