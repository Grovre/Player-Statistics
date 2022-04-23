package github.grovre.playerstatistics.leaderboard;

import github.grovre.playerstatistics.statistics.Statistics;
import github.grovre.playerstatistics.statistics.Tracked;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.TreeMap;
import java.util.UUID;

public class Leaderboard {

    private final UUID playerId;
    private final Tracked trackedEvent;
    public final static ChatColor nameColor = ChatColor.WHITE;
    public final static ChatColor statColor = ChatColor.YELLOW;

    public Leaderboard(UUID playerId, Tracked trackedEvent) {
        this.trackedEvent = trackedEvent;
        this.playerId = playerId;
    }

    public String header() {
        return this.trackedEvent.color + this.trackedEvent.name + ":";
    }

    public String[] rankings(int rows, int maxNameLength) {
        TreeMap<UUID, Integer> ordered = Statistics.getGlobalStatsByTracked(this.trackedEvent);
        String[] rankRows = new String[rows];
        for(int rank = 0; rank < rows;) {
            String playerName = Bukkit.getOfflinePlayer(this.playerId).getName();
            int score = ordered.pollLastEntry().getValue();
            if(playerName == null) continue;
            if(playerName.length() > maxNameLength) {
                playerName = playerName.substring(0, maxNameLength);
            }

            rankRows[rank++] = "[#" + rank + "] | " + nameColor + playerName + ": " + statColor + score;
        }

        return rankRows;
    }

    public void send(Player player) {
        player.sendMessage(this.header());
        // TODO: 4/23/22 Add config options for leaderboard stuff
        player.sendMessage(this.rankings(10, Integer.MAX_VALUE));
    }
}
