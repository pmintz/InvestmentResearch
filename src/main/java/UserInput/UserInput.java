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

                if (readString.equalsIgnoreCase("close")) {
                    System.out.println("Exiting program...");
                    break;
                } else {
                    System.out.println("Gathering data");
                    sourceData = new SourceData();
                    sourceData.enterTickerSymbol(readString);
                }

                //if(sourceData.checkForResults()){
                if(sourceData.clickUSSecuritiesLink() ){
                    sourceData.retrieveDataFromResultsPage();
                }else{
                    System.out.println("No results");
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
