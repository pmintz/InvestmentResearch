package user_input;

import excel.WorkbookManagement;
import selenium.WebActions;
import org.apache.poi.ss.usermodel.Workbook;
import utility.Utility;

import java.util.List;
import java.util.Scanner;

public class UserInput extends Thread {

    String readString;

    WorkbookManagement workbookManagement;
    WebActions webActions;

    public void promptUser() {

        System.out.print("Enter ticker symbol and then press \"Enter\".  Type \"close\" to exit: ");

        try (Scanner scanner = new Scanner(System.in);) {

            while (scanner.hasNextLine()) {

                readString = scanner.nextLine();
                webActions = new WebActions();

                if (readString.equalsIgnoreCase("close")) {
                    System.out.println("Exiting program...");
                    webActions.closeBrowser();
                    webActions.closeBrowser();
                    break;
                } else {
                    System.out.println("Gathering data");
                    webActions.setTicker(readString);
                    start();
                }

                join();

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


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
