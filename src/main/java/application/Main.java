package application;

import java.io.IOException;

import selenium.WebActions;
import user_input.UserInput;


public class Main {


    public static void main(String[] args) throws IOException {

        //WebActions webActions = new WebActions();
        //webActions.morningstarLogin();
        UserInput ui = new UserInput();
        ui.promptUser();

       /* WebActions webActions = new WebActions();
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
        WorkbookManagement.writeToDestinationWorkbook(destinationWorkbook, "Historical Data");*/

    }


}
