package Moon.modules;

import Moon.event.EventBus;
import Moon.event.value.Mode;
import Moon.event.value.Numbers;
import Moon.event.value.Option;
import Moon.event.value.Value;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Module {
    public String modName;
    public String description;
    private String suffix = "";
    private boolean enabled = false;
    public boolean enabledOnStartup = false;
    private int key = 0;
    public List<Value> values = new ArrayList();
    public ModuleType type;
    public Minecraft mc = Minecraft.getMinecraft();
    public static Random random = new Random();

    public float animX, animY;

    public float optionAnim = 0;// present
    public float optionAnimNow = 0;// present

    public String getName() {
        return this.modName;
    }

    public ModuleType getType() {
        return this.type;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(Object obj) {
        String suffix = obj.toString();
        this.suffix = suffix;

    }

    public Module(String name, ModuleType mt) {
        this.modName = name;
        this.type = mt;
    }

    public Module(String name, String description, ModuleType mt) {
        this.modName = name;
        this.description = description;
        this.type = mt;
    }

    public void setEnabledWithoutInfo(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            this.onEnable();
            EventBus.getInstance().register(new Object[]{this});
            if (mc.theWorld != null) {
                mc.thePlayer.playSound("random.click", 0.5f, 1);
            }
            ModuleManager.enabledModules.add(this);
        } else {
            EventBus.getInstance().unregister(new Object[]{this});
            if (mc.theWorld != null) {
                mc.thePlayer.playSound("random.click", 0.4f, 0.8f);
            }
            this.onDisable();
            ModuleManager.enabledModules.remove(this);

        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            this.onEnable();
            EventBus.getInstance().register(new Object[]{this});
            if (mc.theWorld != null) {
                mc.thePlayer.playSound("random.click", 0.5f, 1);
            }
            ModuleManager.enabledModules.add(this);
        } else {
            EventBus.getInstance().unregister(new Object[]{this});
            if (mc.theWorld != null) {
                mc.thePlayer.playSound("random.click", 0.4f, 0.8f);
            }
            this.onDisable();
            ModuleManager.enabledModules.remove(this);

        }
    }

    protected void addValues(Value... values) {
        Value[] var5 = values;
        int var4 = values.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            Value value = var5[var3];
            if (value instanceof Numbers) {
                this.values.add(value);
            }
        }

        for (int var3 = 0; var3 < var4; ++var3) {
            Value value = var5[var3];
            if (value instanceof Option) {
                this.values.add(value);
            }
        }

        for (int var3 = 0; var3 < var4; ++var3) {
            Value value = var5[var3];
            if (value instanceof Mode) {
                this.values.add(value);
            }
        }

    }

    public List<Value> getValues() {
        return this.values;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public String getDescription() {
        return description;
    }
}