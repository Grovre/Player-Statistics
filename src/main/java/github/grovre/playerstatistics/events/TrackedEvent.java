package github.grovre.playerstatistics.events;

import github.grovre.playerstatistics.statistics.Statistics;
import github.grovre.playerstatistics.statistics.Tracked;

import java.util.UUID;

public interface TrackedEvent {
    default void add(Tracked tracked, UUID uuid, int count) {
        Statistics playerStats = Statistics.getGlobalStats().get(uuid);
        playerStats.getStatisticsMap().merge(tracked, count, Integer::sum);
    }
}
