package models.observer;

/**
 * Define a class that listen to an object
 */
public interface Listener {
    /**
     * Called when something has changed
     *
     * @param source Source of the change
     */
    void onChange(Object source);
}
