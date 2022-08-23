package com.claro.gestionrecursosapi.cargue.service;

import com.claro.gestionrecursosapi.cargue.model.CarguePresupuesto;
import com.claro.gestionrecursosapi.cargue.model.TablaPruebaCargaPresupuesto;
//import com.claro.gestionrecursosapi.cargue.repository.TablaPruebaCargaPresupuestoRepository;
import com.claro.gestionrecursosapi.excepcion.DataNotFoundException;
import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoEntity;
import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoHistorialEntity;
import com.claro.gestionrecursosapi.presupuesto.enums.TipoHistorialEnum;
import com.claro.gestionrecursosapi.presupuesto.repository.IPresupuestoHistorialRepository;
import com.claro.gestionrecursosapi.presupuesto.repository.IPresupuestoRepository;
import com.claro.gestionrecursosapi.proyecto.entity.ProyectoEntity;
import com.claro.gestionrecursosapi.proyecto.repository.IProyectoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CargueService implements ICargueService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CargueService.class);

    private static final Integer TIPO_PROYECTO_POR_DEFINIR = 11;

    @Autowired
    private IPresupuestoRepository iPresupuestoRepository;

    @Autowired
    private IProyectoRepository iProyectoRepository;

    /*@Autowired
    private TablaPruebaCargaPresupuestoRepository tablaPruebaCargaPresupuestoRepository;*/

    @Autowired
    private IPresupuestoHistorialRepository iPresupuestoHistorialRepository;

    @Override
    @Transactional
    public Map<String, List<CarguePresupuesto>> carguePresupuesto(List<CarguePresupuesto> lCarguePresupuesto, String usuarioSesion) {
        //List<ProyectoEntity> lProyectoEntityBD = new ArrayList<>();
        List<PresupuestoEntity> lPresupuestoEntityBD = new ArrayList<>();
        List<TablaPruebaCargaPresupuesto> lTablaPruebaCargaPresupuesto = new ArrayList<>();
        Map<String, List<CarguePresupuesto>> resumen = new HashMap<>();
        List<CarguePresupuesto> presupuestosActualizados = new ArrayList<>();
        List<CarguePresupuesto> presupuestosCreados = new ArrayList<>();
        List<CarguePresupuesto> presupuestosConErrores = new ArrayList<>();
        List<String> lString = lCarguePresupuesto.stream()
                .map(c -> c.getElementoPEP())
                .collect(Collectors.toList());
        if (!lString.isEmpty()) {
            lPresupuestoEntityBD = iPresupuestoRepository.buscarPresupuestoPorCodigoPEP(lString);
        }

        List<String> listaElementosPEPBD = lPresupuestoEntityBD.stream()
                .map(c -> c.getElemento_pep())
                .collect(Collectors.toList());

        //Proceso de actualización y auditoria de presupuestos existentes
        for (CarguePresupuesto carguePresupuesto : lCarguePresupuesto) {
            for (PresupuestoEntity pre : lPresupuestoEntityBD) {
                if (pre.getElemento_pep().equals(carguePresupuesto.getElementoPEP())) {
                    PresupuestoHistorialEntity presupuestoHistorialEntity = new PresupuestoHistorialEntity(
                            pre,
                            usuarioSesion,
                            pre.getElemento_pep(),
                            pre.getSaldo_ussd(),
                            Objects.nonNull(carguePresupuesto.getUsd()) ? new BigDecimal(carguePresupuesto.getUsd()) : BigDecimal.ZERO,
                            pre.getSaldo_cop(),
                            Objects.nonNull(carguePresupuesto.getCop()) ? new BigDecimal(carguePresupuesto.getCop()) : BigDecimal.ZERO,
                            pre.getPresupuesto_ussd(),
                            Objects.nonNull(carguePresupuesto.getPresupuestoUSD()) ? new BigDecimal(carguePresupuesto.getPresupuestoUSD()) : BigDecimal.ZERO,
                            pre.getPresupuesto_cop(),
                            Objects.nonNull(carguePresupuesto.getPresupuestoCOP()) ? new BigDecimal(carguePresupuesto.getPresupuestoCOP()) : BigDecimal.ZERO,
                            TipoHistorialEnum.CARGA_MASIVA.getCodigo()
                    );
                    //if (Objects.nonNull(pro.getCodpresupuesto())) {
                        //pro.getCodpresupuesto().setPresupuesto_cop(Objects.nonNull(carguePresupuesto.getPresupuestoCOP()) ? new BigDecimal(carguePresupuesto.getPresupuestoCOP()) : BigDecimal.ZERO);
                        //pro.getCodpresupuesto().setPresupuesto_ussd(Objects.nonNull(carguePresupuesto.getPresupuestoUSD()) ? new BigDecimal(carguePresupuesto.getPresupuestoUSD()) : BigDecimal.ZERO);
                        pre.setSaldo_cop(Objects.nonNull(carguePresupuesto.getCop()) ? new BigDecimal(carguePresupuesto.getCop()) : BigDecimal.ZERO);
                        pre.setSaldo_ussd(Objects.nonNull(carguePresupuesto.getUsd()) ? new BigDecimal(carguePresupuesto.getUsd()) : BigDecimal.ZERO);

                    //}

                    try {
                        //iProyectoRepository.save(pro);
                        iPresupuestoRepository.save(pre);
                    } catch (Exception ex) {
                        presupuestosConErrores.add(carguePresupuesto);
                    }

                    iPresupuestoHistorialRepository.save(presupuestoHistorialEntity);
                    presupuestosActualizados.add(carguePresupuesto);
                    break;
                }
            }
        }

        //Proceso de creación de proyectos y presupuestos que no existan en bd
        //for (CarguePresupuesto carguePresupuesto : lCarguePresupuesto) {
          //  if (!listaCodigosProyectosBD.contains(carguePresupuesto.getIdProyecto())) {
                /*ProyectoEntity proyectoEntity = new ProyectoEntity();
                proyectoEntity.setCodigoproyecto(carguePresupuesto.getIdProyecto());
                proyectoEntity.setNombre(carguePresupuesto.getNombreProyecto());
                proyectoEntity.setCodproyectotipo(TIPO_PROYECTO_POR_DEFINIR);
                PresupuestoEntity presupuestoEntity = new PresupuestoEntity();
                presupuestoEntity.setElemento_pep(carguePresupuesto.getElementoPEP());
                presupuestoEntity.setPresupuesto_cop(new BigDecimal(carguePresupuesto.getMontoPresupuesto()));
                proyectoEntity.setCodpresupuesto(presupuestoEntity);
                try {
                    iProyectoRepository.save(proyectoEntity);
                } catch (Exception ex) {*/
                    //carguePresupuesto.setObservacion("ID de proyecto no encontrado");
                    //presupuestosConErrores.add(carguePresupuesto);
                //}
                //PresupuestoHistorialEntity presupuestoHistorialEntity = new PresupuestoHistorialEntity(proyectoEntity.getCodpresupuesto(), null, proyectoEntity.getCodpresupuesto().getElemento_pep(), proyectoEntity.getCodpresupuesto().getPresupuesto_ussd(), proyectoEntity.getCodpresupuesto().getPresupuesto_ussd(), proyectoEntity.getCodpresupuesto().getPresupuesto_cop(), proyectoEntity.getCodpresupuesto().getPresupuesto_cop(), TipoHistorialEnum.CARGA_MASIVA.getCodigo());
                //iPresupuestoHistorialRepository.save(presupuestoHistorialEntity);
                //presupuestosCreados.add(carguePresupuesto);
        //    }
        //}

        /*lCarguePresupuesto.stream()
                .forEach(c -> {
                    TablaPruebaCargaPresupuesto presupuesto = new TablaPruebaCargaPresupuesto();
                    presupuesto.setCodigoProyecto(c.getIdProyecto());
                    presupuesto.setNombreGerente(c.getNombreGerente());
                    presupuesto.setElementoPEP(c.getElementoPEP());
                    presupuesto.setProyecto(c.getNombreProyecto());
                    presupuesto.setMontoPresupuesto(new BigDecimal(c.getMontoPresupuesto()));
                    lTablaPruebaCargaPresupuesto.add(presupuesto);
                });

        tablaPruebaCargaPresupuestoRepository.saveAll(lTablaPruebaCargaPresupuesto);*/

        resumen.put("actualizados", presupuestosActualizados);
        //resumen.put("creados", presupuestosCreados);
        //resumen.put("errores", presupuestosConErrores);

        return resumen;


    }


}
