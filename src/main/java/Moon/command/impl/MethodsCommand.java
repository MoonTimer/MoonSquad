package Moon.command.impl;

import Moon.command.Command;
import Moon.command.CommandInfo;
import Moon.exeptions.CommandException;
import Moon.helpers.ChatHelper;

@CommandInfo(
        alias = "methods"
)
public class MethodsCommand extends Command {
    public void execute(String... args) throws CommandException {
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&7List of available crashers&8:", true);
        ChatHelper.sendMessage("&7In \"()\" is recommended packet amount", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lYOKAI1 &8-> &fCustomPayload crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("&lYOKAI2 &8-> &fCustomPayload crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("&lYOKAI3 &8-> &fCustomPayload crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lSHADE1 &8-> &fNetty crasher &8(&f150-500s&8)", true);
        ChatHelper.sendMessage("&lSHADE2 &8-> &fNetty crasher &8(&f150-500&8)", true);
        ChatHelper.sendMessage("&lSHADE3 &8-> &fNetty crasher &8(&f150-500&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lMARE1 &8-> &fExtra unicode crasher &8(&f10-100&8)", true);
        ChatHelper.sendMessage("&lMARE2 &8-> &fExtra unicode crasher &8(&f10-100&8)", true);
        ChatHelper.sendMessage("&lMARE3 &8-> &fExtra unicode crasher &8(&f10-100&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lSPIRIT1 &8-> &fPosition crasher &8(&f1-30&8)", true);
        ChatHelper.sendMessage("&lSPIRIT2 &8-> &fPosition crasher &8(&f100-300&8)", true);
        ChatHelper.sendMessage("&lSPIRIT3 &8-> &fPosition crasher &8(&f50-200&8)", true);
        ChatHelper.sendMessage("&lSPIRIT4 &8-> &fPosition crasher &8(&f1-20&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lTEKE1 &8-> &fSmall book crasher &8(&f3000-15000&8)", true);
        ChatHelper.sendMessage("&lTEKE2 &8-> &fSmall book crasher &8(&f3000-15000&8)", true);
        ChatHelper.sendMessage("&lTEKE3 &8-> &fSmall book crasher &8(&f500-10000&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lBAKU1 &8-> &fBuffered byte crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("&lBAKU2 &8-> &fBuffered byte crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("&lBAKU3 &8-> &fBuffered byte crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lFENO1 &8-> &fInstant json sign crasher &8(&f1&8)", true);
        ChatHelper.sendMessage("&lFENO2 &8-> &fLag json sign crasher &8(&f1&8)", true);
        ChatHelper.sendMessage("&lFENO3 &8-> &fInstant message crasher &8(&f1&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lVENTI1 &8-> &fAdvanced byte crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("&lVENTI2 &8-> &fAdvanced byte crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("&lVENTI3 &8-> &fAdvanced byte crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("", false);
    }
}
