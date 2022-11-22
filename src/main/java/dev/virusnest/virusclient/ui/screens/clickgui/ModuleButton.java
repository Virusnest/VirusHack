package dev.virusnest.virusclient.ui.screens.clickgui;

import com.mojang.datafixers.types.templates.Check;
import dev.virusnest.virusclient.VirusClient;
import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.mods.movement.Speed;
import dev.virusnest.virusclient.module.settings.BooleanSetting;
import dev.virusnest.virusclient.module.settings.ModeSetting;
import dev.virusnest.virusclient.module.settings.NumberSetting;
import dev.virusnest.virusclient.module.settings.Setting;
import dev.virusnest.virusclient.ui.screens.clickgui.setting.CheckBox;
import dev.virusnest.virusclient.ui.screens.clickgui.setting.Component;
import dev.virusnest.virusclient.ui.screens.clickgui.setting.ModeBox;
import dev.virusnest.virusclient.ui.screens.clickgui.setting.Slider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.world.gen.placementmodifier.EnvironmentScanPlacementModifier;

import java.awt.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class ModuleButton {
    public Module module;
    public Frame parent;
    public int offset;
    public boolean extended;
    public List<Component> components;
    int setOffset = offset;
    public ModuleButton(Module module, Frame parent, int offset) {
        this.module = module;
        this.parent = parent;
        this.offset = offset;
        this.extended = false;
        this.components = new ArrayList<>();
        for (Setting setting : module.getSettings()){
            setOffset += parent.height;
            if (setting instanceof BooleanSetting){
                components.add(new CheckBox(setting, this, this.setOffset));
            } else if (setting instanceof ModeSetting) {
                components.add(new ModeBox(setting, this, this.setOffset));
            }else if (setting instanceof NumberSetting) {
                components.add(new Slider(setting, this, this.setOffset));
            }
            VirusClient.LOGGER.info(this.setOffset + module.getName());
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){
        DrawableHelper.fill(matrices, parent.x, parent.y +offset,parent.x + parent.width,parent.y+ parent.height+offset, new Color(0,0,0,160).getRGB());
        if(isHovered(mouseX,mouseY)) DrawableHelper.fill(matrices, parent.x, parent.y +offset,parent.x + parent.width,parent.y+ parent.height+offset, new Color(0,0,0,160).getRGB());
        int textOffset = (parent.height/2)-(parent.mc.textRenderer.fontHeight/2);
        parent.mc.textRenderer.drawWithShadow(matrices,module.getName(),parent.x+textOffset, parent.y+offset+textOffset,module.isEnabled()? Color.decode("#89dceb").getRGB():Color.decode("#cdd6f4").getRGB());
        if(extended) {
            for (Component component : components) {
                component.render(matrices, mouseX, mouseY, delta);
            }
        }
    }
    public void mouseClicked(double mouseX, double mouseY, int button){
        if(isHovered(mouseX,mouseY)){
            if(button == 0){
                module.toggle();
            }else if(button == 1){
                extended = !extended;
                parent.updateButtons();
            }
        }
        for(Component component:components){
            component.mouseClicked(mouseX,mouseY,button);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button){
        for(Component component:components){
            component.mouseReleased(mouseX,mouseY,button);
        }
    }
    public boolean isHovered(double mouseX, double mouseY){
        return mouseX > parent.x && mouseX < parent.x+ parent.width && mouseY > parent.y +offset && mouseY < parent.y + parent.height +offset;
    }
}
