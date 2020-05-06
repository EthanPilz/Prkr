package com.luzinsk.prkr.command;


import com.luzinsk.prkr.Prkr;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCheckpointCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        sender.sendMessage("nix");
        Player player = (Player) sender;

        if (sender == null) {
            sender.sendMessage("Console can't set checkpoints.");
        } else {
            if (sender.hasPermission("prkr.setcp")) {
                Prkr.checkpointController.registerCheckpoint(player, player.getLocation());
                sender.sendMessage(Prkr.prkrPrefix + ChatColor.YELLOW + "Checkpoint set.");

                }
            }
        return true;
    }
}
