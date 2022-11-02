package dev.virusnest.virusclient.module;

import dev.virusnest.virusclient.module.settings.Setting;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private String name;
    private String description;
    public Category category;
    private int key;
    private boolean enabled;
    private List<Setting> settings = new ArrayList<>();

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Module(String name, String description, Category category){
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public List<Setting> getSettings(){
        return settings;
    }
    public void addSetting(Setting setting){
        settings.add(setting);
    }
    public void addSettings(Setting... settings){
        for (Setting setting : settings) addSetting(setting);;
    }
    public void toggle(){
        enabled = !enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public void onDisable(){

    }
    public void onEnable(){

    }

    public void onTick(){

    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getKey() {
        return key;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public Category getCategory(){
        return category;
    }

    public enum Category{
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        RENDER("Render"),
        EXPLOIT("Exploit"),
        WORLD("World");

        public String name;
        private Category(String name){
            this.name = name;
        }
    }

}
