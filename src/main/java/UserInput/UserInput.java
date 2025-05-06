package UserInput;

import ExcelActions.WorkbookManagement;
import Selenium.WebActions;

import java.util.Scanner;

public class UserInput extends Thread {

    String readString;

    WorkbookManagement workbookManagement = new WorkbookManagement();
    WebActions webActions = new WebActions();

    public void promptUser() {

        System.out.print("Enter ticker symbol and then press \"Enter\".  Type \"close\" to exit: ");

        try (Scanner scanner = new Scanner(System.in);) {

            while (scanner.hasNextLine()) {

                readString = scanner.nextLine();
                webActions = new WebActions();

                if (readString.equalsIgnoreCase("close")) {
                    System.out.println("Exiting program...");
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
            webActions.enterTickerSymbol();

            if (webActions.checkForResults()) {
                webActions.downloadAllFinancialStatements();
                workbookManagement.copyData();
                workbookManagement.removeFinancialStatementsFromDownloadFolder();
                webActions.pageRefresh();
            } else {
                System.out.println("No results");
                webActions.pageRefresh();
            }

        } catch (Exception e) {
            System.out.println("SourceData run method has thrown an error");
            System.out.println(e.getMessage());
        }

        System.out.print("Enter ticker symbol and then press \"Enter\".  Type \"close\" to exit: ");

    }


}
