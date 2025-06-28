package Application;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import EXCEL.StatementType;
import EXCEL.WorkbookManagement;
import Selenium.WebActions;
import org.apache.poi.ss.usermodel.Workbook;
import utility.Utility;


public class Main {


    public static void main(String[] args) throws IOException {

        /*WebActions webActions = new WebActions();
        webActions.morningstarLogin();
        UserInput ui = new UserInput();
        ui.promptUser();*/

        WebActions webActions = new WebActions();
        webActions.yahooHomePage();
        webActions.clickYahooFinancialLink();
        webActions.setTicker("GOOGL");
        webActions.enterTickerYahooFinancialSearchBox();
        webActions.clickYahooHistoricalLink();
        webActions.scrollToHistoricalDropDown();
        webActions.clickDailyDropDown();
        webActions.selectMonthly();
        webActions.clickDateRangeDropDown();
        webActions.clickMaxButton();
        List<List<String>> objTable = webActions.readTable();
        Utility.removeDividendRow(objTable);
        WorkbookManagement wbm = new WorkbookManagement();
        Workbook destinationWorkbook = WorkbookManagement.getDestinationWorkbook();
        wbm.insertData(objTable, destinationWorkbook);
        WorkbookManagement.writeToDestinationWorkbook(destinationWorkbook, "Historical Data");

    }


}
