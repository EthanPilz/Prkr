package com.luzinsk.prkr.listener;

import com.luzinsk.prkr.Prkr;
import com.luzinsk.prkr.components.PlayerCheckpoint;
import com.luzinsk.prkr.exceptions.SaveToDatabaseException;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class PlayerSignListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void checkpointSignClick(PlayerInteractEvent event) throws SQLException, SaveToDatabaseException {

        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        BlockState clickedState = event.getClickedBlock().getState();

        if (clickedState instanceof Sign) {
            Sign sign = (Sign) clickedState;
            String line1 = sign.getLine(1);

            if (line1.equalsIgnoreCase("[Checkpoint]")) {
                Prkr.checkpointController.registerCheckpoint(new PlayerCheckpoint(player, player.getLocation()));

                player.getInventory().clear();
                player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
            }
        }
    }
}
