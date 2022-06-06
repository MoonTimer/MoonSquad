/*
 * Decompiled with CFR 0_132.
 */
package Moon.event.events.world;

import Moon.event.Event;
import Moon.utils.cheats.player.Helper;

import static Moon.utils.cheats.player.Helper.mc;

public class EventPreUpdate
extends Event {
    private float yaw;
    private float pitch;
    public double y;
    private boolean ground;

    public EventPreUpdate(float yaw, float pitch, double y, boolean ground) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.y = y;
        this.ground = ground;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float yaw) {
		Helper.mc.thePlayer.renderYawOffset = yaw;
		Helper.mc.thePlayer.rotationYawHead = yaw;
        this.yaw = yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
        mc.thePlayer.rotationPitch = pitch;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isOnground() {
        return this.ground;
    }

    public void setOnground(boolean ground) {
        this.ground = ground;
    }

}

