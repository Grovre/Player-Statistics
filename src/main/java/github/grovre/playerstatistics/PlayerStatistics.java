package github.grovre.playerstatistics;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class PlayerStatistics extends JavaPlugin {

    public static PlayerStatistics plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static File getDatabaseFile() throws IOException {
        File db = new File(PlayerStatistics.plugin.getDataFolder() + "/stats.json");
        db.mkdirs();
        db.createNewFile();
        return db;
    }
}
