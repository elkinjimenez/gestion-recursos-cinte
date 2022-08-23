package com.claro.gestionrecursosapi.presupuesto.domain;

import com.claro.gestionrecursosapi.excepcion.BusinessException;
import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoEntity;

public interface IPresupuestoHistorialService {

    public void guardarHistorialPresupuesto(PresupuestoEntity presupuestoEntity, String usuarioSesion) throws BusinessException;

}
