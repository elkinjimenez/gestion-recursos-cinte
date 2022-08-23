package com.claro.gestionrecursosapi.reporte.domain;

import com.claro.gestionrecursosapi.reporte.dto.ReporteFiltroDto;
import com.claro.gestionrecursosapi.reporte.entity.ReporteProrrateoPersona;
import com.claro.gestionrecursosapi.reporte.entity.ReporteTiempo;
import com.claro.gestionrecursosapi.reporte.repository.IReporteProrrateoRepository;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ReporteProrrateoService {

    @Autowired
    private IReporteProrrateoRepository repository;


    public Iterable<ReporteProrrateoPersona> informe(String CLUSUARIO, ReporteFiltroDto dto) {

        List<ReporteProrrateoPersona> prorrateoPersonaIterable = repository.generaInforme(CLUSUARIO, dto.getFechainicio(), dto.getFechafin(), dto.getCodproyecto(), dto.getCodtareatipo(), dto.getCodproveedor(), dto.getCodempleadoasignado(), dto.getCodempleadocreo(), dto.getCodtareaestado(), dto.getEslogro(), dto.getTarea());
        return prorrateoPersonaIterable;
        //return repository.generaInforme(CLUSUARIO, dto.getFechainicio(), dto.getFechafin(), dto.getCodproyecto(), dto.getCodtareatipo(), dto.getCodproveedor(), dto.getCodempleadoasignado(), dto.getCodempleadocreo(), dto.getCodtareaestado(), dto.getEslogro(), dto.getTarea());
    }

    public byte[] download(String CLUSUARIO, ReporteFiltroDto dto) {
        if (dto == null)
            dto = new ReporteFiltroDto();

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
        headerCell.setCellValue("Fecha");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(1);
        headerCell.setCellValue("cod Proyecto");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(2);
        headerCell.setCellValue("ID Proyecto");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(3);
        headerCell.setCellValue("PEP");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(4);
        headerCell.setCellValue("Proyecto");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(5);
        headerCell.setCellValue("Proveedor");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(6);
        headerCell.setCellValue("ID recurso");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(7);
        headerCell.setCellValue("Recurso");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(8);
        headerCell.setCellValue("Costo");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(9);
        headerCell.setCellValue("Fecha inicio");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(10);
        headerCell.setCellValue("Horas");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(11);
        headerCell.setCellValue("Horas en estructura");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(12);
        headerCell.setCellValue("Porcentaje proyecto");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(13);
        headerCell.setCellValue("Horas con estructura");
        headerCell.setCellStyle(headerStyle);

        sheetResumen.setAutoFilter(new CellRangeAddress(0, 0, 0, 13));
        sheetResumen.createFreezePane(0, 1);

        Iterable<ReporteProrrateoPersona> infoResumen = repository.generaInforme(CLUSUARIO, dto.getFechainicio(), dto.getFechafin(), dto.getCodproyecto(), dto.getCodtareatipo(), dto.getCodproveedor(), dto.getCodempleadoasignado(), dto.getCodempleadocreo(), dto.getCodtareaestado(), dto.getEslogro(), dto.getTarea());
        int iFila = 1;
        for (ReporteProrrateoPersona item : infoResumen) {
            Row fila = sheetResumen.createRow(iFila++);

            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle cellStyleDate = workbook.createCellStyle();
            cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
            Cell column = fila.createCell(0);
            column.setCellStyle(cellStyleDate);
            column.setCellValue(item.getFecha());

            column = fila.createCell(1);
            column.setCellValue(Objects.nonNull(item.getIdProyecto()) ? StringUtils.stripAccents(item.getIdProyecto()).toUpperCase() : item.getIdProyecto());

            column = fila.createCell(2);
            column.setCellValue(Objects.nonNull(item.getCodproyecto()) ? StringUtils.stripAccents(item.getCodproyecto()).toUpperCase() : item.getCodproyecto());

            column = fila.createCell(3);
            column.setCellValue(Objects.nonNull(item.getPep()) ? StringUtils.stripAccents(item.getPep()).toUpperCase() : item.getPep());

            column = fila.createCell(4);
            column.setCellValue(Objects.nonNull(item.getProyecto()) ? StringUtils.stripAccents(item.getProyecto()).toUpperCase() : item.getProyecto());

            column = fila.createCell(5);
            column.setCellValue(Objects.nonNull(item.getProveedor()) ? StringUtils.stripAccents(item.getProveedor()).toUpperCase() : item.getProveedor());

            column = fila.createCell(6);
            column.setCellValue(item.getIdrecurso());

            column = fila.createCell(7);
            column.setCellValue(Objects.nonNull(item.getRecurso()) ? ReplaceTildesUtil.replace(item.getRecurso()).toUpperCase() : item.getRecurso());

            column = fila.createCell(8);
           column.setCellValue(Objects.nonNull(item.getCosto()) ? item.getCosto().longValue(): BigDecimal.ZERO.longValue());

            cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
            column = fila.createCell(9);
            column.setCellStyle(cellStyleDate);
            column.setCellValue(item.getFechaingreso());

            column = fila.createCell(10);
            column.setCellStyle(cellStyleDate);
            column.setCellValue(Objects.nonNull(item.getHoras()) ? item.getHoras().doubleValue() : BigDecimal.ZERO.doubleValue());

            column = fila.createCell(11);
            column.setCellValue(Objects.nonNull(item.getHoraEnEstructura()) ? item.getHoraEnEstructura().doubleValue() : BigDecimal.ZERO.doubleValue());

            column = fila.createCell(12);
            column.setCellValue(Objects.nonNull(item.getPorcentajeProyecto()) ? FormatoUtil.porcentaje(item.getPorcentajeProyecto().toString()) : BigDecimal.ZERO.doubleValue());

            column = fila.createCell(13);
            column.setCellValue(Objects.nonNull(item.getHoraConEstructura()) ? item.getHoraConEstructura().doubleValue() : BigDecimal.ZERO.doubleValue());

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


        ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
        try {
            workbook.write(respuesta);
            workbook.close();
        } catch(Exception e) {

        }
        return respuesta.toByteArray();
    }

    

}
