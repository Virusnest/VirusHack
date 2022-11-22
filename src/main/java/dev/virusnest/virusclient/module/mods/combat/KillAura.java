package dev.virusnest.virusclient.module.mods.combat;

import dev.virusnest.virusclient.VirusClient;
import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.settings.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerAbilities;

public class KillAura extends Module {
    NumberSetting range = new NumberSetting("Range", 1,10,3,0.5);
    public KillAura(){
        super("KillAura","Atack enemys all at onece", Category.COMBAT);
    }

    Boolean isOnCooldown = false;
    @Override
    public void onTick() {
        super.onTick();
        assert mc.player != null;
        if(mc.player.getHandItems().iterator().next()!=null) {
            isOnCooldown = mc.player.getAttackCooldownProgressPerTick() > 0f;
            VirusClient.LOGGER.info(isOnCooldown.toString());
        }
        for (Entity entity : mc.world.getEntities()){
            double dist = Math.sqrt(Math.pow(entity.getX(),2)+Math.pow(entity.getY(),2)+Math.pow(entity.getZ(),2));
            if(dist<range.getValue()){
                if(!isOnCooldown){
                    mc.player.attack(entity);
                }
            }
        }


    }
}
