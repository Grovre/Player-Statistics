package github.grovre.playerstatistics.statistics;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import github.grovre.playerstatistics.PlayerStatistics;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Statistics {

    private static Map<UUID, Statistics> globalStats = new HashMap<>();

    private final UUID uuid;
    private final Map<Tracked, Integer> statisticsMap;

    private Statistics(UUID uuid) {
        assert uuid != null;
        this.uuid = uuid;
        this.statisticsMap = new HashMap<>();
        globalStats.put(uuid, this);
    }

    @Nonnull
    public static Statistics get(UUID uuid) {
        assert uuid != null;
        Statistics stats = globalStats.get(uuid);
        if(stats != null) return stats;

        return new Statistics(uuid);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public Map<Tracked, Integer> getStatisticsMap() {
        return this.statisticsMap;
    }

    public static Map<UUID, Statistics> getGlobalStats() {
        return Collections.unmodifiableMap(globalStats);
    }

    protected static void loadGlobalStatsAsync() {
        Bukkit.getScheduler().runTaskAsynchronously(PlayerStatistics.plugin, () -> {
            try {
                loadGlobalStats();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    protected static void saveGlobalStatsAsync() {
        Bukkit.getScheduler().runTaskAsynchronously(PlayerStatistics.plugin, () -> {
            try {
                saveGlobalStats();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    protected static void loadGlobalStats() throws IOException {
        File db;
        try {
            db = PlayerStatistics.getDatabaseFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        Reader r = new FileReader(db);
        HashMap<UUID, Statistics> globalStats =
                gson.fromJson(r, new TypeToken<HashMap<UUID, Statistics>>(){}.getType());
        Statistics.globalStats = globalStats == null ? new HashMap<>() : globalStats;
        r.close();
    }

    protected static void saveGlobalStats() throws IOException {
        File db;
        try {
            db = PlayerStatistics.getDatabaseFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Writer wr = new FileWriter(db, true);
        Gson gson = new Gson();
        gson.toJson(Collections.unmodifiableMap(globalStats), wr);
        wr.close();
    }
}
