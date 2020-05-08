package com.luzinsk.prkr.command;

import com.luzinsk.prkr.Prkr;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Optional;

public class CheckpointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender == null) {
            assert false;
            sender.sendMessage("Console can't retrieve checkpoints.");

        } else {
            Optional<Location> playerLocation = Optional.empty();
            try {
                playerLocation = Prkr.checkpointController.getCheckpoint(player);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (player.hasPermission("prkr.cp")) {

                if (playerLocation.isPresent()) {
                    player.teleport(playerLocation.get());
                    sender.sendMessage(Prkr.prkrPrefix + ChatColor.YELLOW + "Teleported to checkpoint.");
                    player.playSound(player.getLocation(), Sound.ENTITY_PARROT_IMITATE_ENDERMITE, 1, 1);
                } else {
                    sender.sendMessage(Prkr.prkrPrefix + ChatColor.RED + "No checkpoint set");
                }
            } else {
                sender.sendMessage(Prkr.prkrPrefix + ChatColor.RED + "Sorry, but you're missing permissions to do"
                        + ChatColor.AQUA + "" + ChatColor.BOLD + "cp" + ChatColor.RESET + ChatColor.RED + ". " + ChatColor.RED
                        + "You'll need" + ChatColor.GOLD + " prkr.cp " + ChatColor.RED + "to do that.");
                player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
            }

        }
        return true;
    }
}
