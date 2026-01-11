package models.observer;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractListenable implements Listenable {

    /**
     * Set of listeners that listen to this class
     */
    protected Set<Listener> listeners;

    public AbstractListenable() {
        listeners = new HashSet<>();
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    /**
     * Call onChange method for all listeners
     */
    protected void fireChange() {
        for (Listener listener : listeners) {
            listener.onChange(this);
        }
    }
}
