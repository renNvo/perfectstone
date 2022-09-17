package me.rennvo.perfectstone.listener;

import com.google.common.collect.Lists;
import me.rennvo.perfectstone.configuration.Messages;
import me.rennvo.perfectstone.model.drop.IDropItem;
import me.rennvo.perfectstone.model.user.IUser;
import me.rennvo.perfectstone.service.DropManager;
import me.rennvo.perfectstone.service.UserManager;
import me.rennvo.perfectstone.utilities.MathUtilities;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
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

        int exp = 0;

        int fortune = 0;
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if(itemInMainHand != null) fortune = itemInMainHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);

        for (IDropItem dropItem : this.dropManager.getDropItems()) {

            if(!dropItem.getHeight().isValid(block.getY())) continue;

            if(dropItem.getChance() < MathUtilities.random()) {
                continue;
            }

            exp += dropItem.getExp();
            player.giveExp(dropItem.getXp());

            final ItemStack droppedStack = dropItem.build(fortune);

            player.getInventory().addItem(droppedStack);

            String message = Messages.INSTANCE.DROP_MESSAGE;

            message = StringUtils.replace(message, "{NAME}", dropItem.getName());
            message = StringUtils.replace(message, "{AMOUNT}", Integer.toString(droppedStack.getAmount()));

            player.sendMessage(message);
        }

        if(exp != 0) {
            IUser user = this.userManager.get(player.getUniqueId());

            if (user.getExp() >= user.getNeed()) {
                do {
                    user.addLevel(1);
                    user.setExp(user.getExp() - user.getNeed());
                    user.setNeed(user.getNeed() + 100); // TODO conditions

                    String message = Messages.INSTANCE.LEVEL_UP;

                    message = StringUtils.replace(message, "{LEVEL}", Integer.toString(user.getLevel()));
                    message = StringUtils.replace(message, "{EXP}", Double.toString(user.getExp()));
                    message = StringUtils.replace(message, "{NEED}", Double.toString(user.getNeed()));
                    message = StringUtils.replace(message, "{LEFT}", Double.toString(user.getNeed() - user.getExp()));

                    player.sendMessage(message);
                } while (user.getExp() >= user.getNeed());
            }
        }
    }
}
