package com.claro.gestionrecursosapi.utils;

import com.claro.gestionrecursosapi.cargue.model.CarguePresupuesto;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Component
public class CargueUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CargueUtil.class);

    private static final String FORMATO1 = "xls";
    private static final String FORMATO2 = "xlsx";

    public List<CarguePresupuesto> lecturaArchivoPresupuesto(MultipartFile archivo, String extension) throws IOException {
        File file = convert(archivo);
        List<CarguePresupuesto> lCarguePresupuesto = new ArrayList<>();
        List<Object[]> lValores = new ArrayList<>();
        //obtaining input bytes from a file
        FileInputStream fis = new FileInputStream(file);
        if (FORMATO2.equals(extension)) {
            //creating workbook instance that refers to .xls file
            XSSFWorkbook wbxlsx = new XSSFWorkbook(fis);
            //creating a Sheet object to retrieve the object
            XSSFSheet sheetxlsx = wbxlsx.getSheetAt(0);
            //evaluating cell type
            FormulaEvaluator formulaEvaluator = wbxlsx.getCreationHelper().createFormulaEvaluator();
            int indice = 1;
            for (Row row : sheetxlsx)     //iteration over row using for each loop
            {
                for (Cell cell : row)    //iteration over cell using for each loop
                {
                    if (Objects.isNull(sheetxlsx.getRow(row.getRowNum() + indice))) {
                        break;
                    }
                    CarguePresupuesto carguePresupuesto = new CarguePresupuesto();
                    XSSFCell celda1 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(0);
                    XSSFCell celda2 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(1);
                    XSSFCell celda3 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(2);
                    XSSFCell celda4 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(3);
                    XSSFCell celda5 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(4);
                    XSSFCell celda6 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(5);
                    XSSFCell celda7 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(6);
                    XSSFCell celda8 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(7);
                    XSSFCell celda9 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(8);
                    XSSFCell celda10 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(9);
                    XSSFCell celda11 = sheetxlsx.getRow(row.getRowNum() + indice).getCell(10);

                    carguePresupuesto.setNombreGerente(Objects.nonNull(celda1) ? celda1.getStringCellValue() : null);
                    carguePresupuesto.setAgrcapex(Objects.nonNull(celda2) ? celda2.getStringCellValue() : null);
                    carguePresupuesto.setIdProyecto(Objects.nonNull(celda3) ? celda3.getStringCellValue() : null);
                    carguePresupuesto.setNombreAMX(Objects.nonNull(celda4) ? celda4.getStringCellValue() : null);
                    carguePresupuesto.setNombreLocal(Objects.nonNull(celda5) ? celda5.getStringCellValue() : null);
                    carguePresupuesto.setElementoPEP(Objects.nonNull(celda6) ? celda6.getStringCellValue() : null);
                    carguePresupuesto.setTipoProyecto(Objects.nonNull(celda7) ? celda7.getStringCellValue() : null);
                    //carguePresupuesto.setPresupuestoUSD(Objects.nonNull(celda8) ? String.valueOf(celda8.getNumericCellValue()) : null);
                    //carguePresupuesto.setPresupuestoCOP(Objects.nonNull(celda9) ? String.valueOf(celda9.getNumericCellValue()) : null);
                    carguePresupuesto.setUsd(Objects.nonNull(celda8) ? String.valueOf(celda8.getNumericCellValue()) : null);
                    carguePresupuesto.setCop(Objects.nonNull(celda9) ? String.valueOf(celda9.getNumericCellValue()) : null);

                    indice++;

                    lCarguePresupuesto.add(carguePresupuesto);
                }

            }
        } else {
            //creating workbook instance that refers to .xls file
            HSSFWorkbook wbxls = new HSSFWorkbook(fis);
            //creating a Sheet object to retrieve the object
            HSSFSheet sheetxls = wbxls.getSheetAt(0);
            //evaluating cell type
            FormulaEvaluator formulaEvaluator = wbxls.getCreationHelper().createFormulaEvaluator();
            int indice = 1;
            for (Row row : sheetxls)     //iteration over row using for each loop
            {
                for (Cell cell : row)    //iteration over cell using for each loop
                {
                    if (Objects.isNull(sheetxls.getRow(row.getRowNum() + indice))) {
                        break;
                    }
                    CarguePresupuesto carguePresupuesto = new CarguePresupuesto();
                    HSSFCell celda1 = sheetxls.getRow(row.getRowNum() + indice).getCell(0);
                    HSSFCell celda2 = sheetxls.getRow(row.getRowNum() + indice).getCell(1);
                    HSSFCell celda3 = sheetxls.getRow(row.getRowNum() + indice).getCell(2);
                    HSSFCell celda4 = sheetxls.getRow(row.getRowNum() + indice).getCell(3);
                    HSSFCell celda5 = sheetxls.getRow(row.getRowNum() + indice).getCell(4);
                    HSSFCell celda6 = sheetxls.getRow(row.getRowNum() + indice).getCell(5);
                    HSSFCell celda7 = sheetxls.getRow(row.getRowNum() + indice).getCell(6);
                    HSSFCell celda8 = sheetxls.getRow(row.getRowNum() + indice).getCell(7);
                    HSSFCell celda9 = sheetxls.getRow(row.getRowNum() + indice).getCell(8);
                    HSSFCell celda10 = sheetxls.getRow(row.getRowNum() + indice).getCell(9);
                    HSSFCell celda11 = sheetxls.getRow(row.getRowNum() + indice).getCell(10);

                    carguePresupuesto.setNombreGerente(Objects.nonNull(celda1) ? celda1.getStringCellValue() : null);
                    carguePresupuesto.setAgrcapex(Objects.nonNull(celda2) ? celda2.getStringCellValue() : null);
                    carguePresupuesto.setIdProyecto(Objects.nonNull(celda3) ? celda3.getStringCellValue() : null);
                    carguePresupuesto.setNombreAMX(Objects.nonNull(celda4) ? celda4.getStringCellValue() : null);
                    carguePresupuesto.setNombreLocal(Objects.nonNull(celda5) ? celda5.getStringCellValue() : null);
                    carguePresupuesto.setElementoPEP(Objects.nonNull(celda6) ? celda6.getStringCellValue() : null);
                    carguePresupuesto.setTipoProyecto(Objects.nonNull(celda7) ? celda7.getStringCellValue() : null);
                    //carguePresupuesto.setPresupuestoUSD(Objects.nonNull(celda8) ? String.valueOf(celda8.getNumericCellValue()) : null);
                    //carguePresupuesto.setPresupuestoCOP(Objects.nonNull(celda9) ? String.valueOf(celda9.getNumericCellValue()) : null);
                    carguePresupuesto.setUsd(Objects.nonNull(celda8) ? String.valueOf(celda8.getNumericCellValue()) : null);
                    carguePresupuesto.setCop(Objects.nonNull(celda9) ? String.valueOf(celda9.getNumericCellValue()) : null);

                    indice++;

                    lCarguePresupuesto.add(carguePresupuesto);
                }

            }
        }

        return lCarguePresupuesto;
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
