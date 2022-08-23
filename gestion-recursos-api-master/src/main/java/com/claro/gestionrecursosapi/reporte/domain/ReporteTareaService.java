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
import com.claro.gestionrecursosapi.reporte.entity.ReporteTareaVU;
import com.claro.gestionrecursosapi.reporte.repository.IReporteTareaRepository;

@Service
public class ReporteTareaService {

	@Autowired
	private IReporteTareaRepository repository;

	@Autowired
	private EmpleadoService empleadoService; 
	
	public Iterable<ReporteTareaVU> findAll(String CLUSUARIO, ReporteFiltroDto dto) {
		if (dto == null)
			dto = new ReporteFiltroDto();

		EmpleadoEstructuraOrganizacionalVU lastEmpleadoParent = empleadoService.getLastEmpleadoParent(CLUSUARIO);

		return repository.findAll(	CLUSUARIO,
									dto.getFechainicio(), dto.getFechafin(), 
									dto.getCodproyecto(), dto.getCodproveedor(), dto.getTarea(), 
									dto.getCodtareatipo(), dto.getCodtareaestado(), dto.getEslogro(), 
									dto.getCodempleadocreo(), dto.getCodempleadoasignado(), 
									lastEmpleadoParent.getEstructuraJerarquia());
	}
	
	public byte[] download(String CLUSUARIO, ReporteFiltroDto dto) {
		if (dto == null)
			dto = new ReporteFiltroDto();

		EmpleadoEstructuraOrganizacionalVU lastEmpleadoParent = empleadoService.getLastEmpleadoParent(CLUSUARIO);
		
		Iterable<ReporteTareaVU> info = repository.findAll( CLUSUARIO,
															dto.getFechainicio(), dto.getFechafin(), 
															dto.getCodproyecto(), dto.getCodproveedor(), dto.getTarea(), 
															dto.getCodtareatipo(), dto.getCodtareaestado(), dto.getEslogro(), 
															dto.getCodempleadocreo(), dto.getCodempleadoasignado(),
															lastEmpleadoParent.getEstructuraJerarquia());
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Información");
		Row header = sheet.createRow(0);
		 
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
		
		 
		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("Proyecto");
		headerCell.setCellStyle(headerStyle);
		 
		headerCell = header.createCell(1);
		headerCell.setCellValue("Miga");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(2);
		headerCell.setCellValue("Tarea");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(3);
		headerCell.setCellValue("Tipo");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(4);
		headerCell.setCellValue("Estado");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(5);
		headerCell.setCellValue("Es logro");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(6);
		headerCell.setCellValue("Responsable");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(7);
		headerCell.setCellValue("Fecha ini real");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(8);
		headerCell.setCellValue("Fecha fin real");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(9);
		headerCell.setCellValue("Tiempo real");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(10);
		headerCell.setCellValue("Tiempo total");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(11);
		headerCell.setCellValue("Fecha ini estimada");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(12);
		headerCell.setCellValue("Fecha fin estimada");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(13);
		headerCell.setCellValue("Tiempo estimado");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(14);
		headerCell.setCellValue("Proveedor");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(15);
		headerCell.setCellValue("Fecha creación");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(16);
		headerCell.setCellValue("Quién la creó");
		headerCell.setCellStyle(headerStyle);
		
		sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 17));
		sheet.createFreezePane(0, 1);
		
		int iFila = 1;
		for (ReporteTareaVU item : info) {
			Row fila = sheet.createRow(iFila++);

			Cell column = fila.createCell(0);
			column.setCellValue(Objects.nonNull(item.getProyecto()) ? StringUtils.stripAccents(item.getProyecto()).toUpperCase() : item.getProyecto());
			
			column = fila.createCell(1);
			column.setCellValue(Objects.nonNull(item.getTarearuta()) ? StringUtils.stripAccents(item.getTarearuta()).toUpperCase() : item.getTarearuta());
			
			column = fila.createCell(2);
			column.setCellValue(Objects.nonNull(item.getTarea()) ? StringUtils.stripAccents(item.getTarea()).toUpperCase() : item.getTarea());
			
			column = fila.createCell(3);
			column.setCellValue(Objects.nonNull(item.getTareatipo()) ? StringUtils.stripAccents(item.getTareatipo()).toUpperCase() : item.getTareatipo());
			
			column = fila.createCell(4);
			column.setCellValue(Objects.nonNull(item.getTareaestado()) ? StringUtils.stripAccents(item.getTareaestado()).toUpperCase() : item.getTareaestado());
			
			column = fila.createCell(5);
			column.setCellValue(item.getEslogro() == null ? "" : item.getEslogro() ? "Si" : "No");
			
			column = fila.createCell(6);
			column.setCellValue(Objects.nonNull(item.getEmpleadoasignado()) ? StringUtils.stripAccents(item.getEmpleadoasignado()).toUpperCase() : item.getEmpleadoasignado());
			
			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle cellStyleDate = workbook.createCellStyle();
			cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
			column = fila.createCell(7);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechainireal());
			
			column = fila.createCell(8);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechafinreal());
			
			column = fila.createCell(9);
			column.setCellValue(item.getTiemporeal() != null ? item.getTiemporeal().intValue() : 0);
			
			column = fila.createCell(10);
			column.setCellValue(item.getTiemporealacumulado() != null ? item.getTiemporealacumulado().intValue() : 0);
			
			column = fila.createCell(11);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechainiestimada());
			
			column = fila.createCell(12);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechafinestimada());
			
			column = fila.createCell(13);
			column.setCellValue(item.getTiempoestimado() != null ? item.getTiempoestimado().intValue() : 0);
			
			column = fila.createCell(14);
			column.setCellValue(Objects.nonNull(item.getProveedor()) ? StringUtils.stripAccents(item.getProveedor()).toUpperCase() : item.getProveedor());
			
			column = fila.createCell(15);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechacreacion());
			
			column = fila.createCell(16);
			column.setCellValue(Objects.nonNull(item.getEmpleadocreo()) ? StringUtils.stripAccents(item.getEmpleadocreo()).toUpperCase() : item.getEmpleadocreo());
		}
		
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);
		sheet.autoSizeColumn(8);
		sheet.autoSizeColumn(9);
		sheet.autoSizeColumn(10);
		sheet.autoSizeColumn(11);
		sheet.autoSizeColumn(12);
		sheet.autoSizeColumn(13);
		sheet.autoSizeColumn(14);
		sheet.autoSizeColumn(15);
		sheet.autoSizeColumn(16);
		
		ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
		try {
			workbook.write(respuesta);
			workbook.close();
		} catch(Exception e) {
			
		}
		return respuesta.toByteArray();
	}
	
}
