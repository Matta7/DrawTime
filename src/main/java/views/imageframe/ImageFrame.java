package views.imageframe;

import controllers.Controller;

import static models.constants.files.ImageFileConstants.ICON;

import models.services.ImageLoaderService;
import views.components.swing_components.DTFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Frame containing image viewer panel and control bar panel
 */
public class ImageFrame extends DTFrame {

    public ImageFrame() {
        initialize();
    }

    @Override
    protected void initialize() {
        setTitle("DrawTime");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        add(new ImageViewerPanel(Controller.getInstance().getImageService().getCurrentImageAsBufferedImage()), BorderLayout.CENTER);
        add(new ControlBarPanel(), BorderLayout.SOUTH);

        // Set icon
        ImageLoaderService imageLoaderService = new ImageLoaderService();
        try {
            setIconImage(imageLoaderService.loadImage(ICON));
        } catch (IOException _) {
            // Nothing to do
        }

        pack();
        validate();
        setVisible(true);

        // Center and set full screen
        setLocationRelativeTo(null);
        setExtendedState(Frame.MAXIMIZED_BOTH);

    }
}
