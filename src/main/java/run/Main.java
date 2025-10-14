package run;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import controllers.Controller;


public class Main {
    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        FlatDarculaLaf.setup();

        Controller.getInstance().run();
    }
}
