package github.grovre.playerstatistics.commands;

import github.grovre.playerstatistics.leaderboard.Leaderboard;
import github.grovre.playerstatistics.statistics.Tracked;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.UUID;

public class LeaderboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@Nonnull CommandSender sender,
                             @Nonnull Command command,
                             @Nonnull String label,
                             @Nonnull String[] args) {

        if(!(sender instanceof Player)) {
            System.out.println("You need to be a player in order to see the leaderboard.");
            return false;
        }

        Player player = (Player) sender;

        if(!command.toString().equalsIgnoreCase("leaderboard") ||
                !command.toString().equalsIgnoreCase("lb")) {
            System.out.println("Why???");
            System.out.println("  -P-S");
            return true;
        }

        if(args.length < 1) {
            player.sendMessage(ChatColor.RED + "You need to specify what kind of tracked information " +
                            "you are searching the leaderboard for.");
            return false;
        }

        System.out.println("Fetching leaderboard for " + player.getName());
        Tracked tracked = null;
        for(Tracked t : Tracked.values()) {
            if(t.name.equalsIgnoreCase(args[0])) {
                tracked = t;
                break;
            }
        } if(tracked == null) {
            player.sendMessage(ChatColor.RED + args[0] + " is not a valid tracking name.");
            System.out.println("Fetch failed.");
            return false;
        }

        UUID requestedUuid = player.getUniqueId();
        if(args.length > 2) {
            OfflinePlayer requestedPlayer = Bukkit.getOfflinePlayer(args[1]);
            requestedUuid = requestedPlayer.getUniqueId();
        }

        Leaderboard lb = new Leaderboard(requestedUuid, tracked);
        lb.sendTo(player);

        return false;
    }
}
