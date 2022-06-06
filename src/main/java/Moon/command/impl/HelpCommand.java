package Moon.command.impl;

import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.exeptions.CommandException;
import Moon.helpers.ChatHelper;

@CommandInfo(
        alias = "help"
)
public class HelpCommand extends Command {
    public void execute(String... args) throws CommandException {
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage(",help &8-> &fAvailable commands", true);
        ChatHelper.sendMessage(",crash &8-> &fSend packets to server", true);
        ChatHelper.sendMessage(",acrash &8-> &fAdvenced crash system", true);
        ChatHelper.sendMessage(",exploit &8-> &fSend plugins exploits", true);
        ChatHelper.sendMessage(",fakegm &8-> &fChange gamemode for player", true);
        ChatHelper.sendMessage(",authors &8-> &fAuthors List", true);
        ChatHelper.sendMessage(",toggle &8-> &fToggle the module", true);
        ChatHelper.sendMessage(",bind &8-> &fBind the module", true);
        ChatHelper.sendMessage(",unbind &8-> &fUnbind the module", true);
        ChatHelper.sendMessage(",methods &8-> &fAvailable crash methods", true);
        ChatHelper.sendMessage(",threads &8-> &fInformations about threads", true);
        ChatHelper.sendMessage(",clearchat &8-> &fClears your chat", true);
        ChatHelper.sendMessage(",protocols &8-> &fShows all minecraft protocols", true);
        ChatHelper.sendMessage(",vclip &8-> &fChange the Y axis", true);
        ChatHelper.sendMessage("", false);
    }
}
