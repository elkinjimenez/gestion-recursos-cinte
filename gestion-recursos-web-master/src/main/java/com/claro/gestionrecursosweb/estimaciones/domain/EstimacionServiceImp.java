package com.claro.gestionrecursosweb.estimaciones.domain;

import com.claro.gestionrecursosweb.estimaciones.dto.EstimacionDto;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EstimacionServiceImp implements IEstimacionService{

    private List<EstimacionDto> lista = new LinkedList<EstimacionDto>();
    
    EstimacionDto estimacion = new EstimacionDto();

    public EstimacionServiceImp(){
         //damos formato a la fecha
//           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//           lista = new LinkedList<EstimacionDto>();
       
    }

    @Override
    public List<EstimacionDto> traerTodos() {
        return lista;
    }

    @Override
    public EstimacionDto buscarPorId(Integer idEstimacion) {
        return null;
    }

    @Override
    public void guardar(EstimacionDto estimacion) {
    	lista.clear();
        if (estimacion.getPerfil().equals("1")){
            estimacion.setPerfil("DESARROLLO ESB");
        } else if (estimacion.getPerfil().equals("2")){
            estimacion.setPerfil("DESARROLLO EAP");
        } else if (estimacion.getPerfil().equals("3")){
            estimacion.setPerfil("DESARROLLO API/MS");
        } else if (estimacion.getPerfil().equals("4")){
            estimacion.setPerfil("DESARROLLO ODI");
        } if (estimacion.getPerfil().equals("5")){
            estimacion.setPerfil("ARQUITECTO API/MS");
        }
        
        int valorHora = 0;
        int horasEstimacion = 1;
        int horasDiseno = 2;
        int horasServicio = 63;
        int horasDespliegueQA = 7;
        int horasDocuArquitectura = 9;
        int horasPruebasQA = 5;
        int horasPruebasNoFuncionales = 3;
        int horasValiEstandaresDocu = 5;
        int horasSesionProyecto = 3;
        int horasPaps = 5;
        
        if (estimacion.getPerfil().equals("DESARROLLO ESB")){
            valorHora = 112800; 
            estimacion.setValorHoraServicio(valorHora);
        } else if (estimacion.getPerfil().equals("DESARROLLO EAP")){
            valorHora = 100200;
            estimacion.setValorHoraServicio(valorHora);
        } else if (estimacion.getPerfil().equals("DESARROLLO API/MS")){
            valorHora = 78286;
            estimacion.setValorHoraServicio(valorHora);
        } else if (estimacion.getPerfil().equals("DESARROLLO ODI")){
            valorHora = 112800;
            estimacion.setValorHoraServicio(valorHora);
        } else if (estimacion.getPerfil().equals("ARQUITECTO API/MS")){
            valorHora = 94200;
            estimacion.setValorHoraServicio(valorHora);
        }

        double costoEstimacion = valorHora * horasEstimacion;
        double costoDiseno = valorHora * horasDiseno;
        double costoServicio = valorHora * horasServicio * estimacion.getCantidadServicios();
        double costoDespliegueQA = valorHora * horasDespliegueQA * estimacion.getCantidadServicios();
        double costoDocuArquitectura = valorHora * horasDocuArquitectura * estimacion.getCantidadServicios();
        double costoPruebasQA = valorHora * horasPruebasQA * estimacion.getCantidadServicios();
        double costosPruebasNoFuncionales = valorHora * horasPruebasNoFuncionales * estimacion.getCantidadServicios();
        double costoValiEstandaresDocu = valorHora * horasValiEstandaresDocu * estimacion.getCantidadServicios();
        double costoSesionProyecto = valorHora * horasSesionProyecto * estimacion.getCantidadServicios();
        double costoPaps = valorHora * horasPaps * estimacion.getCantidadServicios();

        double costoTotal = costoEstimacion + costoDiseno + costoServicio + costoDespliegueQA + costoDocuArquitectura + costoPruebasQA + costosPruebasNoFuncionales + costoValiEstandaresDocu + costoSesionProyecto + costoPaps;

        estimacion.setHorasEstimacion(horasEstimacion);
        estimacion.setHorasDiseno(horasDiseno);
        estimacion.setHorasServicios(horasServicio * estimacion.getCantidadServicios());
        estimacion.setHorasDespliegueQA(horasDespliegueQA * estimacion.getCantidadServicios());
        estimacion.setHorasDocuArquitectura(horasDocuArquitectura * estimacion.getCantidadServicios());
        estimacion.setHorasPruebasQA(horasPruebasQA * estimacion.getCantidadServicios());
        estimacion.setHorasPruebasNoFuncionales(horasPruebasNoFuncionales * estimacion.getCantidadServicios());
        estimacion.setHorasValiEstandaresDocu(horasValiEstandaresDocu * estimacion.getCantidadServicios());
        estimacion.setHorasSesionProyecto(horasSesionProyecto * estimacion.getCantidadServicios());
        estimacion.setHorasPaps(horasPaps * estimacion.getCantidadServicios());

        estimacion.setCostoEstimacion((int)costoEstimacion);
        estimacion.setCostoDiseno((int)costoDiseno);
        estimacion.setCostoServicios((int)costoServicio);
        estimacion.setCostoDespliegueQA((int)costoDespliegueQA);
        estimacion.setCostoDocuArquitectura((int)costoDocuArquitectura);
        estimacion.setCostoPruebasQA((int)costoPruebasQA);
        estimacion.setCostoPruebasNoFuncionales((int)costosPruebasNoFuncionales);
        estimacion.setCostoValiEstandaresDocu((int)costoValiEstandaresDocu);
        estimacion.setCostoSesionProyecto((int)costoSesionProyecto);
        estimacion.setCostoPaps((int)costoPaps);
        estimacion.setCostoTotal((int)costoTotal);

        estimacion.setPorEstimacion((int)Math.round((costoEstimacion / costoTotal) * 100));
        estimacion.setPorDiseno((int)Math.round((costoDiseno / costoTotal) * 100));
        estimacion.setPorServicios((int)Math.round((costoServicio / costoTotal) * 100));
        estimacion.setPorDespliegueQA((int)Math.round((costoDespliegueQA / costoTotal) * 100));
        estimacion.setPorDocuArquitectura((int)Math.round((costoDocuArquitectura / costoTotal) * 100));
        estimacion.setPorPruebasQA((int)Math.round((costoPruebasQA / costoTotal) * 100));
        estimacion.setPorPruebasNoFuncionales((int)Math.round((costosPruebasNoFuncionales / costoTotal) * 100));
        estimacion.setPorSesionProyecto((int)Math.round((costoSesionProyecto / costoTotal) * 100));
        estimacion.setPorValiEstandaresDocu((int)Math.round((costoValiEstandaresDocu / costoTotal) * 100));
        estimacion.setPorPaps((int)Math.round((costoPaps / costoTotal) * 100));

        lista.add(estimacion);
    }
}
