package controllers;

import models.objects.CountdownTimer;
import models.objects.Parameters;
import models.services.ImageLoaderService;
import models.services.ImageService;
import views.imageframe.ImageFrame;
import views.mainframe.MainFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class Controller {

    // Singleton pattern

    private static final Controller instance = new Controller();

    private Controller() {
        parameters = new Parameters();
        timer = new CountdownTimer();
        imageService = new ImageService();

        // When timer has timeout, go to the next image
        timer.addTimeoutAction(imageService::nextImage);
    }

    /**
     * Retrieve the instance of the controller
     *
     * @return Instance of the controller
     */
    public static Controller getInstance() {
        return instance;
    }


    // PROPERTIES

    // Model

    private Parameters parameters;

    private final CountdownTimer timer;

    private final ImageService imageService;

    // View

    private ImageFrame imageFrame;


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


    // PUBLIC METHODS

    /**
     * Run application
     */
    public void run() {
        new MainFrame();
    }

    /**
     * Start timer and open image frame
     */
    public void startDrawing() {
        if (imageFrame == null) {
            // Initialize the timer
            timer.setTime(parameters.getTimePerImage() * 60);

            // Load images
            ImageLoaderService imageLoaderService = new ImageLoaderService();
            List<String> images = imageLoaderService.retrieveAllImagesFromDirectory(parameters.getFilePath(), true);

            if (!images.isEmpty()) {
                imageService.setImages(images);

                // Open image frame
                imageFrame = new ImageFrame();
                imageFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        imageFrame = null;
                    }
                });

                // Start timer
                timer.start();
            } else {
                // TODO : Open dialog
            }
        }
    }
}
