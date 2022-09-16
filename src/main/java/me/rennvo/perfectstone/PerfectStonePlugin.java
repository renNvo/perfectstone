package me.rennvo.perfectstone;

import me.rennvo.perfectstone.commands.impl.PerfectStoneCommand;
import me.rennvo.perfectstone.commands.impl.PerfectStoneLevelCommand;
import me.rennvo.perfectstone.configuration.Configuration;
import me.rennvo.perfectstone.configuration.Messages;
import me.rennvo.perfectstone.listener.BlockBreakListener;
import me.rennvo.perfectstone.model.user.UserImpl;
import me.rennvo.perfectstone.service.DropManager;
import me.rennvo.perfectstone.service.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PerfectStonePlugin extends JavaPlugin {

    private UserManager userManager;
    private DropManager dropManager;

    @Override
    public void onEnable() {
        this.userManager = new UserManager();
        this.dropManager = new DropManager();

        Configuration.INSTANCE.load(this, this.dropManager);
        Messages.INSTANCE.load(this);

        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(userManager, dropManager), this);

        this.getCommand("drop").setExecutor(new PerfectStoneCommand(this, dropManager));
        this.getCommand("level").setExecutor(new PerfectStoneLevelCommand(userManager));

        Bukkit.getOnlinePlayers().forEach(player -> userManager.getUserMap().put(player.getUniqueId(), new UserImpl(player.getUniqueId(), 1, 0, 100)));
    }

    @Override
    public void onDisable() {

    }
}
