package Moon.command.impl;

import Moon.Moon;
import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.exeptions.CommandException;
import Moon.helpers.ChatHelper;
import Moon.modules.Module;
import Moon.modules.ModuleManager;
import org.lwjgl.input.Keyboard;

@CommandInfo(
        alias = "bind",
        usage = ",bind <module> <key>",
        aliases = {"b"}
)
public class BindCommand extends Command {
    public void execute(String... args) throws CommandException {
        if (args.length != 2) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }
        String module = args[0];
        String key = args[1];
        Module name = Moon.INSTANCE.moduleManager.getModuleByName(module);
        if (name != null) {
            int k = Keyboard.getKeyIndex((String)args[1].toUpperCase());
            name.setKey(k);
            Object[] arrobject = new Object[2];
            arrobject[0] = name.getName();
            arrobject[1] = k == 0 ? "none" : args[1].toUpperCase();
            ChatHelper.sendMessage(("Module &f" + name.getName() + "&7 has been bind to&8: &f" + Keyboard.getKeyName(name.getKey())));
        } else {
            ChatHelper.sendMessage("This module doesn't exist or cannot be binded!", true);
        }
    }
}
