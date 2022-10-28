package dev.virusnest.virusclient.mixin;

import dev.virusnest.virusclient.VirusClient;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeybindMixin {

    @Inject(method = "onKey", at = @At("HEAD"))
    public void onKey(long window, int key, int scanscore, int action, int modifers, CallbackInfo ci){
        VirusClient.INSTANCE.onKeyPress(key,action);
    }
}
