package Moon.command.impl;

import Moon.Moon;
import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.exeptions.CommandException;
import Moon.helpers.ChatHelper;
import Moon.helpers.InGameHookHelper;
import Moon.mods.chunkanimator.ChunkAnimator;
import Moon.mods.notifications.NotificationManager;
import Moon.modules.Module;
import net.minecraft.util.EnumChatFormatting;
import tv.twitch.chat.Chat;

@CommandInfo(
        alias = "toggle",
        usage = ",toggle <list/module>",
        aliases = {"t"}
)
public class ToggleCommand extends Command {

    public static Boolean wings = true;
    public static boolean rpc = true;
    public static boolean ircEnabled = true;

    public void execute(String... args) throws CommandException {
        if (args.length != 1) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }

        switch (args[0].toLowerCase()) {
            case "list":
                ChatHelper.sendMessage("", false);
                ChatHelper.sendMessage("&7List of available modules&8:", true);
                ChatHelper.sendMessage("", false);
                ChatHelper.sendMessage("&lSPEEDMINE &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lNOFALL &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lSPRINT &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lFLIGHT &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lFASTPLACE &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lBUNNYHOP &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lCHEATGUI &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lFASTUSE &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lHIGHJUMP &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lTIMER &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lTRACERS &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("", false);
                ChatHelper.sendMessage("&lCHUNKANIMATOR &8(&fCANNOT BE BINDED&8)", true);
                ChatHelper.sendMessage("&lWINGS &8(&fCANNOT BE BINDED&8)", true);
                ChatHelper.sendMessage("&lNOTIFY &8(&fCANNOT BE BINDED&8)", true);
                ChatHelper.sendMessage("&lHUD &8(&fCAN BE BINDED&8)", true);
                ChatHelper.sendMessage("&lRPC &8(&fCANNOT BE BINDED&8)", true);
                ChatHelper.sendMessage("&lIRC &8(&fCANNOT BE BINDED&8)", true);
                ChatHelper.sendMessage("", false);
                break;
            case "wings":
                ChatHelper.sendMessage(String.format("Wings has been &f%s&7!", !wings ? "enabled" : "disabled"));
                wings = !wings;
                break;

            case "animator":
            case "chunkanimator":
                ChatHelper.sendMessage(String.format("ChunkAnimator has been &f%s&7!", !ChunkAnimator.isEnabled ? "enabled" : "disabled"));
                ChunkAnimator.isEnabled = !ChunkAnimator.isEnabled;
                break;

            case "discordrpc":
            case "rpc":
                ChatHelper.sendMessage(String.format("DiscordRPC has been &f%s&7!", rpc ? "enabled" : "disabled"));
                rpc = !rpc;
                break;

            case "irc":
                ircEnabled = !ircEnabled;
                ChatHelper.sendMessage(String.format("ClientIRC has been &f%s&7! ", ircEnabled ? "&aenabled" : "&cdisabled"));
                break;

            case "hud":
                ChatHelper.sendMessage(String.format("Hud has been &f%s&7!", !InGameHookHelper.showHud ? "enabled" : "disabled"));
                InGameHookHelper.showHud = !InGameHookHelper.showHud;
                break;

            case "notifications":
            case "notify":
                ChatHelper.sendMessage(String.format("Notifications has been &f%s&7!", !NotificationManager.notifyEnabled ? "enabled" : "disabled"));
                NotificationManager.notifyEnabled = !NotificationManager.notifyEnabled;
                break;

            default:
                Module module = Moon.INSTANCE.moduleManager.getModuleByName(args[0]);
                if (module != null) {
                    if (!module.isEnabled()) {
                        module.setEnabled(true);
                    } else {
                        module.setEnabled(false);
                    }
                    if (module.isEnabled()) {
                        ChatHelper.sendMessage("> " + module.getName() + (Object)((Object) EnumChatFormatting.GRAY) + " was" + (Object)((Object)EnumChatFormatting.GREEN) + " enabled");
                    } else {
                        ChatHelper.sendMessage("> " + module.getName() + (Object)((Object)EnumChatFormatting.GRAY) + " was" + (Object)((Object)EnumChatFormatting.RED) + " disabled");
                    }
                } else {
                    ChatHelper.sendMessage("This module doesn't exist!", true);
                }
        }
    }
}
