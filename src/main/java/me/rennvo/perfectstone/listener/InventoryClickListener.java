package me.rennvo.perfectstone.listener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class InventoryClickListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {

        final HumanEntity whoClicked = event.getWhoClicked();
        final Inventory   clickedInventory = event.getClickedInventory();
        final PlayerInventory inventory = whoClicked.getInventory();

    }
}
