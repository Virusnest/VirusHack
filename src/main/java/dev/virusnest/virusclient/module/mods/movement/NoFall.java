package dev.virusnest.virusclient.module.mods.movement;

import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.settings.ModeSetting;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

public class NoFall extends Module{
    public ModeSetting mode = new ModeSetting("Mode","Simple","Simple", "Packet");
    public NoFall() {
        super("NoFall", "Prevents Fall Damage", Module.Category.MOVEMENT);
        addSetting(mode);
    }
    @Override
    public void onTick(){
        if (mode.getMode() == "Simple") {
            ClientPlayerEntity player = mc.player;
            if(player.fallDistance <= (player.isFallFlying() ? 1 : 2))
                return;

            if(player.isFallFlying() && player.isSneaking()
                    && !isFallingFastEnoughToCauseDamage(player))
                return;

            player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
        }

        if (mc.player.fallDistance > 2.5f && mode.getMode() == "Packet" &&
                mc.world.getBlockState(mc.player.getBlockPos().add(
                        0, -1.5 + (mc.player.getVelocity().y * 0.1), 0)).getBlock() != Blocks.AIR) {
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(false));
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(
                    mc.player.getX(), mc.player.getY() - 420.69, mc.player.getZ(), true));
            mc.player.fallDistance = 0;
        }
    }
    private boolean isFallingFastEnoughToCauseDamage(ClientPlayerEntity player)
    {
        return player.getVelocity().y < -0.5;
    }
}
