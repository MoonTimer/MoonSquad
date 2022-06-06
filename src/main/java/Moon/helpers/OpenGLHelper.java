package Moon.helpers;

import de.matthiasmann.twl.utils.PNGDecoder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

public enum OpenGLHelper {
    INSTANCE;


    public static void drawRect(float left, float top, float right, float bottom, int color) {
        if (left < right) {
            float i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            float j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (color >> 24 & 0xFF) / 255.0F;
        float f = (color >> 16 & 0xFF) / 255.0F;
        float f1 = (color >> 8 & 0xFF) / 255.0F;
        float f2 = (color & 0xFF) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }


    public static void drawTracerLine(double x, double y, double z, float red, float green, float blue, float alpha,
                                      float lineWdith) {
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        Minecraft.getMinecraft().entityRenderer.orientCamera(Minecraft.getMinecraft().timer.renderPartialTicks);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0D, 0.0D + Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0D);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }


    public static void disableLighting() {
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
    }

    public void setWindowIcon(String px32, String px64) throws IOException {
        Display.setIcon(new ByteBuffer[]{loadIcon(new URL(px32)), loadIcon(new URL(px64))});
    }

    private ByteBuffer loadIcon(URL url) throws IOException {
        InputStream inputStream = url.openStream();
        Throwable var2 = null;

        ByteBuffer var5;
        try {
            PNGDecoder decoder = new PNGDecoder(inputStream);
            ByteBuffer buffer = ByteBuffer.allocateDirect(decoder.getWidth() * decoder.getHeight() * 4);
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buffer.flip();
            var5 = buffer;
        } catch (Throwable var14) {
            var2 = var14;
            throw var14;
        } finally {
            if (inputStream != null) {
                if (var2 != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable var13) {
                        var2.addSuppressed(var13);
                    }
                } else {
                    inputStream.close();
                }
            }

        }

        return var5;
    }

    public void drawBorderedRect(float x2, float y2, float x1, float y1, float width, int internalColor, int borderColor) {
        enableGL2D();
        glColor(internalColor);
        drawRect(x2 + width, y2 + width, x1 - width, y1 - width);
        glColor(borderColor);
        drawRect(x2 + width, y2, x1 - width, y2 + width);
        drawRect(x2, y2, x2 + width, y1);
        drawRect(x1 - width, y2, x1, y1);
        drawRect(x2 + width, y1 - width, x1 - width, y1);
        disableGL2D();
    }

    private void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }

    private void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    private void glColor(int hex) {
        float alpha = (float) (hex >> 24 & 255) / 255.0F;
        float red = (float) (hex >> 16 & 255) / 255.0F;
        float green = (float) (hex >> 8 & 255) / 255.0F;
        float blue = (float) (hex & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public void drawRect(float x2, float y2, float x1, float y1, float r2, float g2, float b2, float a2) {
        enableGL2D();
        GL11.glColor4f(r2, g2, b2, a2);
        drawRect(x2, y2, x1, y1);
        disableGL2D();
    }

    public void drawRect(float x2, float y2, float x1, float y1) {
        GL11.glBegin(7);
        GL11.glVertex2f(x2, y1);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y2);
        GL11.glVertex2f(x2, y2);
        GL11.glEnd();
    }

    public void drawLine(int fromX, int fromY, int toX, int toY, int steps, int color) {
        double count = 0.0D;
        double distX = toX - fromX;
        double distY = toY - fromY;
        double dist = Math.sqrt(distX * distX + distY * distY);

        for (int i = 0; (double) i < dist; i += steps) {
            ++count;
        }

        int i2 = 0;
        float red = (float) (color >> 16 & 255) / 255.0F;
        float blue = (float) (color >> 8 & 255) / 255.0F;
        float green = (float) (color & 255) / 255.0F;

        for (float alpha = (float) (color >> 24 & 255) / 255.0F; (double) i2 < count; ++i2) {
            GL11.glColor4d(red, blue, green, alpha);
            double x = (double) fromX + (double) i2 * (distX / count);
            double y = (double) fromY + (double) i2 * (distY / count);
            double x1 = (double) fromX + (double) (i2 + 1) * (distX / count);
            double y1 = (double) fromY + (double) (i2 + 1) * (distY / count);
            GL11.glLineWidth(3.0F);
            GL11.glDisable(2884);
            GL11.glDisable(3553);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glBegin(1);
            GL11.glVertex2d(x, y);
            GL11.glVertex2d(x1, y1);
            GL11.glEnd();
            GL11.glEnable(3553);
        }
    }
}
