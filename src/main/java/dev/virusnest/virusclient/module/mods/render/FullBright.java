package dev.virusnest.virusclient.module.mods.render;

import dev.virusnest.virusclient.module.Module;
import org.lwjgl.glfw.GLFW;

public class FullBright extends Module {
    public FullBright() {
        super("Brightness", "Tweak The Brightness beyond vanilla limits", Category.RENDER);
        this.setKey(GLFW.GLFW_KEY_G);
    }
    private double brightness;
    @Override
    public void onTick() {
        super.onTick();
    }

    @Override
    public void onEnable() {
        brightness = mc.options.getGamma().getValue();
        mc.options.getGamma().setValue(10000d);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        mc.options.getGamma().setValue(brightness);
        super.onDisable();
    }
}
