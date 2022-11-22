package dev.virusnest.virusclient.ui.screens.clickgui.setting;

import dev.virusnest.virusclient.module.settings.BooleanSetting;
import dev.virusnest.virusclient.module.settings.ModeSetting;
import dev.virusnest.virusclient.module.settings.Setting;
import dev.virusnest.virusclient.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class ModeBox extends Component{
    private ModeSetting modeSet = (ModeSetting)setting;
    public ModeBox(Setting setting, ModuleButton parent, int offset){
        super(setting,parent,offset);
        this.modeSet = (ModeSetting) setting;
    }
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){
        super.render(matrices,mouseX,mouseY,delta);
        DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y +parent.offset+offset,parent.parent.x + parent.parent.width,parent.parent.y+ parent.parent.height+parent.offset+offset, new Color(0,0,0,160).getRGB());
        if(isHovered(mouseX,mouseY)) DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y +parent.offset+offset,parent.parent.x + parent.parent.width,parent.parent.y+ parent.parent.height+parent.offset+offset, new Color(0,0,0,160).getRGB());
        int textOffset = (parent.parent.height/2)-(mc.textRenderer.fontHeight/2);
        mc.textRenderer.drawWithShadow(matrices,modeSet.getName()+": " + modeSet.getMode(), parent.parent.x+textOffset,parent.parent.y+textOffset+offset+parent.offset,Color.decode("#cdd6f4").getRGB());
    }
    @Override
    public void mouseClicked(double mouseX, double mouseY, int button){
        super.mouseClicked(mouseX,mouseY,button);
        if(isHovered(mouseX,mouseY)&&button==0) {
            modeSet.cycle();
        }
    }
}
