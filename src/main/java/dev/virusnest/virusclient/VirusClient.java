package dev.virusnest.virusclient;

import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.ModuleManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VirusClient implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("virusclient");
	public static final VirusClient INSTANCE = new VirusClient();
	private MinecraftClient mc = MinecraftClient.getInstance();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		//getClass().getClassLoader().getResourceAsStream("");
		// LOGGER.info("!");
	}

	public void postInit() {
		//LOGGER.info("Postinit");
		ModuleManager.genModules(ModuleManager.modules());
		//LOGGER.info("Postinit");
	}
	public void onTick(){
		if (mc.player != null){
			for(Module module : ModuleManager.getEnabledModules()){
				module.onTick();
				//LOGGER.info("Ticked");
			}
		}
	}

	public void onKeyPress(int key, int action){
		if(action == GLFW.GLFW_PRESS){
			for(Module module : ModuleManager.getModules()){
				if (key == module.getKey()) module.toggle();
				//LOGGER.info(key+" Was Pressed");
			}
		}
	}
}
