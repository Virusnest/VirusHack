package dev.virusnest.virusclient.ui.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Hud {

    private static MinecraftClient mc = MinecraftClient.getInstance();
    public static void render(MatrixStack matrices, float tickDelta){
        mc.textRenderer.drawWithShadow(matrices, "Test", 10 ,10, -1);

    }
}
