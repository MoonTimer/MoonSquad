package Moon.modules.impl.combat;

import Moon.event.EventHandler;
import Moon.event.events.rendering.EventRender2D;
import Moon.event.events.rendering.EventRender3D;
import Moon.event.events.world.EventPostUpdate;
import Moon.event.events.world.EventPreUpdate;
import Moon.event.value.Mode;
import Moon.event.value.Numbers;
import Moon.event.value.Option;
import Moon.modules.Module;
import Moon.modules.ModuleManager;
import Moon.modules.ModuleType;
import Moon.utils.cheats.player.PlayerUtils;
import Moon.utils.cheats.world.TimerUtil;
import Moon.utils.math.AnimationUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.lwjgl.opengl.GL11;
import Moon.modules.impl.ClientSettings;
import Moon.modules.impl.world.Scaffold;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Killaura extends Module {

    public static EntityLivingBase target;
    private Option mob = new Option("Mob", false);
    private Option animals = new Option("Animals", false);
    private Option player = new Option("Player", true);
    private Option wither = new Option("Wither", false);
    private Option invisible = new Option("Invisible", false);
    private Option block = new Option("Block", false);
    private Option smart = new Option("SmartSwitch", false);
    private Option noSwing = new Option("NoSwing", false);
    private Option esp = new Option("Esp", true);


    private Mode mode = new Mode("Mode", "Mode", new String[]{"Switch", "Single", "Multi"}, "Switch");
    private Mode rot = new Mode("RotationMode", "RotationMode", new String[]{"Instant", "Animate"}, "Animate");
    private Mode priority = new Mode("Priority", "Priority", new String[]{"Distance", "Health", "Direction"}, "Distance");

    private Numbers<Number> switchDelay = new Numbers<>("SwitchDelay", 150, 10, 2000, 10);
    private Numbers<Number> rotSpeed = new Numbers<>("RotationSpeed", 80, 1, 100, 1);
    private Numbers<Number> cps = new Numbers<>("CPS", 11, 1, 18, 0.1);
    private Numbers<Number> range = new Numbers<>("Range", 3.8, 1, 6, 0.1);
    private Numbers<Number> targets = new Numbers<>("Targets", 3, 1, 6, 1);

    static ArrayList<EntityLivingBase> curTargets = new ArrayList<>();

    private TimerUtil timer = new TimerUtil();
    private TimerUtil cpsTimer = new TimerUtil();
    private AnimationUtils animationUtils1 = new AnimationUtils();
    private AnimationUtils animationUtils2 = new AnimationUtils();
    private AnimationUtils espAnimation = new AnimationUtils();
    private float yPos = 0;
    private boolean direction;
    private int cur = 0;
    private Criticals crit;

    public Killaura() {
        super("KillAura", ModuleType.Combat);
        addValues(priority, esp, cps, range, targets, mob, animals, player, wither, invisible, rot, switchDelay, rotSpeed, block, mode, smart, noSwing);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        crit = (Criticals) ModuleManager.getModuleByClass(Criticals.class);
    }

    @EventHandler
    private void onRender3D(EventRender3D e) {
        if (target != null && ((boolean) esp.getValue())) {
            drawShadow(target, e.getPartialTicks(), yPos, direction);
            drawCircle(target, e.getPartialTicks(), yPos);
        }
    }
    TimerUtil timerUtil = new TimerUtil();
    @EventHandler
    private void onRender2D(EventRender2D e) {
        if(timerUtil.delay(20)) {
            if (direction) {
                yPos += 0.03;
                if (2 - yPos < 0.02) {
                    direction = false;
                }
            } else {
                yPos -= 0.03;
                if (yPos < 0.02) {
                    direction = true;
                }
            }
            timerUtil.reset();
        }
    }

    private void drawShadow(Entity entity, float partialTicks, float pos, boolean direction) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel((int) 7425);
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) partialTicks - mc.getRenderManager().viewerPosX;
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) partialTicks - mc.getRenderManager().viewerPosY + pos;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) partialTicks - mc.getRenderManager().viewerPosZ;
        GL11.glBegin(GL11.GL_QUAD_STRIP);
        Color c = new Color(ClientSettings.r.getValue().intValue(), ClientSettings.g.getValue().intValue(), ClientSettings.b.getValue().intValue());
        for (int i = 0; i <= 12; i++) {
            double c1 = i * Math.PI * 2 / 12;
            double c2 = (i + 1) * Math.PI * 2 / 12;
            GL11.glColor4f(c.getRed() / 255f, (float) c.getGreen() / 255f, (float) c.getBlue() / 255f, 0.4f);
            GL11.glVertex3d(x + 0.5 * Math.cos(c1), y, z + 0.5 * Math.sin(c1));
            GL11.glVertex3d(x + 0.5 * Math.cos(c2), y, z + 0.5 * Math.sin(c2));
            GL11.glColor4f(c.getRed() / 255f, (float) c.getGreen() / 255f, (float) c.getBlue() / 255f, 0f);

            GL11.glVertex3d(x + 0.5 * Math.cos(c1), y + (direction ? -0.3 : 0.3), z + 0.5 * Math.sin(c1));
            GL11.glVertex3d(x + 0.5 * Math.cos(c2), y + (direction ? -0.3 : 0.3), z + 0.5 * Math.sin(c2));


        }
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel((int) 7424);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    private void drawCircle(Entity entity, float partialTicks, float pos) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel((int) 7425);
        GL11.glLineWidth(2);
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) partialTicks - mc.getRenderManager().viewerPosX;
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) partialTicks - mc.getRenderManager().viewerPosY + pos;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) partialTicks - mc.getRenderManager().viewerPosZ;
        GL11.glBegin(GL11.GL_LINE_STRIP);
        Color c = new Color(ClientSettings.r.getValue().intValue(), ClientSettings.g.getValue().intValue(), ClientSettings.b.getValue().intValue());
        for (int i = 0; i <= 12; i++) {
            double c1 = i * Math.PI * 2 / 12;
            GL11.glColor4f(c.getRed() / 255f, (float) c.getGreen() / 255f, (float) c.getBlue() / 255f, 1);
            GL11.glVertex3d(x + 0.5 * Math.cos(c1), y, z + 0.5 * Math.sin(c1));


        }

        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel((int) 7424);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
    @Override
    public void onDisable() {
        super.onDisable();
        target = null;
        curTargets.clear();
        mc.gameSettings.keyBindUseItem.pressed = false;
    }

    public void filter(List<? extends Entity> entities) {
        curTargets.clear();
        target = null;
        for (Entity entity : entities) {
            if (entity == mc.thePlayer) continue;
            if (!entity.isEntityAlive()) continue;
            if (AntiBots.isServerBot(entity)) continue;
            if (curTargets.size() > targets.getValue().intValue()) continue;
            if (mc.thePlayer.getDistanceToEntity(entity) > range.getValue().doubleValue()) continue;

            if (entity instanceof EntityMob && ((boolean) mob.getValue())) curTargets.add((EntityLivingBase) entity);

            if (entity instanceof EntityAnimal && ((boolean) animals.getValue()))
                curTargets.add((EntityLivingBase) entity);

            if (entity instanceof EntityPlayer && ((boolean) player.getValue())) {
                if (entity.isInvisible() && ((boolean) invisible.getValue()))
                    curTargets.add((EntityLivingBase) entity);
                else if (!entity.isInvisible())
                    curTargets.add((EntityLivingBase) entity);
            }

            if (entity instanceof EntityWither && ((boolean) wither.getValue()))
                curTargets.add((EntityLivingBase) entity);
        }
    }

    float cYaw, cPitch;

    @EventHandler
    public void onPre(EventPreUpdate e) {

        this.setSuffix(this.mode.getModeAsString());
        if (ModuleManager.getModuleByClass(Scaffold.class).isEnabled())
            return;
        filter(mc.theWorld.getLoadedEntityList());
        switchTarget();
        if (curTargets.size() != 0) {
            if (cur > curTargets.size() - 1) {
                cur = 0;
            }
            target = curTargets.get(cur);
            float[] rotations = PlayerUtils.getRotations(target);
            rotate(rotations, e);
        }
        if (mc.thePlayer.isBlocking() && target == null) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
        }
    }

    private void stopBlock() {
        mc.playerController.onStoppedUsingItem(mc.thePlayer);
    }

    private void startBlock() {
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
        mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.getHeldItem());
    }

    @EventHandler
    public void onPost(EventPostUpdate e) {
        if (ModuleManager.getModuleByClass(Scaffold.class).isEnabled())
            return;
        if (cpsTimer.delay(1000 / cps.getValue().floatValue()) && target != null) {
            if (mc.thePlayer.isBlocking())
                this.stopBlock();
            crit.doCrit(target);
            if ((boolean) noSwing.getValue()) {
                mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
            } else {
                mc.thePlayer.swingItem();
            }
            mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, C02PacketUseEntity.Action.ATTACK));
            if (this.canBlock())
                this.startBlock();
            cpsTimer.reset();
        }
    }

    private boolean canBlock() {
        return ((boolean) block.getValue()) && (mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword) && target != null;
    }

    private void rotate(float[] rotations, EventPreUpdate e) {
        switch (rot.getModeAsString()) {
            case "Instant":
                cYaw = rotations[0];
                cPitch = rotations[1];
                PlayerUtils.rotate(cYaw, cPitch);
                e.setYaw(cYaw);
                e.setPitch(cPitch);
                break;
            case "Animate":
                if (cYaw == 0 || cPitch == 0) {
                    cYaw = mc.thePlayer.rotationYaw;
                    cPitch = mc.thePlayer.rotationPitch;
                }
                cYaw = animationUtils1.animate(rotations[0], cYaw, rotSpeed.getValue().intValue() / 100f);
                cPitch = animationUtils2.animate(rotations[1], cPitch, rotSpeed.getValue().intValue() / 100f);
                PlayerUtils.rotate(cYaw, cPitch);
                e.setYaw(cYaw);
                e.setPitch(cPitch);
        }
    }

    public float getRotationDis(EntityLivingBase entity) {
        float pitch = PlayerUtils.getRotations(entity)[1];
        return mc.thePlayer.rotationPitch - pitch;
    }

    private void switchTarget() {
        if (mode.isValid("Switch")) {
            if (timer.delay(switchDelay.getValue().intValue())) {
                if (cur < curTargets.size() - 1) {
                    if (((boolean) smart.getValue() && target != null && target.getHealth() < 5)) {
                        timer.reset();
                        return;
                    }
                    cur++;
                } else {
                    cur = 0;
                }
                timer.reset();
            }
        } else if (mode.isValid("Single")) {
            switch (priority.getValue().toString()) {
                case "Distance":
                    curTargets.sort(((o1, o2) -> (int) (o2.getDistanceToEntity(mc.thePlayer) - o1.getDistanceToEntity(mc.thePlayer))));
                    break;
                case "Health":
                    curTargets.sort(((o1, o2) -> (int) (o2.getHealth() - o1.getHealth())));
                    break;
                case "Direction":
                    curTargets.sort(((o1, o2) -> (int) (getRotationDis(o2) - getRotationDis(o1))));
                    break;
            }
            cur = 0;
        } else if (mode.isValid("Multi")) {
            if (timer.delay(80)) {
                if (cur < curTargets.size() - 1) {
                    if (((boolean) smart.getValue() && target != null && target.getHealth() < 5)) {
                        timer.reset();
                        return;
                    }
                    cur++;
                } else {
                    cur = 0;
                }
                timer.reset();
            }
        }
    }
}
