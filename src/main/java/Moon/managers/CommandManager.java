package Moon.managers;

import Moon.command.Command;
import Moon.command.impl.*;
import Moon.exeptions.CommandException;
import Moon.helpers.ChatHelper;
import net.minecraft.client.gui.Gui;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandManager {
    private final List<Command> commands;

    public CommandManager() {
        this.commands = Arrays.asList(
                new AdvancedCrashCommand(),
                new CrashCommand(),
                new HelpCommand(),
                new MethodsCommand(),
                new GamemodeCommand(),
                new ThreadsCommand(),
                new ExploitCommand(),
                new ClearChatCommand(),
                new ProtocolsCommand(),
                new ToggleCommand(),
                new VClipCommand(),
                new BindCommand(),
                new UnbindCommand(),
                new GuiCommand()
        );
    }

    public static String getPrefix() {
        return ",";
    }

    public boolean handleCommand(String message) {
        if (message.isEmpty()) {
            return false;
        } else {
            String[] args = message.substring(1).split(" ");

            try {
                this.getCommand(args[0]).orElseThrow(() -> new CommandException("Command cannot be found!")).execute(Arrays.copyOfRange(args, 1, args.length));
            } catch (net.minecraft.command.CommandException | CommandException var4) {
                ChatHelper.sendMessage(var4.getMessage());
            }

            return true;
        }

    }

    public Optional<Command> getCommand(String alias) {
        return this.commands.stream().filter((command) -> command.is(alias)).findFirst();
    }

    public List<Command> getCommands() {
        return this.commands;
    }
}
