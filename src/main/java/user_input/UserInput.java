package user_input;

import batch.Batch;
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

        webActions = new WebActions();

        System.out.print("Enter ticker symbol and then press \"Enter\".  Type \"close\" to exit: ");

        try (Scanner scanner = new Scanner(System.in);) {

            while (scanner.hasNextLine()) {

                readString = scanner.nextLine();

                if (readString.equalsIgnoreCase("close")) {
                    System.out.println("Exiting program...");
                    webActions.closeBrowser();
                    break;
                } else {
                    System.out.println("Gathering data");
                    webActions.setTicker(readString);
                    Thread thread = new Thread(new Batch(webActions));
                    thread.start();
                    thread.join();
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }




}
