package com.claro.gestionrecursosapi.compromisosfabrica.domain;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.claro.gestionrecursosapi.compromisosfabrica.dto.CompromisosFabricaDto;
import com.claro.gestionrecursosapi.compromisosfabrica.dto.FiltrosDto;
import com.claro.gestionrecursosapi.compromisosfabrica.entity.CompromisosFabricaEntity;
import com.claro.gestionrecursosapi.compromisosfabrica.repository.ICompromisosFabricaDtoRepository;
import com.claro.gestionrecursosapi.compromisosfabrica.repository.ICompromisosFabricaRepository;
import com.claro.gestionrecursosapi.reportegenerico.entity.ColumnInformationDb;
import com.claro.gestionrecursosapi.reportegenerico.repository.ColumnInformationDbRepository;


@Service
@Transactional
public class CompromisosFabricaService {

	@Autowired
	private ICompromisosFabricaRepository repository;

	@Autowired
	private ICompromisosFabricaDtoRepository iCompromisosFabricaRepository;
	
	@Autowired
	private ColumnInformationDbRepository columnInformationDbRepository;

	@Autowired
	EntityManager  entityManager;

	public CompromisosFabricaDto saveCompromisosFabrica(CompromisosFabricaDto compromisosFabricaDto) {
		Optional<CompromisosFabricaEntity> lastRecord = repository.findLastRecord();
		CompromisosFabricaEntity compromisosFabricaEntity = new CompromisosFabricaEntity();

		// asigna el id del registro tomando como referencia el último insertado en la tabla
		if (lastRecord.isPresent()) {
			compromisosFabricaEntity.setId(lastRecord.get().getId() + 1);
		} else {
			compromisosFabricaEntity.setId(1);
		}

		compromisosFabricaEntity.setCodProyecto(compromisosFabricaDto.getIdCodProyecto());
		compromisosFabricaEntity.setCodperfil(compromisosFabricaDto.getIdCodPerfil());
		compromisosFabricaEntity.setCodperfilnivel(compromisosFabricaDto.getIdCodPerfiLNivel());
		compromisosFabricaEntity.setCodperfiltipo(compromisosFabricaDto.getIdCodPerfilTipo());
		compromisosFabricaEntity.setLineaProducto(compromisosFabricaDto.getLineaProducto());
		compromisosFabricaEntity.setCodproveedor(compromisosFabricaDto.getIdCodProveedor());
		compromisosFabricaEntity.setHorasHL(compromisosFabricaDto.getHorasHL());
		compromisosFabricaEntity.setFechaInicio(compromisosFabricaDto.getFechaInicio());
		compromisosFabricaEntity.setFechaFin(compromisosFabricaDto.getFechaFin());
		compromisosFabricaEntity.setFechacreacion(compromisosFabricaDto.getFechaCreacion());
		compromisosFabricaEntity.setFechamodificacion(compromisosFabricaDto.getFechaModificacion());
		compromisosFabricaEntity.setHorarLL(compromisosFabricaDto.getHorasLL());
		compromisosFabricaEntity.setBrief(compromisosFabricaDto.getBrief());
		compromisosFabricaEntity.setIdRlp(compromisosFabricaDto.getIdRlp());
		compromisosFabricaEntity.setFechaQaInicio(compromisosFabricaDto.getFechaQaInicio());
		compromisosFabricaEntity.setFechaQaFin(compromisosFabricaDto.getFechaQaFin());
		compromisosFabricaEntity.setFechaDespliegue(compromisosFabricaDto.getFechaDespliegue());
		compromisosFabricaEntity.setFechaDespliegueReal(compromisosFabricaDto.getFechaDespliegueReal());
		compromisosFabricaEntity.setIdCambio(compromisosFabricaDto.getIdCambio());
		compromisosFabricaEntity.setResultado(compromisosFabricaDto.getResultado());
		compromisosFabricaEntity.setJustificacion(compromisosFabricaDto.getJustificacion());
		compromisosFabricaEntity.setDescripcion(compromisosFabricaDto.getDescripcion());
		compromisosFabricaEntity.setFechaPresupuesto(compromisosFabricaDto.getFechaPresupuesto());
		compromisosFabricaEntity.setHorasReal(compromisosFabricaDto.getHorasReal());
		compromisosFabricaEntity.setLt(compromisosFabricaDto.getLt());
		compromisosFabricaEntity.setPm(compromisosFabricaDto.getPm());
		compromisosFabricaEntity.setEstado(compromisosFabricaDto.getEstado());
		compromisosFabricaEntity.setFase(compromisosFabricaDto.getFase());
		compromisosFabricaEntity.setCodEstructura(compromisosFabricaDto.getCodEstructura());
		compromisosFabricaEntity.setFechaSolicitud(compromisosFabricaDto.getFechaSolicitud());
		compromisosFabricaEntity.setStopper(compromisosFabricaDto.getStopper());
		compromisosFabricaEntity.setEvidencia(compromisosFabricaDto.getEvidencia());

		repository.save(compromisosFabricaEntity);
		compromisosFabricaDto.setId(compromisosFabricaEntity.getId());
		return compromisosFabricaDto;
	}

