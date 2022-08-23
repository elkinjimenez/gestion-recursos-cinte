package com.claro.gestionrecursosapi.empleado.domain;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoVU;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoEstructuraOrganizacionalRepository;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoVURepository;
import com.claro.gestionrecursosapi.estructura.entity.EstructuraorganizacionalEntity;
import com.claro.gestionrecursosapi.seguridad.domain.UsuarioService;
import com.claro.gestionrecursosapi.seguridad.entity.UsuarioEntity;
import com.claro.gestionrecursosapi.seguridad.entity.UsuarioRolEntity;
import com.claro.gestionrecursosapi.seguridad.entity.UsuarioRolesEntity;
import com.claro.gestionrecursosapi.seguridad.repository.IUsuarioRepository;
import com.claro.gestionrecursosapi.seguridad.repository.IUsuarioRolRepository;
import com.claro.gestionrecursosapi.seguridad.repository.IUsuarioRolesRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEntity;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEstructuraOrganizacionalVU;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoRepository;
import com.claro.gestionrecursosapi.excepcion.BusinessException;

@Service
@Validated
public class EmpleadoService implements ICrudService<EmpleadoEntity, Integer> {

    @Autowired
    private IEmpleadoRepository repository;

    @Autowired
    private IUsuarioRolesRepository iUsuarioRolesRepository;

    @Autowired
    private IUsuarioRolRepository iUsuarioRolRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private IEmpleadoEstructuraOrganizacionalRepository iEmpleadoEstructuraOrganizacionalRepository;


    @Autowired
    private IEmpleadoVURepository empleado_vu;

    @Autowired
    private UsuarioService serviceusuario;

    @Override
    @Transactional
    public EmpleadoEntity save(EmpleadoEntity empleado) {
        if (!empleado.getUsuarioclaroedicion().equals(empleado.getUsuarioclaro())) {
            EmpleadoEntity empleadoNuevoEntity = repository.findActivoByUsuarioclaro(empleado.getUsuarioclaroedicion(), new Date());

            // valida si el nombre de usuario ya se está usando
            if (Objects.nonNull(empleadoNuevoEntity)) {
                throw new BusinessException("El nombre de usuario '" + empleado.getUsuarioclaroedicion() + "' ya está en uso");
            }
        }
        if (Objects.nonNull(empleado.getId())) {
            UsuarioEntity usuarioEntity = iUsuarioRepository.findByUsuario(empleado.getUsuarioclaro());
            UsuarioRolesEntity usuarioRolesEntity = iUsuarioRolesRepository.obtenerUsuarioRoles(empleado.getUsuarioclaro());
            Optional<UsuarioRolEntity> usuarioRolEntity = iUsuarioRolRepository.findById(empleado.getRol());

            if (usuarioEntity == null || usuarioEntity.equals(""))
                usuarioEntity = crearUsuario(empleado);
            if (Objects.isNull(usuarioRolesEntity)) {
                usuarioRolesEntity = new UsuarioRolesEntity();
                usuarioRolesEntity.setUsuarioEntity(usuarioEntity);
                usuarioRolesEntity.getUsuarioEntity().setCodusuariorol(empleado.getRol());

            } else {
                usuarioRolesEntity.getUsuarioEntity().setCodusuariorol(empleado.getRol());
            }
            // si se editó el nombre de usuario, se actualiza el empleado y el usuario en db
            if (empleado.getUsuarioclaroedicion() != null && !empleado.getUsuarioclaroedicion().equals(empleado.getUsuarioclaro())) {
                empleado.setUsuarioclaro(empleado.getUsuarioclaroedicion());
                usuarioEntity.setUsuario(empleado.getUsuarioclaroedicion());
                iUsuarioRepository.save(usuarioEntity);
            }
            usuarioRolesEntity.setUsuarioRolEntity(usuarioRolEntity.get());
            iUsuarioRolesRepository.save(usuarioRolesEntity);
        } else {
            empleado.setUsuarioclaro(empleado.getUsuarioclaroedicion());
        }
        return repository.save(empleado);
    }

