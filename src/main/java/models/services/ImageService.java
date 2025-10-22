package models.services;

import java.awt.image.BufferedImage;
import java.util.*;

public class ImageService {

    private final Deque<BufferedImage> previousImages;
    private final Deque<BufferedImage> images;

    private BufferedImage currentImage;

    public ImageService() {
        previousImages = new ArrayDeque<>();
        images = new ArrayDeque<>();
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public void setImages(Collection<BufferedImage> images) {
        // Copy images to shuffle them
        List<BufferedImage> imagesCopy = new ArrayList<>(images.stream().toList());
        Collections.shuffle(imagesCopy);

        // Push images
        imagesCopy.forEach(this.images::push);
        currentImage = this.images.pop();
    }

    /**
     * Count all images
     *
     * @return The count of all images
     */
    public int countImages() {
        return countRemainingImages() + previousImages.size();
    }

    /**
     * Count all remaining images
     *
     * @return The count of the remaining images
     */
    public int countRemainingImages() {
        if (currentImage == null) {
            return 0;
        } else {
            return 1 + images.size();
        }
    }

    /**
     * Pass to the new image
     */
    public void nextImage() {
        if (!images.isEmpty()) {
            previousImages.push(currentImage);
            currentImage = images.pop();
        }
    }

    /**
     * Pass to the previous image
     */
    public void previousImage() {
        if (!previousImages.isEmpty()) {
            images.push(currentImage);
            currentImage = previousImages.pop();
        }
    }
}
