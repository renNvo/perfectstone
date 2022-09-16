package me.rennvo.perfectstone.utilities;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class ChatUtilities {

    public static String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String buildMessageFromList(List<String> texts) {
        return texts.stream()
                .map(ChatUtilities::colored)
                .collect(Collectors.joining("\n"));
    }

}
