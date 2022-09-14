package me.rennvo.perfectstone;

import me.rennvo.perfectstone.inventory.StoneInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PerfectStoneCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof ConsoleCommandSender) {
            return false;
        }

        final Player player = (Player) sender;
        final Inventory inventory = new StoneInventory().getInventory();

        player.openInventory(inventory);

        return false;
    }
}
