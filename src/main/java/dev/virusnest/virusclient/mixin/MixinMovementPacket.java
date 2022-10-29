package dev.virusnest.virusclient.mixin;

import dev.virusnest.virusclient.VirusClient;
import dev.virusnest.virusclient.module.ModuleManager;
import dev.virusnest.virusclient.module.mods.exploit.OverflowBypass;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerMoveC2SPacket.class})
public class MixinMovementPacket {
    private static double lastModifyX;
    private static double lastModifyZ;

    @ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true) // Credits: https://github.com/ProtoByter/kaihack, doesn't glitch pitch and yaw + if rounding error we restore the last packet
    private static double modifyX(double value)
    {
        if(!ModuleManager.getModule(OverflowBypass.class).isEnabled()) return value;

        double modifyX = (double) (long)(value * 100.0) / 100.0;

        if (((long)modifyX * 1000) % 10 != 0) {
            VirusClient.LOGGER.info("Rounding error in \"X\"! Restoring last packet.");
            return lastModifyX;
        }
        lastModifyX = modifyX;
        return modifyX;
    }

    @ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 2, argsOnly = true)
    private static double modifyZ(double value)
    {
        if(!ModuleManager.getModule(OverflowBypass.class).isEnabled()) return value;

        double modifyZ = (double) (long)(value * 100.0) / 100.0;

        if (((long)modifyZ * 1000) % 10 != 0) {
            VirusClient.LOGGER.info("Rounding error in \"Z\"! Restoring last packet.");
            return lastModifyZ;
        }
        lastModifyZ = modifyZ;
        return modifyZ;
    }
}
