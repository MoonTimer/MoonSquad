package Moon.command.impl;

import Moon.Moon;
import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.helpers.ChatHelper;
import Moon.helpers.ExecutorHelper;
import Moon.methods.Crash;
import net.minecraft.command.CommandException;

import java.util.Arrays;
import java.util.stream.Collectors;

@CommandInfo(
        alias = "crash",
        usage = ",crash <method/list> <amount>"
)
public class CrashCommand extends Command {

    public void execute(String... args) throws CommandException {
        if (!Moon.INSTANCE.authorised)
            System.exit(0);

        if (args.length <= 0) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            ChatHelper.sendMessage("Available methods&8: &f" + Moon.INSTANCE.getExploitManager().getExploits().stream().map(Crash::getName).collect(Collectors.joining("&f, &f")).toUpperCase());
        } else if (args.length != 2 && !args[0].equalsIgnoreCase("list")) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        } else if (args.length == 2) {
            Crash exploit = Moon.INSTANCE.getExploitManager().getExploit(args[0]).orElseThrow(() -> new CommandException("Method cannot be found!"));
            ExecutorHelper.submit(() -> exploit.execute(this.parseArgs(Arrays.copyOfRange(args, 1, args.length))));
        } else {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }
    }

    public Object[] parseArgs(String[] args) {
        Object[] parsedArgs = new Object[1];

        try {
            String arg = args[0];
            parsedArgs[0] = Integer.parseInt(arg);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return parsedArgs;
    }
}
