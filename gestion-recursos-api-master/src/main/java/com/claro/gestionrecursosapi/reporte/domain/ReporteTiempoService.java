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

import com.claro.gestionrecursosapi.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEstructuraOrganizacionalVU;
import com.claro.gestionrecursosapi.reporte.dto.ReporteFiltroDto;
import com.claro.gestionrecursosapi.reporte.entity.ReporteTiempo;
import com.claro.gestionrecursosapi.reporte.repository.IReporteTiempoRepository;
import com.claro.gestionrecursosapi.utils.ReplaceTildesUtil;

@Service
public class ReporteTiempoService {

	@Autowired
	private IReporteTiempoRepository repository;

	@Autowired
	private EmpleadoService empleadoService; 
	
	public Iterable<ReporteTiempo> findResumen(String CLUSUARIO, ReporteFiltroDto dto) {
		EmpleadoEstructuraOrganizacionalVU lastEmpleadoParent = empleadoService.getLastEmpleadoParent(CLUSUARIO);
		return repository.findResumen(dto.getFechainicio(), dto.getFechafin(), dto.getCodproyecto(), dto.getCodtareatipo(), dto.getCodproveedor(), dto.getCodempleadoasignado(), dto.getCodempleadocreo(), dto.getCodtareaestado(), dto.getEslogro(), dto.getTarea(), lastEmpleadoParent.getEstructuraJerarquia());
	}
	
	public Iterable<ReporteTiempo> findDetalle(String CLUSUARIO, ReporteFiltroDto dto) {
		EmpleadoEstructuraOrganizacionalVU lastEmpleadoParent = empleadoService.getLastEmpleadoParent(CLUSUARIO);
		return repository.findDetalle(dto.getFechainicio(), dto.getFechafin(), dto.getCodproyecto(), dto.getCodtareatipo(), dto.getCodproveedor(), dto.getCodempleadoasignado(), dto.getCodempleadocreo(), dto.getCodtareaestado(), dto.getEslogro(), dto.getTarea(), lastEmpleadoParent.getEstructuraJerarquia());
	}
	
	public byte[] download(String CLUSUARIO, ReporteFiltroDto dto) {
		if (dto == null)
			dto = new ReporteFiltroDto();

		Iterable<ReporteTiempo> infoDetalle = this.findDetalle(CLUSUARIO, dto);
		Iterable<ReporteTiempo> infoResumen = this.findResumen(CLUSUARIO, dto);

		Workbook workbook = new XSSFWorkbook();
		
		// Hoja de resumen
		
		Sheet sheetResumen = workbook.createSheet("Resumen");
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
		headerCell.setCellValue("Proyecto");
		headerCell.setCellStyle(headerStyle);
		 
		headerCell = headerResumen.createCell(1);
		headerCell.setCellValue("Tarea");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(2);
		headerCell.setCellValue("Proveedor");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(3);
		headerCell.setCellValue("Recurso");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(4);
		headerCell.setCellValue("Horas");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(5);
		headerCell.setCellValue("Fecha inicio");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(6);
		headerCell.setCellValue("Fecha fin");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(7);
		headerCell.setCellValue("Horas reportadas");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(8);
		headerCell.setCellValue("Horas ausencias");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(9);
		headerCell.setCellValue("Horas laborales");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(10);
		headerCell.setCellValue("Horas pendientes por reportar");
		headerCell.setCellStyle(headerStyle);
		
		sheetResumen.setAutoFilter(new CellRangeAddress(0, 0, 0, 11));
		sheetResumen.createFreezePane(0, 1);

		int iFila = 1;
		for (ReporteTiempo item : infoResumen) {
			Row fila = sheetResumen.createRow(iFila++);

			Cell column = fila.createCell(0);
			column.setCellValue(Objects.nonNull(item.getProyecto()) ? StringUtils.stripAccents(item.getProyecto()).toUpperCase(): item.getProyecto());
			//column.setCellValue(item.getProyecto());
			
			column = fila.createCell(1);
			column.setCellValue(Objects.nonNull(item.getTarea()) ? StringUtils.stripAccents(item.getTarea()).toUpperCase(): item.getTarea());
			
			column = fila.createCell(2);
			column.setCellValue(Objects.nonNull(item.getProveedor()) ? StringUtils.stripAccents(item.getProveedor()).toUpperCase() : item.getProveedor());
			
			column = fila.createCell(3);
			column.setCellValue(Objects.nonNull(item.getRecurso()) ? ReplaceTildesUtil.replace(item.getRecurso()).toUpperCase() : item.getRecurso());
			
			column = fila.createCell(4);
			column.setCellValue((item.getHoras()!=null && !item.getHoras().toString().isEmpty())?item.getHoras().doubleValue():0);
			
			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle cellStyleDate = workbook.createCellStyle();
			cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
			column = fila.createCell(5);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechainicio());
			
			column = fila.createCell(6);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechafin());
			
