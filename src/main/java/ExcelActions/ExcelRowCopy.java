package ExcelActions;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelRowCopy {

    public static void main(String[] args) {
        String sourceFilePath = "source.xlsx";
        String destinationFilePath = "destination.xlsx";
        int rowIndexToCopy = 1; // Row index to copy (0-based)

        try (FileInputStream sourceFile = new FileInputStream(sourceFilePath);
             FileInputStream destinationFile = new FileInputStream(destinationFilePath);
             Workbook sourceWorkbook = new XSSFWorkbook(sourceFile);
             Workbook destinationWorkbook = new XSSFWorkbook(destinationFile)) {

            Sheet sourceSheet = sourceWorkbook.getSheetAt(0);
            Sheet destinationSheet = destinationWorkbook.getSheetAt(0);

            Row sourceRow = sourceSheet.getRow(rowIndexToCopy);
            Row destinationRow = destinationSheet.createRow(destinationSheet.getLastRowNum() + 1);

            copyRow(sourceRow, destinationRow);

            try (FileOutputStream outputStream = new FileOutputStream(destinationFilePath)) {
                destinationWorkbook.write(outputStream);
            }

            System.out.println("Row copied successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyRow(Row sourceRow, Row destinationRow) {
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            Cell sourceCell = sourceRow.getCell(i);
            Cell destinationCell = destinationRow.createCell(i);

            if (sourceCell != null) {
                switch (sourceCell.getCellType()) {
                    case STRING:
                        destinationCell.setCellValue(sourceCell.getStringCellValue());
                        break;
                    case NUMERIC:
                        destinationCell.setCellValue(sourceCell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        destinationCell.setCellValue(sourceCell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        destinationCell.setCellFormula(sourceCell.getCellFormula());
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
