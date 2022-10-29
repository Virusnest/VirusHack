package dev.virusnest.virusclient.ui.screens.clickgui;

import dev.virusnest.virusclient.module.Module;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGui extends Screen {
    private List<Frame> frames;
    private ClickGui(){
        super(Text.literal("Click GUI"));
        frames = new ArrayList<>();
        int offset = 20;
        for(Module.Category category : Module.Category.values()){
            frames.add(new Frame(category,offset,30,100,30));
            offset+= 120;
        }
    }
    public static final ClickGui INSTANCE = new ClickGui();

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){
        super.render(matrices,mouseX,mouseY,delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button){
        return super.mouseClicked(mouseX,mouseY,button);
    }
}
