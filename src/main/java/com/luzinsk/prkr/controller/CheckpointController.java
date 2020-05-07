package com.luzinsk.prkr.controller;

import com.luzinsk.prkr.Prkr;
import com.luzinsk.prkr.components.PlayerCheckpoint;
import com.luzinsk.prkr.io.InputOutput;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class CheckpointController {
    private HashMap<String, Location> checkpoints;
    private Prkr plugin = Prkr.getPlugin(Prkr.class);

    public CheckpointController(){
        checkpoints = new HashMap<>();

    }

    public void registerCheckpoint(PlayerCheckpoint cp) {
        checkpoints.put(cp.getUUID(), cp.getLocation());
        Prkr.inputOutput.storePlayerCheckpoint(cp);
    }

    public Optional<Location> getCheckpoint(Player player) throws SQLException {
        player.sendMessage("bob gcp");

        if(checkpoints.containsKey(player.getUniqueId().toString())) {

            return Optional.of(checkpoints.get(player.getUniqueId().toString()));

        } else if (Prkr.inputOutput.getPlayerCheckpoint(player) != null) {
            player.sendMessage("bob");
            PlayerCheckpoint cp = Prkr.inputOutput.getPlayerCheckpoint(player);
            return Optional.of(cp.getLocation());
        }
        else {
            player.sendMessage("bob empty");
            return Optional.empty();
        }
    }
}

/* else if (plugin.getConfig().contains("Player Checkpoints." + player.getUniqueId().toString())) {

            float yaw = plugin.getConfig().getInt("Player Checkpoints." + player.getUniqueId().toString() + ".Yaw");
            float pitch = plugin.getConfig().getInt("Player Checkpoints." + player.getUniqueId().toString() + ".Pitch");
            double x = (Double) plugin.getConfig().getDouble("Player Checkpoints." + player.getUniqueId().toString() + ".X");
            double y = (Double) plugin.getConfig().getDouble("Player Checkpoints." + player.getUniqueId().toString() + ".Y");
            double z = (Double) plugin.getConfig().getDouble("Player Checkpoints." + player.getUniqueId().toString() + ".Z");
            String wrld = plugin.getConfig().getString("Player Checkpoints." + player.getUniqueId().toString() + ".World");

            Location checkpointLoc = new Location(Bukkit.getWorld(wrld), x, y, z, yaw, pitch);
            return Optional.of(checkpointLoc);
        } */