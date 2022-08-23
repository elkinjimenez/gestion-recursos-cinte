package com.claro.gestionrecursosapi.compromisosfabrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.compromisosfabrica.dto.CompromisosFabricaDto;


public interface ICompromisosFabricaDtoRepository extends CrudRepository<CompromisosFabricaDto, Integer> {

	
    @Query(nativeQuery = true,
            value = "SELECT dpb.id ,dpb.codproyecto idCodProyecto ,dpb.codperfil idCodPerfil,dpb.codperfilnivel idCodPerfiLNivel,dpb.codperfiltipo  idCodPerfilTipo, \r\n" + 
            		"dpb.codproveedor idCodProveedor, dp.nombre codProyecto,p.nombre codPerfil ,pn.nombre codPerfiLNivel ,pt.nombre codPerfilTipo,\r\n" + 
            		"dpb.lineaproducto lineaProducto, dpr.nombre codProveedor, dpb.horas_hl horasHL ,dpb.fechainicio fechaInicio,dpb.fechafin fechaFin,\r\n" + 
            		"dpb.fechacreacion fechaCreacion,dpb.fechamodificacion fechaModificacion , dpb.horas_ll horasLL,dpb.brief brief,dpb.idrlp idRlp,\r\n" + 
            		"dpb.fechaqaini fechaQaInicio,dpb.fechaqafin fechaQaFin,dpb.fecha_despliegue fechaDespliegue,dpb.fecha_desp_real fechaDespliegueReal,\r\n" + 
            		"dpb.id_cambio idCambio,dpb.resultado resultado ,dpb.justificacion justificacion , dpb.descripcion descripcion,dpb.fecha_presupuesto fechaPresupuesto,\r\n" + 
            		"dpb.horas_real horasReal, dpb.lt lt,dpb.pm pm, dpb.estado estado , dpb.fase fase , dpb.fecha_solicitud fechaSolicitud, dpb.codestructura codEstructura,  \r\n" +
            		"dpb.stopper stopper,  \r\n" + 
            		"estructura.nombre nombreEstructura,  \r\n" + 
            		"dpb.evidencia evidencia  \r\n" + 
            		"FROM DF_PRY_BRIEF_IDRLP dpb\r\n" + 
            		"inner join df_proyecto dp on dpb.codproyecto=dp.id\r\n" + 
            		"inner join df_perfil p on dpb.codperfil=p.id\r\n" + 
            		"inner join df_perfilnivel pn on dpb.codperfilnivel=pn.id\r\n" + 
            		"inner join df_perfiltipo pt on dpb.codperfiltipo=pt.id\r\n" + 
            		"inner join df_proveedor dpr on dpb.codproveedor=dpr.id \r\n " +
            		"inner join df_estructuraorganizacional estructura on dpb.codestructura=estructura.id \r\n ")
    public List<CompromisosFabricaDto> findAllCompromisosFabrica();

    @Query(nativeQuery = true,
            value = "SELECT dpb.id ,dpb.codproyecto idCodProyecto ,dpb.codperfil idCodPerfil,dpb.codperfilnivel idCodPerfiLNivel,dpb.codperfiltipo  idCodPerfilTipo, \r\n" + 
            		"dpb.codproveedor idCodProveedor, dp.nombre codProyecto,p.nombre codPerfil ,pn.nombre codPerfiLNivel ,pt.nombre codPerfilTipo,\r\n" + 
            		"dpb.lineaproducto lineaProducto, dpr.nombre codProveedor, dpb.horas_hl horasHL ,dpb.fechainicio fechaInicio,dpb.fechafin fechaFin,\r\n" + 
            		"dpb.fechacreacion fechaCreacion,dpb.fechamodificacion fechaModificacion , dpb.horas_ll horasLL,dpb.brief brief,dpb.idrlp idRlp,\r\n" + 
            		"dpb.fechaqaini fechaQaInicio,dpb.fechaqafin fechaQaFin,dpb.fecha_despliegue fechaDespliegue,dpb.fecha_desp_real fechaDespliegueReal,\r\n" + 
            		"dpb.id_cambio idCambio,dpb.resultado resultado ,dpb.justificacion justificacion , dpb.descripcion descripcion,dpb.fecha_presupuesto fechaPresupuesto,\r\n" + 
            		"dpb.horas_real horasReal, dpb.lt lt,dpb.pm pm, dpb.estado estado , dpb.fase fase , dpb.fecha_solicitud fechaSolicitud, dpb.codestructura codEstructura,  \r\n" +
            		"dpb.stopper stopper,  \r\n" + 
            		"estructura.nombre nombreEstructura,  \r\n" + 
            		"dpb.evidencia evidencia  \r\n" + 
            		"FROM DF_PRY_BRIEF_IDRLP dpb\r\n" + 
            		"inner join df_proyecto dp on dpb.codproyecto=dp.id\r\n" + 
            		"inner join df_perfil p on dpb.codperfil=p.id\r\n" + 
            		"inner join df_perfilnivel pn on dpb.codperfilnivel=pn.id\r\n" + 
            		"inner join df_perfiltipo pt on dpb.codperfiltipo=pt.id\r\n" + 
            		"inner join df_proveedor dpr on dpb.codproveedor=dpr.id \r\n " +
            		"inner join df_estructuraorganizacional estructura on dpb.codestructura=estructura.id \r\n " +
            		"where dpb.id=?")
    public CompromisosFabricaDto findByIdCompromisosFabrica(Integer id);
}
