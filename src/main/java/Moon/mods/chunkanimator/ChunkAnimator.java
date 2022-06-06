package Moon.mods.chunkanimator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.RenderChunk;

import java.util.WeakHashMap;

public class ChunkAnimator {
    public static boolean isEnabled = true;
    private final Minecraft mc;
    private final WeakHashMap<RenderChunk, Long> timeStamps = new WeakHashMap<>();

    public ChunkAnimator(Minecraft mc) {
        this.mc = mc;
    }

    public void preRender(RenderChunk renderChunk) {
        if (isEnabled) {
            if (this.timeStamps.containsKey(renderChunk)) {
                long time = this.timeStamps.get(renderChunk);
                if (time == -1L) {
                    time = System.currentTimeMillis();
                    this.timeStamps.put(renderChunk, time);
                }

                long timeDif = System.currentTimeMillis() - time;
                if (timeDif < 1000L) {
                    double chunkY = renderChunk.getPosition().getY();
                    double modY = chunkY / 1000.0D * (double) timeDif;
                    GlStateManager.translate(0.0D, -chunkY + modY, 0.0D);
                } else {
                    this.timeStamps.remove(renderChunk);
                }
            }

        }
    }

    public void setPosition(RenderChunk renderChunk) {
        if (this.mc.thePlayer != null) {
            this.timeStamps.put(renderChunk, -1L);
        }

    }
}
