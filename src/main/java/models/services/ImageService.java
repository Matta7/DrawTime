package models.services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Deque;

public class ImageService {
    private Deque<String> images;

    private String currentImage;

    public ImageService() {

    }

    /**
     * Load all images in the given repertory
     *
     * @param repertoryPath Repertory path containing all images
     */
    public void loadImagesFromRepertory(String repertoryPath) {

    }

    public BufferedImage getCurrentImage() {
        try {
            return ImageIO.read(new File(""));
        } catch (IOException e) {
            // Test
        }

        return null;
    }
}
