package Moon;

import Moon.command.impl.AdvancedCrashCommand;
import Moon.managers.CommandManager;
import Moon.managers.CrashManager;
import Moon.modules.ModuleManager;
import Moon.utils.FileManager;
import Moon.utils.font.FontLoaders;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.blockparticles.FBP;
import net.minecraft.client.Minecraft;
import net.minecraft.viamcp.ViaMCP;

import java.io.File;
import java.util.Objects;

public class Moon {

    public static Moon INSTANCE;
    public ModuleManager moduleManager;
    private final CommandManager commandManager;
    private final CrashManager crashManager;
    public static String CLIENT_NAME = "Moon";
    public String version = "4.7.3";
    public static int flag = -666;
    public boolean authorised;
    public static FontLoaders fontLoaders;
    public static File dataFolder = new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath(), CLIENT_NAME);


    public Moon() {
        INSTANCE = this;
        authorised = false;
        moduleManager = new ModuleManager();
        moduleManager.init();
        this.commandManager = new CommandManager();
        this.crashManager = new CrashManager();
        initialize();
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public CrashManager getExploitManager() {
        return this.crashManager;
    }

    public void initialize() {
        System.setProperty("log4j2.formatMsgNoLookups", "true");
            moduleManager.init();
            new DiscordRP();
            ViaMCP.getInstance().start();
            FBP.INSTANCE.onStart();
            loadAdvancedCrash();
        FileManager.init();
    }

        public void loadAdvancedCrash() {
        File folder = new File("data");
        if (!folder.exists()) folder.mkdir();

        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equalsIgnoreCase("saved.txt")) {
                AdvancedCrashCommand.methods.add(listOfFiles[i].getName());
            }
        }
    }

    public void terminate() {
        DiscordRPC.discordShutdown();
    }
}
