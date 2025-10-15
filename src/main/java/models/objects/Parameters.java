package models.objects;

import models.observer.AbstractListenable;
import models.observer.objectlisteners.ParametersListener;

public class Parameters extends AbstractListenable {

    private String filePath;

    private int timePerImage = 5;


    // GETTERS AND SETTERS

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        String oldFilePath = this.filePath;
        this.filePath = filePath;

        // Call listeners
        listeners.forEach(l -> {
            ((ParametersListener) l).onFilePathChange(oldFilePath, filePath);
            l.onChange(this);
        });
    }

    public int getTimePerImage() {
        return timePerImage;
    }

    public void setTimePerImage(int timePerImage) {
        int oldTimePerImage = this.timePerImage;
        this.timePerImage = timePerImage;

        // Call listeners
        listeners.forEach(l -> {
            ((ParametersListener) l).onTimePerImage(oldTimePerImage, timePerImage);
            l.onChange(this);
        });
    }


    // METHODS

    public boolean areValid() {
        return filePath != null && !filePath.isEmpty() && timePerImage < 30 && timePerImage > 1;
    }
}
