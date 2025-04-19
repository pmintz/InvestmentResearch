package Application;

import Selenium.SourceData;
import UserInput.UserInput;

public class Main {

    public static void main(String[] args) {

        SourceData sourceData = new SourceData();
        sourceData.login();
        UserInput ui = new UserInput();
        ui.promptUser();

    }

}
