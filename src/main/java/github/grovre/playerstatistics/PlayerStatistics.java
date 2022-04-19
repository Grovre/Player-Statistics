package github.grovre.playerstatistics;

import github.grovre.playerstatistics.events.BlockEvents;
import github.grovre.playerstatistics.events.MobEvents;
import github.grovre.playerstatistics.events.PlayerEvents;
import github.grovre.playerstatistics.statistics.StatisticsIoController;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class PlayerStatistics extends JavaPlugin implements StatisticsIoController {

    public static PlayerStatistics plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        this.load();
        this.startAutosave(this.getConfig().getLong("AutosaveInterval"));

        Bukkit.getPluginManager().registerEvents(new BlockEvents(), this);
        Bukkit.getPluginManager().registerEvents(new MobEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.save();
    }

    public void startAutosave(long interval) {
        Bukkit.getScheduler().runTaskTimer(this, this::saveAsync, interval, interval);
    }

    public static File getDatabaseFile() throws IOException {
        File db = new File(PlayerStatistics.plugin.getDataFolder() + "/stats.json");
        db.mkdirs();
        db.createNewFile();
        return db;
    }
}
