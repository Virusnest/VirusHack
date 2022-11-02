package dev.virusnest.virusclient.ui.screens.clickgui;

import dev.virusnest.virusclient.VirusClient;
import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.ModuleManager;
import dev.virusnest.virusclient.ui.screens.clickgui.setting.Component;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Frame {
    public int x,y,width,height,dragX,dragY;
    public Module.Category category;
    public boolean dragging,extended;
    private List<ModuleButton> buttons;
    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Frame(Module.Category category, int x, int y, int width, int height){
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dragging = false;
        this.extended = false;
        buttons = new ArrayList<>();

        int offset = height;
        for(Module module : ModuleManager.getModulesInCategory(category)) {
            buttons.add(new ModuleButton(module,this,offset));
            offset += height;

        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrices,x,y,x+width,y+height,Color.decode("#1e1e2e").getRGB());
        int textOffset = (height/2)-(mc.textRenderer.fontHeight/2);
        mc.textRenderer.drawWithShadow(matrices,category.name(),x+textOffset,y+textOffset,Color.decode("#cdd6f4").getRGB());
        mc.textRenderer.drawWithShadow(matrices,extended ? "-" : "+",x+width-textOffset-4,y+textOffset,Color.decode("#cdd6f4").getRGB());
        if(extended) {
            for (ModuleButton button : buttons) {
                button.render(matrices, mouseX, mouseY, delta);
            }
        }
    }
    public void mouseClicked(double mouseX, double mouseY, int button){
        if (isHovered(mouseX,mouseY)){
            if(button==0){
                dragging = true;
                dragX=(int)(mouseX-x);
                dragY=(int)(mouseY-y);
            }else if(button == 1){
                extended = !extended;
            }
        }
        if (extended) {
            for (ModuleButton mb : buttons) {
                mb.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public void mouseRelease(double mouseX,double mouseY, int button){
        if((button == 0 && dragging) == true) dragging = false;
        for(ModuleButton mb : buttons){
            mb.mouseReleased(mouseX,mouseY,button);
        }
    }
    public boolean isHovered(double mouseX, double mouseY){
        return mouseX > x && mouseX < x+ width && mouseY > y && mouseY < y + height ;
    }

    public void updatePosition(double mouseX, double mouseY){
        if(dragging){
            x=(int)(mouseX-dragX);
            y=(int)(mouseY-dragY);
        }
    }

    public void updateButtons() {
        int offset = height;
        for(ModuleButton button : buttons){
            button.offset = offset;
            offset += height;
            if(button.extended){
                for(Component component : button.components){
                    if(component.setting.isVisible()) offset += height;
                }
            }
        }
    }
}
