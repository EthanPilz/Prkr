package com.luzinsk.prkr.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    private static final String prkrPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "Prkr" + ChatColor.DARK_GRAY + "] " + ChatColor.YELLOW;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (sender.hasPermission("prkr.help")) {
                if (args[0] == "help") {
                    if (args.length == 2){
                        sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.GREEN + ""
                                + ChatColor.BOLD + " Prkr " + ChatColor.RESET + ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--------------------");
                        sender.sendMessage(prkrPrefix + "we will fill this shit in laterrrrrr");
                    }
                }
        }
        return true;
    }

}
