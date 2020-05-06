package com.luzinsk.prkr.controller;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CheckpointController {
    private HashMap<String, Location> checkpoints;

    public CheckpointController(){
        checkpoints = new HashMap<>();

    }

    public void registerCheckpoint(Player player, Location location){
        checkpoints.put(player.getUniqueId().toString(), location);
    }

    public Location getCheckpoint(Player player){
        return checkpoints.get(player.getUniqueId().toString());

    }

}
