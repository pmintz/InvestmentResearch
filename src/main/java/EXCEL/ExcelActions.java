package EXCEL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RangeCopier;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.util.Map;

public class ExcelActions extends RangeCopier {
    public ExcelActions(Sheet sheet) {
        super(sheet);
    }

    public ExcelActions(Sheet sourceSheet, Sheet destSheet) {
        super(sourceSheet, destSheet);
    }


    public static void copyRows(Workbook sourceWorkbook, Workbook destinationWorkbook,
                                Map<String, Integer> excelRange) throws IOException {

        Sheet sourceSheet = sourceWorkbook.getSheetAt(excelRange.get("SOURCE_SHEET"));
        Sheet destinationSheet = destinationWorkbook.getSheetAt(excelRange.get("DEST_SHEET"));

        ExcelActions erc = new ExcelActions(sourceSheet, destinationSheet);

        CellRangeAddress sourceCellRangeAddress =
                new CellRangeAddress(excelRange.get("SOURCE_ROW_START"), excelRange.get("SOURCE_ROW_END"),
                        excelRange.get("SOURCE_COL_START"), excelRange.get("SOURCE_COL_END"));
        CellRangeAddress destinationCellRangeAddress =
                new CellRangeAddress(excelRange.get("DEST_ROW_START"), excelRange.get("DEST_ROW_END"),
                        excelRange.get("DEST_COL_START"), excelRange.get("DEST_COL_END"));
        erc.copyRange(sourceCellRangeAddress, destinationCellRangeAddress);

    }

    @Override
    public void copyRange(CellRangeAddress tilePatternRange, CellRangeAddress tileDestRange) {
        super.copyRange(tilePatternRange, tileDestRange, true, true);
    }

    @Override
    protected void adjustCellReferencesInsideFormula(Cell cell, Sheet destSheet, int deltaX, int deltaY) {

    }

}
