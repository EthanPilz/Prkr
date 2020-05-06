package com.luzinsk.prkr.Commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCheckpointCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Console can't set checkpoints.");
        } else {
            if (args[0] == "setcp") {
                if (sender.hasPermission("prkr.setcp")){

                }

            }
        }

        return true;
    }
}
