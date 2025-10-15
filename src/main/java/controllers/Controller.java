package controllers;

import models.objects.Parameters;
import views.mainframe.MainFrame;

public class Controller {

    private static final Controller instance = new Controller();

    private MainFrame mainFrame;

    private Parameters parameters;

    private Controller() {
        parameters = new Parameters();
    }

    public static Controller getInstance() {
        return instance;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public void run() {
        mainFrame = new MainFrame();
    }
}
