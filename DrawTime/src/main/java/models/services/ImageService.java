package models.services;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class ImageService {

    private Deque<String> previousImages;
    private Deque<String> images;

    private String currentImage;

    private final Set<Runnable> onImageServiceReadyActions;
    private final Set<Runnable> onImageChangedActions;


    // CONSTRUCTORS

    public ImageService() {
        onImageServiceReadyActions = new HashSet<>();
        onImageChangedActions = new HashSet<>();
    }


    // GETTERS AND SETTERS

    public String getCurrentImage() {
        return currentImage;
    }

    /**
     * Retrieve current image as BufferedImage.
     * If current image failed to load, load next image as BufferedImage
     *
     * @return Current image as BufferedImage
     */
    public BufferedImage getCurrentImageAsBufferedImage() {
        try {
            ImageLoaderService imageLoaderService = new ImageLoaderService();
            return (BufferedImage) imageLoaderService.loadImage(currentImage);
        } catch (IOException _) {
            currentImage = null;
            nextImage();
            return getCurrentImageAsBufferedImage();
        }
    }

    public void setImages(Collection<String> images) {
        // Copy images to shuffle them
        List<String> imagesCopy = new ArrayList<>(images.stream().toList());
        Collections.shuffle(imagesCopy);

        this.previousImages = new ArrayDeque<>();
        this.images = new ArrayDeque<>();

        // Push images
        imagesCopy.forEach(this.images::push);
        currentImage = this.images.pop();

        onImageServiceReadyActions.forEach(Runnable::run);
    }


    // PUBLIC METHODS

    public void addImageServiceReadyAction(Runnable action) {
        onImageServiceReadyActions.add(action);
    }

    public void removeImageServiceReadyAction(Runnable action) {
        onImageServiceReadyActions.remove(action);
    }

    public void addImageChangedAction(Runnable onImageChanged) {
        onImageChangedActions.add(onImageChanged);
    }

    public void removeImageChangedAction(Runnable onImageChanged) {
        onImageChangedActions.remove(onImageChanged);
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
        if (currentImage != null) {
            previousImages.push(currentImage);
        }
        if (!images.isEmpty()) {
            currentImage = images.pop();
            onImageChangedActions.forEach(Runnable::run);
        } else {
            currentImage = null;
        }
    }

    /**
     * Pass to the previous image
     */
    public void previousImage() {
        if (!previousImages.isEmpty()) {
            if (currentImage != null) {
                images.push(currentImage);
            }
            currentImage = previousImages.pop();
            onImageChangedActions.forEach(Runnable::run);
        }
    }
}
