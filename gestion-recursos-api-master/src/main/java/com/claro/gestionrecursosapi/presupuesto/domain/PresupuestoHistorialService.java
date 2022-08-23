package com.claro.gestionrecursosapi.presupuesto.domain;

import com.claro.gestionrecursosapi.excepcion.BusinessException;
import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoEntity;
import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoHistorialEntity;
import com.claro.gestionrecursosapi.presupuesto.enums.TipoHistorialEnum;
import com.claro.gestionrecursosapi.presupuesto.repository.IPresupuestoHistorialRepository;
import com.claro.gestionrecursosapi.presupuesto.repository.IPresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PresupuestoHistorialService implements IPresupuestoHistorialService {

    //@Autowired
    //private IPresupuestoHistorialRepository iPresupuestoHistorialRepository;

    //@Autowired
    //private IPresupuestoRepository iPresupuestoRepository;

    @Override
    public void guardarHistorialPresupuesto(PresupuestoEntity presupuestoEntity, String usuarioSesion) throws BusinessException {
        /*Optional<PresupuestoEntity> o = iPresupuestoRepository.findById(presupuestoEntity.getId());
        PresupuestoHistorialEntity presupuestoHistorialEntity = null;
        if (!(o.isPresent())) {
            throw new BusinessException("Presupuesto no encontrado");
        }
        presupuestoHistorialEntity = new PresupuestoHistorialEntity(
                presupuestoEntity,
                usuarioSesion,
                o.get().getElemento_pep(),
                o.get().getPresupuesto_ussd(),
                presupuestoEntity.getPresupuesto_ussd(),
                o.get().getPresupuesto_cop(),
                presupuestoEntity.getPresupuesto_cop(),
                TipoHistorialEnum.INDIVIDUAL.getCodigo());

        iPresupuestoHistorialRepository.save(presupuestoHistorialEntity);*/

    }
}
