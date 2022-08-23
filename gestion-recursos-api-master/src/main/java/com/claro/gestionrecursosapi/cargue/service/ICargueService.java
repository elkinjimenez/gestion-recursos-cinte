package com.claro.gestionrecursosapi.cargue.service;

import com.claro.gestionrecursosapi.cargue.model.CarguePresupuesto;
import com.claro.gestionrecursosapi.cargue.model.TablaPruebaCargaPresupuesto;
import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoEntity;

import java.util.List;
import java.util.Map;

public interface ICargueService {

    public Map<String, List<CarguePresupuesto>> carguePresupuesto(List<CarguePresupuesto> lCarguePresupuesto, String usuarioSesion);
}
