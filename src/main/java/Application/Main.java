package Application;

import ExcelActions.ExcelRowCopy;
import Selenium.SourceData;
import UserInput.UserInput;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        SourceData sourceData = new SourceData();
        sourceData.login();
        UserInput ui = new UserInput();
        ui.promptUser();

    }

}
