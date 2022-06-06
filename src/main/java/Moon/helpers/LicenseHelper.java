package Moon.helpers;

import Moon.Moon;
import Moon.managers.FileManager;
import Moon.mods.alts.MagicField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class LicenseHelper extends GuiScreen {
    public static String license;
    private static MagicField code;
    private final File dataFile = new File("data");
    private boolean xd = false;

    public static void setDisplay() throws IOException {
        String s = "Moon | v" + Moon.INSTANCE.version;
        Display.setTitle(s);
        if (!s.equalsIgnoreCase("Moon | v" + Moon.INSTANCE.version))
            System.exit(0);
        OpenGLHelper.INSTANCE.setWindowIcon("https://i.imgur.com/NC6G8SF.png", "https://i.imgur.com/OZ776cK.png");
    }

    public void checkLicense() {

    }

    protected void actionPerformed(GuiButton button) {
        if (dataFile.exists()) {
            try (FileWriter _fileWriter = new FileWriter(dataFile + "/saved.txt")) {
                _fileWriter.write(code.getText());
                _fileWriter.flush();
                System.out.println(code.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FileManager.CreateDataFile();
        }
        xd = true;
        if (button.id == 0) {
            license = code.getText();
            Moon.INSTANCE.authorised = true;
            Minecraft.getMinecraft().displayGuiScreen(new MainMenuHelper());
        }
    }

    public void drawScreen(int x2, int y2, float z2) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("Moon/menu/BackGround.png"));
        drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, width, height, (float) width, (float) height);
        code.drawTextBox();

        String error = "Login by using your license key";
        drawCenteredString(this.mc.fontRendererObj, error, width / 2, 10, -1);

        if (code.getText().isEmpty()) {
            drawString(this.mc.fontRendererObj, "Enter your license key here", width / 2 - 96, 106, -7829368);
        }

        super.drawScreen(x2, y2, z2);
    }

    public void initGui() {
        int var3 = height / 4 + 24;
        this.buttonList.add(new GuiButton(0, width / 2 - 100, var3 + 72 + 12, "Login"));
        this.buttonList.add(new GuiButton(4, width / 2 - 1000, var3 + 702 + 120, ""));
        code = new MagicField(this.mc.fontRendererObj, width / 2 - 100, 100, 200, 20);
        Keyboard.enableRepeatEvents(true);
        code.writeText(FileManager.loadPinCode());

    }

    protected void keyTyped(char character, int key) {
        try {
            super.keyTyped(character, key);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
        if (character == '\r') {
            this.actionPerformed(this.buttonList.get(0));
        }
        code.textboxKeyTyped(character, key);
    }

    protected void mouseClicked(int x2, int y2, int button) {
        try {
            super.mouseClicked(x2, y2, button);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        code.mouseClicked(x2, y2, button);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!xd) {
            Minecraft.getMinecraft().shutdownMinecraftApplet();
            System.exit(0);
        }
    }

    public void updateScreen() {
        code.updateCursorCounter();
    }
}