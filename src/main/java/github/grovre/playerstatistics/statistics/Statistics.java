package github.grovre.playerstatistics.statistics;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import github.grovre.playerstatistics.PlayerStatistics;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Statistics {

    private static Map<UUID, Statistics> globalStats = new HashMap<>();

    private UUID uuid;
    private Map<Tracked, Integer> statisticsMap = new HashMap<>();

    private Statistics(UUID uuid) {
        Statistics potential = globalStats.get(uuid);
        if(potential != null) return;

        this.uuid = uuid;
        for(Tracked v : Tracked.values()) {
            this.statisticsMap.put(v, 0);
        }
    }

    public static Statistics get(UUID uuid) {
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

    protected static void loadGlobalStats() throws IOException {
        File db = null;
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
        File db = null;
        try {
            db = PlayerStatistics.getDatabaseFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Writer wr = new FileWriter(PlayerStatistics.getDatabaseFile());
        Gson gson = new Gson();
        gson.toJson(Collections.unmodifiableMap(globalStats), wr);
        wr.close();
    }
}
