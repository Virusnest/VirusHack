package dev.virusnest.virusclient.mixin;

import dev.virusnest.virusclient.module.ModuleManager;
import dev.virusnest.virusclient.module.mods.exploit.OverflowBypass;
import dev.virusnest.virusclient.util.misc.RoundPosition;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PlayerMoveC2SPacket.PositionAndOnGround.class)
public abstract class PlayerPosPacketMixin {
    // Anti-human bypass
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V"))
    private static void init(Args args) {

            RoundPosition.onPositionPacket(args);

    }
}
