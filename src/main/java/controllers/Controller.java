package controllers;

import models.commands.CommandInvoker;
import models.commands.concrete.CheckVersionCommand;
import models.commands.concrete.NextImageCommand;
import models.objects.CountdownTimer;
import models.objects.Parameters;
import models.services.ImageLoaderService;
import models.services.ImageService;
import views.components.ProgressBarDialog;
import views.imageframe.ImageFrame;
import views.mainframe.MainFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static models.constants.project.GitHubURLConstants.GITHUB_DRAWTIME_LATEST_RELEASE_URL;

public class Controller {

    // Singleton pattern

    private static final Controller instance = new Controller();

    private Controller() {
        parameters = new Parameters();
        timer = new CountdownTimer();
        imageService = new ImageService();

        // When timer has timeout, go to the next image
        timer.addTimeoutAction(() -> CommandInvoker.getInstance().executeCommand(new NextImageCommand()));

        // When image service is ready, open image frame and start timer
        imageService.addImageServiceReadyAction(this::onImageServiceReady);
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

    private MainFrame mainFrame;

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
        mainFrame = new MainFrame();

        CommandInvoker commandInvoker = CommandInvoker.getInstance();
        commandInvoker.executeCommand(new CheckVersionCommand());
    }

    /**
     * Start timer and open image frame
     */
    public void startDrawing() {
        if (imageFrame == null) {
            // Initialize the timer
            timer.setTime(parameters.getTimePerImage() * 60);

            // Load images with a thread
            new Thread(() -> {
                // Open progress bar
                ProgressBarDialog progressBarDialog = new ProgressBarDialog(mainFrame);

                // Load images
                ImageLoaderService imageLoaderService = new ImageLoaderService();
                List<String> images = imageLoaderService.retrieveAllImagesFromDirectory(parameters.getFilePath(), true);

                // Dispose progress bar
                progressBarDialog.dispose();

                if (!images.isEmpty()) {
                    imageService.setImages(images);
                }
                else {
                    mainFrame.showErrorMessageDialog("No images found. Try choosing a directory that contains images.", "No images found");
                }
            }).start();
        }
    }

    /**
     * Open a dialog that prevents user there is a newer version available
     */
    public void openNewVersionDialog() {
        mainFrame.showCustomOptionDialog(
                "A new version is available on GitHub.",
                "New version available",
                new String[]{"Open in browser", "Close"},
                new Runnable[]{
                        () -> openWebPage(GITHUB_DRAWTIME_LATEST_RELEASE_URL),
                        () -> {}
                });
    }

    /**
     * Open web page with desktop browser
     *
     * @param url URL to open
     * @return True if page has been opened, false otherwise
     */
    public boolean openWebPage(String url) {
        try {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(URI.create(url));
                return true;
            }
        } catch (IOException _) {
            // Nothing to do
        }
        return false;
    }

    // EVENT HANDLER METHODS

    /**
     * Called when image service is ready
     */
    public void onImageServiceReady() {
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
    }
}
