package Moon.modules;

import Moon.event.EventBus;
import Moon.event.EventHandler;
import Moon.event.events.misc.EventKey;
import Moon.event.events.rendering.EventRender2D;
import Moon.event.events.rendering.EventRender3D;
import Moon.event.events.world.EventTick;
import Moon.event.value.Mode;
import Moon.event.value.Numbers;
import Moon.event.value.Option;
import Moon.event.value.Value;
import Moon.managers.Manager;
import Moon.modules.impl.ClientSettings;
import Moon.utils.FileManager;
import Moon.utils.render.gl.GLUtils;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import Moon.modules.impl.combat.*;
import Moon.modules.impl.movement.*;
import Moon.modules.impl.world.*;
import Moon.modules.impl.misc.*;

public class ModuleManager implements Manager {
    public static List<Module> modules = new ArrayList<Module>();
    public static List<Module> enabledModules = new ArrayList<Module>();

    private boolean enabledNeededMod = true;

    @Override
    public void init() {

        modules.add(new Killaura());
        modules.add(new AntiVelocity());
        modules.add(new Regen());
        modules.add(new AutoArmor());
        modules.add(new AutoHeal());
        modules.add(new FastBow());
        modules.add(new BowAimBot());
        modules.add(new AntiBots());
        modules.add(new TpAura());
        modules.add(new Criticals());

        // Render

        modules.add(new Fly());
        modules.add(new Speed());

        modules.add(new Sprint());

        modules.add(new InvMove());

        modules.add(new AntiVoid());
        modules.add(new SafeWalk());


        // World
        modules.add(new ChestStealer());
        modules.add(new Scaffold());


        // Ghost


        // Misc

        modules.add(new AntiAim());

        modules.add(new NoRotate());
        modules.add(new ClientSettings());


        EventBus.getInstance().register(this);
    }

    private static void sortModules() {

    }

    public static List<Module> getModules() {
        return modules;
    }

    public synchronized static Module getModuleByClass(Class<? extends Module> cls) {
        for (Module m : modules) {
            if (m.getClass() != cls)
                continue;
            return m;
        }

        System.out.println("一个功能没有获取到:" + cls.getName());
        return modules.get(0);
    }

    public static Module getModuleByName(String name) {
        for (Module m : modules) {
            if (!m.getName().equalsIgnoreCase(name))
                continue;
            return m;
        }
        return null;
    }

    public List<Module> getModulesInType(ModuleType t) {
        ArrayList<Module> output = new ArrayList<Module>();
        for (Module m : modules) {
            if (m.getType() != t)
                continue;
            output.add(m);
        }
        return output;
    }

    @EventHandler
    private void onKeyPress(EventKey e) {
        for (Module m : modules) {
            if (m.getKey() != e.getKey())
                continue;
            m.setEnabled(!m.isEnabled());
        }
    }


    @EventHandler
    public void onTick(EventTick e) {
    }

    @EventHandler
    private void onGLHack(EventRender3D e) {
        GlStateManager.getFloat(2982, (FloatBuffer) GLUtils.MODELVIEW.clear());
        GlStateManager.getFloat(2983, (FloatBuffer) GLUtils.PROJECTION.clear());
//        GlStateManager.glGetInteger(2978, (IntBuffer) GLUtils.VIEWPORT.clear());
    }


    public static String getShitString(int length) {
        String str = "456g4fdgh98637156df4g69874dfgf44gfd56g4f5d6g";
        return str.substring(0, length);
    }

    @EventHandler
    private void on2DRender(EventRender2D e) {
        if (this.enabledNeededMod) {
            this.enabledNeededMod = false;
            for (Module m : modules) {
                if (!m.enabledOnStartup)
                    continue;
                m.setEnabled(true);
            }
        }
    }

    public void readSettings() {
        List<String> binds = FileManager.read("Binds.txt");
        for (String v : binds) {
            String name = v.split(":")[0];
            String bind = v.split(":")[1];
            Module m = ModuleManager.getModuleByName(name);
            if (m == null)
                continue;
            m.setKey(Keyboard.getKeyIndex(bind.toUpperCase()));
        }
        List<String> enabled = FileManager.read("Enabled.txt");
        for (String v : enabled) {
            Module m = ModuleManager.getModuleByName(v);
            if (m == null)
                continue;
            m.enabledOnStartup = true;
        }
        List<String> vals = FileManager.read("Values.txt");
        for (String v : vals) {
            String name = v.split(":")[0];
            String values = v.split(":")[1];
            Module m = ModuleManager.getModuleByName(name);
            if (m == null)
                continue;
            for (Value value : m.getValues()) {
                if (!value.getName().equalsIgnoreCase(values))
                    continue;
                if (value instanceof Option) {
                    value.setValue(Boolean.parseBoolean(v.split(":")[2]));
                    continue;
                }
                if (value instanceof Numbers) {
                    value.setValue(Double.parseDouble(v.split(":")[2]));
                    continue;
                }
                ((Mode) value).setMode(v.split(":")[2]);
            }
        }
    }

    public void saveSettings() {
        StringBuilder content = new StringBuilder();

        for (Module m : modules) {
            content.append(
                    String.format("%s:%s%s", m.getName(), Keyboard.getKeyName(m.getKey()), System.lineSeparator()));
        }

        FileManager.save("Binds.txt", content.toString(), false);

        String values = "";
        for (Module m : ModuleManager.getModules()) {
            for (Value v : m.getValues()) {
                values = String.valueOf(values)
                        + String.format("%s:%s:%s%s", m.getName(), v.getName(), v.getValue(), System.lineSeparator());
            }
        }
        FileManager.save("Values.txt", values, false);
        String enabled = "";
        for (Module m : ModuleManager.getModules()) {
            if (!m.isEnabled())
                continue;
            enabled = String.valueOf(enabled) + String.format("%s%s", m.getName(), System.lineSeparator());
        }
        FileManager.save("Enabled.txt", enabled, false);
    }

    public void saveSettings(String text) {
        String values = "";
        for (Module m : ModuleManager.getModules()) {
            for (Value v : m.getValues()) {
                values = String.valueOf(values)
                        + String.format("%s:%s:%s%s", m.getName(), v.getName(), v.getValue(), System.lineSeparator());
            }
        }
        FileManager.save("Values.txt", values, false);
        String enabled = "";
        for (Module m : ModuleManager.getModules()) {
            if (!m.isEnabled())
                continue;
            enabled = String.valueOf(enabled) + String.format("%s%s", m.getName(), System.lineSeparator());
        }
        FileManager.save("Enabled.txt", enabled, false);
    }
}
