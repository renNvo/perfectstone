package me.rennvo.perfectstone.commands.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import me.rennvo.perfectstone.commands.PerfectStonePlayerCommand;
import me.rennvo.perfectstone.configuration.Configuration;
import me.rennvo.perfectstone.model.drop.IDropItem;
import me.rennvo.perfectstone.service.DropManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Set;
import java.util.UUID;

public class PerfectStoneCommand extends PerfectStonePlayerCommand {

    private final Plugin      plugin;
    private final DropManager dropManager;

    private final Set<UUID> concurrentInventories = Sets.newHashSet();

    public PerfectStoneCommand(Plugin plugin, DropManager dropManager) {
        this.plugin      = plugin;
        this.dropManager = dropManager;
    }

    @Override
    public void command(Player player, String[] args) {

        if(concurrentInventories.contains(player.getUniqueId())) {
            return;
        }

        concurrentInventories.add(player.getUniqueId());

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            Inventory inventory = Bukkit.createInventory(null, Configuration.INSTANCE.SLOTS, Configuration.INSTANCE.TITLE);

            for (IDropItem dropItem : dropManager.getDropItems()) {
                ItemStack itemStack = new ItemStack(dropItem.getMaterial(), 1, dropItem.getType());
                ItemMeta  itemMeta  = itemStack.getItemMeta();

                itemMeta.setDisplayName(dropItem.getName());
                itemMeta.setLore(Lists.newArrayList("Chance: " + dropItem.getChance(), "Exp: " + dropItem.getExp(), "Xp: " + dropItem.getXp(), dropItem.getHeight().toString()));

                itemStack.setItemMeta(itemMeta);
                inventory.addItem(itemStack);
            }

            Bukkit.getScheduler().runTask(plugin, () -> {

                concurrentInventories.remove(player.getUniqueId());

                if (player.isOnline()) {
                    player.openInventory(inventory);
                }
            });
        });
    }

    /*@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            return false;
        }

        final Player player = (Player) sender;

        CompletableFuture.supplyAsync(() -> {
            Inventory inventory = Bukkit.createInventory(null, Configuration.INSTANCE.SLOTS, Configuration.INSTANCE.TITLE);

            for (IDropItem dropItem : dropManager.getDropItems()) {
                ItemStack itemStack = new ItemStack(dropItem.getMaterial(), 1, dropItem.getType());
                ItemMeta  itemMeta  = itemStack.getItemMeta();

                itemMeta.setDisplayName(dropItem.getName());
                itemMeta.setLore(Lists.newArrayList("Chance: " + dropItem.getChance(), "Exp: " + dropItem.getExp(), "Xp: " + dropItem.getXp()));

                itemStack.setItemMeta(itemMeta);
                inventory.addItem(itemStack);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return inventory;
        }).thenAccept(inventory -> {
            final Player player = (Player) sender;
            player.openInventory(inventory);
        });
        return false;
    }*/
}
