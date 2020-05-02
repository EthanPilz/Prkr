package com.luzinsk.prkr;

import com.luzinsk.prkr.Commands.HelpCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Prkr extends JavaPlugin {

    private static final String prkrPrefix = ChatColor.AQUA + "[Prkr] ";

    @Override
    public void onEnable(){


        getCommand("help").setExecutor(new HelpCommand());

    }

    @Override
    public void onDisable(){

        Bukkit.getLogger().log(Level.INFO, prkrPrefix + ChatColor.RED + "Prkr disabled.");

    }


}
