package Moon.command.impl;

import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.exeptions.CommandException;
import Moon.helpers.ChatHelper;

@CommandInfo(
        alias = "authors"
)
public class AuthorsCommand extends Command {
    public void execute(String... args) throws CommandException {
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("MoonTimer &8-> &bDeveloper", true);
        ChatHelper.sendMessage("", false);
    }
}
