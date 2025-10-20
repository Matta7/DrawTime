package models.objects;

import models.observer.AbstractListenable;
import models.observer.objectlisteners.ParametersListener;

public class Parameters extends AbstractListenable {

    private String filePath;

    private int timePerImage = 5;

    private boolean valid = false;

    public Parameters() {
        updateValidity();
    }


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

        // Update validity
        updateValidity();
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

        // Update validity
        updateValidity();
    }

    public boolean isValid() {
        return valid;
    }

    // METHODS

    /**
     * Update parameters validity
     */
    private void updateValidity() {
        boolean newValid = filePath != null && !filePath.isEmpty() && timePerImage < 30 && timePerImage > 1;

        if (newValid != valid) {
            valid = newValid;

            // Call listeners
            listeners.forEach(l -> {
                ((ParametersListener) l).onValidityChange(newValid);
                l.onChange(this);
            });
        }
    }
}
