package ExcelActions;

import java.util.HashMap;
import java.util.Map;

public class StatementType {

    static final int SOURCE_SHEET_INCOME = 0;
    static final int SOURCE_ROW_INCOME_START = 0;
    static final int SOURCE_ROW_INCOME_END = 80;
    static final int SOURCE_COL_INCOME_START = 0;
    static final int SOURCE_COL_INCOME_END = 12;

    static final int DEST_SHEET_INCOME = 2;
    static final int DEST_ROW_INCOME_START = 0;
    static final int DEST_ROW_INCOME_END = 80;
    static final int DEST_COL_INCOME_START = 0;
    static final int DEST_COL_INCOME_END = 12;

    static final int SOURCE_SHEET_BALANCE_SHEET = 0;
    static final int SOURCE_ROW_BALANCE_SHEET_START = 0;
    static final int SOURCE_ROW_BALANCE_SHEET_END = 130;
    static final int SOURCE_COL_BALANCE_SHEET_START = 0;
    static final int SOURCE_COL_BALANCE_SHEET_END = 12;

    static final int DEST_SHEET_BALANCE_SHEET = 3;
    static final int DEST_ROW_BALANCE_SHEET_START = 0;
    static final int DEST_ROW_BALANCE_SHEET_END = 130;
    static final int DEST_COL_BALANCE_SHEET_START = 0;
    static final int DEST_COL_BALANCE_SHEET_END = 12;

    static final int SOURCE_SHEET_CASH_FLOW = 0;
    static final int SOURCE_ROW_CASH_FLOW_START = 0;
    static final int SOURCE_ROW_CASH_FLOW_END = 80;
    static final int SOURCE_COL_CASH_FLOW_START = 0;
    static final int SOURCE_COL_CASH_FLOW_END = 12;

    static final int DEST_SHEET_CASH_FLOW = 4;
    static final int DEST_ROW_CASH_FLOW_START = 0;
    static final int DEST_ROW_CASH_FLOW_END = 80;
    static final int DEST_COL_CASH_FLOW_START = 0;
    static final int DEST_COL_CASH_FLOW_END = 12;

    static Map<String, String> filePaths = new HashMap<>();
    static Map<String, Integer> cells = new HashMap<>();
    static Map<String,Map<String,Integer>> cellRanges = new HashMap<String, Map<String, Integer>>();
    
    public static void intializeExcelData(){

        cells.put("SOURCE_SHEET",SOURCE_SHEET_INCOME);
        cells.put("SOURCE_ROW_START",SOURCE_ROW_INCOME_START);
        cells.put("SOURCE_ROW_END", SOURCE_ROW_INCOME_END);
        cells.put("SOURCE_COL_START", SOURCE_COL_INCOME_START);
        cells.put("SOURCE_COL_END", SOURCE_COL_INCOME_END);

        cells.put("DEST_SHEET",DEST_SHEET_INCOME);
        cells.put("DEST_ROW_START",DEST_ROW_INCOME_START);
        cells.put("DEST_ROW_END", DEST_ROW_INCOME_END);
        cells.put("DEST_COL_START", DEST_COL_INCOME_START);
        cells.put("DEST_COL_END", DEST_COL_INCOME_END);

        cellRanges.put("Income", cells);

        cells.put("SOURCE_SHEET",SOURCE_SHEET_BALANCE_SHEET);
        cells.put("SOURCE_ROW_START",SOURCE_ROW_BALANCE_SHEET_START);
        cells.put("SOURCE_ROW_END", SOURCE_ROW_BALANCE_SHEET_END);
        cells.put("SOURCE_COL_START", SOURCE_COL_BALANCE_SHEET_START);
        cells.put("SOURCE_COL_END", SOURCE_COL_BALANCE_SHEET_END);

        cells.put("DEST_SHEET",DEST_SHEET_BALANCE_SHEET);
        cells.put("DEST_ROW_START",DEST_ROW_BALANCE_SHEET_START);
        cells.put("DEST_ROW_END", DEST_ROW_BALANCE_SHEET_END);
        cells.put("DEST_COL_START", DEST_COL_BALANCE_SHEET_START);
        cells.put("DEST_COL_END", DEST_COL_BALANCE_SHEET_END);

        cellRanges.put("Balance", cells);

        cells.put("SOURCE_SHEET",SOURCE_SHEET_CASH_FLOW);
        cells.put("SOURCE_ROW_START",SOURCE_ROW_CASH_FLOW_START);
        cells.put("SOURCE_ROW_END", SOURCE_ROW_CASH_FLOW_END);
        cells.put("SOURCE_COL_START", SOURCE_COL_CASH_FLOW_START);
        cells.put("SOURCE_COL_END", SOURCE_COL_CASH_FLOW_END);

        cells.put("DEST_SHEET",DEST_SHEET_CASH_FLOW);
        cells.put("DEST_ROW_START",DEST_ROW_CASH_FLOW_START);
        cells.put("DEST_ROW_END", DEST_ROW_CASH_FLOW_END);
        cells.put("DEST_COL_START", DEST_COL_CASH_FLOW_START);
        cells.put("DEST_COL_END", DEST_COL_CASH_FLOW_END);

        cellRanges.put("Cash", cells);

        filePaths.put("Income", "Income Statement_Annual_As Originally Reported.xls");
        filePaths.put("Balance", "Balance Sheet_Annual_As Originally Reported.xls");
        filePaths.put("Cash", "Cash Flow_Annual_As Originally Reported.xls");

    }
}