	public List<CompromisosFabricaEntity> findCompromisosByProyecto(Integer idProyecto){
		return repository.findCompromisosByProyecto(idProyecto);
	}

	public CompromisosFabricaDto updateCompromisosFabrica(CompromisosFabricaDto compromisosFabricaDto, Integer id) {
		Optional<CompromisosFabricaEntity> oCompromisosFabricaBd =  repository.findById(id);

		if (oCompromisosFabricaBd.isPresent()) {
			CompromisosFabricaEntity compromisosFabricaEntity = oCompromisosFabricaBd.get();
			compromisosFabricaEntity.setCodProyecto(compromisosFabricaDto.getIdCodProyecto());
			compromisosFabricaEntity.setCodperfil(compromisosFabricaDto.getIdCodPerfil());
			compromisosFabricaEntity.setCodperfilnivel(compromisosFabricaDto.getIdCodPerfiLNivel());
			compromisosFabricaEntity.setCodperfiltipo(compromisosFabricaDto.getIdCodPerfilTipo());
			compromisosFabricaEntity.setLineaProducto(compromisosFabricaDto.getLineaProducto());
			compromisosFabricaEntity.setCodproveedor(compromisosFabricaDto.getIdCodProveedor());
			compromisosFabricaEntity.setHorasHL(compromisosFabricaDto.getHorasHL());
			compromisosFabricaEntity.setFechaInicio(compromisosFabricaDto.getFechaInicio());
			compromisosFabricaEntity.setFechaFin(compromisosFabricaDto.getFechaFin());
			compromisosFabricaEntity.setFechacreacion(compromisosFabricaDto.getFechaCreacion());
			compromisosFabricaEntity.setFechamodificacion(compromisosFabricaDto.getFechaModificacion());
			compromisosFabricaEntity.setHorarLL(compromisosFabricaDto.getHorasLL());
			compromisosFabricaEntity.setBrief(compromisosFabricaDto.getBrief());
			compromisosFabricaEntity.setIdRlp(compromisosFabricaDto.getIdRlp());
			compromisosFabricaEntity.setFechaQaInicio(compromisosFabricaDto.getFechaQaInicio());
			compromisosFabricaEntity.setFechaQaFin(compromisosFabricaDto.getFechaQaFin());
			compromisosFabricaEntity.setFechaDespliegue(compromisosFabricaDto.getFechaDespliegue());
			compromisosFabricaEntity.setFechaDespliegueReal(compromisosFabricaDto.getFechaDespliegueReal());
			compromisosFabricaEntity.setIdCambio(compromisosFabricaDto.getIdCambio());
			compromisosFabricaEntity.setResultado(compromisosFabricaDto.getResultado());
			compromisosFabricaEntity.setJustificacion(compromisosFabricaDto.getJustificacion());
			compromisosFabricaEntity.setDescripcion(compromisosFabricaDto.getDescripcion());
			compromisosFabricaEntity.setFechaPresupuesto(compromisosFabricaDto.getFechaPresupuesto());
			compromisosFabricaEntity.setHorasReal(compromisosFabricaDto.getHorasReal());
			compromisosFabricaEntity.setLt(compromisosFabricaDto.getLt());
			compromisosFabricaEntity.setPm(compromisosFabricaDto.getPm());
			compromisosFabricaEntity.setEstado(compromisosFabricaDto.getEstado());
			compromisosFabricaEntity.setFase(compromisosFabricaDto.getFase());
			compromisosFabricaEntity.setCodEstructura(compromisosFabricaDto.getCodEstructura());
			compromisosFabricaEntity.setFechaSolicitud(compromisosFabricaDto.getFechaSolicitud());
			compromisosFabricaEntity.setStopper(compromisosFabricaDto.getStopper());
			compromisosFabricaEntity.setEvidencia(compromisosFabricaDto.getEvidencia());
			
			return compromisosFabricaDto;
		} else {
			return null;
		}
	}

