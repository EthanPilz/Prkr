package com.luzinsk.prkr.command;

import com.luzinsk.prkr.Prkr;
import com.luzinsk.prkr.components.PlayerCheckpoint;
import com.luzinsk.prkr.exceptions.SaveToDatabaseException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.logging.Level;

public class SetCheckpointCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;
        if(!(sender instanceof Player)){
            Bukkit.getLogger().log(Level.SEVERE,   ChatColor.RED + "Checkpoints cannot be set by console, clearly :)))))");
        }
        if (sender.hasPermission("prkr.setcp")) {

            PlayerCheckpoint cp = new PlayerCheckpoint(player, player.getLocation());

            try {
                Prkr.checkpointController.registerCheckpoint(cp);
            } catch (SaveToDatabaseException | SQLException e) {
                e.printStackTrace();
            }



            } else {

            sender.sendMessage(Prkr.prkrPrefix + ChatColor.RED + "Sorry, but you're missing permissions to do"
                + ChatColor.AQUA + "" + ChatColor.BOLD + "setcp" + ChatColor.RESET + ChatColor.RED + ". " + ChatColor.RED
                    + "You'll need" + ChatColor.GOLD + " prkr.setcp " + ChatColor.RED + "to do that.");
            player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);

            }
        return true;
    }
}
