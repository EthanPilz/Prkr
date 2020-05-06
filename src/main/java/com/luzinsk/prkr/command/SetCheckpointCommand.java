package com.luzinsk.prkr.command;


import com.luzinsk.prkr.Prkr;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SetCheckpointCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;
        if(!(sender instanceof Player)){
            Bukkit.getLogger().log(Level.SEVERE,   ChatColor.RED + "Checkpoints cannot be set by console, clearly :)))))");
        }
        if (sender.hasPermission("prkr.setcp")) {
            Prkr.checkpointController.registerCheckpoint(player, player.getLocation());
            sender.sendMessage(Prkr.prkrPrefix + ChatColor.YELLOW + "Checkpoint set.");

            }
        return true;
    }
}
