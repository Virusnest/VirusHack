package dev.virusnest.virusclient.module.mods.world;

import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.settings.BooleanSetting;

import net.minecraft.text.Text;

import java.sql.Timestamp;

public class DeathLogs extends Module {
    BooleanSetting timeStamp = new BooleanSetting("TimeStamp", false);
    public DeathLogs(){
        super("DeathLogs","Logs Info About Your Death", Category.WORLD);
        addSetting(timeStamp);
    }

    boolean once = false;

    void sendDeathMessage(){
        StringBuilder stringBuilder = new StringBuilder();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        stringBuilder.append("You Died at: §b§nX: ");
        stringBuilder.append(Math.floor(mc.player.getX())+" Y: "+ Math.floor(mc.player.getY())+" Z: "+Math.floor(mc.player.getZ()));
        mc.inGameHud.getChatHud().addMessage(Text.of( stringBuilder.toString()));
        if(timeStamp.isEnabled()) {
            mc.inGameHud.getChatHud().addMessage(Text.of("Time: §b§n"+time.toString()));
        }
    }
    Timestamp time = new Timestamp(System.currentTimeMillis());
    @Override
    public void onTick() {
        super.onTick();
        if(mc.player.getHealth() <= 0&&!once){
            sendDeathMessage();
            once = true;
        }else if (!(mc.player.getHealth() <= 0)&&once){
            once = false;
        }
    }
}
