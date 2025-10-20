package views.imageframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ImageViewerPanel extends JPanel {

    private final BufferedImage image;

    private double scale = 1.0;

    double offsetX = 0;
    double offsetY = 0;

    private Point lastDrag;

    private final double ZOOM_STEP = 0.1;


    public ImageViewerPanel(BufferedImage image) {
        this.image = image;
        initialize();
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

            //clampOffsets();
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

                    //clampOffsets();

                    repaint();

                }
            }
        });
    }

    /**
     * Prevents the image to pass the borders
     */
    private void clampOffsets() {
        if (image == null) return;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        double imgWidth = image.getWidth() * scale;
        double imgHeight = image.getHeight() * scale;

        if (imgWidth <= panelWidth)
            offsetX = (panelWidth - imgWidth) / 2;
        else
            offsetX = Math.min(offsetX, 0);
        offsetX = Math.max(offsetX, panelWidth - imgWidth);

        if (imgHeight <= panelHeight)
            offsetY = (panelHeight - imgHeight) / 2;
        else
            offsetY = Math.min(offsetY, 0);
        offsetY = Math.max(offsetY, panelHeight - imgHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(offsetX, offsetY);
        g2.scale(scale, scale);
        g2.drawImage(image, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize() {
        if (image == null) return new Dimension(400, 300);
        return new Dimension(image.getWidth(), image.getHeight());
    }
}
