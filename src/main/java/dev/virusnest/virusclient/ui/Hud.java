package dev.virusnest.virusclient.ui;

import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Comparator;
import java.util.List;

public class Hud {

    private static MinecraftClient mc = MinecraftClient.getInstance();
    public static void render(MatrixStack matrices, float tickDelta){
        renderArrayList(matrices);
    }
    public static void renderArrayList(MatrixStack matrices){
        int index = 0;
        int sWidth = mc.getWindow().getScaledWidth();
        int sHeight = mc.getWindow().getScaledHeight();

        List<Module> enabled = ModuleManager.getEnabledModules();
        enabled.sort(Comparator.comparingInt(m -> (int)mc.textRenderer.getWidth(((Module)m).getName())).reversed());

        for(Module module : enabled){
            mc.textRenderer.drawWithShadow(matrices,module.getName(), 4, 10+(index * mc.textRenderer.fontHeight), -1);
            index++;
        }
    }
}
