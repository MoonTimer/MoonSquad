/*
 * Decompiled with CFR 0_132.
 */
package Moon.modules.impl.combat;

import Moon.event.EventHandler;
import Moon.event.events.world.EventPreUpdate;
import Moon.modules.Module;
import Moon.modules.ModuleType;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Regen
extends Module {
    public Regen(){
        super("Regen", ModuleType.Combat);
    }

    @EventHandler
    private void onUpdate(EventPreUpdate event) {
        if (this.mc.thePlayer.onGround && (double)this.mc.thePlayer.getHealth() < 16.0 && this.mc.thePlayer.getFoodStats().getFoodLevel() > 17 && this.mc.thePlayer.isCollidedVertically) {
            int i = 0;
            while (i < 3) {
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 1.0E-9, this.mc.thePlayer.posZ, this.mc.thePlayer.rotationYaw, this.mc.thePlayer.rotationPitch, true));
                this.mc.thePlayer.motionX = 0.0;
                this.mc.thePlayer.motionZ = 0.0;
                ++i;
            }

            this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
        }
    }
}

