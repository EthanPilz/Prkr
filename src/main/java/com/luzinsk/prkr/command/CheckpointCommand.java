package com.luzinsk.prkr.command;

import com.luzinsk.prkr.Prkr;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
            Optional<Location> playerLocation = null;
            try {
                playerLocation = Prkr.checkpointController.getCheckpoint(player);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(playerLocation.isPresent()){
                player.teleport(playerLocation.get());
                sender.sendMessage(Prkr.prkrPrefix + ChatColor.YELLOW + "Teleported to checkpoint.");
            } else {
                sender.sendMessage(Prkr.prkrPrefix + ChatColor.RED + "No checkpoint set");
            }

        }
        return true;
    }
}
