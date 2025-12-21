package models.observer;

/**
 * Define a listenable class
 */
public interface Listenable {
    /**
     * Add a listener that will listen to this class
     *
     * @param listener Listener that will listen to this class
     */
    void addListener(Listener listener);

    /**
     * Remove a listener that is listening this class
     *
     * @param listener Listener that is listening this class
     */
    void removeListener(Listener listener);
}
