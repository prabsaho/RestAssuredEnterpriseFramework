package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelUtils {

    public static List<LinkedHashMap<String,String>> getExcelDataAsListOfMap(String excelFileName, String sheetName) throws IOException {
        List<LinkedHashMap<String,String>> dataFromExcel = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(new File("src/test/resources/testData/" + excelFileName + ".xlsx"));
        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();
        LinkedHashMap<String,String> mapData;
        List<String> allKeys = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        for(int i = 0; i< totalRows ; i++) {
            mapData = new LinkedHashMap<>();
            if( i == 0) {
                int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
                for (int j = 0; j < totalCols; j++) {
                    allKeys.add(sheet.getRow(0).getCell(j).getStringCellValue());
                }
            }
            else {
                int totalCols = sheet.getRow(i).getPhysicalNumberOfCells();
                for (int j = 0; j < totalCols; j++) {
                    String cellValue = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
                    int size = 6;
                    if(cellValue.contains("RandomNumber")) {
                        // With size
                        if(cellValue.contains("_")) {
                            size = Integer.parseInt((cellValue.split("_"))[1]);
                        }
                        cellValue = RandomDataGen.getRandomNumberInRange(5,100);
                    }
                    mapData.put(allKeys.get(j), cellValue);
                }
                dataFromExcel.add(mapData);
            }
        }
        dataFromExcel = dataFromExcel.stream().filter(keyValuePair -> "Y".equalsIgnoreCase(keyValuePair.get("Enabled")))
                .collect(Collectors.toList());
        return dataFromExcel;
    }

    public static String getCellValueByColumnNameAndRowValue(String excelFilePath,
                                                             String sheetName,
                                                             String rowSearchColumnName,
                                                             String rowSearchValue,
                                                             String valueColumnName) throws IOException {
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        Sheet sheet = workbook.getSheet(sheetName);
        DataFormatter dataFormatter = new DataFormatter();

        if (sheet == null) {
            workbook.close();
            throw new RuntimeException("Sheet not found: " + sheetName);
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            workbook.close();
            throw new RuntimeException("Header row not found in sheet: " + sheetName);
        }

        int rowSearchColumnIndex = getColumnIndex(headerRow, rowSearchColumnName);
        int valueColumnIndex = getColumnIndex(headerRow, valueColumnName);

        if (rowSearchColumnIndex == -1) {
            workbook.close();
            throw new RuntimeException("Column not found: " + rowSearchColumnName);
        }

        if (valueColumnIndex == -1) {
            workbook.close();
            throw new RuntimeException("Column not found: " + valueColumnName);
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row currentRow = sheet.getRow(i);
            if (currentRow == null) {
                continue;
            }

            Cell searchCell = currentRow.getCell(rowSearchColumnIndex);
            String currentValue = dataFormatter.formatCellValue(searchCell).trim();

            if (rowSearchValue.equalsIgnoreCase(currentValue)) {
                Cell targetCell = currentRow.getCell(valueColumnIndex);
                String result = dataFormatter.formatCellValue(targetCell).trim();
                workbook.close();
                return result;
            }
        }

        workbook.close();
        return null;
    }

    public static void setCellValueByColumnNameAndRowValue(String excelFilePath,
                                                           String sheetName,
                                                           String rowSearchColumnName,
                                                           String rowSearchValue,
                                                           String valueColumnName,
                                                           String valueToSet) throws IOException {

        File file = new File(excelFilePath);

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter dataFormatter = new DataFormatter();

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row not found in sheet: " + sheetName);
            }

            int rowSearchColumnIndex = getColumnIndex(headerRow, rowSearchColumnName);
            int valueColumnIndex = getColumnIndex(headerRow, valueColumnName);

            if (rowSearchColumnIndex == -1) {
                throw new RuntimeException("Column not found: " + rowSearchColumnName);
            }

            if (valueColumnIndex == -1) {
                throw new RuntimeException("Column not found: " + valueColumnName);
            }

            boolean isUpdated = false;

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) {
                    continue;
                }

                Cell searchCell = currentRow.getCell(rowSearchColumnIndex);
                String currentValue = dataFormatter.formatCellValue(searchCell).trim();

                if (rowSearchValue.equalsIgnoreCase(currentValue)) {
                    Cell targetCell = currentRow.getCell(valueColumnIndex);
                    if (targetCell == null) {
                        targetCell = currentRow.createCell(valueColumnIndex);
                    }
                    targetCell.setCellValue(valueToSet);
                    isUpdated = true;
                    break;
                }
            }

            if (!isUpdated) {
                throw new RuntimeException("No row found where " + rowSearchColumnName + " = " + rowSearchValue);
            }

            // Write once
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
                fos.flush();
            }
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }

    private static int getColumnIndex(Row headerRow, String columnName) {
        DataFormatter dataFormatter = new DataFormatter();

        for (Cell cell : headerRow) {
            String headerValue = dataFormatter.formatCellValue(cell).trim();
            if (headerValue.equalsIgnoreCase(columnName.trim())) {
                return cell.getColumnIndex();
            }
        }
        return -1;
    }
}