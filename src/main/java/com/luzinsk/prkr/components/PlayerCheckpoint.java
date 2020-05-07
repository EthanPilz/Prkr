package com.luzinsk.prkr.components;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerCheckpoint {

    private Player player;
    private String uuid;
    private Location location;

    public PlayerCheckpoint(Player plr, Location loc) {
        uuid = plr.getUniqueId().toString();
        location = loc;
        player = plr;
    }

    public String getUUID() {
        return uuid;
    }

    public Location getLocation() {
        return location;
    }

    public Player getPlayer() { return player; }
}
