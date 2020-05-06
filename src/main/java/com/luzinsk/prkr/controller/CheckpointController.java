package com.luzinsk.prkr.controller;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Optional;

public class CheckpointController {
    private HashMap<String, Location> checkpoints;

    public CheckpointController(){
        checkpoints = new HashMap<>();

    }

    public void registerCheckpoint(Player player, Location location){
        checkpoints.put(player.getUniqueId().toString(), location);
    }

    public Optional<Location> getCheckpoint(Player player){
        if(checkpoints.containsKey(player.getUniqueId().toString())) {
            return Optional.of(checkpoints.get(player.getUniqueId().toString()));
        } else {
            return Optional.empty();
        }
    }
}
