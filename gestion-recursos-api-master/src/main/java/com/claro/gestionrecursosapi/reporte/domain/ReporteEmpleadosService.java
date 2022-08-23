package com.claro.gestionrecursosapi.reporte.domain;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

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
import com.claro.gestionrecursosapi.reporte.entity.ReporteEmpleados;
import com.claro.gestionrecursosapi.reporte.entity.ReporteOpex;
import com.claro.gestionrecursosapi.reporte.entity.ReporteRhPagos;
import com.claro.gestionrecursosapi.reporte.enums.ModalidadEmpleadoEnum;
import com.claro.gestionrecursosapi.reporte.repository.IReporteEmpleadosRepository;
import com.claro.gestionrecursosapi.reporte.repository.IReporteOpexRepository;
import com.claro.gestionrecursosapi.reporte.repository.IReporteRhPagosRepository;
import com.claro.gestionrecursosapi.utils.ReplaceTildesUtil;

@Service
public class ReporteEmpleadosService {

	@Autowired
	private IReporteEmpleadosRepository repository;

	public Iterable<ReporteEmpleados> generarReporte(String CLUSUARIO,
			ReporteFiltroDto dto) {
		return repository.infoReporte(CLUSUARIO, dto.getFechainicio(),
				dto.getFechafin(), dto.getCodempleadocreo(),dto.getCodproveedor());
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
		headerCell.setCellValue("Id");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(1);
		headerCell.setCellValue("Codigo Empleado");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(2);
		headerCell.setCellValue("Codigo Persona");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(3);
		headerCell.setCellValue("Nombre");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(4);
		headerCell.setCellValue("Codigo Tipo Documento");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(5);
		headerCell.setCellValue("Tipo Documento");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(6);
		headerCell.setCellValue("Numero Documento");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(7);
		headerCell.setCellValue("Primer Nombre");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(8);
		headerCell.setCellValue("Segundo Nombre");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(9);
		headerCell.setCellValue("Primer Apellido");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(10);
		headerCell.setCellValue("Segundo Apellido");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(11);
		headerCell.setCellValue("Telefono");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(12);
		headerCell.setCellValue("Correo");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(13);
		headerCell.setCellValue("Direccion de residencia");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(14);
		headerCell.setCellValue("Genero");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(15);
		headerCell.setCellValue("Nombre Genero");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(16);
		headerCell.setCellValue("Fecha de Nacimiento");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(17);
		headerCell.setCellValue("Codigo Perfil");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(18);
		headerCell.setCellValue("Perfil");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(19);
		headerCell.setCellValue("Codigo Tipo Perfil");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(20);
		headerCell.setCellValue("Tipo Perfil");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(21);
		headerCell.setCellValue("Codigo Linea Producto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(22);
		headerCell.setCellValue("Linea Producto");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(23);
		headerCell.setCellValue("Codigo Proveedor");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(24);
		headerCell.setCellValue("Proveedor");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(25);
		headerCell.setCellValue("Codigo Perfil Nivel");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(26);
		headerCell.setCellValue("Perfil Nivel");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(27);
		headerCell.setCellValue("Usuario Claro");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(28);
		headerCell.setCellValue("Fecha Inicio");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(29);
		headerCell.setCellValue("Fecha Fin");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(30);
		headerCell.setCellValue("Codigo Estructura Organizacional");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(31);
		headerCell.setCellValue("Estructura Organizacional");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(32);
		headerCell.setCellValue("Rol");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(33);
		headerCell.setCellValue("Codigo Modalidad");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(34);
		headerCell.setCellValue("Celula");
		headerCell.setCellStyle(headerStyle);

		headerCell = headerResumen.createCell(35);
		headerCell.setCellValue("Valor");
		headerCell.setCellStyle(headerStyle);

		sheetResumen.setAutoFilter(new CellRangeAddress(0, 0, 0, 17));
		sheetResumen.createFreezePane(0, 1);

		Iterable<ReporteEmpleados> infoResumen = repository.infoReporte(
				CLUSUARIO, dto.getFechainicio(), dto.getFechafin(),
				dto.getCodempleadocreo(),dto.getCodproveedor());
		int iFila = 1;
		for (ReporteEmpleados item : infoResumen) {
			Row fila = sheetResumen.createRow(iFila++);

			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle cellStyleDate = workbook.createCellStyle();

			Cell column = fila.createCell(0);
			column.setCellValue(Objects.nonNull(item.getId()) ? item.getId()
					.doubleValue() : item.getId());

			column = fila.createCell(1);
			column.setCellValue(Objects.nonNull(item.getCodEmpleado()) ? item
					.getCodEmpleado().doubleValue() : item.getCodEmpleado());

			column = fila.createCell(2);
			column.setCellValue(Objects.nonNull(item.getCodPersona()) ? item
					.getCodPersona().doubleValue() : item.getCodPersona());

			column = fila.createCell(3);
			column.setCellValue(Objects.nonNull(item.getNombre()) ? StringUtils
					.stripAccents(item.getNombre()).toUpperCase() : item
					.getNombre());

			column = fila.createCell(4);
			column.setCellValue(Objects.nonNull(item.getCodTipoDocumento()));

			column = fila.createCell(5);
			column.setCellValue(Objects.nonNull(item.getTipoDocumento()) ? StringUtils
					.stripAccents(item.getTipoDocumento()).toUpperCase() : item
					.getTipoDocumento());

			column = fila.createCell(6);
			column.setCellValue(Objects.nonNull(item.getNumeroDocumento()) ? item
					.getNumeroDocumento().doubleValue() : item
					.getNumeroDocumento());

			column = fila.createCell(7);
			column.setCellValue(Objects.nonNull(item.getPrimerNombre()) ? StringUtils
					.stripAccents(item.getPrimerNombre()).toUpperCase() : item
					.getPrimerNombre());

			column = fila.createCell(8);
			column.setCellValue(Objects.nonNull(item.getSegundoNombre()) ? StringUtils
					.stripAccents(item.getSegundoNombre()).toUpperCase() : item
					.getSegundoNombre());

			column = fila.createCell(9);
			column.setCellValue(Objects.nonNull(item.getPrimerApellido()) ? StringUtils
					.stripAccents(item.getPrimerApellido()).toUpperCase()
					: item.getPrimerApellido());

			column = fila.createCell(10);
			column.setCellValue(Objects.nonNull(item.getSegungoApellido()) ? StringUtils
					.stripAccents(item.getSegungoApellido()).toUpperCase()
					: item.getSegungoApellido());

			column = fila.createCell(11);
			column.setCellValue(Objects.nonNull(item.getTelefono()) ? StringUtils
					.stripAccents(item.getTelefono()).toUpperCase() : item
					.getTelefono());

			column = fila.createCell(12);
			column.setCellValue(Objects.nonNull(item.getCorreo()) ? StringUtils
					.stripAccents(item.getCorreo()).toUpperCase() : item
					.getCorreo());

			column = fila.createCell(13);
			column.setCellValue(Objects.nonNull(item.getDireccionResidencia()) ? StringUtils
					.stripAccents(item.getDireccionResidencia()).toUpperCase()
					: item.getDireccionResidencia());

			column = fila.createCell(14);
			column.setCellValue(Objects.nonNull(item.getTipoGenero()) ? StringUtils
					.stripAccents(item.getTipoGenero()).toUpperCase() : item
					.getTipoGenero());

			column = fila.createCell(15);
			column.setCellValue(Objects.nonNull(item.getGenero()) ? StringUtils
					.stripAccents(item.getGenero()).toUpperCase() : item
					.getGenero());

			cellStyleDate.setDataFormat(createHelper.createDataFormat()
					.getFormat("dd/mm/yyyy"));
			column = fila.createCell(16);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechaNacimiento());

			column = fila.createCell(17);
			column.setCellValue(Objects.nonNull(item.getCodPerfil()) ? item
					.getCodPerfil().doubleValue() : BigDecimal.ZERO.doubleValue());

			column = fila.createCell(18);
			column.setCellValue(Objects.nonNull(item.getPerfil()) ? StringUtils
					.stripAccents(item.getPerfil()).toUpperCase() : item
					.getPerfil());

			column = fila.createCell(19);
			column.setCellValue(Objects.nonNull(item.getCodPerfilTipo()) ? item
					.getCodPerfilTipo().doubleValue() : BigDecimal.ZERO.doubleValue());

			column = fila.createCell(20);
			column.setCellValue(Objects.nonNull(item.getPerfilTipo()) ? StringUtils
					.stripAccents(item.getPerfilTipo()).toUpperCase() : item
					.getPerfilTipo());

			column = fila.createCell(21);
			column.setCellValue(Objects.nonNull(item.getCodLineaProducto()) ? item
					.getCodLineaProducto().doubleValue() : BigDecimal.ZERO.doubleValue());

			column = fila.createCell(22);
			column.setCellValue(Objects.nonNull(item.getLineaProducto()) ? StringUtils
					.stripAccents(item.getLineaProducto()).toUpperCase() : item
					.getLineaProducto());

			column = fila.createCell(23);
			column.setCellValue(Objects.nonNull(item.getCodProveedor()) ? item
					.getCodProveedor().doubleValue() :BigDecimal.ZERO.doubleValue());

			column = fila.createCell(24);
			column.setCellValue(Objects.nonNull(item.getProveedor()) ? StringUtils
					.stripAccents(item.getProveedor()).toUpperCase() : item
					.getProveedor());

			column = fila.createCell(25);
			column.setCellValue(Objects.nonNull(item.getCodPerfilNivel()) ? item
					.getCodPerfilNivel().doubleValue() :BigDecimal.ZERO.doubleValue());

			column = fila.createCell(26);
			column.setCellValue(Objects.nonNull(item.getPerfilNivel()) ? StringUtils
					.stripAccents(item.getPerfilNivel()).toUpperCase() : item
					.getPerfilNivel());

			column = fila.createCell(27);
			column.setCellValue(Objects.nonNull(item.getUsuarioClaro()) ? StringUtils
					.stripAccents(item.getUsuarioClaro()).toUpperCase() : item
					.getUsuarioClaro());

			cellStyleDate.setDataFormat(createHelper.createDataFormat()
					.getFormat("dd/mm/yyyy"));
			column = fila.createCell(28);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechaInicio());

			cellStyleDate.setDataFormat(createHelper.createDataFormat()
					.getFormat("dd/mm/yyyy"));
			column = fila.createCell(29);
			column.setCellStyle(cellStyleDate);
			column.setCellValue(item.getFechaFin());

			column = fila.createCell(30);
			column.setCellValue(Objects.nonNull(item
					.getCodEstructuraOrganizacional()) ? item
					.getCodEstructuraOrganizacional().doubleValue() : item
					.getCodEstructuraOrganizacional());

			column = fila.createCell(31);
			column.setCellValue(Objects.nonNull(item
					.getEstructuraOrganizacional()) ? StringUtils.stripAccents(
					item.getEstructuraOrganizacional()).toUpperCase() : item
					.getEstructuraOrganizacional());

			column = fila.createCell(32);
			column.setCellValue(Objects.nonNull(item.getRol()) ? item.getRol()
					.doubleValue() :BigDecimal.ZERO.doubleValue());

			column = fila.createCell(33);
			column.setCellValue(Objects.nonNull(item.getCodModalidad()) ? item
					.getCodModalidad().doubleValue() : BigDecimal.ZERO.doubleValue());

			column = fila.createCell(34);
			column.setCellValue(Objects.nonNull(item.getCelula()) ? StringUtils
					.stripAccents(item.getCelula()).toUpperCase() : item
					.getCelula());

			column = fila.createCell(35);
			column.setCellValue(Objects.nonNull(item.getValor()) ? item
					.getValor().doubleValue() : BigDecimal.ZERO.doubleValue());

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

		ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
		try {
			workbook.write(respuesta);
			workbook.close();
		} catch (Exception e) {

		}
		return respuesta.toByteArray();
	}

}
