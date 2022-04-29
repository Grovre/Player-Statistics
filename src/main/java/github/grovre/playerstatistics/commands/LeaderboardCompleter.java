package github.grovre.playerstatistics.commands;

import github.grovre.playerstatistics.statistics.Tracked;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeaderboardCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender,
                                      @Nonnull Command command,
                                      @Nonnull String label,
                                      @Nonnull String[] args) {

        List<String> tabs = Arrays.stream(Tracked.values())
                .map(t -> t.name)
                .collect(Collectors.toList());

        return tabs;
    }
}
