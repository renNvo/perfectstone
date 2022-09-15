package me.rennvo.perfectstone.configuration;

import com.google.common.collect.Lists;
import me.rennvo.perfectstone.model.drop.CustomDropItem;
import me.rennvo.perfectstone.model.drop.DropItemImpl;
import me.rennvo.perfectstone.service.DropManager;
import me.rennvo.perfectstone.utilities.ChatUtilities;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Configuration {

    INSTANCE;

    public String TITLE;
    public int SLOTS;

    public void load(Plugin plugin, DropManager dropManager) {
        File file = new File(plugin.getDataFolder(), "config.yml");

        if (!file.exists()) {
            plugin.saveResource("config.yml", true);
        }

        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        TITLE = ChatUtilities.colored(yaml.getString("title"));

        yaml.getConfigurationSection("drops").getKeys(false).forEach(item -> {
            String   name     = ChatUtilities.colored(yaml.getString("drops." + item + ".name"));
            Material material = Material.getMaterial(yaml.getString("drops." + item + ".material").toUpperCase());
            short     type     = (short) yaml.getInt("drops." + item + ".material_type");
            double   chance   = yaml.getDouble("drops." + item + ".chance");
            double   exp      = yaml.getDouble("drops." + item + ".exp");
            int      xp       = yaml.getInt("drops." + item + ".xp");

            if (yaml.getString("drops." + item + ".type").equalsIgnoreCase("normal")) {
                dropManager.getDropItems().add(new DropItemImpl(name, material, type, chance, exp, xp));
            } else {

                List<String> lore = yaml.getStringList("drops." + item + ".lore");
                if(lore == null) {
                    lore = Collections.emptyList();
                } else {
                    lore = lore.stream()
                            .map(ChatUtilities::colored)
                            .collect(Collectors.toList());
                }

                dropManager.getDropItems().add(new CustomDropItem(name, material, type, chance, exp, xp, lore));
            }
        });

        SLOTS = slotsGenerator(dropManager.getDropItems().size()) + 9;
    }

    private int slotsGenerator(int count) {
        if(count % 9 != 0) {
            return slotsGenerator(count + 1);
        } else {
            return count;
        }
    }

}
