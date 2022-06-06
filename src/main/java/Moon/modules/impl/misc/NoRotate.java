/*
 * Decompiled with CFR 0_132.
 */
package Moon.modules.impl.misc;

import Moon.event.EventHandler;
import Moon.event.events.world.EventPacketRecieve;
import Moon.event.events.world.EventPreUpdate;
import Moon.modules.Module;
import Moon.modules.ModuleType;
import Moon.utils.cheats.player.Helper;
import Moon.utils.cheats.world.TimerUtil;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
public class NoRotate
        extends Module {
    public NoRotate() {
        super("NoStuck", ModuleType.Misc);
    }

    TimerUtil timerUtil = new TimerUtil();
    int stuck = 0;

    @EventHandler
    public void onUpdate(EventPreUpdate e) {
        if (timerUtil.delay(10000)) {
            stuck = 0;
            timerUtil.reset();
        }
    }

    @EventHandler
    private void onPacket(EventPacketRecieve e) {
        if (e.getPacket() instanceof S08PacketPlayerPosLook && stuck < 3) {
            S08PacketPlayerPosLook look = (S08PacketPlayerPosLook) e.getPacket();
            look.yaw = this.mc.thePlayer.rotationYaw;
            look.pitch = this.mc.thePlayer.rotationPitch;
            Helper.sendMessage("(NoStuck!):" + stuck + " " + look.yaw + "  " + look.pitch);
        }
    }
}

