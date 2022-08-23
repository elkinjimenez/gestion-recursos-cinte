package com.claro.gestionrecursosapi.reporte.domain;

import com.claro.gestionrecursosapi.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEstructuraOrganizacionalVU;
import com.claro.gestionrecursosapi.reporte.dto.ReporteFiltroDto;
import com.claro.gestionrecursosapi.reporte.entity.ReporteOpex;
import com.claro.gestionrecursosapi.reporte.enums.ModalidadEmpleadoEnum;
import com.claro.gestionrecursosapi.reporte.repository.IReporteOpexRepository;
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
public class ReporteOpexService {

    @Autowired
    private IReporteOpexRepository repository;

	@Autowired
	private EmpleadoService empleadoService;

    public Iterable<ReporteOpex> generarReporte(String CLUSUARIO, ReporteFiltroDto dto) {
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
        headerCell.setCellValue("Proveedor");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(2);
        headerCell.setCellValue("Perfil");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(3);
        headerCell.setCellValue("Modalidad");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(4);
        headerCell.setCellValue("Recurso");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(5);
        headerCell.setCellValue("Prorrateo por recurso");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(6);
        headerCell.setCellValue("Horas directas");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(7);
        headerCell.setCellValue("Horas en estructura");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(8);
        headerCell.setCellValue("Horas OPEX");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerResumen.createCell(9);
        headerCell.setCellValue("Cobro proyecto");
        headerCell.setCellStyle(headerStyle);

        sheetResumen.setAutoFilter(new CellRangeAddress(0, 0, 0, 9));
        sheetResumen.createFreezePane(0, 1);

		EmpleadoEstructuraOrganizacionalVU lastEmpleadoParent = empleadoService.getLastEmpleadoParent(CLUSUARIO);

        Iterable<ReporteOpex> infoResumen = repository.infoReporte(dto.getFechainicio(), dto.getFechafin(), dto.getCodproyecto(), dto.getCodtareatipo(), dto.getCodproveedor(), dto.getCodempleadoasignado(), dto.getCodempleadocreo(), dto.getCodtareaestado(), dto.getEslogro(), dto.getTarea(), lastEmpleadoParent.getEstructuraJerarquia());
        int iFila = 1;
        for (ReporteOpex item : infoResumen) {
            Row fila = sheetResumen.createRow(iFila++);

            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle cellStyleDate = workbook.createCellStyle();
            Cell column = fila.createCell(0);
            column.setCellValue(Objects.nonNull(item.getProyecto()) ? StringUtils.stripAccents(item.getProyecto()).toUpperCase() : item.getProyecto());

            column = fila.createCell(1);
            column.setCellValue(Objects.nonNull(item.getProveedor()) ? StringUtils.stripAccents(item.getProveedor()).toUpperCase() : item.getProveedor());

            column = fila.createCell(2);
            column.setCellValue(Objects.nonNull(item.getPerfil()) ? StringUtils.stripAccents(item.getPerfil()).toUpperCase() : item.getPerfil());

            column = fila.createCell(3);
            column.setCellValue(item.getModalidad().toString().equals("0") ? ModalidadEmpleadoEnum.CAPEX.getDescripcion() : ModalidadEmpleadoEnum.OPEX.getDescripcion());

            column = fila.createCell(4);
            column.setCellValue(Objects.nonNull(item.getRecurso()) ? ReplaceTildesUtil.replace(item.getRecurso()).toUpperCase() : item.getRecurso());

            column = fila.createCell(5);
            column.setCellValue(Objects.nonNull(item.getProrrateoPorRecurso()) ? item.getProrrateoPorRecurso().doubleValue(): BigDecimal.ZERO.doubleValue());

            column = fila.createCell(6);
            column.setCellValue(Objects.nonNull(item.getHorasDirectas()) ? item.getHorasDirectas().doubleValue() : BigDecimal.ZERO.doubleValue());

            column = fila.createCell(7);
            column.setCellValue(Objects.nonNull(item.getHorasEnEstructura()) ? item.getHorasEnEstructura().doubleValue() : BigDecimal.ZERO.doubleValue());

            column = fila.createCell(8);
            column.setCellValue(Objects.nonNull(item.getHorasOpex()) ? item.getHorasOpex().doubleValue() : BigDecimal.ZERO.doubleValue());

            column = fila.createCell(9);
            column.setCellValue(Objects.nonNull(item.getCobroProyecto()) ? item.getCobroProyecto().doubleValue() : BigDecimal.ZERO.doubleValue());

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

        ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
        try {
            workbook.write(respuesta);
            workbook.close();
        } catch(Exception e) {

        }
        return respuesta.toByteArray();
    }
}
