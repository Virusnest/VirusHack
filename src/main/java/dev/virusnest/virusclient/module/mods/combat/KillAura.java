package dev.virusnest.virusclient.module.mods.combat;

import dev.virusnest.virusclient.module.Module;
import net.minecraft.entity.Entity;

public class KillAura extends Module {
    public KillAura(){
        super("KillAura","Atack enemys all at onece", Category.COMBAT);
    }

    @Override
    public void onTick() {
        super.onTick();
        for (Entity entity : mc.world.getEntities()){
            mc.player.attack(entity);
        }
    }
}
