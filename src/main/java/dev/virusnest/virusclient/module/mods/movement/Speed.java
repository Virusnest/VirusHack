package dev.virusnest.virusclient.module.mods.movement;

import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.settings.BooleanSetting;
import dev.virusnest.virusclient.module.settings.ModeSetting;
import dev.virusnest.virusclient.module.settings.NumberSetting;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class Speed extends Module{

    public NumberSetting speed = new NumberSetting("Speed",0, 10, 0, 0.1);
    private boolean jumping;

    public Speed() {
        super("Speed", "Increased Movement Speed", Module.Category.MOVEMENT);
        addSetting(speed);
    }

    @Override
    public void onTick(){

        if ((mc.player.forwardSpeed != 0 || mc.player.sidewaysSpeed != 0) /*&& mc.player.isOnGround()*/) {
            if (!mc.player.isSprinting()) {
                mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.START_SPRINTING));
            }

            mc.player.setVelocity(new Vec3d(0, mc.player.getVelocity().y, 0));
            mc.player.updateVelocity(speed.getValueFloat(),
                    new Vec3d(mc.player.sidewaysSpeed, 0, mc.player.forwardSpeed));

            double vel = Math.abs(mc.player.getVelocity().getX()) + Math.abs(mc.player.getVelocity().getZ());

        }
    }
    @Override
    public void onDisable() {
        super.onDisable();
    }
}
