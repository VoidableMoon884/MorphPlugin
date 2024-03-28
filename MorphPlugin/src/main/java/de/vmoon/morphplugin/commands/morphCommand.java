package de.vmoon.morphplugin.commands;

import jdk.tools.jmod.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class morphCommand implements CommandExecutor, TabCompleter {
    private EntityType currentEntity = null;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Dieser Befehl kann nur von Spielern verwendet werden.");
            return true;
        }

        Player player = (Player) commandSender;

        if (strings.length < 1) {
            commandSender.sendMessage("Bitte gib eine Entität an, in die du dich verwandeln möchtest.");
            return false;
        }

        String entityName = strings[0].toUpperCase();

        try {
            EntityType entityType = EntityType.valueOf(entityName);
            player.getWorld().spawnEntity(player.getLocation(), entityType); // Verwandlung in die ausgewählte Entität
            player.sendMessage("Du hast dich in " + entityType.name() + " verwandelt.");
        } catch (IllegalArgumentException e) {
            player.sendMessage("Ungültige Entität angegeben.");
            return false;
        }

        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            String query = args[0].toUpperCase();
            for (EntityType entityType : EntityType.values()) {
                if (entityType.isAlive() && entityType.name().startsWith(query)) {
                    completions.add(entityType.name());
                }
            }
        }
        return completions;
    }


}
