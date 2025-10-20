package controllers;

import models.objects.Parameters;
import models.services.ImageService;
import views.imageframe.ImageFrame;
import views.mainframe.MainFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Controller {

    private static final Controller instance = new Controller();

    // Model

    private Parameters parameters;

    private ImageService imageService;

    // View

    private MainFrame mainFrame;

    private ImageFrame imageFrame;

    private Controller() {
        parameters = new Parameters();
        imageService = new ImageService();
    }


    /**
     * Retrieve the instance of the controller
     *
     * @return Instance of the controller
     */
    public static Controller getInstance() {
        return instance;
    }


    // GETTERS AND SETTERS

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public ImageService getImageService() {
        return imageService;
    }

    // METHODS

    public void openImageFrame() {
        if (imageFrame == null) {
            imageFrame = new ImageFrame();
            imageFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    imageFrame = null;
                }
            });
        }
    }

    public void run() {
        mainFrame = new MainFrame();
    }
}
