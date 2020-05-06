package com.luzinsk.prkr.command;

import com.luzinsk.prkr.Prkr;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckpointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender == null) {
            assert false;
            sender.sendMessage("Console can't retrieve checkpoints.");

        } else {
            player.teleport(Prkr.checkpointController.getCheckpoint(player));
            sender.sendMessage(Prkr.prkrPrefix + ChatColor.YELLOW + "Teleported to checkpoint.");
        }
        return true;
    }
}
