package Moon.mods.notifications;

import java.util.concurrent.LinkedBlockingQueue;

public class NotificationManager {
    private static final LinkedBlockingQueue<Notification> pendingNotifications = new LinkedBlockingQueue<>();
    public static boolean notifyEnabled = true;
    private static Notification currentNotification = null;

    public static void show(Notification notification) {
        if (notifyEnabled)
            pendingNotifications.add(notification);
        else pendingNotifications.clear();
    }

    public static void update() {
        if (notifyEnabled) {
            if (currentNotification != null && !currentNotification.isShown()) {
                currentNotification = null;
            }

            if (notifyEnabled)
                if (currentNotification == null && !pendingNotifications.isEmpty()) {
                    currentNotification = pendingNotifications.poll();
                    currentNotification.show();
                }

        } else {
            pendingNotifications.clear();
        }
    }

    public static void render() {
        if (notifyEnabled) {
            update();
            if (currentNotification != null) {
                currentNotification.render();
            }
        } else {
            pendingNotifications.clear();
        }

    }
}
