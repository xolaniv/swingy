package za.wtc.swingy;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import za.wtc.swingy.view.console.ConsoleView;
import za.wtc.swingy.view.gui.GameWindow;

import static za.wtc.swingy.StaticGlobal.*;


public class Main {

    public static ValidatorFactory validatorFactory;

    public static void main(String[] args) {
        getMode(args);
    }
    public static void getMode(String[] input){
        try {
            if (input[0].equals("console")) {
                validatorFactory = Validation.buildDefaultValidatorFactory();
                CONSOLE_MODE = true;
                ConsoleView.run();
            } else if (input[0].equals("gui")) {
                validatorFactory = Validation.buildDefaultValidatorFactory();
                CONSOLE_MODE = false;
                GameWindow.run();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}