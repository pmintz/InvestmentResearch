package UserInput;

import Selenium.SourceData;

import java.util.Scanner;

public class UserInput {

    String readString;

    SourceData sourceData;

    public void promptUser() {

        System.out.print("Enter ticker symbol and then press \"Enter\".  Type \"close\" to exit: ");

        try (Scanner scanner = new Scanner(System.in);) {

            while (scanner.hasNextLine()) {

                readString = scanner.nextLine();
                sourceData = new SourceData();

                if (readString.equalsIgnoreCase("close")) {
                    System.out.println("Exiting program...");
                    sourceData.closeBrowser();
                    break;
                } else {
                    System.out.println("Gathering data");
                    sourceData.setTicker(readString);
                    sourceData.start();
                }

                sourceData.join();

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
