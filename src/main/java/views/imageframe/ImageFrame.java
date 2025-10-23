package views.imageframe;

import controllers.Controller;

import javax.swing.*;
import java.awt.*;

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

        pack();
        validate();
        setVisible(true);

        // Set full screen
        setExtendedState(Frame.MAXIMIZED_BOTH);

    }
}
