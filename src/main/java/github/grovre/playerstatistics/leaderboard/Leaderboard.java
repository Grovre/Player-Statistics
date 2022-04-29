package github.grovre.playerstatistics.leaderboard;

import github.grovre.playerstatistics.PlayerStatistics;
import github.grovre.playerstatistics.statistics.Statistics;
import github.grovre.playerstatistics.statistics.Tracked;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Leaderboard {

    private final UUID playerId;
    private final Tracked trackedEvent;
    public final static ChatColor nameColor = ChatColor.WHITE;
    public final static ChatColor statColor = ChatColor.YELLOW;
    public final static int maxNameLength = PlayerStatistics.plugin
            .getConfig().getInt("MaxNameLengthOnLeaderboard");
    public final static int rows = PlayerStatistics.plugin
            .getConfig().getInt("NumberOfPlayersShownOnLeaderboard");

    public Leaderboard(UUID playerId, Tracked trackedEvent) {
        this.trackedEvent = trackedEvent;
        this.playerId = playerId;
    }

    public String header() {
        return this.trackedEvent.color + this.trackedEvent.name + ":";
    }

    public String[] rankings() {
        TreeMap<UUID, Integer> ordered = Statistics.getGlobalStatsByTracked(this.trackedEvent);
        String[] rankRows = new String[rows+2];
        int ownRank = -1;
        boolean ownFound = false;
        Map.Entry<UUID, Integer> entry;
        for(int rank = 0; rank < rows;) {
            entry = ordered.pollLastEntry();
            int score = entry.getValue();
            UUID uuid = entry.getKey();
            String playerName = Bukkit.getOfflinePlayer(uuid).getName();
            if(playerName == null) continue;
            if(playerName.length() > maxNameLength) {
                playerName = playerName.substring(0, maxNameLength);
            }
            if(!ownFound) {
                ownRank = rank;
            }
            if(this.playerId == uuid) {
                ownFound = true;
            }

            rankRows[rank++] = "[#" + rank + "] | " + nameColor + playerName + ": " + statColor + score;
        }

        rankRows[rows] = "--------------";
        rankRows[rows+1] = "[#" + ownRank + "] | " + nameColor + Bukkit.getPlayer(this.playerId);

        return rankRows;
    }

    public void sendTo(Player player) {
        player.sendMessage(this.header());
        player.sendMessage(this.rankings());
    }
}