			column = fila.createCell(7);
			column.setCellValue((item.getHorasreportadas()!=null && !item.getHorasreportadas().toString().isEmpty())? item.getHorasreportadas().doubleValue():0);
			
			column = fila.createCell(8);
			column.setCellValue(item.getHorasausencias() == null ? 0 : item.getHorasausencias().intValue());
			
			column = fila.createCell(9);
			column.setCellValue(item.getHoraslaborales() == null ? 0 : item.getHoraslaborales().intValue());
			
			column = fila.createCell(10);
			column.setCellValue(item.getHoraspendientes() == null ? 0 : item.getHoraspendientes().intValue());
			
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
		
		
		// Hora de detalle *************************************************
		Sheet sheetDetalle = workbook.createSheet("Detalle");
		Row headerDetalle = sheetDetalle.createRow(0);				
		 
		headerCell = headerDetalle.createCell(0);
		headerCell.setCellValue("Proyecto");
		headerCell.setCellStyle(headerStyle);
		 
		headerCell = headerDetalle.createCell(1);
		headerCell.setCellValue("Tarea");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerDetalle.createCell(2);
		headerCell.setCellValue("Proveedor");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerDetalle.createCell(3);
		headerCell.setCellValue("Recurso");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerDetalle.createCell(4);
		headerCell.setCellValue("Horas");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = headerDetalle.createCell(5);
		headerCell.setCellValue("Actividades");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerDetalle.createCell(6);
		headerCell.setCellValue("Fecha inicio");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerDetalle.createCell(7);
		headerCell.setCellValue("Fecha fin");
		headerCell.setCellStyle(headerStyle);
		
		sheetDetalle.setAutoFilter(new CellRangeAddress(0, 0, 0, 8));
		sheetDetalle.createFreezePane(0, 1);

		iFila = 1;
		for (ReporteTiempo item : infoDetalle) {
			Row fila = sheetDetalle.createRow(iFila++);

			Cell column = fila.createCell(0);
			column.setCellValue(Objects.nonNull(item.getProyecto()) ? StringUtils.stripAccents(item.getProyecto()).toUpperCase() : item.getProyecto());
			
			column = fila.createCell(1);
			column.setCellValue(Objects.nonNull(item.getTarea()) ? StringUtils.stripAccents(item.getTarea()).toUpperCase() : item.getTarea());
			
			column = fila.createCell(2);
			column.setCellValue(Objects.nonNull(item.getProveedor()) ? StringUtils.stripAccents(item.getProveedor()).toUpperCase() : item.getProveedor());
			
			column = fila.createCell(3);
			column.setCellValue(Objects.nonNull(item.getRecurso()) ? StringUtils.stripAccents(item.getRecurso()).toUpperCase() : item.getRecurso());
			
			column = fila.createCell(4);
			column.setCellValue(item.getHoras().toString());
			
			column = fila.createCell(5);
			column.setCellValue(item.getDescripcion());
			
			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle cellStyleDate = workbook.createCellStyle();
			cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
			column = fila.createCell(6);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechainicio());
			
			column = fila.createCell(7);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechafin());			
		}
		
		sheetDetalle.autoSizeColumn(0);
		sheetDetalle.autoSizeColumn(1);
		sheetDetalle.autoSizeColumn(2);
		sheetDetalle.autoSizeColumn(3);
		sheetDetalle.autoSizeColumn(4);
		sheetDetalle.autoSizeColumn(5);
		sheetDetalle.autoSizeColumn(6);
		sheetDetalle.autoSizeColumn(7);
		
		
		ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
		try {
			workbook.write(respuesta);
			workbook.close();
		} catch(Exception e) {
			
		}
		return respuesta.toByteArray();
	}
	
}
