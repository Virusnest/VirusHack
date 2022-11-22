package dev.virusnest.virusclient.module.mods.movement;

import dev.virusnest.virusclient.VirusClient;
import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.settings.BooleanSetting;
import dev.virusnest.virusclient.module.settings.ModeSetting;
import dev.virusnest.virusclient.module.settings.NumberSetting;
import dev.virusnest.virusclient.util.io.PacketHelper;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;

public class Flight extends Module{
    public NumberSetting speed = new NumberSetting("Speed",0.1, 5, 0.1, 0.1);
    public BooleanSetting antiKick = new BooleanSetting("AntiKick", false);
    public ModeSetting mode = new ModeSetting("FlightMode","Creative", "Creative","Packet");
    public Flight() {
        super("Flight", "Alows You to Fly", Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_BACKSLASH);
        addSettings(speed,antiKick,mode);
    }
    double oldY;
    int floatingTick;
    @Override
    public void onTick(){
        
        mc.player.getAbilities().allowFlying = true;
        mc.player.getAbilities().setFlySpeed(speed.getValueFloat());
        if(mc.player.getPos().getY()>=oldY-0.0433d){
            floatingTick++;
        }
        oldY = mc.player.getPos().getY();
        if((floatingTick >10 && antiKick.isEnabled())
                &&mc.player.world.getBlockState(new BlockPos(mc.player.getPos().subtract(0,0.0433d,0))).isAir()) {
            PacketHelper.sendPosition(mc.player.getPos().subtract(0.0,0.05d,0.0));
            floatingTick =0;
            VirusClient.LOGGER.info("fell");
        }
        super.onTick();
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.getAbilities().allowFlying = mc.player.isCreative();
            mc.player.getAbilities().flying = false;
        }
        super.onDisable();
    }
}
