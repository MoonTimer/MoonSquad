package Moon.command.impl;

import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.exeptions.CommandException;
import Moon.helpers.ChatHelper;

import java.util.Set;

@CommandInfo(
        alias = "Threads",
        usage = ",threads <list/count>"
)
public class ThreadsCommand extends Command {
    public void execute(String... args) throws CommandException {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        if (args.length != 1) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }
        switch (args[0].toLowerCase()) {
            case "count":
                ChatHelper.sendMessage("", false);
                ChatHelper.sendMessage("Threads count&8: &f" + Thread.activeCount(), true);
                ChatHelper.sendMessage("Current&8: &f" + Thread.currentThread(), true);
                ChatHelper.sendMessage("", false);
                break;

            case "list":
                ChatHelper.sendMessage("", false);
                ChatHelper.sendMessage("All threads in usage&8: ", true);
                threadSet.forEach((thread) -> ChatHelper.sendMessage("&4&lThread &8-> &f" + thread, false));
                ChatHelper.sendMessage("", false);
                break;

            default:
                throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }
    }
}
