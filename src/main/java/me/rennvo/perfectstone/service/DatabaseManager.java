package me.rennvo.perfectstone.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.plugin.Plugin;

public class DatabaseManager {

    private HikariDataSource dataSource;

    public DatabaseManager(Plugin plugin) {
        HikariConfig config = new HikariConfig(plugin.getDataFolder() + "database.properties");
        this.dataSource = new HikariDataSource(config);
    }

}
