package me.rennvo.perfectstone.configuration;

import me.rennvo.perfectstone.utilities.ChatUtilities;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public enum Messages {

    INSTANCE;

    public String DROP_MESSAGE;
    public String LEVEL_UP;

    public void load(Plugin plugin) {
        File file = new File(plugin.getDataFolder(), "messages.yml");

        if(!file.exists()) {
            plugin.saveResource("messages.yml", true);
        }

        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        DROP_MESSAGE = ChatUtilities.colored(yaml.getString("dropMessage"));
        LEVEL_UP = ChatUtilities.colored(yaml.getString("levelUp"));
    }

}
