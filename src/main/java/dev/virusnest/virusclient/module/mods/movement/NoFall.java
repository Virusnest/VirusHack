package dev.virusnest.virusclient.module.mods.movement;

import dev.virusnest.virusclient.module.Module;
import net.minecraft.block.Blocks;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

public class NoFall extends Module{
    public NoFall() {
        super("NoFall", "Prevents Fall Damage", Module.Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_BACKSLASH);
    }
    @Override
    public void onTick(){
        if (mc.player.fallDistance > 2.5f ) {
            if (mc.player.isFallFlying())
                return;
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
        }

        if (mc.player.fallDistance > 2.5f &&
                mc.world.getBlockState(mc.player.getBlockPos().add(
                        0, -1.5 + (mc.player.getVelocity().y * 0.1), 0)).getBlock() != Blocks.AIR) {
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(false));
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                    mc.player.getX(), mc.player.getY() - 420.69, mc.player.getZ(), true));
            mc.player.fallDistance = 0;
        }
    }
}
