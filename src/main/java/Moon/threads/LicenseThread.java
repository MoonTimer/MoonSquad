package Moon.threads;

import Moon.Moon;

import java.util.TimerTask;

public class LicenseThread extends TimerTask {

    public static boolean isThreadRunning = false;

    public void run() {
        isThreadRunning = true;
        if (!Moon.INSTANCE.authorised) {
            System.exit(0);
        }
    }
}
