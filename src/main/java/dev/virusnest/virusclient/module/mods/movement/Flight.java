package dev.virusnest.virusclient.module.mods.movement;

import dev.virusnest.virusclient.module.Module;
import org.lwjgl.glfw.GLFW;

public class Flight extends Module{

    public Flight() {
        super("Flight", "Alows You to Fly", Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_SLASH);
    }

    @Override
    public void onTick(){
        mc.player.getAbilities().allowFlying = true;
        super.onTick();
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.getAbilities().allowFlying = false;
            mc.player.getAbilities().flying = false;
        }
        super.onDisable();
    }
}
