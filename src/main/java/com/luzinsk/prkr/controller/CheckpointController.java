package com.luzinsk.prkr.controller;

import com.luzinsk.prkr.Prkr;
import com.luzinsk.prkr.components.PlayerCheckpoint;
import com.luzinsk.prkr.exceptions.SaveToDatabaseException;
import com.luzinsk.prkr.io.InputOutput;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
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

    public void registerCheckpoint(PlayerCheckpoint cp) throws SQLException, SaveToDatabaseException {
        checkpoints.put(cp.getUUID(), cp.getLocation());
        if (Prkr.inputOutput.getPlayerCheckpoint(cp.getPlayer()) != null)
            Prkr.inputOutput.deletePlayerCheckpoint(cp.getPlayer());
        Prkr.inputOutput.storePlayerCheckpoint(cp);
        cp.getPlayer().sendMessage(Prkr.prkrPrefix + "Checkpoint set.");
        cp.getPlayer().playSound(cp.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }

    public Optional<Location> getCheckpoint(Player player) throws SQLException {

        if(checkpoints.containsKey(player.getUniqueId().toString())) {

            player.playSound(player.getLocation(), Sound.ENTITY_PARROT_IMITATE_ENDERMITE, 1, 1);
            return Optional.of(checkpoints.get(player.getUniqueId().toString()));


        } else if (Prkr.inputOutput.getPlayerCheckpoint(player) != null) {
            PlayerCheckpoint cp = Prkr.inputOutput.getPlayerCheckpoint(player);
            player.playSound(player.getLocation(), Sound.ENTITY_PARROT_IMITATE_ENDERMITE, 1, 1);
            return Optional.of(cp.getLocation());
        }
        else {
            return Optional.empty();
        }
    }
}
