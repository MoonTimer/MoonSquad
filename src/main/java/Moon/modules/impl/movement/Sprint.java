/*
 * Decompiled with CFR 0_132.
 */
package Moon.modules.impl.movement;

import Moon.event.EventHandler;
import Moon.event.events.world.EventPreUpdate;
import Moon.event.value.Mode;
import Moon.event.value.Option;
import Moon.modules.Module;
import Moon.modules.ModuleType;

public class Sprint
        extends Module {
    private Mode mod = new Mode("Mode", "Mode", new String[]{"Key", "Set"}, "Key");
    private Option<Boolean> omni = new Option<Boolean>("Omni-Directional", "omni", true);

    public Sprint() {
        super("Sprint", ModuleType.Movement);
        this.addValues(this.omni, this.mod);
    }

    @EventHandler
    private void onUpdate(EventPreUpdate event) {
        omni.setVisible(mod.getModeAsString().equals("Set"));

        if (mod.getModeAsString().equals("Key")) {
            mc.gameSettings.keyBindSprint.pressed = true;
        }

        if (this.mc.thePlayer.getFoodStats().getFoodLevel() > 6 && this.omni.getValue() != false ? this.mc.thePlayer.moving() : this.mc.thePlayer.moveForward > 0.1f) {
            this.mc.thePlayer.setSprinting(true);
        }
    }
}

