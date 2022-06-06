package Moon.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public final class ChatHelper {

    public static String fix(String string) {
        return string.replace('&', '§').replace(">>", "»");
    }

    public static void sendMessage(String message) {
        sendMessage(message, true);
    }

    public static void sendMessage(String message, boolean prefix) {
        if (prefix) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(fix("&4&lMoon &8>> &7" + message)));
        } else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(fix("" + message)));
        }
    }
}
