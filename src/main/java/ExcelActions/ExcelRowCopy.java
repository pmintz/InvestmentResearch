package ExcelActions;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RangeCopier;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ExcelRowCopy extends RangeCopier {
    static String[] statementTypes = {"Income", "Balance", "Cash"};
    static String home = System.getProperty("user.home");
    static String sourceBaseFilePath = home + "\\Downloads\\";
    static String destinationFilePath = "./src/main/resources/MASTER COPY 2025.xls";
    public ExcelRowCopy(Sheet sheet) {
        super(sheet);
    }

    public ExcelRowCopy(Sheet sourceSheet, Sheet destSheet) {
        super(sourceSheet, destSheet);
    }

    public static void copyData() throws IOException {
        StatementType.intializeExcelData();
        Workbook destinationWorkbook = getDestinationWorkbook();
        for (String statementType : statementTypes) {
            copyRows(getSourceWorkbook(StatementType.filePaths.get(statementType)),
                    destinationWorkbook,
                    StatementType.cellRanges.get(statementType),
                    statementType
            );
        }

    }

    public static void copyRows(Workbook sourceWorkbook, Workbook destinationWorkbook,
                                Map<String, Integer> excelRange, String statementType) throws IOException {

        Sheet sourceSheet = sourceWorkbook.getSheetAt(excelRange.get("SOURCE_SHEET"));
        Sheet destinationSheet = destinationWorkbook.getSheetAt(excelRange.get("DEST_SHEET"));

        ExcelRowCopy erc = new ExcelRowCopy(sourceSheet, destinationSheet);

        CellRangeAddress sourceCellRangeAddress =
                new CellRangeAddress(excelRange.get("SOURCE_ROW_START"), excelRange.get("SOURCE_ROW_END"),
                        excelRange.get("SOURCE_COL_START"), excelRange.get("SOURCE_COL_END"));
        CellRangeAddress destinationCellRangeAddress =
                new CellRangeAddress(excelRange.get("DEST_ROW_START"), excelRange.get("DEST_ROW_END"),
                        excelRange.get("DEST_COL_START"), excelRange.get("DEST_COL_END"));
        erc.copyRange(sourceCellRangeAddress, destinationCellRangeAddress);


        try (FileOutputStream outputStream = new FileOutputStream(destinationFilePath)) {
            destinationWorkbook.write(outputStream);
        }catch(Exception e){
            System.out.println(statementType + " statement not copied!");
            System.out.println(e.getMessage());
        }

        System.out.println(statementType + " statement copied successfully!");


    }

    public static HSSFWorkbook getSourceWorkbook(String path) {
        String sourceFilePath = sourceBaseFilePath + path;
        try (FileInputStream sourceFile = new FileInputStream(sourceFilePath)) {
            Thread.sleep(2000);
            return new HSSFWorkbook(sourceFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to Retrieve Source Workbook");

        }

        return null;
    }

    public static Workbook getDestinationWorkbook() {
        try (FileInputStream sourceFile = new FileInputStream(destinationFilePath)) {
            Thread.sleep(2000);
            return new HSSFWorkbook(sourceFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to Retrieve Destination Workbook");

        }

        return null;
    }

    @Override
    public void copyRange(CellRangeAddress tilePatternRange, CellRangeAddress tileDestRange) {
        super.copyRange(tilePatternRange, tileDestRange, true, true);
    }

    @Override
    protected void adjustCellReferencesInsideFormula(Cell cell, Sheet destSheet, int deltaX, int deltaY) {

    }

}
