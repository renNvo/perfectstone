package me.rennvo.perfectstone.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class PerfectStoneCommand implements CommandExecutor {

    public abstract void command(final CommandSender sender, final String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        this.command(sender, args);
        return false;
    }
}
