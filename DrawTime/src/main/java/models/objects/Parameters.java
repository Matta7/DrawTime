package models.objects;

import static models.constants.preferences.PreferencesNameConstants.*;
import models.observer.AbstractListenable;
import models.observer.objectlisteners.ParametersListener;
import models.services.PreferenceService;

import java.util.prefs.Preferences;

public class Parameters extends AbstractListenable {

    private final PreferenceService prefService;

    private String filePath;

    private int timePerImage;

    private boolean valid = false;

    public Parameters() {
        prefService = new PreferenceService();

        setFilePath(prefService.getValue(PARAM_ROOT_DIRECTORY_PATH_PREF).asString());
        setTimePerImage(prefService.getValue(PARAM_TIME_PER_IMAGE_PREF).asInt());

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

        // Update preferences if valid
        if (isFilePathValid()) {
            prefService.setValue(PARAM_ROOT_DIRECTORY_PATH_PREF, filePath);
        }
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

        // Update preferences if valid
        if (isTimePerImageValid()) {
            prefService.setValue(PARAM_TIME_PER_IMAGE_PREF, timePerImage);
        }
    }

    public boolean isValid() {
        return valid;
    }

    // METHODS

    /**
     * Update parameters validity
     */
    private void updateValidity() {
        boolean newValid = isFilePathValid() && isTimePerImageValid();

        if (newValid != valid) {
            valid = newValid;

            // Call listeners
            listeners.forEach(l -> {
                ((ParametersListener) l).onValidityChange(newValid);
                l.onChange(this);
            });
        }
    }

    /**
     * Check if file path is valid
     * A file path is valid if file path is not null or empty
     *
     * @return True if file path is valid, false otherwise
     */
    private boolean isFilePathValid() {
        return filePath != null && !filePath.isEmpty();
    }

    /**
     * Check if time per image is valid
     * Time per image is valid if value is between 0 (excluded) and 60 (included)
     *
     * @return True if time per image is valid, false otherwise
     */
    private boolean isTimePerImageValid() {
        return timePerImage > 0 && timePerImage < 60;
    }
}
