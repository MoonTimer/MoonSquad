package Moon.command.impl;

import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.helpers.MainMenuHelper;
import Moon.modules.gui.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;

@CommandInfo(
        alias = "gui"
)
public class GuiCommand extends Command {
    @Override
    public void execute(String... var1) throws CommandException {
        Minecraft.getMinecraft().displayGuiScreen(new MainWindow());
    }
}
