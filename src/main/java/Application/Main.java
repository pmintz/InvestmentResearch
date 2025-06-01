package Application;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import EXCEL.WorkbookManagement;
import Selenium.WebActions;


public class Main {


    public static void main(String[] args) throws IOException {

        /*WebActions webActions = new WebActions();
        webActions.morningstarLogin();
        UserInput ui = new UserInput();
        ui.promptUser();*/

        WebActions webActions = new WebActions();
        webActions.yahooHomePage();
        webActions.clickYahooFinancialLink();
        webActions.setTicker("XOM");
        webActions.enterTickerYahooFinancialSearchBox();
        webActions.clickYahooHistoricalLink();
        webActions.scrollToHistoricalDropDown();
        webActions.clickDailyDropDown();
        webActions.selectMonthly();
        webActions.clickDateRangeDropDown();
        webActions.clickMaxButton();
        List<List<String>> objTable = webActions.readTable();
        WorkbookManagement wbm = new WorkbookManagement();
        wbm.insertData(objTable);

    }


}
