package dev.virusnest.virusClient.mixin;

import dev.virusnest.virusClient.module.ModuleManager;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerMoveC2SPacket.class})
public class MixinMovementPacket {
    @Shadow
    @Mutable
    @Final
    protected double x;

    @Shadow
    @Mutable
    @Final
    protected double z;

    @Inject(at = @At("RETURN"), method = "<init>*")
    public void onMovePacket(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean changePosition, boolean changeLook, CallbackInfo ci) {

        if (true) {
            this.x = (((long) x * 100) / 100.0);
            this.z = (((long) z * 100) / 100.0);
        }
    }
}
