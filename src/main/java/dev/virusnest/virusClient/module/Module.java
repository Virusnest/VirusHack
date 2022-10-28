package dev.virusnest.virusClient.module;

import jdk.jfr.Category;
import net.minecraft.client.MinecraftClient;

public class Module {

    private String name;
    private String description;
    public Category category;
    private int key;
    private boolean enabled;

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Module(String name, String description, Category category){
        this.name = name;
        this.description = description;
        this.category = category;
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


    public enum Category{
        COMBAT,
        MOVEMENT,
        RENDER,
        EXPLOIT,
        WORLD
    }

}
