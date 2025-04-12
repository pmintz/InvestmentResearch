package ExcelActions;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelRowCopy extends RangeCopier{

    public ExcelRowCopy(Sheet sheet) {
        super(sheet);
    }

    public ExcelRowCopy(Sheet sourceSheet, Sheet destSheet) {
        super(sourceSheet, destSheet);
    }

    @Override
    public void copyRange(CellRangeAddress tilePatternRange, CellRangeAddress tileDestRange) {
        super.copyRange(tilePatternRange, tileDestRange);
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String sourceFilePath = "./src/main/resources/nvda-income-statement-annual.xlsx";
        String destinationFilePath = "./src/main/resources/MASTER COPY_copy.xlsx";
        int rowIndexToCopy = 23; // Row index to copy (0-based)

        try (FileInputStream sourceFile = new FileInputStream(sourceFilePath);
             FileInputStream destinationFile = new FileInputStream(destinationFilePath);
             Workbook sourceWorkbook = new XSSFWorkbook(sourceFile);
             Workbook destinationWorkbook = new XSSFWorkbook(destinationFile)) {

            Sheet sourceSheet = sourceWorkbook.getSheetAt(0);
            Sheet destinationSheet = destinationWorkbook.getSheetAt(1);

            ExcelRowCopy erc = new ExcelRowCopy(sourceSheet, destinationSheet);
            CellRangeAddress sourceCellRangeAddress = new CellRangeAddress(24, 24,1,11);
            CellRangeAddress destinationCellRangeAddress = new CellRangeAddress(2, 2,1,11);
            erc.copyRange(sourceCellRangeAddress, destinationCellRangeAddress);


            //Row sourceRow = sourceSheet.getRow(rowIndexToCopy);
            ////Row destinationRow = destinationSheet.createRow(destinationSheet.getLastRowNum() + 1);
            //Row destinationRow = destinationSheet.createRow(3);

            //copyRow(sourceRow, destinationRow);


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

    @Override
    protected void adjustCellReferencesInsideFormula(Cell cell, Sheet destSheet, int deltaX, int deltaY) {

    }
}
