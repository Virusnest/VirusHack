package dev.virusnest.virusclient.module;

import dev.virusnest.virusclient.VirusClient;
import dev.virusnest.virusclient.util.objects.NameableStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleManager {
    private static NameableStorage<Module> modules = new NameableStorage<>(Module::getName);

    public static void genModules(List<String> mods) {
        //VirusClient.LOGGER.info("mod");
        try {
            //VirusClient.LOGGER.info("mod1");
            for (String modString : mods) {
                //VirusClient.LOGGER.info("mod2");
                Class<?> moduleClass = Class.forName(String.format("dev.virusnest.virusclient.module.mods.%s",modString));
                try {
                    //VirusClient.LOGGER.info("mod3");
                    if (Module.class.isAssignableFrom(moduleClass)) {
                        try {
                            Module module = (Module) moduleClass.getConstructor().newInstance();

                            loadModule(module);
                            //VirusClient.LOGGER.info("mod4");
                            //VirusClient.LOGGER.info(modString);
                            //VirusClient.LOGGER.info(module.getName());
                        } catch (Exception exception) {
                            VirusClient.LOGGER.error("Failed to load module "+moduleClass+": could not instantiate.");
                            exception.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    VirusClient.LOGGER.error("Failed To Load Module From Class %s: Could not find class "+ modString, moduleClass);
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadModule(Module module) {
        if (!modules.add(module))
            VirusClient.LOGGER.error("Failed to load module %s: a module with this name is already loaded.", module.getName());
    }

    public static Iterable<Module> getModules() {
        return modules.values();
    }

    public static Module getModule(String name) {
        return modules.get(name);
    }

    public static <M extends Module> M getModule(Class<M> class_) {
        return modules.get(class_);
    }

    public static Iterable<Module> getEnabledModules(){
        List<Module> enabled = new ArrayList<>();
        for(Module module : getModules()) {
            //VirusClient.LOGGER.info(module.toString());
            if (module.isEnabled()){
                enabled.add(module);
            }
        }
        return enabled;
    }

    public static List<String> modules(){
        return Arrays.asList(
                "movement.Flight",
                "exploit.OverflowBypass",
                "render.FullBright"
        );
    }
}
