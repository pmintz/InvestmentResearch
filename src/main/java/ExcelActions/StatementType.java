package ExcelActions;

import java.util.HashMap;
import java.util.Map;

public class StatementType {

    static final int SOURCE_SHEET_INCOME = 0;
    static final int SOURCE_ROW_INCOME_START = 0;
    static final int SOURCE_ROW_INCOME_END = 100;
    static final int SOURCE_COL_INCOME_START = 0;
    static final int SOURCE_COL_INCOME_END = 12;

    static final int DEST_SHEET_INCOME = 1;
    static final int DEST_ROW_INCOME_START = 0;
    static final int DEST_ROW_INCOME_END = 100;
    static final int DEST_COL_INCOME_START = 0;
    static final int DEST_COL_INCOME_END = 12;

    static final int SOURCE_SHEET_BALANCE_SHEET = 0;
    static final int SOURCE_ROW_BALANCE_SHEET_START = 0;
    static final int SOURCE_ROW_BALANCE_SHEET_END = 150;
    static final int SOURCE_COL_BALANCE_SHEET_START = 0;
    static final int SOURCE_COL_BALANCE_SHEET_END = 12;

    static final int DEST_SHEET_BALANCE_SHEET = 2;
    static final int DEST_ROW_BALANCE_SHEET_START = 0;
    static final int DEST_ROW_BALANCE_SHEET_END = 150;
    static final int DEST_COL_BALANCE_SHEET_START = 0;
    static final int DEST_COL_BALANCE_SHEET_END = 12;

    static final int SOURCE_SHEET_CASH_FLOW = 0;
    static final int SOURCE_ROW_CASH_FLOW_START = 0;
    static final int SOURCE_ROW_CASH_FLOW_END = 150;
    static final int SOURCE_COL_CASH_FLOW_START = 0;
    static final int SOURCE_COL_CASH_FLOW_END = 12;

    static final int DEST_SHEET_CASH_FLOW = 3;
    static final int DEST_ROW_CASH_FLOW_START = 0;
    static final int DEST_ROW_CASH_FLOW_END = 150;
    static final int DEST_COL_CASH_FLOW_START = 0;
    static final int DEST_COL_CASH_FLOW_END = 12;

    static Map<String, String> filePaths = new HashMap<>();
    static Map<String, Integer> incomeCells = new HashMap<>();
    static Map<String, Integer> balanceSheetCells = new HashMap<>();
    static Map<String, Integer> cashFlowCells = new HashMap<>();
    static Map<String,Map<String,Integer>> cellRanges = new HashMap<String, Map<String, Integer>>();
    
    public static void intializeExcelData(){

        incomeCells.put("SOURCE_SHEET",SOURCE_SHEET_INCOME);
        incomeCells.put("SOURCE_ROW_START",SOURCE_ROW_INCOME_START);
        incomeCells.put("SOURCE_ROW_END", SOURCE_ROW_INCOME_END);
        incomeCells.put("SOURCE_COL_START", SOURCE_COL_INCOME_START);
        incomeCells.put("SOURCE_COL_END", SOURCE_COL_INCOME_END);

        incomeCells.put("DEST_SHEET",DEST_SHEET_INCOME);
        incomeCells.put("DEST_ROW_START",DEST_ROW_INCOME_START);
        incomeCells.put("DEST_ROW_END", DEST_ROW_INCOME_END);
        incomeCells.put("DEST_COL_START", DEST_COL_INCOME_START);
        incomeCells.put("DEST_COL_END", DEST_COL_INCOME_END);

        cellRanges.put("Income", incomeCells);

        balanceSheetCells.put("SOURCE_SHEET",SOURCE_SHEET_BALANCE_SHEET);
        balanceSheetCells.put("SOURCE_ROW_START",SOURCE_ROW_BALANCE_SHEET_START);
        balanceSheetCells.put("SOURCE_ROW_END", SOURCE_ROW_BALANCE_SHEET_END);
        balanceSheetCells.put("SOURCE_COL_START", SOURCE_COL_BALANCE_SHEET_START);
        balanceSheetCells.put("SOURCE_COL_END", SOURCE_COL_BALANCE_SHEET_END);

        balanceSheetCells.put("DEST_SHEET",DEST_SHEET_BALANCE_SHEET);
        balanceSheetCells.put("DEST_ROW_START",DEST_ROW_BALANCE_SHEET_START);
        balanceSheetCells.put("DEST_ROW_END", DEST_ROW_BALANCE_SHEET_END);
        balanceSheetCells.put("DEST_COL_START", DEST_COL_BALANCE_SHEET_START);
        balanceSheetCells.put("DEST_COL_END", DEST_COL_BALANCE_SHEET_END);

        cellRanges.put("Balance", balanceSheetCells);

        cashFlowCells.put("SOURCE_SHEET",SOURCE_SHEET_CASH_FLOW);
        cashFlowCells.put("SOURCE_ROW_START",SOURCE_ROW_CASH_FLOW_START);
        cashFlowCells.put("SOURCE_ROW_END", SOURCE_ROW_CASH_FLOW_END);
        cashFlowCells.put("SOURCE_COL_START", SOURCE_COL_CASH_FLOW_START);
        cashFlowCells.put("SOURCE_COL_END", SOURCE_COL_CASH_FLOW_END);

        cashFlowCells.put("DEST_SHEET",DEST_SHEET_CASH_FLOW);
        cashFlowCells.put("DEST_ROW_START",DEST_ROW_CASH_FLOW_START);
        cashFlowCells.put("DEST_ROW_END", DEST_ROW_CASH_FLOW_END);
        cashFlowCells.put("DEST_COL_START", DEST_COL_CASH_FLOW_START);
        cashFlowCells.put("DEST_COL_END", DEST_COL_CASH_FLOW_END);

        cellRanges.put("Cash", cashFlowCells);

        filePaths.put("Income", "Income Statement_Annual_As Originally Reported.xls");
        filePaths.put("Balance", "Balance Sheet_Annual_As Originally Reported.xls");
        filePaths.put("Cash", "Cash Flow_Annual_As Originally Reported.xls");

    }
}
