package net.minecraft.client.gui;

import Moon.helpers.ColorHelper;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.net.InetAddress;

public class GuiScreenServerList extends GuiScreen {
    private final GuiScreen field_146303_a;
    private final ServerData field_146301_f;
    private final ServerListEntryNormal entry;
    boolean changedIP = false;
    boolean update = false;
    private GuiTextField field_146302_g;
    private long tick;

    public GuiScreenServerList(GuiScreen p_i1031_1_, ServerData p_i1031_2_) {
        this.field_146303_a = p_i1031_1_;
        this.field_146301_f = p_i1031_2_;
        this.entry = new ServerListEntryNormal((GuiMultiplayer) p_i1031_1_, p_i1031_2_);
    }

    public void update() {
        ++this.tick;
    }

    public void updateScreen() {
        this.field_146302_g.updateCursorCounter();
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 12, I18n.format("selectServer.select")));
        this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + 12, I18n.format("gui.cancel")));
        this.buttonList.add(new GuiButton(2, width / 2 - 100, height / 4 + 144 + 12, "Resolve IP"));
        this.field_146302_g = new GuiTextField(2, this.fontRendererObj, width / 2 - 100, 116, 200, 20);
        this.field_146302_g.setMaxStringLength(128);
        this.field_146302_g.setFocused(true);
        this.field_146302_g.setText(this.mc.gameSettings.lastServer);
        this.buttonList.get(0).enabled = this.field_146302_g.getText().length() > 0 && this.field_146302_g.getText().split(":").length > 0;
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        this.mc.gameSettings.lastServer = this.field_146302_g.getText();
        this.mc.gameSettings.saveOptions();
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        this.changedIP = false;
        String server = this.field_146302_g.getText();
        String ip = ServerAddress.fromString(server).getIP();
        String port = "" + ServerAddress.fromString(server).getPort();

        String serverIp;
        try {
            serverIp = InetAddress.getByName(ip).getHostAddress() + ":" + port;
        } catch (Exception var7) {
            serverIp = "Server cannot be found!";
        }

        if (button.id == 1) {
            this.field_146303_a.confirmClicked(false, 0);
        }

        if (button.enabled) {
            if (button.id == 0) {
                this.field_146301_f.serverIP = server;
                this.field_146303_a.confirmClicked(true, 0);
            } else if (button.id == 2) {
                this.field_146302_g.setText(serverIp);
                this.changedIP = true;
            }
        }

    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        if (this.field_146302_g.textboxKeyTyped(typedChar, keyCode)) {
            this.buttonList.get(0).enabled = this.field_146302_g.getText().length() > 0 && this.field_146302_g.getText().split(":").length > 0;
        } else if (keyCode == 28 || keyCode == 156) {
            this.actionPerformed(this.buttonList.get(0));
        }

        this.field_146301_f.serverIP = this.field_146302_g.getText();
        this.field_146301_f.serverName = this.field_146302_g.getText();
        this.entry.setServerData(this.field_146301_f);
        this.entry.ping();
        this.reset();
        this.update = true;
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.field_146302_g.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void reset() {
        this.tick = 0L;
    }

    public boolean hasTimePassed(long ticks) {
        return this.tick >= ticks;
    }

    public long get() {
        return this.tick;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.field_146301_f.serverIP = this.field_146302_g.getText();
        this.field_146301_f.serverName = this.field_146302_g.getText();
        this.update();
        long max = 166L;
        int x = width / 2 - 150;
        int w = 275;
        int y = 85;
        int h = 1;
        drawRect(x, y, x + (int) ((double) this.get() / (double) max * (double) w), y + h, ColorHelper.getButtonColor());
        if (this.hasTimePassed(max)) {
            this.entry.setServerData(this.field_146301_f);
            this.entry.ping();
            this.reset();
        }

        this.entry.drawEntry(0, width / 2 - 150, 50, 275, 35, 0, 0, false);
        if (this.changedIP) {
            this.changedIP = false;
        }

        drawCenteredString(this.fontRendererObj, I18n.format("selectServer.direct"), width / 2, 20, 16777215);
        drawString(this.fontRendererObj, I18n.format("addServer.enterIp"), width / 2 - 100, 100, 10526880);
        this.field_146302_g.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
