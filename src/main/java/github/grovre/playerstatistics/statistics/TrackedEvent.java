package github.grovre.playerstatistics.statistics;

import java.util.UUID;

public interface TrackedEvent {
    default void add(Tracked tracked, UUID uuid, int count) {
        Statistics playerStats = Statistics.getGlobalStats().get(uuid);
        playerStats.getStatisticsMap().merge(tracked, count, Integer::sum);
    }
}
