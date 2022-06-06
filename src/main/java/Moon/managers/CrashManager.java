package Moon.managers;

import Moon.methods.Crash;
import Moon.methods.impl.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CrashManager {
    private final List<Crash> exploits;


    public CrashManager() {
        this.exploits = Arrays.asList(
                new CrashYokai1(),
                new CrashYokai2(),
                new CrashYokai3(),
                new CrashShade1(),
                new CrashShade2(),
                new CrashShade3(),
                new CrashMare1(),
                new CrashMare2(),
                new CrashMare3(),
                new CrashSpirit1(),
                new CrashSpirit2(),
                new CrashSpirit3(),
                new CrashSpirit4(),
                new CrashBaku1(),
                new CrashBaku2(),
                new CrashBaku3(),
                new CrashFeno1(),
                new CrashFeno2(),
                new CrashFeno3(),
                new CrashFeno4(),
                new CrashVenti1(),
                new CrashVenti2(),
                new CrashVenti3(),
                new CrashTeke1(),
                new CrashTeke2(),
                new CrashTeke3()
        );
    }

    public Optional<Crash> getExploit(String name) {
        return this.exploits.stream().filter((exploit) -> exploit.getName().equalsIgnoreCase(name)).findFirst();
    }

    public List<Crash> getExploits() {
        return this.exploits;
    }
}
