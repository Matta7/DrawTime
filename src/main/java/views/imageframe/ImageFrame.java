package views.imageframe;

import controllers.Controller;
import models.constants.files.ImageFileConstants;
import models.services.ImageLoaderService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Frame containing image viewer panel and control bar panel
 */
public class ImageFrame extends JFrame {

    public ImageFrame() {
        initialize();
    }

    /**
     * Initialize component
     */
    private void initialize() {
        setTitle("DrawTime");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        add(new ImageViewerPanel(Controller.getInstance().getImageService().getCurrentImage()), BorderLayout.CENTER);
        add(new ControlBarPanel(), BorderLayout.SOUTH);

        // Set icon
        ImageLoaderService imageLoaderService = new ImageLoaderService();
        try {
            setIconImage(imageLoaderService.loadImage(ImageFileConstants.ICON));
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
