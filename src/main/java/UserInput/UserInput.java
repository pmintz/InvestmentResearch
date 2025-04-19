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

                    //allow SourceData to extend Thread
                    //override run method
                    //put remaining code within thread.run()
                    sourceData = new SourceData();
                    sourceData.enterTickerSymbol(readString);
                }

                //if(sourceData.checkForResults()){
                if(sourceData.checkForResults(readString) ){
                    sourceData.retrieveDataFromResultsPage();
                    sourceData.pageRefresh();
                }else{
                    System.out.println("No results");
                    sourceData.pageRefresh();
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
