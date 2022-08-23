package com.claro.gestionrecursosapi.reporte.domain;

import java.io.ByteArrayOutputStream;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.claro.gestionrecursosapi.reporte.dto.ReporteFiltroDto;
import com.claro.gestionrecursosapi.reporte.entity.ReporteInfoCompromisos;
import com.claro.gestionrecursosapi.reporte.repository.IReporteInfoCompromisos;

@Service
public class ReporteInfoCompromisosService {
	@Autowired
	private IReporteInfoCompromisos repository;

	public Iterable<ReporteInfoCompromisos> generarReporte(String CLUSUARIO,
			ReporteFiltroDto dto) {
		return repository.infoReporte(CLUSUARIO,
				dto.getFechainicio(), 
				dto.getFechafin(), 
				dto.getCodproyecto(), 
				dto.getCodproveedor(),
				dto.getCodtareaestado(),
				dto.getTarea());
	}

	public byte[] download(String CLUSUARIO, ReporteFiltroDto dto) {
		if (dto == null)
			dto = new ReporteFiltroDto();

		Workbook workbook = new XSSFWorkbook();

		// Hoja de resumen

		Sheet sheetResumen = workbook
				.createSheet("Informe Compromisos");
		Row headerResumen = sheetResumen.createRow(0);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(font);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);

		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFont(font);

