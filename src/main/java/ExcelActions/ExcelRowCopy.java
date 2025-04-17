package ExcelActions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import UserInput.UserInput;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RangeCopier;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Selenium.GetSourceData;

public class ExcelRowCopy extends RangeCopier {

    static String sourceFilePath = "./src/main/resources/nvda-income-statement-annual.xlsx";
    static String destinationFilePath = "./src/main/resources/MASTER COPY_copy.xlsx";

    //Income Data
    
    //Source
    static final int SOURCE_SHEET = 0;
    static final int SOURCE_DILUTED_WASO_ROW = 19;
    static final int SOURCE_DILUTED_WASO_BEG_COL = 1;
    static final int SOURCE_DILUTED_WASO_END_COL = 11;
    static final int SOURCE_NET_INCOME_AFTER_NC_M_I_ROW = 16;
    static final int SOURCE_NET_INCOME_AFTER_NC_M_I_BEG_COL = 1;
    static final int SOURCE_NET_INCOME_AFTER_NC_M_I_END_COL = 11;
    //Destination
    static final int DEST_SHEET = 1;
    static final int DEST_DILUTED_WASO_ROW = 2;
    static final int DEST_DILUTED_WASO_BEG_COL = 1;
    static final int DEST_DILUTED_WASO_END_COL = 11;
    static final int DEST_NET_INCOME_AFTER_NC_M_I_ROW = 1;
    static final int DEST_NET_INCOME_AFTER_NC_M_I_BEG_COL = 1;
    static final int DEST_NET_INCOME_AFTER_NC_M_I_END_COL = 11;

    static List<Integer[]> sourceIncomeArrays = new ArrayList<>();
    static Integer[] sourceDilutedWASOArray = new Integer[]{};
    static Integer[] sourceNetIncomeAfterNCMIArray = new Integer[]{};
    static List<Integer[]> destIncomeArrays = new ArrayList<>();
    static Integer[] destDilutedWASOArray = new Integer[]{};
    static Integer[] destNetIncomeAfterNCMIArray = new Integer[]{};

    public static void main(String[] args) {

        GetSourceData getSourceData = new GetSourceData();
        getSourceData.login();
        UserInput ui = new UserInput();
        ui.promptUser();

        // try (FileInputStream sourceFile = new FileInputStream(sourceFilePath);
        //      FileInputStream destinationFile = new FileInputStream(destinationFilePath);
        //      Workbook sourceWorkbook = new XSSFWorkbook(sourceFile);
        //      Workbook destinationWorkbook = new XSSFWorkbook(destinationFile)) {
        //     intializeData();
        //     copyData(sourceWorkbook, destinationWorkbook);

        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    public ExcelRowCopy(Sheet sheet) {
        super(sheet);
    }

    public ExcelRowCopy(Sheet sourceSheet, Sheet destSheet) {
        super(sourceSheet, destSheet);
    }

    public static void intializeData(){


        sourceDilutedWASOArray = new Integer[]{
                SOURCE_SHEET,
                SOURCE_DILUTED_WASO_ROW,
                SOURCE_DILUTED_WASO_ROW,
                SOURCE_DILUTED_WASO_BEG_COL,
                SOURCE_DILUTED_WASO_END_COL};

        sourceNetIncomeAfterNCMIArray = new Integer[]{
                SOURCE_SHEET,
                SOURCE_NET_INCOME_AFTER_NC_M_I_ROW,
                SOURCE_NET_INCOME_AFTER_NC_M_I_ROW,
                SOURCE_NET_INCOME_AFTER_NC_M_I_BEG_COL,
                SOURCE_NET_INCOME_AFTER_NC_M_I_END_COL};

        sourceIncomeArrays.add(sourceDilutedWASOArray);
        sourceIncomeArrays.add(sourceNetIncomeAfterNCMIArray);

        destDilutedWASOArray = new Integer[]{
                DEST_SHEET,
                DEST_DILUTED_WASO_ROW,
                DEST_DILUTED_WASO_ROW,
                DEST_DILUTED_WASO_BEG_COL,
                DEST_DILUTED_WASO_END_COL};

        destNetIncomeAfterNCMIArray = new Integer[]{
                DEST_SHEET,
                DEST_NET_INCOME_AFTER_NC_M_I_ROW,
                DEST_NET_INCOME_AFTER_NC_M_I_ROW,
                DEST_NET_INCOME_AFTER_NC_M_I_BEG_COL,
                DEST_NET_INCOME_AFTER_NC_M_I_END_COL};

        destIncomeArrays.add(destDilutedWASOArray);
        destIncomeArrays.add(destNetIncomeAfterNCMIArray);


    }

    public static void copyData(Workbook sourceWorkbook, Workbook destinationWorkbook) throws IOException {

        Iterator<Integer[]> sourceItr = sourceIncomeArrays.iterator();
        Iterator<Integer[]> destItr = destIncomeArrays.iterator();

        while(sourceItr.hasNext() && destItr.hasNext()) {
            Integer[] sourceArray = (Integer[]) sourceItr.next();
            Integer[] destArray = (Integer[]) destItr.next();
            Sheet sourceSheet = sourceWorkbook.getSheetAt(sourceArray[0]);
            Sheet destinationSheet = destinationWorkbook.getSheetAt(destArray[0]);
            ExcelRowCopy erc = new ExcelRowCopy( sourceSheet, destinationSheet);
            CellRangeAddress sourceCellRangeAddress =
                    new CellRangeAddress(sourceArray[1],sourceArray[2],sourceArray[3],sourceArray[4]);
            CellRangeAddress destinationCellRangeAddress =
                    new CellRangeAddress(destArray[1],destArray[2],destArray[3],destArray[4]);
            erc.copyRange(sourceCellRangeAddress, destinationCellRangeAddress);
        }

        try (FileOutputStream outputStream = new FileOutputStream(destinationFilePath)) {
            destinationWorkbook.write(outputStream);
        }

        System.out.println("Row copied successfully!");


    }

    @Override
    public void copyRange(CellRangeAddress tilePatternRange, CellRangeAddress tileDestRange) {
        super.copyRange(tilePatternRange, tileDestRange, true,true);
    }

    @Override
    protected void adjustCellReferencesInsideFormula(Cell cell, Sheet destSheet, int deltaX, int deltaY) {

    }

        /*private static void copyRow(Row sourceRow, Row destinationRow) {
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
    }*/
}
