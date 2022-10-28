package dev.virusnest.virusClient.mixin;

import dev.virusnest.virusClient.VirusClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ClientMixin {
    @Inject(method="tick", at = @At("HEAD"))
    public void onTick (CallbackInfo ci){
        VirusClient.INSTANCE.onTick();
    }
}
