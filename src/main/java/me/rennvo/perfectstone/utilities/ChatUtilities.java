package me.rennvo.perfectstone.utilities;

import org.bukkit.ChatColor;

public class ChatUtilities {

    public static String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
