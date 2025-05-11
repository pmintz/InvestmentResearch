package Application;

import java.io.IOException;

import Selenium.WebActions;
import UserInput.UserInput;


public class Main {


    public static void main(String[] args) throws IOException {

        WebActions webActions = new WebActions();
        webActions.morningstarLogin();
        UserInput ui = new UserInput();
        ui.promptUser();

        /*WebActions webActions = new WebActions();
        webActions.yahooHomePage();
        webActions.clickYahooFinancialLink();
        webActions.setTicker("XOM");
        webActions.enterTickerYahooFinancialSearchBox();*/

    }


}
