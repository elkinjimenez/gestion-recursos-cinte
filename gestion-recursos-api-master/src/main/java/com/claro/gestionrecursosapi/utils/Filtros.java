package com.claro.gestionrecursosapi.utils;

import com.claro.gestionrecursosapi.compromisosfabrica.dto.CompromisosFabricaDto;
import com.claro.gestionrecursosapi.compromisosfabrica.entity.CompromisosFabricaEntity;
import com.claro.gestionrecursosapi.hallazgo.entity.HallazgoProyectosEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Component
public class Filtros {
    public Iterable<CompromisosFabricaDto> filterProjects(Iterable<CompromisosFabricaDto> registros, int dias){
        List<CompromisosFabricaDto> proyectosFiltrados = new ArrayList<>();

        Date fechaActual = new Date();

            for (CompromisosFabricaDto compromiso : registros){
                //Obtenemos la diferencia en milisegundos entre las dos fechas y la comparamos con los 60 dias

                if (Objects.nonNull(compromiso.getFechaDespliegueReal()) && ((fechaActual.getTime()-compromiso.getFechaDespliegueReal().getTime())/86400000) <= dias){
                    proyectosFiltrados.add(compromiso);
                }
            }


        return proyectosFiltrados;
    }
}
