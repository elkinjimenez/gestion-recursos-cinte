package com.claro.gestionrecursosapi.estructura.controller;


import java.util.List;
import java.util.Optional;
import com.claro.gestionrecursosapi.estructura.domain.EstructuraOrganizacionService;
import com.claro.gestionrecursosapi.gerente.entity.GerenteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.claro.gestionrecursosapi.base.model.RespuestaBase;
import com.claro.gestionrecursosapi.base.model.RespuestaCustomizada;
import com.claro.gestionrecursosapi.estructura.entity.EstructuraorganizacionalEntity;

@RestController
@RequestMapping("/api/v1/estructuraorganizacional")
public class EstructuraOrganizacionalController {

    @Autowired
    private EstructuraOrganizacionService service;

    @GetMapping
    public ResponseEntity<RespuestaBase> buscarTodo() {
        try {
            Iterable<EstructuraorganizacionalEntity> lista = service.findAll();
            RespuestaCustomizada<Iterable<EstructuraorganizacionalEntity>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(lista);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/findJerarquiaAndTipoEstructura/{padre}/{tipoEstructura}")
    public ResponseEntity<RespuestaBase> findJerarquiaAndTipoEstructura(@PathVariable Integer padre,
            @PathVariable Integer tipoEstructura) {
        try {
            Iterable<GerenteEntity> lista = service.findJerarquiaAndTipoEstructura(padre, tipoEstructura);
            RespuestaCustomizada<Iterable<GerenteEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(lista);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/filtrada")
    public ResponseEntity<RespuestaBase> buscarFiltrada() {
        try {
            Iterable<EstructuraorganizacionalEntity> lista = service.findAllFiltrada();
            RespuestaCustomizada<Iterable<EstructuraorganizacionalEntity>> respuesta = new RespuestaCustomizada<>();

            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(lista);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBase> buscarPorId(@PathVariable Integer id) {
        try {
            Optional<EstructuraorganizacionalEntity> esctructuraEntity = service.findById(id);
            RespuestaCustomizada<EstructuraorganizacionalEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(esctructuraEntity.get());
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RespuestaBase> crear(@RequestBody EstructuraorganizacionalEntity entity) {
        try {
            EstructuraorganizacionalEntity esctructuraEntity = service.save(entity);
            RespuestaCustomizada<EstructuraorganizacionalEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.CREATED.value());
            respuesta.setMensaje("Se creo correctamente");
            respuesta.setData(esctructuraEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.CREATED);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaBase> actualizar(@PathVariable Integer id,
            @RequestBody EstructuraorganizacionalEntity entity) {
        try {
            EstructuraorganizacionalEntity estructuraEntity = service.save(entity);
            RespuestaCustomizada<EstructuraorganizacionalEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Se actualizo correctamente");
            respuesta.setData(estructuraEntity);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaBase> eliminar(@PathVariable Integer id) {

        try {
            service.delete(service.findById(id).get());
            RespuestaBase respuesta = new RespuestaBase();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Se elimino registro");
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEstructuraXCodProyecto/{codProyecto}")
    public ResponseEntity<RespuestaBase> getEstructuraXCodProyecto(@PathVariable Integer codProyecto) {
        try {
            Iterable<EstructuraorganizacionalEntity> estructura = service.getEstructuraXCodProyecto(codProyecto);
            RespuestaCustomizada<Iterable<EstructuraorganizacionalEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estructura);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEstructuraxCodPadre/{codpadre}")
    public ResponseEntity<RespuestaBase> getEstructuraxCodPadre(@PathVariable Integer codpadre) {
        try {
            Iterable<EstructuraorganizacionalEntity> estructura = service.getEstructuraxCodPadre(codpadre);
            RespuestaCustomizada<Iterable<EstructuraorganizacionalEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estructura);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEstructuraXGerencia/{codGerencia}")
    public ResponseEntity<RespuestaBase> getEstructuraXGerencia(@PathVariable Integer codGerencia) {
        try {
            List<EstructuraorganizacionalEntity> estructura = service.getEstructuraXGerencia(codGerencia);
            if(estructura.isEmpty()){
                estructura.add(new EstructuraorganizacionalEntity());
            }
            RespuestaCustomizada<Iterable<EstructuraorganizacionalEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estructura);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getGerenteporCodigoEstructura/{codGerencia}")
    public ResponseEntity<RespuestaBase> getGerenteporCodigoEstructura(@PathVariable Integer codGerencia) {
        try {
            EstructuraorganizacionalEntity estructura = service.getGerenteporCodigoEstructura(codGerencia);
            RespuestaCustomizada<EstructuraorganizacionalEntity> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estructura);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEstructuraXTipo/{codTipo}")
    public ResponseEntity<RespuestaBase> getEstructuraXTipo(@PathVariable Integer codTipo) {
        try {
            List<EstructuraorganizacionalEntity> estructuraxtipo = service.getEstructuraXTipo(codTipo);
            RespuestaCustomizada<List<EstructuraorganizacionalEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estructuraxtipo);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEstructuraFiltroDonuts/{codProyecto}")
    public ResponseEntity<RespuestaBase> getEstructuraFiltroDonuts(@PathVariable Integer codProyecto) {
        try {
            List<EstructuraorganizacionalEntity> estructuraxtipo = service.getEstructuraFiltroDonuts(codProyecto);
            RespuestaCustomizada<List<EstructuraorganizacionalEntity>> respuesta = new RespuestaCustomizada<>();
            respuesta.setCodigoEstatus(HttpStatus.OK.value());
            respuesta.setMensaje("Consulta exitosa");
            respuesta.setData(estructuraxtipo);
            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
        }
    }


//
//    @GetMapping("/getEstructurasXJerarquia/{jerarquia}")
//    public ResponseEntity<RespuestaBase> getEstructurasXJerarquia(@PathVariable String jerarquia) {
//        try {
//            List<EstructuraorganizacionalEntity> estructuraxtipo = service.getEstructurasXJerarquia(jerarquia);
//            RespuestaCustomizada<List<EstructuraorganizacionalEntity>> respuesta = new RespuestaCustomizada<>();
//            respuesta.setCodigoEstatus(HttpStatus.OK.value());
//            respuesta.setMensaje("Consulta exitosa");
//            respuesta.setData(estructuraxtipo);
//            return new ResponseEntity<RespuestaBase>(respuesta, HttpStatus.OK);
//        } catch (Exception e) {
//            RespuestaBase respuestaBase = new RespuestaBase(HttpStatus.NOT_FOUND.value(), e.getMessage());
//            return new ResponseEntity<RespuestaBase>(respuestaBase, HttpStatus.NOT_FOUND);
//        }
//    }

}
