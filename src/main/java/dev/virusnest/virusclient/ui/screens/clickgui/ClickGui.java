package dev.virusnest.virusclient.ui.screens.clickgui;

import dev.virusnest.virusclient.module.Module;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGui extends Screen {
    private List<Frame> frames;
    @Override
    public boolean shouldPause() {
        return false;
    }
    private ClickGui(){
        super(Text.literal("Click GUI"));
        frames = new ArrayList<>();
        int offset = 20;
        for(Module.Category category : Module.Category.values()){
            frames.add(new Frame(category,offset,20,100,20));
            offset+= 120;
        }
    }
    public static final ClickGui INSTANCE = new ClickGui();

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){
        for(Frame frame : frames){
            frame.render(matrices,mouseX,mouseY,delta);
            frame.updatePosition(mouseX,mouseY);
        }
        super.render(matrices,mouseX,mouseY,delta);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY,int button){
        for (Frame frame :frames){
            frame.mouseRelease(mouseX,mouseY,button);
        }
        return super.mouseReleased(mouseX,mouseY,button);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button){
        for (Frame frame :frames){
            frame.mouseClicked(mouseX,mouseY,button);
        }
        return super.mouseClicked(mouseX,mouseY,button);
    }
}
