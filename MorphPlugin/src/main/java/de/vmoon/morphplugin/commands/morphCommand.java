package de.vmoon.morphplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class morphCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Dieser Befehl kann nur von Spielern verwendet werden.");
            return true;
        }

        if (strings.length < 1) {
            commandSender.sendMessage("Bitte gib eine Entität an, in die du dich verwandeln möchtest.");
            return false;
        }

        Player player = (Player) commandSender;
        String entityName = strings[0].toLowerCase(); // Entität in Kleinbuchstaben konvertieren

        try {
            EntityType entityType = EntityType.valueOf(entityName.toUpperCase());
            player.getWorld().spawnEntity(player.getLocation(), entityType);
            player.sendMessage("Du hast dich in " + entityType.name() + " verwandelt.");
        } catch (IllegalArgumentException e) {
            commandSender.sendMessage("Ungültige Entität angegeben.");
            return false;
        }

        return true;
    }
}
