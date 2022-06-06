package Moon.modules.impl.movement;

import Moon.event.EventHandler;
import Moon.event.events.world.EventPreUpdate;
import Moon.modules.Module;
import Moon.modules.ModuleType;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class InvMove extends Module {

	public InvMove(){
		super("InvMove", ModuleType.Movement);
	}

	@EventHandler
	public void onUpdate(EventPreUpdate event) {
			KeyBinding[] key = { this.mc.gameSettings.keyBindForward, this.mc.gameSettings.keyBindBack, this.mc.gameSettings.keyBindLeft, this.mc.gameSettings.keyBindRight, this.mc.gameSettings.keyBindSprint, this.mc.gameSettings.keyBindJump };
			KeyBinding[] array;
			for (int length = (array = key).length, i = 0; i < length; ++i) {
				KeyBinding b = array[i];
				KeyBinding.setKeyBindState(b.getKeyCode(), Keyboard.isKeyDown(b.getKeyCode()));
			}
	}
}
