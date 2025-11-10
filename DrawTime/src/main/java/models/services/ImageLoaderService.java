package models.services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImageLoaderService {

    /**
     * Load an image from its path
     *
     * @param filePath Path of the file
     * @return The loaded image
     * @throws IOException Thrown exception if image has failed to read
     */
    public BufferedImage loadImage(String filePath) throws IOException {
        return loadImage(new File(filePath));
    }

    /**
     * Load an image from a file
     *
     * @param file File to load as image
     * @return The loaded image
     * @throws IOException Thrown exception if image has failed to read
     */
    public BufferedImage loadImage(File file) throws IOException {
        return ImageIO.read(file);
    }

    /**
     * Load all images from a directory
     *
     * @param dirPath Directory path
     * @return Loaded images that are directly under the directory
     */
    public List<BufferedImage> loadAllImagesFromDirectory(String dirPath) {
        List<BufferedImage> result = new ArrayList<>();

        File directory = new File(dirPath);
        if (directory.exists() && directory.isDirectory()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                try {
                    BufferedImage image = loadImage(file);

                    if (isFileImage(file)) {
                        result.add(image);
                    }
                } catch (IOException _) {
                }
            }
        }

        return result;
    }

    /**
     * Check if a file is an image
     *
     * @param file File to check
     * @return True if the file is an image, false otherwise
     */
    public boolean isFileImage(File file) {
        try {
            return loadImage(file) != null;
        } catch (IOException _) {
        }
        return false;
    }
}
