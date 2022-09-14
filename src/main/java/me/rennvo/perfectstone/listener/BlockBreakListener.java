package me.rennvo.perfectstone.listener;

import com.google.common.collect.Lists;
import me.rennvo.perfectstone.model.drop.IDropItem;
import me.rennvo.perfectstone.model.user.IUser;
import me.rennvo.perfectstone.service.DropManager;
import me.rennvo.perfectstone.service.UserManager;
import me.rennvo.perfectstone.utilities.MathUtilities;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BlockBreakListener implements Listener {

    private final UserManager userManager;
    private final DropManager dropManager;

    public BlockBreakListener(UserManager userManager, DropManager dropManager) {
        this.userManager = userManager;
        this.dropManager = dropManager;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {

        final Player player = event.getPlayer();
        final Block  block  = event.getBlock();

        if(block.getType() != Material.STONE) {
            return;
        }

        List<IDropItem> droppedItems = null;

        for (IDropItem dropItem : this.dropManager.getDropItems()) {

            if(dropItem.getChance() < MathUtilities.random()) {
                continue;
            }

            if(droppedItems == null) {
                droppedItems = Lists.newArrayList();
            }

            droppedItems.add(dropItem);
        }

        if(droppedItems == null) {
            return;
        }

        IUser user = this.userManager.get(player.getUniqueId());

        for (IDropItem droppedItem : droppedItems) {
            user.addExp(droppedItem.getExp());
            player.giveExp(droppedItem.getXp());

            player.getInventory().addItem(new ItemStack(droppedItem.getMaterial(), 1));
            player.sendMessage("You dropped 1x" + droppedItem.getName());
        }

        while(user.getExp() >= user.getNeed()) {
            user.addLevel(1);
            user.setExp(user.getExp() - user.getNeed());
            user.setNeed(user.getNeed() + 100); // TODO conditions
        }

    }
}
