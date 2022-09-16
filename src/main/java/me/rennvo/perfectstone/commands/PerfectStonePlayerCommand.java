package me.rennvo.perfectstone.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class PerfectStonePlayerCommand extends PerfectStoneCommand{

    public abstract void command(final Player player, final String[] args);

    @Override
    public void command(CommandSender sender, String[] args) {

        if(sender instanceof ConsoleCommandSender) {
            return;
        }

        this.command((Player) sender, args);
    }
}
