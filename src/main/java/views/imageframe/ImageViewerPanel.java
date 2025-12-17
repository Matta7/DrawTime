package views.imageframe;

import controllers.Controller;
import models.services.ImageService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ImageViewerPanel extends JPanel {

    private final ImageService imageService;
    private BufferedImage image;

    private double scale = 1.0;

    double offsetX = 0;
    double offsetY = 0;

    private Point lastDrag;

    private static final double ZOOM_STEP = 0.1;


    public ImageViewerPanel(BufferedImage image) {
        this.image = image;
        initialize();

        imageService = Controller.getInstance().getImageService();
        imageService.addImageChangedAction(() -> updateImage(imageService.getCurrentImageAsBufferedImage()));
    }

    private void initialize() {
        // Zoom management
        addMouseWheelListener(e -> {
            double oldScale = scale;

            if (e.getPreciseWheelRotation() < 0) {
                scale *= (1 + ZOOM_STEP);
            } else {
                scale *= (1 - ZOOM_STEP);
            }

            double mouseX = e.getX();
            double mouseY = e.getY();
            offsetX = mouseX - (mouseX - offsetX) * (scale / oldScale);
            offsetY = mouseY - (mouseY - offsetY) * (scale / oldScale);

            repaint();
        });

        // Click to enable drag
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastDrag = e.getPoint();
            }
        });

        // Drag to move
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastDrag != null) {
                    double dx = e.getX() - lastDrag.getX();
                    double dy = e.getY() - lastDrag.getY();

                    offsetX += dx;
                    offsetY += dy;

                    lastDrag = e.getPoint();

                    repaint();
                }
            }
        });
    }

    public void updateImage(BufferedImage image) {
        this.image = image;

        scale = 1.0;
        offsetX = 0;
        offsetY = 0;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(offsetX, offsetY);
        g2.scale(scale, scale);

        int x = Math.round((float) (getWidth() - image.getWidth()) / 2);
        int y = Math.round((float) (getHeight() - image.getHeight()) / 2);
        g2.drawImage(image, x, y, null);
    }
}
