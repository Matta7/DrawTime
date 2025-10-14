package controllers;

import views.mainframe.MainFrame;

public class Controller {

    private static final Controller instance = new Controller();

    private MainFrame mainFrame;

    private Controller() {
    }

    public static Controller getInstance() {
        return instance;
    }

    public void run() {
        mainFrame = new MainFrame();
    }
}
