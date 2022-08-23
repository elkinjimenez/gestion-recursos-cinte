package com.claro.gestionrecursosapi.reporte.domain;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
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
import com.claro.gestionrecursosapi.reporte.entity.ReporteOpex;
import com.claro.gestionrecursosapi.reporte.entity.ReporteRhPagos;
import com.claro.gestionrecursosapi.reporte.enums.ModalidadEmpleadoEnum;
import com.claro.gestionrecursosapi.reporte.repository.IReporteOpexRepository;
import com.claro.gestionrecursosapi.reporte.repository.IReporteRhPagosRepository;
import com.claro.gestionrecursosapi.utils.ReplaceTildesUtil;

@Service
public class ReporteRhPagosService {

	@Autowired
	private IReporteRhPagosRepository repository;

	public Iterable<ReporteRhPagos> generarReporte(String CLUSUARIO,
			ReporteFiltroDto dto) {
		return repository.infoReporte(CLUSUARIO, dto.getFechainicio(),dto.getFechafin(), 
				dto.getCodproyecto(), 
				dto.getCodproveedor());
	}

	public byte[] download(String CLUSUARIO, ReporteFiltroDto dto) {
		if (dto == null)
			dto = new ReporteFiltroDto();

		Workbook workbook = new XSSFWorkbook();

		// Hoja de resumen

		Sheet sheetResumen = workbook
				.createSheet("Informe Listado Solicitudes");
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
		headerCell.setCellValue("Recurso");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(1);
		headerCell.setCellValue("Id Recurso");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(2);
		headerCell.setCellValue("Proveedor");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(3);
		headerCell.setCellValue("Tipo Presupuesto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(4);
		headerCell.setCellValue("Id Proyecto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(5);
		headerCell.setCellValue("Pep");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(6);
		headerCell.setCellValue("Nombre Amx");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(7);
		headerCell.setCellValue("Con Presupuesto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(8);
		headerCell.setCellValue("Tipo Proyecto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(9);
		headerCell.setCellValue("Proyecto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(10);
		headerCell.setCellValue("Fecha");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(11);
		headerCell.setCellValue("Semana");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(12);
		headerCell.setCellValue("Porcentaje Proyecto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(13);
		headerCell.setCellValue("Costo recursos");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(14);
		headerCell.setCellValue("Costo Por Pagar");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(15);
		headerCell.setCellValue("Gerente");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(16);
		headerCell.setCellValue("Aprobador");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(17);
		headerCell.setCellValue("Celula");
		headerCell.setCellStyle(headerStyle);

		sheetResumen.setAutoFilter(new CellRangeAddress(0, 0, 0, 17));
		sheetResumen.createFreezePane(0, 1);

		Iterable<ReporteRhPagos> infoResumen = repository.infoReporte(
				CLUSUARIO, dto.getFechainicio(), dto.getFechafin(),
				dto.getCodproyecto(),
				//dto.getCodtareatipo(),
				dto.getCodproveedor()
//				, 
//				dto.getCodempleadoasignado(),
//				dto.getCodempleadocreo(), dto.getCodtareaestado(),
//				dto.getEslogro(), dto.getTarea()
				);
		int iFila = 1;
		for (ReporteRhPagos item : infoResumen) {
			Row fila = sheetResumen.createRow(iFila++);

			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle cellStyleDate = workbook.createCellStyle();

			Cell column = fila.createCell(0);
			column.setCellValue(Objects.nonNull(item.getRecurso()) ? StringUtils
					.stripAccents(item.getRecurso()).toUpperCase() : item
					.getRecurso());

			column = fila.createCell(1);
			column.setCellValue(Objects.nonNull(item.getIdRecurso()) ? item
					.getIdRecurso().doubleValue() : item.getIdRecurso());

			column = fila.createCell(2);
			column.setCellValue(Objects.nonNull(item.getProveedor()) ? StringUtils
					.stripAccents(item.getProveedor()).toUpperCase() : item
					.getProveedor());

			column = fila.createCell(3);
			column.setCellValue(Objects.nonNull(item.getTipoPresupuesto()) ? StringUtils
					.stripAccents(item.getTipoPresupuesto()).toUpperCase()
					: item.getTipoPresupuesto());

			column = fila.createCell(4);
			column.setCellValue(Objects.nonNull(item.getIdProyecto()) ? StringUtils
					.stripAccents(item.getIdProyecto()).toUpperCase() : item
					.getIdProyecto());

			column = fila.createCell(5);
			column.setCellValue(Objects.nonNull(item.getPep()) ? StringUtils
					.stripAccents(item.getPep()).toUpperCase() : item.getPep());

			column = fila.createCell(6);
			column.setCellValue(Objects.nonNull(item.getNombreAmx()) ? StringUtils
					.stripAccents(item.getNombreAmx()).toUpperCase() : item
					.getNombreAmx());

			column = fila.createCell(7);
			column.setCellValue(Objects.nonNull(item.getConPresupuesto()) ? StringUtils
					.stripAccents(item.getConPresupuesto()).toUpperCase()
					: item.getConPresupuesto());

			column = fila.createCell(8);
			column.setCellValue(Objects.nonNull(item.getTipoProyecto()) ? StringUtils
					.stripAccents(item.getTipoProyecto()).toUpperCase() : item
					.getTipoProyecto());

			column = fila.createCell(9);
			column.setCellValue(Objects.nonNull(item.getProyecto()) ? StringUtils
					.stripAccents(item.getProyecto()).toUpperCase() : item
					.getProyecto());

			cellStyleDate.setDataFormat(createHelper.createDataFormat()
					.getFormat("dd/mm/yyyy"));
			column = fila.createCell(10);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFecha());

			column = fila.createCell(11);
			column.setCellValue(Objects.nonNull(item.getSemana()) ? item
					.getSemana().doubleValue() : BigDecimal.ZERO.doubleValue());

			column = fila.createCell(12);
			column.setCellValue(Objects.nonNull(item.getPorcentajeProyecto()) ? item
					.getPorcentajeProyecto().doubleValue() : BigDecimal.ZERO
					.doubleValue());

			column = fila.createCell(13);
			column.setCellValue(Objects.nonNull(item.getCostoRecursos()) ? item
					.getCostoRecursos().doubleValue() : BigDecimal.ZERO
					.doubleValue());

			column = fila.createCell(14);
			column.setCellValue(Objects.nonNull(item.getCostoxPagar()) ? item
					.getCostoxPagar().doubleValue() : BigDecimal.ZERO
					.doubleValue());

			column = fila.createCell(15);
			column.setCellValue(Objects.nonNull(item.getGerente()) ? StringUtils
					.stripAccents(item.getGerente()).toUpperCase() : item
					.getGerente());

			column = fila.createCell(16);
			column.setCellValue(Objects.nonNull(item.getAprobador()) ? StringUtils
					.stripAccents(item.getAprobador()).toUpperCase() : item
					.getAprobador());

			column = fila.createCell(17);
			column.setCellValue(Objects.nonNull(item.getCelula()) ? StringUtils
					.stripAccents(item.getCelula()).toUpperCase() : item
					.getCelula());

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

		ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
		try {
			workbook.write(respuesta);
			workbook.close();
		} catch (Exception e) {

		}
		return respuesta.toByteArray();
	}

}
