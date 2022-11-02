package dev.virusnest.virusclient.ui.screens.clickgui.setting;

import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.settings.Setting;
import dev.virusnest.virusclient.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPRef;

public class Component {
    public Setting setting;
    public ModuleButton parent;
    public int offset;
    protected MinecraftClient mc = MinecraftClient.getInstance();
    public Component(Setting setting, ModuleButton parent, int offset){
        this.parent = parent;
        this.setting = setting;
        this.offset = offset;
    }
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){

    }
    public void mouseClicked(double mouseX, double mouseY, int button){

    }
    public void mouseReleased(double mouseX, double mouseY, int button){

    }
    public boolean isHovered(double mouseX, double mouseY){
        return mouseX > parent.parent.x && mouseX < parent.parent.x+ parent.parent.width && mouseY > parent.parent.y +parent.offset +offset && mouseY < parent.parent.y + parent.parent.height +offset+parent.offset;
    }
}
