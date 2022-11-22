package dev.virusnest.virusclient.util.io;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.virusnest.virusclient.VirusClient;
import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.ModuleManager;
import dev.virusnest.virusclient.module.settings.BooleanSetting;
import dev.virusnest.virusclient.module.settings.ModeSetting;
import dev.virusnest.virusclient.module.settings.NumberSetting;
import dev.virusnest.virusclient.module.settings.Setting;
import dev.virusnest.virusclient.ui.screens.clickgui.Frame;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ConfigManager {
    public static void createConfig() throws IOException{
        File modFile= new File(FabricLoader.getInstance().getGameDirectory(),"virusConf.json");
        File catFile= new File(FabricLoader.getInstance().getGameDirectory(),"clickGuiConf.json");
        if (!catFile.exists()&&!modFile.exists()) {
            catFile.createNewFile();
            modFile.createNewFile();
            saveConfig();
        }
    }
    public static void saveConfig() throws IOException {
        File file= new File(FabricLoader.getInstance().getGameDirectory(),"virusConf.json");
        FileOutputStream fileO = new FileOutputStream(file);
        JsonObject moduleObj = new JsonObject();
        for (Module module: ModuleManager.getModules()){
            JsonObject mod = new JsonObject();
            JsonObject set = new JsonObject();
            for(Setting setting :module.getSettings()){
                if(setting instanceof NumberSetting){
                    set.addProperty(setting.getName(),((NumberSetting) setting).getValue());
                }else if (setting instanceof BooleanSetting){
                    set.addProperty(setting.getName(),((BooleanSetting) setting).isEnabled());
                }else if (setting instanceof ModeSetting){
                    set.addProperty(setting.getName(),((ModeSetting) setting).getMode());
                }
            }
            mod.addProperty("Enabled",module.isEnabled());
            mod.add("Settings",set);
            moduleObj.add(module.getName(), mod);
        }
        fileO.write(moduleObj.toString().getBytes());
        fileO.close();

    }

    public static void saveClickGui(List<Frame> frames) throws IOException{
        File file= new File(FabricLoader.getInstance().getGameDirectory(),"clickGuiConf.json");
        FileOutputStream fileO = new FileOutputStream(file);
        JsonObject clickGuiObj = new JsonObject();
        for(Frame frame:frames){
            JsonObject frameObj = new JsonObject();
            JsonArray frameXY = new JsonArray();
            frameXY.add(frame.x);
            frameXY.add(frame.y);
            frameObj.add("Pos",frameXY);
            frameObj.addProperty("Extended",frame.extended);
            clickGuiObj.add(frame.category.name,frameObj);
        }

        fileO.write(clickGuiObj.toString().getBytes());
        fileO.close();
    }
    public static void loadConfig() throws IOException{
        File file= new File(FabricLoader.getInstance().getGameDirectory(),"virusConf.json");
        FileInputStream fileI = new FileInputStream(file);
        String s = new String(fileI.readAllBytes(), StandardCharsets.UTF_8);
        JsonParser parser = new JsonParser();
        Object parsedobj = parser.parse(s);
        JsonObject obj = (JsonObject)parsedobj;
        for (Module module:ModuleManager.getModules()){
            try {
                JsonObject jsonModule = obj.get(module.getName()).getAsJsonObject();
                module.setEnabled(jsonModule.get("Enabled").getAsBoolean());
                JsonObject jsonModSetting = jsonModule.get("Settings").getAsJsonObject();

                for (Setting setting : module.getSettings()) {
                    if (setting instanceof NumberSetting) {
                        ((NumberSetting) setting).setValue(jsonModSetting.get(setting.getName()).getAsDouble());
                    } else if (setting instanceof BooleanSetting) {
                        ((BooleanSetting) setting).setEnabled(jsonModSetting.get(setting.getName()).getAsBoolean());
                    } else if (setting instanceof ModeSetting) {
                        ((ModeSetting) setting).setMode(jsonModSetting.get(setting.getName()).getAsString());
                    }
                }
            }catch(NullPointerException e){
            VirusClient.LOGGER.error("Failed to Load Module Config: "+module.getName());
        }
        }
        fileI.close();
    }
}
