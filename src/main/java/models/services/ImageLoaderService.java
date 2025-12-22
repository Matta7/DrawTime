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
     * @param recursive If true, take all directories under the given directory
     * @return Loaded images that are directly under the directory
     */
    public List<String> retrieveAllImagesFromDirectory(String dirPath, boolean recursive) {
        List<String> result = new ArrayList<>();

        File directory = new File(dirPath);
        if (directory.exists() && directory.isDirectory()) {
            try {
                for (File file : Objects.requireNonNull(directory.listFiles())) {
                    String imagePath = file.getAbsolutePath();

                    if (isFileImage(imagePath)) {
                        result.add(imagePath);
                    }
                    // If recursive, check images in all directories under root directory
                    else if (recursive && file.isDirectory()) {
                        result.addAll(retrieveAllImagesFromDirectory(imagePath, true));
                    }
                }
            }
            catch (NullPointerException _) {
                // Nothing to do
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
            // Nothing to do
        }
        return false;
    }

    /**
     * Check if a file is an image using its path
     *
     * @param filePath Path of the file to check
     * @return True if the file path is an image, false otherwise
     */
    public boolean isFileImage(String filePath) {
        return filePath.toLowerCase().endsWith(".png") || filePath.toLowerCase().endsWith(".jpg") || filePath.toLowerCase().endsWith(".jpeg");
    }
}
