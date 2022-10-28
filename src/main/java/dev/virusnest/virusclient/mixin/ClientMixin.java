package dev.virusnest.virusclient.mixin;

import dev.virusnest.virusclient.VirusClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ClientMixin {

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setOverlay(Lnet/minecraft/client/gui/screen/Overlay;)V", shift = At.Shift.BEFORE))
    private void init(RunArgs args, CallbackInfo callback) {
        VirusClient.INSTANCE.postInit();
    }
    @Inject(method="tick", at = @At("HEAD"))
    public void onTick (CallbackInfo ci){
        VirusClient.INSTANCE.onTick();
    }
}
