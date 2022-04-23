package github.grovre.playerstatistics.statistics;

import org.bukkit.ChatColor;

public enum Tracked {
    BROKEN_BLOCKS (ChatColor.DARK_BLUE),
    PLACED_BLOCKS (ChatColor.BLUE),
    KILLED_MOBS (ChatColor.RED),
    SERVER_JOINS (ChatColor.GREEN),
    BEDTIME (ChatColor.RED),
    KICKS (ChatColor.RED),
    COMMANDS_SENT (ChatColor.LIGHT_PURPLE),
    DROPPED_ITEMS (ChatColor.LIGHT_PURPLE),
    EXP_GAINED (ChatColor.GREEN),
    THROWN_EGGS (ChatColor.YELLOW),
    CAUGHT_FISH (ChatColor.YELLOW),
    MESSAGES_SENT (ChatColor.AQUA),
    CHARACTERS_TYPED (ChatColor.DARK_AQUA);

    Tracked(ChatColor color) {}
}
