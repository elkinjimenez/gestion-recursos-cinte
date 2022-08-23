package com.claro.gestionrecursosapi.hallazgo.domain;

import com.claro.gestionrecursosapi.compromisosfabrica.dto.CompromisosFabricaDto;
import com.claro.gestionrecursosapi.compromisosfabrica.entity.CompromisosFabricaEntity;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEstructuraOrganizacionalVU;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoVU;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoProyectosEntity;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgosReportadosEntity;
import com.claro.gestionrecursosapi.hallazgo.repository.IHallazgosReportadosRepository;
import com.claro.gestionrecursosapi.novedad.entity.NovedadEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class HallazgoService {
	
	@Autowired
	IHallazgosReportadosRepository hallazgosReportadosRepository;
	
    public List<HallazgoProyectosEntity> filterProjects(List<HallazgoProyectosEntity> registros){
        List<HallazgoProyectosEntity> proyectosFiltrados = new ArrayList<>();

        Date fechaActual = new Date();

        for (HallazgoProyectosEntity proyecto: registros) {

            for (CompromisosFabricaEntity compromiso : proyecto.getCompromisos()){
                //Obtenemos la diferencia en milisegundos entre las dos fechas y la comparamos con los 60 dias

                if (Objects.nonNull(compromiso.getFechaDespliegueReal()) && ((fechaActual.getTime()-compromiso.getFechaDespliegueReal().getTime())/86400000) <= 60){
                    proyectosFiltrados.add(proyecto);
                    break;
                }
            }

        }
        return proyectosFiltrados;
    }
    
    public HallazgosReportadosEntity getHallazgosById(Integer id) {
    	return hallazgosReportadosRepository.findByIdHallazgo(id);
	}
}
