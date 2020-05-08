package com.luzinsk.prkr.command;

import com.luzinsk.prkr.Prkr;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    private static final String prkrPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "Prkr" + ChatColor.DARK_GRAY + "] " + ChatColor.YELLOW;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender.hasPermission("prkr.help")) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.GREEN + ""
                        + ChatColor.BOLD + " Prkr " + ChatColor.RESET + ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--------------------");
                sender.sendMessage(prkrPrefix + "we will fill this shit in laterrrrrr");

            } else {
                if (args[0].equalsIgnoreCase("help")) {
                    if (args.length == 1) {
                        sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.GREEN + ""
                                + ChatColor.BOLD + " Prkr " + ChatColor.RESET + ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--------------------");
                        sender.sendMessage(prkrPrefix + "we will fill this shit in laterrrrrr");

                    } else if (args.length == 2){
                        if (args[1].equalsIgnoreCase("2")){
                            sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.GREEN + ""
                                    + ChatColor.BOLD + " Prkr " + ChatColor.RESET + ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--------------------");
                            sender.sendMessage("Page 2");

                        } if (args[1].equalsIgnoreCase("1")){
                            sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.GREEN + ""
                                    + ChatColor.BOLD + " Prkr " + ChatColor.RESET + ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "--------------------");
                            sender.sendMessage("Page 1");
                        }
                    }
                }
            }

        }  else {
            sender.sendMessage(Prkr.prkrPrefix + ChatColor.RED + "Sorry, but you're missing permissions to do"
                    + ChatColor.AQUA + "" + ChatColor.BOLD + "help" + ChatColor.RESET + ChatColor.RED + ". " + ChatColor.RED
                    + "You'll need" + ChatColor.GOLD + " prkr.help " + ChatColor.RED + "to do that.");
            player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);

        } return true;

    }
}
