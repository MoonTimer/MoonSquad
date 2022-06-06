package Moon.command.impl;

import Moon.Moon;
import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.exeptions.CommandException;
import Moon.helpers.ChatHelper;
import Moon.modules.Module;

@CommandInfo(
        alias = "unbind",
        usage = ",unbind <module>",
        aliases = {"ub"}
)
public class UnbindCommand extends Command {
    public void execute(String... args) throws CommandException {
        if (args.length != 1) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }
        String module = args[0];
        Module name = Moon.INSTANCE.moduleManager.getModuleByName(module);
        if (name != null) {
            name.setKey(Integer.MAX_VALUE);
            ChatHelper.sendMessage(("Module &f" + name.getName() + "&7 has been unbinded"));
        } else {
            ChatHelper.sendMessage("This module doesn't exist!", true);
        }
    }
}
