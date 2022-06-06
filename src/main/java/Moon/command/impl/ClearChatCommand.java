package Moon.command.impl;

import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.exeptions.CommandException;
import net.minecraft.client.Minecraft;

@CommandInfo(
        alias = "clearchat",
        aliases = {"cc", "clearc"}
)
public class ClearChatCommand extends Command {
    public void execute(String... args) throws CommandException {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
    }
}