		Cell headerCell = headerResumen.createCell(0);
		headerCell.setCellValue("Cod Proyecto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(1);
		headerCell.setCellValue("Proyecto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(2);
		headerCell.setCellValue("Nombre AMX");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(3);
		headerCell.setCellValue("Id Proyecto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(4);
		headerCell.setCellValue("Cod Perfil");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(5);
		headerCell.setCellValue("Perfil");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(6);
		headerCell.setCellValue("Cod Perfil Nivel");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(7);
		headerCell.setCellValue("Perfil Nivel");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(8);
		headerCell.setCellValue("Cod Perfil Tipo");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(9);
		headerCell.setCellValue("Perfil Tipo");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(10);
		headerCell.setCellValue("Cod Proveedor");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(11);
		headerCell.setCellValue("Proveedor");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(12);
		headerCell.setCellValue("Cod Empleado");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(13);
		headerCell.setCellValue("Celula");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(14);
		headerCell.setCellValue("Id Brief");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(15);
		headerCell.setCellValue("Id Linea");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(16);
		headerCell.setCellValue("Linea Producto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(17);
		headerCell.setCellValue("Linea_Producto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(18);
		headerCell.setCellValue("Descripcion Linea");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(19);
		headerCell.setCellValue("Fase");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(20);
		headerCell.setCellValue("Id Cambio");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(21);
		headerCell.setCellValue("Estado");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(22);
		headerCell.setCellValue("Resultado del Cambio");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(23);
		headerCell.setCellValue("Justificacion");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(24);
		headerCell.setCellValue("PM");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(25);
		headerCell.setCellValue("LT");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(26);
		headerCell.setCellValue("Fecha Solicitud");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(27);
		headerCell.setCellValue("Fecha Inicio Proceso");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(28);
		headerCell.setCellValue("Fecha Entrega");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(29);
		headerCell.setCellValue("FEcha Despliegue Planeada");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(30);
		headerCell.setCellValue("Fecha Despliegue Real");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(31);
		headerCell.setCellValue("Horas HL");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(32);
		headerCell.setCellValue("Horas LL");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(33);
		headerCell.setCellValue("Horas Real");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(34);
		headerCell.setCellValue("Fecha Presupuesto");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(35);
		headerCell.setCellValue("Fecha QA Ini");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerResumen.createCell(36);
		headerCell.setCellValue("Fecha QA Fin");
		headerCell.setCellStyle(headerStyle);

		sheetResumen.setAutoFilter(new CellRangeAddress(0, 0, 0, 29));
		sheetResumen.createFreezePane(0, 1);

		Iterable<ReporteInfoCompromisos> infoResumen = repository.infoReporte(CLUSUARIO,
				dto.getFechainicio(), dto.getFechafin(), 
				dto.getCodproyecto(), dto.getCodproveedor(), 
				dto.getCodtareaestado(), dto.getTarea());
		
		int iFila = 1;
		for (ReporteInfoCompromisos item : infoResumen) {
			Row fila = sheetResumen.createRow(iFila++);

			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle cellStyleDate = workbook.createCellStyle();

			Cell column = fila.createCell(0);
			column.setCellValue(Objects.nonNull(item.getCodProyecto()) ? 
					item.getCodProyecto() : item.getCodProyecto());
			
			column = fila.createCell(1);
			column.setCellValue(Objects.nonNull(item.getProyecto()) ? StringUtils
					.stripAccents(item.getProyecto()).toUpperCase() : item
					.getProyecto());
			
			column = fila.createCell(2);
			column.setCellValue(Objects.nonNull(item.getNombre_amx()) ? StringUtils
					.stripAccents(item.getNombre_amx()).toUpperCase() : item
					.getNombre_amx());
			
			column = fila.createCell(3);
			column.setCellValue(Objects.nonNull(item.getIdProyecto()) ? StringUtils
					.stripAccents(item.getIdProyecto()).toUpperCase() : item
					.getIdProyecto());
			
			column = fila.createCell(4);
			column.setCellValue(Objects.nonNull(item.getCodPerfil()) ? 
					item.getCodPerfil() : item.getCodPerfil());
			
			column = fila.createCell(5);
			column.setCellValue(Objects.nonNull(item.getPerfil()) ? StringUtils
					.stripAccents(item.getPerfil()).toUpperCase() : item
					.getPerfil());
			
			column = fila.createCell(6);
			column.setCellValue(Objects.nonNull(item.getCodPerfilNivel()) ? 
					item.getCodPerfilNivel() : item.getCodPerfilNivel());
			
			column = fila.createCell(7);
			column.setCellValue(Objects.nonNull(item.getPerfilNivel()) ? StringUtils
					.stripAccents(item.getPerfilNivel()).toUpperCase() : item
					.getPerfilNivel());
			
			column = fila.createCell(8);
			column.setCellValue(Objects.nonNull(item.getCodPerfilTipo()) ? 
					item.getCodPerfilTipo() : item.getCodPerfilTipo());
			
			column = fila.createCell(9);
			column.setCellValue(Objects.nonNull(item.getPerfilTipo()) ? StringUtils
					.stripAccents(item.getPerfilTipo()).toUpperCase() : item
					.getPerfilTipo());
			
			column = fila.createCell(10);
			column.setCellValue(Objects.nonNull(item.getCodProveedor()) ? 
					item.getCodProveedor() : item.getCodProveedor());
			
			column = fila.createCell(11);
			column.setCellValue(Objects.nonNull(item.getProveedor()) ? StringUtils
					.stripAccents(item.getProveedor()).toUpperCase() : item
					.getProveedor());
			
			column = fila.createCell(12);
			column.setCellValue(Objects.nonNull(item.getCodEmpleado()) ? 
					item.getCodEmpleado() : item.getCodEmpleado());
			
			column = fila.createCell(13);
			column.setCellValue(Objects.nonNull(item.getCelula()) ? StringUtils
					.stripAccents(item.getCelula()).toUpperCase() : item
					.getCelula());
			
			column = fila.createCell(14);
			column.setCellValue(Objects.nonNull(item.getIdBrief()) ? StringUtils
					.stripAccents(item.getIdBrief()).toUpperCase() : item
					.getIdBrief());
			
			column = fila.createCell(15);
			column.setCellValue(Objects.nonNull(item.getIdLinea()) ? StringUtils
					.stripAccents(item.getIdLinea()).toUpperCase() : item
					.getIdLinea());
			
			column = fila.createCell(16);
			column.setCellValue(Objects.nonNull(item.getLineaProducto()) ? StringUtils
					.stripAccents(item.getLineaProducto()).toUpperCase() : item
					.getLineaProducto());
			
			column = fila.createCell(17);
			column.setCellValue(Objects.nonNull(item.getLinea_Producto()) ? StringUtils
					.stripAccents(item.getLinea_Producto()).toUpperCase() : item
					.getLinea_Producto());
			
			column = fila.createCell(18);
			column.setCellValue(Objects.nonNull(item.getDescripcionLinea()) ? StringUtils
					.stripAccents(item.getDescripcionLinea()).toUpperCase() : item
					.getDescripcionLinea());
			
			column = fila.createCell(19);
			column.setCellValue(Objects.nonNull(item.getFase()) ? 
					item.getFase() : item.getFase());
			
			column = fila.createCell(20);
			column.setCellValue(Objects.nonNull(item.getIdCambio()) ? StringUtils
					.stripAccents(item.getIdCambio()).toUpperCase() : item
					.getIdCambio());
			
			column = fila.createCell(21);
			column.setCellValue(Objects.nonNull(item.getEstado()) ? StringUtils
					.stripAccents(item.getEstado()).toUpperCase() : item
					.getEstado());
			
			column = fila.createCell(22);
			column.setCellValue(Objects.nonNull(item.getResultadoDelCambio()) ? StringUtils
					.stripAccents(item.getResultadoDelCambio()).toUpperCase() : item
					.getResultadoDelCambio());
			
			column = fila.createCell(23);
			column.setCellValue(Objects.nonNull(item.getJustificacion()) ? StringUtils
					.stripAccents(item.getJustificacion()).toUpperCase() : item
					.getJustificacion());
			
			column = fila.createCell(24);
			column.setCellValue(Objects.nonNull(item.getPm()) ? StringUtils
					.stripAccents(item.getPm()).toUpperCase() : item
					.getPm());
			
			column = fila.createCell(25);
			column.setCellValue(Objects.nonNull(item.getLt()) ? StringUtils
					.stripAccents(item.getLt()).toUpperCase() : item
					.getLt());
			
			cellStyleDate.setDataFormat(createHelper.createDataFormat()
					.getFormat("dd/mm/yyyy"));
			column = fila.createCell(26);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechaSolicitud());
			
			cellStyleDate.setDataFormat(createHelper.createDataFormat()
					.getFormat("dd/mm/yyyy"));
			column = fila.createCell(27);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechaInicioProceso());
			
			cellStyleDate.setDataFormat(createHelper.createDataFormat()
					.getFormat("dd/mm/yyyy"));
			column = fila.createCell(28);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechaEntrega());
			
			cellStyleDate.setDataFormat(createHelper.createDataFormat()
					.getFormat("dd/mm/yyyy"));
			column = fila.createCell(29);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechaDesplieguePlaneada());
			
			cellStyleDate.setDataFormat(createHelper.createDataFormat()
					.getFormat("dd/mm/yyyy"));
			column = fila.createCell(30);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechaDespliegueReal());
			
			column = fila.createCell(31);
			column.setCellValue(Objects.nonNull(item.getHoras_Hl()) ? StringUtils
					.stripAccents(item.getHoras_Hl()).toUpperCase() : item
					.getHoras_Hl());
			
			column = fila.createCell(32);
			column.setCellValue(Objects.nonNull(item.getHoras_Ll()) ? StringUtils
					.stripAccents(item.getHoras_Ll()).toUpperCase() : item
					.getHoras_Ll());
			
			column = fila.createCell(33);
			column.setCellValue(Objects.nonNull(item.getHoras_Real()) ? StringUtils
					.stripAccents(item.getHoras_Real()).toUpperCase() : item
					.getHoras_Real());
			
			column = fila.createCell(34);
			column.setCellValue(Objects.nonNull(item.getFecha_Presupuesto()) ? StringUtils
					.stripAccents(item.getFecha_Presupuesto()).toUpperCase() : item
					.getFecha_Presupuesto());
			
			column = fila.createCell(35);
			column.setCellValue(Objects.nonNull(item.getFechaQAIni()) ? StringUtils
					.stripAccents(item.getFechaQAIni()).toUpperCase() : item
					.getFechaQAIni());
			
			column = fila.createCell(36);
			column.setCellValue(Objects.nonNull(item.getFechaQAFin()) ? StringUtils
					.stripAccents(item.getFechaQAFin()).toUpperCase() : item
					.getFechaQAFin());
		}

