package com.claro.gestionrecursosapi.estimaciones.controller;

import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.estimaciones.domain.EstimacionService;
import com.claro.gestionrecursosapi.estimaciones.dto.DonutChartDTO;
import com.claro.gestionrecursosapi.estimaciones.dto.DonutChartDataDTO;
import com.claro.gestionrecursosapi.estimaciones.dto.EstimacionXProyectoDTO;
import com.claro.gestionrecursosapi.estimaciones.entity.CostoEstimacion;
import com.claro.gestionrecursosapi.estimaciones.entity.EstimacionAdmin;
import com.claro.gestionrecursosapi.estimaciones.entity.EstimacionEntity;
import com.claro.gestionrecursosapi.estimaciones.repository.IEstimacionRepository;
import com.claro.gestionrecursosapi.reportegenerico.dto.ReporteFiltroDto;
import com.claro.gestionrecursosapi.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v1/estimaciones")
public class EstimacionController {

    @Autowired
    IEstimacionRepository estimacionRepository;

    @Autowired
    EstimacionService estimacionService;

    @PostMapping
    public ResponseEntity<RespuestaBase> crearEstimacion(@RequestBody EstimacionEntity estimacion) {
        try {
            EstimacionEntity EstimacionNueva = estimacionRepository.save(estimacion);
            RespuestaCustomizada<EstimacionEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Operación exitosa");
            respuesta.setData(EstimacionNueva);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/estimaciones")
    public ResponseEntity<RespuestaBase> buscarTodoEstimaciones() {
        try {
            //casteo
            List<EstimacionEntity> listasEstimaciones = (List<EstimacionEntity>) estimacionRepository.findAll();
            RespuestaCustomizada<Iterable<EstimacionEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listasEstimaciones);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionUtils.getStackTraceString(e));
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postCrearEstimacionAdmin(@RequestBody List<EstimacionAdmin> estimacion) {
        try {
            List<EstimacionAdmin> estimacionSave = estimacionService.saveAll(estimacion);
            RespuestaCustomizada<List<EstimacionAdmin>> respuesta = new RespuestaCustomizada<>();
            if (estimacionSave.isEmpty()) {
                respuesta.setCodigoEstatus(HttpStatus.OK.value());
                respuesta.setMensaje("Estamacion creada con éxito");
            } else {
                respuesta.setCodigoEstatus(HttpStatus.BAD_REQUEST.value());
                respuesta.setMensaje("Error al guardar la información");
            }
            respuesta.setData(estimacionSave);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionUtils.getStackTraceString(e));
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/costosEstimaciones")
    public ResponseEntity<RespuestaBase> findAllByFechaFinIsNull() {
        try {
            List<CostoEstimacion> listaCosto = estimacionService.findAllByFechaFinIsNull();
            RespuestaCustomizada<List<CostoEstimacion>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listaCosto);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionUtils.getStackTraceString(e));
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estimacionesXProyecto")
    public ResponseEntity<RespuestaBase> estimacionXProyecto() {
        try {
            Iterable<EstimacionXProyectoDTO> listProyectos = estimacionService.estimacionXProyecto();
            RespuestaCustomizada<Iterable<EstimacionXProyectoDTO>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listProyectos);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionUtils.getStackTraceString(e));
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estimacioneXCodproyecto/{codProyecto}")
    public ResponseEntity<RespuestaBase> estimacionXCodProyecto(@PathVariable int codProyecto) {
        try {
            Iterable<EstimacionAdmin> estimaciones = estimacionService.estimacionXCodProyecto(codProyecto);
            RespuestaCustomizada<Iterable<EstimacionAdmin>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estimaciones);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionUtils.getStackTraceString(e));
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaBase> eliminar(@PathVariable Integer id) {
        try {
            estimacionService.deleteById(id);
            RespuestaBase respuesta = new RespuestaBase();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Se elimino registro");
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBase> finById(@PathVariable Integer id) {
        try {
            EstimacionAdmin estimacionAdmin = estimacionService.finById(id);
            RespuestaCustomizada<EstimacionAdmin> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estimacionAdmin);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/reporteChart/{codProyecto}/{jerarquia}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaBase> reporteChart(@PathVariable int codProyecto, @PathVariable String jerarquia) {
        try {
            Iterable<DonutChartDTO> listDonutChart = estimacionService.reporteChart(codProyecto, jerarquia);
            RespuestaCustomizada<Iterable<DonutChartDTO>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listDonutChart);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/Monitoreo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaBase> monitoreo(@RequestBody ReporteFiltroDto filtros) {
        try {

            List<DonutChartDTO> listDonutChart = estimacionService.monitoreo(filtros);
            RespuestaCustomizada<List<DonutChartDTO>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(listDonutChart);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/descargar/{codProyecto}")
    public HttpEntity<ByteArrayResource> download(@PathVariable int codProyecto) {
        try {

            byte[] resultado = estimacionService.download(codProyecto);
            DonutChartDataDTO costo = estimacionService.costoEstimacionXProyecto(codProyecto);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = Calendar.getInstance().getTime();
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + costo.getLabel() + " " + dateFormat.format(fecha) + ".xlsx");
            return new HttpEntity<>(new ByteArrayResource(resultado), header);
        } catch (Exception e) {
            System.out.println("println: " + ExceptionUtils.getStackTraceString(e));
            return null;
        }
    }
}