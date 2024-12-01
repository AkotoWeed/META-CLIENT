package de.javafunction.meta.minigame;

public class NzxterUtil {
    private long lastMS;

    public NzxterUtil() {
        reset();
    }

    /**
     * Converts a given value to milliseconds.
     *
     * @param d The value to be converted.
     * @return The equivalent value in milliseconds.
     */
    public int convertToMS(int d) {
        return 1000 / d;
    }

    /**
     * Returns the delay since the last reset.
     *
     * @return The delay in milliseconds.
     */
    public long getDelay() {
        return System.currentTimeMillis() - this.lastMS;
    }

    /**
     * Checks if the specified delay has been reached.
     *
     * @param delay The delay to check against.
     * @return True if the delay has been reached, false otherwise.
     */
    public boolean hasTimeReached(long delay) {
        return getDelay() >= delay;
    }

    /**
     * Resets the timer by setting the lastMS to the current time.
     */
    public void reset() {
        setLastMS(System.currentTimeMillis());
    }

    /**
     * Sets the lastMS to the specified value.
     *
     * @param lastMS The new value of lastMS.
     */
    public void setLastMS(long lastMS) {
        this.lastMS = lastMS;
    }
}
