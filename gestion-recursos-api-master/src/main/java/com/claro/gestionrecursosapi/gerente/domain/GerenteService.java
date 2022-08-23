package com.claro.gestionrecursosapi.gerente.domain;

import com.claro.gestionrecursosapi.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoVU;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoVURepository;
import com.claro.gestionrecursosapi.estructura.entity.EstructuraorganizacionalEntity;
import com.claro.gestionrecursosapi.estructura.repository.IEstructuraOrganizacionalRepository;
import com.claro.gestionrecursosapi.gerente.entity.GerenteEntity;
import com.claro.gestionrecursosapi.gerente.repository.IGerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GerenteService {

    @Autowired
    private IGerenteRepository repository;
    
    @Autowired
    EmpleadoService serviceempleado;

    @Autowired
    private IEstructuraOrganizacionalRepository iEstructuraOrganizacionalRepository;

    @Autowired
    private IEmpleadoVURepository iEmpleadoVURepository;

    public Iterable<GerenteEntity> findAllGerentes() {
        return repository.findAllGerentes();
    }

    public GerenteEntity findGerenteByCodEmpleado(Integer id) {
        return repository.findGerenteByCodEmpleado(id);
    }

    @Transactional
    public GerenteEntity actualizarGerente(GerenteEntity gerenteEntity, Long id) {

        Optional<EstructuraorganizacionalEntity> estructuraOrgActual = iEstructuraOrganizacionalRepository.findById(id.intValue());
        Optional<EmpleadoVU> empleado = iEmpleadoVURepository.findByCodEmpleado(gerenteEntity.getCodEmpleado());
        if(!empleado.isPresent()){
            EmpleadoVU e = new EmpleadoVU();
            e.setNombre("SIN ASIGNAR");
            e.setCodempleado(null);
            gerenteEntity.setCodEmpleado(null);
            empleado =  Optional.of(e);
            
        }
        estructuraOrgActual.get().setNombre(gerenteEntity.getNombreGerencia());
        estructuraOrgActual.get().setCodempleado_responsable(gerenteEntity.getCodEmpleado());
        if (gerenteEntity.getCodPadre() != null) {
            Optional<EstructuraorganizacionalEntity> estructuraOrgPadre = iEstructuraOrganizacionalRepository.findById(gerenteEntity.getCodPadre());
            String jerarquiaPadre = estructuraOrgPadre.get().getJerarquia();
            if (estructuraOrgPadre.get().getCodestructuratipo() == 1) {
                String jerarquiacoordinador = estructuraOrgActual.get().getJerarquia();
                String idCoordinador = jerarquiacoordinador.substring(jerarquiacoordinador.length() - 10, jerarquiacoordinador.length());
                String nuevajerarquiacoordinador = jerarquiaPadre + idCoordinador;
                Iterable<EstructuraorganizacionalEntity> listahijos = iEstructuraOrganizacionalRepository.findIdXJerarquia(jerarquiacoordinador, 3);
                for (EstructuraorganizacionalEntity hijo : listahijos) {
                    String jerarquiaactual = hijo.getJerarquia();
                    String nuevajerarquia = jerarquiaactual.substring(jerarquiaactual.length() - 10, jerarquiaactual.length());
                    hijo.setJerarquia(nuevajerarquiacoordinador + nuevajerarquia);
                    hijo.setCodpadre(estructuraOrgActual.get().getId());
                    iEstructuraOrganizacionalRepository.save(hijo);
                }
                estructuraOrgActual.get().setCodpadre(gerenteEntity.getCodPadre());
                jerarquiaPadre = jerarquiaPadre + this.jerarquia(id.intValue());
                estructuraOrgActual.get().setJerarquia(jerarquiaPadre);
            }
            if (estructuraOrgPadre.get().getCodestructuratipo() == 2 || estructuraOrgPadre.get().getCodestructuratipo() == 3) {
                estructuraOrgActual.get().setCodpadre(gerenteEntity.getCodPadre());
                jerarquiaPadre = jerarquiaPadre + this.jerarquia(id.intValue());
                estructuraOrgActual.get().setJerarquia(jerarquiaPadre);
            }
        }
        EstructuraorganizacionalEntity estructuraorganizacionalEntity = iEstructuraOrganizacionalRepository.save(estructuraOrgActual.get());
        GerenteEntity gerenteEntityRes = new GerenteEntity();
        gerenteEntityRes.setId(estructuraorganizacionalEntity.getId().longValue());
        gerenteEntityRes.setNombreGerencia(estructuraorganizacionalEntity.getNombre());
        gerenteEntityRes.setNombreGerente(empleado.get().getNombre());
        gerenteEntityRes.setCodEmpleado(gerenteEntity.getCodEmpleado());
        //actualizarEnTablaEmpleado(gerenteEntity.getCodEmpleado(), estructuraOrgActual.get());
        return gerenteEntityRes;
    }

    private String jerarquia(Integer id) {
        return String.format("%010d", id);
    }

//    private void actualizarEnTablaEmpleado(Integer codempleadonuevo, EstructuraorganizacionalEntity estructuraactual) {
//
//        EmpleadoEntity empleadoanterior = serviceempleado.findByCodestructuraorganizacional(estructuraactual.getId());
//        if(empleadoanterior == null){
//             EstructuraorganizacionalEntity eo =  iEstructuraOrganizacionalRepository.findById(estructuraactual.getId()).get();
//             int codempleadoanterior = eo.getCodempleado_responsable();
//             empleadoanterior =  iEmpleadoRepository.findById(codempleadoanterior).get();
//
//        }
//        empleadoanterior.setCodestructuraorganizacional(251);
//        iEmpleadoRepository.save(empleadoanterior);
//        EmpleadoEntity empleadoactual = iEmpleadoRepository.findById(codempleadonuevo).get();
//        empleadoactual.setCodestructuraorganizacional(estructuraactual.getId());
//        iEmpleadoRepository.save(empleadoactual);
//
//    }

}
