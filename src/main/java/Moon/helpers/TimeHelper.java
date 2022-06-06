package Moon.helpers;

import java.time.Instant;

public final class TimeHelper {
    private static long lastMS = 0L;
    public long time = System.nanoTime() / 1000000L;
    public long tick;
    public boolean enabling;

    public static double fromMillis(long millis) {
        return (double) millis / 1000.0D;
    }

    public static long getCurrentMillis() {
        return Instant.now().toEpochMilli();
    }

    public static double getCurrentTime() {
        return fromMillis(getCurrentMillis());
    }

    public static long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public static boolean hasReached(long millisecounds) {
        return getCurrentMS() - lastMS >= millisecounds;
    }

    public static void resets() {
        lastMS = getCurrentMS();
    }

    public void update() {
        if (this.enabling) {
            ++this.time;
        } else {
            --this.time;
        }
        if (this.time < 0L) {
            this.time = 0L;
        }

        if (this.time > (long) this.getMaxTime()) {
            this.time = this.getMaxTime();
        }

    }

    public int getMaxTime() {
        return 10;
    }

    public void on() {
        this.enabling = true;
    }

    public long getTimes() {
        return System.nanoTime() / 1000000L;
    }

    public void reset() {
        this.time = System.nanoTime() / 1000000L;
    }
}
