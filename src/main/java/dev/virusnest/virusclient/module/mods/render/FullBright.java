package dev.virusnest.virusclient.module.mods.render;

import dev.virusnest.virusclient.module.Module;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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
        mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 500, 0));
    }
}
