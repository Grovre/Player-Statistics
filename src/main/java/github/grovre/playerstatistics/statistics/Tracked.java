package github.grovre.playerstatistics.statistics;

import org.bukkit.ChatColor;

public enum Tracked {
    BROKEN_BLOCKS (ChatColor.DARK_BLUE, "Broken Blocks"),
    PLACED_BLOCKS (ChatColor.BLUE, "Placed Blocks"),
    KILLED_MOBS (ChatColor.RED, "Killed Mobs"),
    SERVER_JOINS (ChatColor.GREEN, "Server Joins"),
    BEDTIME (ChatColor.RED, "Bedtimes"),
    KICKS (ChatColor.RED, "Server Kicks"),
    COMMANDS_SENT (ChatColor.LIGHT_PURPLE, "Sent Commands"),
    DROPPED_ITEMS (ChatColor.LIGHT_PURPLE, "Items Dropped"),
    EXP_GAINED (ChatColor.GREEN, "Experience Gained"),
    THROWN_EGGS (ChatColor.YELLOW, "Eggs Thrown"),
    CAUGHT_FISH (ChatColor.YELLOW, "Fish Caught"),
    MESSAGES_SENT (ChatColor.AQUA, "Sent Messages"),
    CHARACTERS_TYPED (ChatColor.DARK_AQUA, "Sent Characters");

    public final ChatColor color;
    public final String name;
    Tracked(ChatColor color, String trackedName) {
        this.color = color;
        this.name = trackedName;
    }
}
