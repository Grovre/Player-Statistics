package github.grovre.playerstatistics.statistics;

import java.io.IOException;

public interface StatisticsIoController {
    default void save() {
        try {
            Statistics.saveGlobalStats();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default void saveAsync() {
        Statistics.saveGlobalStatsAsync();
    }

    default void load() {
        try {
            Statistics.loadGlobalStats();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default void loadAsync() {
        Statistics.loadGlobalStatsAsync();
    }
}
