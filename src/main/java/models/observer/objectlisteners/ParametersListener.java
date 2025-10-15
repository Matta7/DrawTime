package models.observer.objectlisteners;

import models.objects.Parameters;
import models.observer.Listener;

/**
 * Define a class that listen to parameters
 */
public interface ParametersListener extends Listener {
    /**
     * Called when file path has changed
     *
     * @param oldFilePath Old file path
     * @param newFilePath New file path
     */
    void onFilePathChange(String oldFilePath, String newFilePath);

    /**
     * Called when time per image has changed
     *
     * @param oldTimePerImage Old time per image
     * @param newTimePerImage New time per image
     */
    void onTimePerImage(int oldTimePerImage, int newTimePerImage);
}
