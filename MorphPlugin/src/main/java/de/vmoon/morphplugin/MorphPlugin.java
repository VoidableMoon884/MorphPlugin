package de.vmoon.morphplugin;

import de.vmoon.morphplugin.commands.morphCommand;
import org.bukkit.plugin.java.JavaPlugin;


public final class MorphPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("morph").setExecutor(new morphCommand());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
