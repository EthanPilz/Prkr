package com.luzinsk.prkr;

import com.luzinsk.prkr.command.CheckpointCommand;
import com.luzinsk.prkr.command.HelpCommand;
import com.luzinsk.prkr.command.SetCheckpointCommand;
import com.luzinsk.prkr.controller.CheckpointController;
import com.luzinsk.prkr.files.DataManager;
import com.luzinsk.prkr.listener.PlayerSignListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public class Prkr extends JavaPlugin {

    public static final String prkrPrefix = ChatColor.AQUA + "[Prkr] ";
    public DataManager data;
    public static CheckpointController checkpointController;

    @Override
    public void onEnable(){



        //Logger
        Bukkit.getLogger().log(Level.INFO, prkrPrefix + ChatColor.YELLOW + "Prkr enabled.");

        //Listener
        getServer().getPluginManager().registerEvents(new PlayerSignListener(), this);

        //Controller
        checkpointController = new CheckpointController();

        //Files
       // this.data = new DataManager(this);

        //Commands
        getCommand("prkr").setExecutor(new HelpCommand());
        getCommand("setcp").setExecutor(new SetCheckpointCommand());
        getCommand("cp").setExecutor(new CheckpointCommand());

    }

    @Override
    public void onDisable(){

        Bukkit.getLogger().log(Level.INFO, prkrPrefix + ChatColor.YELLOW + "Prkr disabled.");

    }


}
