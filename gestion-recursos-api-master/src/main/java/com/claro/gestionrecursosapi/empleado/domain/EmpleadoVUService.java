package com.claro.gestionrecursosapi.empleado.domain;

import java.util.List;
import java.util.Optional;

import com.claro.gestionrecursosapi.opcionfront.domain.OpcionFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEstructuraOrganizacionalVU;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoVU;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoVURepository;
import java.util.stream.Collectors;

@Service
public class EmpleadoVUService {

    @Autowired
    private IEmpleadoVURepository repository;

    @Autowired
    private OpcionFrontService opcionrepository;

    @Autowired
    private EmpleadoService empleadoService;

    public Iterable<EmpleadoVU> findAll(String CLUSUARIO) {

        if (opcionrepository.ValidarsiUsurioExiste(CLUSUARIO) > 0) {
            List<EmpleadoVU> listacompleta = (List<EmpleadoVU>) repository.findAll();
            List<EmpleadoVU> listafiltrada = listacompleta.stream().filter(dato -> dato.getFechafin() == null).collect(Collectors.toList());
            Iterable<EmpleadoVU> it = () -> listafiltrada.stream().iterator();
            return it;
        }
        EmpleadoEstructuraOrganizacionalVU lastEmpleadoParent = empleadoService.getLastEmpleadoParent(CLUSUARIO);
        return repository.findAll(CLUSUARIO, lastEmpleadoParent.getEstructuraJerarquia());
    }

    public Iterable<EmpleadoVU> findTodos() {
        return repository.findAllWithOutUser();
    }

    public Optional<EmpleadoVU> findById(Integer id) {
        return repository.findById(id);
    }

    public Optional<EmpleadoVU> findByCodEmpleado(Integer codpersona) {
        return repository.findByCodEmpleado(codpersona);
    }

    public Iterable<EmpleadoVU> findAllGerentes() {
        return repository.findAllGerentes();
    }

    public Iterable<EmpleadoVU> findAllBycodEstructura(Integer codTipoEstructura) {
        return repository.findAllBycodEstructura(codTipoEstructura);
    }

}
