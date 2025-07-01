package application;

import user_input.UserInput;

import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException {
        UserInput ui = new UserInput();
        ui.promptUser();
    }


}
