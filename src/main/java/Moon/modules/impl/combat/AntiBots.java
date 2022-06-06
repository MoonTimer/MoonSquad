/*
 * Decompiled with CFR 0_132.
 */
package Moon.modules.impl.combat;

import Moon.event.EventHandler;
import Moon.event.events.world.EventPreUpdate;
import Moon.modules.Module;
import Moon.modules.ModuleManager;
import Moon.modules.ModuleType;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;


public class AntiBots
        extends Module {
    private final ArrayList<EntityPlayer> bots = new ArrayList<>();

    public AntiBots() {
        super("AntiBots", "remove bots.", ModuleType.Combat);
        this.setEnabled(true);
    }

    @EventHandler
    public void onUpdate(EventPreUpdate e) {
        this.setSuffix("Basic");
        if (!mc.isSingleplayer()) {
            for (Object entities : mc.theWorld.loadedEntityList) {
                if (entities instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) entities;
                    if (entityPlayer != mc.thePlayer) {
                        if (mc.thePlayer.getDistanceToEntity(entityPlayer) < 10) {
                            if (!entityPlayer.getDisplayName().getFormattedText().contains("\u0e22\u0e07") || entityPlayer.isInvisible()
                                    || entityPlayer.getDisplayName().getFormattedText().toLowerCase().contains("npc")
                                    || entityPlayer.getDisplayName().getFormattedText().toLowerCase().contains("\247r")) {
                                this.bots.add(entityPlayer);
                            }
                        }
                        if (!this.bots.contains(entityPlayer)) {
                            continue;
                        }
                        this.bots.remove(entityPlayer);
                    }
                }
                for (final Entity entity2 : mc.theWorld.getLoadedEntityList()) {
                    if (entity2 instanceof EntityPlayer) {
                        EntityPlayer entityPlayer = (EntityPlayer) entity2;
                        if (entityPlayer == mc.thePlayer) {
                            continue;
                        }
                        if (!entityPlayer.isInvisible()) {
                            continue;
                        }
                        if (entityPlayer.ticksExisted <= 105) {
                            continue;
                        }
                        if (getTabPlayerList().contains(entityPlayer)) {
                            if (!entityPlayer.isInvisible()) {
                                continue;
                            }
                            entityPlayer.setInvisible(false);
                        } else {
                            entityPlayer.setInvisible(false);
                            mc.theWorld.removeEntity(entityPlayer);
                        }
                    }
                }
            }
        }
    }

    public static boolean isServerBot(Entity entity) {
        if (ModuleManager.getModuleByClass(AntiBots.class).isEnabled()) {
            return entity.getDisplayName().getFormattedText().startsWith("\u00a7") && !entity.isInvisible() && !entity.getDisplayName().getFormattedText().toLowerCase().contains("[npc]");
        }
        return false;
    }

    private List<EntityPlayer> getTabPlayerList() {
        ArrayList<EntityPlayer> list = new ArrayList<>();
        return list;
    }
}

