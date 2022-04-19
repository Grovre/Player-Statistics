package github.grovre.playerstatistics.events;

import github.grovre.playerstatistics.statistics.Tracked;
import github.grovre.playerstatistics.statistics.TrackedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

public class BlockEvents implements Listener, TrackedEvent {

    @EventHandler
    public void OnBlockBreakEvent(BlockBreakEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        this.add(Tracked.BROKEN_BLOCKS, uuid, 1);
    }

    @EventHandler
    public void OnBlockPlaceEvent(BlockPlaceEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        this.add(Tracked.PLACED_BLOCKS, uuid, 1);
    }
}
