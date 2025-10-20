package controllers;

import models.objects.CountdownTimer;
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

    private final CountdownTimer timer;

    private final ImageService imageService;


    // View

    private ImageFrame imageFrame;

    private Controller() {
        parameters = new Parameters();
        timer = new CountdownTimer();
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

    public CountdownTimer getTimer() {
        return timer;
    }

    public ImageService getImageService() {
        return imageService;
    }


    // METHODS

    public void openImageFrame() {
        if (imageFrame == null) {
            timer.setTime(parameters.getTimePerImage() * 60);

            imageFrame = new ImageFrame();
            imageFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    imageFrame = null;
                }
            });

            timer.start();
        }
    }

    public void run() {
        new MainFrame();
    }
}
