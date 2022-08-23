package com.claro.gestionrecursosapi.reporte.domain;

import com.claro.gestionrecursosapi.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEstructuraOrganizacionalVU;
import com.claro.gestionrecursosapi.reporte.dto.ReporteFiltroDto;
import com.claro.gestionrecursosapi.reporte.entity.ReporteListadoSolicitudes;
import com.claro.gestionrecursosapi.reporte.repository.IReporteListadoSolicitudesRepository;
import com.claro.gestionrecursosapi.utils.FormatoUtil;
import com.claro.gestionrecursosapi.utils.ReplaceTildesUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Objects;

@Service
public class ReporteListadoSolicitudesService {

    @Autowired
    private IReporteListadoSolicitudesRepository repository;

	@Autowired
	private EmpleadoService empleadoService; 

    public Iterable<ReporteListadoSolicitudes> generarReporte(String CLUSUARIO, ReporteFiltroDto dto) {
		EmpleadoEstructuraOrganizacionalVU lastEmpleadoParent = empleadoService.getLastEmpleadoParent(CLUSUARIO);

        return repository.infoReporte(dto.getFechainicio(), dto.getFechafin(), dto.getCodproyecto(), dto.getCodtareatipo(), dto.getCodproveedor(), dto.getCodempleadoasignado(), dto.getCodempleadocreo(), dto.getCodtareaestado(), dto.getEslogro(), dto.getTarea(), lastEmpleadoParent.getEstructuraJerarquia());
    }

    public byte[] download(String CLUSUARIO, ReporteFiltroDto dto) {
        if (dto == null)
            dto = new ReporteFiltroDto();

        Workbook workbook = new XSSFWorkbook();

        // Hoja de resumen

        Sheet sheetResumen = workbook.createSheet("Informe Listado Solicitudes");
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
        headerCell.setCellValue("Titulo Proyecto");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(1);
        headerCell.setCellValue("Gerente");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(2);
        headerCell.setCellValue("Descripción Tarea");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(3);
        headerCell.setCellValue("Proveedor");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(4);
        headerCell.setCellValue("Perfil");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(5);
        headerCell.setCellValue("ID Recurso");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(6);
        headerCell.setCellValue("Recurso");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(7);
        headerCell.setCellValue("Horas");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(8);
        headerCell.setCellValue("Porcentaje en proyecto");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(9);
        headerCell.setCellValue("Horas con estructura");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(10);
        headerCell.setCellValue("Lider");
        headerCell.setCellStyle(headerStyle);


        sheetResumen.setAutoFilter(new CellRangeAddress(0, 0, 0, 10));
        sheetResumen.createFreezePane(0, 1);

		EmpleadoEstructuraOrganizacionalVU lastEmpleadoParent = empleadoService.getLastEmpleadoParent(CLUSUARIO);

        Iterable<ReporteListadoSolicitudes> infoResumen = repository.infoReporte(dto.getFechainicio(), dto.getFechafin(), dto.getCodproyecto(), dto.getCodtareatipo(), dto.getCodproveedor(), dto.getCodempleadoasignado(), dto.getCodempleadocreo(), dto.getCodtareaestado(), dto.getEslogro(), dto.getTarea(), lastEmpleadoParent.getEstructuraJerarquia());
        int iFila = 1;
        for (ReporteListadoSolicitudes item : infoResumen) {
            Row fila = sheetResumen.createRow(iFila++);

            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle cellStyleDate = workbook.createCellStyle();
            Cell column = fila.createCell(0);
            column.setCellValue(Objects.nonNull(item.getProyecto()) ? StringUtils.stripAccents(item.getProyecto()).toUpperCase() : item.getProyecto());

            column = fila.createCell(1);
            column.setCellValue(Objects.nonNull(item.getGerente()) ? StringUtils.stripAccents(item.getGerente()).toUpperCase() : item.getGerente());

            column = fila.createCell(2);
            column.setCellValue(Objects.nonNull(item.getDescripcionTarea()) ? StringUtils.stripAccents(item.getDescripcionTarea()).toUpperCase() : item.getDescripcionTarea());

            column = fila.createCell(3);
            column.setCellValue(Objects.nonNull(item.getProveedor()) ? StringUtils.stripAccents(item.getProveedor()).toUpperCase() : item.getProveedor());

            column = fila.createCell(4);
            column.setCellValue(Objects.nonNull(item.getPerfil()) ? StringUtils.stripAccents(item.getPerfil()).toUpperCase() : item.getPerfil());

            column = fila.createCell(5);
            column.setCellValue(item.getIdRecurso());

            column = fila.createCell(6);
            column.setCellValue(Objects.nonNull(item.getRecurso()) ? ReplaceTildesUtil.replace(item.getRecurso()).toUpperCase() : item.getRecurso());

            column = fila.createCell(7);
            column.setCellValue(Objects.nonNull(item.getHoras()) ? item.getHoras().doubleValue() : BigDecimal.ZERO.doubleValue());

            column = fila.createCell(8);
            column.setCellValue(Objects.nonNull(item.getPorcentajeEnProyecto()) ? FormatoUtil.porcentaje(item.getPorcentajeEnProyecto().toString()) : BigDecimal.ZERO.doubleValue());

            column = fila.createCell(9);
            column.setCellValue(Objects.nonNull(item.getHorasConEstructura()) ? item.getHorasConEstructura().doubleValue() : BigDecimal.ZERO.doubleValue());

            column = fila.createCell(10);
            column.setCellValue(Objects.nonNull(item.getDoliente()) ? StringUtils.stripAccents(item.getDoliente()).toUpperCase() : item.getDoliente());

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


        ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
        try {
            workbook.write(respuesta);
            workbook.close();
        } catch(Exception e) {

        }
        return respuesta.toByteArray();
    }
}
