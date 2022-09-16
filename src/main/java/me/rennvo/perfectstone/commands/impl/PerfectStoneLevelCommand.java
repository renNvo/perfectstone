package me.rennvo.perfectstone.commands.impl;

import me.rennvo.perfectstone.commands.PerfectStonePlayerCommand;
import me.rennvo.perfectstone.configuration.Messages;
import me.rennvo.perfectstone.model.user.IUser;
import me.rennvo.perfectstone.service.UserManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

public class PerfectStoneLevelCommand extends PerfectStonePlayerCommand {

    private final UserManager userManager;

    public PerfectStoneLevelCommand(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public void command(Player player, String[] args) {
        final IUser user = userManager.get(player.getUniqueId());

        String message = Messages.INSTANCE.LEVEL_INFO;

        message = StringUtils.replace(message, "{LEVEL}", Integer.toString(user.getLevel()));
        message = StringUtils.replace(message, "{EXP}", Double.toString(user.getExp()));
        message = StringUtils.replace(message, "{NEED}", Double.toString(user.getNeed()));
        message = StringUtils.replace(message, "{LEFT}", Double.toString(user.getNeed() - user.getExp()));

        player.sendMessage(message);
    }
}
