package com.claro.gestionrecursosapi.reporte.domain;

import com.claro.gestionrecursosapi.reporte.dto.ReporteFiltroDto;
import com.claro.gestionrecursosapi.reporte.entity.ReporteProrrateoGeneral;
import com.claro.gestionrecursosapi.reporte.entity.ReporteProrrateoPersona;
import com.claro.gestionrecursosapi.reporte.entity.ReporteProrrateoPresupuesto;
import com.claro.gestionrecursosapi.reporte.repository.IReporteProrrateoPresupuestoRepository;
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
import java.util.Objects;

@Service
public class ReporteProrrateoPresupuestoService {

    @Autowired
    private IReporteProrrateoPresupuestoRepository repository;

    public Iterable<ReporteProrrateoPresupuesto> informe(String CLUSUARIO, ReporteFiltroDto dto) {
        return repository.infoReporte(CLUSUARIO, dto.getFechainicio(), dto.getFechafin(), dto.getCodproyecto(), dto.getCodtareatipo(), dto.getCodproveedor(), dto.getCodempleadoasignado(), dto.getCodempleadocreo(), dto.getCodtareaestado(), dto.getEslogro(), dto.getTarea());
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
        headerCell.setCellValue("Proyecto");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(2);
        headerCell.setCellValue("Presupuesto");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(3);
        headerCell.setCellValue("Proveedor");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(4);
        headerCell.setCellValue("Perfil");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(5);
        headerCell.setCellValue("ID recurso");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(6);
        headerCell.setCellValue("Recurso");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(7);
        headerCell.setCellValue("Horas sin estructura");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(8);
        headerCell.setCellValue("Costo sin estructura");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(9);
        headerCell.setCellValue("Horas con estructura");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(10);
        headerCell.setCellValue("Costo con estructura");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(11);
        headerCell.setCellValue("Prorrateo");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(12);
        headerCell.setCellValue("Presupuesto disponible");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(13);
        headerCell.setCellValue("Presupuesto consumido");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(14);
        headerCell.setCellValue("Porcentaje horas");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(15);
        headerCell.setCellValue("Porcentaje costo");
        headerCell.setCellStyle(headerStyle);

        sheetResumen.setAutoFilter(new CellRangeAddress(0, 0, 0, 15));
        sheetResumen.createFreezePane(0, 1);

        Iterable<ReporteProrrateoPresupuesto> infoResumen = repository.infoReporte(CLUSUARIO, dto.getFechainicio(), dto.getFechafin(), dto.getCodproyecto(), dto.getCodtareatipo(), dto.getCodproveedor(), dto.getCodempleadoasignado(), dto.getCodempleadocreo(), dto.getCodtareaestado(), dto.getEslogro(), dto.getTarea());
        int iFila = 1;
        for (ReporteProrrateoPresupuesto item : infoResumen) {
            Row fila = sheetResumen.createRow(iFila++);

            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle cellStyleDate = workbook.createCellStyle();
            cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
            Cell column = fila.createCell(0);
            column.setCellStyle(cellStyleDate);
            column.setCellValue(item.getFecha());

            column = fila.createCell(1);
            column.setCellValue(Objects.nonNull(item.getProyecto()) ? ReplaceTildesUtil.replace(item.getProyecto()).toUpperCase() : item.getProyecto());

            column = fila.createCell(2);
            column.setCellValue(Objects.nonNull(item.getPresupuesto()) ? item.getPresupuesto().longValue():0);

            column = fila.createCell(3);
            column.setCellValue(Objects.nonNull(item.getProveedor()) ? ReplaceTildesUtil.replace(item.getProveedor()).toUpperCase() : item.getProveedor());

            column = fila.createCell(4);
            column.setCellValue(Objects.nonNull(item.getPerfil()) ? StringUtils.stripAccents(item.getPerfil()).toUpperCase() : item.getPerfil());

            column = fila.createCell(5);
            column.setCellValue(item.getIdRecurso());

            column = fila.createCell(6);
            column.setCellValue(Objects.nonNull(item.getRecurso()) ? ReplaceTildesUtil.replace(item.getRecurso()).toUpperCase() : item.getRecurso());

            column = fila.createCell(7);
            column.setCellValue(Objects.nonNull(item.getHorasSinEstructura()) ? item.getHorasSinEstructura().doubleValue():0);

            column = fila.createCell(8);
            column.setCellValue(Objects.nonNull(item.getCostoSinEstructura()) ?  item.getCostoSinEstructura().doubleValue():0);

            column = fila.createCell(9);
            column.setCellValue(Objects.nonNull(item.getHorasConEstructura())   ?
            		item.getHorasConEstructura().doubleValue(): 
            			0);

            column = fila.createCell(10);
            column.setCellValue(Objects.nonNull(item.getCostoConEstructura()) ?  item.getCostoConEstructura().longValue():0);

            column = fila.createCell(11);
          
            column.setCellValue(Objects.nonNull(item.getProrrateo())? item.getProrrateo().doubleValue():0);

            column = fila.createCell(12);
            column.setCellValue(Objects.nonNull(item.getPresupuestoDisponible()) ? item.getPresupuestoDisponible().doubleValue():0);

            column = fila.createCell(13);
            column.setCellValue(Objects.nonNull(item.getPresupuestoConsumido()) ? item.getPresupuestoConsumido().doubleValue():0);

            column = fila.createCell(14);
            column.setCellValue(Objects.nonNull(item.getPorcentajeHoras()) ? FormatoUtil.porcentaje(item.getPorcentajeHoras().toString()):0);

            
            column = fila.createCell(15);
            column.setCellValue(Objects.nonNull(item.getPorcentajeCosto()) ? FormatoUtil.porcentaje(item.getPorcentajeCosto().toString()):0);


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


        ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
        try {
            workbook.write(respuesta);
            workbook.close();
        } catch(Exception e) {

        }
        return respuesta.toByteArray();
    }
    
    private void columnPorcentaje(Cell column,Workbook workbook) {
    	 CellStyle style = workbook.createCellStyle();
         style.setDataFormat(workbook.createDataFormat().getFormat("0.000%"));
         column.setCellStyle(style);
    }
}
