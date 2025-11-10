package models.services;

import java.awt.image.BufferedImage;
import java.util.*;

public class ImageService {

    private final Deque<BufferedImage> previousImages;
    private final Deque<BufferedImage> images;

    private BufferedImage currentImage;

    private final Set<Runnable> onImageChangedActions;


    // CONSTRUCTORS

    public ImageService() {
        previousImages = new ArrayDeque<>();
        images = new ArrayDeque<>();

        onImageChangedActions = new HashSet<>();
    }


    // GETTERS AND SETTERS

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


    // PUBLIC METHODS

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
