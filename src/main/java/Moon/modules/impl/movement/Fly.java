package Moon.modules.impl.movement;

import Moon.event.EventHandler;
import Moon.event.events.world.EventMove;
import Moon.event.events.world.EventPreUpdate;
import Moon.modules.Module;
import Moon.modules.ModuleType;
import Moon.utils.cheats.player.PlayerUtils;

public class Fly extends Module {
    public Fly() {
        super("Fly", ModuleType.Movement);
    }

    @Override
    public void onEnable() {
        super.onEnable();

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onUpdate(EventPreUpdate e) {
        if (mc.gameSettings.keyBindJump.isPressed()) {
            mc.thePlayer.motionY = 0.5;
        } else if (mc.gameSettings.keyBindSneak.isPressed()) {
            mc.thePlayer.motionY = -0.5;
        }
    }

    @EventHandler
    public void onMove(EventMove e) {
        if (PlayerUtils.isMoving()) {
            mc.thePlayer.setMoveSpeed(e, 1);
        } else {
            mc.thePlayer.setMoveSpeed(e, 0);
        }
    }

}
