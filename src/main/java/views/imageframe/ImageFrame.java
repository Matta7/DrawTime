package views.imageframe;

import controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class ImageFrame extends JFrame {

    public ImageFrame() {
        initialize();
    }

    private void initialize() {
        setTitle("DrawTime");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        add(new ImageViewerPanel(Controller.getInstance().getImageService().getCurrentImage()), BorderLayout.CENTER);

        pack();
        validate();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