		sheetResumen.autoSizeColumn(0);
		sheetResumen.autoSizeColumn(1);
		sheetResumen.autoSizeColumn(2);
		sheetResumen.autoSizeColumn(3);
		sheetResumen.autoSizeColumn(4);
		sheetResumen.autoSizeColumn(5);
		sheetResumen.autoSizeColumn(6);
		sheetResumen.autoSizeColumn(7);
		sheetResumen.autoSizeColumn(8);
		sheetResumen.autoSizeColumn(9);
		sheetResumen.autoSizeColumn(10);
		sheetResumen.autoSizeColumn(11);
		sheetResumen.autoSizeColumn(12);
		sheetResumen.autoSizeColumn(13);
		sheetResumen.autoSizeColumn(14);
		sheetResumen.autoSizeColumn(15);
		sheetResumen.autoSizeColumn(16);
		sheetResumen.autoSizeColumn(17);
		sheetResumen.autoSizeColumn(18);
		sheetResumen.autoSizeColumn(19);
		sheetResumen.autoSizeColumn(20);
		sheetResumen.autoSizeColumn(21);
		sheetResumen.autoSizeColumn(22);
		sheetResumen.autoSizeColumn(23);
		sheetResumen.autoSizeColumn(24);
		sheetResumen.autoSizeColumn(25);
		sheetResumen.autoSizeColumn(26);
		sheetResumen.autoSizeColumn(27);
		sheetResumen.autoSizeColumn(28);
		sheetResumen.autoSizeColumn(29);
		sheetResumen.autoSizeColumn(30);
		sheetResumen.autoSizeColumn(31);
		sheetResumen.autoSizeColumn(32);
		sheetResumen.autoSizeColumn(33);
		sheetResumen.autoSizeColumn(34);
		sheetResumen.autoSizeColumn(35);
		sheetResumen.autoSizeColumn(36);
		
		ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
		try {
			workbook.write(respuesta);
			workbook.close();
		} catch (Exception e) {

		}
		return respuesta.toByteArray();
	}
}