    @Override
    public Iterable<EmpleadoEntity> saveAll(Iterable<EmpleadoEntity> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public Iterable<EmpleadoEntity> findAll() {
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
    public void delete(EmpleadoEntity entity) {
        repository.delete(entity);

    }

    @Override
    public void deleteAll(Iterable<EmpleadoEntity> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public Optional<EmpleadoEntity> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public EmpleadoEntity findByCodPersona(Integer codpersona) {
        return repository.findByCodPersona(codpersona);
    }

    public EmpleadoEntity findByUsuarioclaro(String usuarioClaro) {
        return repository.findByusuarioclaro(usuarioClaro);
    }

    public EmpleadoEntity findActivoByCodPersona(Integer codpersona) {
        return repository.findActivoByCodPersona(codpersona, new Date());
    }

    public Iterable<EmpleadoEstructuraOrganizacionalVU> findAllEmpleadosConEstructuraOrganizacional() {
        return iEmpleadoEstructuraOrganizacionalRepository.findAll();
    }

    /**
     * Busca el primer empleado con la estructura organizacional indicada
     *
     * @param empleadoCodEstructuraPadre
     * @param empleados
     * @return
     */
    private EmpleadoEstructuraOrganizacionalVU getPrimerEmpleadoPorCodigoEstructura(Integer empleadoCodEstructuraPadre, Iterable<EmpleadoEstructuraOrganizacionalVU> empleados) {
        EmpleadoEstructuraOrganizacionalVU empleadoParent = null;

        for (EmpleadoEstructuraOrganizacionalVU empleado : empleados) {
            if (Objects.nonNull(empleadoParent)) {
                break;
            } else if (empleado.getEstructuraId().equals(empleadoCodEstructuraPadre)) {
                empleadoParent = empleado;
            }
        }
        ;

        return empleadoParent;
    }

    /**
     * busca el empleado pariente más lejano al empleado indicado, en el jerarquía de estructura oranizacional
     *
     * @param nombreUsuario
     * @return
     */
    public EmpleadoEstructuraOrganizacionalVU getLastEmpleadoParent(String nombreUsuario) {
        Iterable<EmpleadoEstructuraOrganizacionalVU> empleados = findAllEmpleadosConEstructuraOrganizacional();
        EmpleadoEstructuraOrganizacionalVU empleadoConsulta = null;
        EmpleadoEstructuraOrganizacionalVU empleadoLastParent = null;

        for (EmpleadoEstructuraOrganizacionalVU empleado : empleados) {
            if (empleado.getNombreUsuario().equals(nombreUsuario)) {
                empleadoConsulta = empleado;
            }
        }
        ;

        /**
         * se busca en todos los parientes del empleado actual,
         * hasta encontrar uno que no tenga padre, este último, será el último empleado pariente
         * y en consecuencia, uno de los que están en la cima de la jerarquía
         */
        if (Objects.nonNull(empleadoConsulta) && empleadoConsulta.getEstructuraIdPadre() != null) {
            EmpleadoEstructuraOrganizacionalVU currentEmpleadoParent = empleadoConsulta;
            while (Objects.nonNull(currentEmpleadoParent)) {
                empleadoLastParent = currentEmpleadoParent;
                if (currentEmpleadoParent.getEstructuraIdPadre() != null) {
                    currentEmpleadoParent = getPrimerEmpleadoPorCodigoEstructura(currentEmpleadoParent.getEstructuraIdPadre(), empleados);
                } else {
                    break;
                }
            }
        } else {
            // si no tiene padre, se asume el que consulta como último pariente
            empleadoLastParent = empleadoConsulta;
        }

        return empleadoLastParent;
    }

    private UsuarioEntity crearUsuario(EmpleadoEntity empleado) {

        //creacion del usuario del empleado
        UsuarioEntity user = new UsuarioEntity();
        user.setUsuario(empleado.getUsuarioclaroedicion());
        user.setEstado("A");
        Optional<EmpleadoVU> empleadovu = empleado_vu.findByCodEmpleado(empleado.getId());
        user.setNombre(empleadovu.get().getNombre());
        user.setClave(Hashing.sha256().hashString(empleado.getUsuarioclaroedicion(), StandardCharsets.UTF_8).toString());
        user.setCodpersona(empleado.getCodpersona());
        user.setCodusuariorol(empleado.getRol());
        return serviceusuario.save(user);

    }
    /**
     * obtiene el empleado con el codigo de la estructura organizacional en el parametro
     * @param codEstructura
     * @return
     */
    public EmpleadoEntity findByCodestructuraorganizacional(Integer codEstructura){
        try {
        	 List<EmpleadoEntity> resultado =  repository.findByCodestructuraorganizacional(codEstructura);
        	 if(resultado.isEmpty())
        		 return null;
        	 return resultado.get(0);
        }
        catch (NullPointerException e) {
        	return null;
		}
    }

    
}
