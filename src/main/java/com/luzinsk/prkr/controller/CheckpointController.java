package com.luzinsk.prkr.controller;

import com.luzinsk.prkr.Prkr;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Optional;

public class CheckpointController {
    private HashMap<String, Location> checkpoints;
    private Prkr plugin = Prkr.getPlugin(Prkr.class);

    public CheckpointController(){
        checkpoints = new HashMap<>();

    }

    public void registerCheckpoint(Player player, Location location){
        checkpoints.put(player.getUniqueId().toString(), location);

        plugin.getConfig().set("Player Checkpoints." + player.getUniqueId().toString() + ".Yaw", location.getYaw());
        plugin.getConfig().set("Player Checkpoints." + player.getUniqueId().toString() + ".Pitch", location.getPitch());
        plugin.getConfig().set("Player Checkpoints." + player.getUniqueId().toString() + ".X", location.getX());
        plugin.getConfig().set("Player Checkpoints." + player.getUniqueId().toString() + ".Y", location.getY());
        plugin.getConfig().set("Player Checkpoints." + player.getUniqueId().toString() + ".Z", location.getZ());
        plugin.getConfig().set("Player Checkpoints." + player.getUniqueId().toString() + ".World", location.getWorld().getName());
        plugin.saveConfig();
    }

    public Optional<Location> getCheckpoint(Player player){

        if(checkpoints.containsKey(player.getUniqueId().toString())) {

            return Optional.of(checkpoints.get(player.getUniqueId().toString()));

        } else if (plugin.getConfig().contains("Player Checkpoints." + player.getUniqueId().toString())) {

            float yaw = plugin.getConfig().getInt("Player Checkpoints." + player.getUniqueId().toString() + ".Yaw");
            float pitch = plugin.getConfig().getInt("Player Checkpoints." + player.getUniqueId().toString() + ".Pitch");
            double x = (Double) plugin.getConfig().getDouble("Player Checkpoints." + player.getUniqueId().toString() + ".X");
            double y = (Double) plugin.getConfig().getDouble("Player Checkpoints." + player.getUniqueId().toString() + ".Y");
            double z = (Double) plugin.getConfig().getDouble("Player Checkpoints." + player.getUniqueId().toString() + ".Z");
            String wrld = plugin.getConfig().getString("Player Checkpoints." + player.getUniqueId().toString() + ".World");

            Location checkpointLoc = new Location(Bukkit.getWorld(wrld), x, y, z, yaw, pitch);
            return Optional.of(checkpointLoc);
        }
        else {
            return Optional.empty();
        }
    }
}
