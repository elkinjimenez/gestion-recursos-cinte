package com.claro.gestionrecursosapi.devops.domain;

import com.claro.gestionrecursosapi.devops.entity.Fields;
import com.claro.gestionrecursosapi.devops.entity.Ocupacion;
import com.claro.gestionrecursosapi.devops.entity.RespuestaDevOpsOcupacion;
import com.claro.gestionrecursosapi.empleado.domain.EmpleadoVUService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoVU;
import com.claro.gestionrecursosapi.tarea.entity.TareaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class OcupacionImplementacion {

    @Autowired
    private OcupacionApiImplementacion service;

    @Autowired
    private EmpleadoVUService empleadoVUService;

    @Autowired
    UsuariosInfoDevops serviceuserdevops;

    /**
     * @param tarea        la tarea que ava a registrar
     * @param tipoRegistro si el registro es 1 es para adicionar un nueva ocupacion si es 2 es para actualizar la ocupacion
     * @return si actualizo o registro return true de lo contrario false
     */
    public boolean RegistrarOcupacion(TareaEntity tarea, Integer tipoRegistro) throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'-05:00'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        if (tarea.getFechafinestimada() == null || tarea.getFechafinestimada() == null) {
            Date fechaactual = new Date(System.currentTimeMillis());
            tarea.setFechainiestimada(fechaactual);
            tarea.setFechafinestimada(fechaactual);
        }
        Ocupacion ocu = new Ocupacion();
        ocu.setTitle(tarea.getNombre());
        ocu.setState("New");
        ocu.setPorcentajedeocupacion(calcularPorcentajedeOcupacion(tarea));
        ocu.setFechainicio(sdf.format(tarea.getFechainiestimada()));
        ocu.setFechafin(sdf.format(tarea.getFechafinestimada()));
        ocu.setGerenciaDirEvolucionDigital("Gerencia de soluciones digitales");
        ocu.setEmpresa("Claro");
        ocu.setTipoAsignacion("Proyecto");
        ocu.setDescription(tarea.getDescripcion() == null ? "" : tarea.getDescripcion());
        ocu.setAssignedTo(getEmailEmpleado(tarea.getCodempleadoasignado()));
        ocu.setProyecto("");
        List<RespuestaDevOpsOcupacion> respuestas = new ArrayList<>();

        for (Fields f : Fields.values()) {
            RespuestaDevOpsOcupacion respuesta = new RespuestaDevOpsOcupacion();
            switch (f.nombre()) {
                case "Title":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getTitle());
                    break;
                case "State":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getState());
                    break;
                case "GerenciaDirEvolucionDigital":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getGerenciaDirEvolucionDigital());
                    break;
                case "AssignedTo":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getAssignedTo());
                    break;
                case "TipoAsignacion":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getTipoAsignacion());
                    break;
                case "Empresa":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getEmpresa());
                    break;
                case "Fechainicio":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getFechainicio());
                    break;
                case "Fechafin":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getFechafin());
                    break;
                case "Porcentajedeocupacion":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getPorcentajedeocupacion());
                    break;
                case "Description":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getDescription());
                    break;
                case "Proyecto":
                    respuesta.setPath(f.valor());
                    respuesta.setValue(ocu.getProyecto());
                    break;
                default:
                    respuesta.setValue("No hay mas");
                    break;
            }
            respuesta.setOp("add");
            respuesta.setFrom(null);
            respuestas.add(respuesta);
        }
        if (tipoRegistro == 1)
            return service.agregarOcupacion(respuestas);

        if (tipoRegistro == 2)
            return service.actualizarOcupacion(respuestas);
        else
            return false;
    }

    public String getEmailEmpleado(int codempleado) throws IOException {
        Optional<EmpleadoVU> empleado = empleadoVUService.findByCodEmpleado(codempleado);
        String usuarioclaro = empleado.get().getUsuarioclaro().toUpperCase();
        if (!usuarioclaro.equals("") || usuarioclaro != null) {
            return serviceuserdevops.getUserEmail(usuarioclaro.concat("@claro.com.co"));
        }
        return "";
    }

    public String calcularPorcentajedeOcupacion(TareaEntity tarea) {

        //ZoneId zona = ZoneId.of("America/Bogota");
        SimpleDateFormat formatfecha = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate fechainicial = LocalDate.parse(formatfecha.format(tarea.getFechainiestimada()));
        LocalDate fechafinal = LocalDate.parse(formatfecha.format(tarea.getFechafinestimada()));
        
        long diasTotalesProyecto = ChronoUnit.DAYS.between(fechainicial, fechafinal);
        long diasTransInicio = ChronoUnit.DAYS.between(LocalDate.now(), fechainicial);
        if (diasTotalesProyecto == 0)
            diasTotalesProyecto = 1;
        if(LocalDate.now().isAfter(fechafinal)) {
        	return "100";
        }
        if (LocalDate.now().isAfter(fechainicial)) {
            Double resporcentaje = Math.abs(((double) (diasTransInicio / (double) diasTotalesProyecto)) * 100);
            return String.valueOf(Math.round(resporcentaje));
        }
        
        return String.valueOf(0);
    }

}
