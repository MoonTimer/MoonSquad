package Moon.command.impl;

import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.exeptions.CommandException;
import Moon.helpers.ChatHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

@CommandInfo(
        alias = "vclip",
        usage = ",vclip <blocks>",
        aliases = {"vc"}
)
public class VClipCommand extends Command {
    public void execute(String... args) throws CommandException {
        if (args.length != 1) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }
        try {
            final double blocks = Double.parseDouble(args[0]);
            final Entity e = Minecraft.getMinecraft().thePlayer.ridingEntity;
            final double yPos = Minecraft.getMinecraft().thePlayer.posY + (blocks + 0.002);
            Minecraft.getMinecraft().thePlayer.setLocationAndAngles(Minecraft.getMinecraft().thePlayer.posX, yPos, Minecraft.getMinecraft().thePlayer.posZ, Minecraft.getMinecraft().thePlayer.rotationYaw, Minecraft.getMinecraft().thePlayer.rotationPitch);
            if (e != null) {
                e.setPosition(e.posX, yPos, e.posZ);
            }
            ChatHelper.sendMessage("Teleported \"&f" + blocks + "\" &7blocks.", true);
        } catch (Exception E) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }
    }
}
