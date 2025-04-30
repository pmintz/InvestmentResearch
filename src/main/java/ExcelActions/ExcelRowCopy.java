package ExcelActions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RangeCopier;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRowCopy extends RangeCopier {

    static String[] statementTypes = {"Income", "Balance", "Cash"};
    static String home = System.getProperty("user.home");
    //File file = new File(home+"/Downloads/" + fileName + ".xls");
    //static String sourceFilePath = "./src/main/resources/nvda-income-statement-annual.xlsx";
    static String sourceBaseFilePath = home +"/Downloads/" + "fileName" + ".xls";
    static String destinationFilePath = "./src/main/resources/MASTER COPY_copy.xlsx";

    //Source Income
    static final int SOURCE_SHEET_INCOME = 1;
    static final int SOURCE_ROW_INCOME_START = 2;
    static final int SOURCE_ROW_INCOME_END = 2;
    static final int SOURCE_COL_INCOME_START = 1;
    static final int SOURCE_COL_INCOME_END = 11;
    //Source Balance
    static final int SOURCE_SHEET_BALANCE = 1;
    static final int SOURCE_ROW_BALANCE_START = 2;
    static final int SOURCE_ROW_BALANCE_END = 2;
    static final int SOURCE_COL_BALANCE_START = 1;
    static final int SOURCE_COL_BALANCE_END = 11;

    //Source Cash
    static final int SOURCE_SHEET_CASH = 1;
    static final int SOURCE_ROW_CASH_START = 2;
    static final int SOURCE_ROW_CASH_END = 2;
    static final int SOURCE_COL_CASH_START = 1;
    static final int SOURCE_COL_CASH_END = 11;

    //Destination Income
    static final int DEST_SHEET_INCOME = 1;
    static final int DEST_ROW_INCOME_START = 2;
    static final int DEST_ROW_INCOME_END = 2;
    static final int DEST_COL_INCOME_START = 1;
    static final int DEST_COL_INCOME_END = 11;

    //Destination Balance

    static final int DEST_SHEET_BALANCE = 1;
    static final int DEST_ROW_BALANCE_START = 2;
    static final int DEST_ROW_BALANCE_END = 2;
    static final int DEST_COL_BALANCE_START = 1;
    static final int DEST_COL_BALANCE_END = 11;

    //Destination Cash
    static final int DEST_SHEET_CASH = 1;
    static final int DEST_ROW_CASH_START = 2;
    static final int DEST_ROW_CASH_END = 2;
    static final int DEST_COL_CASH_START = 1;
    static final int DEST_COL_CASH_END = 11;

    static List<Integer[]> sourceIncomeArrays = new ArrayList<>();
    static Integer[] sourceSheetIncomeArr = new Integer[]{};
    static List<Integer> sourceSheetIncomeList = new ArrayList<>();
    static Map<String, Integer> sourceSheetIncomeMap = new HashMap<>();
    static Map<String, Integer> destSheetIncomeMap = new HashMap<>();
    static Map<String, Map<String,Integer>> mapTypes = new HashMap<>();
    static Integer[] sourceNetIncomeAfterNCMIArray = new Integer[]{};
    static List<Integer[]> destIncomeArrays = new ArrayList<>();
    static Integer[] destDilutedWASOArray = new Integer[]{};
    static Integer[] destNetIncomeAfterNCMIArray = new Integer[]{};
    public ExcelRowCopy(Sheet sheet) {
        super(sheet);
    }

    public ExcelRowCopy(Sheet sourceSheet, Sheet destSheet) {
        super(sourceSheet, destSheet);
    }

    public static void intializeData(){


        sourceSheetIncomeArr = new Integer[]{
        SOURCE_SHEET_INCOME,
        SOURCE_ROW_INCOME_START,
        SOURCE_ROW_INCOME_END,
        SOURCE_COL_INCOME_START,
        SOURCE_COL_INCOME_END};

        sourceSheetIncomeList.add(SOURCE_SHEET_INCOME);
        sourceSheetIncomeList.add(SOURCE_ROW_INCOME_START);
        sourceSheetIncomeList.add(SOURCE_ROW_INCOME_END);
        sourceSheetIncomeList.add(SOURCE_COL_INCOME_START);
        sourceSheetIncomeList.add(SOURCE_COL_INCOME_END);

        sourceSheetIncomeMap.put("SOURCE_SHEET",SOURCE_SHEET_INCOME);
        sourceSheetIncomeMap.put("SOURCE_ROW_START",SOURCE_ROW_INCOME_START);
        sourceSheetIncomeMap.put("SOURCE_ROW_END", SOURCE_ROW_INCOME_END);
        sourceSheetIncomeMap.put("SOURCE_COL_START", SOURCE_COL_INCOME_START);
        sourceSheetIncomeMap.put("SOURCE_COL_END", SOURCE_COL_INCOME_END);

        destSheetIncomeMap.put("DEST_SHEET",DEST_SHEET_INCOME);
        destSheetIncomeMap.put("DEST_ROW_START",DEST_ROW_INCOME_START);
        destSheetIncomeMap.put("DEST_ROW_END", DEST_ROW_INCOME_END);
        destSheetIncomeMap.put("DEST_COL_START", DEST_COL_INCOME_START);
        destSheetIncomeMap.put("DEST_COL_END", DEST_COL_INCOME_END);

        mapTypes.put("sourceIncome", sourceSheetIncomeMap);
        mapTypes.put("destIncome", destSheetIncomeMap);

        /*sourceNetIncomeAfterNCMIArray = new Integer[]{
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
        destIncomeArrays.add(destNetIncomeAfterNCMIArray);*/


    }

    public static void copyData() throws IOException {
        intializeData();
        Workbook destinationWorkbook = getDestinationWorkbook();
        for(String statementType : statementTypes){
            copyRows(getSourceWorkbook(statementType), getDestinationWorkbook(),
                    mapTypes.get("source" + statementType), mapTypes.get("dest" + statementType)  );
        }

    }

    public static void copyRows(Workbook sourceWorkbook, Workbook destinationWorkbook,
                                Map<String,Integer> sourceMap, Map<String,Integer>  destMap) throws IOException {

        //Iterator<Integer[]> sourceItr = sourceIncomeArrays.iterator();
        //Iterator<Integer[]> destItr = destIncomeArrays.iterator();

        int counter = sourceMap.get("SOURCE_ROW_START");
        int stop = sourceMap.get("SOURCE_ROW_END");


        //while(sourceItr.hasNext() && destItr.hasNext()) {
            /*Integer[] sourceArray = (Integer[]) sourceItr.next();
            Integer[] destArray = (Integer[]) destItr.next();
            Sheet sourceSheet = sourceWorkbook.getSheetAt(sourceArray[0]);
            Sheet destinationSheet = destinationWorkbook.getSheetAt(destArray[0]);*/
            Sheet sourceSheet = sourceWorkbook.getSheetAt(sourceMap.get("SOURCE_SHEET"));
            Sheet destinationSheet = destinationWorkbook.getSheetAt(sourceMap.get("DEST_SHEET"));
            ExcelRowCopy erc = new ExcelRowCopy( sourceSheet, destinationSheet);

       //while(counter <= stop) {
            CellRangeAddress sourceCellRangeAddress =
                    new CellRangeAddress(sourceMap.get("SOURCE_ROW_START"), sourceMap.get("SOURCE_ROW_END"),
                            sourceMap.get("SOURCE_COL_START"), sourceMap.get("SOURCE_COL_START"));
            CellRangeAddress destinationCellRangeAddress =
                    new CellRangeAddress(sourceMap.get("DEST_ROW_START"),destMap.get("DEST_ROW_END"),
                            destMap.get("DEST_COL_START"), destMap.get("DEST_COL_START"));
            erc.copyRange(sourceCellRangeAddress, destinationCellRangeAddress);
        //}


        try (FileOutputStream outputStream = new FileOutputStream(destinationFilePath)) {
            destinationWorkbook.write(outputStream);
        }

        System.out.println("Row copied successfully!");


    }

    public static XSSFWorkbook getSourceWorkbook(String statementType) {
        String sourceFilePath  = sourceBaseFilePath.replace("filname", statementType + "*");
        try (FileInputStream sourceFile = new FileInputStream(sourceFilePath)) {
            return  new XSSFWorkbook(sourceFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to Retrieve Source Workbook");

        }

        return null;
    }

    public static Workbook getDestinationWorkbook() {
        try (FileInputStream sourceFile = new FileInputStream(destinationFilePath)) {
             return new XSSFWorkbook(sourceFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to Retrieve Destination Workbook");

        }

        return null;
    }



    @Override
    public void copyRange(CellRangeAddress tilePatternRange, CellRangeAddress tileDestRange) {
        super.copyRange(tilePatternRange, tileDestRange, true,true);
    }

    @Override
    protected void adjustCellReferencesInsideFormula(Cell cell, Sheet destSheet, int deltaX, int deltaY) {

    }

}
