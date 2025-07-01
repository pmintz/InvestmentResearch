package batch;

import excel.WorkbookManagement;
import org.apache.poi.ss.usermodel.Workbook;
import selenium.WebActions;
import utility.Utility;

import java.util.List;

public class Batch implements Runnable {
    WebActions webActions;
    WorkbookManagement workbookManagement;

    public Batch(WebActions webActions){
        this.webActions = webActions;
    }

    @Override
    public void run() {

        try {
            webActions.morningstarLogin();
            webActions.enterTickerSymbol();

            if (webActions.checkForResults()) {
                //Get Financial statements from Morningstar and insert them into excel spreadsheet
                webActions.downloadAllFinancialStatements();
                workbookManagement = new WorkbookManagement();
                workbookManagement.copyData();
                workbookManagement.removeFinancialStatementsFromDownloadFolder();
                //Get Historical prices from yahoo
                webActions.yahooHomePage();
                webActions.clickYahooFinancialLink();
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
                //webActions.pageRefresh();
            } else {
                System.out.println("No results");
                webActions.pageRefresh();
            }

        } catch (Exception e) {
            System.out.println(" run method has thrown an error");
            System.out.println(e.getMessage());
        }

        System.out.print("Enter ticker symbol and then press \"Enter\".  Type \"close\" to exit: ");

    }
}
