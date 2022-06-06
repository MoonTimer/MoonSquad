/*
 * Decompiled with CFR 0_132.
 */
package Moon.event.events.misc;

import Moon.event.Event;
import net.minecraft.entity.player.EntityPlayer;

public class EventInventory
extends Event {
    private final EntityPlayer player;

    public EventInventory(EntityPlayer player) {
        this.player = player;
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }
}

