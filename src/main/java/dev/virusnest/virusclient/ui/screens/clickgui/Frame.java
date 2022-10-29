package dev.virusnest.virusclient.ui.screens.clickgui;

import dev.virusnest.virusclient.module.Module;
import net.minecraft.client.util.math.MatrixStack;

public class Frame {
    public int x,y,width,height;
    public Module.Category category;

    public boolean dragging;

    public Frame(Module.Category category, int x, int y, int width, int height){
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dragging = false;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

    }
    public void mouseClicked(double mouseX, double mouseY, int button){

    }

}