	public CompromisosFabricaDto findByIdCompromisosFabrica(Integer id) {
		return iCompromisosFabricaRepository.findByIdCompromisosFabrica(id);
	}

	public List<CompromisosFabricaDto> findAllCompromisosFabrica() {
		return iCompromisosFabricaRepository.findAllCompromisosFabrica();
	}

	public void delete(Integer id) {
		repository.delete(repository.findById(id).get());
	}
	
	public Iterable<ColumnInformationDb> getColumnasFiltro() {
		return columnInformationDbRepository.getColumnasTablaParaFiltros("DF_PRY_BRIEF_IDRLP");
	}

	public String gerenteProyecto(Integer idCompromiso){
		return repository.gerenteProyectoXCompromisoFabrica(idCompromiso);
	}

	public List<CompromisosFabricaDto> findAllByFiltro(FiltrosDto filtros) {
		// TODO: poner la base query en un properties
		String baseQuery = "SELECT dpb.id ,dpb.codproyecto idCodProyecto ,dpb.codperfil idCodPerfil,dpb.codperfilnivel idCodPerfiLNivel,dpb.codperfiltipo  idCodPerfilTipo, \r\n" + 
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
        		"inner join df_estructuraorganizacional estructura on dpb.codestructura=estructura.id \r\n ";

		String whereQuery = "";

		whereQuery = agregarCondicionalFiltroGenerico(whereQuery, filtros.getColumnaUno(), filtros.getValorUno());
		whereQuery = agregarCondicionalFiltroGenerico(whereQuery, filtros.getColumnaDos(), filtros.getValorDos());
		whereQuery = agregarCondicionalFiltroGenerico(whereQuery, filtros.getColumnaTres(), filtros.getValorTres());

		whereQuery = agregarCondicionalValorNumerico(whereQuery, "CODPERFILTIPO", filtros.getCodperfiltipo());
		whereQuery = agregarCondicionalValorNumerico(whereQuery, "CODPROYECTO", filtros.getCodproyecto());
		whereQuery = agregarCondicionalValorNumerico(whereQuery, "CODPROVEEDOR", filtros.getCodproveedor());
		whereQuery = agregarCondicionalValorNumerico(whereQuery, "CODPERFIL", filtros.getCodperfil());
		whereQuery = agregarCondicionalValorNumerico(whereQuery, "CODESTRUCTURA", filtros.getCodestructuraorganizacional());
		whereQuery = agregarCondicionalValorNumerico(whereQuery, "CODPERFILNIVEL", filtros.getCodperfilnivel());

		if (!whereQuery.isEmpty()) {
			baseQuery += "where " + whereQuery;
		}

		Query query = entityManager.createNativeQuery(baseQuery, CompromisosFabricaDto.class);

		return query.getResultList();
	}
	
	private String agregarCondicionalValorNumerico (String queryWhere, String nombreColumna, Integer valor) {
		if(Objects.nonNull(valor) && valor > 0) {
			if (!queryWhere.isEmpty()) {
				queryWhere += " AND ";
			}

			queryWhere += "dpb." + nombreColumna + " = " + valor;
		}

		return queryWhere;
	}
	
	private String agregarCondicionalFiltroGenerico (String queryWhere, String nombreColumna, String valor) {
		if(nombreColumna != null && !nombreColumna.isEmpty()) {
			if (!queryWhere.isEmpty()) {
				queryWhere += " AND ";
			}

			if(valor != null && !valor.isEmpty()) {
				valor = valor.toLowerCase();
			}

			queryWhere += "LOWER(dpb." + nombreColumna + ") like '%" + valor + "%'";
		}
		
		return queryWhere;
	}
}

