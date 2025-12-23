package models.objects;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Countdown timer class
 */
public class CountdownTimer {

    private int time = 1;

    private int remainingSeconds = time;

    private final Timer timer;

    private final Set<Runnable> onTimeoutActions;

    private final Set<Runnable> onTickActions;


    // CONSTRUCTORS

    public CountdownTimer() {
        timer = new Timer(1000, _ -> tick());
        onTimeoutActions = new HashSet<>();
        onTickActions = new HashSet<>();
    }

    public CountdownTimer(int time) {
        this();

        this.time = time;
        remainingSeconds = time;

    }


    // GETTERS AND SETTERS

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        remainingSeconds = time;
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }


    // PRIVATE METHODS

    /**
     * Method called on each tick (second)
     */
    private void tick() {
        onTickActions.forEach(Runnable::run);
        if (remainingSeconds > 0) {
            remainingSeconds--;
        } else {
            timer.stop();
            onTimeoutActions.forEach(Runnable::run);
        }
    }


    // PUBLIC METHODS

    public void addTimeoutAction(Runnable onTimeout) {
        onTimeoutActions.add(onTimeout);
    }

    public void removeTimeoutAction(Runnable onTimeout) {
        onTimeoutActions.remove(onTimeout);
    }

    public void addTickAction(Runnable onTick) {
        onTickActions.add(onTick);
    }

    public void removeTickAction(Runnable onTick) {
        onTickActions.remove(onTick);
    }

    /**
     * Start the timer
     */
    public void start() {
        timer.start();
    }

    /**
     * Start the timer
     *
     * @param time Time (second)
     */
    public void start(int time) {
        setTime(time);
        start();
    }

    /**
     * Pause the timer
     */
    public void pause() {
        timer.stop();
    }

    /**
     * Stop and reload the timer
     */
    public void stop() {
        timer.stop();
        remainingSeconds = time;
    }

    /**
     * Resume the timer if paused
     */
    public void resume() {
        timer.start();
    }

    /**
     * Reset the timer
     */
    public void reset() {
        reset(time);
    }

    /**
     * Reset the timer
     *
     * @param time Time (second)
     */
    public void reset(int time) {
        stop();
        start(time);
    }

    /**
     * Get remaining time formatted
     *
     * @return Time formatted (mm:ss)
     */
    public String getFormattedTime() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
