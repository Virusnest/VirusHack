package dev.virusnest.virusclient.ui.screens.clickgui.setting;

import dev.virusnest.virusclient.module.settings.BooleanSetting;
import dev.virusnest.virusclient.module.settings.NumberSetting;
import dev.virusnest.virusclient.module.settings.Setting;
import dev.virusnest.virusclient.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends Component{
    private NumberSetting numSet = (NumberSetting)setting;
    private boolean sliding = false;
    public Slider(Setting setting, ModuleButton parent, int offset){
        super(setting,parent,offset);
        this.numSet = (NumberSetting)setting;
    }
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){
        super.render(matrices,mouseX,mouseY,delta);
        DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y +parent.offset+offset,parent.parent.x + parent.parent.width,parent.parent.y+ parent.parent.height+parent.offset+offset, new Color(0,0,0,160).getRGB());

        double diff = Math.min(parent.parent.width,Math.max(0,mouseX - parent.parent.x));
        int renderWidth = (int)(parent.parent.width * (numSet.getValue()-numSet.getMin())/(numSet.getMax()-numSet.getMin()));

        DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y +parent.offset+offset,parent.parent.x + renderWidth,parent.parent.y+ parent.parent.height+parent.offset+offset,Color.decode("#1e1e2e").getRGB());

        if(sliding) {
            if (diff == 0) {
                numSet.setValue(numSet.getMin());
            } else {
                numSet.setValue(roundToPlace(((diff/ parent.parent.width)*(numSet.getMax()-numSet.getMin())+numSet.getMin()),2));
            }
        }

        int textOffset = ((parent.parent.height/2)-mc.textRenderer.fontHeight/2);
        mc.textRenderer.drawWithShadow(matrices,numSet.getName()+": " + roundToPlace(numSet.getValue(),2), parent.parent.x+textOffset,parent.parent.y+textOffset+offset+parent.offset,Color.decode("#cdd6f4").getRGB());
    }
    @Override
    public void mouseClicked(double mouseX, double mouseY, int button){
        if(isHovered(mouseX,mouseY)) sliding = true;
        super.mouseClicked(mouseX,mouseY,button);

    }
    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        sliding = false;
        super.mouseReleased(mouseX, mouseY, button);
    }
    private double roundToPlace(double value, int place){
        if(place<0){
            return value;
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(place, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
