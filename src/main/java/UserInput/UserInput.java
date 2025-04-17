package UserInput;

import Selenium.GetSourceData;

import java.util.Scanner;

public class UserInput {

    String readString;

    GetSourceData getSourceData;

    public void promptUser() {

        System.out.print("Enter ticker symbol and then press \"Enter\".  Type \"close\" to exit: ");

        try (Scanner scanner = new Scanner(System.in);) {

            while (scanner.hasNextLine()) {

                readString = scanner.nextLine();

                if (readString.equalsIgnoreCase("close")) {
                    System.out.println("Exiting program...");
                    break;
                } else {
                    System.out.println("Gathering data");
                    getSourceData = new GetSourceData();
                    getSourceData.enterTickerSymbol(readString);
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
