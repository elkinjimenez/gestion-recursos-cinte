package com.claro.gestionrecursosapi.reportegenerico.domain;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.claro.gestionrecursosapi.reportegenerico.entity.VistaEntity;
import com.claro.gestionrecursosapi.reportegenerico.repository.VistaViewRepository;
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

import com.claro.gestionrecursosapi.estructura.entity.EstructuraorganizacionalEntity;
import com.claro.gestionrecursosapi.estructura.repository.IEstructuraOrganizacionalRepository;
import com.claro.gestionrecursosapi.reportegenerico.dto.ReporteFiltroDto;
import com.claro.gestionrecursosapi.reportegenerico.dto.ViewInformationDto;
import com.claro.gestionrecursosapi.reportegenerico.entity.ColumnInformationDb;
import com.claro.gestionrecursosapi.reportegenerico.entity.RangoEntity;
import com.claro.gestionrecursosapi.reportegenerico.repository.ColumnInformationDbRepository;
import com.claro.gestionrecursosapi.reportegenerico.repository.RangoRepository;



@Service
public class ReporteGenericoService {
	@Autowired
	VistaViewRepository vistaViewRepository;

	@Autowired
	ColumnInformationDbRepository columnInformationDbRepository;

	@Autowired
	RangoRepository rangoRepository;

	@Autowired
	EntityManager  entityManager;
	
	@Autowired
	IEstructuraOrganizacionalRepository estructuraOrganizacionalRepository;

	/**
	 * Actualiza el registro de rango en base de datos, este es usado por las vistas
	 * internamente en la base de datos cuando se generan 
	 * @param filtros
	 */
	private void updateRango(ReporteFiltroDto filtros) {
		Optional<RangoEntity> rangoRegistroResult = rangoRepository.getRangoRegistro();
		RangoEntity rangoRegistro = rangoRegistroResult.get();
		rangoRegistro.setFechainicio(filtros.getFechainicio());
		rangoRegistro.setFechafin(filtros.getFechafin());
		rangoRegistro.setCodEstructura(filtros.getCodEstructura());
		
		
		EstructuraorganizacionalEntity estructuraorganizacional = estructuraOrganizacionalRepository.findById(filtros.getCodEstructura()).orElse(null);
		rangoRegistro.setGer(estructuraorganizacional.getCodpadre());
		

		rangoRepository.save(rangoRegistro);
	}

	/**
	 * Retorna la lista de vistas vinculadas al usuario de base de datos
	 * actual
	 * @return
	 */
	public Iterable<VistaEntity> getVistasUsuarioDb() {
		return vistaViewRepository.findVistasHerramienta();
	}



	public RangoEntity getRangoRegistro() {
		return rangoRepository.getRangoRegistro().orElse(null);
	}

	/**
	 * Retorna la información de las columnas y filas de la vista especificada
	 * @param filtros
	 * @return
	 */
	public ViewInformationDto getInformacionVista(ReporteFiltroDto filtros) {
		updateRango(filtros);

		Iterable<ColumnInformationDb> columns = columnInformationDbRepository.getColumnasTabla(filtros.getViewName());
		ArrayList<String> columnsNames = new ArrayList<String>();

		for (ColumnInformationDb column : columns) {
			columnsNames.add(column.getName());
		}

		String queryString = "select ";

		for(String columnName : columnsNames) {
			// si es la última columna no se pone coma en la query
			if(columnName.equals(columnsNames.get(columnsNames.size() - 1))) {
				queryString += columnName + " ";
			} else {
				queryString += columnName + ", ";
			}
		}

		queryString += "from " + filtros.getViewName();
		
		System.out.println("Query: "+queryString);

		setSessionDatesFormat();

		Query query = entityManager.createNativeQuery(queryString);

		List<Object[]> dataRows = query.getResultList();
		ViewInformationDto viewInformation = new ViewInformationDto();
		viewInformation.setColumns(columns);
		viewInformation.setDataRows(dataRows);
		return viewInformation;
	}

	public byte[] download(ReporteFiltroDto filtros) {
		ViewInformationDto viewInfo = getInformacionVista(filtros);
		Workbook workbook = new XSSFWorkbook();
		// Hoja de datos
		Sheet sheetDatos = workbook.createSheet("Registros");
		Row headerRow = sheetDatos.createRow(0);

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

		int currentHeaderCellIndex = 0;

		for(ColumnInformationDb column: viewInfo.getColumns()) {
			Cell headerCell = headerRow.createCell(currentHeaderCellIndex);
			headerCell.setCellValue(column.getName());
			headerCell.setCellStyle(headerStyle);
			currentHeaderCellIndex++;
		}

		currentHeaderCellIndex--;

		sheetDatos.setAutoFilter(new CellRangeAddress(0, 0, 0, currentHeaderCellIndex));
		sheetDatos.createFreezePane(0, 1);

		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle cellStyleDate = workbook.createCellStyle();
		cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

		int currentRowIndex = 1;

		for (Object[] dataRow : viewInfo.getDataRows()) {
			Row fila = sheetDatos.createRow(currentRowIndex++);

			Cell cell = fila.createCell(0);

			int currentRowCellIndex = 0;

			for(Object value: dataRow) {
				if(value instanceof Date) {
					cell = fila.createCell(currentRowCellIndex);
					cell.setCellStyle(cellStyleDate);
					cell.setCellValue((Date) value);
				} else if (value instanceof BigDecimal) {
					cell = fila.createCell(currentRowCellIndex);
					cell.setCellValue(((BigDecimal) value).doubleValue());
				} else {
					cell = fila.createCell(currentRowCellIndex);
					cell.setCellValue((String) value);
				}

				currentRowCellIndex++;
			}
		}

		int currentColumnIndex = 0;

		for(ColumnInformationDb column: viewInfo.getColumns()) {
			sheetDatos.autoSizeColumn(currentColumnIndex);

			currentColumnIndex++;
		}

		ByteArrayOutputStream respuesta = new ByteArrayOutputStream();

		try {
			workbook.write(respuesta);
			workbook.close();
		} catch(Exception e) {
			
		}
		return respuesta.toByteArray();
	}

	/**
	 * Asigna el formato de fechas de trabajo, para consultar las vistas de DB
	 * solución a error ORA-01843: not a valid month, el formato de fechas usado por las vistas actualmente es DD/MM/YYYY
	 */
	private void setSessionDatesFormat() {
		try {
			Query query = entityManager.createNativeQuery("ALTER SESSION SET nls_date_format = 'DD/MM/YYYY'");

			query.getResultList();
		} catch (Exception e) {
			//System.out.println("session alter: " + ExceptionUtils.getStackTraceString(e));
		}
	}
}
